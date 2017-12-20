describe('FlightListCtrl Spec', function () {
  var scope, controller, q, Flight, FlightListCtrl, FlightService, Flight

  var AIRLINE_ID = 3

  beforeEach(function () {
    sandbox = sinon.sandbox.create()

    module('flight-ui')

    inject(function ($controller, $rootScope, _FlightService_, _Flight_, _allFlightStatus_) {
      scope = $rootScope.$new()
      FlightService = _FlightService_
      Flight = _Flight_

      FlightListCtrl = $controller('FlightListCtrl', {
        $scope: scope,
        $routeParams: {airlineId: AIRLINE_ID},
        FlightService: FlightService,
        allFlightStatus: _allFlightStatus_
      })
    })
  })

  afterEach(function () {
    sandbox.verify()
    sandbox.restore()
  })

  describe('.search', function () {
    it('should list flights', function (done) {
      var searchParams = {aircraftId: 5}
      var mockedResult = {content: [{id: 2}, {id: 3}]}

      sandbox.mock(Flight)
        .expects('search')
        .withArgs(searchParams, AIRLINE_ID)
        .resolves(mockedResult)
        .atLeast(1)

      FlightListCtrl.search(searchParams).then(function (res) {
        expect(FlightListCtrl.flights[0].id).toEqual(2)
        expect(FlightListCtrl.flights[1].id).toEqual(3)
        done()
      }).catch(function (error) {
        fail(error)
        done()
      })
    })
  })

  describe('.viewFlight', function () {
    it('should call flight service to open flight', function (done) {
      var flightId = 2

      sandbox.mock(FlightService)
        .expects('view')
        .withArgs(flightId)
        .resolves()
        .atLeast(1)

      FlightListCtrl.viewFlight(flightId).then(function (res) {
        done()
      }).catch(function (error) {
        fail(error)
        done()
      })
    })
  })
})
