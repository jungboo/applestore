package com.javaweb.apple.service.inf;

import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.ReviewBoardVO;
import com.javaweb.apple.model.vo.virtual.ReviewBoardRowVO;

public interface IReviewBoardSVC_C {
	// - 회원이 신규 게시글을 등록 할 수 있다. (세션, 파일 업로드-사진/첨부)
	boolean insertNewReview(ReviewBoardVO rb);

	boolean insertNewReview(String postTitle, int postId, String postContents, float productScore, String productImage);

	int insertNewReviewReturnKey(ReviewBoardVO rb);

	// - 게시글을 상세보기 할 수 있다.
	ReviewBoardVO selectOneReview(int rId);

	// - 회원이 자신의 게시글 편집/갱신 할 수 있다. (권한/롤?)
	boolean updateReview(ReviewBoardVO at);

	// - 회원이 자신의 게시글을 삭제 할 수 있다.
	boolean deleteReview(int rId);

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬)
	List<ReviewBoardVO> selectAllReviews();

	List<ReviewBoardVO> selectAllReviews(int page);

	// - 게시글 검색할 수 있다.
	List<ReviewBoardRowVO> searchReview(String keyword, String target, int pg);

//	List<ReviewBoardVO> searchReviewForTitle(String keyword);
//
//	List<ReviewBoardVO> searchReviewForContent(String keyword);
//
//	List<ReviewBoardVO> searchReviewForPostMember(String keyword);
//
//	List<ReviewBoardVO> searchReviewForAll(String keyword);
//
//	
//	List<ReviewBoardVO> searchReview(String keyword, int offset, int limit, boolean order, Date startDate,
//			Date endDate); // 범위..
// ALL에 한정

	public static final int PAGE_SIZE = 10;

	// 페이지당 표시 최대 게시글 수 (limit) == 블록사이즈수
	int checkMaxPageNumber(); // 최대 페이지 수 전체 게시글 개수 기준..

	public static final int SEARCH_PAGE_SIZE = 5; // 검색 결과 페이지당 게시글 수

	Map<String, Integer> checkMaxPageNumber(String target, String keyword);
	// 최대 페이지 수 -검색한 한정된 게시글 개수 기준..

	// 마이페이지 추가//
	List<ReviewBoardVO> selectAllReviewsForOneMember(int postId);

	List<ReviewBoardVO> selectAllReviewsForOneMember(int page, int postId);

	public static final int MR_PAGE_SIZE = 10;

	int checkMaxPageNumber(int maxPageOneMember);
	//
}
