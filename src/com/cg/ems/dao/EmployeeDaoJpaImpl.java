package com.cg.ems.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cg.ems.bean.Employee;
import com.cg.ems.exception.EmployeeException;
import com.cg.ems.util.JPAUtil;

public class EmployeeDaoJpaImpl implements EmployeeDao 
{
	EntityManager em = JPAUtil.getEntityManager();

	@Override
	public int createEmployee(Employee employee) throws EmployeeException {
		em.getTransaction().begin();
		em.persist(employee);
		em.getTransaction().commit();
		em.close();
		     
		return employee.getEmployeeId();
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws EmployeeException {
		Employee employee =em.find(Employee.class,employeeId);
		  if(employee==null)
		  {
		 throw new EmployeeException("Id not found");
		  }
		//  em.getTransaction().commit();
		 // em.close();
		return employee;
	}

	@Override
	public Employee deleteEmployeeById(int employeeId) throws EmployeeException {
		em.getTransaction().begin();
		Employee employee =em.find(Employee.class,employeeId);
		  if(employee==null)
		  {
		  throw new EmployeeException(employeeId+" Id not found");
		 
		 // System.out.println("employee removed");
		  }
		 
		  em.remove(employee);
		 
		  em.getTransaction().commit();
		 //em.close();
		 
		return employee;
	}

	@Override
	public List<Employee> findAll() throws EmployeeException {
		String query="select e from Employee e";

		TypedQuery<Employee> qry = em.createQuery(query,Employee.class);

		List<Employee> list = qry.getResultList();
		return list;
	}

	@Override
	public Employee updateEmployeeById(Employee employee) throws EmployeeException {
		em.getTransaction().begin();
		Employee employee1 =em.find(Employee.class,employee.getEmployeeId());
		  if(employee1==null)
		  {
		 
		  throw new EmployeeException(" Id not found");  
		 
		  }
		 employee1.setName(employee.getName());
		 employee1.setSalary(employee.getSalary());
		  em.merge(employee1);
		  em.getTransaction().commit();
		//  em.close();
		return employee1;
	}

}
