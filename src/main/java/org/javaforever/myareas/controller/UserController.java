package org.javaforever.myareas.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.javaforever.myareas.domain.User;
import org.javaforever.myareas.service.UserService;
import org.javaforever.myareas.utils.BooleanUtil;
import org.javaforever.myareas.utils.PDFUtil;
import org.javaforever.myareas.utils.POIExcelUtil;
import org.javaforever.myareas.utils.POIPPTUtil;
import org.javaforever.myareas.utils.StringUtil;
import org.javaforever.myareas.utils.WordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/userController")
@RestController
public class UserController {
	public User tempUser = new User();
	@Autowired
	protected UserService service;

	@RequestMapping(value = "/activateAllUsers", method = RequestMethod.POST)
	public Map<String,Object> activateAllUsers(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateAllUsers(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/activateUser", method = RequestMethod.POST)
	public Map<String,Object> activateUser(@RequestParam Long userId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateUser(userId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public Map<String,Object> addUser(@RequestBody User user) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.addUser(user);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneAllUsers", method = RequestMethod.POST)
	public Map<String,Object> cloneAllUsers(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneAllUsers(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneUser", method = RequestMethod.POST)
	public Map<String,Object> cloneUser(@RequestParam Long userId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneUser(userId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteAllUsers", method = RequestMethod.POST)
	public Map<String,Object> deleteAllUsers(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteAllUsers(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public Map<String,Object> deleteUser(@RequestParam Long userId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteUser(userId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/exportPPTUsers")
	public void exportPPTUsers(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Users.pptx");
			List<User> list = service.listAllUsers();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "用户信息表";
			String [] headers = {"用户序号","登录名","活跃","性别","手机","公共手机","QQ ","微信","微博","昵称","姓名","类型","密码","开始日期","等级","信用等级","评价","生日","地址","描述","费率等级","令牌","纬度","经度","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (User user:list) {
				Object [] row = {StringUtil.toNullString(user.getUserId()),user.getLoginName(),StringUtil.toNullString(user.getActive()),user.getGender(),user.getMobile(),user.getMobilePublic(),user.getQq(),user.getWeixin(),user.getWeibo(),user.getNickName(),user.getRealName(),StringUtil.toNullString(user.getUserType()),user.getUserPassword(),user.getStartDate(),StringUtil.toNullString(user.getUserLevel()),StringUtil.toNullString(user.getCreditLevel()),StringUtil.toNullString(user.getUserComment()),user.getBirthday(),user.getNormalAddress(),user.getUserDescription(),StringUtil.toNullString(user.getFeeLevel()),user.getTokenId(),StringUtil.toNullString(user.getLatitude()),StringUtil.toNullString(user.getLongitude()),StringUtil.toNullString(user.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportUsers")
	public void exportUsers(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Users.xls");
			List<User> list = service.listAllUsers();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "用户信息表";
			String [] headers = {"用户序号","登录名","活跃","性别","手机","公共手机","QQ ","微信","微博","昵称","姓名","类型","密码","开始日期","等级","信用等级","评价","生日","地址","描述","费率等级","令牌","纬度","经度","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (User user:list) {
				Object [] row = {StringUtil.toNullString(user.getUserId()),user.getLoginName(),StringUtil.toNullString(user.getActive()),user.getGender(),user.getMobile(),user.getMobilePublic(),user.getQq(),user.getWeixin(),user.getWeibo(),user.getNickName(),user.getRealName(),StringUtil.toNullString(user.getUserType()),user.getUserPassword(),user.getStartDate(),StringUtil.toNullString(user.getUserLevel()),StringUtil.toNullString(user.getCreditLevel()),StringUtil.toNullString(user.getUserComment()),user.getBirthday(),user.getNormalAddress(),user.getUserDescription(),StringUtil.toNullString(user.getFeeLevel()),user.getTokenId(),StringUtil.toNullString(user.getLatitude()),StringUtil.toNullString(user.getLongitude()),StringUtil.toNullString(user.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportUsersPDF")
	public void exportUsersPDF(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Users.pdf");
			List<User> list = service.listAllUsers();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "用户信息表";
			String [] headers = {"用户序号","登录名","活跃","性别","手机","公共手机","QQ ","微信","微博","昵称","姓名","类型","密码","开始日期","等级","信用等级","评价","生日","地址","描述","费率等级","令牌","纬度","经度","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (User user:list) {
				Object [] row = {StringUtil.toNullString(user.getUserId()),user.getLoginName(),StringUtil.toNullString(user.getActive()),user.getGender(),user.getMobile(),user.getMobilePublic(),user.getQq(),user.getWeixin(),user.getWeibo(),user.getNickName(),user.getRealName(),StringUtil.toNullString(user.getUserType()),user.getUserPassword(),user.getStartDate(),StringUtil.toNullString(user.getUserLevel()),StringUtil.toNullString(user.getCreditLevel()),StringUtil.toNullString(user.getUserComment()),user.getBirthday(),user.getNormalAddress(),user.getUserDescription(),StringUtil.toNullString(user.getFeeLevel()),user.getTokenId(),StringUtil.toNullString(user.getLatitude()),StringUtil.toNullString(user.getLongitude()),StringUtil.toNullString(user.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportUsersWord")
	public void exportUsersWord(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Users.doc");
			List<User> list = service.listAllUsers();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "用户信息表";
			String [] headers = {"用户序号","登录名","活跃","性别","手机","公共手机","QQ ","微信","微博","昵称","姓名","类型","密码","开始日期","等级","信用等级","评价","生日","地址","描述","费率等级","令牌","纬度","经度","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (User user:list) {
				Object [] row = {StringUtil.toNullString(user.getUserId()),user.getLoginName(),StringUtil.toNullString(user.getActive()),user.getGender(),user.getMobile(),user.getMobilePublic(),user.getQq(),user.getWeixin(),user.getWeibo(),user.getNickName(),user.getRealName(),StringUtil.toNullString(user.getUserType()),user.getUserPassword(),user.getStartDate(),StringUtil.toNullString(user.getUserLevel()),StringUtil.toNullString(user.getCreditLevel()),StringUtil.toNullString(user.getUserComment()),user.getBirthday(),user.getNormalAddress(),user.getUserDescription(),StringUtil.toNullString(user.getFeeLevel()),user.getTokenId(),StringUtil.toNullString(user.getLatitude()),StringUtil.toNullString(user.getLongitude()),StringUtil.toNullString(user.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterPPTUsers", method = RequestMethod.GET)
	public void filterPPTUsers(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "loginName", required = false) String loginName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "gender", required = false) String gender,
		@RequestParam(value = "mobile", required = false) String mobile,
		@RequestParam(value = "mobilePublic", required = false) String mobilePublic,
		@RequestParam(value = "qq", required = false) String qq,
		@RequestParam(value = "weixin", required = false) String weixin,
		@RequestParam(value = "weibo", required = false) String weibo,
		@RequestParam(value = "nickName", required = false) String nickName,
		@RequestParam(value = "realName", required = false) String realName,
		@RequestParam(value = "userType", required = false) Integer userType,
		@RequestParam(value = "userPassword", required = false) String userPassword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "userLevel", required = false) Integer userLevel,
		@RequestParam(value = "creditLevel", required = false) Integer creditLevel,
		@RequestParam(value = "userComment", required = false) Double userComment,
		@RequestParam(value = "birthday", required = false) String birthday,
		@RequestParam(value = "normalAddress", required = false) String normalAddress,
		@RequestParam(value = "userDescription", required = false) String userDescription,
		@RequestParam(value = "feeLevel", required = false) Integer feeLevel,
		@RequestParam(value = "tokenId", required = false) String tokenId,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		User user0 = new User();
		if (!StringUtil.isBlank(loginName)) user0.setLoginName(loginName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) user0.setActive(activeBool);
		if (!StringUtil.isBlank(gender)) user0.setGender(gender);
		if (!StringUtil.isBlank(mobile)) user0.setMobile(mobile);
		if (!StringUtil.isBlank(mobilePublic)) user0.setMobilePublic(mobilePublic);
		if (!StringUtil.isBlank(qq)) user0.setQq(qq);
		if (!StringUtil.isBlank(weixin)) user0.setWeixin(weixin);
		if (!StringUtil.isBlank(weibo)) user0.setWeibo(weibo);
		if (!StringUtil.isBlank(nickName)) user0.setNickName(nickName);
		if (!StringUtil.isBlank(realName)) user0.setRealName(realName);
		if (userType != null) user0.setUserType(userType);
		if (!StringUtil.isBlank(userPassword)) user0.setUserPassword(userPassword);
		if (!StringUtil.isBlank(startDate)) user0.setStartDate(startDate);
		if (userLevel != null) user0.setUserLevel(userLevel);
		if (creditLevel != null) user0.setCreditLevel(creditLevel);
		if (userComment != null) user0.setUserComment(userComment);
		if (!StringUtil.isBlank(birthday)) user0.setBirthday(birthday);
		if (!StringUtil.isBlank(normalAddress)) user0.setNormalAddress(normalAddress);
		if (!StringUtil.isBlank(userDescription)) user0.setUserDescription(userDescription);
		if (feeLevel != null) user0.setFeeLevel(feeLevel);
		if (!StringUtil.isBlank(tokenId)) user0.setTokenId(tokenId);
		if (latitude != null) user0.setLatitude(latitude);
		if (longitude != null) user0.setLongitude(longitude);
		if (cityId != null) user0.setCityId(cityId);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Users.pptx");
			List<User> list = service.searchUsersByFields(user0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "用户信息表";
			String [] headers = {"用户序号","登录名","活跃","性别","手机","公共手机","QQ ","微信","微博","昵称","姓名","类型","密码","开始日期","等级","信用等级","评价","生日","地址","描述","费率等级","令牌","纬度","经度","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (User user:list) {
				Object [] row = {StringUtil.toNullString(user.getUserId()),user.getLoginName(),StringUtil.toNullString(user.getActive()),user.getGender(),user.getMobile(),user.getMobilePublic(),user.getQq(),user.getWeixin(),user.getWeibo(),user.getNickName(),user.getRealName(),StringUtil.toNullString(user.getUserType()),user.getUserPassword(),user.getStartDate(),StringUtil.toNullString(user.getUserLevel()),StringUtil.toNullString(user.getCreditLevel()),StringUtil.toNullString(user.getUserComment()),user.getBirthday(),user.getNormalAddress(),user.getUserDescription(),StringUtil.toNullString(user.getFeeLevel()),user.getTokenId(),StringUtil.toNullString(user.getLatitude()),StringUtil.toNullString(user.getLongitude()),StringUtil.toNullString(user.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterUsersExcel", method = RequestMethod.GET)
	public void filterUsersExcel(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "loginName", required = false) String loginName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "gender", required = false) String gender,
		@RequestParam(value = "mobile", required = false) String mobile,
		@RequestParam(value = "mobilePublic", required = false) String mobilePublic,
		@RequestParam(value = "qq", required = false) String qq,
		@RequestParam(value = "weixin", required = false) String weixin,
		@RequestParam(value = "weibo", required = false) String weibo,
		@RequestParam(value = "nickName", required = false) String nickName,
		@RequestParam(value = "realName", required = false) String realName,
		@RequestParam(value = "userType", required = false) Integer userType,
		@RequestParam(value = "userPassword", required = false) String userPassword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "userLevel", required = false) Integer userLevel,
		@RequestParam(value = "creditLevel", required = false) Integer creditLevel,
		@RequestParam(value = "userComment", required = false) Double userComment,
		@RequestParam(value = "birthday", required = false) String birthday,
		@RequestParam(value = "normalAddress", required = false) String normalAddress,
		@RequestParam(value = "userDescription", required = false) String userDescription,
		@RequestParam(value = "feeLevel", required = false) Integer feeLevel,
		@RequestParam(value = "tokenId", required = false) String tokenId,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		User user0 = new User();
		if (!StringUtil.isBlank(loginName)) user0.setLoginName(loginName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) user0.setActive(activeBool);
		if (!StringUtil.isBlank(gender)) user0.setGender(gender);
		if (!StringUtil.isBlank(mobile)) user0.setMobile(mobile);
		if (!StringUtil.isBlank(mobilePublic)) user0.setMobilePublic(mobilePublic);
		if (!StringUtil.isBlank(qq)) user0.setQq(qq);
		if (!StringUtil.isBlank(weixin)) user0.setWeixin(weixin);
		if (!StringUtil.isBlank(weibo)) user0.setWeibo(weibo);
		if (!StringUtil.isBlank(nickName)) user0.setNickName(nickName);
		if (!StringUtil.isBlank(realName)) user0.setRealName(realName);
		if (userType != null) user0.setUserType(userType);
		if (!StringUtil.isBlank(userPassword)) user0.setUserPassword(userPassword);
		if (!StringUtil.isBlank(startDate)) user0.setStartDate(startDate);
		if (userLevel != null) user0.setUserLevel(userLevel);
		if (creditLevel != null) user0.setCreditLevel(creditLevel);
		if (userComment != null) user0.setUserComment(userComment);
		if (!StringUtil.isBlank(birthday)) user0.setBirthday(birthday);
		if (!StringUtil.isBlank(normalAddress)) user0.setNormalAddress(normalAddress);
		if (!StringUtil.isBlank(userDescription)) user0.setUserDescription(userDescription);
		if (feeLevel != null) user0.setFeeLevel(feeLevel);
		if (!StringUtil.isBlank(tokenId)) user0.setTokenId(tokenId);
		if (latitude != null) user0.setLatitude(latitude);
		if (longitude != null) user0.setLongitude(longitude);
		if (cityId != null) user0.setCityId(cityId);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Users.xls");
			List<User> list = service.searchUsersByFields(user0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "用户信息表";
			String [] headers = {"用户序号","登录名","活跃","性别","手机","公共手机","QQ ","微信","微博","昵称","姓名","类型","密码","开始日期","等级","信用等级","评价","生日","地址","描述","费率等级","令牌","纬度","经度","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (User user:list) {
				Object [] row = {StringUtil.toNullString(user.getUserId()),user.getLoginName(),StringUtil.toNullString(user.getActive()),user.getGender(),user.getMobile(),user.getMobilePublic(),user.getQq(),user.getWeixin(),user.getWeibo(),user.getNickName(),user.getRealName(),StringUtil.toNullString(user.getUserType()),user.getUserPassword(),user.getStartDate(),StringUtil.toNullString(user.getUserLevel()),StringUtil.toNullString(user.getCreditLevel()),StringUtil.toNullString(user.getUserComment()),user.getBirthday(),user.getNormalAddress(),user.getUserDescription(),StringUtil.toNullString(user.getFeeLevel()),user.getTokenId(),StringUtil.toNullString(user.getLatitude()),StringUtil.toNullString(user.getLongitude()),StringUtil.toNullString(user.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterUsersPDF", method = RequestMethod.GET)
	public void filterUsersPDF(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "loginName", required = false) String loginName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "gender", required = false) String gender,
		@RequestParam(value = "mobile", required = false) String mobile,
		@RequestParam(value = "mobilePublic", required = false) String mobilePublic,
		@RequestParam(value = "qq", required = false) String qq,
		@RequestParam(value = "weixin", required = false) String weixin,
		@RequestParam(value = "weibo", required = false) String weibo,
		@RequestParam(value = "nickName", required = false) String nickName,
		@RequestParam(value = "realName", required = false) String realName,
		@RequestParam(value = "userType", required = false) Integer userType,
		@RequestParam(value = "userPassword", required = false) String userPassword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "userLevel", required = false) Integer userLevel,
		@RequestParam(value = "creditLevel", required = false) Integer creditLevel,
		@RequestParam(value = "userComment", required = false) Double userComment,
		@RequestParam(value = "birthday", required = false) String birthday,
		@RequestParam(value = "normalAddress", required = false) String normalAddress,
		@RequestParam(value = "userDescription", required = false) String userDescription,
		@RequestParam(value = "feeLevel", required = false) Integer feeLevel,
		@RequestParam(value = "tokenId", required = false) String tokenId,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		User user0 = new User();
		if (!StringUtil.isBlank(loginName)) user0.setLoginName(loginName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) user0.setActive(activeBool);
		if (!StringUtil.isBlank(gender)) user0.setGender(gender);
		if (!StringUtil.isBlank(mobile)) user0.setMobile(mobile);
		if (!StringUtil.isBlank(mobilePublic)) user0.setMobilePublic(mobilePublic);
		if (!StringUtil.isBlank(qq)) user0.setQq(qq);
		if (!StringUtil.isBlank(weixin)) user0.setWeixin(weixin);
		if (!StringUtil.isBlank(weibo)) user0.setWeibo(weibo);
		if (!StringUtil.isBlank(nickName)) user0.setNickName(nickName);
		if (!StringUtil.isBlank(realName)) user0.setRealName(realName);
		if (userType != null) user0.setUserType(userType);
		if (!StringUtil.isBlank(userPassword)) user0.setUserPassword(userPassword);
		if (!StringUtil.isBlank(startDate)) user0.setStartDate(startDate);
		if (userLevel != null) user0.setUserLevel(userLevel);
		if (creditLevel != null) user0.setCreditLevel(creditLevel);
		if (userComment != null) user0.setUserComment(userComment);
		if (!StringUtil.isBlank(birthday)) user0.setBirthday(birthday);
		if (!StringUtil.isBlank(normalAddress)) user0.setNormalAddress(normalAddress);
		if (!StringUtil.isBlank(userDescription)) user0.setUserDescription(userDescription);
		if (feeLevel != null) user0.setFeeLevel(feeLevel);
		if (!StringUtil.isBlank(tokenId)) user0.setTokenId(tokenId);
		if (latitude != null) user0.setLatitude(latitude);
		if (longitude != null) user0.setLongitude(longitude);
		if (cityId != null) user0.setCityId(cityId);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Users.pdf");
			List<User> list = service.searchUsersByFields(user0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "用户信息表";
			String [] headers = {"用户序号","登录名","活跃","性别","手机","公共手机","QQ ","微信","微博","昵称","姓名","类型","密码","开始日期","等级","信用等级","评价","生日","地址","描述","费率等级","令牌","纬度","经度","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (User user:list) {
				Object [] row = {StringUtil.toNullString(user.getUserId()),user.getLoginName(),StringUtil.toNullString(user.getActive()),user.getGender(),user.getMobile(),user.getMobilePublic(),user.getQq(),user.getWeixin(),user.getWeibo(),user.getNickName(),user.getRealName(),StringUtil.toNullString(user.getUserType()),user.getUserPassword(),user.getStartDate(),StringUtil.toNullString(user.getUserLevel()),StringUtil.toNullString(user.getCreditLevel()),StringUtil.toNullString(user.getUserComment()),user.getBirthday(),user.getNormalAddress(),user.getUserDescription(),StringUtil.toNullString(user.getFeeLevel()),user.getTokenId(),StringUtil.toNullString(user.getLatitude()),StringUtil.toNullString(user.getLongitude()),StringUtil.toNullString(user.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterUsersWord", method = RequestMethod.GET)
	public void filterUsersWord(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "loginName", required = false) String loginName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "gender", required = false) String gender,
		@RequestParam(value = "mobile", required = false) String mobile,
		@RequestParam(value = "mobilePublic", required = false) String mobilePublic,
		@RequestParam(value = "qq", required = false) String qq,
		@RequestParam(value = "weixin", required = false) String weixin,
		@RequestParam(value = "weibo", required = false) String weibo,
		@RequestParam(value = "nickName", required = false) String nickName,
		@RequestParam(value = "realName", required = false) String realName,
		@RequestParam(value = "userType", required = false) Integer userType,
		@RequestParam(value = "userPassword", required = false) String userPassword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "userLevel", required = false) Integer userLevel,
		@RequestParam(value = "creditLevel", required = false) Integer creditLevel,
		@RequestParam(value = "userComment", required = false) Double userComment,
		@RequestParam(value = "birthday", required = false) String birthday,
		@RequestParam(value = "normalAddress", required = false) String normalAddress,
		@RequestParam(value = "userDescription", required = false) String userDescription,
		@RequestParam(value = "feeLevel", required = false) Integer feeLevel,
		@RequestParam(value = "tokenId", required = false) String tokenId,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		User user0 = new User();
		if (!StringUtil.isBlank(loginName)) user0.setLoginName(loginName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) user0.setActive(activeBool);
		if (!StringUtil.isBlank(gender)) user0.setGender(gender);
		if (!StringUtil.isBlank(mobile)) user0.setMobile(mobile);
		if (!StringUtil.isBlank(mobilePublic)) user0.setMobilePublic(mobilePublic);
		if (!StringUtil.isBlank(qq)) user0.setQq(qq);
		if (!StringUtil.isBlank(weixin)) user0.setWeixin(weixin);
		if (!StringUtil.isBlank(weibo)) user0.setWeibo(weibo);
		if (!StringUtil.isBlank(nickName)) user0.setNickName(nickName);
		if (!StringUtil.isBlank(realName)) user0.setRealName(realName);
		if (userType != null) user0.setUserType(userType);
		if (!StringUtil.isBlank(userPassword)) user0.setUserPassword(userPassword);
		if (!StringUtil.isBlank(startDate)) user0.setStartDate(startDate);
		if (userLevel != null) user0.setUserLevel(userLevel);
		if (creditLevel != null) user0.setCreditLevel(creditLevel);
		if (userComment != null) user0.setUserComment(userComment);
		if (!StringUtil.isBlank(birthday)) user0.setBirthday(birthday);
		if (!StringUtil.isBlank(normalAddress)) user0.setNormalAddress(normalAddress);
		if (!StringUtil.isBlank(userDescription)) user0.setUserDescription(userDescription);
		if (feeLevel != null) user0.setFeeLevel(feeLevel);
		if (!StringUtil.isBlank(tokenId)) user0.setTokenId(tokenId);
		if (latitude != null) user0.setLatitude(latitude);
		if (longitude != null) user0.setLongitude(longitude);
		if (cityId != null) user0.setCityId(cityId);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Users.doc");
			List<User> list = service.searchUsersByFields(user0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "用户信息表";
			String [] headers = {"用户序号","登录名","活跃","性别","手机","公共手机","QQ ","微信","微博","昵称","姓名","类型","密码","开始日期","等级","信用等级","评价","生日","地址","描述","费率等级","令牌","纬度","经度","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (User user:list) {
				Object [] row = {StringUtil.toNullString(user.getUserId()),user.getLoginName(),StringUtil.toNullString(user.getActive()),user.getGender(),user.getMobile(),user.getMobilePublic(),user.getQq(),user.getWeixin(),user.getWeibo(),user.getNickName(),user.getRealName(),StringUtil.toNullString(user.getUserType()),user.getUserPassword(),user.getStartDate(),StringUtil.toNullString(user.getUserLevel()),StringUtil.toNullString(user.getCreditLevel()),StringUtil.toNullString(user.getUserComment()),user.getBirthday(),user.getNormalAddress(),user.getUserDescription(),StringUtil.toNullString(user.getFeeLevel()),user.getTokenId(),StringUtil.toNullString(user.getLatitude()),StringUtil.toNullString(user.getLongitude()),StringUtil.toNullString(user.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/findUserByLoginName", method = RequestMethod.POST)
	public Map<String,Object> findUserByLoginName(@RequestParam(value = "loginName", required = true) String loginName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		User user = service.findUserByLoginName(loginName);
		result.put("success",true);
		result.put("data",user);
		return result;
	}
	@RequestMapping(value = "/findUserByUserId", method = RequestMethod.POST)
	public Map<String,Object> findUserByUserId(@RequestParam Long userId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		User user = service.findUserByUserId(userId);
		result.put("success",true);
		result.put("data",user);
		return result;
	}
	@RequestMapping(value = "/listActiveUsers", method = RequestMethod.POST)
	public Map<String,Object> listActiveUsers() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<User> list = service.listActiveUsers();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/listAllUsers", method = RequestMethod.POST)
	public Map<String,Object> listAllUsers() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<User> list = service.listAllUsers();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/searchUsersByFieldsByPage", method = RequestMethod.POST)
	public Map<String,Object> searchUsersByFieldsByPage(@RequestParam(value = "page", required = false) Integer pagenum,
		@RequestParam(value = "rows", required = false) Integer pagesize,
		@RequestParam(value = "last", required = false) String lastFlag,
		@RequestParam(value = "loginName", required = false) String loginName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "gender", required = false) String gender,
		@RequestParam(value = "mobile", required = false) String mobile,
		@RequestParam(value = "mobilePublic", required = false) String mobilePublic,
		@RequestParam(value = "qq", required = false) String qq,
		@RequestParam(value = "weixin", required = false) String weixin,
		@RequestParam(value = "weibo", required = false) String weibo,
		@RequestParam(value = "nickName", required = false) String nickName,
		@RequestParam(value = "realName", required = false) String realName,
		@RequestParam(value = "userType", required = false) Integer userType,
		@RequestParam(value = "userPassword", required = false) String userPassword,
		@RequestParam(value = "startDate", required = false) String startDate,
		@RequestParam(value = "userLevel", required = false) Integer userLevel,
		@RequestParam(value = "creditLevel", required = false) Integer creditLevel,
		@RequestParam(value = "userComment", required = false) Double userComment,
		@RequestParam(value = "birthday", required = false) String birthday,
		@RequestParam(value = "normalAddress", required = false) String normalAddress,
		@RequestParam(value = "userDescription", required = false) String userDescription,
		@RequestParam(value = "feeLevel", required = false) Integer feeLevel,
		@RequestParam(value = "tokenId", required = false) String tokenId,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		User user = new User();
		if (loginName != null) user.setLoginName(loginName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) user.setActive(activeBool);
		if (gender != null) user.setGender(gender);
		if (mobile != null) user.setMobile(mobile);
		if (mobilePublic != null) user.setMobilePublic(mobilePublic);
		if (qq != null) user.setQq(qq);
		if (weixin != null) user.setWeixin(weixin);
		if (weibo != null) user.setWeibo(weibo);
		if (nickName != null) user.setNickName(nickName);
		if (realName != null) user.setRealName(realName);
		if (userType != null) user.setUserType(userType);
		if (userPassword != null) user.setUserPassword(userPassword);
		if (startDate != null) user.setStartDate(startDate);
		if (userLevel != null) user.setUserLevel(userLevel);
		if (creditLevel != null) user.setCreditLevel(creditLevel);
		if (userComment != null) user.setUserComment(userComment);
		if (birthday != null) user.setBirthday(birthday);
		if (normalAddress != null) user.setNormalAddress(normalAddress);
		if (userDescription != null) user.setUserDescription(userDescription);
		if (feeLevel != null) user.setFeeLevel(feeLevel);
		if (tokenId != null) user.setTokenId(tokenId);
		if (latitude != null) user.setLatitude(latitude);
		if (longitude != null) user.setLongitude(longitude);
		if (cityId != null) user.setCityId(cityId);
		if (pagesize==null || pagesize <= 0) pagesize = 10;
		Integer recordCount = service.countSearchUsersByFieldsRecords(user);
		Integer pageCount = (int)Math.ceil((double)recordCount/pagesize);
		if (pageCount <= 1) pageCount = 1;
		if (pagenum==null || pagenum <= 1) pagenum = 1;
		if (pagenum >= pageCount) pagenum = pageCount;
		Boolean lastFlagBool = BooleanUtil.parseBoolean(lastFlag);
		if (lastFlagBool == null) lastFlagBool = false;
		if (lastFlagBool) pagenum = pageCount;
		Map<String,Object> result = new TreeMap<String,Object>();
		List<User> userList = service.searchUsersByFieldsByPage(user,pagesize,pagenum);
		result.put("success",true);
		result.put("rows",userList);
		result.put("total",recordCount);
		result.put("page",pagenum);
		return result;
	}
	@RequestMapping(value = "/searchUsersByLoginName", method = RequestMethod.POST)
	public Map<String,Object> searchUsersByLoginName(@RequestParam(value = "loginName", required = true) String loginName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<User> userList = service.searchUsersByLoginName(loginName);
		result.put("success",true);
		result.put("rows",userList);
		return result;
	}
	@RequestMapping(value = "/softDeleteAllUsers", method = RequestMethod.POST)
	public Map<String,Object> softDeleteAllUsers(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteAllUsers(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/softDeleteUser", method = RequestMethod.POST)
	public Map<String,Object> softDeleteUser(@RequestParam Long userId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteUser(userId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleOneUser", method = RequestMethod.POST)
	public Map<String,Object> toggleOneUser(@RequestParam Long userId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleOneUser(userId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleUser", method = RequestMethod.POST)
	public Map<String,Object> toggleUser(@RequestParam Long userId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleUser(userId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public Map<String,Object> updateUser(@RequestBody User user) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.updateUser(user);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
}

