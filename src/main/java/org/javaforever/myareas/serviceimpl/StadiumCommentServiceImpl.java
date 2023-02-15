package org.javaforever.myareas.serviceimpl;

import java.util.List;
import org.javaforever.myareas.dao.StadiumCommentDao;
import org.javaforever.myareas.domain.StadiumComment;
import org.javaforever.myareas.service.StadiumCommentService;
import org.javaforever.myareas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StadiumCommentServiceImpl implements StadiumCommentService{
	@Autowired
	protected StadiumCommentDao dao;
	@Override
	public void activateAllStadiumComments(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumCommentId = Long.valueOf(idString);
			dao.activateStadiumComment(stadiumCommentId);
		}
	}

	@Override
	public Boolean activateStadiumComment(Long stadiumCommentId) throws Exception{
		dao.activateStadiumComment(stadiumCommentId);
		return true;
	}

	@Override
	public Boolean addStadiumComment(StadiumComment stadiumComment) throws Exception{
		dao.addStadiumComment(stadiumComment);
		return true;
	}

	@Override
	public void cloneAllStadiumComments(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumCommentId = Long.valueOf(idString);
			cloneStadiumComment(stadiumCommentId);
		}
	}

	@Override
	public Boolean cloneStadiumComment(Long stadiumCommentId) throws Exception{
		StadiumComment stadiumComment = dao.findStadiumCommentFullInfoByStadiumCommentId(stadiumCommentId);
		
		String stadiumCommentName = stadiumComment.getStadiumCommentName();
		String shortName = stadiumCommentName;
		if (stadiumCommentName.contains("_") && StringUtil.isInteger(stadiumCommentName.substring(stadiumCommentName.lastIndexOf('_')+1,stadiumCommentName.length()))) {
			shortName = stadiumCommentName.substring(0,stadiumCommentName.lastIndexOf('_'));
		}
		List<StadiumComment> names = dao.searchStadiumCommentIndexedNamesByShortName(shortName);
		int indexNum = 1;
		for (StadiumComment stadiumComment0:names) {
			String name = stadiumComment0.getStadiumCommentName();
			if (name.contains("_") && StringUtil.isInteger(name.substring(name.lastIndexOf('_')+1,name.length()))) {
				
				int index = Integer.valueOf(name.substring(name.lastIndexOf('_')+1,name.length()));
				if (index >= indexNum) {
					indexNum = index + 1;
				}
			}
		}
		String indexName = shortName + "_" + indexNum;
		stadiumComment.setStadiumCommentName(indexName);
		dao.addStadiumComment(stadiumComment);
		return true;
	}

	@Override
	public Integer countActiveStadiumCommentRecords() throws Exception{
		return dao.countActiveStadiumCommentRecords();
	}

	@Override
	public Integer countSearchStadiumCommentsByFieldsRecords(StadiumComment stadiumComment) throws Exception{
		return dao.countSearchStadiumCommentsByFieldsRecords(stadiumComment);
	}

	@Override
	public void deleteAllStadiumComments(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumCommentId = Long.valueOf(idString);
			dao.deleteStadiumComment(stadiumCommentId);
		}
	}

	@Override
	public Boolean deleteStadiumComment(Long stadiumCommentId) throws Exception{
		dao.deleteStadiumComment(stadiumCommentId);
		return true;
	}

	@Override
	public StadiumComment findStadiumCommentByStadiumCommentId(Long stadiumCommentId) throws Exception{
		return dao.findStadiumCommentByStadiumCommentId(stadiumCommentId);
	}

	@Override
	public StadiumComment findStadiumCommentByStadiumCommentName(String stadiumCommentName) throws Exception{
		return dao.findStadiumCommentByStadiumCommentName(stadiumCommentName);
	}

	@Override
	public List<StadiumComment> listActiveStadiumComments() throws Exception{
		return dao.listActiveStadiumComments();
	}

	@Override
	public List<StadiumComment> listAllStadiumComments() throws Exception{
		return dao.listAllStadiumComments();
	}

	@Override
	public List<StadiumComment> searchStadiumCommentsByFields(StadiumComment stadiumComment) throws Exception{
		return dao.searchStadiumCommentsByFields(stadiumComment);
	}

	@Override
	public List<StadiumComment> searchStadiumCommentsByFieldsByPage(StadiumComment stadiumComment,Integer pagesize,Integer pagenum) throws Exception{
		Integer start = (pagenum-1)*pagesize;
		Integer limit = pagesize;
		return dao.searchStadiumCommentsByFieldsByLimit(stadiumComment,start,limit);
	}

	@Override
	public List<StadiumComment> searchStadiumCommentsByStadiumCommentName(String stadiumCommentName) throws Exception{
		return dao.searchStadiumCommentsByStadiumCommentName(stadiumCommentName);
	}

	public void setDao(StadiumCommentDao dao){
		this.dao = dao;
	}

	@Override
	public void softDeleteAllStadiumComments(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumCommentId = Long.valueOf(idString);
			dao.softDeleteStadiumComment(stadiumCommentId);
		}
	}

	@Override
	public boolean softDeleteStadiumComment(Long stadiumCommentId) throws Exception{
		dao.softDeleteStadiumComment(stadiumCommentId);
		return true;
	}

	@Override
	public Boolean toggleOneStadiumComment(Long stadiumCommentId) throws Exception{
		StadiumComment stadiumComment = dao.findStadiumCommentByStadiumCommentId(stadiumCommentId);
		if (stadiumComment.getActive()==false) {
			dao.toggleStadiumComment(stadiumCommentId);
		}
		else {
			Integer count = dao.countActiveStadiumCommentRecords();
			if (count > 1){
				dao.toggleStadiumComment(stadiumCommentId);
			}
		}
		return true;
	}

	@Override
	public Boolean toggleStadiumComment(Long stadiumCommentId) throws Exception{
		dao.toggleStadiumComment(stadiumCommentId);
		return true;
	}

	@Override
	@Transactional
	public Boolean updateStadiumComment(StadiumComment stadiumComment) throws Exception{
		dao.updateStadiumComment(stadiumComment);
		return true;
	}

}
