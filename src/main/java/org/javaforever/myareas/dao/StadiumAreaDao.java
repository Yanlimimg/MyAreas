package org.javaforever.myareas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.javaforever.myareas.domain.StadiumArea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StadiumAreaDao{
	public List<StadiumArea> listAllStadiumAreas() throws Exception;
	public void updateStadiumArea(StadiumArea stadiumArea) throws Exception;
	public void deleteStadiumArea(Long stadiumAreaId) throws Exception;
	public void addStadiumArea(StadiumArea stadiumArea) throws Exception;
	public void softDeleteStadiumArea(Long stadiumAreaId) throws Exception;
	public StadiumArea findStadiumAreaByStadiumAreaId(Long stadiumAreaId) throws Exception;
	public StadiumArea findStadiumAreaByStadiumAreaName(String stadiumAreaName) throws Exception;
	public List<StadiumArea> searchStadiumAreasByStadiumAreaName(String stadiumAreaName) throws Exception;
	public List<StadiumArea> listActiveStadiumAreas() throws Exception;
	public void toggleStadiumArea(Long stadiumAreaId) throws Exception;
	public List<StadiumArea> searchStadiumAreasByFieldsByLimit(@Param(value="stadiumArea") StadiumArea stadiumArea,@Param(value="start") Integer start,@Param(value="limit") Integer limit) throws Exception;
	public void activateStadiumArea(Long stadiumAreaId) throws Exception;
	public List<StadiumArea> searchStadiumAreasByFields(StadiumArea stadiumArea) throws Exception;
	public StadiumArea findStadiumAreaFullInfoByStadiumAreaId(Long stadiumAreaId) throws Exception;
	public List<StadiumArea> searchStadiumAreaIndexedNamesByShortName(String shortName) throws Exception;
	public Integer countSearchStadiumAreasByFieldsRecords(StadiumArea stadiumArea) throws Exception;
	public Integer countActiveStadiumAreaRecords() throws Exception;
}
