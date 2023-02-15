package org.javaforever.myareas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.javaforever.myareas.domain.Stadium;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StadiumDao{
	public List<Stadium> listAllStadiums() throws Exception;
	public void updateStadium(Stadium stadium) throws Exception;
	public void deleteStadium(Long stadiumId) throws Exception;
	public void addStadium(Stadium stadium) throws Exception;
	public void softDeleteStadium(Long stadiumId) throws Exception;
	public Stadium findStadiumByStadiumId(Long stadiumId) throws Exception;
	public Stadium findStadiumByStadiumName(String stadiumName) throws Exception;
	public List<Stadium> searchStadiumsByStadiumName(String stadiumName) throws Exception;
	public List<Stadium> listActiveStadiums() throws Exception;
	public void toggleStadium(Long stadiumId) throws Exception;
	public List<Stadium> searchStadiumsByFieldsByLimit(@Param(value="stadium") Stadium stadium,@Param(value="start") Integer start,@Param(value="limit") Integer limit) throws Exception;
	public void activateStadium(Long stadiumId) throws Exception;
	public List<Stadium> searchStadiumsByFields(Stadium stadium) throws Exception;
	public Stadium findStadiumFullInfoByStadiumId(Long stadiumId) throws Exception;
	public List<Stadium> searchStadiumIndexedNamesByShortName(String shortName) throws Exception;
	public Integer countSearchStadiumsByFieldsRecords(Stadium stadium) throws Exception;
	public Integer countActiveStadiumRecords() throws Exception;
}
