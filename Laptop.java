package com.tyss.lms.entity;

import java.util.Objects;

public class Laptop {

	private int id;
	private String brand;
	private int ram;
	private int rom;
	private double price;

	public Laptop(int id, String brand, int ram, int rom, double price) {
		super();
		this.id = id;
		this.brand = brand;
		this.ram = ram;
		this.rom = rom;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getRom() {
		return rom;
	}

	public void setRom(int rom) {
		this.rom = rom;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", brand=" + brand + ", ram=" + ram + ", rom=" + rom + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(brand, id, price, ram, rom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laptop other = (Laptop) obj;
		return Objects.equals(brand, other.brand) && id == other.id
				&& Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && ram == other.ram
				&& rom == other.rom;
	}

}
