;(function () {
  'use strict'

  angular.module('flight-ui')
  	.directive('pilotSelect', PilotSelect)
  	.controller('PilotSelectController', PilotSelectController)

  function PilotSelect () {
    return {
      restrict: 'AE',
      templateUrl: 'app/pilot/select.html',
      controllerAs: 'vm',
      controller: 'PilotSelectController',
      scope: {
      	ngModel: '='
      }
    }
  }

  PilotSelectController.$inject = ['Pilot']

  function PilotSelectController(Pilot) {
  	var vm = this
  	vm.$onInit = initialize

  	function initialize () {
  		Pilot.list().then(function (pilots) {
  			vm.pilots = pilots
  		})
  	}
  }
})()
