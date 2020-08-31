package com.koreait.user;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.vo.UserVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class profile
 */
@WebServlet("/profile")
public class profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	//프로필화면(나의 프로필 이미지, 이미지 변경가능화면)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser=MyUtils.getLoginUser(request);
		request.setAttribute("data", UserDAO.selUser(loginUser.getI_user()));
		ViewResolver.forward("user/profile", request, response);
		                     
	}

	//이미지 변경처리(파일 변경때문에 무조건  포스트 방식 쿼리 스트링의 한계가 있음으로!)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVO loginUser=MyUtils.getLoginUser(request);
		
	
		
		
		String savePath=getServletContext().getRealPath("img")+"/user/"+loginUser.getI_user();
		                //어플리케이션을 얻고  
		// saveePath= 절대경로/img/user/5
		System.out.println("savePath:"+savePath); //저장경로
		
		//폴더 만들기
		File directory =new File(savePath);
		 String saveFileNm=null;
		
		//만약 폴더(디렉토리)가 없다면 폴더 생성 (처음 업로드하는사람의 폴더를 생성해줌)
		if(!directory.exists()) {
			directory.mkdirs();
			//폴더를 여러개 만든다면 mkdirs로하는데 무조건 하면됨. 
		}
		
		int maxFileSize=10_485_760; //1024*1024*10(10mb) // 최대 파일 사이즈 크기
		String fileNm=""; // 정책에 의해 만들어지파일 이름
				
		try {
			MultipartRequest mr= new MultipartRequest(request, savePath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());
     		Enumeration files=mr.getFileNames();
			
			if(files.hasMoreElements()) {   
			String key=(String)files.nextElement();
			fileNm=mr.getFilesystemName(key);
			
			System.out.println("key: "+ key);
			System.out.println("fileNm: "+fileNm); 
			//파일이 선택되지 않았다면 null
			int pos = fileNm.lastIndexOf( "." ); //인덱스 값으로 들어감 
			String fileExt = fileNm.substring( pos);
			 //substring(5)-> 5개 뒤로~  substring(5 ,8) 시작값~ 끝값 
			System.out.println("파일확장자는: "+fileExt);
			 
			 saveFileNm=UUID.randomUUID() + fileExt;//UUID를 통해겹치기 어려운 이름으로 변경
			File oldFile=new File(savePath+"/"+fileNm); //방금 업로드한 파일명
			
			//원하는 파일명으로 변환
      	     File newFile=new File(savePath+"/"+saveFileNm);			                                  
			 oldFile.renameTo(newFile);		
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if(saveFileNm!=null) { //null이 아닐때만 DB에 저장
			UserVO param=new UserVO();
			param.setProfile_img(saveFileNm);
			param.setI_user(loginUser.getI_user());
			
			UserDAO.updUser(param);
		}
		
		
		response.sendRedirect("/profile");
		
	}

}
