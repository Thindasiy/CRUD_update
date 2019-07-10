package com.axonactive.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "showEmployeeList", query = "select e from EmployeeEntity e order by e.id ASC")
@Entity
@Table(name = "employee")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name",nullable=false)
	private String name;

	@Column(name = "age", nullable = true)
	private String age;

	@Column(name = "email", nullable = true)
	private String email;

	@ManyToOne
	@JoinColumn(name = "department", nullable = true)
	private DepartmentEntity department;
}
