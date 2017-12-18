describe('Navbar Directive', function () {
  var element, scope, compile
  beforeEach(module('flight-ui'))

  beforeEach(inject(function ($compile, $rootScope, $templateCache) {
    scope = $rootScope.$new()
    compile = $compile
    $templateCache.put('components/navbar/navbar.html', '<div class="navbar-custom">Test</div>')
  }))

  it('should load navbar view', function () {
    element = '<navbar></navbar>'
    element = compile(element)(scope)
    scope.$digest()

    expect(element.length).toEqual(1)
    expect(element['0'].tagName).toContain('NAVBAR')
    expect(element['0'].firstChild.className).toEqual('navbar-custom')
  })
})
