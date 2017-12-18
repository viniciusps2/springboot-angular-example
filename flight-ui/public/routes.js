;(function () {
  'use strict'

  angular.module('flight-ui').config(routes)

  routes.$inject = ['$locationProvider', '$routeProvider']

  function routes ($locationProvider, $routeProvider) {
    function route (url, opts) {
      $routeProvider.when(url, opts)
    }

    $routeProvider.otherwise({
      redirectTo: '/not_found'
    })

    route('/', {
      templateUrl: 'app/home/home.html',
      controller: 'HomeCtrl',
      controllerAs: 'vm'
    })

    route('/flight/airline/:airlineId', {
      templateUrl: 'app/flight/list.html',
      controller: 'FlightListCtrl',
      controllerAs: 'vm'
    })

    route('/not_found', {
      templateUrl: '404.html'
    })
  }
})()
