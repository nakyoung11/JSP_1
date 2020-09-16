package com.koreait.matzip.restaurant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.koreait.matzip.CommonUtils;
import com.koreait.matzip.FileUtils;
import com.koreait.matzip.vo.RestaurantDomain;
import com.koreait.matzip.vo.RestaurantRecommendMenuVO;
import com.koreait.matzip.vo.RestaurantVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


//로직처리
public class RestaurantService {
	private RestaurantDAO dao;

	public RestaurantService() {
		dao = new RestaurantDAO();
	}

	public int restReg(RestaurantVO param) {
		return dao.insrestRegProc(param);
	}

	public RestaurantDomain getRest(RestaurantVO param) {
		RestaurantDomain vo = dao.selRest(param);
		return vo;
	}
	


	public String getRestList() {
		List<RestaurantDomain> list = dao.selRestList();
		Gson gson = new Gson();
		return gson.toJson(list);
	}

	public int addMenus(HttpServletRequest request) { // 메뉴
		int i_rest = CommonUtils.getIntParameter("i_rest", request);
		String savePath = request.getServletContext().getRealPath("/res/img/restaurant");
		String tempPath = savePath +"/"+ i_rest+ "/menu";
		FileUtils.makeFolder(tempPath);
		List<RestaurantRecommendMenuVO> list = new ArrayList();
		try {
			for (Part part : request.getParts()) {
				String fileName = part.getSubmittedFileName();	
				System.out.println(fileName);
				if(fileName !=null) {
					String ext=FileUtils.getExt(fileName);
					String saveFileNm = UUID.randomUUID()+ext;
					RestaurantRecommendMenuVO vo=new RestaurantRecommendMenuVO();
					vo.setI_rest(i_rest);
					vo.setMenu_pic(saveFileNm);
					part.write(tempPath+"/"+saveFileNm); //파일저장
					list.add(vo);
				}
			}
			
		}catch(Exception e){ e.printStackTrace();}
		

		if (list != null) {
			for (RestaurantRecommendMenuVO vo : list) {
				dao.insMenu(vo);
			}
		}

		return i_rest;
	}


	public int addRecMenus(HttpServletRequest request) {
		String savePath = request.getServletContext().getRealPath("/res/img/restaurant");
		/*
		 * context내의 절대주소값(서버(톰캣)이 돌아가고 있는 )이 넘어감 그후에 +savePath+/temp "/"를 사용하면 모든
		 * 운영체제에서 사용가능
		 */
		System.out.println(savePath);
		String tempPath = savePath + "/temp";// 임시로 이미지를 저장~

		FileUtils.makeFolder(tempPath); // 폴더가 없다면만들기

		int maxFileSize = 10_485_760;
		MultipartRequest multi = null;
		int i_rest = 0;
		String[] menu_nmArr = null; // 0번째에 값이 먼저 들어감
		String[] menu_priceArr = null; // 0번째에 들어감.
		List<RestaurantRecommendMenuVO> list = null;
		try {
			multi = new MultipartRequest(request, tempPath, maxFileSize, "UTF-8", new DefaultFileRenamePolicy());

			i_rest = CommonUtils.getIntParameter("i_rest", multi); //
			System.out.println("1: " + i_rest);
			menu_nmArr = multi.getParameterValues("menu_nm");

			menu_priceArr = multi.getParameterValues("menu_price");

			if (menu_nmArr == null || menu_priceArr == null) {
				System.out.println(menu_nmArr[0]);
				System.out.println(menu_priceArr[0]);
				return i_rest;
			}

			list = new ArrayList();
			for (int i = 0; i < menu_nmArr.length; i++) {
				RestaurantRecommendMenuVO vo = new RestaurantRecommendMenuVO();
				vo.setMenu_nm(menu_nmArr[i]);
				vo.setMenu_price(CommonUtils.parseStringToInt(menu_priceArr[i]));
				vo.setI_rest(i_rest);
				list.add(vo);

			}

			String targetPath = savePath + "/" + i_rest;
			FileUtils.makeFolder(targetPath);

			String originFileNM = "";
			String saveFileNm = "";
			Enumeration files = multi.getFileNames();
			while (files.hasMoreElements()) { // 파일이 있는 만큼 반복
				// 이름이 같다면 1번만 돌아서.. 이름이 달라야해
				String key = (String) files.nextElement();
				System.out.println("key:" + key);

				originFileNM = multi.getFilesystemName(key);

				if (originFileNM != null) { // 파일을 선택하지 않았다면 null!
					String ext = FileUtils.getExt(originFileNM);
					saveFileNm = UUID.randomUUID() + ext;

					File oldFile = new File(tempPath + "/" + originFileNM);
					File newFile = new File(targetPath + "/" + saveFileNm);
					oldFile.renameTo(newFile);
					// ()안으로 파일을 이동시키면서 이름까지 바꿈!

					int idx = CommonUtils.parseStringToInt(key.substring(key.lastIndexOf("_") + 1));
					// 키값의 번호찾기!
					System.out.println(idx);
					RestaurantRecommendMenuVO vo = list.get(idx);
					vo.setMenu_pic(saveFileNm);

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (list != null) {
			for (RestaurantRecommendMenuVO vo : list) {
				dao.insRecommendMenu(vo);
			}
		}

		return i_rest;

	}

	public List<RestaurantRecommendMenuVO> getMenuList(int i_rest) {
		return dao.selMenuList(i_rest);
	}
	public List<RestaurantRecommendMenuVO> getRecommendMenuList(int i_rest) {
		return dao.selRecommendMenuList(i_rest);
	}

	public int delRecMenu(RestaurantRecommendMenuVO param) {
		return dao.delRecommendMenu(param);
	}
}
