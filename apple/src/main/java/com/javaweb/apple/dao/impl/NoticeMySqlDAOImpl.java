package com.javaweb.apple.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javaweb.apple.dao.inf.INoticeDAO;
import com.javaweb.apple.model.vo.NoticeVO;

@Repository
public class NoticeMySqlDAOImpl implements INoticeDAO {
	// SQL 정의부
	public static final String SQL_NOTICE_INSERT 
	= "insert into notice_board(post_title, post_contents) values (?,?)";
	public static final String SQL_NOTICE_SELECT_ONE
	= "select * from notice_board where n_id = ?";
	public static final String SQL_NOTICE_SELECT_ALL
	= "select * from notice_board order by posted_day desc";
	public static final String SQL_NOTICE_SEARCH_ALL
	= "SELECT * FROM notice_board where post_title like concat('%',?,'%') or "
			+ " post_contents like concat('%',?,'%') order by posted_day desc limit ?, ?";
	public static final String SQL_NOTICE_SEARCH_TITLE
	= "SELECT * FROM notice_board where post_title like concat('%',?,'%') "
			+ "order by posted_day desc limit ?, ?";
	public static final String SQL_NOTICE_SEARCH_CONTENTS
	= "SELECT * FROM notice_board where post_contents like concat('%',?,'%') "
			+ "order by posted_day desc limit ?, ?";
	public static final String SQL_NOTICE_CHECK_SEARCH_ALL_PG
	= "SELECT count(*) as searchCnt FROM notice_board where post_title like concat('%',?,'%') "
			+ "or post_contents like concat('%',?,'%')";
	public static final String SQL_NOTICE_CHECK_SEARCH_TITLE_PG
	= "select count(*) as searchCnt from notice_board where post_title like concat('%',?,'%')";
	public static final String SQL_NOTICE_CHECK_SEARCH_CONTENTS_PG
	= "SELECT count(*) as searchCnt FROM notice_board where post_contents like concat('%',?,'%')";
	public static final String SQL_NOTICE_UPDATE
	= "update notice_board set post_title=?, post_contents=? where n_id = ?";
	public static final String SQL_NOTICE_DELETE
	= "delete from notice_board where n_id = ?";
	
	@Autowired
	private JdbcTemplate jtem;
	
	@Override
	public boolean insertNewNotice(NoticeVO nt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertNewNotice(String postTitle, String postContents) {
		int r = jtem.update(SQL_NOTICE_INSERT, postTitle, postContents);
		return r == 1;
	}

	@Override
	public NoticeVO selectOneNotice(int ntId) {
		try {
			return jtem.queryForObject(SQL_NOTICE_SELECT_ONE,
				BeanPropertyRowMapper
				.newInstance(NoticeVO.class), ntId);
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
	public boolean updateNotice(NoticeVO nt) {
		int r = jtem.update(SQL_NOTICE_UPDATE, nt.getPostTitle(), 
				nt.getPostContents(), nt.getnId());
		System.out.println(r);
		return r == 1;
	}

	@Override
	public List<NoticeVO> selectAllNotices() {
		return jtem.query(SQL_NOTICE_SELECT_ALL, BeanPropertyRowMapper.
				newInstance(NoticeVO.class));
	}

	@Override
	public List<NoticeVO> selectNoticesByALL(String keyword, int offset, int limit) {
		return jtem.query(SQL_NOTICE_SEARCH_ALL, BeanPropertyRowMapper.newInstance(NoticeVO.class),
				keyword, keyword, offset, limit);
	}

	@Override
	public int checkAllNoticesCount(String keyword) {
		return jtem.queryForObject(SQL_NOTICE_CHECK_SEARCH_ALL_PG,
				Integer.class, keyword, keyword);
	}

	@Override
	public List<NoticeVO> selectNoticesByPostTitle(String postTitle, int offset, int limit) {
		return jtem.query(SQL_NOTICE_SEARCH_TITLE, BeanPropertyRowMapper.newInstance(NoticeVO.class),
				postTitle, offset, limit);
	}

	@Override
	public int checkPostTitleNoticesCount(String keyword) {
		return jtem.queryForObject(SQL_NOTICE_CHECK_SEARCH_TITLE_PG,
				Integer.class, keyword);
	}

	@Override
	public List<NoticeVO> selectNoticesByPostContents(String postContents, int offset, int limit) {
		return jtem.query(SQL_NOTICE_SEARCH_CONTENTS, BeanPropertyRowMapper.newInstance(NoticeVO.class),
				postContents, offset, limit);
	}

	@Override
	public int checkPostContentsNoticesCount(String keyword) {
		return jtem.queryForObject(SQL_NOTICE_CHECK_SEARCH_CONTENTS_PG,
				Integer.class, keyword);
	}

	@Override
	public boolean deleteNotice(int ntId) {
		int r = jtem.update(SQL_NOTICE_DELETE, ntId);
		return r == 1;
	}

}
