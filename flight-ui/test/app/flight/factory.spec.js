describe('Flight Factory Spec', function () {
  var q, httpBackend, rootScope, Flight

  var environment = {flightApi: 'http://localhost:3006'}

  beforeEach(function () {
    module('flight-ui', function ($provide) {
      $provide.constant('environment', environment)
    })

    inject(function ($q, $httpBackend, $rootScope, _Flight_) {
      Flight = _Flight_
      httpBackend = $httpBackend
      rootScope = $rootScope
      q = $q
    })
  })

  describe('findById', function () {
    it('should call API and return', function () {
      httpBackend
        .whenGET(environment.flightApi + '/flights/5')
        .respond(200, {id: 5})

      Flight.findById(5)

      httpBackend.flush()
      rootScope.$apply()
    })
  })

})
