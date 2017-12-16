module.exports = function (config) {
  config.set({
    basePath: '',
    port: 9876,
    frameworks: ['wiredep', 'jasmine', 'sinon', 'promise'],
    colors: true,
    logLevel: config.LOG_INFO,
    files: ['../public/app.js',            '../public/routes.js',            '../public/components/environment.js',            '../public/app/flight/controller.js',            '../public/app/flight/factory.js',            '../public/components/modal/controller.js',            '../public/components/navbar/directive.js',            './**/*spec.js'],
    preprocessors: {
      '../public/!(bower_components|assets)/**/!(*.mock|*.spec).js': 'coverage'
    },
    browsers: ['PhantomJS'],
    reporters: ['progress', 'coverage', 'spec'],
    autoWatch: true,
    singleRun: false,
    wiredep: {
      devDependencies: true,
      dependencies: true,
      cwd: '..'
    },
    plugins: [
      'karma-wiredep',
      'karma-jasmine',
      'karma-phantomjs-launcher',
      'karma-coverage',
      'karma-sinon',
      'karma-promise',
      'karma-spec-reporter'
    ],
    coverageReporter: {
      reporters: [
        {type: 'html'},
        {type: 'lcov'},
        {type: 'text-summary'},
        {type: 'lcovonly'}
      ],
      dir: '../coverage'
    }
  })
}
