package com.javaweb.apple.dao.inf;

import java.sql.Date;
import java.util.List;

import com.javaweb.apple.model.vo.QnAVO;
import com.javaweb.apple.model.vo.stat.QnAStatVO;

public interface IQnADAO {
//- QnA를 수정 할 수 있다.
//	ad_nt_update_form.ap
//	ad_nt_update_proc.ap
	boolean updateQnA(QnAVO qa);
// QnA를 검색 할 수 있다.
//	ad_nt_search.ap
// all으로 조회
	List<QnAVO> selectQnAsByALL(String keyword, int offset , int limit);
	int checkAllQnAsCount(String keyword);
// postTitle로 조회
	List<QnAVO> selectQnAsByPostTitle(String postTitle, int offset , int limit);
	int checkPostTitleQnAsCount(String keyword);
//	postContents로 조회
	List<QnAVO> selectQnAsByPostContents(String postContents, int offset , int limit);
	int checkPostContentsQnAsCount(String keyword);
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
