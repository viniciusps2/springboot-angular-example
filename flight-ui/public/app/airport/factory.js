;(function () {
  'use strict'

  angular.module('flight-ui').factory('Airport', Airport)

  Airport.$inject = ['$resource', 'environment']

  function Airport ($resource, environment) {
    var rootUrl = environment.flightApi + '/airports'

    var resource = $resource(rootUrl + '/:id', { id: '@_id'})

    return {
      list: list,
    }

    function list (listParams) {
      return resource.query().$promise
    }
  }
})()
