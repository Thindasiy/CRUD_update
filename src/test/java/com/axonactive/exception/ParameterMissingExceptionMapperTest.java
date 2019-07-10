package com.axonactive.exception;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class ParameterMissingExceptionMapperTest {

	@InjectMocks
	ParameterMissingExceptionMapper parameterMissingException;
	
	@Mock
	SimpleDateFormat timeGMT;
	
	@Test
	public void testToResponse_ShouldReturnBadRequest_WhenExceptionIsGiven() { 
		
		
	}
	
	

}
