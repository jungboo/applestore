package com.javaweb.apple.service.inf;

import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.ReviewVO;
import com.javaweb.apple.model.vo.stat.ReviewStatVO;

public interface IReviewSVC {
	public static final int SEARCH_PAGE_SIZE = 3;
//- review를 수정 할 수 있다.
//	ad_nt_update_form.ap
//	ad_nt_update_proc.ap
	int updateReview(ReviewVO rv);
// 	review를 검색 할 수 있다.
//	ad_nt_search.ap
	List<ReviewVO> searchReview(String keyword, String target, int pg);
	
	Map<String,Integer> checkMaxPageNumber(String keyword, String target);
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
