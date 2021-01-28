package com.javaweb.apple.model.vo.msg;

import com.javaweb.apple.model.vo.ProductVO;

// JSON
public class ProductPCardVO {
	private int id;
	private String name;
	private int price;
	private String category;
	private String color;
	private String image_path;

	public ProductPCardVO() {
		// TODO Auto-generated constructor stub
	}

	public ProductPCardVO(ProductVO pd) {
		super();
		this.name = pd.getName();
		this.price = pd.getPrice();
		this.category = pd.getCategory();
		this.color = pd.getColor();
		this.image_path = pd.getImage_path();
	}

	public ProductPCardVO(int id, String name, int price, String category, String color, String image_path) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
		this.color = color;
		this.image_path = image_path;
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

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

}
