package com.koreait.matzip;

import javax.servlet.http.HttpServletRequest;

public class LoginChkInerceptor {

	public static String routerChk(HttpServletRequest request) {
		//null이 넘어오면 아무일이 없어요. 
		//문자열이 리턴되면 그 문자열로 sendRediect@@
		String[] chkUesrUriArr = { "login", "loginProc", "join", "joinProc", "ajaxIdChk"};
		//2차 주소값!
		boolean isLogout = SecurityUtils.isLogout(request);
		String[] targetUri = request.getRequestURI().split("/");

		if (targetUri.length < 3) {
			return null; 
		}

		if (isLogout) {// 로그아웃
			if (targetUri[1].equals(ViewRef.URI_USER)) {
				for (String uri : chkUesrUriArr) {
					if (uri.equals(targetUri[2])) {
						return null; //아무일이 없어요~~원하는 페이지로 가요. 
					}
				}
			}
			return "/user/login";

		} else { // 로그인
			if (targetUri[1].equals(ViewRef.URI_USER)) {
				for (String uri : chkUesrUriArr) {
					if (uri.equals(targetUri[2])) {
						return "/restaurant/restMap";
					}
				}
			}

			return null;
		}
	}
}
