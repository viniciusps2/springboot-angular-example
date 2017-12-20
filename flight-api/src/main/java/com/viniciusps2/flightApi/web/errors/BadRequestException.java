package com.viniciusps2.flightApi.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Invalid Params")
public class BadRequestException extends RuntimeException {
}
