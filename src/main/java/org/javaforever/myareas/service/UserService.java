package org.javaforever.myareas.service;

import java.util.List;
import org.javaforever.myareas.domain.User;

public interface UserService{
	public List<User> listAllUsers() throws Exception;
	public Boolean updateUser(User user) throws Exception;
	public Boolean deleteUser(Long userId) throws Exception;
	public Boolean addUser(User user) throws Exception;
	public boolean softDeleteUser(Long userId) throws Exception;
	public User findUserByUserId(Long userId) throws Exception;
	public User findUserByLoginName(String loginName) throws Exception;
	public List<User> searchUsersByLoginName(String loginName) throws Exception;
	public List<User> listActiveUsers() throws Exception;
	public void deleteAllUsers(String ids) throws Exception;
	public void softDeleteAllUsers(String ids) throws Exception;
	public Boolean toggleUser(Long userId) throws Exception;
	public Boolean toggleOneUser(Long userId) throws Exception;
	public List<User> searchUsersByFieldsByPage(User user,Integer pagesize,Integer pagenum) throws Exception;
	public Boolean activateUser(Long userId) throws Exception;
	public void activateAllUsers(String ids) throws Exception;
	public List<User> searchUsersByFields(User user) throws Exception;
	public Boolean cloneUser(Long userId) throws Exception;
	public void cloneAllUsers(String ids) throws Exception;
	public Integer countSearchUsersByFieldsRecords(User user) throws Exception;
	public Integer countActiveUserRecords() throws Exception;
}
