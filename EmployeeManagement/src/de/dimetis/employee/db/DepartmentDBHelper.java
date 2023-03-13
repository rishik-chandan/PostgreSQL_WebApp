package de.dimetis.employee.db;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import de.dimetis.employee.common.OperationFailedException;
import de.dimetis.employee.model.DepartmentInfo;
import de.dimetis.employee.util.HibernateUtil;

public class DepartmentDBHelper {
	private static DepartmentDBHelper helper = null;
	private DepartmentDBHelper(){}
	
	public static DepartmentDBHelper getInstance(){
		if(null == helper){
			helper = new DepartmentDBHelper();
		}
		return helper;
	}
	
	public void saveDepartment(DepartmentInfo department) throws OperationFailedException{
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.beginTransaction();
			session.save(department);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new OperationFailedException("Unable to save department, Reason: ", e);
//		}finally{
//			if(null != session){
//				session.close();
//			}
		}
	}
	
	public List<DepartmentInfo> getAllDepartment() throws OperationFailedException{
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria crit = session.createCriteria(DepartmentInfo.class);
			return crit.list();
		} catch (Exception e) {
			throw new OperationFailedException("Unable get all departments, Reason: ", e);
//		}finally{
//			if(null != session){
//				session.close();
//			}
		}
	}
	
	public DepartmentInfo getDepartmentById(String deptId) throws OperationFailedException{
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			Criteria crit = session.createCriteria(DepartmentInfo.class);
			crit.add(Restrictions.eq("id", deptId));
			return (DepartmentInfo) crit.uniqueResult();
		} catch (Exception e) {
			throw new OperationFailedException("Unable get department for id "+deptId+", Reason: ", e);
//		}finally{
//			if(null != session){
//				session.close();
//			}
		}
	}
	
	public void deleteDepartment(String id) throws OperationFailedException{
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			DepartmentInfo department = getDepartmentById(id);
			session.beginTransaction();
			session.delete(department);
			session.getTransaction().commit();
		} catch (Exception e) {
			throw new OperationFailedException("Unable delete department for id "+id+", Reason: ", e);
//		}finally{
//			if(null != session){
//				session.close();
//			}
		}
	}
}
