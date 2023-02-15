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
import org.javaforever.myareas.domain.StadiumArea;
import org.javaforever.myareas.service.StadiumAreaService;
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

@RequestMapping(value = "/stadiumAreaController")
@RestController
public class StadiumAreaController {
	public StadiumArea tempStadiumArea = new StadiumArea();
	@Autowired
	protected StadiumService stadiumService;
	@Autowired
	protected StadiumAreaService service;

	@RequestMapping(value = "/activateAllStadiumAreas", method = RequestMethod.POST)
	public Map<String,Object> activateAllStadiumAreas(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateAllStadiumAreas(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/activateStadiumArea", method = RequestMethod.POST)
	public Map<String,Object> activateStadiumArea(@RequestParam Long stadiumAreaId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateStadiumArea(stadiumAreaId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/addStadiumArea", method = RequestMethod.POST)
	public Map<String,Object> addStadiumArea(@RequestBody StadiumArea stadiumArea) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.addStadiumArea(stadiumArea);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneAllStadiumAreas", method = RequestMethod.POST)
	public Map<String,Object> cloneAllStadiumAreas(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneAllStadiumAreas(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneStadiumArea", method = RequestMethod.POST)
	public Map<String,Object> cloneStadiumArea(@RequestParam Long stadiumAreaId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneStadiumArea(stadiumAreaId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteAllStadiumAreas", method = RequestMethod.POST)
	public Map<String,Object> deleteAllStadiumAreas(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteAllStadiumAreas(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteStadiumArea", method = RequestMethod.POST)
	public Map<String,Object> deleteStadiumArea(@RequestParam Long stadiumAreaId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteStadiumArea(stadiumAreaId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/exportPPTStadiumAreas")
	public void exportPPTStadiumAreas(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumAreas.pptx");
			List<StadiumArea> list = service.listAllStadiumAreas();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆区域信息表";
			String [] headers = {"场馆区域序号","场馆区域名称","活跃","场馆","时间片序号","开始时间","结束时间","费用","其他费用","状态"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false};
			
			for (StadiumArea stadiumArea:list) {
				Long stadiumId = stadiumArea.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumArea.getStadiumAreaId()),stadiumArea.getStadiumAreaName(),StringUtil.toNullString(stadiumArea.getActive()),stadiumIdName0,StringUtil.toNullString(stadiumArea.getTimeId()),stadiumArea.getStartTime(),stadiumArea.getEndTime(),StringUtil.toNullString(stadiumArea.getFee()),StringUtil.toNullString(stadiumArea.getOtherFee()),StringUtil.toNullString(stadiumArea.getState())};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumAreas")
	public void exportStadiumAreas(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumAreas.xls");
			List<StadiumArea> list = service.listAllStadiumAreas();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆区域信息表";
			String [] headers = {"场馆区域序号","场馆区域名称","活跃","场馆","时间片序号","开始时间","结束时间","费用","其他费用","状态"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false};
			
			for (StadiumArea stadiumArea:list) {
				Long stadiumId = stadiumArea.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumArea.getStadiumAreaId()),stadiumArea.getStadiumAreaName(),StringUtil.toNullString(stadiumArea.getActive()),stadiumIdName0,StringUtil.toNullString(stadiumArea.getTimeId()),stadiumArea.getStartTime(),stadiumArea.getEndTime(),StringUtil.toNullString(stadiumArea.getFee()),StringUtil.toNullString(stadiumArea.getOtherFee()),StringUtil.toNullString(stadiumArea.getState())};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumAreasPDF")
	public void exportStadiumAreasPDF(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumAreas.pdf");
			List<StadiumArea> list = service.listAllStadiumAreas();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆区域信息表";
			String [] headers = {"场馆区域序号","场馆区域名称","活跃","场馆","时间片序号","开始时间","结束时间","费用","其他费用","状态"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false};
			
			for (StadiumArea stadiumArea:list) {
				Long stadiumId = stadiumArea.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumArea.getStadiumAreaId()),stadiumArea.getStadiumAreaName(),StringUtil.toNullString(stadiumArea.getActive()),stadiumIdName0,StringUtil.toNullString(stadiumArea.getTimeId()),stadiumArea.getStartTime(),stadiumArea.getEndTime(),StringUtil.toNullString(stadiumArea.getFee()),StringUtil.toNullString(stadiumArea.getOtherFee()),StringUtil.toNullString(stadiumArea.getState())};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumAreasWord")
	public void exportStadiumAreasWord(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumAreas.doc");
			List<StadiumArea> list = service.listAllStadiumAreas();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆区域信息表";
			String [] headers = {"场馆区域序号","场馆区域名称","活跃","场馆","时间片序号","开始时间","结束时间","费用","其他费用","状态"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false};
			
			for (StadiumArea stadiumArea:list) {
				Long stadiumId = stadiumArea.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumArea.getStadiumAreaId()),stadiumArea.getStadiumAreaName(),StringUtil.toNullString(stadiumArea.getActive()),stadiumIdName0,StringUtil.toNullString(stadiumArea.getTimeId()),stadiumArea.getStartTime(),stadiumArea.getEndTime(),StringUtil.toNullString(stadiumArea.getFee()),StringUtil.toNullString(stadiumArea.getOtherFee()),StringUtil.toNullString(stadiumArea.getState())};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterPPTStadiumAreas", method = RequestMethod.GET)
	public void filterPPTStadiumAreas(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumAreaName", required = false) String stadiumAreaName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "timeId", required = false) Long timeId,
		@RequestParam(value = "startTime", required = false) String startTime,
		@RequestParam(value = "endTime", required = false) String endTime,
		@RequestParam(value = "fee", required = false) Double fee,
		@RequestParam(value = "otherFee", required = false) Double otherFee,
		@RequestParam(value = "state", required = false) Integer state) throws Exception{
		StadiumArea stadiumArea0 = new StadiumArea();
		if (!StringUtil.isBlank(stadiumAreaName)) stadiumArea0.setStadiumAreaName(stadiumAreaName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumArea0.setActive(activeBool);
		if (stadiumId != null) stadiumArea0.setStadiumId(stadiumId);
		if (timeId != null) stadiumArea0.setTimeId(timeId);
		if (!StringUtil.isBlank(startTime)) stadiumArea0.setStartTime(startTime);
		if (!StringUtil.isBlank(endTime)) stadiumArea0.setEndTime(endTime);
		if (fee != null) stadiumArea0.setFee(fee);
		if (otherFee != null) stadiumArea0.setOtherFee(otherFee);
		if (state != null) stadiumArea0.setState(state);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumAreas.pptx");
			List<StadiumArea> list = service.searchStadiumAreasByFields(stadiumArea0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆区域信息表";
			String [] headers = {"场馆区域序号","场馆区域名称","活跃","场馆","时间片序号","开始时间","结束时间","费用","其他费用","状态"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false};
			
			for (StadiumArea stadiumArea:list) {
				Long stadiumId0 = stadiumArea.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumArea.getStadiumAreaId()),stadiumArea.getStadiumAreaName(),StringUtil.toNullString(stadiumArea.getActive()),stadiumIdName0,StringUtil.toNullString(stadiumArea.getTimeId()),stadiumArea.getStartTime(),stadiumArea.getEndTime(),StringUtil.toNullString(stadiumArea.getFee()),StringUtil.toNullString(stadiumArea.getOtherFee()),StringUtil.toNullString(stadiumArea.getState())};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumAreasExcel", method = RequestMethod.GET)
	public void filterStadiumAreasExcel(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumAreaName", required = false) String stadiumAreaName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "timeId", required = false) Long timeId,
		@RequestParam(value = "startTime", required = false) String startTime,
		@RequestParam(value = "endTime", required = false) String endTime,
		@RequestParam(value = "fee", required = false) Double fee,
		@RequestParam(value = "otherFee", required = false) Double otherFee,
		@RequestParam(value = "state", required = false) Integer state) throws Exception{
		StadiumArea stadiumArea0 = new StadiumArea();
		if (!StringUtil.isBlank(stadiumAreaName)) stadiumArea0.setStadiumAreaName(stadiumAreaName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumArea0.setActive(activeBool);
		if (stadiumId != null) stadiumArea0.setStadiumId(stadiumId);
		if (timeId != null) stadiumArea0.setTimeId(timeId);
		if (!StringUtil.isBlank(startTime)) stadiumArea0.setStartTime(startTime);
		if (!StringUtil.isBlank(endTime)) stadiumArea0.setEndTime(endTime);
		if (fee != null) stadiumArea0.setFee(fee);
		if (otherFee != null) stadiumArea0.setOtherFee(otherFee);
		if (state != null) stadiumArea0.setState(state);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumAreas.xls");
			List<StadiumArea> list = service.searchStadiumAreasByFields(stadiumArea0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆区域信息表";
			String [] headers = {"场馆区域序号","场馆区域名称","活跃","场馆","时间片序号","开始时间","结束时间","费用","其他费用","状态"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false};
			
			for (StadiumArea stadiumArea:list) {
				Long stadiumId0 = stadiumArea.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumArea.getStadiumAreaId()),stadiumArea.getStadiumAreaName(),StringUtil.toNullString(stadiumArea.getActive()),stadiumIdName0,StringUtil.toNullString(stadiumArea.getTimeId()),stadiumArea.getStartTime(),stadiumArea.getEndTime(),StringUtil.toNullString(stadiumArea.getFee()),StringUtil.toNullString(stadiumArea.getOtherFee()),StringUtil.toNullString(stadiumArea.getState())};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumAreasPDF", method = RequestMethod.GET)
	public void filterStadiumAreasPDF(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumAreaName", required = false) String stadiumAreaName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "timeId", required = false) Long timeId,
		@RequestParam(value = "startTime", required = false) String startTime,
		@RequestParam(value = "endTime", required = false) String endTime,
		@RequestParam(value = "fee", required = false) Double fee,
		@RequestParam(value = "otherFee", required = false) Double otherFee,
		@RequestParam(value = "state", required = false) Integer state) throws Exception{
		StadiumArea stadiumArea0 = new StadiumArea();
		if (!StringUtil.isBlank(stadiumAreaName)) stadiumArea0.setStadiumAreaName(stadiumAreaName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumArea0.setActive(activeBool);
		if (stadiumId != null) stadiumArea0.setStadiumId(stadiumId);
		if (timeId != null) stadiumArea0.setTimeId(timeId);
		if (!StringUtil.isBlank(startTime)) stadiumArea0.setStartTime(startTime);
		if (!StringUtil.isBlank(endTime)) stadiumArea0.setEndTime(endTime);
		if (fee != null) stadiumArea0.setFee(fee);
		if (otherFee != null) stadiumArea0.setOtherFee(otherFee);
		if (state != null) stadiumArea0.setState(state);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumAreas.pdf");
			List<StadiumArea> list = service.searchStadiumAreasByFields(stadiumArea0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆区域信息表";
			String [] headers = {"场馆区域序号","场馆区域名称","活跃","场馆","时间片序号","开始时间","结束时间","费用","其他费用","状态"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false};
			
			for (StadiumArea stadiumArea:list) {
				Long stadiumId0 = stadiumArea.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumArea.getStadiumAreaId()),stadiumArea.getStadiumAreaName(),StringUtil.toNullString(stadiumArea.getActive()),stadiumIdName0,StringUtil.toNullString(stadiumArea.getTimeId()),stadiumArea.getStartTime(),stadiumArea.getEndTime(),StringUtil.toNullString(stadiumArea.getFee()),StringUtil.toNullString(stadiumArea.getOtherFee()),StringUtil.toNullString(stadiumArea.getState())};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumAreasWord", method = RequestMethod.GET)
	public void filterStadiumAreasWord(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumAreaName", required = false) String stadiumAreaName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "timeId", required = false) Long timeId,
		@RequestParam(value = "startTime", required = false) String startTime,
		@RequestParam(value = "endTime", required = false) String endTime,
		@RequestParam(value = "fee", required = false) Double fee,
		@RequestParam(value = "otherFee", required = false) Double otherFee,
		@RequestParam(value = "state", required = false) Integer state) throws Exception{
		StadiumArea stadiumArea0 = new StadiumArea();
		if (!StringUtil.isBlank(stadiumAreaName)) stadiumArea0.setStadiumAreaName(stadiumAreaName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumArea0.setActive(activeBool);
		if (stadiumId != null) stadiumArea0.setStadiumId(stadiumId);
		if (timeId != null) stadiumArea0.setTimeId(timeId);
		if (!StringUtil.isBlank(startTime)) stadiumArea0.setStartTime(startTime);
		if (!StringUtil.isBlank(endTime)) stadiumArea0.setEndTime(endTime);
		if (fee != null) stadiumArea0.setFee(fee);
		if (otherFee != null) stadiumArea0.setOtherFee(otherFee);
		if (state != null) stadiumArea0.setState(state);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=StadiumAreas.doc");
			List<StadiumArea> list = service.searchStadiumAreasByFields(stadiumArea0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆区域信息表";
			String [] headers = {"场馆区域序号","场馆区域名称","活跃","场馆","时间片序号","开始时间","结束时间","费用","其他费用","状态"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false};
			
			for (StadiumArea stadiumArea:list) {
				Long stadiumId0 = stadiumArea.getStadiumId();
				Stadium stadiumIdType0;
				String stadiumIdName0 = "";
				if (stadiumId0 != null) {
					stadiumIdType0 = stadiumService.findStadiumByStadiumId(stadiumId0);
					if (stadiumIdType0 !=null) stadiumIdName0 = stadiumIdType0.getStadiumName();
				}
				Object [] row = {StringUtil.toNullString(stadiumArea.getStadiumAreaId()),stadiumArea.getStadiumAreaName(),StringUtil.toNullString(stadiumArea.getActive()),stadiumIdName0,StringUtil.toNullString(stadiumArea.getTimeId()),stadiumArea.getStartTime(),stadiumArea.getEndTime(),StringUtil.toNullString(stadiumArea.getFee()),StringUtil.toNullString(stadiumArea.getOtherFee()),StringUtil.toNullString(stadiumArea.getState())};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/findStadiumAreaByStadiumAreaId", method = RequestMethod.POST)
	public Map<String,Object> findStadiumAreaByStadiumAreaId(@RequestParam Long stadiumAreaId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		StadiumArea stadiumArea = service.findStadiumAreaByStadiumAreaId(stadiumAreaId);
		result.put("success",true);
		result.put("data",stadiumArea);
		return result;
	}
	@RequestMapping(value = "/findStadiumAreaByStadiumAreaName", method = RequestMethod.POST)
	public Map<String,Object> findStadiumAreaByStadiumAreaName(@RequestParam(value = "stadiumAreaName", required = true) String stadiumAreaName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		StadiumArea stadiumArea = service.findStadiumAreaByStadiumAreaName(stadiumAreaName);
		result.put("success",true);
		result.put("data",stadiumArea);
		return result;
	}
	@RequestMapping(value = "/listActiveStadiumAreas", method = RequestMethod.POST)
	public Map<String,Object> listActiveStadiumAreas() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumArea> list = service.listActiveStadiumAreas();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/listAllStadiumAreas", method = RequestMethod.POST)
	public Map<String,Object> listAllStadiumAreas() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumArea> list = service.listAllStadiumAreas();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/searchStadiumAreasByFieldsByPage", method = RequestMethod.POST)
	public Map<String,Object> searchStadiumAreasByFieldsByPage(@RequestParam(value = "page", required = false) Integer pagenum,
		@RequestParam(value = "rows", required = false) Integer pagesize,
		@RequestParam(value = "last", required = false) String lastFlag,
		@RequestParam(value = "stadiumAreaName", required = false) String stadiumAreaName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumId", required = false) Long stadiumId,
		@RequestParam(value = "timeId", required = false) Long timeId,
		@RequestParam(value = "startTime", required = false) String startTime,
		@RequestParam(value = "endTime", required = false) String endTime,
		@RequestParam(value = "fee", required = false) Double fee,
		@RequestParam(value = "otherFee", required = false) Double otherFee,
		@RequestParam(value = "state", required = false) Integer state) throws Exception{
		StadiumArea stadiumArea = new StadiumArea();
		if (stadiumAreaName != null) stadiumArea.setStadiumAreaName(stadiumAreaName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadiumArea.setActive(activeBool);
		if (stadiumId != null) stadiumArea.setStadiumId(stadiumId);
		if (timeId != null) stadiumArea.setTimeId(timeId);
		if (startTime != null) stadiumArea.setStartTime(startTime);
		if (endTime != null) stadiumArea.setEndTime(endTime);
		if (fee != null) stadiumArea.setFee(fee);
		if (otherFee != null) stadiumArea.setOtherFee(otherFee);
		if (state != null) stadiumArea.setState(state);
		if (pagesize==null || pagesize <= 0) pagesize = 10;
		Integer recordCount = service.countSearchStadiumAreasByFieldsRecords(stadiumArea);
		Integer pageCount = (int)Math.ceil((double)recordCount/pagesize);
		if (pageCount <= 1) pageCount = 1;
		if (pagenum==null || pagenum <= 1) pagenum = 1;
		if (pagenum >= pageCount) pagenum = pageCount;
		Boolean lastFlagBool = BooleanUtil.parseBoolean(lastFlag);
		if (lastFlagBool == null) lastFlagBool = false;
		if (lastFlagBool) pagenum = pageCount;
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumArea> stadiumAreaList = service.searchStadiumAreasByFieldsByPage(stadiumArea,pagesize,pagenum);
		result.put("success",true);
		result.put("rows",stadiumAreaList);
		result.put("total",recordCount);
		result.put("page",pagenum);
		return result;
	}
	@RequestMapping(value = "/searchStadiumAreasByStadiumAreaName", method = RequestMethod.POST)
	public Map<String,Object> searchStadiumAreasByStadiumAreaName(@RequestParam(value = "stadiumAreaName", required = true) String stadiumAreaName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<StadiumArea> stadiumAreaList = service.searchStadiumAreasByStadiumAreaName(stadiumAreaName);
		result.put("success",true);
		result.put("rows",stadiumAreaList);
		return result;
	}
	@RequestMapping(value = "/softDeleteAllStadiumAreas", method = RequestMethod.POST)
	public Map<String,Object> softDeleteAllStadiumAreas(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteAllStadiumAreas(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/softDeleteStadiumArea", method = RequestMethod.POST)
	public Map<String,Object> softDeleteStadiumArea(@RequestParam Long stadiumAreaId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteStadiumArea(stadiumAreaId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleOneStadiumArea", method = RequestMethod.POST)
	public Map<String,Object> toggleOneStadiumArea(@RequestParam Long stadiumAreaId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleOneStadiumArea(stadiumAreaId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleStadiumArea", method = RequestMethod.POST)
	public Map<String,Object> toggleStadiumArea(@RequestParam Long stadiumAreaId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleStadiumArea(stadiumAreaId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/updateStadiumArea", method = RequestMethod.POST)
	public Map<String,Object> updateStadiumArea(@RequestBody StadiumArea stadiumArea) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.updateStadiumArea(stadiumArea);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
}

