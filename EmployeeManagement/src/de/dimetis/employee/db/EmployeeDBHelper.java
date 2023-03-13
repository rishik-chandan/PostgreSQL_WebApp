package de.dimetis.employee.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import de.dimetis.employee.common.OperationFailedException;
import de.dimetis.employee.model.EmployeeInfo;
import de.dimetis.employee.util.HibernateUtil;

public class EmployeeDBHelper {
	private static EmployeeDBHelper helper = null;
	private EmployeeDBHelper() {
	}

	public static EmployeeDBHelper getInstance() {
		if (null == helper) {
			helper = new EmployeeDBHelper();
		}
		return helper;
	}

	public void saveEmployee(EmployeeInfo employee) throws OperationFailedException {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new OperationFailedException("Unable to save employee, Reason: ", e);
//		} finally {
//			if (null != session) {
//				session.close();
//			}
		}
	}
	
	public List<EmployeeInfo> getAllEmployees() throws OperationFailedException{
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.getTransaction().isActive();
			Criteria crit = session.createCriteria(EmployeeInfo.class);
			return crit.list();
		} catch (Exception e) {
			throw new OperationFailedException("Unable get all employees, Reason: ", e);
//		}finally{
//			if(null != session){
//				session.close();
//			}
		}
	}
	
	public EmployeeInfo getEmployeeById(String empId) throws OperationFailedException{
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria crit = session.createCriteria(EmployeeInfo.class);
			crit.add(Restrictions.eq("id", empId));
			return (EmployeeInfo) crit.uniqueResult();
		} catch (Exception e) {
			throw new OperationFailedException("Unable get employee for id "+empId+", Reason: ", e);
//		}finally{
//			if(null != session){
//				session.close();
//			}
		}
	}
	
	public void deleteEmployee(String id) throws OperationFailedException{
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			EmployeeInfo employee = getEmployeeById(id);
			session.beginTransaction();
			session.delete(employee);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new OperationFailedException("Unable delete employee for id "+id+", Reason: ", e);
//		}finally{
//			if(null != session){
//				session.close();
//			}
		}
	}
}
