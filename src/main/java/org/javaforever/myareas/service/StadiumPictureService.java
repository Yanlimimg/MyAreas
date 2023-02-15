package org.javaforever.myareas.service;

import java.util.List;
import org.javaforever.myareas.domain.StadiumPicture;

public interface StadiumPictureService{
	public List<StadiumPicture> listAllStadiumPictures() throws Exception;
	public Boolean updateStadiumPicture(StadiumPicture stadiumPicture) throws Exception;
	public Boolean deleteStadiumPicture(Long stadiumPictureId) throws Exception;
	public Boolean addStadiumPicture(StadiumPicture stadiumPicture) throws Exception;
	public boolean softDeleteStadiumPicture(Long stadiumPictureId) throws Exception;
	public StadiumPicture findStadiumPictureByStadiumPictureId(Long stadiumPictureId) throws Exception;
	public StadiumPicture findStadiumPictureByStadiumPictureName(String stadiumPictureName) throws Exception;
	public List<StadiumPicture> searchStadiumPicturesByStadiumPictureName(String stadiumPictureName) throws Exception;
	public List<StadiumPicture> listActiveStadiumPictures() throws Exception;
	public void deleteAllStadiumPictures(String ids) throws Exception;
	public void softDeleteAllStadiumPictures(String ids) throws Exception;
	public Boolean toggleStadiumPicture(Long stadiumPictureId) throws Exception;
	public Boolean toggleOneStadiumPicture(Long stadiumPictureId) throws Exception;
	public List<StadiumPicture> searchStadiumPicturesByFieldsByPage(StadiumPicture stadiumPicture,Integer pagesize,Integer pagenum) throws Exception;
	public Boolean activateStadiumPicture(Long stadiumPictureId) throws Exception;
	public void activateAllStadiumPictures(String ids) throws Exception;
	public List<StadiumPicture> searchStadiumPicturesByFields(StadiumPicture stadiumPicture) throws Exception;
	public Boolean cloneStadiumPicture(Long stadiumPictureId) throws Exception;
	public void cloneAllStadiumPictures(String ids) throws Exception;
	public Integer countSearchStadiumPicturesByFieldsRecords(StadiumPicture stadiumPicture) throws Exception;
	public Integer countActiveStadiumPictureRecords() throws Exception;
}
