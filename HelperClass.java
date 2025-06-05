package com.tyss.lms.utility;

import java.util.Scanner;

import com.tyss.lms.entity.Laptop;

public class HelperClass {
	static public Laptop createLaptop(Scanner scanner){
		System.out.println("user enter laptop id");
		int id=scanner.nextInt();
		System.out.println("user enter laptop brand");
		String brand= scanner.next();
		System.out.println("user enter laptop RAM");
		int	 ram= scanner.nextInt();

		System.out.println("user enter laptop ROM");
		int	 rom= scanner.nextInt();

		System.out.println("user enter Laptop price");
		Double price= scanner.nextDouble();

		Laptop laptop=new Laptop(id, brand, ram, rom, price);
		return laptop;
	}

}
