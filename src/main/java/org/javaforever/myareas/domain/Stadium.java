package org.javaforever.myareas.domain;


public class Stadium implements Comparable<Stadium> {
	protected Long stadiumId;
	protected String stadiumName;
	protected Boolean active;
	protected Double stadiumComment;
	protected String opentime;
	protected Double latitude;
	protected Double longitude;
	protected String telephone;
	protected String detail;
	protected String canPark;
	protected String canSell;
	protected String canRest;
	protected String canRent;
	protected String canCard;
	protected String canBath;
	protected String canLongBuy;
	protected Integer ballType;
	protected Integer stadiumType;
	protected Long cityId;

	public Boolean getActive(){
		return this.active;
	}

	public Integer getBallType(){
		return this.ballType;
	}

	public String getCanBath(){
		return this.canBath;
	}

	public String getCanCard(){
		return this.canCard;
	}

	public String getCanLongBuy(){
		return this.canLongBuy;
	}

	public String getCanPark(){
		return this.canPark;
	}

	public String getCanRent(){
		return this.canRent;
	}

	public String getCanRest(){
		return this.canRest;
	}

	public String getCanSell(){
		return this.canSell;
	}

	public Long getCityId(){
		return this.cityId;
	}

	public String getDetail(){
		return this.detail;
	}

	public Double getLatitude(){
		return this.latitude;
	}

	public Double getLongitude(){
		return this.longitude;
	}

	public String getOpentime(){
		return this.opentime;
	}

	public Double getStadiumComment(){
		return this.stadiumComment;
	}

	public Long getStadiumId(){
		return this.stadiumId;
	}

	public String getStadiumName(){
		return this.stadiumName;
	}

	public Integer getStadiumType(){
		return this.stadiumType;
	}

	public String getTelephone(){
		return this.telephone;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void setBallType(Integer ballType){
		this.ballType = ballType;
	}

	public void setCanBath(String canBath){
		this.canBath = canBath;
	}

	public void setCanCard(String canCard){
		this.canCard = canCard;
	}

	public void setCanLongBuy(String canLongBuy){
		this.canLongBuy = canLongBuy;
	}

	public void setCanPark(String canPark){
		this.canPark = canPark;
	}

	public void setCanRent(String canRent){
		this.canRent = canRent;
	}

	public void setCanRest(String canRest){
		this.canRest = canRest;
	}

	public void setCanSell(String canSell){
		this.canSell = canSell;
	}

	public void setCityId(Long cityId){
		this.cityId = cityId;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude){
		this.longitude = longitude;
	}

	public void setOpentime(String opentime){
		this.opentime = opentime;
	}

	public void setStadiumComment(Double stadiumComment){
		this.stadiumComment = stadiumComment;
	}

	public void setStadiumId(Long stadiumId){
		this.stadiumId = stadiumId;
	}

	public void setStadiumName(String stadiumName){
		this.stadiumName = stadiumName;
	}

	public void setStadiumType(Integer stadiumType){
		this.stadiumType = stadiumType;
	}

	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	public int compareTo(Stadium stadium){
		return this.getStadiumId().compareTo(stadium.getStadiumId());
	}

}
