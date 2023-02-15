package org.javaforever.myareas.service;

import java.util.List;
import org.javaforever.myareas.domain.StadiumArea;

public interface StadiumAreaService{
	public List<StadiumArea> listAllStadiumAreas() throws Exception;
	public Boolean updateStadiumArea(StadiumArea stadiumArea) throws Exception;
	public Boolean deleteStadiumArea(Long stadiumAreaId) throws Exception;
	public Boolean addStadiumArea(StadiumArea stadiumArea) throws Exception;
	public boolean softDeleteStadiumArea(Long stadiumAreaId) throws Exception;
	public StadiumArea findStadiumAreaByStadiumAreaId(Long stadiumAreaId) throws Exception;
	public StadiumArea findStadiumAreaByStadiumAreaName(String stadiumAreaName) throws Exception;
	public List<StadiumArea> searchStadiumAreasByStadiumAreaName(String stadiumAreaName) throws Exception;
	public List<StadiumArea> listActiveStadiumAreas() throws Exception;
	public void deleteAllStadiumAreas(String ids) throws Exception;
	public void softDeleteAllStadiumAreas(String ids) throws Exception;
	public Boolean toggleStadiumArea(Long stadiumAreaId) throws Exception;
	public Boolean toggleOneStadiumArea(Long stadiumAreaId) throws Exception;
	public List<StadiumArea> searchStadiumAreasByFieldsByPage(StadiumArea stadiumArea,Integer pagesize,Integer pagenum) throws Exception;
	public Boolean activateStadiumArea(Long stadiumAreaId) throws Exception;
	public void activateAllStadiumAreas(String ids) throws Exception;
	public List<StadiumArea> searchStadiumAreasByFields(StadiumArea stadiumArea) throws Exception;
	public Boolean cloneStadiumArea(Long stadiumAreaId) throws Exception;
	public void cloneAllStadiumAreas(String ids) throws Exception;
	public Integer countSearchStadiumAreasByFieldsRecords(StadiumArea stadiumArea) throws Exception;
	public Integer countActiveStadiumAreaRecords() throws Exception;
}
