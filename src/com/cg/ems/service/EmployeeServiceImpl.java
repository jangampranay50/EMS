package com.cg.ems.service;

import java.util.List;

import com.cg.ems.bean.Employee;
import com.cg.ems.dao.EmployeeDao;
import com.cg.ems.dao.EmployeeDaoImpl;
import com.cg.ems.dao.EmployeeDaoJpaImpl;
import com.cg.ems.exception.EmployeeException;

public class EmployeeServiceImpl implements EmployeeService
{
	private EmployeeDao  employeeDao;

	public EmployeeServiceImpl()
	{
		employeeDao = new EmployeeDaoJpaImpl();
		
	}
	
	@Override
	public int createEmployee(Employee employee) throws EmployeeException {
		boolean f = employee.getName().matches("[a-zA-z]+");
		
		if(!f)
		{
			throw new EmployeeException("name should contain only chaacters");
		}
		return employeeDao.createEmployee(employee);
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws EmployeeException {
		
		String  empid = String.valueOf(employeeId);
		
		boolean  flag = empid.matches("[0-9]{4}");
		if( ! flag )
		{
			throw new EmployeeException("ID shoud be 4 digits ");
		}
		return employeeDao.findEmployeeById(employeeId);
	}

	@Override
	public Employee deleteEmployeeById(int employeeId) throws EmployeeException {
		String  empid = String.valueOf(employeeId);
		boolean  flag = empid.matches("[0-9]{4}");
		if( ! flag )
		{
			throw new EmployeeException("ID shoud be 4 digits ");
		}
		return employeeDao.deleteEmployeeById(employeeId);
	}

	@Override
	public List<Employee> findAll() throws EmployeeException {
		
		return employeeDao.findAll();
	}

	@Override
	public Employee updateEmployeeById(Employee employee) throws EmployeeException {
		String  empid = String.valueOf(employee.getEmployeeId());
		boolean  flag = empid.matches("[0-9]{4}");
		boolean f = employee.getName().matches("[a-zA-z]+");
		if(!f)
		{
			throw new EmployeeException("name should contain only chaacters");
		}
		if( ! flag )
		{
			throw new EmployeeException("ID shoud be 4 digits ");
		}
		return employeeDao.updateEmployeeById(employee);

	}

}
