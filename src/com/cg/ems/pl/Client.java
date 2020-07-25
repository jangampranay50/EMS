package com.cg.ems.pl;

import java.util.List;
import java.util.Scanner;

import com.cg.ems.bean.Employee;
import com.cg.ems.exception.EmployeeException;
import com.cg.ems.service.EmployeeService;
import com.cg.ems.service.EmployeeServiceImpl;

public class Client {

	
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			int choice = 0;
			int id=0;
			Employee employee=null;
			EmployeeService service = new EmployeeServiceImpl();
			while(choice!=7)
			{
				System.out.println("1.Create Employee");
				System.out.println("2.Find Employee");
				System.out.println("3.Delete Employee");
				System.out.println("4.List all Employee");
				System.out.println("5.Update Employee");
				System.out.println("6.Exit");
				System.out.println("enter Choice");
				choice = scanner.nextInt();
				switch(choice)
				{
				case 1:
					scanner.nextLine();
					employee = new Employee();
					System.out.println("Enter Employee name");
					String name=scanner.nextLine();
					System.out.println("Enter salary");
					double salary = scanner.nextDouble();
					try
					{
					employee.setName(name);
					employee.setSalary(salary);
					int employeeId = service.createEmployee(employee);
					System.out.println("Employee Added with id=" + employeeId);
					}
					catch(EmployeeException e)
					{
						System.err.println(e.getMessage());
					}
					break;
					
				case 2:
					System.out.println("Enter Employee Id ");
					id = scanner.nextInt();
					
					try
					{
					employee = service.findEmployeeById(id);
					System.out.println("Employee ID ="+employee.getEmployeeId());
					System.out.println(" Name  = "+employee.getName());
					System.out.println(" Salary =" +employee.getSalary());
					}catch(EmployeeException e)
					{
						System.err.println(e.getMessage());
					}
				    break;
				case 3:
					try
					{
					System.out.println("Enter employee id to be deleted");
					id = scanner.nextInt();
					 employee= service.deleteEmployeeById(id);
					System.out.println("details of deleted employee are : "+ employee.getEmployeeId()+" "+employee.getName()+" "+employee.getSalary());
					}
					catch(EmployeeException e)
					{
						System.err.println(e.getMessage());
					}
					
					break;
				case 4 :
					try {
					List<Employee> list = service.findAll();
					for(Employee e : list)
					{
					System.out.println(e.getEmployeeId()+" "+e.getName()+" "+e.getSalary());	
					}
					}
					catch(EmployeeException e)
					{
						System.err.println(e.getMessage());
					}
					break;
				case 5:
					try
					{
					System.out.println("enter id of employee to be updated");
					id =scanner.nextInt();
					scanner.nextLine();
					System.out.println("Update Employee Name");
					name=scanner.nextLine();
					System.out.println("Update Salary");
					salary = scanner.nextDouble();
					employee = new Employee();
					employee.setEmployeeId(id);
					employee.setName(name);
					employee.setSalary(salary);
					Employee employee1 = service.updateEmployeeById(employee);
					System.out.println("updated employe details"+ employee1.getEmployeeId()+" "+employee1.getName()+" "+employee1.getSalary());
					}
					catch(EmployeeException e)
					{
						System.err.println(e.getMessage());
					}
					break;
				case 6 :
					System.out.println("Thank you");
					return;
				default :
					System.err.println("Enter Correct Option");
					break;
				}
				
	   		}
			

		}
}
