package org.javaforever.myareas.domain;


public class StadiumComment implements Comparable<StadiumComment> {
	protected Long stadiumCommentId;
	protected String stadiumCommentName;
	protected Boolean active;
	protected Long stadiumId;
	protected Long userId;
	protected String createTime;
	protected Integer star;
	protected String comments;

	public Boolean getActive(){
		return this.active;
	}

	public String getComments(){
		return this.comments;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public Long getStadiumCommentId(){
		return this.stadiumCommentId;
	}

	public String getStadiumCommentName(){
		return this.stadiumCommentName;
	}

	public Long getStadiumId(){
		return this.stadiumId;
	}

	public Integer getStar(){
		return this.star;
	}

	public Long getUserId(){
		return this.userId;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void setComments(String comments){
		this.comments = comments;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public void setStadiumCommentId(Long stadiumCommentId){
		this.stadiumCommentId = stadiumCommentId;
	}

	public void setStadiumCommentName(String stadiumCommentName){
		this.stadiumCommentName = stadiumCommentName;
	}

	public void setStadiumId(Long stadiumId){
		this.stadiumId = stadiumId;
	}

	public void setStar(Integer star){
		this.star = star;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public int compareTo(StadiumComment stadiumComment){
		return this.getStadiumCommentId().compareTo(stadiumComment.getStadiumCommentId());
	}

}
