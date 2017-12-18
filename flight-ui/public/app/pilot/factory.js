;(function () {
  'use strict'

  angular.module('flight-ui').factory('Pilot', Pilot)

  Pilot.$inject = ['$resource', 'environment']

  function Pilot ($resource, environment) {
    var rootUrl = environment.flightApi + '/pilots'

    var resource = $resource(rootUrl + '/:id', { id: '@_id'})

    return {
      list: list,
    }

    function list (listParams) {
      return resource.query().$promise
    }
  }
})()
