;(function () {
  'use strict'

  angular.module('flight-ui').factory('Flight', Flight)

  Flight.$inject = ['$resource', 'environment']

  function Flight ($resource, environment) {
    var rootUrl = environment.flightApi + '/flights'

    var resource = $resource(rootUrl + '/:id', { id: '@_id', flightId: '@flightId' }, {
      query: {
        method:'GET',
        isArray: false
      }
    })

    return {
      search: search,
      findById: findById
    }

    function search (searchParams, airlineId) {
      var params = angular.extend({airlineId}, searchParams)
      return resource.query({search: JSON.stringify(params)}).$promise
    }

    function findById (id) {
      return resource.get({id: id}).$promise
    }
  }
})()
