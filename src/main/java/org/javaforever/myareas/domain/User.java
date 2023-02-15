package org.javaforever.myareas.domain;


public class User implements Comparable<User> {
	protected Long userId;
	protected String loginName;
	protected Boolean active;
	protected String gender;
	protected String mobile;
	protected String mobilePublic;
	protected String qq;
	protected String weixin;
	protected String weibo;
	protected String nickName;
	protected String realName;
	protected Integer userType;
	protected String userPassword;
	protected String startDate;
	protected Integer userLevel;
	protected Integer creditLevel;
	protected Double userComment;
	protected String birthday;
	protected String normalAddress;
	protected String userDescription;
	protected Integer feeLevel;
	protected String tokenId;
	protected Double latitude;
	protected Double longitude;
	protected Long cityId;

	public Boolean getActive(){
		return this.active;
	}

	public String getBirthday(){
		return this.birthday;
	}

	public Long getCityId(){
		return this.cityId;
	}

	public Integer getCreditLevel(){
		return this.creditLevel;
	}

	public Integer getFeeLevel(){
		return this.feeLevel;
	}

	public String getGender(){
		return this.gender;
	}

	public Double getLatitude(){
		return this.latitude;
	}

	public String getLoginName(){
		return this.loginName;
	}

	public Double getLongitude(){
		return this.longitude;
	}

	public String getMobile(){
		return this.mobile;
	}

	public String getMobilePublic(){
		return this.mobilePublic;
	}

	public String getNickName(){
		return this.nickName;
	}

	public String getNormalAddress(){
		return this.normalAddress;
	}

	public String getQq(){
		return this.qq;
	}

	public String getRealName(){
		return this.realName;
	}

	public String getStartDate(){
		return this.startDate;
	}

	public String getTokenId(){
		return this.tokenId;
	}

	public Double getUserComment(){
		return this.userComment;
	}

	public String getUserDescription(){
		return this.userDescription;
	}

	public Long getUserId(){
		return this.userId;
	}

	public Integer getUserLevel(){
		return this.userLevel;
	}

	public String getUserPassword(){
		return this.userPassword;
	}

	public Integer getUserType(){
		return this.userType;
	}

	public String getWeibo(){
		return this.weibo;
	}

	public String getWeixin(){
		return this.weixin;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void setBirthday(String birthday){
		this.birthday = birthday;
	}

	public void setCityId(Long cityId){
		this.cityId = cityId;
	}

	public void setCreditLevel(Integer creditLevel){
		this.creditLevel = creditLevel;
	}

	public void setFeeLevel(Integer feeLevel){
		this.feeLevel = feeLevel;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public void setLatitude(Double latitude){
		this.latitude = latitude;
	}

	public void setLoginName(String loginName){
		this.loginName = loginName;
	}

	public void setLongitude(Double longitude){
		this.longitude = longitude;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public void setMobilePublic(String mobilePublic){
		this.mobilePublic = mobilePublic;
	}

	public void setNickName(String nickName){
		this.nickName = nickName;
	}

	public void setNormalAddress(String normalAddress){
		this.normalAddress = normalAddress;
	}

	public void setQq(String qq){
		this.qq = qq;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public void setTokenId(String tokenId){
		this.tokenId = tokenId;
	}

	public void setUserComment(Double userComment){
		this.userComment = userComment;
	}

	public void setUserDescription(String userDescription){
		this.userDescription = userDescription;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public void setUserLevel(Integer userLevel){
		this.userLevel = userLevel;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}

	public void setUserType(Integer userType){
		this.userType = userType;
	}

	public void setWeibo(String weibo){
		this.weibo = weibo;
	}

	public void setWeixin(String weixin){
		this.weixin = weixin;
	}

	public int compareTo(User user){
		return this.getUserId().compareTo(user.getUserId());
	}

}
