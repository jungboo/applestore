package com.javaweb.apple.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.inf.IReviewDAO;
import com.javaweb.apple.model.vo.ReviewVO;
import com.javaweb.apple.model.vo.stat.ReviewStatVO;
import com.javaweb.apple.service.inf.IReviewSVC;

@Service
public class ReviewSVCImpl implements IReviewSVC {
	
	@Autowired
	IReviewDAO rvDao;
	
	@Override
	public int updateReview(ReviewVO rv) {
		boolean r = rvDao.updateReview(rv);
		System.out.println(r);
		return r == true ? 1:-1;
	}

	@Override
	public List<ReviewVO> searchReview(String keyword, String target, int pg) {
		List<ReviewVO> rvList = null;
		int offset = (pg-1) * SEARCH_PAGE_SIZE;
		switch (target) {
		case "all":
			rvList = rvDao.selectReviewsByALL(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "title":
			rvList = rvDao.selectReviewsByPostTitle(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "contens":
			rvList = rvDao.selectReviewsByPostContents(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		}
		if( rvList != null ) {
			System.out.println("검색결과 review 수: " + rvList.size());
		} else {
			System.out.println("검색결과 null");
		}
		return rvList;
	}

	@Override
	public Map<String, Integer> checkMaxPageNumber(String keyword, String target) {
		int totalRvCnt = 0;
		switch (target) {
		case "all":
			totalRvCnt = rvDao.checkAllReviewsCount(keyword);
			break;
		case "title":
			totalRvCnt = rvDao.checkPostTitleReviewsCount(keyword);
			break;
		case "contens":
			totalRvCnt = rvDao.checkPostContentsReviewsCount(keyword);
			break;
		}
		int maxPg = totalRvCnt / SEARCH_PAGE_SIZE + 
				(totalRvCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
		System.out.println("totalRvCnt = " + totalRvCnt);
		Map<String,Integer> rMap = new HashMap<>();
		rMap.put("totalRvCnt", totalRvCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}

	@Override
	public ReviewVO selectOneReview(int rvId) {
		return rvDao.selectOneReview(rvId);
	}

	@Override
	public boolean answerReview(ReviewVO rv) {
		return rvDao.answerReview(rv);
	}

	@Override
	public boolean deleteReview(int rvId) {
		return rvDao.deleteReview(rvId);
	}

	@Override
	public List<ReviewStatVO> reviewCountStatPerArticle() {
		return rvDao.reviewCountStatPerArticle();
	}

}
