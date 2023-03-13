package de.dimetis.employee.module;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import de.dimetis.employee.common.OperationFailedException;
import de.dimetis.employee.controller.DefaultOperation;
import de.dimetis.employee.db.EmployeeDBHelper;
import de.dimetis.employee.model.EmployeeInfo;

public class EmployeeModule implements DefaultOperation {
	EmployeeDBHelper dbHelper;
	String operation;
	public String getOperation() {
		return operation;
	}
	
	@Override
	public Object performOp(Map<String, Object> params) {
		if("getAllEmp".equals(operation)){
			return getAllEmp();
//		} else if ("formUpload".equals(operation)) {
//			return formUpload();
		}
		return null;
	}

//	private Object formUpload() {
////		public void empFormUpload(HttpServletRequest request, HttpServletResponse response)
////				throws ServletException, IOException {
//
//			String id = request.getParameter("ID");
//			String name = request.getParameter("Name");
//			String email = request.getParameter("Email");
//			double phone = Double.parseDouble(request.getParameter("Phone"));
//			String address = request.getParameter("Address");
//			String state = request.getParameter("State");
//			String place = request.getParameter("Place");
//			String dept_id = request.getParameter("dept_id");
//
//			// Fetch the Department object based on the dept_id
//			Department department = null;
//			Session session = null;
//			Transaction transaction = null;
//
//			try {
//				session = HibernateUtil.getSessionFactory().openSession();
//				department = session.get(Department.class, dept_id);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				response.sendRedirect("txFailure.jsp");
//				return;
//			} finally {
//				session.close();
//			}
//
//			// Check if the department exists in the database
//			if (department == null) {
//				System.out.println("Department not found in the database.");
//				response.sendRedirect("deptNotFound.jsp");
//				return;
//			}
//
//			// Set the Department object for the Employee
//			Employee user = new Employee();
//			user.setId(id);
//			user.setName(name);
//			user.setAddress(address);
//			user.setEmail(email);
//			user.setPhNo(phone);
//			user.setState(state);
//			user.setPlace(place);
//			user.setDepartment(department);
//
//			// Save the User object to the database using Hibernate
//			session = null;
//			transaction = null;
//
//			try {
//				session = HibernateUtil.getSessionFactory().openSession();
//
//				// check if the object already exists in the database
//				Employee existingUser = session.get(Employee.class, id);
//				if (existingUser != null) {
//					// handle the case where the object already exists
//					System.out.println("User already exists in the database.");
//					response.sendRedirect("userExists.jsp");
//					return;
//				}
//
//				// continue with saving the User object to the database
//				transaction = session.beginTransaction();
//				System.out.println("Saving single Form item.");
//				session.save(user);
//				transaction.commit();
//				response.sendRedirect("successfulUpload.jsp");
//			} catch (Exception ex) {
//				ex.printStackTrace();
//				if (transaction != null) {
//					transaction.rollback();
//					response.sendRedirect("txFailure.jsp");
//				}
//				return;
//			} finally {
//				session.close();
//			}
//		
//		return null;
//	}

	private JsonArray getAllEmp() {
	    dbHelper = EmployeeDBHelper.getInstance();
	    JsonArray jsonArray = new JsonArray();
	    try {
	        List<EmployeeInfo> empList = dbHelper.getAllEmployees();
	        for (EmployeeInfo emp : empList) {
	            JsonObject obj = new JsonObject();
	            obj.addProperty("emp_id", emp.getId());
	    		obj.addProperty("emp_name", emp.getName());
	    		obj.addProperty("emp_address", emp.getAddress());
	    		obj.addProperty("emp_email", emp.getEmail());
	    		obj.addProperty("emp_phoneNo", emp.getPhoneNo());
	    		obj.addProperty("emp_state", emp.getState());
	    		obj.addProperty("emp_place", emp.getPlace());
	    		obj.addProperty("emp_dept_id", emp.getDepartmentinfo().getId());
	            jsonArray.add(obj);
	        }
	    } catch (OperationFailedException e) {
	        System.out.println("Getting Employee from DBHelper failed, reason :");
	        e.printStackTrace();
	    }
	    System.out.println("Sending from Module :\n" + jsonArray.toString());
	    return jsonArray;
	}

	@Override
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
