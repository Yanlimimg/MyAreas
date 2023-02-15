package org.javaforever.myareas.serviceimpl;

import java.util.List;
import org.javaforever.myareas.dao.UserDao;
import org.javaforever.myareas.domain.User;
import org.javaforever.myareas.service.UserService;
import org.javaforever.myareas.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	protected UserDao dao;
	@Override
	public void activateAllUsers(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long userId = Long.valueOf(idString);
			dao.activateUser(userId);
		}
	}

	@Override
	public Boolean activateUser(Long userId) throws Exception{
		dao.activateUser(userId);
		return true;
	}

	@Override
	public Boolean addUser(User user) throws Exception{
		dao.addUser(user);
		return true;
	}

	@Override
	public void cloneAllUsers(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long userId = Long.valueOf(idString);
			cloneUser(userId);
		}
	}

	@Override
	public Boolean cloneUser(Long userId) throws Exception{
		User user = dao.findUserFullInfoByUserId(userId);
		
		String loginName = user.getLoginName();
		String shortName = loginName;
		if (loginName.contains("_") && StringUtil.isInteger(loginName.substring(loginName.lastIndexOf('_')+1,loginName.length()))) {
			shortName = loginName.substring(0,loginName.lastIndexOf('_'));
		}
		List<User> names = dao.searchUserIndexedNamesByShortName(shortName);
		int indexNum = 1;
		for (User user0:names) {
			String name = user0.getLoginName();
			if (name.contains("_") && StringUtil.isInteger(name.substring(name.lastIndexOf('_')+1,name.length()))) {
				
				int index = Integer.valueOf(name.substring(name.lastIndexOf('_')+1,name.length()));
				if (index >= indexNum) {
					indexNum = index + 1;
				}
			}
		}
		String indexName = shortName + "_" + indexNum;
		user.setLoginName(indexName);
		dao.addUser(user);
		return true;
	}

	@Override
	public Integer countActiveUserRecords() throws Exception{
		return dao.countActiveUserRecords();
	}

	@Override
	public Integer countSearchUsersByFieldsRecords(User user) throws Exception{
		return dao.countSearchUsersByFieldsRecords(user);
	}

	@Override
	public void deleteAllUsers(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long userId = Long.valueOf(idString);
			dao.deleteUser(userId);
		}
	}

	@Override
	public Boolean deleteUser(Long userId) throws Exception{
		dao.deleteUser(userId);
		return true;
	}

	@Override
	public User findUserByLoginName(String loginName) throws Exception{
		return dao.findUserByLoginName(loginName);
	}

	@Override
	public User findUserByUserId(Long userId) throws Exception{
		return dao.findUserByUserId(userId);
	}

	@Override
	public List<User> listActiveUsers() throws Exception{
		return dao.listActiveUsers();
	}

	@Override
	public List<User> listAllUsers() throws Exception{
		return dao.listAllUsers();
	}

	@Override
	public List<User> searchUsersByFields(User user) throws Exception{
		return dao.searchUsersByFields(user);
	}

	@Override
	public List<User> searchUsersByFieldsByPage(User user,Integer pagesize,Integer pagenum) throws Exception{
		Integer start = (pagenum-1)*pagesize;
		Integer limit = pagesize;
		return dao.searchUsersByFieldsByLimit(user,start,limit);
	}

	@Override
	public List<User> searchUsersByLoginName(String loginName) throws Exception{
		return dao.searchUsersByLoginName(loginName);
	}

	public void setDao(UserDao dao){
		this.dao = dao;
	}

	@Override
	public void softDeleteAllUsers(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long userId = Long.valueOf(idString);
			dao.softDeleteUser(userId);
		}
	}

	@Override
	public boolean softDeleteUser(Long userId) throws Exception{
		dao.softDeleteUser(userId);
		return true;
	}

	@Override
	public Boolean toggleOneUser(Long userId) throws Exception{
		User user = dao.findUserByUserId(userId);
		if (user.getActive()==false) {
			dao.toggleUser(userId);
		}
		else {
			Integer count = dao.countActiveUserRecords();
			if (count > 1){
				dao.toggleUser(userId);
			}
		}
		return true;
	}

	@Override
	public Boolean toggleUser(Long userId) throws Exception{
		dao.toggleUser(userId);
		return true;
	}

	@Override
	@Transactional
	public Boolean updateUser(User user) throws Exception{
		dao.updateUser(user);
		return true;
	}

}
