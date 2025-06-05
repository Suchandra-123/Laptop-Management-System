package com.tyss.lms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tyss.lms.entity.Laptop;
import com.tyss.lms.utility.ConnectionPool;

public class LaptopDao {

	public Laptop addLaptop(Laptop laptop) {
		Connection connection = ConnectionPool.getConnection();
		String query = "insert into laptop values(?, ?, ?, ?, ?)";
		PreparedStatement pstm;
		try {
			pstm = connection.prepareStatement(query);
			pstm.setInt(1, laptop.getId());
			pstm.setString(2, laptop.getBrand());
			pstm.setInt(3, laptop.getRam());
			pstm.setInt(4, laptop.getRom());
			pstm.setDouble(5, laptop.getPrice());
			if (pstm.executeUpdate() == 1) {
				ConnectionPool.addConnection(connection);
				return laptop;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionPool.addConnection(connection);
		return null;
	}

	public boolean deleteLaptop(int id) {
		Connection c = ConnectionPool.getConnection();
		
		
		Laptop laptop = findById(id);
		if (laptop != null) {
			String queryString2 = "delete from laptop where id=?";
			PreparedStatement preparedStatement;
			try {
				preparedStatement = c.prepareStatement(queryString2);
				preparedStatement.setInt(1, laptop.getId());
				preparedStatement.execute();
				ConnectionPool.addConnection(c);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public Laptop updateLaptop(int id, Scanner s) {
		Connection connection = ConnectionPool.getConnection();
		try {
			Laptop laptop = findById(id);
			if (laptop != null) {
				System.out.println("user enter laptop brand");
				laptop.setBrand(s.next());
				System.out.println("user enter laptop RAM");
				laptop.setRam(s.nextInt());
				System.out.println("user enter laptop ROM");
				laptop.setRom(s.nextInt());
				System.out.println("user enter Laptop price");
				laptop.setPrice(s.nextDouble());
				String quString = "update laptop  set brand=?, ram=?, rom=?, price=? where id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(quString);
				preparedStatement.setString(1, (laptop.getBrand()));
				preparedStatement.setInt(2, (laptop.getRam()));
				preparedStatement.setInt(3, (laptop.getRom()));
				preparedStatement.setDouble(4, (laptop.getPrice()));
				preparedStatement.setInt(5, (laptop.getId()));
				preparedStatement.execute();
				ConnectionPool.addConnection(connection);
				return laptop;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			ConnectionPool.addConnection(connection);
			e.printStackTrace();
		}
		ConnectionPool.addConnection(connection);
		return null;
	}

	public List<Laptop> findAllLaptops() {
		List<Laptop> laptops = new ArrayList<Laptop>();
		Connection connection = ConnectionPool.getConnection();

		String queryString = "select * from laptop";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(queryString);
			for (; resultSet.next(); ) {
				int id = resultSet.getInt(1);
				String brand = resultSet.getString(2);
				int ram = resultSet.getInt(3);
				int rom = resultSet.getInt(4);
				double price = resultSet.getDouble(5);
				
				
				laptops.add(new Laptop(id, brand, ram, rom, price));
			}
			
			for (Laptop laptop : laptops) {
				System.out.println(laptop);//toString() method will be invoked
				System.out.println("_______________________________________________________________________");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionPool.addConnection(connection);

		return laptops;

	}

	public Laptop findById(int id) {
		Connection c = ConnectionPool.getConnection();
		ResultSet resultSet = null;
		try {
			String queryString = "select * from laptop where id=?";
			PreparedStatement pstm = c.prepareStatement(queryString, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			pstm.setInt(1, id);
			resultSet = pstm.executeQuery();
			if (resultSet.next()) {
				Laptop laptop = new Laptop(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getInt(4), resultSet.getDouble(5));
				ConnectionPool.addConnection(c);
				return laptop;
			} else
				System.out.println("no laptop exists please add laptops first to perform crud operations");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionPool.addConnection(c);
		return null;
	}
}
