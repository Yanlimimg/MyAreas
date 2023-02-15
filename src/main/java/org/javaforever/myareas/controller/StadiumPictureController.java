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
import org.javaforever.myareas.domain.StadiumPicture;
import org.javaforever.myareas.service.StadiumPictureService;
import org.javaforever.myareas.service.StadiumService;
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

@RequestMapping(value = "/stadiumPictureController")
@RestController
public class StadiumPictureController {
	public StadiumPicture tempStadiumPicture = new StadiumPicture();
	@Autowired
	protected StadiumService stadiumService;
	@Autowired
	protected StadiumPictureService service;

	@RequestMapping(value = "/activateAllStadiumPictures", method = RequestMethod.POST)
	public Map<String,Object> activateAllStadiumPictures(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateAllStadiumPictures(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/activateStadiumPicture", method = RequestMethod.POST)
	public Map<String,Object> activateStadiumPicture(@RequestParam Long stadiumPictureId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateStadiumPicture(stadiumPictureId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/addStadiumPicture", method = RequestMethod.POST)
	public Map<String,Object> addStadiumPicture(@RequestBody StadiumPicture stadiumPicture) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.addStadiumPicture(stadiumPicture);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneAllStadiumPictures", method = RequestMethod.POST)
	public Map<String,Object> cloneAllStadiumPictures(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneAllStadiumPictures(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneStadiumPicture", method = RequestMethod.POST)
	public Map<String,Object> cloneStadiumPicture(@RequestParam Long stadiumPictureId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneStadiumPicture(stadiumPictureId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteAllStadiumPictures", method = RequestMethod.POST)
	public Map<String,Object> deleteAllStadiumPictures(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteAllStadiumPictures(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteStadiumPicture", method = RequestMethod.POST)
	public Map<String,Object> deleteStadiumPicture(@RequestParam Long stadiumPictureId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteStadiumPicture(stadiumPictureId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/exportPPTStadiumPictures")
	public void exportPPTStadiumPictures(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumPictures.pptx");
			List<StadiumPicture> list = service.listAllStadiumPictures();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆图片信息表";
			String [] headers = {"场馆图片序号","场馆图片名称","活跃","场馆","图片"};
			Boolean [] isImages = {false,false,false,false,false};
			
			for (StadiumPicture stadiumPicture:list) {
				Long stadiumId = stadiumPicture.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumPicture.getStadiumPictureId()),stadiumPicture.getStadiumPictureName(),StringUtil.toNullString(stadiumPicture.getActive()),stadiumIdName0,stadiumPicture.getPicture()};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumPictures")
	public void exportStadiumPictures(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumPictures.xls");
			List<StadiumPicture> list = service.listAllStadiumPictures();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆图片信息表";
			String [] headers = {"场馆图片序号","场馆图片名称","活跃","场馆","图片"};
			Boolean [] isImages = {false,false,false,false,false};
			
			for (StadiumPicture stadiumPicture:list) {
				Long stadiumId = stadiumPicture.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumPicture.getStadiumPictureId()),stadiumPicture.getStadiumPictureName(),StringUtil.toNullString(stadiumPicture.getActive()),stadiumIdName0,stadiumPicture.getPicture()};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumPicturesPDF")
	public void exportStadiumPicturesPDF(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumPictures.pdf");
			List<StadiumPicture> list = service.listAllStadiumPictures();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆图片信息表";
			String [] headers = {"场馆图片序号","场馆图片名称","活跃","场馆","图片"};
			Boolean [] isImages = {false,false,false,false,false};
			
			for (StadiumPicture stadiumPicture:list) {
				Long stadiumId = stadiumPicture.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumPicture.getStadiumPictureId()),stadiumPicture.getStadiumPictureName(),StringUtil.toNullString(stadiumPicture.getActive()),stadiumIdName0,stadiumPicture.getPicture()};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumPicturesWord")
	public void exportStadiumPicturesWord(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumPictures.doc");
			List<StadiumPicture> list = service.listAllStadiumPictures();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆图片信息表";
			String [] headers = {"场馆图片序号","场馆图片名称","活跃","场馆","图片"};
			Boolean [] isImages = {false,false,false,false,false};
			
			for (StadiumPicture stadiumPicture:list) {
				Long stadiumId = stadiumPicture.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumPicture.getStadiumPictureId()),stadiumPicture.getStadiumPictureName(),StringUtil.toNullString(stadiumPicture.getActive()),stadiumIdName0,stadiumPicture.getPicture()};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterPPTStadiumPictures", method = RequestMethod.GET)
	public void filterPPTStadiumPictures(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumPictureName", required = false) String stadiumPictureName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "picture", required = false) String picture) throws Exception{
		StadiumPicture stadiumPicture0 = new StadiumPicture();
		if (!StringUtil.isBlank(stadiumPictureName)) stadiumPicture0.setStadiumPictureName(stadiumPictureName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumPicture0.setActive(activeBool);
		if (stadiumId != null) stadiumPicture0.setStadiumId(stadiumId);
		if (!StringUtil.isBlank(picture)) stadiumPicture0.setPicture(picture);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumPictures.pptx");
			List<StadiumPicture> list = service.searchStadiumPicturesByFields(stadiumPicture0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆图片信息表";
			String [] headers = {"场馆图片序号","场馆图片名称","活跃","场馆","图片"};
			Boolean [] isImages = {false,false,false,false,false};
			
			for (StadiumPicture stadiumPicture:list) {
				Long stadiumId0 = stadiumPicture.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumPicture.getStadiumPictureId()),stadiumPicture.getStadiumPictureName(),StringUtil.toNullString(stadiumPicture.getActive()),stadiumIdName0,stadiumPicture.getPicture()};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumPicturesExcel", method = RequestMethod.GET)
	public void filterStadiumPicturesExcel(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumPictureName", required = false) String stadiumPictureName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "picture", required = false) String picture) throws Exception{
		StadiumPicture stadiumPicture0 = new StadiumPicture();
		if (!StringUtil.isBlank(stadiumPictureName)) stadiumPicture0.setStadiumPictureName(stadiumPictureName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumPicture0.setActive(activeBool);
		if (stadiumId != null) stadiumPicture0.setStadiumId(stadiumId);
		if (!StringUtil.isBlank(picture)) stadiumPicture0.setPicture(picture);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumPictures.xls");
			List<StadiumPicture> list = service.searchStadiumPicturesByFields(stadiumPicture0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆图片信息表";
			String [] headers = {"场馆图片序号","场馆图片名称","活跃","场馆","图片"};
			Boolean [] isImages = {false,false,false,false,false};
			
			for (StadiumPicture stadiumPicture:list) {
				Long stadiumId0 = stadiumPicture.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumPicture.getStadiumPictureId()),stadiumPicture.getStadiumPictureName(),StringUtil.toNullString(stadiumPicture.getActive()),stadiumIdName0,stadiumPicture.getPicture()};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumPicturesPDF", method = RequestMethod.GET)
	public void filterStadiumPicturesPDF(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumPictureName", required = false) String stadiumPictureName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "picture", required = false) String picture) throws Exception{
		StadiumPicture stadiumPicture0 = new StadiumPicture();
		if (!StringUtil.isBlank(stadiumPictureName)) stadiumPicture0.setStadiumPictureName(stadiumPictureName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumPicture0.setActive(activeBool);
		if (stadiumId != null) stadiumPicture0.setStadiumId(stadiumId);
		if (!StringUtil.isBlank(picture)) stadiumPicture0.setPicture(picture);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumPictures.pdf");
			List<StadiumPicture> list = service.searchStadiumPicturesByFields(stadiumPicture0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆图片信息表";
			String [] headers = {"场馆图片序号","场馆图片名称","活跃","场馆","图片"};
			Boolean [] isImages = {false,false,false,false,false};
			
			for (StadiumPicture stadiumPicture:list) {
				Long stadiumId0 = stadiumPicture.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumPicture.getStadiumPictureId()),stadiumPicture.getStadiumPictureName(),StringUtil.toNullString(stadiumPicture.getActive()),stadiumIdName0,stadiumPicture.getPicture()};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumPicturesWord", method = RequestMethod.GET)
	public void filterStadiumPicturesWord(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumPictureName", required = false) String stadiumPictureName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "picture", required = false) String picture) throws Exception{
		StadiumPicture stadiumPicture0 = new StadiumPicture();
		if (!StringUtil.isBlank(stadiumPictureName)) stadiumPicture0.setStadiumPictureName(stadiumPictureName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumPicture0.setActive(activeBool);
		if (stadiumId != null) stadiumPicture0.setStadiumId(stadiumId);
		if (!StringUtil.isBlank(picture)) stadiumPicture0.setPicture(picture);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumPictures.doc");
			List<StadiumPicture> list = service.searchStadiumPicturesByFields(stadiumPicture0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆图片信息表";
			String [] headers = {"场馆图片序号","场馆图片名称","活跃","场馆","图片"};
			Boolean [] isImages = {false,false,false,false,false};
			
			for (StadiumPicture stadiumPicture:list) {
				Long stadiumId0 = stadiumPicture.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumPicture.getStadiumPictureId()),stadiumPicture.getStadiumPictureName(),StringUtil.toNullString(stadiumPicture.getActive()),stadiumIdName0,stadiumPicture.getPicture()};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/findStadiumPictureByStadiumPictureId", method = RequestMethod.POST)
	public Map<String,Object> findStadiumPictureByStadiumPictureId(@RequestParam Long stadiumPictureId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		StadiumPicture stadiumPicture = service.findStadiumPictureByStadiumPictureId(stadiumPictureId);
		result.put("success",true);
		result.put("data",stadiumPicture);
		return result;
	}
	@RequestMapping(value = "/findStadiumPictureByStadiumPictureName", method = RequestMethod.POST)
	public Map<String,Object> findStadiumPictureByStadiumPictureName(@RequestParam(value = "stadiumPictureName", required = true) String stadiumPictureName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		StadiumPicture stadiumPicture = service.findStadiumPictureByStadiumPictureName(stadiumPictureName);
		result.put("success",true);
		result.put("data",stadiumPicture);
		return result;
	}
	@RequestMapping(value = "/listActiveStadiumPictures", method = RequestMethod.POST)
	public Map<String,Object> listActiveStadiumPictures() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumPicture> list = service.listActiveStadiumPictures();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/listAllStadiumPictures", method = RequestMethod.POST)
	public Map<String,Object> listAllStadiumPictures() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumPicture> list = service.listAllStadiumPictures();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/searchStadiumPicturesByFieldsByPage", method = RequestMethod.POST)
	public Map<String,Object> searchStadiumPicturesByFieldsByPage(@RequestParam(value = "page", required = false) Integer pagenum,
		@RequestParam(value = "rows", required = false) Integer pagesize,
		@RequestParam(value = "last", required = false) String lastFlag,
		@RequestParam(value = "stadiumPictureName", required = false) String stadiumPictureName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "picture", required = false) String picture) throws Exception{
		StadiumPicture stadiumPicture = new StadiumPicture();
		if (stadiumPictureName != null) stadiumPicture.setStadiumPictureName(stadiumPictureName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumPicture.setActive(activeBool);
		if (stadiumId != null) stadiumPicture.setStadiumId(stadiumId);
		if (picture != null) stadiumPicture.setPicture(picture);
		if (pagesize==null || pagesize <= 0) pagesize = 10;
		Integer recordCount = service.countSearchStadiumPicturesByFieldsRecords(stadiumPicture);
		Integer pageCount = (int)Math.ceil((double)recordCount/pagesize);
		if (pageCount <= 1) pageCount = 1;
		if (pagenum==null || pagenum <= 1) pagenum = 1;
		if (pagenum >= pageCount) pagenum = pageCount;
		Boolean lastFlagBool = BooleanUtil.parseBoolean(lastFlag);
		if (lastFlagBool == null) lastFlagBool = false;
		if (lastFlagBool) pagenum = pageCount;
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumPicture> stadiumPictureList = service.searchStadiumPicturesByFieldsByPage(stadiumPicture,pagesize,pagenum);
		result.put("success",true);
		result.put("rows",stadiumPictureList);
		result.put("total",recordCount);
		result.put("page",pagenum);
		return result;
	}
	@RequestMapping(value = "/searchStadiumPicturesByStadiumPictureName", method = RequestMethod.POST)
	public Map<String,Object> searchStadiumPicturesByStadiumPictureName(@RequestParam(value = "stadiumPictureName", required = true) String stadiumPictureName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumPicture> stadiumPictureList = service.searchStadiumPicturesByStadiumPictureName(stadiumPictureName);
		result.put("success",true);
		result.put("rows",stadiumPictureList);
		return result;
	}
	@RequestMapping(value = "/softDeleteAllStadiumPictures", method = RequestMethod.POST)
	public Map<String,Object> softDeleteAllStadiumPictures(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteAllStadiumPictures(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/softDeleteStadiumPicture", method = RequestMethod.POST)
	public Map<String,Object> softDeleteStadiumPicture(@RequestParam Long stadiumPictureId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteStadiumPicture(stadiumPictureId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleOneStadiumPicture", method = RequestMethod.POST)
	public Map<String,Object> toggleOneStadiumPicture(@RequestParam Long stadiumPictureId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleOneStadiumPicture(stadiumPictureId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleStadiumPicture", method = RequestMethod.POST)
	public Map<String,Object> toggleStadiumPicture(@RequestParam Long stadiumPictureId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleStadiumPicture(stadiumPictureId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/updateStadiumPicture", method = RequestMethod.POST)
	public Map<String,Object> updateStadiumPicture(@RequestBody StadiumPicture stadiumPicture) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.updateStadiumPicture(stadiumPicture);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
}

