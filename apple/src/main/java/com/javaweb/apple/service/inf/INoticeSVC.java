package com.javaweb.apple.service.inf;

import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.NoticeVO;

public interface INoticeSVC {
	
	public static final int SEARCH_PAGE_SIZE = 3;
	//- 공지사항을 작성 할 수 있다.
//	ad_nt_form.ap
//	ad_nt_proc.ap
	boolean insertNewNotice(NoticeVO nt);
	boolean	insertNewNotice(String postTitle, String postContents);
//- 공지사항 리스트를 조회 할 수 있다.
//	nt_list.ap
	List<NoticeVO> selectAllNotices();
//- 공지사항을 상세보기 할 수 있다.
//	ad_nt_show_proc.ap?ntId=x
	NoticeVO selectOneNotice(int ntId);
//- 공지사항을 수정 할 수 있다.
//	ad_nt_update_form.ap
//	ad_nt_update_proc.ap
	int updateNotice(NoticeVO nt);
//- 공지사항을 검색 할 수 있다.
//	ad_nt_search.ap
	List<NoticeVO> searchNotice(String keyword, String target, int pg);
	
	Map<String,Integer> checkMaxPageNumber(String keyword, String target);
//- 공지사항을 삭제 할 수 있다.
	boolean deleteNotice(int ntId);
//	보류
}
