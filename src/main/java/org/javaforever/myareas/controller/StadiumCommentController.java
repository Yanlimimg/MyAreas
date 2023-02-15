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
import org.javaforever.myareas.domain.Stadium;
import org.javaforever.myareas.domain.StadiumComment;
import org.javaforever.myareas.domain.User;
import org.javaforever.myareas.service.StadiumCommentService;
import org.javaforever.myareas.service.StadiumService;
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

@RequestMapping(value = "/stadiumCommentController")
@RestController
public class StadiumCommentController {
	public StadiumComment tempStadiumComment = new StadiumComment();
	@Autowired
	protected StadiumService stadiumService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected StadiumCommentService service;

	@RequestMapping(value = "/activateAllStadiumComments", method = RequestMethod.POST)
	public Map<String,Object> activateAllStadiumComments(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateAllStadiumComments(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/activateStadiumComment", method = RequestMethod.POST)
	public Map<String,Object> activateStadiumComment(@RequestParam Long stadiumCommentId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateStadiumComment(stadiumCommentId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/addStadiumComment", method = RequestMethod.POST)
	public Map<String,Object> addStadiumComment(@RequestBody StadiumComment stadiumComment) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.addStadiumComment(stadiumComment);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneAllStadiumComments", method = RequestMethod.POST)
	public Map<String,Object> cloneAllStadiumComments(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneAllStadiumComments(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneStadiumComment", method = RequestMethod.POST)
	public Map<String,Object> cloneStadiumComment(@RequestParam Long stadiumCommentId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneStadiumComment(stadiumCommentId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteAllStadiumComments", method = RequestMethod.POST)
	public Map<String,Object> deleteAllStadiumComments(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteAllStadiumComments(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteStadiumComment", method = RequestMethod.POST)
	public Map<String,Object> deleteStadiumComment(@RequestParam Long stadiumCommentId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteStadiumComment(stadiumCommentId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/exportPPTStadiumComments")
	public void exportPPTStadiumComments(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumComments.pptx");
			List<StadiumComment> list = service.listAllStadiumComments();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆评论信息表";
			String [] headers = {"场馆评论序号","场馆评论名称","活跃","场馆","用户","创建时间","星标","评论"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false};
			
			for (StadiumComment stadiumComment:list) {
				Long stadiumId = stadiumComment.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Long userId = stadiumComment.getUserId();
				User userIdType0;
				String userIdName0 = "";
				if (userId != null) {
					userIdType0 = userService.findUserByUserId(userId);
					if (userIdType0 !=null) userIdName0 = userIdType0.getLoginName();
				}
				Object [] row = {StringUtil.toNullString(stadiumComment.getStadiumCommentId()),stadiumComment.getStadiumCommentName(),StringUtil.toNullString(stadiumComment.getActive()),stadiumIdName0,userIdName0,stadiumComment.getCreateTime(),StringUtil.toNullString(stadiumComment.getStar()),stadiumComment.getComments()};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumComments")
	public void exportStadiumComments(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumComments.xls");
			List<StadiumComment> list = service.listAllStadiumComments();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆评论信息表";
			String [] headers = {"场馆评论序号","场馆评论名称","活跃","场馆","用户","创建时间","星标","评论"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false};
			
			for (StadiumComment stadiumComment:list) {
				Long stadiumId = stadiumComment.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Long userId = stadiumComment.getUserId();
				User userIdType0;
				String userIdName0 = "";
				if (userId != null) {
					userIdType0 = userService.findUserByUserId(userId);
					if (userIdType0 !=null) userIdName0 = userIdType0.getLoginName();
				}
				Object [] row = {StringUtil.toNullString(stadiumComment.getStadiumCommentId()),stadiumComment.getStadiumCommentName(),StringUtil.toNullString(stadiumComment.getActive()),stadiumIdName0,userIdName0,stadiumComment.getCreateTime(),StringUtil.toNullString(stadiumComment.getStar()),stadiumComment.getComments()};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumCommentsPDF")
	public void exportStadiumCommentsPDF(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumComments.pdf");
			List<StadiumComment> list = service.listAllStadiumComments();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆评论信息表";
			String [] headers = {"场馆评论序号","场馆评论名称","活跃","场馆","用户","创建时间","星标","评论"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false};
			
			for (StadiumComment stadiumComment:list) {
				Long stadiumId = stadiumComment.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Long userId = stadiumComment.getUserId();
				User userIdType0;
				String userIdName0 = "";
				if (userId != null) {
					userIdType0 = userService.findUserByUserId(userId);
					if (userIdType0 !=null) userIdName0 = userIdType0.getLoginName();
				}
				Object [] row = {StringUtil.toNullString(stadiumComment.getStadiumCommentId()),stadiumComment.getStadiumCommentName(),StringUtil.toNullString(stadiumComment.getActive()),stadiumIdName0,userIdName0,stadiumComment.getCreateTime(),StringUtil.toNullString(stadiumComment.getStar()),stadiumComment.getComments()};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumCommentsWord")
	public void exportStadiumCommentsWord(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumComments.doc");
			List<StadiumComment> list = service.listAllStadiumComments();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆评论信息表";
			String [] headers = {"场馆评论序号","场馆评论名称","活跃","场馆","用户","创建时间","星标","评论"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false};
			
			for (StadiumComment stadiumComment:list) {
				Long stadiumId = stadiumComment.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Long userId = stadiumComment.getUserId();
				User userIdType0;
				String userIdName0 = "";
				if (userId != null) {
					userIdType0 = userService.findUserByUserId(userId);
					if (userIdType0 !=null) userIdName0 = userIdType0.getLoginName();
				}
				Object [] row = {StringUtil.toNullString(stadiumComment.getStadiumCommentId()),stadiumComment.getStadiumCommentName(),StringUtil.toNullString(stadiumComment.getActive()),stadiumIdName0,userIdName0,stadiumComment.getCreateTime(),StringUtil.toNullString(stadiumComment.getStar()),stadiumComment.getComments()};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterPPTStadiumComments", method = RequestMethod.GET)
	public void filterPPTStadiumComments(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumCommentName", required = false) String stadiumCommentName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "userId", required = false) Long userId,
		@RequestParam(value = "createTime", required = false) String createTime,
		@RequestParam(value = "star", required = false) Integer star,
		@RequestParam(value = "comments", required = false) String comments) throws Exception{
		StadiumComment stadiumComment0 = new StadiumComment();
		if (!StringUtil.isBlank(stadiumCommentName)) stadiumComment0.setStadiumCommentName(stadiumCommentName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumComment0.setActive(activeBool);
		if (stadiumId != null) stadiumComment0.setStadiumId(stadiumId);
		if (userId != null) stadiumComment0.setUserId(userId);
		if (!StringUtil.isBlank(createTime)) stadiumComment0.setCreateTime(createTime);
		if (star != null) stadiumComment0.setStar(star);
		if (!StringUtil.isBlank(comments)) stadiumComment0.setComments(comments);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumComments.pptx");
			List<StadiumComment> list = service.searchStadiumCommentsByFields(stadiumComment0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆评论信息表";
			String [] headers = {"场馆评论序号","场馆评论名称","活跃","场馆","用户","创建时间","星标","评论"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false};
			
			for (StadiumComment stadiumComment:list) {
				Long stadiumId0 = stadiumComment.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Long userId0 = stadiumComment.getUserId();
				User userIdType0;
				String userIdName0 = "";
				if (userId0 != null) {
					userIdType0 = userService.findUserByUserId(userId0);
					if (userIdType0 !=null) userIdName0 = userIdType0.getLoginName();
				}
				Object [] row = {StringUtil.toNullString(stadiumComment.getStadiumCommentId()),stadiumComment.getStadiumCommentName(),StringUtil.toNullString(stadiumComment.getActive()),stadiumIdName0,userIdName0,stadiumComment.getCreateTime(),StringUtil.toNullString(stadiumComment.getStar()),stadiumComment.getComments()};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumCommentsExcel", method = RequestMethod.GET)
	public void filterStadiumCommentsExcel(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumCommentName", required = false) String stadiumCommentName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "userId", required = false) Long userId,
		@RequestParam(value = "createTime", required = false) String createTime,
		@RequestParam(value = "star", required = false) Integer star,
		@RequestParam(value = "comments", required = false) String comments) throws Exception{
		StadiumComment stadiumComment0 = new StadiumComment();
		if (!StringUtil.isBlank(stadiumCommentName)) stadiumComment0.setStadiumCommentName(stadiumCommentName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumComment0.setActive(activeBool);
		if (stadiumId != null) stadiumComment0.setStadiumId(stadiumId);
		if (userId != null) stadiumComment0.setUserId(userId);
		if (!StringUtil.isBlank(createTime)) stadiumComment0.setCreateTime(createTime);
		if (star != null) stadiumComment0.setStar(star);
		if (!StringUtil.isBlank(comments)) stadiumComment0.setComments(comments);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumComments.xls");
			List<StadiumComment> list = service.searchStadiumCommentsByFields(stadiumComment0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆评论信息表";
			String [] headers = {"场馆评论序号","场馆评论名称","活跃","场馆","用户","创建时间","星标","评论"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false};
			
			for (StadiumComment stadiumComment:list) {
				Long stadiumId0 = stadiumComment.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Long userId0 = stadiumComment.getUserId();
				User userIdType0;
				String userIdName0 = "";
				if (userId0 != null) {
					userIdType0 = userService.findUserByUserId(userId0);
					if (userIdType0 !=null) userIdName0 = userIdType0.getLoginName();
				}
				Object [] row = {StringUtil.toNullString(stadiumComment.getStadiumCommentId()),stadiumComment.getStadiumCommentName(),StringUtil.toNullString(stadiumComment.getActive()),stadiumIdName0,userIdName0,stadiumComment.getCreateTime(),StringUtil.toNullString(stadiumComment.getStar()),stadiumComment.getComments()};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumCommentsPDF", method = RequestMethod.GET)
	public void filterStadiumCommentsPDF(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumCommentName", required = false) String stadiumCommentName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "userId", required = false) Long userId,
		@RequestParam(value = "createTime", required = false) String createTime,
		@RequestParam(value = "star", required = false) Integer star,
		@RequestParam(value = "comments", required = false) String comments) throws Exception{
		StadiumComment stadiumComment0 = new StadiumComment();
		if (!StringUtil.isBlank(stadiumCommentName)) stadiumComment0.setStadiumCommentName(stadiumCommentName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumComment0.setActive(activeBool);
		if (stadiumId != null) stadiumComment0.setStadiumId(stadiumId);
		if (userId != null) stadiumComment0.setUserId(userId);
		if (!StringUtil.isBlank(createTime)) stadiumComment0.setCreateTime(createTime);
		if (star != null) stadiumComment0.setStar(star);
		if (!StringUtil.isBlank(comments)) stadiumComment0.setComments(comments);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumComments.pdf");
			List<StadiumComment> list = service.searchStadiumCommentsByFields(stadiumComment0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆评论信息表";
			String [] headers = {"场馆评论序号","场馆评论名称","活跃","场馆","用户","创建时间","星标","评论"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false};
			
			for (StadiumComment stadiumComment:list) {
				Long stadiumId0 = stadiumComment.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Long userId0 = stadiumComment.getUserId();
				User userIdType0;
				String userIdName0 = "";
				if (userId0 != null) {
					userIdType0 = userService.findUserByUserId(userId0);
					if (userIdType0 !=null) userIdName0 = userIdType0.getLoginName();
				}
				Object [] row = {StringUtil.toNullString(stadiumComment.getStadiumCommentId()),stadiumComment.getStadiumCommentName(),StringUtil.toNullString(stadiumComment.getActive()),stadiumIdName0,userIdName0,stadiumComment.getCreateTime(),StringUtil.toNullString(stadiumComment.getStar()),stadiumComment.getComments()};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumCommentsWord", method = RequestMethod.GET)
	public void filterStadiumCommentsWord(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumCommentName", required = false) String stadiumCommentName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "userId", required = false) Long userId,
		@RequestParam(value = "createTime", required = false) String createTime,
		@RequestParam(value = "star", required = false) Integer star,
		@RequestParam(value = "comments", required = false) String comments) throws Exception{
		StadiumComment stadiumComment0 = new StadiumComment();
		if (!StringUtil.isBlank(stadiumCommentName)) stadiumComment0.setStadiumCommentName(stadiumCommentName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumComment0.setActive(activeBool);
		if (stadiumId != null) stadiumComment0.setStadiumId(stadiumId);
		if (userId != null) stadiumComment0.setUserId(userId);
		if (!StringUtil.isBlank(createTime)) stadiumComment0.setCreateTime(createTime);
		if (star != null) stadiumComment0.setStar(star);
		if (!StringUtil.isBlank(comments)) stadiumComment0.setComments(comments);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumComments.doc");
			List<StadiumComment> list = service.searchStadiumCommentsByFields(stadiumComment0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆评论信息表";
			String [] headers = {"场馆评论序号","场馆评论名称","活跃","场馆","用户","创建时间","星标","评论"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false};
			
			for (StadiumComment stadiumComment:list) {
				Long stadiumId0 = stadiumComment.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Long userId0 = stadiumComment.getUserId();
				User userIdType0;
				String userIdName0 = "";
				if (userId0 != null) {
					userIdType0 = userService.findUserByUserId(userId0);
					if (userIdType0 !=null) userIdName0 = userIdType0.getLoginName();
				}
				Object [] row = {StringUtil.toNullString(stadiumComment.getStadiumCommentId()),stadiumComment.getStadiumCommentName(),StringUtil.toNullString(stadiumComment.getActive()),stadiumIdName0,userIdName0,stadiumComment.getCreateTime(),StringUtil.toNullString(stadiumComment.getStar()),stadiumComment.getComments()};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/findStadiumCommentByStadiumCommentId", method = RequestMethod.POST)
	public Map<String,Object> findStadiumCommentByStadiumCommentId(@RequestParam Long stadiumCommentId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		StadiumComment stadiumComment = service.findStadiumCommentByStadiumCommentId(stadiumCommentId);
		result.put("success",true);
		result.put("data",stadiumComment);
		return result;
	}
	@RequestMapping(value = "/findStadiumCommentByStadiumCommentName", method = RequestMethod.POST)
	public Map<String,Object> findStadiumCommentByStadiumCommentName(@RequestParam(value = "stadiumCommentName", required = true) String stadiumCommentName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		StadiumComment stadiumComment = service.findStadiumCommentByStadiumCommentName(stadiumCommentName);
		result.put("success",true);
		result.put("data",stadiumComment);
		return result;
	}
	@RequestMapping(value = "/listActiveStadiumComments", method = RequestMethod.POST)
	public Map<String,Object> listActiveStadiumComments() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumComment> list = service.listActiveStadiumComments();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/listAllStadiumComments", method = RequestMethod.POST)
	public Map<String,Object> listAllStadiumComments() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumComment> list = service.listAllStadiumComments();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/searchStadiumCommentsByFieldsByPage", method = RequestMethod.POST)
	public Map<String,Object> searchStadiumCommentsByFieldsByPage(@RequestParam(value = "page", required = false) Integer pagenum,
		@RequestParam(value = "rows", required = false) Integer pagesize,
		@RequestParam(value = "last", required = false) String lastFlag,
		@RequestParam(value = "stadiumCommentName", required = false) String stadiumCommentName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "userId", required = false) Long userId,
		@RequestParam(value = "createTime", required = false) String createTime,
		@RequestParam(value = "star", required = false) Integer star,
		@RequestParam(value = "comments", required = false) String comments) throws Exception{
		StadiumComment stadiumComment = new StadiumComment();
		if (stadiumCommentName != null) stadiumComment.setStadiumCommentName(stadiumCommentName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumComment.setActive(activeBool);
		if (stadiumId != null) stadiumComment.setStadiumId(stadiumId);
		if (userId != null) stadiumComment.setUserId(userId);
		if (createTime != null) stadiumComment.setCreateTime(createTime);
		if (star != null) stadiumComment.setStar(star);
		if (comments != null) stadiumComment.setComments(comments);
		if (pagesize==null || pagesize <= 0) pagesize = 10;
		Integer recordCount = service.countSearchStadiumCommentsByFieldsRecords(stadiumComment);
		Integer pageCount = (int)Math.ceil((double)recordCount/pagesize);
		if (pageCount <= 1) pageCount = 1;
		if (pagenum==null || pagenum <= 1) pagenum = 1;
		if (pagenum >= pageCount) pagenum = pageCount;
		Boolean lastFlagBool = BooleanUtil.parseBoolean(lastFlag);
		if (lastFlagBool == null) lastFlagBool = false;
		if (lastFlagBool) pagenum = pageCount;
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumComment> stadiumCommentList = service.searchStadiumCommentsByFieldsByPage(stadiumComment,pagesize,pagenum);
		result.put("success",true);
		result.put("rows",stadiumCommentList);
		result.put("total",recordCount);
		result.put("page",pagenum);
		return result;
	}
	@RequestMapping(value = "/searchStadiumCommentsByStadiumCommentName", method = RequestMethod.POST)
	public Map<String,Object> searchStadiumCommentsByStadiumCommentName(@RequestParam(value = "stadiumCommentName", required = true) String stadiumCommentName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumComment> stadiumCommentList = service.searchStadiumCommentsByStadiumCommentName(stadiumCommentName);
		result.put("success",true);
		result.put("rows",stadiumCommentList);
		return result;
	}
	@RequestMapping(value = "/softDeleteAllStadiumComments", method = RequestMethod.POST)
	public Map<String,Object> softDeleteAllStadiumComments(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteAllStadiumComments(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/softDeleteStadiumComment", method = RequestMethod.POST)
	public Map<String,Object> softDeleteStadiumComment(@RequestParam Long stadiumCommentId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteStadiumComment(stadiumCommentId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleOneStadiumComment", method = RequestMethod.POST)
	public Map<String,Object> toggleOneStadiumComment(@RequestParam Long stadiumCommentId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleOneStadiumComment(stadiumCommentId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleStadiumComment", method = RequestMethod.POST)
	public Map<String,Object> toggleStadiumComment(@RequestParam Long stadiumCommentId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleStadiumComment(stadiumCommentId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/updateStadiumComment", method = RequestMethod.POST)
	public Map<String,Object> updateStadiumComment(@RequestBody StadiumComment stadiumComment) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.updateStadiumComment(stadiumComment);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
}

