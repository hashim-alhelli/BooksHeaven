package beans;

import java.util.Date;

public class VisitEventBean {
	
	private Date day = new Date();
	private String bid;
	private String eventType;
	private int count;
	

	public VisitEventBean(Date day, String bid, String eventType, int count) {
		// TODO Auto-generated constructor stub
		this.day = day;
		this.bid = bid;
		this.eventType = eventType;
		this.count = count;
	}


	public Date getDay() {
		return day;
	}


	public void setDay(Date day) {
		this.day = day;
	}


	public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}


	public String getEventType() {
		return eventType;
	}


	public void setEventType(String eventType) {
		this.eventType = eventType;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}

}
