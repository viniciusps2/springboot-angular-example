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

  describe('/flight/:id', function () {
    it('should load flight by id', function () {
      expect(route.routes['/flight/:id'].controller).toEqual('FlightListCtrl')
      expect(route.routes['/flight/:id'].templateUrl).toEqual('app/flight/list.html')
    })
  })
})
