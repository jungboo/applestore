package com.javaweb.apple.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.inf.IQnADAO;
import com.javaweb.apple.model.vo.QnAVO;
import com.javaweb.apple.model.vo.stat.QnAStatVO;
import com.javaweb.apple.service.inf.IQnASVC;

@Service
public class QnASVCImpl implements IQnASVC {
	
	@Autowired
	IQnADAO qaDao;
	
	@Override
	public int updateQnA(QnAVO qa) {
		boolean r = qaDao.updateQnA(qa);
		System.out.println(r);
		return r == true ? 1:-1;
	}

	@Override
	public List<QnAVO> searchQnA(String keyword, String target, int pg) {
		List<QnAVO> qaList = null;
		int offset = (pg-1) * SEARCH_PAGE_SIZE;
		switch (target) {
		case "all":
			qaList = qaDao.selectQnAsByALL(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "title":
			qaList = qaDao.selectQnAsByPostTitle(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "contens":
			qaList = qaDao.selectQnAsByPostContents(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		}
		if( qaList != null ) {
			System.out.println("검색결과 QnA 수: " + qaList.size());
		} else {
			System.out.println("검색결과 null");
		}
		return qaList;
	}

	@Override
	public Map<String, Integer> checkMaxPageNumber(String keyword, String target) {
		int totalQaCnt = 0;
		switch (target) {
		case "all":
			totalQaCnt = qaDao.checkAllQnAsCount(keyword);
			break;
		case "title":
			totalQaCnt = qaDao.checkPostTitleQnAsCount(keyword);
			break;
		case "contens":
			totalQaCnt = qaDao.checkPostContentsQnAsCount(keyword);
			break;
		}
		int maxPg = totalQaCnt / SEARCH_PAGE_SIZE + 
				(totalQaCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
		System.out.println("totalQaCnt = " + totalQaCnt);
		Map<String,Integer> rMap = new HashMap<>();
		rMap.put("totalQaCnt", totalQaCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}

	@Override
	public QnAVO selectOneQnA(int qaId) {
		return qaDao.selectOneQnA(qaId);
	}

	@Override
	public boolean answerQnA(QnAVO qa) {
		return qaDao.answerQnA(qa);
	}

	@Override
	public boolean deleteQnA(int qaId) {
		return qaDao.deleteQnA(qaId);
	}

	@Override
	public List<QnAStatVO> QnACountCompSumPerDayRatio(String startDay, String endDay) {
		return qaDao.QnACountCompSumPerDayRatio(startDay, endDay);
	}

	

}
