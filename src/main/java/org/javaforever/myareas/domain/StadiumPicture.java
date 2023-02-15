package org.javaforever.myareas.domain;


public class StadiumPicture implements Comparable<StadiumPicture> {
	protected Long stadiumPictureId;
	protected String stadiumPictureName;
	protected Boolean active;
	protected Long stadiumId;
	protected String picture;

	public Boolean getActive(){
		return this.active;
	}

	public String getPicture(){
		return this.picture;
	}

	public Long getStadiumId(){
		return this.stadiumId;
	}

	public Long getStadiumPictureId(){
		return this.stadiumPictureId;
	}

	public String getStadiumPictureName(){
		return this.stadiumPictureName;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void setPicture(String picture){
		this.picture = picture;
	}

	public void setStadiumId(Long stadiumId){
		this.stadiumId = stadiumId;
	}

	public void setStadiumPictureId(Long stadiumPictureId){
		this.stadiumPictureId = stadiumPictureId;
	}

	public void setStadiumPictureName(String stadiumPictureName){
		this.stadiumPictureName = stadiumPictureName;
	}

	public int compareTo(StadiumPicture stadiumPicture){
		return this.getStadiumPictureId().compareTo(stadiumPicture.getStadiumPictureId());
	}

}
