describe('HomeCtrl Spec', function () {
  var scope, controller, location, Airline

  beforeEach(function () {
    sandbox = sinon.sandbox.create()

    module('flight-ui')

    inject(function ($controller, $rootScope, $location, _Airline_) {
      scope = $rootScope.$new()
      Airline = _Airline_
      location = $location
      controller = $controller
    })
  })

  afterEach(function () {
    sandbox.verify()
    sandbox.restore()
  })

  function NewController () {
    return controller('HomeCtrl', {
      $scope: scope,
      $location: location,
      Airline: Airline,
    })
  }

  describe('initialize', function () {
    it('should load flights', function () {
      sandbox.mock(Airline)
        .expects('list')
        .returns({then: function() {}})
        .atLeast(1)
        .atMost(1)

      NewController()
    })
  })

  describe('openAirline', function () {
    it('should load flights', function () {
      var airlineId = 1
      sandbox.mock(location)
        .expects('path')
        .withArgs('/flight/airline/' + airlineId)
        .returns()
        .atLeast(1)
        .atMost(1)

      var HomeCtrl = NewController()
      HomeCtrl.openAirline(airlineId)
    })
  })
})

