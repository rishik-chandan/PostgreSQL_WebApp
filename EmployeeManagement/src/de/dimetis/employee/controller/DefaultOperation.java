package de.dimetis.employee.controller;

import java.util.Map;

public interface DefaultOperation {

	public Object performOp(Map<String, Object> params);
	
	public void setOperation(String operation);
}
