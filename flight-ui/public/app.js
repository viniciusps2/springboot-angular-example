angular
  .module('flight-ui', [
  	'ngResource',
  	'ngRoute',
  	'ngSanitize',
  	'ui.bootstrap',
  	'ui.select',
  	'ui.bootstrap.datetimepicker'
  ])

  .config(['$locationProvider', function ($locationProvider) {
    $locationProvider.hashPrefix('')
  }])
