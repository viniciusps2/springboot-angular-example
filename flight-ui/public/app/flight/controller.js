;(function () {
  'use strict'

  angular.module('flight-ui').controller('FlightListCtrl', FlightListCtrl)

  FlightListCtrl.$inject = ['$routeParams', 'Flight', 'FlightService', 'allFlightStatus']

  function FlightListCtrl ($routeParams, Flight, FlightService, allFlightStatus) {
    var vm = this
    var airlineId = $routeParams.airlineId

    angular.extend(this, {  
      flights: [],
      searchParams: {},
      allFlightStatus: allFlightStatus,
      search: search,
      viewFlight: viewFlight,
      initialize: initialize
    })

    initialize()

    function search (searchParams) {
      return Flight.search(searchParams, airlineId).then(function (res) {
        vm.flights = res.content
      })
    }

    function viewFlight (flightId) {
      return FlightService.view(flightId)
    }

    function initialize () {
      search(vm.searchParams)
    }
  }
})()
