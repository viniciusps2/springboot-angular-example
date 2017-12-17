;(function () {
  'use strict'

  angular.module('flight-ui')

    .constant('allFlightStatus', {
     	'SCHEDULED' : 'Scheduled',
		'DELAYED' : 'Delayed',
		'DEPARTED' : 'Departed',
		'IN_AIR' : 'In Air',
		'EXPECTED' : 'Expected',
		'ARRIVED' : 'Arrived',
		'CANCELLED' : 'Cancelled',
		'NO_INFO' : 'No Info',
		'PAST_FLIGHT' : 'Past Flight'
    })

})()
