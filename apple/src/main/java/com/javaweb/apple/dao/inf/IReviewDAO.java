package com.javaweb.apple.dao.inf;

import java.util.List;

import com.javaweb.apple.model.vo.ReviewVO;
import com.javaweb.apple.model.vo.stat.ReviewStatVO;

public interface IReviewDAO {
//- review를 수정 할 수 있다.
//	rv_update_form.ap
//	rv_update_proc.ap
	boolean updateReview(ReviewVO rv);
// review를 검색 할 수 있다.
//	rv_search.ap
// all으로 조회
	List<ReviewVO> selectReviewsByALL(String keyword, int offset , int limit);
	int checkAllReviewsCount(String keyword);
// postTitle로 조회
	List<ReviewVO> selectReviewsByPostTitle(String postTitle, int offset , int limit);
	int checkPostTitleReviewsCount(String keyword);
//	postContents로 조회
	List<ReviewVO> selectReviewsByPostContents(String postContents, int offset , int limit);
	int checkPostContentsReviewsCount(String keyword);
//- review를 상세보기 할 수 있다.
//	rv_show.ap
	ReviewVO selectOneReview(int rvId);
//- review에 답변 할 수 있다.
//	rv_answer_form.ap
//	rv_answer_proc.ap
	boolean answerReview(ReviewVO rv);
//- review를 삭제 할 수 있다.
	boolean deleteReview(int rvId);
//
	List<ReviewStatVO> reviewCountStatPerArticle();
}
