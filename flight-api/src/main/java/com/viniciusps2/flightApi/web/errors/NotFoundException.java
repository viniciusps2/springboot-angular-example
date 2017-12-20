package com.viniciusps2.flightApi.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Not Found")
public class NotFoundException extends RuntimeException{

}
