;(function () {
  'use strict'

  angular.module('flight-ui').directive('navbar', navbar)

  function navbar () {
    return {
      templateUrl: 'components/navbar/navbar.html'
    }
  }
})()
