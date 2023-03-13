package de.dimetis.employee.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.dimetis.employee.module.DepartmentModule;
import de.dimetis.employee.module.EmployeeModule;

@WebServlet("/OperationServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class OperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public OperationServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation;
		String module;
		
		operation = request.getParameter("operation");
		module = request.getParameter("module");
		
		if(null == request.getParameter("operation") || null == request.getParameter("module")){
			System.out.println("Operation is :"+operation);
			System.out.println("Module is :"+module);
			throw new ServletException("Invalid request");
		}
		DefaultOperation serverOp = null;
		if("employee".equals(module)){
			serverOp = new EmployeeModule();
		}else if("department".equals(module)){
			serverOp = new DepartmentModule();
		}
		serverOp.setOperation(operation);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		request.getParameterMap().forEach((key, obj)->{
			paramMap.put(key, obj);
		});
		Object resp = serverOp.performOp(paramMap);
	}

}
