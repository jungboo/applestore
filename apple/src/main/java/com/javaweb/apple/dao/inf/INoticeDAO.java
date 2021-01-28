package com.javaweb.apple.dao.inf;

import java.util.List;

import com.javaweb.apple.model.vo.NoticeVO;

public interface INoticeDAO {
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
	boolean updateNotice(NoticeVO nt);
// 공지사항을 검색 할 수 있다.
//	ad_nt_search.ap
// all으로 조회
	List<NoticeVO> selectNoticesByALL(String keyword, int offset , int limit);
	int checkAllNoticesCount(String keyword);
// postTitle로 조회
	List<NoticeVO> selectNoticesByPostTitle(String postTitle, int offset , int limit);
	int checkPostTitleNoticesCount(String keyword);
//	postContents로 조회
	List<NoticeVO> selectNoticesByPostContents(String postContents, int offset , int limit);
	int checkPostContentsNoticesCount(String keyword);
//- 공지사항을 삭제 할 수 있다.
	boolean deleteNotice(int ntId);
//	보류
}
