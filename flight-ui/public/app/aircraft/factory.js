;(function () {
  'use strict'

  angular.module('flight-ui').factory('Aircraft', Aircraft)

  Aircraft.$inject = ['$resource', 'environment']

  function Aircraft ($resource, environment) {
    var rootUrl = environment.flightApi + '/aircrafts'

    var resource = $resource(rootUrl + '/:id', { id: '@_id'})

    return {
      list: list,
    }

    function list (listParams) {
      return resource.query().$promise
    }
  }
})()
