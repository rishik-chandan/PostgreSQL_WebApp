package de.dimetis.employee.util;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.dimetis.employee.model.DepartmentInfo;
import de.dimetis.employee.model.EmployeeInfo;

public class HibernateUtil {
	static Session session = null;

	private static final String DATABASE = "linkDB";
	private static final String USERNAME = "linkmanager";
	private static final String PASSWORD = "linkmanager";

	public static Session getSession() {
		if (null == session) {
			session = buildSession();
		}
		return session;
	}

	private static Session buildSession() {
		Configuration config = new Configuration();
		config.addProperties(getProps());
		config.addAnnotatedClass(EmployeeInfo.class);
		config.addAnnotatedClass(DepartmentInfo.class);
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session localSession = sessionFactory.openSession();
		return localSession;
	}

	private static Properties getProps() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/" + DATABASE);
		prop.setProperty("dialect", "org.hibernate.dialect.PostgresSQL");
		prop.setProperty("hibernate.connection.username", USERNAME);
		prop.setProperty("hibernate.connection.password", PASSWORD);
		prop.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		prop.setProperty("show_sql", "true");
		prop.setProperty("hibernate.hbm2ddl.auto", "update");
		
		return prop;
	}
}
