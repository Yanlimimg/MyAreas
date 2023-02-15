package org.javaforever.myareas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.javaforever.myareas.domain.StadiumPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StadiumPictureDao{
	public List<StadiumPicture> listAllStadiumPictures() throws Exception;
	public void updateStadiumPicture(StadiumPicture stadiumPicture) throws Exception;
	public void deleteStadiumPicture(Long stadiumPictureId) throws Exception;
	public void addStadiumPicture(StadiumPicture stadiumPicture) throws Exception;
	public void softDeleteStadiumPicture(Long stadiumPictureId) throws Exception;
	public StadiumPicture findStadiumPictureByStadiumPictureId(Long stadiumPictureId) throws Exception;
	public StadiumPicture findStadiumPictureByStadiumPictureName(String stadiumPictureName) throws Exception;
	public List<StadiumPicture> searchStadiumPicturesByStadiumPictureName(String stadiumPictureName) throws Exception;
	public List<StadiumPicture> listActiveStadiumPictures() throws Exception;
	public void toggleStadiumPicture(Long stadiumPictureId) throws Exception;
	public List<StadiumPicture> searchStadiumPicturesByFieldsByLimit(@Param(value="stadiumPicture") StadiumPicture stadiumPicture,@Param(value="start") Integer start,@Param(value="limit") Integer limit) throws Exception;
	public void activateStadiumPicture(Long stadiumPictureId) throws Exception;
	public List<StadiumPicture> searchStadiumPicturesByFields(StadiumPicture stadiumPicture) throws Exception;
	public StadiumPicture findStadiumPictureFullInfoByStadiumPictureId(Long stadiumPictureId) throws Exception;
	public List<StadiumPicture> searchStadiumPictureIndexedNamesByShortName(String shortName) throws Exception;
	public Integer countSearchStadiumPicturesByFieldsRecords(StadiumPicture stadiumPicture) throws Exception;
	public Integer countActiveStadiumPictureRecords() throws Exception;
}
