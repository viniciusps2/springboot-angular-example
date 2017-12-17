;(function () {
  'use strict'

  angular.module('flight-ui').service('FlightService', FlightService)

  FlightService.$inject = ['$uibModal', 'Flight', 'ModalService']

  function FlightService ($uibModal, Flight, ModalService) {
    return {
      view: view
    }

    function view (flightId) {
      return Flight.findById(flightId).then(function (flight) {
        return ModalService.open({
          templateUrl: 'app/flight/modal.html',
          title: 'Flight nº ' + flight.id,
          flight: flight
        })
      })
    }
  }
})()
