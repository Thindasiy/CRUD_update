package com.axonactive.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.axonactive.dto.EmployeeDTO;
import com.axonactive.entites.EmployeeEntity;

public class EmployeeConverter {

	DepartmentConverter departmentConverter = new DepartmentConverter();
	
	public EmployeeEntity toEntity(EmployeeDTO employeeDTO) {
		if (employeeDTO != null) {
			return EmployeeEntity.builder().id(employeeDTO.getId()).name(employeeDTO.getName()).email(employeeDTO.getEmail()).age(employeeDTO.getAge())
					.department(departmentConverter.toEntity(employeeDTO.getDepartment())).build();
		}
		return null;
	}

	
	public EmployeeDTO toDTO(EmployeeEntity employeeEntity) {
		if (employeeEntity != null) {
			return  new EmployeeDTO(employeeEntity.getId(), employeeEntity.getName(), employeeEntity.getAge(), employeeEntity.getEmail(),
					departmentConverter.toDTO(employeeEntity.getDepartment()));
		}
		return null;
	}
	
	public List<EmployeeDTO> toDTOs(List<EmployeeEntity> employeeEntities) {
		if (employeeEntities == null) {
			return Collections.emptyList();
		}
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		employeeEntities.stream().map(this::toDTO).filter(Objects::nonNull).forEach(employeeDTOs::add);
		return employeeDTOs;
	}
}
