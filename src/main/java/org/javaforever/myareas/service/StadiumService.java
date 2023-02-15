package org.javaforever.myareas.service;

import java.util.List;
import org.javaforever.myareas.domain.Stadium;

public interface StadiumService{
	public List<Stadium> listAllStadiums() throws Exception;
	public Boolean updateStadium(Stadium stadium) throws Exception;
	public Boolean deleteStadium(Long stadiumId) throws Exception;
	public Boolean addStadium(Stadium stadium) throws Exception;
	public boolean softDeleteStadium(Long stadiumId) throws Exception;
	public Stadium findStadiumByStadiumId(Long stadiumId) throws Exception;
	public Stadium findStadiumByStadiumName(String stadiumName) throws Exception;
	public List<Stadium> searchStadiumsByStadiumName(String stadiumName) throws Exception;
	public List<Stadium> listActiveStadiums() throws Exception;
	public void deleteAllStadiums(String ids) throws Exception;
	public void softDeleteAllStadiums(String ids) throws Exception;
	public Boolean toggleStadium(Long stadiumId) throws Exception;
	public Boolean toggleOneStadium(Long stadiumId) throws Exception;
	public List<Stadium> searchStadiumsByFieldsByPage(Stadium stadium,Integer pagesize,Integer pagenum) throws Exception;
	public Boolean activateStadium(Long stadiumId) throws Exception;
	public void activateAllStadiums(String ids) throws Exception;
	public List<Stadium> searchStadiumsByFields(Stadium stadium) throws Exception;
	public Boolean cloneStadium(Long stadiumId) throws Exception;
	public void cloneAllStadiums(String ids) throws Exception;
	public Integer countSearchStadiumsByFieldsRecords(Stadium stadium) throws Exception;
	public Integer countActiveStadiumRecords() throws Exception;
}
