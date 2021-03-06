package com.koreait.matzip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.Const;
import com.koreait.matzip.FileUtils;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//파라미터 받고, 어디로 가야할지 정해줌. 
public class RestaurantController {
	private RestaurantService service;

	public RestaurantController() {
		service = new RestaurantService();
	}

	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "지도보기");
		request.setAttribute(Const.VIEW, "restaurant/restMap");
		return ViewRef.TEMP_MENU_TEMP;
	}

	public String rsetReg(HttpServletRequest request) {
		final int I_M = 1;
		request.setAttribute("categoryList", CommonDAO.selCodeList(I_M));
		request.setAttribute(Const.TITLE, "가게등록");
		request.setAttribute(Const.VIEW, "restaurant/restReg");
		return ViewRef.TEMP_MENU_TEMP;
	}

	public String restDetail(HttpServletRequest request) {
		String i_restStr = request.getParameter("i_rest");
		int i_rest = Integer.parseInt(i_restStr);
		RestaurantVO param = new RestaurantVO();
		param.setI_rest(i_rest);
		
		
		request.setAttribute("css", new String[]{"restaurant"});
		request.setAttribute("menuList", service.getMenuList(i_rest));
		request.setAttribute("recommendMenuList", service.getRecommendMenuList(i_rest));
		request.setAttribute("data", service.getRest(param));
		request.setAttribute(Const.TITLE, "상세");
		request.setAttribute(Const.VIEW, "restaurant/restDetail");
		return ViewRef.TEMP_MENU_TEMP;
	}

	public String restRegProc(HttpServletRequest request) {
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		String latStr = request.getParameter("lat");
		String lngStr = request.getParameter("lng");
		String cd_categoryStr = request.getParameter("cd_category");

		double lat = Double.parseDouble(latStr);
		double lng = Double.parseDouble(lngStr);
		int cd_category = Integer.parseInt(cd_categoryStr);
		UserVO vo = SecurityUtils.getLoginUser(request);

		RestaurantVO param = new RestaurantVO();
		param.setNm(nm);
		param.setAddr(addr);
		param.setLat(lat);
		param.setLng(lng);
		param.setCd_category(cd_category);
		param.setI_user(vo.getI_user());

		int result = service.restReg(param);

		return "redirect:/restaurant/restMap";

	}
	public String addMenusProc(HttpServletRequest request) { //매뉴
		
		int i_rest = service.addMenus(request);
		return "redirect:/restaurant/restDetail?i_rest="+i_rest;
	}
	public String addRecMenusProc(HttpServletRequest request) {//추천매뉴
		
		int i_rest = service.addRecMenus(request);
		System.out.println("2: " +i_rest);
		return "redirect:/restaurant/restDetail?i_rest="+i_rest;
	}

	public String ajaxGetList(HttpServletRequest request) {
		return "ajax:" + service.getRestList();
	}
	/*
	 * public String ajaxDelMenu(HttpServletRequest request) { int
	 * i_rest=CommonUtils.getIntParameter("i_rest", request); int seq=
	 * CommonUtils.getIntParameter("seq", request);
	 * 
	 * 
	 * RestaurantRecommendMenuVO param= new RestaurantRecommendMenuVO();
	 * param.setI_rest(i_rest); param.setSeq(seq);
	 * param.setI_user(SecurityUtils.getLoginUserPk(request)); int
	 * result=service.delMenu(param); return "ajax:" + result;
	 * 
	 * }
	 */
	
	
	public String ajaxDelRecMenu(HttpServletRequest request) {
		int i_rest=CommonUtils.getIntParameter("i_rest", request);
		int seq= CommonUtils.getIntParameter("seq", request);
		
		RestaurantRecommendMenuVO param= new RestaurantRecommendMenuVO();
		param.setI_rest(i_rest);
		param.setSeq(seq);
		param.setI_user(SecurityUtils.getLoginUserPk(request));
		
		int result=service.delRecMenu(param);
		
		return "ajax:" + result;
		
				
	}

}
