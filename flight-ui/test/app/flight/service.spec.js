describe('FlightService Spec', function () {
  var FlightService, Flight, ModalService, sandbox

  var FLIGHT_ID = 123

  beforeEach(function () {
    sandbox = sinon.sandbox.create()

    module('flight-ui')

    inject(function (_FlightService_, _Flight_, _ModalService_) {
      FlightService = _FlightService_
      Flight = _Flight_
      ModalService = _ModalService_
    })
  })

  afterEach(function () {
    sandbox.verify()
    sandbox.restore()
  })

  it('.view: should open modal and view flight', function (done) {
    flightMock('findById', FLIGHT_ID, {id: FLIGHT_ID})
    modalServiceOpenMock()

    FlightService.view(FLIGHT_ID).then(function (res) {
      done()
    }).catch(function (error) {
      fail(error)
      done()
    })
  })

  function modalServiceOpenMock () {
    sandbox.mock(ModalService)
      .expects('open')
      .resolves()
      .atLeast(1)
      .atMost(1)
  }

  function flightMock (method, id, result) {
    sandbox.mock(Flight)
      .expects(method)
      .withArgs(id)
      .resolves(result)
      .atLeast(1)
      .atMost(1)
  }
})
