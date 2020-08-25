package com.koreait.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.vo.UserLoginHistoryVO;
import com.koreait.vo.UserVO;

@WebServlet("/login")
public class LoginSer extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		if(MyUtils.isLogout(request)==false) {		
			response.sendRedirect("/board/list");
			return;
		}		
		ViewResolver.forward("user/login", request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String encrypt_pw = MyUtils.encryptString(user_pw);

		UserVO param = new UserVO();
		param.setUser_id(user_id);
		param.setUser_pw(encrypt_pw);

		int result = UserDAO.login(param);

//	System.out.println(user_id);
//	System.out.println(encrypt_pw);
//	System.out.println(result);
		
		
		
		if (result != 1) {
			String msg=null;
			switch(result) {
			case 2:
				msg="비밀번호를 확인해 주세요";
				break;
				
			case 3:
				msg="아이디를 확인해 주세요";
				break;
			default:
				msg="에러가 발생하였습니다";
			}
			request.setAttribute("user_id", user_id);
			request.setAttribute("msg",msg);
			doGet(request, response);
			return;
		}
		

		//request.getDateHeader("User-Agent");
		
		
		
			
//			if (result == 2) {
//				request.setAttribute("msg", "비밀번호가 틀렸습니다.");
//				request.setAttribute("data", param);
//				doGet(request, response);
//				return;
//			} else if (result == 3) {
//				request.setAttribute("msg", "아이디가 없습니다.");
//				doGet(request, response);
//				return;
//			} else {
//				request.setAttribute("msg", "관리자에게 문의하세요.");
//			}

          //user agent
				 
		String agent = request.getHeader("User-Agent");
		System.out.println("agent: " + agent);
		String os = getOs(agent);
		String browser = getBrowser(agent);
		String ip_addr = request.getRemoteAddr();

		UserLoginHistoryVO ulhVO = new UserLoginHistoryVO();
		ulhVO.setI_user(param.getI_user());
		ulhVO.setOs(os);
		ulhVO.setIp_addr(ip_addr);
		ulhVO.setBrowser(browser);

		
		int result2= UserDAO.insUserLoginHistory(ulhVO);
		 
		
		
		    HttpSession hs = request.getSession();
			hs.setAttribute(Const.LOGIN_USER, param);
			
			response.sendRedirect("/board/list");	
			System.out.println("로그인 성공!");
		}
	
	private String getBrowser(String agent) {
		if(agent.toLowerCase().contains("msie")) {
			return "ie";
		} else if(agent.toLowerCase().contains("safari")) {
			return "safari";
		} else if(agent.toLowerCase().contains("chrome")) {
			return "chrome";
		}

		return "";
	}
	
	
		private String getOs(String agent) {
		if(agent.toLowerCase().contains("mac")) {
			return "mac";
		}else if(agent.toLowerCase().contains("windows")) {
			return "win";
		}else if(agent.toLowerCase().contains("x11")) {
			return "unix";
		}else if(agent.toLowerCase().contains("android")) {
			return "android";
		}else if(agent.toLowerCase().contains("iphone")) {
			return "IOS";
		}
		return "";
	}

	}

