package com.axonactive.converter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.ejb.Stateless;

import com.axonactive.dto.DepartmentDTO;
import com.axonactive.entites.DepartmentEntity;

@Stateless
public class DepartmentConverter {

	public DepartmentEntity toEntity(DepartmentDTO departmentDTO) {
		if (departmentDTO != null) {
			return DepartmentEntity.builder().id(departmentDTO.getId()).name(departmentDTO.getName()).build();
		}
		return null;
	}

	public DepartmentDTO toDTO(DepartmentEntity deparmentEntity) {
		if (deparmentEntity != null) {
			return new DepartmentDTO(deparmentEntity.getId(), deparmentEntity.getName());
		}
		return null;
	}
	
	public List<DepartmentEntity> toEntities(List<DepartmentDTO> departmentDTOs) {
		if (departmentDTOs == null) {
			return Collections.emptyList();
		}
		List<DepartmentEntity> entities = new ArrayList<>();
		departmentDTOs.stream().map( this::toEntity).filter(Objects::nonNull).forEach(entities::add);
		return entities;
	}
	
	public List<DepartmentDTO> toDTOs(List<DepartmentEntity> deparmentEntities) {
		if (deparmentEntities == null) {
			return Collections.emptyList();
		}
		List<DepartmentDTO> departmentDTOs = new ArrayList<>();
		deparmentEntities.stream().map(this::toDTO).filter(Objects::nonNull).forEach(departmentDTOs::add);
		return departmentDTOs;
	}
}
