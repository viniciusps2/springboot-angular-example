describe('Routes', function () {
  var route, location, rootScope

  beforeEach(function () {
    module('flight-ui')
  })

  beforeEach(inject(
    function ($route, $location, $rootScope, $httpBackend) {
      location = $location
      route = $route
      rootScope = $rootScope
    }
  ))

  describe('/flight/airline/:airlineId', function () {
    it('should load airline by id', function () {
      expect(route.routes['/flight/airline/:airlineId'].controller).toEqual('FlightListCtrl')
      expect(route.routes['/flight/airline/:airlineId'].templateUrl).toEqual('app/flight/list.html')
    })
  })
})
