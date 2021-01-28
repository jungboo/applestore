package com.javaweb.apple.dao.inf;

import java.sql.Date;
import java.util.List;

import com.javaweb.apple.model.vo.NoticeVO;

public interface INoticeDAO_C {

	// - 게시글을 상세보기 할 수 있다.
	NoticeVO selectOneNotice(int rId);

	// 전체 조회, 검색 조회, + 페이지, 날자범위...
	int checkAllNoticeCount();
	int checkTitleNoticeCount (String target, String keyword);
	int checkContentsNoticeCount (String target, String keyword);	
	int checkAllNoticeCount(String target, String keyword); // 검색 조회

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬, 태깅?) - 해시태그
	List<NoticeVO> selectAllNotices();

	List<NoticeVO> selectAllNotices(boolean order);

	List<NoticeVO> selectAllNotice(int offset, int limit); // pg

	List<NoticeVO> selectAllNotice(int offset, int limit, boolean order);

	List<NoticeVO> selectAllNotice(int offset, int limit, boolean order, Date startDate, Date endDate);

	// - 게시글 검색할 수 있다.
//	article_search_form.my ; get form 
//	article_search.my ; post dao ... pn, order..
//	List<ArticleVO> searchArticle(String keyword);
	List<NoticeVO> searchNoticeForAll(String keyword, int offset, int limit);
	List<NoticeVO> searchNoticeForTitle(String keyword, int offset, int limit);
	List<NoticeVO> searchNoticeForContent(String keyword, int offset, int limit);
}