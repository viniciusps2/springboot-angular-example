;(function () {
  'use strict'

  angular.module('flight-ui')
  	.directive('airportSelect', AirportSelect)
  	.controller('AirportSelectController', AirportSelectController)

  function AirportSelect () {
    return {
      restrict: 'AE',
      templateUrl: 'app/airport/select.html',
      controllerAs: 'vm',
      controller: 'AirportSelectController',
      scope: {
      	ngModel: '='
      }
    }
  }

  AirportSelectController.$inject = ['Airport']

  function AirportSelectController(Airport) {
  	var vm = this
  	vm.$onInit = initialize

  	function initialize () {
  		Airport.list().then(function (airports) {
  			vm.airports = airports
  		})
  	}
  }
})()
