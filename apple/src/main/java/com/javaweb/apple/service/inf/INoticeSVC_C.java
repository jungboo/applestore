package com.javaweb.apple.service.inf;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.NoticeVO;

public interface INoticeSVC_C {

	// - 게시글을 상세보기 할 수 있다.
	NoticeVO selectOneNotice(int rId);

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬)
	List<NoticeVO> selectAllNotices();

	List<NoticeVO> selectAllNotices(boolean order);

	List<NoticeVO> selectAllNotices(int page);

	List<NoticeVO> selectAllNotice(int offset, int limit); // pg

	List<NoticeVO> selectAllNotice(int offset, int limit, boolean order);

	List<NoticeVO> selectAllNotice(int offset, int limit, boolean order, Date startDate, Date endDate);
	
// - 게시글 검색할 수 있다.
	List<NoticeVO> searchNotice(String keyword, String target, int pg);


	public static final int PAGE_SIZE = 10;

	// 페이지당 표시 최대 게시글 수 (limit) == 블록사이즈수
	int checkMaxPageNumber(); // 최대 페이지 수 전체 게시글 개수 기준..

	public static final int SEARCH_PAGE_SIZE = 5; // 검색 결과 페이지당 게시글 수

	Map<String, Integer> checkMaxPageNumber(String target, String keyword);
	// 최대 페이지 수 -검색한 한정된 게시글 개수 기준..
	
	
}
