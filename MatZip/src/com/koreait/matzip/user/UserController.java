package com.koreait.matzip.user;

import javax.servlet.http.HttpServletRequest;

import com.koreait.matzip.Const;
import com.koreait.matzip.ViewRef;
import com.koreait.matzip.vo.UserVO;
//넘어온 값을 담아서 보냄

// /user/login 
public class UserController {
	private UserService service;

	public UserController() {
		service = new UserService();
	}

	public String login(HttpServletRequest request) {

		String error = request.getParameter("error");

		if (error != null) {

			switch (error) {
			case "2":
				request.setAttribute("msg", "아이디를 확인하세요.");
				break;

			default:
				request.setAttribute("msg", "비밀번호를 확인하세요.");
				break;
			}
		}

		request.setAttribute(Const.TITLE, "로그인");
		request.setAttribute(Const.VIEW, "user/login"); // 템플릿에서 어떤파일을 열까?
		return ViewRef.TEMP_DEFAULT;// 어떤 템플릿을 열까?
	}

	public String join(HttpServletRequest request) {
		request.setAttribute(Const.TITLE, "회원가입");
		request.setAttribute(Const.VIEW, "user/join"); // 템플릿에서 어떤파일을 열까?
		return ViewRef.TEMP_DEFAULT;
	}

	public String joinProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");// 암호화
		String nm = request.getParameter("nm");

		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);
		param.setNm(nm);

		int result = service.join(param);

		return "redirect:/user/login";
	}

	public String loginProc(HttpServletRequest request) {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");

		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(user_pw);

		int result = service.login(param);

		if (result == 1) {
			return "redirect:/view/resMap";
		} else {
			return "redirect:/user/login?error=" + result;
		}

	}
	
	public String ajaxIdChk(HttpServletRequest request) {
		String user_id =request.getParameter("user_id");
		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw("");
		int result= service.login(param) ;

		//2(아이디 없음) or 3(아이디 사용)
		return String.format("ajax:{\"result\": %s}", result);
	}
	

}
