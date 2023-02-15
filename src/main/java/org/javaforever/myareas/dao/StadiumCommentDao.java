package org.javaforever.myareas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.javaforever.myareas.domain.StadiumComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StadiumCommentDao{
	public List<StadiumComment> listAllStadiumComments() throws Exception;
	public void updateStadiumComment(StadiumComment stadiumComment) throws Exception;
	public void deleteStadiumComment(Long stadiumCommentId) throws Exception;
	public void addStadiumComment(StadiumComment stadiumComment) throws Exception;
	public void softDeleteStadiumComment(Long stadiumCommentId) throws Exception;
	public StadiumComment findStadiumCommentByStadiumCommentId(Long stadiumCommentId) throws Exception;
	public StadiumComment findStadiumCommentByStadiumCommentName(String stadiumCommentName) throws Exception;
	public List<StadiumComment> searchStadiumCommentsByStadiumCommentName(String stadiumCommentName) throws Exception;
	public List<StadiumComment> listActiveStadiumComments() throws Exception;
	public void toggleStadiumComment(Long stadiumCommentId) throws Exception;
	public List<StadiumComment> searchStadiumCommentsByFieldsByLimit(@Param(value="stadiumComment") StadiumComment stadiumComment,@Param(value="start") Integer start,@Param(value="limit") Integer limit) throws Exception;
	public void activateStadiumComment(Long stadiumCommentId) throws Exception;
	public List<StadiumComment> searchStadiumCommentsByFields(StadiumComment stadiumComment) throws Exception;
	public StadiumComment findStadiumCommentFullInfoByStadiumCommentId(Long stadiumCommentId) throws Exception;
	public List<StadiumComment> searchStadiumCommentIndexedNamesByShortName(String shortName) throws Exception;
	public Integer countSearchStadiumCommentsByFieldsRecords(StadiumComment stadiumComment) throws Exception;
	public Integer countActiveStadiumCommentRecords() throws Exception;
}
