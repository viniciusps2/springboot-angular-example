;(function () {
  'use strict'

  angular.module('flight-ui').controller('FlightListCtrl', FlightListCtrl)

  FlightListCtrl.$inject = ['$routeParams', '$q', 'Flight', 'FlightService']

  function FlightListCtrl ($routeParams, $q, Flight, FlightService) {
    var vm = this

    angular.extend(this, {
      flights: [],
      searchParams: {},
      search: search,
      addFlight: addFlight,
      viewFlight: viewFlight,
      removeFlight: removeFlight,
      initialize: initialize
    })

    initialize()

    function search () {
      return Flight.search(vm.searchParams).then(function (res) {
        vm.flights = res.content
      })
    }

    function addFlight () {
      return FlightService.create(id).then(listFlights)
    }

    function viewFlight (flightId) {
      return FlightService.view(flightId)
    }

    function removeFlight (flight) {
      return FlightService.remove(flight).then(listFlights)
    }

    function initialize () {
      search()
    }
  }
})()
