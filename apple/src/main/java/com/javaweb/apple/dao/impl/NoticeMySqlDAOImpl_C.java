package com.javaweb.apple.dao.impl;

import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.javaweb.apple.dao.inf.INoticeDAO_C;
import com.javaweb.apple.model.vo.NoticeVO;

@Repository
public class NoticeMySqlDAOImpl_C implements INoticeDAO_C {

	public static final String SQL_NOTICE_CHECK_ALL_COUNT = "SELECT count(n_id) FROM notice_board";
	public static final String SQL_NOTICE_CHECK_SEARCH_ALL_PG = "SELECT count(*) as searchCnt FROM notice_board where post_title like concat('%',?,'%') or  post_contents like concat('%',?,'%') or post_id like concat('%',?,'%')";
	public static final String SQL_NOTICE_SELECT_ALL = "SELECT * FROM notice_board order by posted_day";
	private static final String SQL_NOTICE_SELECT_ALL_PG = "SELECT * FROM notice_board order by posted_day desc limit ?, ?";
	private static final String SQL_NOTICE_SELECT_ONE = "select * from notice_board where n_id = ?";

	private static final String SQL_NOTICE_SEARCH_TITLE
	="select * from notice_board where post_title like concat('%',?,'%') order by posted_day desc limit ?, ?";
	private static final String SQL_NOTICE_CHECK_SEARCH_TITLE
	="select count(*) from notice_board where post_title like concat('%',?,'%') order by posted_day desc";
	
	private static final String SQL_NOTICE_SEARCH_CONTENT 
	= "select * from notice_board where post_contents like concat('%',?,'%') order by posted_day desc limit ?, ?";
	private static final String SQL_NOTICE_CHECK_SEARCH_CONTENT
	= "select count(*) from notice_board where post_contents like concat('%',?,'%') order by posted_day desc";
	
	private static final String SQL_NOTICE_SEARCH_ALL 
	= "SELECT * FROM notice_board where post_title like concat('%',?,'%') or  post_contents like concat('%',?,'%') order by posted_day desc limit ?, ?";
	public static final String SQL_NOTICE_CHECK_SEARCH_ALL
	= "SELECT count(*) FROM notice_board where post_title like concat('%',?,'%') or  post_contents like concat('%',?,'%') order by posted_day desc";

	
	private JdbcTemplate jtem;
	private SimpleJdbcInsert simln;

	@Autowired
	public NoticeMySqlDAOImpl_C(JdbcTemplate jtem) {
		this.jtem = jtem;
		this.simln = new SimpleJdbcInsert(jtem);
		this.simln = new SimpleJdbcInsert(jtem.getDataSource());
		this.simln.withTableName("notice_board");
		this.simln.usingGeneratedKeyColumns("nId");// <<PK>>
	}

	@Override
	public NoticeVO selectOneNotice(int nId) {
		try {
			return jtem.queryForObject(SQL_NOTICE_SELECT_ONE, BeanPropertyRowMapper.newInstance(NoticeVO.class), nId);
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
	public int checkAllNoticeCount() {
		return jtem.queryForObject(SQL_NOTICE_CHECK_ALL_COUNT, Integer.class);
	}

	@Override
	public List<NoticeVO> selectAllNotices() {
		return jtem.query(SQL_NOTICE_SELECT_ALL, BeanPropertyRowMapper.newInstance(NoticeVO.class));
	}

	@Override
	public List<NoticeVO> selectAllNotices(boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NoticeVO> selectAllNotice(int offset, int limit) {
		return jtem.query(SQL_NOTICE_SELECT_ALL_PG, BeanPropertyRowMapper.newInstance(NoticeVO.class), offset, limit);
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
	public int checkAllNoticeCount(String target, String keyword) {
		return jtem.queryForObject(SQL_NOTICE_CHECK_SEARCH_ALL, Integer.class, keyword, keyword);
	}
	@Override
	public int checkTitleNoticeCount(String target, String keyword) {
		return jtem.queryForObject(SQL_NOTICE_CHECK_SEARCH_TITLE, Integer.class, keyword);
	}
	@Override
	public int checkContentsNoticeCount(String target, String keyword) {
		return jtem.queryForObject(SQL_NOTICE_CHECK_SEARCH_CONTENT, Integer.class, keyword);
	}

	@Override
	public List<NoticeVO> searchNoticeForAll(String keyword, int offset, int limit) {
		return jtem.query(SQL_NOTICE_SEARCH_ALL, BeanPropertyRowMapper.newInstance(NoticeVO.class),
				keyword, keyword, offset, limit);
}

	@Override
	public List<NoticeVO> searchNoticeForTitle(String keyword, int offset, int limit) {
		return jtem.query(SQL_NOTICE_SEARCH_TITLE, BeanPropertyRowMapper.newInstance(NoticeVO.class), keyword
				,offset, limit);
}

	@Override
	public List<NoticeVO> searchNoticeForContent(String keyword, int offset, int limit) {
		return jtem.query(SQL_NOTICE_SEARCH_CONTENT, BeanPropertyRowMapper.newInstance(NoticeVO.class), keyword
				,offset, limit);
}

	

}
