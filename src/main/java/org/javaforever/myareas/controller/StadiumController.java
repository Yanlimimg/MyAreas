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

@RequestMapping(value = "/stadiumController")
@RestController
public class StadiumController {
	public Stadium tempStadium = new Stadium();
	@Autowired
	protected StadiumService service;

	@RequestMapping(value = "/activateAllStadiums", method = RequestMethod.POST)
	public Map<String,Object> activateAllStadiums(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateAllStadiums(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/activateStadium", method = RequestMethod.POST)
	public Map<String,Object> activateStadium(@RequestParam Long stadiumId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.activateStadium(stadiumId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/addStadium", method = RequestMethod.POST)
	public Map<String,Object> addStadium(@RequestBody Stadium stadium) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.addStadium(stadium);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneAllStadiums", method = RequestMethod.POST)
	public Map<String,Object> cloneAllStadiums(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneAllStadiums(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/cloneStadium", method = RequestMethod.POST)
	public Map<String,Object> cloneStadium(@RequestParam Long stadiumId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.cloneStadium(stadiumId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteAllStadiums", method = RequestMethod.POST)
	public Map<String,Object> deleteAllStadiums(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteAllStadiums(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/deleteStadium", method = RequestMethod.POST)
	public Map<String,Object> deleteStadium(@RequestParam Long stadiumId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.deleteStadium(stadiumId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/exportPPTStadiums")
	public void exportPPTStadiums(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Stadiums.pptx");
			List<Stadium> list = service.listAllStadiums();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆信息表";
			String [] headers = {"场馆序号","场馆名称","活跃","评价","开放时间","纬度","经度","电话","细节","能否停车","是否有卖品部","能否吃饭","能否出租","是否可使用信用卡","能否洗浴","能否长定","球类类型","场馆类型","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (Stadium stadium:list) {
				Object [] row = {StringUtil.toNullString(stadium.getStadiumId()),stadium.getStadiumName(),StringUtil.toNullString(stadium.getActive()),StringUtil.toNullString(stadium.getStadiumComment()),stadium.getOpentime(),StringUtil.toNullString(stadium.getLatitude()),StringUtil.toNullString(stadium.getLongitude()),stadium.getTelephone(),stadium.getDetail(),stadium.getCanPark(),stadium.getCanSell(),stadium.getCanRest(),stadium.getCanRent(),stadium.getCanCard(),stadium.getCanBath(),stadium.getCanLongBuy(),StringUtil.toNullString(stadium.getBallType()),StringUtil.toNullString(stadium.getStadiumType()),StringUtil.toNullString(stadium.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiums")
	public void exportStadiums(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Stadiums.xls");
			List<Stadium> list = service.listAllStadiums();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆信息表";
			String [] headers = {"场馆序号","场馆名称","活跃","评价","开放时间","纬度","经度","电话","细节","能否停车","是否有卖品部","能否吃饭","能否出租","是否可使用信用卡","能否洗浴","能否长定","球类类型","场馆类型","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (Stadium stadium:list) {
				Object [] row = {StringUtil.toNullString(stadium.getStadiumId()),stadium.getStadiumName(),StringUtil.toNullString(stadium.getActive()),StringUtil.toNullString(stadium.getStadiumComment()),stadium.getOpentime(),StringUtil.toNullString(stadium.getLatitude()),StringUtil.toNullString(stadium.getLongitude()),stadium.getTelephone(),stadium.getDetail(),stadium.getCanPark(),stadium.getCanSell(),stadium.getCanRest(),stadium.getCanRent(),stadium.getCanCard(),stadium.getCanBath(),stadium.getCanLongBuy(),StringUtil.toNullString(stadium.getBallType()),StringUtil.toNullString(stadium.getStadiumType()),StringUtil.toNullString(stadium.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumsPDF")
	public void exportStadiumsPDF(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Stadiums.pdf");
			List<Stadium> list = service.listAllStadiums();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆信息表";
			String [] headers = {"场馆序号","场馆名称","活跃","评价","开放时间","纬度","经度","电话","细节","能否停车","是否有卖品部","能否吃饭","能否出租","是否可使用信用卡","能否洗浴","能否长定","球类类型","场馆类型","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (Stadium stadium:list) {
				Object [] row = {StringUtil.toNullString(stadium.getStadiumId()),stadium.getStadiumName(),StringUtil.toNullString(stadium.getActive()),StringUtil.toNullString(stadium.getStadiumComment()),stadium.getOpentime(),StringUtil.toNullString(stadium.getLatitude()),StringUtil.toNullString(stadium.getLongitude()),stadium.getTelephone(),stadium.getDetail(),stadium.getCanPark(),stadium.getCanSell(),stadium.getCanRest(),stadium.getCanRent(),stadium.getCanCard(),stadium.getCanBath(),stadium.getCanLongBuy(),StringUtil.toNullString(stadium.getBallType()),StringUtil.toNullString(stadium.getStadiumType()),StringUtil.toNullString(stadium.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/exportStadiumsWord")
	public void exportStadiumsWord(HttpSession session,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Stadiums.doc");
			List<Stadium> list = service.listAllStadiums();
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆信息表";
			String [] headers = {"场馆序号","场馆名称","活跃","评价","开放时间","纬度","经度","电话","细节","能否停车","是否有卖品部","能否吃饭","能否出租","是否可使用信用卡","能否洗浴","能否长定","球类类型","场馆类型","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (Stadium stadium:list) {
				Object [] row = {StringUtil.toNullString(stadium.getStadiumId()),stadium.getStadiumName(),StringUtil.toNullString(stadium.getActive()),StringUtil.toNullString(stadium.getStadiumComment()),stadium.getOpentime(),StringUtil.toNullString(stadium.getLatitude()),StringUtil.toNullString(stadium.getLongitude()),stadium.getTelephone(),stadium.getDetail(),stadium.getCanPark(),stadium.getCanSell(),stadium.getCanRest(),stadium.getCanRent(),stadium.getCanCard(),stadium.getCanBath(),stadium.getCanLongBuy(),StringUtil.toNullString(stadium.getBallType()),StringUtil.toNullString(stadium.getStadiumType()),StringUtil.toNullString(stadium.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterPPTStadiums", method = RequestMethod.GET)
	public void filterPPTStadiums(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumName", required = false) String stadiumName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumComment", required = false) Double stadiumComment,
		@RequestParam(value = "opentime", required = false) String opentime,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "telephone", required = false) String telephone,
		@RequestParam(value = "detail", required = false) String detail,
		@RequestParam(value = "canPark", required = false) String canPark,
		@RequestParam(value = "canSell", required = false) String canSell,
		@RequestParam(value = "canRest", required = false) String canRest,
		@RequestParam(value = "canRent", required = false) String canRent,
		@RequestParam(value = "canCard", required = false) String canCard,
		@RequestParam(value = "canBath", required = false) String canBath,
		@RequestParam(value = "canLongBuy", required = false) String canLongBuy,
		@RequestParam(value = "ballType", required = false) Integer ballType,
		@RequestParam(value = "stadiumType", required = false) Integer stadiumType,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		Stadium stadium0 = new Stadium();
		if (!StringUtil.isBlank(stadiumName)) stadium0.setStadiumName(stadiumName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadium0.setActive(activeBool);
		if (stadiumComment != null) stadium0.setStadiumComment(stadiumComment);
		if (!StringUtil.isBlank(opentime)) stadium0.setOpentime(opentime);
		if (latitude != null) stadium0.setLatitude(latitude);
		if (longitude != null) stadium0.setLongitude(longitude);
		if (!StringUtil.isBlank(telephone)) stadium0.setTelephone(telephone);
		if (!StringUtil.isBlank(detail)) stadium0.setDetail(detail);
		if (!StringUtil.isBlank(canPark)) stadium0.setCanPark(canPark);
		if (!StringUtil.isBlank(canSell)) stadium0.setCanSell(canSell);
		if (!StringUtil.isBlank(canRest)) stadium0.setCanRest(canRest);
		if (!StringUtil.isBlank(canRent)) stadium0.setCanRent(canRent);
		if (!StringUtil.isBlank(canCard)) stadium0.setCanCard(canCard);
		if (!StringUtil.isBlank(canBath)) stadium0.setCanBath(canBath);
		if (!StringUtil.isBlank(canLongBuy)) stadium0.setCanLongBuy(canLongBuy);
		if (ballType != null) stadium0.setBallType(ballType);
		if (stadiumType != null) stadium0.setStadiumType(stadiumType);
		if (cityId != null) stadium0.setCityId(cityId);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Stadiums.pptx");
			List<Stadium> list = service.searchStadiumsByFields(stadium0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆信息表";
			String [] headers = {"场馆序号","场馆名称","活跃","评价","开放时间","纬度","经度","电话","细节","能否停车","是否有卖品部","能否吃饭","能否出租","是否可使用信用卡","能否洗浴","能否长定","球类类型","场馆类型","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (Stadium stadium:list) {
				Object [] row = {StringUtil.toNullString(stadium.getStadiumId()),stadium.getStadiumName(),StringUtil.toNullString(stadium.getActive()),StringUtil.toNullString(stadium.getStadiumComment()),stadium.getOpentime(),StringUtil.toNullString(stadium.getLatitude()),StringUtil.toNullString(stadium.getLongitude()),stadium.getTelephone(),stadium.getDetail(),stadium.getCanPark(),stadium.getCanSell(),stadium.getCanRest(),stadium.getCanRent(),stadium.getCanCard(),stadium.getCanBath(),stadium.getCanLongBuy(),StringUtil.toNullString(stadium.getBallType()),StringUtil.toNullString(stadium.getStadiumType()),StringUtil.toNullString(stadium.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			POIPPTUtil.exportPPTWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumsExcel", method = RequestMethod.GET)
	public void filterStadiumsExcel(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumName", required = false) String stadiumName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumComment", required = false) Double stadiumComment,
		@RequestParam(value = "opentime", required = false) String opentime,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "telephone", required = false) String telephone,
		@RequestParam(value = "detail", required = false) String detail,
		@RequestParam(value = "canPark", required = false) String canPark,
		@RequestParam(value = "canSell", required = false) String canSell,
		@RequestParam(value = "canRest", required = false) String canRest,
		@RequestParam(value = "canRent", required = false) String canRent,
		@RequestParam(value = "canCard", required = false) String canCard,
		@RequestParam(value = "canBath", required = false) String canBath,
		@RequestParam(value = "canLongBuy", required = false) String canLongBuy,
		@RequestParam(value = "ballType", required = false) Integer ballType,
		@RequestParam(value = "stadiumType", required = false) Integer stadiumType,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		Stadium stadium0 = new Stadium();
		if (!StringUtil.isBlank(stadiumName)) stadium0.setStadiumName(stadiumName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadium0.setActive(activeBool);
		if (stadiumComment != null) stadium0.setStadiumComment(stadiumComment);
		if (!StringUtil.isBlank(opentime)) stadium0.setOpentime(opentime);
		if (latitude != null) stadium0.setLatitude(latitude);
		if (longitude != null) stadium0.setLongitude(longitude);
		if (!StringUtil.isBlank(telephone)) stadium0.setTelephone(telephone);
		if (!StringUtil.isBlank(detail)) stadium0.setDetail(detail);
		if (!StringUtil.isBlank(canPark)) stadium0.setCanPark(canPark);
		if (!StringUtil.isBlank(canSell)) stadium0.setCanSell(canSell);
		if (!StringUtil.isBlank(canRest)) stadium0.setCanRest(canRest);
		if (!StringUtil.isBlank(canRent)) stadium0.setCanRent(canRent);
		if (!StringUtil.isBlank(canCard)) stadium0.setCanCard(canCard);
		if (!StringUtil.isBlank(canBath)) stadium0.setCanBath(canBath);
		if (!StringUtil.isBlank(canLongBuy)) stadium0.setCanLongBuy(canLongBuy);
		if (ballType != null) stadium0.setBallType(ballType);
		if (stadiumType != null) stadium0.setStadiumType(stadiumType);
		if (cityId != null) stadium0.setCityId(cityId);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Stadiums.xls");
			List<Stadium> list = service.searchStadiumsByFields(stadium0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆信息表";
			String [] headers = {"场馆序号","场馆名称","活跃","评价","开放时间","纬度","经度","电话","细节","能否停车","是否有卖品部","能否吃饭","能否出租","是否可使用信用卡","能否洗浴","能否长定","球类类型","场馆类型","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (Stadium stadium:list) {
				Object [] row = {StringUtil.toNullString(stadium.getStadiumId()),stadium.getStadiumName(),StringUtil.toNullString(stadium.getActive()),StringUtil.toNullString(stadium.getStadiumComment()),stadium.getOpentime(),StringUtil.toNullString(stadium.getLatitude()),StringUtil.toNullString(stadium.getLongitude()),stadium.getTelephone(),stadium.getDetail(),stadium.getCanPark(),stadium.getCanSell(),stadium.getCanRest(),stadium.getCanRent(),stadium.getCanCard(),stadium.getCanBath(),stadium.getCanLongBuy(),StringUtil.toNullString(stadium.getBallType()),StringUtil.toNullString(stadium.getStadiumType()),StringUtil.toNullString(stadium.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			POIExcelUtil.exportExcelWorkbookWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumsPDF", method = RequestMethod.GET)
	public void filterStadiumsPDF(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumName", required = false) String stadiumName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumComment", required = false) Double stadiumComment,
		@RequestParam(value = "opentime", required = false) String opentime,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "telephone", required = false) String telephone,
		@RequestParam(value = "detail", required = false) String detail,
		@RequestParam(value = "canPark", required = false) String canPark,
		@RequestParam(value = "canSell", required = false) String canSell,
		@RequestParam(value = "canRest", required = false) String canRest,
		@RequestParam(value = "canRent", required = false) String canRent,
		@RequestParam(value = "canCard", required = false) String canCard,
		@RequestParam(value = "canBath", required = false) String canBath,
		@RequestParam(value = "canLongBuy", required = false) String canLongBuy,
		@RequestParam(value = "ballType", required = false) Integer ballType,
		@RequestParam(value = "stadiumType", required = false) Integer stadiumType,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		Stadium stadium0 = new Stadium();
		if (!StringUtil.isBlank(stadiumName)) stadium0.setStadiumName(stadiumName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadium0.setActive(activeBool);
		if (stadiumComment != null) stadium0.setStadiumComment(stadiumComment);
		if (!StringUtil.isBlank(opentime)) stadium0.setOpentime(opentime);
		if (latitude != null) stadium0.setLatitude(latitude);
		if (longitude != null) stadium0.setLongitude(longitude);
		if (!StringUtil.isBlank(telephone)) stadium0.setTelephone(telephone);
		if (!StringUtil.isBlank(detail)) stadium0.setDetail(detail);
		if (!StringUtil.isBlank(canPark)) stadium0.setCanPark(canPark);
		if (!StringUtil.isBlank(canSell)) stadium0.setCanSell(canSell);
		if (!StringUtil.isBlank(canRest)) stadium0.setCanRest(canRest);
		if (!StringUtil.isBlank(canRent)) stadium0.setCanRent(canRent);
		if (!StringUtil.isBlank(canCard)) stadium0.setCanCard(canCard);
		if (!StringUtil.isBlank(canBath)) stadium0.setCanBath(canBath);
		if (!StringUtil.isBlank(canLongBuy)) stadium0.setCanLongBuy(canLongBuy);
		if (ballType != null) stadium0.setBallType(ballType);
		if (stadiumType != null) stadium0.setStadiumType(stadiumType);
		if (cityId != null) stadium0.setCityId(cityId);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Stadiums.pdf");
			List<Stadium> list = service.searchStadiumsByFields(stadium0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆信息表";
			String [] headers = {"场馆序号","场馆名称","活跃","评价","开放时间","纬度","经度","电话","细节","能否停车","是否有卖品部","能否吃饭","能否出租","是否可使用信用卡","能否洗浴","能否长定","球类类型","场馆类型","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (Stadium stadium:list) {
				Object [] row = {StringUtil.toNullString(stadium.getStadiumId()),stadium.getStadiumName(),StringUtil.toNullString(stadium.getActive()),StringUtil.toNullString(stadium.getStadiumComment()),stadium.getOpentime(),StringUtil.toNullString(stadium.getLatitude()),StringUtil.toNullString(stadium.getLongitude()),stadium.getTelephone(),stadium.getDetail(),stadium.getCanPark(),stadium.getCanSell(),stadium.getCanRest(),stadium.getCanRent(),stadium.getCanCard(),stadium.getCanBath(),stadium.getCanLongBuy(),StringUtil.toNullString(stadium.getBallType()),StringUtil.toNullString(stadium.getStadiumType()),StringUtil.toNullString(stadium.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			PDFUtil.exportPDFWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/filterStadiumsWord", method = RequestMethod.GET)
	public void filterStadiumsWord(HttpSession session,
		HttpServletResponse response,
		HttpServletRequest request,
		@RequestParam(value = "stadiumName", required = false) String stadiumName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumComment", required = false) Double stadiumComment,
		@RequestParam(value = "opentime", required = false) String opentime,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "telephone", required = false) String telephone,
		@RequestParam(value = "detail", required = false) String detail,
		@RequestParam(value = "canPark", required = false) String canPark,
		@RequestParam(value = "canSell", required = false) String canSell,
		@RequestParam(value = "canRest", required = false) String canRest,
		@RequestParam(value = "canRent", required = false) String canRent,
		@RequestParam(value = "canCard", required = false) String canCard,
		@RequestParam(value = "canBath", required = false) String canBath,
		@RequestParam(value = "canLongBuy", required = false) String canLongBuy,
		@RequestParam(value = "ballType", required = false) Integer ballType,
		@RequestParam(value = "stadiumType", required = false) Integer stadiumType,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		Stadium stadium0 = new Stadium();
		if (!StringUtil.isBlank(stadiumName)) stadium0.setStadiumName(stadiumName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadium0.setActive(activeBool);
		if (stadiumComment != null) stadium0.setStadiumComment(stadiumComment);
		if (!StringUtil.isBlank(opentime)) stadium0.setOpentime(opentime);
		if (latitude != null) stadium0.setLatitude(latitude);
		if (longitude != null) stadium0.setLongitude(longitude);
		if (!StringUtil.isBlank(telephone)) stadium0.setTelephone(telephone);
		if (!StringUtil.isBlank(detail)) stadium0.setDetail(detail);
		if (!StringUtil.isBlank(canPark)) stadium0.setCanPark(canPark);
		if (!StringUtil.isBlank(canSell)) stadium0.setCanSell(canSell);
		if (!StringUtil.isBlank(canRest)) stadium0.setCanRest(canRest);
		if (!StringUtil.isBlank(canRent)) stadium0.setCanRent(canRent);
		if (!StringUtil.isBlank(canCard)) stadium0.setCanCard(canCard);
		if (!StringUtil.isBlank(canBath)) stadium0.setCanBath(canBath);
		if (!StringUtil.isBlank(canLongBuy)) stadium0.setCanLongBuy(canLongBuy);
		if (ballType != null) stadium0.setBallType(ballType);
		if (stadiumType != null) stadium0.setStadiumType(stadiumType);
		if (cityId != null) stadium0.setCityId(cityId);
		try(OutputStream out = response.getOutputStream()){
			response.addHeader("Content-Disposition", "attachment;filename=Stadiums.doc");
			List<Stadium> list = service.searchStadiumsByFields(stadium0);
			List<List<Object>> contents = new ArrayList<>();
			String sheetName = "场馆信息表";
			String [] headers = {"场馆序号","场馆名称","活跃","评价","开放时间","纬度","经度","电话","细节","能否停车","是否有卖品部","能否吃饭","能否出租","是否可使用信用卡","能否洗浴","能否长定","球类类型","场馆类型","城市序号"};
			Boolean [] isImages = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
			
			for (Stadium stadium:list) {
				Object [] row = {StringUtil.toNullString(stadium.getStadiumId()),stadium.getStadiumName(),StringUtil.toNullString(stadium.getActive()),StringUtil.toNullString(stadium.getStadiumComment()),stadium.getOpentime(),StringUtil.toNullString(stadium.getLatitude()),StringUtil.toNullString(stadium.getLongitude()),stadium.getTelephone(),stadium.getDetail(),stadium.getCanPark(),stadium.getCanSell(),stadium.getCanRest(),stadium.getCanRent(),stadium.getCanCard(),stadium.getCanBath(),stadium.getCanLongBuy(),StringUtil.toNullString(stadium.getBallType()),StringUtil.toNullString(stadium.getStadiumType()),StringUtil.toNullString(stadium.getCityId())};
				contents.add(Arrays.asList(row));
			}
			
			WordUtil.exportWordWithImage(out, sheetName, Arrays.asList(headers), contents, Arrays.asList(isImages));
		}
	}
	@RequestMapping(value = "/findStadiumByStadiumId", method = RequestMethod.POST)
	public Map<String,Object> findStadiumByStadiumId(@RequestParam Long stadiumId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		Stadium stadium = service.findStadiumByStadiumId(stadiumId);
		result.put("success",true);
		result.put("data",stadium);
		return result;
	}
	@RequestMapping(value = "/findStadiumByStadiumName", method = RequestMethod.POST)
	public Map<String,Object> findStadiumByStadiumName(@RequestParam(value = "stadiumName", required = true) String stadiumName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		Stadium stadium = service.findStadiumByStadiumName(stadiumName);
		result.put("success",true);
		result.put("data",stadium);
		return result;
	}
	@RequestMapping(value = "/listActiveStadiums", method = RequestMethod.POST)
	public Map<String,Object> listActiveStadiums() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<Stadium> list = service.listActiveStadiums();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/listAllStadiums", method = RequestMethod.POST)
	public Map<String,Object> listAllStadiums() throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<Stadium> list = service.listAllStadiums();
		result.put("success",true);
		result.put("rows",list);
		return result;
	}
	@RequestMapping(value = "/searchStadiumsByFieldsByPage", method = RequestMethod.POST)
	public Map<String,Object> searchStadiumsByFieldsByPage(@RequestParam(value = "page", required = false) Integer pagenum,
		@RequestParam(value = "rows", required = false) Integer pagesize,
		@RequestParam(value = "last", required = false) String lastFlag,
		@RequestParam(value = "stadiumName", required = false) String stadiumName,
		@RequestParam(value = "active", required = false) String active,
		@RequestParam(value = "stadiumComment", required = false) Double stadiumComment,
		@RequestParam(value = "opentime", required = false) String opentime,
		@RequestParam(value = "latitude", required = false) Double latitude,
		@RequestParam(value = "longitude", required = false) Double longitude,
		@RequestParam(value = "telephone", required = false) String telephone,
		@RequestParam(value = "detail", required = false) String detail,
		@RequestParam(value = "canPark", required = false) String canPark,
		@RequestParam(value = "canSell", required = false) String canSell,
		@RequestParam(value = "canRest", required = false) String canRest,
		@RequestParam(value = "canRent", required = false) String canRent,
		@RequestParam(value = "canCard", required = false) String canCard,
		@RequestParam(value = "canBath", required = false) String canBath,
		@RequestParam(value = "canLongBuy", required = false) String canLongBuy,
		@RequestParam(value = "ballType", required = false) Integer ballType,
		@RequestParam(value = "stadiumType", required = false) Integer stadiumType,
		@RequestParam(value = "cityId", required = false) Long cityId) throws Exception{
		Stadium stadium = new Stadium();
		if (stadiumName != null) stadium.setStadiumName(stadiumName);
		Boolean activeBool = BooleanUtil.parseBoolean(active);
		if (activeBool != null) stadium.setActive(activeBool);
		if (stadiumComment != null) stadium.setStadiumComment(stadiumComment);
		if (opentime != null) stadium.setOpentime(opentime);
		if (latitude != null) stadium.setLatitude(latitude);
		if (longitude != null) stadium.setLongitude(longitude);
		if (telephone != null) stadium.setTelephone(telephone);
		if (detail != null) stadium.setDetail(detail);
		if (canPark != null) stadium.setCanPark(canPark);
		if (canSell != null) stadium.setCanSell(canSell);
		if (canRest != null) stadium.setCanRest(canRest);
		if (canRent != null) stadium.setCanRent(canRent);
		if (canCard != null) stadium.setCanCard(canCard);
		if (canBath != null) stadium.setCanBath(canBath);
		if (canLongBuy != null) stadium.setCanLongBuy(canLongBuy);
		if (ballType != null) stadium.setBallType(ballType);
		if (stadiumType != null) stadium.setStadiumType(stadiumType);
		if (cityId != null) stadium.setCityId(cityId);
		if (pagesize==null || pagesize <= 0) pagesize = 10;
		Integer recordCount = service.countSearchStadiumsByFieldsRecords(stadium);
		Integer pageCount = (int)Math.ceil((double)recordCount/pagesize);
		if (pageCount <= 1) pageCount = 1;
		if (pagenum==null || pagenum <= 1) pagenum = 1;
		if (pagenum >= pageCount) pagenum = pageCount;
		Boolean lastFlagBool = BooleanUtil.parseBoolean(lastFlag);
		if (lastFlagBool == null) lastFlagBool = false;
		if (lastFlagBool) pagenum = pageCount;
		Map<String,Object> result = new TreeMap<String,Object>();
		List<Stadium> stadiumList = service.searchStadiumsByFieldsByPage(stadium,pagesize,pagenum);
		result.put("success",true);
		result.put("rows",stadiumList);
		result.put("total",recordCount);
		result.put("page",pagenum);
		return result;
	}
	@RequestMapping(value = "/searchStadiumsByStadiumName", method = RequestMethod.POST)
	public Map<String,Object> searchStadiumsByStadiumName(@RequestParam(value = "stadiumName", required = true) String stadiumName) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		List<Stadium> stadiumList = service.searchStadiumsByStadiumName(stadiumName);
		result.put("success",true);
		result.put("rows",stadiumList);
		return result;
	}
	@RequestMapping(value = "/softDeleteAllStadiums", method = RequestMethod.POST)
	public Map<String,Object> softDeleteAllStadiums(@RequestParam(value = "ids", required = true) String ids) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteAllStadiums(ids);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/softDeleteStadium", method = RequestMethod.POST)
	public Map<String,Object> softDeleteStadium(@RequestParam Long stadiumId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.softDeleteStadium(stadiumId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleOneStadium", method = RequestMethod.POST)
	public Map<String,Object> toggleOneStadium(@RequestParam Long stadiumId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleOneStadium(stadiumId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/toggleStadium", method = RequestMethod.POST)
	public Map<String,Object> toggleStadium(@RequestParam Long stadiumId) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.toggleStadium(stadiumId);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
	@RequestMapping(value = "/updateStadium", method = RequestMethod.POST)
	public Map<String,Object> updateStadium(@RequestBody Stadium stadium) throws Exception{
		Map<String,Object> result = new TreeMap<String,Object>();
		service.updateStadium(stadium);
		result.put("success",true);
		result.put("data",null);
		return result;
	}
}

