;(function () {
  'use strict'

  angular.module('flight-ui')

    .constant('environment', {
      flightApi: checkInjectedVariable('/* @echo FLIGHT_API */', 'http://localhost:8080')
    })

  function checkInjectedVariable (value, defaultValue) {
    return value === 'undefined' || value.indexOf('echo') >= 0 ? defaultValue : value.replace(/\/$/, '')
  }
})()
