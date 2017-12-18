;(function () {
  'use strict'

  angular.module('flight-ui')
  	.directive('aircraftSelect', AircraftSelect)
  	.controller('AircraftSelectController', AircraftSelectController)

  function AircraftSelect () {
    return {
      restrict: 'AE',
      templateUrl: 'app/aircraft/select.html',
      controllerAs: 'vm',
      controller: 'AircraftSelectController',
      scope: {
      	ngModel: '='
      }
    }
  }

  AircraftSelectController.$inject = ['Aircraft']

  function AircraftSelectController(Aircraft) {
  	var vm = this
  	vm.$onInit = initialize

  	function initialize () {
  		Aircraft.list().then(function (aircrafts) {
  			vm.aircrafts = aircrafts
  		})
  	}
  }
})()
