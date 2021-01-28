package com.javaweb.apple.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.apple.service.inf.IFileManageSVC;

@Service
public class FileManageSVCImpl implements IFileManageSVC {

	@Override
	public String writeUploadFile(MultipartFile upfile,	String realPath, HttpSession ses) {
		String filePath = ""; 
		String originFile = "";
		String login = (String) ses.getAttribute("mbLogin");
		if( upfile != null && !upfile.isEmpty() ) {
			originFile = upfile.getOriginalFilename(); 
				// "abc.png"클라이언트에서 대화상자에서 선택한  파일명
				// ...<webapp>/uploads/elsa_abc.png
			// ...<webapp>/uploads/elsa/abc_20210106103710.png 계정명폴더, 년월일시분초
			//realPath += login + "_" + originFile.toLowerCase();
			String storeFilename = makeUniqueFileName(originFile);
			realPath += login + "/" + storeFilename;
			System.out.println(">> realPath2: " + realPath);
			//
			File dest = new File(realPath);
			// ...
			try {
				upfile.transferTo(dest);
				filePath = DEF_UPLOAD_DEST
						+ "/" + login + "/" + storeFilename;// db filePath
					//+ "/" + login + "_" + originFile.toLowerCase();// db filePath
				return filePath;
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
//			} catch (IllegalStateException | IOException e) {				
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		//
		return null;
	}

	// 인터페이스에 추상메소드 원형선언 없는 구현체 객체의 고유 내부 함수
	// uuid 적용 후
	private String makeUniqueFileName(String originFile) {
		// ...<webapp>/uploads/elsa
		// /UP_abc_UUID_20210106103710.png 계정명폴더, 년월일시분초
		// /UP_abc_ee10c793-acbf-47b7-9fc9-33de1e89aa7b_20210106103710.png 계정명폴더, 년월일시분초
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timePart = sdf.format(new Date());
		//
		UUID uuid = UUID.randomUUID();
		//		
		String oriFile = originFile.toLowerCase();
		int lastExtPos = oriFile.lastIndexOf('.'); // 뒤에서부터 처음 만나는 .위치
		String oriFileName = oriFile.substring(0, lastExtPos); 
				// "abc"
		String oriFileExt = oriFile.substring(lastExtPos+1); // 확장자 
				// ".png"		
			
		//return oriFileName + "_" + timePart + "." + oriFileExt;
		StringBuffer sb = new StringBuffer();
		sb.append(DEF_UPLOAD_PREFIX);
		sb.append(oriFileName + "_");
		sb.append(uuid.toString() + "_");
		sb.append(timePart + ".");
		sb.append(oriFileExt);
		
		System.out.println("uniqueName = > " + sb.toString());
		return sb.toString();
	}
	
//	private String makeUniqueFileName(String originFile) {
//		// ...<webapp>/uploads/elsa/abc_20210106103710.png 계정명폴더, 년월일시분초
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//		String timePart = sdf.format(new Date());
//		//		
//		String oriFile = originFile.toLowerCase();
//		int lastExtPos = oriFile.lastIndexOf('.'); // 뒤에서부터 처음 만나는 .위치
//		String oriFileName = oriFile.substring(0, lastExtPos); 
//				// "abc"
//		String oriFileExt = oriFile.substring(lastExtPos+1); // 확장자 
//				// ".png"		
//			
//		return oriFileName + "_" + timePart + "." + oriFileExt;
//	}

	// 사용자 전용 고유 업로드 폴더 생성
	@Override
	public boolean makeMemberDirectory(HttpSession ses, String login) {
		String realPath = ses
				.getServletContext()
				.getRealPath(
				IFileManageSVC.DEF_UPLOAD_DEST
				+"/" + login);
		System.out.println("member join RealPath: " + realPath);
		
		try {
			File userFolder = new File(realPath);			
			//userFolder.canWrite();
			return userFolder.mkdir();
		} catch (SecurityException e) {
			System.out.println(login + "폴더 생성 에러: 권한 문제? ");
		} catch (Exception e) {
			System.out.println(login + "폴더 생성 에러: error ");
		}
		return false;
	}

	// 다수 개 파일 업로드하고 해당 경로명을 모두 SEP로 붙여서 리턴하는 함수
	@Override
	public String writeMultipleUploadFiles(
		List<MultipartFile> upfiles, String realPath, HttpSession ses) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < upfiles.size(); i++) {
			MultipartFile upfile = upfiles.get(i);
			//
			String fp = writeUploadFile(upfile, realPath, ses);
			if( fp != null ) {
				sb.append(fp + 
					( i < upfiles.size()-1 ? DEF_MUTLI_SEPARATOR: "") );
			} else {
				System.out.println(i+"번 파일 저장 실패 : " + 
								upfile.getOriginalFilename());
			}			
		}		
		
		return sb.toString();
	}
	
	// 다수 개 파일 업로드하고 해당 경로명을 모두 SEP로 붙여서 리턴하는 함수
	@Override
	public HashMap<String,Object> writeMultipleUploadFilesWithInfo(
		List<MultipartFile> upfiles, String realPath, HttpSession ses) {
		StringBuffer sb = new StringBuffer();
		long totalByte = 0L;
		for (int i = 0; i < upfiles.size(); i++) {
			MultipartFile upfile = upfiles.get(i);
			long fileSize = upfile.getSize();
			totalByte += fileSize;
			//
			String fp = writeUploadFile(upfile, realPath, ses);
			if( fp != null ) {
				sb.append(fp + 
					( i < upfiles.size()-1 ? DEF_MUTLI_SEPARATOR: "") );
			} else {
				System.out.println(i+"번 파일 저장 실패 : " + 
								upfile.getOriginalFilename());
			}			
		}		
		// info
		double totalMB = totalByte / (1024.0*1024.0); // Mega Bytes
		int totalFiles = upfiles.size();
		String multiFilePaths = sb.toString();
		// ... 다수 정보를 스키마VO 없이 묶어서 리턴하기.. map사용
		HashMap<String,Object> rMap = new HashMap<>();
		rMap.put("totalMB", totalMB); // autoBoxing new Double(totalMB)
		rMap.put("totalFiles", new Integer(totalFiles));
		rMap.put("multiFilePaths", multiFilePaths);
		//return (String)rMap.get("multiFilePaths");
		return rMap;
	}

}
