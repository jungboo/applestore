package com.javaweb.apple.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.inf.IQnaBoardDAO_C;
import com.javaweb.apple.model.vo.QnaBoardVO;
import com.javaweb.apple.model.vo.ReviewBoardVO;
import com.javaweb.apple.model.vo.virtual.QnaBoardRowVO;
import com.javaweb.apple.service.inf.IQnaBoardSVC_C;

@Service
public class QnaBoardSVCImpl_C implements IQnaBoardSVC_C {

	@Autowired
	IQnaBoardDAO_C qbDao;

	@Override
	public boolean insertNewQna(QnaBoardVO qb) {
		return false;
	}

	@Override
	public boolean insertNewQna(String postTitle, int postId, String postContents) {
		return this.qbDao.insertNewQna(postTitle, postId, postContents);
	}

	@Override
	public int insertNewQnaReturnKey(QnaBoardVO qb) {
		return qbDao.insertNewQnaReturnKey(qb);
	}

	@Override
	public QnaBoardVO selectOneQna(int qId) {
		QnaBoardVO qb = qbDao.selectOneQna(qId);
		if (qb != null) {
			if (qbDao.increaseReadCount(qId))
				return qb;
			else {
				System.out.println("svc 상세조회: 조회수 증가 실패..");
				return null; // 조회수증가 실패로 상세보기 실패?
				// return at;
			}
		} else
			return null;
	}

	@Override
	public boolean updateQna(QnaBoardVO qb) {
		return qbDao.updateQna(qb);
	}

	@Override
	public boolean deleteQna(int qId) {
		return qbDao.deleteQna(qId);
	}

	@Override
	public List<QnaBoardVO> selectAllQnas() {
		return qbDao.selectAllQnas();
	}

	@Override
	public List<QnaBoardVO> selectAllQnas(boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnaBoardVO> selectAllQnas(int page) {
		int offset = (page - 1) * PAGE_SIZE;
		int limit = PAGE_SIZE;
		List<QnaBoardVO> qbListPg = qbDao.selectAllQna(offset, limit);
		return qbListPg;
	}

	@Override
	public List<QnaBoardVO> selectAllQna(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnaBoardVO> selectAllQna(int offset, int limit, boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnaBoardVO> selectAllQna(int offset, int limit, boolean order, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

///////////////////////
	@Override
	public List<QnaBoardRowVO> searchQna(String keyword, String target, int pg) {
		List<QnaBoardRowVO> qbList = null;
		int offset = (pg - 1) * SEARCH_PAGE_SIZE;
		switch (target) {
		case "title":
			qbList = qbDao.searchQnaForTitle(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "content":
			qbList = qbDao.searchQnaForContent(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "postName":
			qbList = qbDao.searchQnaForPostMember(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "all":
			qbList = qbDao.searchQnaForAll(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		}
		if (qbList != null)
			System.out.println(">> rbDao search rbList: " + qbList.size());

		return qbList;
	}

	@Override
	public Map<String, Integer> checkMaxPageNumber(String target, String keyword) {
		Map<String, Integer> rMap = new HashMap<>();
		int totalQbCnt = 0;
		switch (target) {
		case "title":
			totalQbCnt = qbDao.checkTitleQnaCount(target, keyword);
			break;
		case "content":
			totalQbCnt = qbDao.checkContentsQnaCount(target, keyword);
			break;
		case "postName":
			totalQbCnt = qbDao.checkMembersQnaCount(target, keyword);
			break;
		case "all":
			totalQbCnt = qbDao.checkAllQnaCount(target, keyword);
			break;
		}
		int maxPg = totalQbCnt / SEARCH_PAGE_SIZE + (totalQbCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
		// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		rMap.put("totalQbCnt", totalQbCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}

///////////////
	@Override
	public List<QnaBoardVO> searchQna(String keyword, int offset, int limit, boolean order, Date startDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnaBoardVO> searchQna(String keyword, int offset, int limit, String orderBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkMaxPageNumber() {
		int totalAtCnt = qbDao.checkAllQnaCount();
		int maxPg = totalAtCnt / PAGE_SIZE + (totalAtCnt % PAGE_SIZE == 0 ? 0 : 1);
		return maxPg == 0 ? 1 : maxPg;
	}

	// 마이페이지 추가//
	@Override
	public List<QnaBoardVO> selectAllQnaForOneMember(int postId) {
		return qbDao.selectAllQnaForOneMember(postId);
	}

	@Override
	public List<QnaBoardVO> selectAllQnaForOneMember(int page, int postId) {
		int offset = (page - 1) * MR_PAGE_SIZE;
		int limit = MR_PAGE_SIZE;
		List<QnaBoardVO> qbListPg = qbDao.selectAllQnaForOneMember(offset, limit, postId);
		return qbListPg;
	}

	@Override
	public int checkMaxPageNumber(int maxPageOneMember) {
		int totalQbCnt = qbDao.checkAllArticleCount(maxPageOneMember);
		int maxPg = totalQbCnt / MR_PAGE_SIZE + (totalQbCnt % MR_PAGE_SIZE == 0 ? 0 : 1);
		// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		return maxPg == 0 ? 1 : maxPg;
	}

}
