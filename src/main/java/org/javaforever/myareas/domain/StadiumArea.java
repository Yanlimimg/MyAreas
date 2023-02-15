package org.javaforever.myareas.domain;


public class StadiumArea implements Comparable<StadiumArea> {
	protected Long stadiumAreaId;
	protected String stadiumAreaName;
	protected Boolean active;
	protected Long stadiumId;
	protected Long timeId;
	protected String startTime;
	protected String endTime;
	protected Double fee;
	protected Double otherFee;
	protected Integer state;

	public Boolean getActive(){
		return this.active;
	}

	public String getEndTime(){
		return this.endTime;
	}

	public Double getFee(){
		return this.fee;
	}

	public Double getOtherFee(){
		return this.otherFee;
	}

	public Long getStadiumAreaId(){
		return this.stadiumAreaId;
	}

	public String getStadiumAreaName(){
		return this.stadiumAreaName;
	}

	public Long getStadiumId(){
		return this.stadiumId;
	}

	public String getStartTime(){
		return this.startTime;
	}

	public Integer getState(){
		return this.state;
	}

	public Long getTimeId(){
		return this.timeId;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	public void setFee(Double fee){
		this.fee = fee;
	}

	public void setOtherFee(Double otherFee){
		this.otherFee = otherFee;
	}

	public void setStadiumAreaId(Long stadiumAreaId){
		this.stadiumAreaId = stadiumAreaId;
	}

	public void setStadiumAreaName(String stadiumAreaName){
		this.stadiumAreaName = stadiumAreaName;
	}

	public void setStadiumId(Long stadiumId){
		this.stadiumId = stadiumId;
	}

	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public void setTimeId(Long timeId){
		this.timeId = timeId;
	}

	public int compareTo(StadiumArea stadiumArea){
		return this.getStadiumAreaId().compareTo(stadiumArea.getStadiumAreaId());
	}

}
