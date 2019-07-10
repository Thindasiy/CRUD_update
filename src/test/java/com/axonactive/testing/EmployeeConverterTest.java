package com.axonactive.testing;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.axonactive.converter.DepartmentConverter;
import com.axonactive.converter.EmployeeConverter;
import com.axonactive.dto.DepartmentDTO;
import com.axonactive.dto.EmployeeDTO;
import com.axonactive.entites.DepartmentEntity;
import com.axonactive.entites.EmployeeEntity;

@RunWith(PowerMockRunner.class)
public class EmployeeConverterTest  {

	@InjectMocks
	EmployeeConverter employeeConverter;
	
	@Mock
	DepartmentConverter departmentConverter;
	
	@Test
	public void testToEntity_ShouldReturnEntity_WhenDTOisGiven() {
		
		EmployeeEntity expected = createEmployeeEntity();
		EmployeeDTO employeeDTO = createEmployeeDTO();
		DepartmentEntity departmentEntity =createDepartmentEntity();	
		
		Mockito.when(departmentConverter.toEntity(employeeDTO.getDepartment())).thenReturn(departmentEntity);		
	
		EmployeeEntity actual = employeeConverter.toEntity(employeeDTO);
		assertEquals(expected, actual);		
	}
	
	@Test
	public void testToEntity_ShouldReturnNull_WhenDTOisNull() {	
		EmployeeEntity actual = employeeConverter.toEntity(null);
		assertEquals(null,actual);
	}

	@Test
	public void testToDTO_ShouldReturnDTO_WhenEntityisGiven() {
		EmployeeEntity employeeEntity = createEmployeeEntity();
		EmployeeDTO expected = createEmployeeDTO();
		DepartmentDTO departmentDTO = createDepartmentDTO();
		
		Mockito.when(departmentConverter.toDTO(employeeEntity.getDepartment())).thenReturn(departmentDTO);
		
		EmployeeDTO actual = employeeConverter.toDTO(employeeEntity);
		assertEquals(expected,actual);
	}
	
	@Test 
	public void testToDTO_ShouldReturnNull_WhenEntityIsNull() {	
		EmployeeDTO actual = employeeConverter.toDTO(null);
		assertEquals(null,actual);
	}
	
	@Test
	public void testToDTOs_ShouldReturnDTOList_WhenEntityListIsGiven() {
		EmployeeEntity employeeEntity = createEmployeeEntity();
		DepartmentDTO departmentDTO = createDepartmentDTO();
		List<EmployeeDTO> expected = Arrays.asList(createEmployeeDTO(),createEmployeeDTO());
		List<EmployeeEntity> employeeEntities = Arrays.asList(createEmployeeEntity(),createEmployeeEntity());
		
		Mockito.when(departmentConverter.toDTO(employeeEntity.getDepartment())).thenReturn(departmentDTO);
		
		List<EmployeeDTO> actual = employeeConverter.toDTOs(employeeEntities);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testToDTOs_ShouldReturnDTOEmptyList_WhenEntityListIsNull() {
		List<EmployeeDTO> actual = employeeConverter.toDTOs(null);
		assertEquals(Arrays.asList(), actual);
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

