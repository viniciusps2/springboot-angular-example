describe('ModalService Spec', function () {
  var ModalService, uibModal, sandbox

  beforeEach(function () {
    sandbox = sinon.sandbox.create()

    module('flight-ui')

    inject(function (_ModalService_, _$uibModal_) {
      ModalService = _ModalService_
      uibModal = _$uibModal_
    })
  })

  afterEach(function () {
    sandbox.verify()
    sandbox.restore()
  })

  it('.view: should open', function (done) {
    var options = {
      templateUrl: 'app/flight/modal.html',
      title: 'Flight nº 1',
      onSave: function () {
        return Promise.resolve()
      }
    }

    uibOpenMock(options)

    ModalService.open(options).then(function (res) {
      done()
    }).catch(function (error) {
      fail(error)
      done()
    })
  })

  function uibOpenMock (expectedOptions) {
    sandbox.stub(uibModal, 'open').callsFake(function (opts) {
      expect(opts.templateUrl).toEqual(expectedOptions.templateUrl)
      return {result: opts.resolve.modalContext().onSave({name: 'abc'})}
    })
  }
})
