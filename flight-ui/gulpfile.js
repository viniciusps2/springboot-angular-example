'use strict'
const path = require('path')
const gulp = require('gulp')
const connect = require('gulp-connect')
const compression = require('compression')

const jshint = require('gulp-jshint')
const inject = require('gulp-inject')
const wiredep = require('wiredep').stream
const preprocess = require('gulp-preprocess')

const concat = require('gulp-concat')
const rename = require('gulp-rename')
const uglify = require('gulp-uglify')
const minifycss = require('gulp-minify-css')
const sourcemaps = require('gulp-sourcemaps')

const getFullPath = (file) => path.join(__dirname, file)

const jsSource = [
  getFullPath('public/**/*.js'),
  '!' + getFullPath('public/bower_components/**/*.js'),
  '!' + getFullPath('public/_dist/*.js')
]

const jsSourceVendor = [
  getFullPath('public/bower_components/angular/**/*.min.js'),
  getFullPath('public/bower_components/angular-bootstrap/ui-bootstrap.min.js'),
  getFullPath('public/bower_components/**/*.min.js')
]

const jsSourceDist = [
  getFullPath('public/_dist/*.min.js'),
  '!' + getFullPath('public/bower_components/**/*.js')
]

const htmlSource = [
  getFullPath('public/**/*.html')
]

const cssSource = [
  getFullPath('public/**/*.css'),
  '!' + getFullPath('public/bower_components/**/*.css'),
  '!' + getFullPath('public/_dist/*.css')
]

const cssSourceVendor = [
  getFullPath('public/bower_components/**/*.min.css')
]

const cssSourceDist = [
  getFullPath('public/_dist/**/*.min.css'),
  '!' + getFullPath('public/bower_components/**/*.css')
]

const fontsSource = [
  getFullPath('public/bower_components/**/*.{eot,svg,ttf,woff,woff2}')
]

const specSource = [
  getFullPath('test/**/*.js')
]

const distFolder = getFullPath('public/_dist')
let isDist = false

const KARMA_CONF_FILE = getFullPath('test/karma.conf.js')
const specFolder = getFullPath('test/')
const specFiles = "'./**/*spec.js'"

gulp.task('serve', ['inject'], () => {
  const browserSync = require('browser-sync').create()
  browserSync.init({
    open: false,
    notify: false,
    port: process.env.PORT || 3001,
    server: {
      baseDir: './public'
    }
  })
  gulp.watch(cssSource).on('change', () => {
    gulp.start('inject')
    browserSync.reload()
  })
  gulp.watch(jsSource).on('change', () => {
    gulp.start('jshint', 'inject')
    browserSync.reload()
  })
  gulp.watch(htmlSource).on('change', browserSync.reload)
})

gulp.task('serve-prod', ['build'], () => {
  connect.server({
    root: 'dist',
    port: process.env.PORT || 3000,
    livereload: false,
    middleware: function () {
      return [
        compression()
      ]
    }
  })
})

gulp.task('serveMin', ['isDist', 'serve'])

// jshint
gulp.task('jshint', () => {
  const JSHINT_JS = jsSource.concat(specSource)

  return gulp.src(JSHINT_JS)
    .pipe(jshint())
    .pipe(jshint.reporter('default'))
})

// Inject
gulp.task('inject', () => {
  let options = {
    read: false,
    ignorePath: ['public'],
    addRootSlash: false
  }

  let wiredepOptions = {
    directory: getFullPath('public/bower_components'),
    exclude: getFullPath('public/bower_components/angular-mocks/angular-mocks.js')
  }

  return gulp.src(getFullPath('public/index.html'))
    .pipe(inject(gulp.src(jsSource), options))
    .pipe(inject(gulp.src(cssSource), options))
    .pipe(wiredep(wiredepOptions))
    .pipe(gulp.dest(getFullPath('public')))
})

// Inject-dist
gulp.task('inject-dist', ['minify'], () => {
  let options = {
    read: false,
    ignorePath: ['public'],
    addRootSlash: false
  }

  return gulp.src(getFullPath('public/index.html'))
    .pipe(wiredep({exclude: ['.*']}))
    .pipe(inject(gulp.src(jsSourceDist), options))
    .pipe(inject(gulp.src(cssSourceDist), options))
    .pipe(gulp.dest(getFullPath('public')))
})

// Karma
gulp.task('inject-karma', () => {
  // Inject all SOURCEJS files
  function injectAppJsFiles (filepath, i, length) {
    return "'.." + filepath + "'" + (i + 1 < length ? ',\n            ' : '')
  }

  // Inject SPEC files
  function injectSpecFiles (i, length, extracted) {
    if (i + 1 === length) {
      extracted = extracted + ',\n            ' + specFiles
    }
    return extracted
  }

  gulp.src(KARMA_CONF_FILE)
    .pipe(inject(gulp.src(jsSource, {read: false}), {
      starttag: 'files: [',
      endtag: ']',
      transform: function (filepath, file, i, length) {
        const extracted = injectAppJsFiles(filepath, i, length)
        return injectSpecFiles(i, length, extracted)
      }
    })).pipe(gulp.dest(specFolder))
})

gulp.task('test', ['inject-karma'], function (done) {
  // Test
  const argv = require('yargs').argv
  const Server = require('karma').Server
  let singleRun = true
  let browsers = ['PhantomJS']

  if (argv.d) { // argument to debug
    singleRun = false
    browsers = ['Chrome']
  } else if (argv.w) { // watch
    singleRun = false
  }

  new Server({
    browsers: browsers,
    configFile: KARMA_CONF_FILE,
    singleRun: singleRun
  }, function (karmaExitStatus) {
    if (karmaExitStatus) process.exit(1)
    done()
  }).start()
})

// Minify
gulp.task('minify', ['minify-js', 'minify-css', 'minify-vendor-js', 'minify-vendor-css'])

gulp.task('minify-js', () => {
  return gulp.src(jsSource)
    .pipe(concat('all.js'))
    .pipe(preprocess())
    .pipe(gulp.dest(distFolder))
    .pipe(rename('all.min.js'))
    .pipe(uglify())
    .pipe(gulp.dest(distFolder))
})

gulp.task('minify-vendor-js', () => {
  return gulp.src(jsSourceVendor)
    .pipe(concat('_vendor.min.js'))
    .pipe(preprocess())
    .pipe(gulp.dest(distFolder))
})

gulp.task('minify-css', () => {
  return gulp.src(cssSource)
    .pipe(concat('all.css'))
    .pipe(gulp.dest(distFolder))
    .pipe(rename('all.min.css'))
    .pipe(sourcemaps.init())
    .pipe(minifycss())
    .pipe(sourcemaps.write())
    .pipe(gulp.dest(distFolder))
})

gulp.task('minify-vendor-css', () => {
  return gulp.src(cssSourceVendor)
    .pipe(concat('_vendor.min.css'))
    .pipe(gulp.dest(distFolder))
})

gulp.task('fonts', () => {
  return gulp.src(fontsSource)
    .pipe(rename({dirname: ''}))
    .pipe(gulp.dest('dist/fonts'))
})

gulp.task('build', ['isDist', 'jshint', 'minify', 'inject-dist', 'fonts'], () => {
  const source = ['public/**/*.*']
  return gulp.src(source)
    .pipe(gulp.dest('dist'))
})

gulp.task('isDist', () => {
  isDist = true
})
