;(function () {
  'use strict'

  angular.module('flight-ui').factory('Airline', Airline)

  Airline.$inject = ['$resource', 'environment']

  function Airline ($resource, environment) {
    var rootUrl = environment.flightApi + '/airlines'

    var resource = $resource(rootUrl + '/:id', { id: '@_id'})

    return {
      list: list,
      findById: findById
    }

    function list (listParams) {
      return resource.query().$promise
    }

    function findById (id) {
      return resource.get({id: id}).$promise
    }
  }
})()
