package com.javaweb.apple.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.inf.IReviewBoardDAO_C;
import com.javaweb.apple.model.vo.ReviewBoardVO;
import com.javaweb.apple.model.vo.virtual.ReviewBoardRowVO;
import com.javaweb.apple.service.inf.IReviewBoardSVC_C;

@Service
public class ReviewBoardSVCImpl_C implements IReviewBoardSVC_C {

	@Autowired
	IReviewBoardDAO_C rbDao;

	@Override
	public boolean insertNewReview(ReviewBoardVO rb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertNewReview(String postTitle, int postId, String postContents, float productScore,
			String productImage) {
		return this.rbDao.insertNewReview(postTitle, postId, postContents, productScore, productImage);
	}

	@Override
	public int insertNewReviewReturnKey(ReviewBoardVO rb) {
		return rbDao.insertNewReviewReturnKey(rb);
	}

	@Override
	public ReviewBoardVO selectOneReview(int rId) {
		ReviewBoardVO rb = rbDao.selectOneReview(rId);
		if (rb != null) {
			if (rbDao.increaseReadCount(rId))
				return rb;
			else {
				System.out.println("svc 상세조회: 조회수 증가 실패..");
				return null; // 조회수증가 실패로 상세보기 실패?
				// return at;
			}
		} else
			return null;
	}

	@Override
	public boolean updateReview(ReviewBoardVO at) {
		return rbDao.updateReview(at);
	}

	@Override
	public boolean deleteReview(int rId) {
		return rbDao.deleteReview(rId);
	}

	@Override
	public List<ReviewBoardVO> selectAllReviews() {
		return rbDao.selectAllReviews();
	}

	@Override
	public List<ReviewBoardVO> selectAllReviews(int page) {
		int offset = (page - 1) * PAGE_SIZE;
		int limit = PAGE_SIZE;
		List<ReviewBoardVO> rbListPg = rbDao.selectAllReview(offset, limit);
		return rbListPg;
	}

	@Override
	public int checkMaxPageNumber() {
		int totalAtCnt = rbDao.checkAllReviewCount();
		int maxPg = totalAtCnt / PAGE_SIZE + (totalAtCnt % PAGE_SIZE == 0 ? 0 : 1);
		return maxPg == 0 ? 1 : maxPg;
	}

	@Override
	public List<ReviewBoardRowVO> searchReview(String keyword, String target, int pg) {
		List<ReviewBoardRowVO> rbList = null;
		int offset = (pg - 1) * SEARCH_PAGE_SIZE;
		switch (target) {
		case "title":
			rbList = rbDao.searchReviewForTitle(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "content":
			rbList = rbDao.searchReviewForContent(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "postName":
			rbList = rbDao.searchReviewForPostMember(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "all":
			rbList = rbDao.searchReviewForAll(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		}
		if (rbList != null)
			System.out.println(">> rbDao search rbList: " + rbList.size());

		return rbList;
	}

	@Override
	public Map<String, Integer> checkMaxPageNumber(String target, String keyword) {
		Map<String, Integer> rMap = new HashMap<>();
		int totalRbCnt = 0;
		switch (target) {
		case "title":
			totalRbCnt = rbDao.checkTitleReviewCount(target, keyword);
			break;
		case "content":
			totalRbCnt = rbDao.checkContentsReviewCount(target, keyword);
			break;
		case "postName":
			totalRbCnt = rbDao.checkMembersReviewCount(target, keyword);
			break;
		case "all":
			totalRbCnt = rbDao.checkAllReviewCount(target, keyword);
			break;
		}
		int maxPg = totalRbCnt / SEARCH_PAGE_SIZE + (totalRbCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
		// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		rMap.put("totalRbCnt", totalRbCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}

	// 마이페이지 추가//
	@Override
	public List<ReviewBoardVO> selectAllReviewsForOneMember(int postId) {
		return rbDao.selectAllReviewsForOneMember(postId);
	}

	@Override
	public List<ReviewBoardVO> selectAllReviewsForOneMember(int page, int postId) {
		int offset = (page - 1) * MR_PAGE_SIZE;
		int limit = MR_PAGE_SIZE;
		List<ReviewBoardVO> rbListPg = rbDao.selectAllReviewsForOneMember(offset, limit, postId);
		return rbListPg;
	}

	@Override
	public int checkMaxPageNumber(int postId) {
		int totalRbCnt = rbDao.checkAllArticleCountOneMember(postId);
		int maxPg = totalRbCnt / MR_PAGE_SIZE + (totalRbCnt % MR_PAGE_SIZE == 0 ? 0 : 1);
		// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		return maxPg == 0 ? 1 : maxPg;
	}

	//

}
