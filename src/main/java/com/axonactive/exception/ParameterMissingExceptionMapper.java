package com.axonactive.exception;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.axonactive.errorbean.ErrorMessage;


@Provider
public class ParameterMissingExceptionMapper implements ExceptionMapper<ParameterMissingException>{

	@Override
	public Response toResponse(ParameterMissingException exception) {
		Date d = new Date();
		SimpleDateFormat timeGMT = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss z");
		timeGMT.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		String timeStampLocal = timeGMT.format(d);
		ErrorMessage errorResponse = new ErrorMessage(400, exception.getMessage(), timeStampLocal);
		return Response.status(Status.BAD_REQUEST).entity(errorResponse).build();
	}

}