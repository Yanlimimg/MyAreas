package org.javaforever.myareas.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.javaforever.myareas.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao{
	public List<User> listAllUsers() throws Exception;
	public void updateUser(User user) throws Exception;
	public void deleteUser(Long userId) throws Exception;
	public void addUser(User user) throws Exception;
	public void softDeleteUser(Long userId) throws Exception;
	public User findUserByUserId(Long userId) throws Exception;
	public User findUserByLoginName(String loginName) throws Exception;
	public List<User> searchUsersByLoginName(String loginName) throws Exception;
	public List<User> listActiveUsers() throws Exception;
	public void toggleUser(Long userId) throws Exception;
	public List<User> searchUsersByFieldsByLimit(@Param(value="user") User user,@Param(value="start") Integer start,@Param(value="limit") Integer limit) throws Exception;
	public void activateUser(Long userId) throws Exception;
	public List<User> searchUsersByFields(User user) throws Exception;
	public User findUserFullInfoByUserId(Long userId) throws Exception;
	public List<User> searchUserIndexedNamesByShortName(String shortName) throws Exception;
	public Integer countSearchUsersByFieldsRecords(User user) throws Exception;
	public Integer countActiveUserRecords() throws Exception;
}
