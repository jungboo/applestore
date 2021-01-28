package com.javaweb.apple.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javaweb.apple.dao.inf.IReviewDAO;
import com.javaweb.apple.model.vo.ReviewVO;
import com.javaweb.apple.model.vo.stat.ReviewStatVO;

@Repository
public class RiviewMySqlDAOImpl implements IReviewDAO{
	//sql 정의
	public static final String SQL_REVIEW_SELECT_ALL
	= "select * from review_board order by posted_day desc";
	public static final String SQL_REVIEW_SELECT_ONE
	= "select * from review_board where r_id = ?";
	public static final String SQL_REVIEW_SEARCH_ALL
	= "SELECT * FROM review_board where post_title like concat('%',?,'%') or "
			+ " post_contents like concat('%',?,'%') order by posted_day desc limit ?, ?";
	public static final String SQL_REVIEW_SEARCH_TITLE
	= "SELECT * FROM review_board where post_title like concat('%',?,'%') "
			+ "order by posted_day desc limit ?, ?";
	public static final String SQL_REVIEW_SEARCH_CONTENTS
	= "SELECT * FROM review_board where post_contents like concat('%',?,'%') "
			+ "order by posted_day desc limit ?, ?";
	public static final String SQL_REVIEW_CHECK_SEARCH_ALL_PG
	= "SELECT count(*) as searchCnt FROM review_board where post_title like concat('%',?,'%') "
			+ "or post_contents like concat('%',?,'%')";
	public static final String SQL_REVIEW_CHECK_SEARCH_TITLE_PG
	= "select count(*) as searchCnt from review_board where post_title like concat('%',?,'%')";
	public static final String SQL_REVIEW_CHECK_SEARCH_CONTENTS_PG
	= "SELECT count(*) as searchCnt FROM review_board where post_contents like concat('%',?,'%')";
	public static final String SQL_REVIEW_UPDATE
	= "update review_board set post_title=?, post_contents=? where r_id = ?";
	public static final String SQL_REVIEW_UPDATE_ANSWER 
	= "update review_board set post_answer=?, answer= 1 where r_id= ?";
	public static final String SQL_REVIEW_DELETE
	= "delete from review_board where r_id = ?";
	public static final String SQL_REVIEW_STAT
	 = "select r_id as rv_id, read_count, post_title from review_board";
	 		
	
	@Autowired
	private JdbcTemplate jtem;
	
	
	@Override
	public boolean updateReview(ReviewVO rv) {
		int r = jtem.update(SQL_REVIEW_UPDATE, rv.getPostTitle(), 
				rv.getPostContents(), rv.getrId());
		System.out.println(r);
		return r == 1;
	}

	@Override
	public List<ReviewVO> selectReviewsByALL(String keyword, int offset, int limit) {
		return jtem.query(SQL_REVIEW_SEARCH_ALL, BeanPropertyRowMapper.newInstance(ReviewVO.class),
				keyword, keyword, offset, limit);
	}

	@Override
	public int checkAllReviewsCount(String keyword) {
		return jtem.queryForObject(SQL_REVIEW_CHECK_SEARCH_ALL_PG,
				Integer.class, keyword, keyword);
	}

	@Override
	public List<ReviewVO> selectReviewsByPostTitle(String postTitle, int offset, int limit) {
		return jtem.query(SQL_REVIEW_SEARCH_TITLE, BeanPropertyRowMapper.newInstance(ReviewVO.class),
				postTitle, offset, limit);
	}

	@Override
	public int checkPostTitleReviewsCount(String keyword) {
		return jtem.queryForObject(SQL_REVIEW_CHECK_SEARCH_TITLE_PG,
				Integer.class, keyword);
	}

	@Override
	public List<ReviewVO> selectReviewsByPostContents(String postContents, int offset, int limit) {
		return jtem.query(SQL_REVIEW_SEARCH_CONTENTS, BeanPropertyRowMapper.newInstance(ReviewVO.class),
				postContents, offset, limit);
	}

	@Override
	public int checkPostContentsReviewsCount(String keyword) {
		return jtem.queryForObject(SQL_REVIEW_CHECK_SEARCH_CONTENTS_PG,
				Integer.class, keyword);
	}

	@Override
	public ReviewVO selectOneReview(int rvId) {
		try {
			return jtem.queryForObject(SQL_REVIEW_SELECT_ONE,
				BeanPropertyRowMapper
				.newInstance(ReviewVO.class), rvId);
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
	public boolean answerReview(ReviewVO rv) {
		int r = jtem.update(SQL_REVIEW_UPDATE_ANSWER, rv.getPostAnswer(), rv.getrId());
		return r == 1;
	}

	@Override
	public boolean deleteReview(int rvId) {
		int r = jtem.update(SQL_REVIEW_DELETE, rvId);
		return r == 1;
	}

	@Override
	public List<ReviewStatVO> reviewCountStatPerArticle() {
		List<ReviewStatVO> rvList = jtem.query(SQL_REVIEW_STAT, BeanPropertyRowMapper.newInstance(ReviewStatVO.class));
		System.out.println(rvList.toString());
		return rvList;
	}

}
