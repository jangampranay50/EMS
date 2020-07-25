package com.cg.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.cg.ems.util.DBUtil;
import com.cg.ems.bean.Employee;
import com.cg.ems.exception.EmployeeException;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public int createEmployee(Employee employee) throws EmployeeException {
		int employeeId=0;
		try
		{
		Connection  connection = DBUtil.getConnection();
		String  cmd="insert into employee_tbl values (employee_seq.nextval,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(cmd);
		
		pstmt.setString(1, employee.getName());
		pstmt.setDouble(2, employee.getSalary());
		pstmt.executeUpdate();
		cmd = "select employee_seq.currval from dual";
		PreparedStatement pstmt1 = connection.prepareStatement(cmd);
	    ResultSet rst = pstmt1.executeQuery();
	   if(rst.next())
		{
	    employeeId = rst.getInt(1);
		}
		connection.close();
		} 
	
		
			catch(Exception e)
		
		{
			throw new EmployeeException(" Dao create Employee "+e.getMessage());
		}
		return employeeId;
	}

	@Override
	public Employee findEmployeeById(int employeeId) throws EmployeeException {
		
		Employee employee;
		try
		{
			Connection connection=DBUtil.getConnection();
		
		String  cmd="select empid,empname,empsal from employee_tbl where empid=?";
		PreparedStatement pstmt = connection.prepareStatement(cmd);
		pstmt.setInt(1, employeeId);
		ResultSet rst =  pstmt.executeQuery();
		
		if(rst.next() )
		{
			int id = rst.getInt("empid");
			String name = rst.getString("empname");
			double  sal = rst.getDouble("empsal");
			employee = new Employee(id,name,sal);
		}
		else
		{
			
			throw new EmployeeException(employeeId+" NOT EXIST ");
		}
		
		}
		catch(Exception e)
		{
			throw new EmployeeException(" Dao Find Employee "+e.getMessage());
		}
		return employee;
	}

	@Override
	public Employee deleteEmployeeById(int employeeId) throws EmployeeException {
		Employee employee = null;
		try
		{ 
			Connection connection = DBUtil.getConnection();
			
			
			
			String  cmd="select empid,empname,empsal from employee_tbl where empid=?";
			PreparedStatement pstmt1 = connection.prepareStatement(cmd);
			pstmt1.setInt(1, employeeId);
			ResultSet rst =  pstmt1.executeQuery();
			
			if(rst.next() )
			{
				int empid = rst.getInt("empid");
				String name = rst.getString("empname");
				double  sal = rst.getDouble("empsal");
				 employee = new Employee(empid,name,sal);
			}
			else
			{
				throw new EmployeeException("Id does not exist");
			}
			 String qry="DELETE FROM employee_tbl WHERE empid = ?";
				PreparedStatement pstmt = connection.prepareStatement(qry);
				
				pstmt.setInt(1,employeeId );
			pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			throw new EmployeeException(" Dao delete Employee "+e.getMessage());
		}
		return employee;
	}

	@Override
	public List<Employee> findAll() throws EmployeeException {
		
		Employee employee= null;
		List<Employee> list = null;
		try
		{
		Connection connection = DBUtil.getConnection();
		  
		   String  qry ="select empid,empname,empsal from employee_tbl ";
		 PreparedStatement pstmt = connection.prepareStatement(qry);
		   
		   ResultSet rst =  pstmt.executeQuery();
		   list = new ArrayList<Employee>();			  
		  while(rst.next() )
			{
				int id = rst.getInt("empid");
				String name = rst.getString("empname");
				double  sal = rst.getDouble("empsal");
				employee = new Employee(id,name,sal);
				
				list.add(employee);
			}
		  
		}
		catch(Exception e)
		{
			throw new EmployeeException(" Dao list all Employee "+e.getMessage());
		}
		return list;
	}

	@Override
	public Employee updateEmployeeById(Employee employee) throws EmployeeException {
		try
		{
		Connection  connection = DBUtil.getConnection();
		 String qry="UPDATE employee_tbl SET empname=?, empsal = ? WHERE empid = ? ";
		PreparedStatement pstmt = connection.prepareStatement(qry);
		
		pstmt.setString(1, employee.getName());
		pstmt.setDouble(2, employee.getSalary());
		pstmt.setInt(3, employee.getEmployeeId());
		pstmt.executeUpdate();
		String  cmd="select empid,empname,empsal from employee_tbl where empid=?";
		PreparedStatement pstmt1 = connection.prepareStatement(cmd);
		pstmt1.setInt(1, employee.getEmployeeId());
		System.out.println(employee.getEmployeeId());
		ResultSet rst =  pstmt1.executeQuery();
		
		if(rst.next() )
		{
			int id = rst.getInt("empid");
			String name = rst.getString("empname");
			double  sal = rst.getDouble("empsal");
			employee = new Employee(id,name,sal);
		}
		else
		{
			throw new EmployeeException("Id Does not Exit");
		}
		
		
		
		} 
	
		
			catch(Exception e)
		
		{
			throw new EmployeeException(" Dao update Employee "+e.getMessage());
		}
		return employee;
	
	}

	
}
