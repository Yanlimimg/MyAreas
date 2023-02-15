package org.javaforever.myareas.service;

import java.util.List;
import org.javaforever.myareas.domain.StadiumComment;

public interface StadiumCommentService{
	public List<StadiumComment> listAllStadiumComments() throws Exception;
	public Boolean updateStadiumComment(StadiumComment stadiumComment) throws Exception;
	public Boolean deleteStadiumComment(Long stadiumCommentId) throws Exception;
	public Boolean addStadiumComment(StadiumComment stadiumComment) throws Exception;
	public boolean softDeleteStadiumComment(Long stadiumCommentId) throws Exception;
	public StadiumComment findStadiumCommentByStadiumCommentId(Long stadiumCommentId) throws Exception;
	public StadiumComment findStadiumCommentByStadiumCommentName(String stadiumCommentName) throws Exception;
	public List<StadiumComment> searchStadiumCommentsByStadiumCommentName(String stadiumCommentName) throws Exception;
	public List<StadiumComment> listActiveStadiumComments() throws Exception;
	public void deleteAllStadiumComments(String ids) throws Exception;
	public void softDeleteAllStadiumComments(String ids) throws Exception;
	public Boolean toggleStadiumComment(Long stadiumCommentId) throws Exception;
	public Boolean toggleOneStadiumComment(Long stadiumCommentId) throws Exception;
	public List<StadiumComment> searchStadiumCommentsByFieldsByPage(StadiumComment stadiumComment,Integer pagesize,Integer pagenum) throws Exception;
	public Boolean activateStadiumComment(Long stadiumCommentId) throws Exception;
	public void activateAllStadiumComments(String ids) throws Exception;
	public List<StadiumComment> searchStadiumCommentsByFields(StadiumComment stadiumComment) throws Exception;
	public Boolean cloneStadiumComment(Long stadiumCommentId) throws Exception;
	public void cloneAllStadiumComments(String ids) throws Exception;
	public Integer countSearchStadiumCommentsByFieldsRecords(StadiumComment stadiumComment) throws Exception;
	public Integer countActiveStadiumCommentRecords() throws Exception;
}
