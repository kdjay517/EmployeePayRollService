package com.bridgelabz.employeepayroll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class EmployeeFileIO {
	
	private static final String HOME = "Data/abc/emp.txt" ;
	
	public void writeDateIntoFile(List<Employee> employee) {
		Path homePath = Paths.get(HOME);
		StringBuffer sb = new StringBuffer();
		employee.forEach(emp -> {
			String person = emp.toString().concat("\n");
			sb.append(person);
		});
		
		try {
			Files.write(homePath,sb.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printData() {
		try {
			Files.lines(new File(HOME).toPath()).forEach(System.out::println);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public long getCountOfRecord() {
		long count = 0;
		try {
			count = Files.lines(new File(HOME).toPath()).count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return count;
	}
}
