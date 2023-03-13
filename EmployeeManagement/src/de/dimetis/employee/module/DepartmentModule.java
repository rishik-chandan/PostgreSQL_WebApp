package de.dimetis.employee.module;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import de.dimetis.employee.common.OperationFailedException;
import de.dimetis.employee.controller.DefaultOperation;
import de.dimetis.employee.db.DepartmentDBHelper;
import de.dimetis.employee.model.DepartmentInfo;

public class DepartmentModule implements DefaultOperation {
	DepartmentDBHelper dbHelper;
	String operation;
	public String getOperation() {
		return operation;
	}

	@Override
	public Object performOp(Map<String, Object> params) {
		if("getAllDep".equals(operation)){
			return getAllDept();
		}
		return null;
	}

	private JsonArray getAllDept() {
		dbHelper = DepartmentDBHelper.getInstance();
		try {
			List<DepartmentInfo> deptList = dbHelper.getAllDepartment();
			
			JsonObject jsonObj = new JsonObject();		
			JsonArray jsonArray = new Gson().toJsonTree(deptList).getAsJsonArray();
			jsonObj.add("jsonArray", jsonArray);
			System.out.println(jsonObj.toString());
		}catch (OperationFailedException e) {
			System.out.println("Getting Department from DBHelper failed, reason :");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setOperation(String operation) {
		this.operation = operation;
	}

}
