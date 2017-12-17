;(function () {
  'use strict'

  angular.module('flight-ui').factory('Flight', Flight)

  Flight.$inject = ['$resource', 'environment']

  function Flight ($resource, environment) {
    var rootUrl = environment.flightApi + '/flights'

    var resource = $resource(rootUrl + '/:id', { id: '@_id', flightId: '@flightId' }, {
      query: {
        method:'GET',
        isArray:false
      },

      create: {
        method: 'POST'
      }
    })

    return {
      list: list,
      search: search,
      create: create,
      findById: findById
    }

    function list () {
      return resource.query().$promise
    }

    function search (searchParams) {
      return resource.query({search: JSON.stringify(searchParams)}).$promise
    }

    function findById (id) {
      return resource.get({id: id}).$promise
    }

    function create (flightData) {
      return resource.create(flightData).$promise
    }
  }
})()
