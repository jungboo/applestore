package com.javaweb.apple.service.inf;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.QnAVO;
import com.javaweb.apple.model.vo.stat.QnAStatVO;

public interface IQnASVC {
	public static final int SEARCH_PAGE_SIZE = 3;
//- QnA를 수정 할 수 있다.
//	ad_nt_update_form.ap
//	ad_nt_update_proc.ap
	int updateQnA(QnAVO qa);
// QnA를 검색 할 수 있다.
//	ad_nt_search.ap
	List<QnAVO> searchQnA(String keyword, String target, int pg);
	
	Map<String,Integer> checkMaxPageNumber(String keyword, String target);	
//- QnA를 상세보기 할 수 있다.
//	qa_show.ap
	QnAVO selectOneQnA(int qaId);
//- QnA에 답변 할 수 있다.
//	qa_answer_form.ap
//	qa_answer_proc.ap
	boolean answerQnA(QnAVO qa);
//- QnA를 삭제 할 수 있다.
	boolean deleteQnA(int qaId);
//	날짜별 통계
	List<QnAStatVO> QnACountCompSumPerDayRatio(String startDay, String endDay);
}
