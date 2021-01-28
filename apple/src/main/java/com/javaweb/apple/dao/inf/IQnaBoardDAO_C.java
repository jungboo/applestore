package com.javaweb.apple.dao.inf;

import java.sql.Date;
import java.util.List;

import com.javaweb.apple.model.vo.QnaBoardVO;
import com.javaweb.apple.model.vo.virtual.QnaBoardRowVO;

public interface IQnaBoardDAO_C {

	// - 회원이 신규 게시글을 등록 할 수 있다. (세션, 파일 업로드-사진/첨부)
//	article_new_form.my ; get 
//	article_add.my ; post proc dao
	boolean insertNewQna(QnaBoardVO qb);

	boolean insertNewQna(String postTitle, int postId, String postContents);

	int insertNewQnaReturnKey(QnaBoardVO qb);

	// - 게시글을 상세보기 할 수 있다.
//	article_show.my?atId=x  ; get proc, dao
	QnaBoardVO selectOneQna(int qId);

	// - 회원이 자신의 게시글 편집/갱신 할 수 있다. (권한/롤?)
//	article_edit_form.my?atId=x ; get proc dao
//	article_update.my?atId=x ; post proc dao
	boolean updateQna(QnaBoardVO qb);

	boolean increaseReadCount(int qId); // 조회수 증가

	// - 회원이 자신의 게시글을 삭제 할 수 있다.
//	article_remove.my?atId=x; get proc dao
	boolean deleteQna(int qId);

	// 전체 조회, 검색 조회, + 페이지, 날자범위...
	int checkAllQnaCount();

	int checkTitleQnaCount(String target, String keyword);

	int checkContentsQnaCount(String target, String keyword);

	int checkMembersQnaCount(String target, String keyword);

	int checkAllQnaCount(String target, String keyword); // 검색 조회

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬, 태깅?) - 해시태그
//	article_list.my ; get proc dao
	List<QnaBoardVO> selectAllQnas();

//	article_list.my?pg=x&order=z ; get proc dao + order?
	List<QnaBoardVO> selectAllQnas(boolean order);

	List<QnaBoardVO> selectAllQna(int offset, int limit); // pg

	List<QnaBoardVO> selectAllQna(int offset, int limit, boolean order);

	List<QnaBoardVO> selectAllQna(int offset, int limit, boolean order, Date startDate, Date endDate);

	// - 게시글 검색할 수 있다.
//	article_search_form.my ; get form 
//	article_search.my ; post dao ... pn, order..
//	List<ArticleVO> searchArticle(String keyword);
	// QnaBoardRowVO
	List<QnaBoardRowVO> searchQnaForAll(String keyword, int offset, int limit);

	List<QnaBoardRowVO> searchQnaForTitle(String keyword, int offset, int limit);

	List<QnaBoardRowVO> searchQnaForContent(String keyword, int offset, int limit);

	List<QnaBoardRowVO> searchQnaForPostMember(String keyword, int offset, int limit);
//	List<ArticleVO> searchArticle(String keyword, int page);

	// 마이페이지 리뷰 추가
	List<QnaBoardVO> selectAllQnaForOneMember(int postId);

	List<QnaBoardVO> selectAllQnaForOneMember(int offset, int limit, int postId);

	int checkAllArticleCount(int maxPageOneMember);
	//
}