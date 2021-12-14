package com.bridgelabz.employeepayroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeepayrollService {
	
	private List<Employee> employee;

	public EmployeepayrollService(List<Employee> employee) {
		super();
		this.employee = employee;
	}
	
	public static void main(String[] args) {
		List<Employee> employee = new ArrayList();
		EmployeepayrollService service = new EmployeepayrollService(employee);
		Scanner consoleInputReader = new Scanner(System.in);
		service.readEmployeeData(consoleInputReader);
		service.printData(IOService.CONSOLE_IO);
		service.writeEmployeeData(employee,IOService.FILE_IO);
		service.printCount(employee,IOService.FILE_IO);
	}

	private void printCount(List<Employee> employee2, IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO)) {
			System.out.println("\n Writing Employee PayRoll Details on Console:\n" +this.employee);
		}else if(ioService.equals(IOService.FILE_IO)) {
			new EmployeeFileIO().getCountOfRecord();
		}
	}

	private void printData(IOService ioService) {
		if (ioService.equals(IOService.CONSOLE_IO)) {
			System.out.println("\n Writing Employee PayRoll Details on Console:\n" +this.employee);
		}else if(ioService.equals(IOService.FILE_IO)) {
			new EmployeeFileIO().printData();
		}
	}

	private void writeEmployeeData(List<Employee> employee,IOService ioService) {
		
		if (ioService.equals(IOService.CONSOLE_IO)) {
			System.out.println("\n Writing Employee PayRoll Details on Console:\n" +this.employee);
		}else if(ioService.equals(IOService.FILE_IO)) {
			new EmployeeFileIO().writeDateIntoFile(employee);
		}
	}

	private void readEmployeeData(Scanner consoleInputReader) {
		System.out.println("Enter Employee Id:");
		int id = consoleInputReader.nextInt();
		System.out.println("Enter Employee Name:");
		String name = consoleInputReader.next();
		System.out.println("Enter Employee Salary:");
		double salary = consoleInputReader.nextDouble();
		
		employee.add(new Employee(id,name,salary));
	}
}
