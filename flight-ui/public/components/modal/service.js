;(function () {
  'use strict'

  angular.module('flight-ui').service('ModalService', ModalService)

  ModalService.$inject = ['$uibModal']

  function ModalService ($uibModal) {
    return {
      open: open
    }

    function open (context) {
      var modalInstance = $uibModal.open({
        animation: true,
        templateUrl: context.templateUrl,
        controller: 'ModalCtrl',
        controllerAs: 'vm',
        resolve: {
          modalContext: function () {
            return context
          }
        }
      })
      return modalInstance.result
    }
  }
})()
