package com.axonactive.testing;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.axonactive.converter.EmployeeConverter;
import com.axonactive.dto.DepartmentDTO;
import com.axonactive.dto.EmployeeDTO;
import com.axonactive.entites.DepartmentEntity;
import com.axonactive.entites.EmployeeEntity;
import com.axonactive.errorbean.ErrorMessage;
import com.axonactive.exception.InvalidValueException;
import com.axonactive.restconfiguration.EmployeeResource;
import com.axonactive.services.EmployeeService;

@RunWith(PowerMockRunner.class)
public class EmployeeResourceTest {
	
	@InjectMocks
	EmployeeResource employeeResource;
	
	@Mock
	EmployeeConverter employeeConverter;
	
	@Mock
	EmployeeService employeeService;
	
	
	
	@Test
	public void testGetEmployeeById_ShouldReturnEntity_WhenValidEmployeeIdIsGiven() {
		//EmployeeDTO employee = createEmployeeDTO();
		
		Mockito.when(employeeService.findEmployeeById(1)).thenReturn(createEmployeeEntity());
		Mockito.when(employeeConverter.toDTO(employeeService.findEmployeeById(1))).thenReturn(createEmployeeDTO());
		
		
		Response actual = employeeResource.getEmployeeById("1");
		assertEquals(200, actual.getStatus()); 
		assertEquals(createEmployeeDTO(),actual.getEntity());
	}
	

	@Test(expected = InvalidValueException.class)
	public void testGetEmployeeById_ShouldReturnStatusResponse_WhenNoExistingEmployeeIdIsGiven() {

		Date d = new Date();
		SimpleDateFormat timeGMT = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss z");
		timeGMT.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		String timeStampLocal = timeGMT.format(d);
		ErrorMessage expected = new ErrorMessage(404, "Requested id is not in the list !!", timeStampLocal);
		
		Mockito.when(employeeService.findEmployeeById(100)).thenReturn(null);
		Mockito.when(employeeConverter.toDTO(employeeService.findEmployeeById(100))).thenReturn(null);
		
		Response actual = employeeResource.getEmployeeById("100");
		assertEquals(expected,actual.getEntity());
	}
	
	
	@Test(expected = InvalidValueException.class)
	public void testGetEmployeeById_ShouldReturnStatusResponse_WhenIdIsNotANumber() {
		
		Date d = new Date();
		SimpleDateFormat timeGMT = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm:ss z");
		timeGMT.setTimeZone(TimeZone.getTimeZone("GMT+7:00"));
		String timeStampLocal = timeGMT.format(d);
		ErrorMessage expected = new ErrorMessage(404, "Id should be a number!! Please Check the Id value.", timeStampLocal);
		
		Response actual = employeeResource.getEmployeeById("gee");
		assertEquals(expected, actual.getEntity());
	}
	
	@Test
	public void testAddEmployee_ShouldReturnOKStatus_WhenEmployeeDTOIsGiven() {
		
		Response actual = employeeResource.addEmployee(createEmployeeDTO());
		Mockito.verify(employeeService).addEmployee(createEmployeeDTO());
		assertEquals(200, actual.getStatus());
	}
	
		private DepartmentDTO createDepartmentDTO() {
		return new DepartmentDTO(1, "ICT");
	}

	private DepartmentEntity createDepartmentEntity() {
		return new DepartmentEntity(1, "ICT");
	}

	private EmployeeDTO createEmployeeDTO() {
		DepartmentDTO department = createDepartmentDTO();
		return new EmployeeDTO(1, "Yoon", "20", "yoon@gmail.com", department);
	}
	
	private EmployeeEntity createEmployeeEntity() {
		DepartmentEntity department = createDepartmentEntity();
		return new EmployeeEntity(1, "Yoon", "20", "yoon@gmail.com", department);
	}
	
	
	
}
