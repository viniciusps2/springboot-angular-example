;(function () {
  'use strict'

  angular.module('flight-ui').controller('HomeCtrl', HomeCtrl)

  HomeCtrl.$inject = ['$location', 'Airline']

  function HomeCtrl ($location, Airline) {
    var vm = this

    angular.extend(this, {
      airlines: [],
      openAirline: openAirline
    })

    initialize()

    function loadAirlines () {
      return Airline.list().then(function (airlines) {
        vm.airlines = airlines
      })
    }

    function openAirline (airlineId) {
      $location.path('/flight/airline/' + airlineId)
    }

    function initialize () {
      loadAirlines()
    }
  }
})()
