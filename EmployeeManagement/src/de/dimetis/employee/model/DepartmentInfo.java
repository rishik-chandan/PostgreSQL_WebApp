package de.dimetis.employee.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table
public class DepartmentInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@OneToMany(mappedBy = "departmentinfo")
	private List<EmployeeInfo> employees = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
	}

	public DepartmentInfo(long id, String name, String desc, List<EmployeeInfo> employees) {
		super();
		this.id = id;
		this.name = name;
		this.description = desc;
		this.employees = employees;
	}

	public DepartmentInfo() {
		super();
	}
	
}