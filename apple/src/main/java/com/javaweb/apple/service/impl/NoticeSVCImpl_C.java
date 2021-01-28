package com.javaweb.apple.service.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.inf.INoticeDAO_C;
import com.javaweb.apple.model.vo.NoticeVO;
import com.javaweb.apple.service.inf.INoticeSVC_C;

@Service
public class NoticeSVCImpl_C implements INoticeSVC_C {

	@Autowired
	INoticeDAO_C ntDao;

	@Override
	public NoticeVO selectOneNotice(int nId) {
		NoticeVO nt = ntDao.selectOneNotice(nId);
		if (nt != null) {
			return nt;
		} else
			return null;
	}

	@Override
	public List<NoticeVO> selectAllNotices() {
		return ntDao.selectAllNotices();
	}

	@Override
	public List<NoticeVO> selectAllNotices(boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeVO> selectAllNotices(int page) {
		int offset = (page - 1) * PAGE_SIZE;
		int limit = PAGE_SIZE;
		List<NoticeVO> ntListPg = ntDao.selectAllNotice(offset, limit);
		return ntListPg;
	}

	@Override
	public List<NoticeVO> selectAllNotice(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeVO> selectAllNotice(int offset, int limit, boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeVO> selectAllNotice(int offset, int limit, boolean order, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int checkMaxPageNumber() {
		int totalAtCnt = ntDao.checkAllNoticeCount();
		int maxPg = totalAtCnt / PAGE_SIZE + (totalAtCnt % PAGE_SIZE == 0 ? 0 : 1);
		return maxPg == 0 ? 1 : maxPg;
	}

	@Override
	public Map<String, Integer> checkMaxPageNumber(String target, String keyword) {
		Map<String,Integer> rMap = new HashMap<>();
		int totalNtCnt = 0;
		switch(target) {
		case "title":
			totalNtCnt = ntDao.checkTitleNoticeCount(target, keyword);
		break;
		case "content":
			totalNtCnt = ntDao.checkContentsNoticeCount(target, keyword);
		break;
		case "all":
			totalNtCnt = ntDao.checkAllNoticeCount(target, keyword);
		break;
		}		
		int maxPg = totalNtCnt / SEARCH_PAGE_SIZE + 
				(totalNtCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
			// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		rMap.put("totalNbCnt", totalNtCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}

	@Override
	public List<NoticeVO> searchNotice(String keyword, String target, int pg) {
		List<NoticeVO> ntList = null;
		int offset = (pg-1) * SEARCH_PAGE_SIZE;
		switch(target) {
			case "title": 
				ntList = ntDao.searchNoticeForTitle(keyword, offset, SEARCH_PAGE_SIZE);
				break;
			case "content":
				ntList = ntDao.searchNoticeForContent(keyword, offset, SEARCH_PAGE_SIZE); 
				break;
			case "all":
				ntList = ntDao.searchNoticeForAll(keyword, offset, SEARCH_PAGE_SIZE); 
				break;
		}
		if( ntList != null)
			System.out.println(">> rbDao search rbList: " + ntList.size());
		
		return ntList;
	}

}
