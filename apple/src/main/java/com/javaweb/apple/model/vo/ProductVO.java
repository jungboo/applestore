package com.javaweb.apple.model.vo;

import java.sql.Timestamp;

public class ProductVO {

	private int id; // 관리번호
	private String name; // 상품이름
	private int price; // 상품가격
	private String category; // 카테고리
	private String color; // 상품컬러
	private String spec; // 스펙
	private String image_path; // 이미지경로
	private String description; // 상품설명
	private Timestamp reg_day; // 출시일

	@Override
	public String toString() {
		return "ProductVO [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", color="
				+ color + ", spec=" + spec + ", image_path=" + image_path + ", description=" + description
				+ ", reg_day=" + reg_day + "]";
	}

	public ProductVO() {
		// TODO Auto-generated constructor stub
	}

	public ProductVO(String name, int price, String category, String color, String spec, String image_path,
			String description) {
		this(0, name, price, category, color, spec, image_path, description, null);
	}

	public ProductVO(int id, String name, int price, String category, String color, String spec, String image_path,
			String description, Timestamp reg_day) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.color = color;
		this.spec = spec;
		this.image_path = image_path;
		this.description = description;
		this.reg_day = reg_day;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getReg_day() {
		return reg_day;
	}

	public void setReg_day(Timestamp reg_day) {
		this.reg_day = reg_day;
	}

}
