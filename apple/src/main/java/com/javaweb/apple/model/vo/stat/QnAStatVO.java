package com.javaweb.apple.model.vo.stat;

public class QnAStatVO {
	private String stDay;
	private long qaCnt;
	private double qaCntRatio;
	private long RcSum;
	private double rcRatio;
	
	public QnAStatVO() {
		// TODO Auto-generated constructor stub
	}
	
	public QnAStatVO(String stDay, long qaCnt, double qaCntRatio, long rcSum, double rcRatio) {
		super();
		this.stDay = stDay;
		this.qaCnt = qaCnt;
		this.qaCntRatio = qaCntRatio;
		RcSum = rcSum;
		this.rcRatio = rcRatio;
	}


	public String getStDay() {
		return stDay;
	}


	public void setStDay(String stDay) {
		this.stDay = stDay;
	}


	public long getQaCnt() {
		return qaCnt;
	}


	public void setQaCnt(long qaCnt) {
		this.qaCnt = qaCnt;
	}


	public double getQaCntRatio() {
		return qaCntRatio;
	}


	public void setQaCntRatio(double qaCntRatio) {
		this.qaCntRatio = qaCntRatio;
	}


	public long getRcSum() {
		return RcSum;
	}


	public void setRcSum(long rcSum) {
		RcSum = rcSum;
	}


	public double getRcRatio() {
		return rcRatio;
	}


	public void setRcRatio(double rcRatio) {
		this.rcRatio = rcRatio;
	}


	@Override
	public String toString() {
		return "QnAStatVO [stDay=" + stDay + ", qaCnt=" + qaCnt + ", qaCntRatio=" + qaCntRatio + ", RcSum=" + RcSum
				+ ", rcRatio=" + rcRatio + "]";
	}
}
