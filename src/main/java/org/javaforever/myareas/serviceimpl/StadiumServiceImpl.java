package org.javaforever.myareas.serviceimpl;

import java.util.List;
import org.javaforever.myareas.dao.StadiumDao;
import org.javaforever.myareas.domain.Stadium;
import org.javaforever.myareas.service.StadiumService;
import org.javaforever.myareas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StadiumServiceImpl implements StadiumService{
	@Autowired
	protected StadiumDao dao;
	@Override
	public void activateAllStadiums(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumId = Long.valueOf(idString);
			dao.activateStadium(stadiumId);
		}
	}

	@Override
	public Boolean activateStadium(Long stadiumId) throws Exception{
		dao.activateStadium(stadiumId);
		return true;
	}

	@Override
	public Boolean addStadium(Stadium stadium) throws Exception{
		dao.addStadium(stadium);
		return true;
	}

	@Override
	public void cloneAllStadiums(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumId = Long.valueOf(idString);
			cloneStadium(stadiumId);
		}
	}

	@Override
	public Boolean cloneStadium(Long stadiumId) throws Exception{
		Stadium stadium = dao.findStadiumFullInfoByStadiumId(stadiumId);
		
		String stadiumName = stadium.getStadiumName();
		String shortName = stadiumName;
		if (stadiumName.contains("_") && StringUtil.isInteger(stadiumName.substring(stadiumName.lastIndexOf('_')+1,stadiumName.length()))) {
			shortName = stadiumName.substring(0,stadiumName.lastIndexOf('_'));
		}
		List<Stadium> names = dao.searchStadiumIndexedNamesByShortName(shortName);
		int indexNum = 1;
		for (Stadium stadium0:names) {
			String name = stadium0.getStadiumName();
			if (name.contains("_") && StringUtil.isInteger(name.substring(name.lastIndexOf('_')+1,name.length()))) {
				
				int index = Integer.valueOf(name.substring(name.lastIndexOf('_')+1,name.length()));
				if (index >= indexNum) {
					indexNum = index + 1;
				}
			}
		}
		String indexName = shortName + "_" + indexNum;
		stadium.setStadiumName(indexName);
		dao.addStadium(stadium);
		return true;
	}

	@Override
	public Integer countActiveStadiumRecords() throws Exception{
		return dao.countActiveStadiumRecords();
	}

	@Override
	public Integer countSearchStadiumsByFieldsRecords(Stadium stadium) throws Exception{
		return dao.countSearchStadiumsByFieldsRecords(stadium);
	}

	@Override
	public void deleteAllStadiums(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumId = Long.valueOf(idString);
			dao.deleteStadium(stadiumId);
		}
	}

	@Override
	public Boolean deleteStadium(Long stadiumId) throws Exception{
		dao.deleteStadium(stadiumId);
		return true;
	}

	@Override
	public Stadium findStadiumByStadiumId(Long stadiumId) throws Exception{
		return dao.findStadiumByStadiumId(stadiumId);
	}

	@Override
	public Stadium findStadiumByStadiumName(String stadiumName) throws Exception{
		return dao.findStadiumByStadiumName(stadiumName);
	}

	@Override
	public List<Stadium> listActiveStadiums() throws Exception{
		return dao.listActiveStadiums();
	}

	@Override
	public List<Stadium> listAllStadiums() throws Exception{
		return dao.listAllStadiums();
	}

	@Override
	public List<Stadium> searchStadiumsByFields(Stadium stadium) throws Exception{
		return dao.searchStadiumsByFields(stadium);
	}

	@Override
	public List<Stadium> searchStadiumsByFieldsByPage(Stadium stadium,Integer pagesize,Integer pagenum) throws Exception{
		Integer start = (pagenum-1)*pagesize;
		Integer limit = pagesize;
		return dao.searchStadiumsByFieldsByLimit(stadium,start,limit);
	}

	@Override
	public List<Stadium> searchStadiumsByStadiumName(String stadiumName) throws Exception{
		return dao.searchStadiumsByStadiumName(stadiumName);
	}

	public void setDao(StadiumDao dao){
		this.dao = dao;
	}

	@Override
	public void softDeleteAllStadiums(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long stadiumId = Long.valueOf(idString);
			dao.softDeleteStadium(stadiumId);
		}
	}

	@Override
	public boolean softDeleteStadium(Long stadiumId) throws Exception{
		dao.softDeleteStadium(stadiumId);
		return true;
	}

	@Override
	public Boolean toggleOneStadium(Long stadiumId) throws Exception{
		Stadium stadium = dao.findStadiumByStadiumId(stadiumId);
		if (stadium.getActive()==false) {
			dao.toggleStadium(stadiumId);
		}
		else {
			Integer count = dao.countActiveStadiumRecords();
			if (count > 1){
				dao.toggleStadium(stadiumId);
			}
		}
		return true;
	}

	@Override
	public Boolean toggleStadium(Long stadiumId) throws Exception{
		dao.toggleStadium(stadiumId);
		return true;
	}

	@Override
	@Transactional
	public Boolean updateStadium(Stadium stadium) throws Exception{
		dao.updateStadium(stadium);
		return true;
	}

}
