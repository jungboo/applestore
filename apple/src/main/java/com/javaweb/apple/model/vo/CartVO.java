package com.javaweb.apple.model.vo;


// 장바구니 자체가 임시 저장소 역활을 할것임, db에 넣고 결제 보내기 전까지 저장하는 역활, 다른 아이디로 로그인하면 장바구니 db 날릴 것임
public class CartVO
{
	private int memberId;
	private String prIds;			
	private String names;
	private String prices;		
	private String imagePaths;	
	private String counts;			
	
	public CartVO()
	{
	}

	public CartVO(int memberId, String prIds, String prNames, String prPrices, String imagePaths, String counts)
	{
		super();
		this.memberId = memberId;
		this.prIds = prIds;
		this.names = prNames;
		this.prices = prPrices;
		this.imagePaths = imagePaths;
		this.counts = counts;
	}

	public int getMemberId()
	{
		return memberId;
	}

	public void setMemberId(int memberId)
	{
		this.memberId = memberId;
	}

	public String getPrIds()
	{
		return prIds;
	}

	public void setPrIds(String prIds)
	{
		this.prIds = prIds;
	}

	public String getNames()
	{
		return names;
	}

	public void setNames(String prNames)
	{
		this.names = prNames;
	}

	public String getPrices()
	{
		return prices;
	}

	public void setPrices(String prPrices)
	{
		this.prices = prPrices;
	}

	public String getImagePaths()
	{
		return imagePaths;
	}

	public void setImagePaths(String imagePaths)
	{
		this.imagePaths = imagePaths;
	}

	public String getCounts()
	{
		return counts;
	}

	public void setCounts(String counts)
	{
		this.counts = counts;
	}

	@Override
	public String toString()
	{
		return "CartVO [memberId=" + memberId + ", prIds=" + prIds + ", prNames=" + names + ", prPrices=" + prices
				+ ", imagePaths=" + imagePaths + ", counts=" + counts + "]";
	}

	
	

}

