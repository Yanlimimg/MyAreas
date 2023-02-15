package org.javaforever.myareas.serviceimpl;

import java.util.List;
import org.javaforever.myareas.dao.StadiumAreaDao;
import org.javaforever.myareas.domain.StadiumArea;
import org.javaforever.myareas.service.StadiumAreaService;
import org.javaforever.myareas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StadiumAreaServiceImpl implements StadiumAreaService{
	@Autowired
	protected StadiumAreaDao dao;
	@Override
	public void activateAllStadiumAreas(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumAreaId = Long.valueOf(idString);
			dao.activateStadiumArea(stadiumAreaId);
		}
	}

	@Override
	public Boolean activateStadiumArea(Long stadiumAreaId) throws Exception{
		dao.activateStadiumArea(stadiumAreaId);
		return true;
	}

	@Override
	public Boolean addStadiumArea(StadiumArea stadiumArea) throws Exception{
		dao.addStadiumArea(stadiumArea);
		return true;
	}

	@Override
	public void cloneAllStadiumAreas(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumAreaId = Long.valueOf(idString);
			cloneStadiumArea(stadiumAreaId);
		}
	}

	@Override
	public Boolean cloneStadiumArea(Long stadiumAreaId) throws Exception{
		StadiumArea stadiumArea = dao.findStadiumAreaFullInfoByStadiumAreaId(stadiumAreaId);
		
		String stadiumAreaName = stadiumArea.getStadiumAreaName();
		String shortName = stadiumAreaName;
		if (stadiumAreaName.contains("_") && StringUtil.isInteger(stadiumAreaName.substring(stadiumAreaName.lastIndexOf('_')+1,stadiumAreaName.length()))) {
			shortName = stadiumAreaName.substring(0,stadiumAreaName.lastIndexOf('_'));
		}
		List<StadiumArea> names = dao.searchStadiumAreaIndexedNamesByShortName(shortName);
		int indexNum = 1;
		for (StadiumArea stadiumArea0:names) {
			String name = stadiumArea0.getStadiumAreaName();
			if (name.contains("_") && StringUtil.isInteger(name.substring(name.lastIndexOf('_')+1,name.length()))) {
				
				int index = Integer.valueOf(name.substring(name.lastIndexOf('_')+1,name.length()));
				if (index >= indexNum) {
					indexNum = index + 1;
				}
			}
		}
		String indexName = shortName + "_" + indexNum;
		stadiumArea.setStadiumAreaName(indexName);
		dao.addStadiumArea(stadiumArea);
		return true;
	}

	@Override
	public Integer countActiveStadiumAreaRecords() throws Exception{
		return dao.countActiveStadiumAreaRecords();
	}

	@Override
	public Integer countSearchStadiumAreasByFieldsRecords(StadiumArea stadiumArea) throws Exception{
		return dao.countSearchStadiumAreasByFieldsRecords(stadiumArea);
	}

	@Override
	public void deleteAllStadiumAreas(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumAreaId = Long.valueOf(idString);
			dao.deleteStadiumArea(stadiumAreaId);
		}
	}

	@Override
	public Boolean deleteStadiumArea(Long stadiumAreaId) throws Exception{
		dao.deleteStadiumArea(stadiumAreaId);
		return true;
	}

	@Override
	public StadiumArea findStadiumAreaByStadiumAreaId(Long stadiumAreaId) throws Exception{
		return dao.findStadiumAreaByStadiumAreaId(stadiumAreaId);
	}

	@Override
	public StadiumArea findStadiumAreaByStadiumAreaName(String stadiumAreaName) throws Exception{
		return dao.findStadiumAreaByStadiumAreaName(stadiumAreaName);
	}

	@Override
	public List<StadiumArea> listActiveStadiumAreas() throws Exception{
		return dao.listActiveStadiumAreas();
	}

	@Override
	public List<StadiumArea> listAllStadiumAreas() throws Exception{
		return dao.listAllStadiumAreas();
	}

	@Override
	public List<StadiumArea> searchStadiumAreasByFields(StadiumArea stadiumArea) throws Exception{
		return dao.searchStadiumAreasByFields(stadiumArea);
	}

	@Override
	public List<StadiumArea> searchStadiumAreasByFieldsByPage(StadiumArea stadiumArea,Integer pagesize,Integer pagenum) throws Exception{
		Integer start = (pagenum-1)*pagesize;
		Integer limit = pagesize;
		return dao.searchStadiumAreasByFieldsByLimit(stadiumArea,start,limit);
	}

	@Override
	public List<StadiumArea> searchStadiumAreasByStadiumAreaName(String stadiumAreaName) throws Exception{
		return dao.searchStadiumAreasByStadiumAreaName(stadiumAreaName);
	}

	public void setDao(StadiumAreaDao dao){
		this.dao = dao;
	}

	@Override
	public void softDeleteAllStadiumAreas(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumAreaId = Long.valueOf(idString);
			dao.softDeleteStadiumArea(stadiumAreaId);
		}
	}

	@Override
	public boolean softDeleteStadiumArea(Long stadiumAreaId) throws Exception{
		dao.softDeleteStadiumArea(stadiumAreaId);
		return true;
	}

	@Override
	public Boolean toggleOneStadiumArea(Long stadiumAreaId) throws Exception{
		StadiumArea stadiumArea = dao.findStadiumAreaByStadiumAreaId(stadiumAreaId);
		if (stadiumArea.getActive()==false) {
			dao.toggleStadiumArea(stadiumAreaId);
		}
		else {
			Integer count = dao.countActiveStadiumAreaRecords();
			if (count > 1){
				dao.toggleStadiumArea(stadiumAreaId);
			}
		}
		return true;
	}

	@Override
	public Boolean toggleStadiumArea(Long stadiumAreaId) throws Exception{
		dao.toggleStadiumArea(stadiumAreaId);
		return true;
	}

	@Override
	@Transactional
	public Boolean updateStadiumArea(StadiumArea stadiumArea) throws Exception{
		dao.updateStadiumArea(stadiumArea);
		return true;
	}

}
