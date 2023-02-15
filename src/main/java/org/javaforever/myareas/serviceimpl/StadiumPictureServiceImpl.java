package org.javaforever.myareas.serviceimpl;

import java.util.List;
import org.javaforever.myareas.dao.StadiumPictureDao;
import org.javaforever.myareas.domain.StadiumPicture;
import org.javaforever.myareas.service.StadiumPictureService;
import org.javaforever.myareas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StadiumPictureServiceImpl implements StadiumPictureService{
	@Autowired
	protected StadiumPictureDao dao;
	@Override
	public void activateAllStadiumPictures(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumPictureId = Long.valueOf(idString);
			dao.activateStadiumPicture(stadiumPictureId);
		}
	}

	@Override
	public Boolean activateStadiumPicture(Long stadiumPictureId) throws Exception{
		dao.activateStadiumPicture(stadiumPictureId);
		return true;
	}

	@Override
	public Boolean addStadiumPicture(StadiumPicture stadiumPicture) throws Exception{
		dao.addStadiumPicture(stadiumPicture);
		return true;
	}

	@Override
	public void cloneAllStadiumPictures(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumPictureId = Long.valueOf(idString);
			cloneStadiumPicture(stadiumPictureId);
		}
	}

	@Override
	public Boolean cloneStadiumPicture(Long stadiumPictureId) throws Exception{
		StadiumPicture stadiumPicture = dao.findStadiumPictureFullInfoByStadiumPictureId(stadiumPictureId);
		
		String stadiumPictureName = stadiumPicture.getStadiumPictureName();
		String shortName = stadiumPictureName;
		if (stadiumPictureName.contains("_") && StringUtil.isInteger(stadiumPictureName.substring(stadiumPictureName.lastIndexOf('_')+1,stadiumPictureName.length()))) {
			shortName = stadiumPictureName.substring(0,stadiumPictureName.lastIndexOf('_'));
		}
		List<StadiumPicture> names = dao.searchStadiumPictureIndexedNamesByShortName(shortName);
		int indexNum = 1;
		for (StadiumPicture stadiumPicture0:names) {
			String name = stadiumPicture0.getStadiumPictureName();
			if (name.contains("_") && StringUtil.isInteger(name.substring(name.lastIndexOf('_')+1,name.length()))) {
				
				int index = Integer.valueOf(name.substring(name.lastIndexOf('_')+1,name.length()));
				if (index >= indexNum) {
					indexNum = index + 1;
				}
			}
		}
		String indexName = shortName + "_" + indexNum;
		stadiumPicture.setStadiumPictureName(indexName);
		dao.addStadiumPicture(stadiumPicture);
		return true;
	}

	@Override
	public Integer countActiveStadiumPictureRecords() throws Exception{
		return dao.countActiveStadiumPictureRecords();
	}

	@Override
	public Integer countSearchStadiumPicturesByFieldsRecords(StadiumPicture stadiumPicture) throws Exception{
		return dao.countSearchStadiumPicturesByFieldsRecords(stadiumPicture);
	}

	@Override
	public void deleteAllStadiumPictures(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumPictureId = Long.valueOf(idString);
			dao.deleteStadiumPicture(stadiumPictureId);
		}
	}

	@Override
	public Boolean deleteStadiumPicture(Long stadiumPictureId) throws Exception{
		dao.deleteStadiumPicture(stadiumPictureId);
		return true;
	}

	@Override
	public StadiumPicture findStadiumPictureByStadiumPictureId(Long stadiumPictureId) throws Exception{
		return dao.findStadiumPictureByStadiumPictureId(stadiumPictureId);
	}

	@Override
	public StadiumPicture findStadiumPictureByStadiumPictureName(String stadiumPictureName) throws Exception{
		return dao.findStadiumPictureByStadiumPictureName(stadiumPictureName);
	}

	@Override
	public List<StadiumPicture> listActiveStadiumPictures() throws Exception{
		return dao.listActiveStadiumPictures();
	}

	@Override
	public List<StadiumPicture> listAllStadiumPictures() throws Exception{
		return dao.listAllStadiumPictures();
	}

	@Override
	public List<StadiumPicture> searchStadiumPicturesByFields(StadiumPicture stadiumPicture) throws Exception{
		return dao.searchStadiumPicturesByFields(stadiumPicture);
	}

	@Override
	public List<StadiumPicture> searchStadiumPicturesByFieldsByPage(StadiumPicture stadiumPicture,Integer pagesize,Integer pagenum) throws Exception{
		Integer start = (pagenum-1)*pagesize;
		Integer limit = pagesize;
		return dao.searchStadiumPicturesByFieldsByLimit(stadiumPicture,start,limit);
	}

	@Override
	public List<StadiumPicture> searchStadiumPicturesByStadiumPictureName(String stadiumPictureName) throws Exception{
		return dao.searchStadiumPicturesByStadiumPictureName(stadiumPictureName);
	}

	public void setDao(StadiumPictureDao dao){
		this.dao = dao;
	}

	@Override
	public void softDeleteAllStadiumPictures(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumPictureId = Long.valueOf(idString);
			dao.softDeleteStadiumPicture(stadiumPictureId);
		}
	}

	@Override
	public boolean softDeleteStadiumPicture(Long stadiumPictureId) throws Exception{
		dao.softDeleteStadiumPicture(stadiumPictureId);
		return true;
	}

	@Override
	public Boolean toggleOneStadiumPicture(Long stadiumPictureId) throws Exception{
		StadiumPicture stadiumPicture = dao.findStadiumPictureByStadiumPictureId(stadiumPictureId);
		if (stadiumPicture.getActive()==false) {
			dao.toggleStadiumPicture(stadiumPictureId);
		}
		else {
			Integer count = dao.countActiveStadiumPictureRecords();
			if (count > 1){
				dao.toggleStadiumPicture(stadiumPictureId);
			}
		}
		return true;
	}

	@Override
	public Boolean toggleStadiumPicture(Long stadiumPictureId) throws Exception{
		dao.toggleStadiumPicture(stadiumPictureId);
		return true;
	}

	@Override
	@Transactional
	public Boolean updateStadiumPicture(StadiumPicture stadiumPicture) throws Exception{
		dao.updateStadiumPicture(stadiumPicture);
		return true;
	}

}
