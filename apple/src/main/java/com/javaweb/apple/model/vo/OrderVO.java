package com.javaweb.apple.model.vo;

import java.sql.Timestamp;

public class OrderVO
{
	private int orderId;
	private int memberId;
	private Timestamp orderDate;
	private int totalPrice;
	private int orderState;
	private String creditCard;
	private String address;
	private String addressDetail;
	private String prIds;
	private String counts;

	public OrderVO()
	{
	}

	public OrderVO(int orderId, int memberId, Timestamp orderDate, int totalPrice, int orderState, String creditCard,
			String address, String addressDetail, String prIds, String counts)
	{
		super();
		this.orderId = orderId;
		this.memberId = memberId;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.orderState = orderState;
		this.creditCard = creditCard;
		this.address = address;
		this.addressDetail = addressDetail;
		this.prIds = prIds;
		this.counts = counts;
	}

	@Override
	public String toString()
	{
		return "OrderVO [orderId=" + orderId + ", memberId=" + memberId + ", orderDate=" + orderDate + ", totalPrice="
				+ totalPrice + ", orderState=" + orderState + ", creditCard=" + creditCard + ", address=" + address
				+ ", addressDetail=" + addressDetail + ", prIds=" + prIds + ", counts=" + counts + "]";
	}

	public int getOrderId()
	{
		return orderId;
	}

	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	public int getMemberId()
	{
		return memberId;
	}

	public void setMemberId(int memberId)
	{
		this.memberId = memberId;
	}

	public Timestamp getOrderDate()
	{
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate)
	{
		this.orderDate = orderDate;
	}

	public int getTotalPrice()
	{
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice)
	{
		this.totalPrice = totalPrice;
	}

	public int getOrderState()
	{
		return orderState;
	}

	public void setOrderState(int orderState)
	{
		this.orderState = orderState;
	}

	public String getCreditCard()
	{
		return creditCard;
	}

	public void setCreditCard(String creditCard)
	{
		this.creditCard = creditCard;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getAddressDetail()
	{
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail)
	{
		this.addressDetail = addressDetail;
	}

	public String getPrIds()
	{
		return prIds;
	}

	public void setPrIds(String prIds)
	{
		this.prIds = prIds;
	}

	public String getCounts()
	{
		return counts;
	}

	public void setCounts(String counts)
	{
		this.counts = counts;
	}
}