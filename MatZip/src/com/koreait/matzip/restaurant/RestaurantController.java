package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantVO;
import com.koreait.matzip.vo.UserVO;

public class RestaurantController {
	private RestaurantService service;

	public RestaurantController(){
		service = new RestaurantService();
	}
	
	public String restMap(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "지도보기");
		request.setAttribute(Const.VIEW, "restaurant/restMap");
		return ViewRef.TEMP_MENU_TEMP ;
	}		
	public String rsetReg(HttpServletRequest request) {
		final int I_M=1;
		request.setAttribute("categoryList", CommonDAO.selCodeList(I_M));
		request.setAttribute(Const.TITLE, "가게등록");
		request.setAttribute(Const.VIEW, "restaurant/restReg");
		return ViewRef.TEMP_MENU_TEMP;
	}
	
	public String restRegProc(HttpServletRequest request) {
		String nm = request.getParameter("nm");
		String addr = request.getParameter("addr");
		String latStr= request.getParameter("lat");
		String lngStr=request.getParameter("lng");
		String cd_categoryStr=request.getParameter("cd_category");
		
		
		double lat=Double.parseDouble(latStr);
		double lng=Double.parseDouble(lngStr);
		int cd_category=Integer.parseInt(cd_categoryStr);
		UserVO vo=SecurityUtils.getLoginUser(request);
		
		RestaurantVO param= new RestaurantVO();
		param.setNm(nm);
		param.setAddr(addr);
		param.setLat(lat);
		param.setLng(lng);
		param.setCd_category(cd_category);
		param.setI_user(vo.getI_user());
		
		int result=service.restReg(param);
		
		return "redirect:/restaurant/restMap";
		
	}
	
	public String ajaxGetList(HttpServletRequest request) {
				return "";
	}

}
