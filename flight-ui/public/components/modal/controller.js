;(function () {
  'use strict'

  angular.module('flight-ui').controller('ModalCtrl', ModalCtrl)

  ModalCtrl.$inject = ['$uibModalInstance', 'modalContext']

  function ModalCtrl ($uibModalInstance, modalContext) {
    var vm = this

    angular.extend(this, modalContext, {
      save: save,
      close: close
    })

    function save () {
      vm.errorMessage = ''

      if (modalContext.onSave) {
        return modalContext.onSave(vm.data)
          .then(function () {
            close()
          })
          .catch(function (error) {
            vm.errorMessage = error.data.message
          })
      }

      close()
    }

    function close() {
      $uibModalInstance.close(true)
    }
  }
})()
