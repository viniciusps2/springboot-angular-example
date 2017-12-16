;(function () {
  'use strict'

  angular.module('flight-ui').controller('FlightListCtrl', FlightListCtrl)

  FlightListCtrl.$inject = ['$routeParams', '$q', 'Flight']

  function FlightListCtrl ($routeParams, $q, Flight) {
    var vm = this
    var id = $routeParams.id

    vm.flights = []
    vm.title = 'ssss'
    // vm.addFlight = addFlight
    // vm.editFlight = editFlight
    // vm.removeFlight = removeFlight
    vm.listFlights = listFlights
    vm.initialize = initialize

    initialize()

    function listFlights () {
      return Flight.list().then(function (res) {
        vm.flights = res.content
      })
    }

    // function addFlight () {
    //   return FlightService.create(id).then(listFlights)
    // }

    // function editFlight (flight) {
    //   return FlightService.edit(flight).then(listFlights)
    // }

    // function removeFlight (flight) {
    //   return FlightService.remove(flight).then(listFlights)
    // }

    function initialize () {
      $q.all([
        listFlights()
      ])
    }
  }
})()
