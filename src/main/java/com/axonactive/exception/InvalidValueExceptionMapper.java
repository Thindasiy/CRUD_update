
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
public class InvalidValueExceptionMapper implements ExceptionMapper<InvalidValueException> {

	@Override
	public Response toResponse(InvalidValueException exception) {

		Date d = new Date();
		SimpleDateFormat timeGMT = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss z");
		timeGMT.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		String timeStampLocal = timeGMT.format(d);
		ErrorMessage errorResponse = new ErrorMessage(404, exception.getMessage(), timeStampLocal);
		return Response.status(Status.NOT_FOUND).entity(errorResponse).build();
	}

}