package com.javaweb.apple.dao.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javaweb.apple.dao.inf.IQnADAO;
import com.javaweb.apple.model.vo.QnAVO;
import com.javaweb.apple.model.vo.stat.QnAStatVO;

@Repository
public class QnAMySqlDAOImpl implements IQnADAO{
	//sql 정의
	public static final String SQL_QNA_SELECT_ALL
	= "select * from qna_board order by posted_day desc";
	public static final String SQL_QNA_SELECT_ONE
	= "select * from qna_board where q_id = ?";
	public static final String SQL_QNA_SEARCH_ALL
	= "SELECT * FROM qna_board where post_title like concat('%',?,'%') or "
			+ " post_contents like concat('%',?,'%') order by posted_day desc limit ?, ?";
	public static final String SQL_QNA_SEARCH_TITLE
	= "SELECT * FROM qna_board where post_title like concat('%',?,'%') "
			+ "order by posted_day desc limit ?, ?";
	public static final String SQL_QNA_SEARCH_CONTENTS
	= "SELECT * FROM qna_board where post_contents like concat('%',?,'%') "
			+ "order by posted_day desc limit ?, ?";
	public static final String SQL_QNA_CHECK_SEARCH_ALL_PG
	= "SELECT count(*) as searchCnt FROM qna_board where post_title like concat('%',?,'%') "
			+ "or post_contents like concat('%',?,'%')";
	public static final String SQL_QNA_CHECK_SEARCH_TITLE_PG
	= "select count(*) as searchCnt from qna_board where post_title like concat('%',?,'%')";
	public static final String SQL_QNA_CHECK_SEARCH_CONTENTS_PG
	= "SELECT count(*) as searchCnt FROM qna_board where post_contents like concat('%',?,'%')";
	public static final String SQL_QNA_UPDATE
	= "update qna_board set post_title=?, post_contents=? where q_id = ?";
	public static final String SQL_QNA_UPDATE_ANSWER 
	= "update qna_board set post_answer=?, answer= 1 where q_id= ?";
	public static final String SQL_QNA_DELETE
	= "delete from qna_board where q_id = ?";
	public static final String SQL_QNA_RATIO
	= "select date(A.posted_day) as st_day, count(A.q_id) as qa_cnt," + 
			"		(count(A.q_id)/(select count(*) from qna_board " + 
			"				where posted_day between ? and " + 
			"				 ?)) as qa_cnt_ratio," + 
			"		sum(A.read_count) as rc_sum,  " + 
			"        (sum(A.read_count)/(select sum(read_count) from qna_board " + 
			"				where posted_day between ? and " + 
			"				 ?)) as rc_ratio  " + 
			"		from qna_board A where A.posted_day between ? and " + 
			"				 ? group by st_day  order by st_day asc";
	
	@Autowired
	private JdbcTemplate jtem;
	
	
	@Override
	public boolean updateQnA(QnAVO qa) {
		int r = jtem.update(SQL_QNA_UPDATE, qa.getPostTitle(), 
				qa.getPostContents(), qa.getqId());
		System.out.println(r);
		return r == 1;
	}

	@Override
	public List<QnAVO> selectQnAsByALL(String keyword, int offset, int limit) {
		return jtem.query(SQL_QNA_SEARCH_ALL, BeanPropertyRowMapper.newInstance(QnAVO.class),
				keyword, keyword, offset, limit);
	}

	@Override
	public int checkAllQnAsCount(String keyword) {
		return jtem.queryForObject(SQL_QNA_CHECK_SEARCH_ALL_PG,
				Integer.class, keyword, keyword);
	}

	@Override
	public List<QnAVO> selectQnAsByPostTitle(String postTitle, int offset, int limit) {
		return jtem.query(SQL_QNA_SEARCH_TITLE, BeanPropertyRowMapper.newInstance(QnAVO.class),
				postTitle, offset, limit);
	}

	@Override
	public int checkPostTitleQnAsCount(String keyword) {
		return jtem.queryForObject(SQL_QNA_CHECK_SEARCH_TITLE_PG,
				Integer.class, keyword);
	}

	@Override
	public List<QnAVO> selectQnAsByPostContents(String postContents, int offset, int limit) {
		return jtem.query(SQL_QNA_SEARCH_CONTENTS, BeanPropertyRowMapper.newInstance(QnAVO.class),
				postContents, offset, limit);
	}

	@Override
	public int checkPostContentsQnAsCount(String keyword) {
		return jtem.queryForObject(SQL_QNA_CHECK_SEARCH_CONTENTS_PG,
				Integer.class, keyword);
	}

	@Override
	public QnAVO selectOneQnA(int qaId) {
		try {
			return jtem.queryForObject(SQL_QNA_SELECT_ONE,
				BeanPropertyRowMapper
				.newInstance(QnAVO.class), qaId);
		} catch (EmptyResultDataAccessException e) {
			// 0개 
			System.out.println("0개 게시글 조회");
			return null; 
		} catch (DataAccessException e) {
			System.out.println("게시글 조회 일반 예외..");
			return null;
		}
	}

	@Override
	public boolean answerQnA(QnAVO qa) {
		int r = jtem.update(SQL_QNA_UPDATE_ANSWER, qa.getPostAnswer(), qa.getqId());
		System.out.println(r);
		return r == 1;
	}

	@Override
	public boolean deleteQnA(int qaId) {
		int r = jtem.update(SQL_QNA_DELETE, qaId);
		return r == 1;
	}

	@Override
	public List<QnAStatVO> QnACountCompSumPerDayRatio(String startDay, String endDay) {
		List<QnAStatVO> qaList = jtem.query(SQL_QNA_RATIO, BeanPropertyRowMapper.newInstance(QnAStatVO.class), 
				startDay, endDay, startDay, endDay, startDay, endDay);
		System.out.println(qaList.toString());
		return qaList;
	}

}
