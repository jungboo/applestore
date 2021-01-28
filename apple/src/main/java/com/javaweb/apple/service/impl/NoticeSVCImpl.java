package com.javaweb.apple.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.inf.INoticeDAO;
import com.javaweb.apple.model.vo.NoticeVO;
import com.javaweb.apple.service.inf.INoticeSVC;

@Service
public class NoticeSVCImpl implements INoticeSVC {
	
	@Autowired
	INoticeDAO ntDao;
	
	@Override
	public boolean insertNewNotice(NoticeVO nt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertNewNotice(String postTitle, String postContents) {
		return ntDao.insertNewNotice(postTitle, postContents);
	}

	@Override
	public NoticeVO selectOneNotice(int ntId) {
		return ntDao.selectOneNotice(ntId);
	}

	@Override
	public int updateNotice(NoticeVO nt) {
		boolean r = ntDao.updateNotice(nt);
		System.out.println(r);
		return r == true ? 1:-1;
	}

	@Override
	public List<NoticeVO> selectAllNotices() {
		return ntDao.selectAllNotices();
	}

	@Override
	public List<NoticeVO> searchNotice(String keyword, String target, int pg) {
		List<NoticeVO> ntList = null;
		int offset = (pg-1) * SEARCH_PAGE_SIZE;
		switch (target) {
		case "all":
			ntList = ntDao.selectNoticesByALL(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "title":
			ntList = ntDao.selectNoticesByPostTitle(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "contens":
			ntList = ntDao.selectNoticesByPostContents(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		}
		if( ntList != null ) {
			System.out.println("검색결과 공지사항 수: " + ntList.size());
		}
		return ntList;
	}

	@Override
	public Map<String, Integer> checkMaxPageNumber(String keyword, String target) {
		int totalNtCnt = 0;
		switch (target) {
		case "all":
			totalNtCnt = ntDao.checkAllNoticesCount(keyword);
			break;
		case "title":
			totalNtCnt = ntDao.checkPostTitleNoticesCount(keyword);
			break;
		case "contens":
			totalNtCnt = ntDao.checkPostContentsNoticesCount(keyword);
			break;
		}
		int maxPg = totalNtCnt / SEARCH_PAGE_SIZE + 
				(totalNtCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
		System.out.println("totalNtCnt = " + totalNtCnt);
		Map<String,Integer> rMap = new HashMap<>();
		rMap.put("totalNtCnt", totalNtCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}

	@Override
	public boolean deleteNotice(int ntId) {
		return ntDao.deleteNotice(ntId);
	}

}
