package com.javaweb.apple.dao.inf;

import java.sql.Date;
import java.util.List;

import com.javaweb.apple.model.vo.ReviewBoardVO;
import com.javaweb.apple.model.vo.virtual.ReviewBoardRowVO;

public interface IReviewBoardDAO_C {

	// - 회원이 신규 게시글을 등록 할 수 있다. (세션, 파일 업로드-사진/첨부)
//	article_new_form.my ; get 
//	article_add.my ; post proc dao
	boolean insertNewReview(ReviewBoardVO rb);

	boolean insertNewReview(String postTitle, int postId, String postContents, float productScore, String productImage);

	int insertNewReviewReturnKey(ReviewBoardVO rb);

	// - 게시글을 상세보기 할 수 있다.
//	article_show.my?atId=x  ; get proc, dao
	ReviewBoardVO selectOneReview(int rId);

	// - 회원이 자신의 게시글 편집/갱신 할 수 있다. (권한/롤?)
//	article_edit_form.my?atId=x ; get proc dao
//	article_update.my?atId=x ; post proc dao
	boolean updateReview(ReviewBoardVO at);

	boolean increaseReadCount(int rId); // 조회수 증가

	// - 회원이 자신의 게시글을 삭제 할 수 있다.
//	article_remove.my?atId=x; get proc dao
	boolean deleteReview(int rId);

	// 전체 조회, 검색 조회, + 페이지, 날자범위...
	int checkAllReviewCount();

	int checkTitleReviewCount(String target, String keyword);

	int checkContentsReviewCount(String target, String keyword);

	int checkMembersReviewCount(String target, String keyword);

	int checkAllReviewCount(String target, String keyword); // 검색 조회

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬, 태깅?) - 해시태그
//	article_list.my ; get proc dao
	List<ReviewBoardVO> selectAllReviews();

//	article_list.my?pg=x&order=z ; get proc dao + order?
	List<ReviewBoardVO> selectAllReview(int offset, int limit); // pg

	// - 게시글 검색할 수 있다.
//	article_search_form.my ; get form 
//	article_search.my ; post dao ... pn, order..
//	List<ArticleVO> searchArticle(String keyword);
//	List<ReviewBoardVO> searchReviewForTitle(String keyword);
//	List<ReviewBoardVO> searchReviewForContent(String keyword);
//	List<ReviewBoardVO> searchReviewForPostMember(String keyword);
//	List<ReviewBoardVO> searchReviewForAll(String keyword);
//	List<ArticleVO> searchArticle(String keyword, int page);
	//
	List<ReviewBoardRowVO> searchReviewForAll(String keyword, int offset, int limit);

	List<ReviewBoardRowVO> searchReviewForTitle(String keyword, int offset, int limit);

	List<ReviewBoardRowVO> searchReviewForContent(String keyword, int offset, int limit);

	List<ReviewBoardRowVO> searchReviewForPostMember(String keyword, int offset, int limit);

	// 마이페이지 리뷰 추가
	List<ReviewBoardVO> selectAllReviewsForOneMember(int postId);

	List<ReviewBoardVO> selectAllReviewsForOneMember(int offset, int limit, int postId);

	int checkAllArticleCountOneMember(int postId);
	//

}