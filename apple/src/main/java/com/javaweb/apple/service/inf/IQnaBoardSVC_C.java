package com.javaweb.apple.service.inf;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.QnaBoardVO;
import com.javaweb.apple.model.vo.virtual.QnaBoardRowVO;

public interface IQnaBoardSVC_C {
	// - 회원이 신규 게시글을 등록 할 수 있다. (세션, 파일 업로드-사진/첨부)
	boolean insertNewQna(QnaBoardVO qb);

	boolean insertNewQna(String postTitle, int postId, String postContents);

	int insertNewQnaReturnKey(QnaBoardVO qb);

	// - 게시글을 상세보기 할 수 있다.
	QnaBoardVO selectOneQna(int qId);

	// - 회원이 자신의 게시글 편집/갱신 할 수 있다. (권한/롤?)
	boolean updateQna(QnaBoardVO qb);

	// - 회원이 자신의 게시글을 삭제 할 수 있다.
	boolean deleteQna(int qId);

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬)
	List<QnaBoardVO> selectAllQnas();

	List<QnaBoardVO> selectAllQnas(boolean order);

	List<QnaBoardVO> selectAllQnas(int page);

	List<QnaBoardVO> selectAllQna(int offset, int limit); // pg

	List<QnaBoardVO> selectAllQna(int offset, int limit, boolean order);

	List<QnaBoardVO> selectAllQna(int offset, int limit, boolean order, Date startDate, Date endDate);

	// - 게시글 검색할 수 있다.
	List<QnaBoardRowVO> searchQna(String keyword, String target, int pg);

	//
	List<QnaBoardVO> searchQna(String keyword, int offset, int limit, boolean order, Date startDate, Date endDate); // 범위..
	// ALL에 한정

	List<QnaBoardVO> searchQna(String keyword, int offset, int limit, String orderBy);

	public static final int PAGE_SIZE = 10;

	// 페이지당 표시 최대 게시글 수 (limit) == 블록사이즈수
	int checkMaxPageNumber(); // 최대 페이지 수 전체 게시글 개수 기준..

	public static final int SEARCH_PAGE_SIZE = 5; // 검색 결과 페이지당 게시글 수

	Map<String, Integer> checkMaxPageNumber(String target, String keyword);
	// 최대 페이지 수 -검색한 한정된 게시글 개수 기준..

	// 마이페이지 추가//
	List<QnaBoardVO> selectAllQnaForOneMember(int postId);

	List<QnaBoardVO> selectAllQnaForOneMember(int page, int postId);

	public static final int MR_PAGE_SIZE = 10;

	int checkMaxPageNumber(int maxPageOneMember);
	//

}
