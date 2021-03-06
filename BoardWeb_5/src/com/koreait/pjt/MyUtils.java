package com.koreait.pjt;

import java.io.IOException;
import java.security.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.vo.UserVO;


public class MyUtils {
	//true: 로그인이 안됨, false: 로그인 된 상퇴
	public static boolean isLogout(HttpServletRequest request) throws IOException {
		
		HttpSession hs=request.getSession();
		if(null == hs.getAttribute(Const.LOGIN_USER)) {
			return true;
		}
		return false;
	}
	
	public static int getIntParameter(HttpServletRequest request, String keyNm) {
		return parseStrToInt(request.getParameter(keyNm));
	//무조건 숫자만 넘어와야 0이 아닌값이 뜸. 
	}
		
	
	public static int parseStrToInt(String str) {
		return parseStrToInt(str, 0);
	}
	//오버로딩: 파라미터를 다르게함
	public static int parseStrToInt(String str, int defNo) {
		try {
			return Integer.parseInt(str);
		} catch(Exception e) {
			return defNo;
		}
	}
	

	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserVO)hs.getAttribute(Const.LOGIN_USER);
	}
	
	
	//암호화
	public static String encryptString(String str) {
		   String sha = "";

	       try{
	          MessageDigest sh = MessageDigest.getInstance("SHA-256");
	          sh.update(str.getBytes());
	          byte byteData[] = sh.digest();
	          StringBuffer sb = new StringBuffer();
	                                //문자열 합치기  속도를 올려줌
	          for(int i = 0 ; i < byteData.length ; i++){
	              sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
	          }

	          sha = sb.toString();

	      }catch(NoSuchAlgorithmException e){
	          //e.printStackTrace();
	          System.out.println("Encrypt Error - NoSuchAlgorithmException");
	          sha = null;
	      }

	      return sha;
	}

}
