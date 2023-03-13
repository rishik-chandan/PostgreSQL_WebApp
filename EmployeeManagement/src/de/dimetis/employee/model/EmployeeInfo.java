package de.dimetis.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class EmployeeInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column
	private String email;
	
	@Column
	private Double phoneNo;
	
	@Column
	private String state;
	
	@Column
	private String place;
	
	@ManyToOne
	@JoinColumn(name = "dept_id")
	private DepartmentInfo departmentinfo;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(Double phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public DepartmentInfo getDepartmentinfo() {
		return departmentinfo;
	}
	public void setDepartmentinfo(DepartmentInfo departmentinfo) {
		this.departmentinfo = departmentinfo;
	}
	@Override
	public String toString() {
		return "EmployeeInfo [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phoneNo="
				+ phoneNo + ", state=" + state + ", place=" + place + "]";
	}
	public EmployeeInfo(long id, String name, String address, String email, Double phoneNo, String state, String place,
			DepartmentInfo departmentinfo) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNo = phoneNo;
		this.state = state;
		this.place = place;
		this.departmentinfo = departmentinfo;
	}
	public EmployeeInfo() {
		super();
	}
	
	
}
