package com.axonactive.restconfiguration;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.axonactive.converter.EmployeeConverter;
import com.axonactive.dto.EmployeeDTO;
import com.axonactive.entites.EmployeeEntity;
import com.axonactive.exception.InvalidValueException;
import com.axonactive.exception.ParameterMissingException;
import com.axonactive.services.EmployeeService;
import com.axonactive.exception.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

@Stateless
@Path("/example")
@SwaggerDefinition (
		schemes = {SwaggerDefinition.Scheme.HTTP,SwaggerDefinition.Scheme.HTTPS},
		info = @Info (title = "Employee Management",description = "A simple example of apiee",version = "1.0.0"))
@Api(tags="tags")
@Produces(MediaType.APPLICATION_JSON)

public class EmployeeResource {

	@EJB
	EmployeeService employeeService;

	EmployeeConverter employeeConverter = new EmployeeConverter();
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	 @ApiOperation(value = "Fetch all to dos")
	  @ApiResponses({
	    @ApiResponse(code=200, message="Success")
	  })
	public List<EmployeeDTO> getAllList() {
		List<EmployeeDTO> employeeList = employeeService.getAllEmployeeList();
		if (employeeList.isEmpty()) {
			throw new InvalidValueException("Currently there is no employee to be showed.");
		}
		return employeeList;
	}

	@GET
	@Path("{EmployeeId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Find employee by id")
	  @ApiResponses({
	    @ApiResponse(code=200, message="Success")
	  })
	public Response getEmployeeById(@PathParam("EmployeeId") String id) {
		try {
			Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			throw new InvalidValueException("Id should be a number!! Please Check the Id value.");
		}
		EmployeeDTO employee = employeeConverter.toDTO(employeeService.findEmployeeById(Integer.parseInt(id)));
		if (employee == null) {
			throw new InvalidValueException("Requested id is not in the list !!");
		}
		return Response.status(Status.OK).entity(employee).build();
	}

	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Add new Employee")
	  @ApiResponses({
	    @ApiResponse(code=204, message="Success")
	  })
	public Response addEmployee(EmployeeDTO employee) {
		try {
			employeeService.addEmployee(employee);
		} catch (Exception ex) {
			throw new ParameterMissingException("Some input parameters are missing!! Please check again.");
		}
		return Response.status(Status.OK).build();
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update  Employee")
	  @ApiResponses({
	    @ApiResponse(code=204, message="Success"),
	    @ApiResponse(code=404, message="Not Found")
	  })
	public Response updateEmployee(EmployeeDTO employee) {
		try {
			employeeService.updateEmployee(employee);
		} catch (Exception ex) {
			throw new ParameterMissingException("Some input parameters are missing!! Please check again.");
		}
		return Response.status(Status.OK).build();		
	}

	@DELETE
	@Path("{EmployeeId}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@ApiOperation(value = "Update  Employee")
	  @ApiResponses({
	    @ApiResponse(code=204, message="Success"),
	    @ApiResponse(code=404, message="Not Found")
	  })
	public Response deleteEmployeebyId(@PathParam("EmployeeId") String id) {
		try {
			Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			throw new InvalidValueException("Id should be a number!! Please Check the Id value.");
		}
		EmployeeEntity employeeEntity = employeeService.findEmployeeById(Integer.parseInt(id));
		if (employeeEntity != null) {
			employeeService.deleteEmployeeForREST(employeeEntity);
			return Response.status(Status.OK).build();
		}
		throw new InvalidValueException("Fail to delete Employee!! Requested id is not in the employee list.");
	
	}
}
