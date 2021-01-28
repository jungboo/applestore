package com.javaweb.apple.service.inf;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface IFileManageSVC {
	// 기본 파일 업로드 대상 폴더
	String DEF_UPLOAD_DEST = "/uploads"; 
	String DEF_UPLOAD_PREFIX = "UP_";
	String DEF_MUTLI_SEPARATOR = "|"; 
	// 가입 시 사용자 계정명으로 고유 업로드 전용 폴더 생성하기
	boolean makeMemberDirectory(HttpSession ses, String login);

	// 멀티파트 파일데이터, 파일업로드 기준물리폴더, 세션을 받아서
	// 실제 물리경로에 정책을 따라 파일을 저장완료후 파일경로명을 리턴.
	String writeUploadFile(MultipartFile upfile,  // 단일 파일
			String realPath, HttpSession ses);

	// 다수개 다중 파일
	String writeMultipleUploadFiles(List<MultipartFile> upfiles, 
			String realPath, HttpSession ses);
	
	// 다수개 다중 파일 with info
	HashMap<String,Object> writeMultipleUploadFilesWithInfo(List<MultipartFile> upfiles, 
				String realPath, HttpSession ses);
}
