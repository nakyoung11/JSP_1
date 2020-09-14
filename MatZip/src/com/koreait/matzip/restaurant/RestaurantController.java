package com.koreait.matzip.restaurant;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.CommonDAO;
import com.koreait.matzip.Const;
import com.koreait.matzip.SecurityUtils;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.RestaurantDomain;
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
	
	public String restDetail(HttpServletRequest request) {
		String i_restStr = request.getParameter("i_rest");
		int i_rest=Integer.parseInt(i_restStr);
		RestaurantDomain param= new RestaurantDomain();
		param.setI_rest(i_rest);
		
		
	
		request.setAttribute("data", service.selRsetDetail(param));
		request.setAttribute(Const.TITLE, "상세");
		request.setAttribute(Const.VIEW, "restaurant/restDetail");
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
	
	
	public String addRecMenusProc(HttpServletRequest request) {
		String i_restStr=request.getParameter("i_rset");
		int i_rest=Integer.parseInt(i_restStr);
		
		
		String[] menu_nmArr=request.getParameterValues("menu_nm");
		String[] menu_priceArr=request.getParameterValues("menu_price");
		
		for(int i=0; i<menu_nmArr.length; i++) {
			System.out.println();
		}
		
		return "redirect:/restaurant/restDetail?";
	}
	
	public String ajaxGetList(HttpServletRequest request) {
		return "ajax:" + service.getRestList();
	}

}
