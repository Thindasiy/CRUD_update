package com.axonactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
	private int id;
	private String name;
	private String age;
	private String email;
	private DepartmentDTO department;
}
