package com.axonactive.testing;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import com.axonactive.converter.DepartmentConverter;
import com.axonactive.dto.DepartmentDTO;
import com.axonactive.entites.DepartmentEntity;

@RunWith(PowerMockRunner.class)
public class DepartmentConverterTest {

	@InjectMocks
	DepartmentConverter departmentConverter;
	
	@Test
	public void testToEntity_ShouldReturnEntity_WhenDTOisGiven() {
		DepartmentEntity expected = createDepartmentEntity();
		DepartmentDTO departmentDTO = createDepartmentDTO();
		
		DepartmentEntity actual = departmentConverter.toEntity(departmentDTO);
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void testToEntity_ShouldReturnNull_WhenDTOisNull() {
		
		DepartmentEntity actual = departmentConverter.toEntity(null);
		assertEquals(null, actual);
	}
	
	@Test
	public void testToDTO_ShouldReturnDTO_WhenEntityisGiven() {
		DepartmentEntity departmentEntity = createDepartmentEntity();
		DepartmentDTO expected = createDepartmentDTO();
		
		DepartmentDTO actual = departmentConverter.toDTO(departmentEntity);
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void testToDTO_ShouldReturnNull_WhenEntityisNull() {
		
		DepartmentDTO actual = departmentConverter.toDTO(null);
		assertEquals(null, actual);
	}
	
	@Test
	public void testToDTOs_ShouldReturnDTOList_WhenEntityListIsGiven() {
		
	
		List<DepartmentDTO> expected = Arrays.asList(createDepartmentDTO(),createDepartmentDTO());
		List<DepartmentEntity> departmentEntities = Arrays.asList(createDepartmentEntity(),createDepartmentEntity());
		
		List<DepartmentDTO> actual = departmentConverter.toDTOs(departmentEntities);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testToDTOs_ShouldReturnDTOEmptyList_WhenEntityListIsNull() {
		List<DepartmentDTO> actual = departmentConverter.toDTOs(null);
		assertEquals(Arrays.asList(), actual);
	}
		
	private DepartmentDTO createDepartmentDTO() {

		return new DepartmentDTO(1, "ICT");
	}

	private DepartmentEntity createDepartmentEntity() {
		
		return new DepartmentEntity(1, "ICT");
	}
}
