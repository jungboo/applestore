package com.javaweb.apple.dao.impl;

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

import com.javaweb.apple.dao.inf.IQnaBoardDAO_C;
import com.javaweb.apple.model.vo.QnaBoardVO;
import com.javaweb.apple.model.vo.ReviewBoardVO;
import com.javaweb.apple.model.vo.virtual.QnaBoardRowVO;

@Repository
public class QnaBoardMySqlDAOImpl_C implements IQnaBoardDAO_C {
	public static final String SQL_QNA_INSERT = "INSERT INTO qna_board(post_title, post_id, post_contents) values (?,? ,?)";
	public static final String SQL_QNA_CHECK_ALL_COUNT = "SELECT count(q_id) FROM qna_board";
	public static final String SQL_QNA_CHECK_SEARCH_ALL_PG = "SELECT count(*) as searchCnt FROM QNA_board where post_title like concat('%',?,'%') or  post_contents like concat('%',?,'%') or post_id like concat('%',?,'%')";
	public static final String SQL_QNA_SELECT_ALL = "SELECT * FROM qna_board order by posted_day";
	private static final String SQL_QNA_SELECT_ALL_PG = "SELECT * FROM qna_board order by posted_day desc limit ?, ?";
	private static final String SQL_QNA_SELECT_ONE = "select * from qna_board where q_id = ?";
	private static final String SQL_QNA_INCREASE_READCOUNT = "update qna_board set read_count = read_count + 1 where q_id = ?";
	private static final String SQL_QNA_DELETE = "DELETE FROM qna_board WHERE q_id = ?";
	private static final String SQL_QNA_UPDATE = "update qna_board set post_title=?, post_contents=?, updated_day=now() where q_id=?";

	private static final String SQL_QNA_SEARCH_TITLE = "select * from (select Q.q_id, Q.post_title, Q.post_contents, (select M.login from members M where M.id = Q.post_id) as mbid, Q.posted_day, Q.read_count,"
			+ "Q.post_answer  from qna_board Q) S where S.post_title like concat('%',?,'%') order by posted_day desc limit ?, ?";

	private static final String SQL_QNA_CHECK_SEARCH_TITLE = "select count(*) from (select Q.q_id, Q.post_title, Q.post_contents, (select M.login from members M where M.id = Q.post_id) as mbid, Q.posted_day, Q.read_count,"
			+ "Q.post_answer  from qna_board Q) S where S.post_title like concat('%',?,'%') order by posted_day desc";

	private static final String SQL_QNA_SEARCH_CONTENT = "select * from (select Q.q_id, Q.post_title, Q.post_contents, (select M.login from members M where M.id = Q.post_id) as mbid, Q.posted_day, Q.read_count,"
			+ "Q.post_answer  from qna_board Q) S where S.post_contents like concat('%',?,'%') order by posted_day desc limit ?, ?";

	private static final String SQL_QNA_CHECK_SEARCH_CONTENT = "select count(*) from (select Q.q_id, Q.post_title, Q.post_contents, (select M.login from members M where M.id = Q.post_id) as mbid, Q.posted_day, Q.read_count,"
			+ "Q.post_answer  from qna_board Q) S where S.post_contents like concat('%',?,'%') order by posted_day desc";

	private static final String SQL_QNA_SEARCH_MEMBERS = "select * from (select Q.q_id, Q.post_title, Q.post_contents, (select M.login from members M where M.id = Q.post_id) as mbid, Q.posted_day, Q.read_count,"
			+ "Q.post_answer  from qna_board Q) S where S.mbid like concat('%',?,'%') order by posted_day desc limit ?, ?";

	private static final String SQL_QNA_CHECK_SEARCH_MEMBERS = "select count(*) from (select Q.q_id, Q.post_title, Q.post_contents, (select M.login from members M where M.id = Q.post_id) as mbid, Q.posted_day, Q.read_count,"
			+ "Q.post_answer  from qna_board Q) S where S.mbid like concat('%',?,'%') order by posted_day desc";

	private static final String SQL_QNA_SEARCH_ALL = "select * from (select Q.q_id, Q.post_title, Q.post_contents, (select M.login from members M where M.id = Q.post_id) as mbid, Q.posted_day, Q.read_count,"
			+ "Q.post_answer  from qna_board Q) S where S.post_title like concat('%',?,'%') or S.post_contents like concat('%',?,'%') or "
			+ "S.mbid like concat('%',?,'%') order by posted_day desc limit ?, ?";

	public static final String SQL_QNA_CHECK_SEARCH_ALL = "select count(*) from (select Q.q_id, Q.post_title, Q.post_contents, (select M.login from members M where M.id = Q.post_id) as mbid, Q.posted_day, Q.read_count,"
			+ "Q.post_answer  from qna_board Q) S where S.post_title like concat('%',?,'%') or S.post_contents like concat('%',?,'%') or "
			+ "S.mbid like concat('%',?,'%') order by posted_day desc";

	@Override
	public boolean insertNewQna(QnaBoardVO qb) {
		// TODO Auto-generated method stub
		return false;
	}

	private JdbcTemplate jtem;
	private SimpleJdbcInsert simln;

	@Autowired
	public QnaBoardMySqlDAOImpl_C(JdbcTemplate jtem) {
		this.jtem = jtem;
		this.simln = new SimpleJdbcInsert(jtem);
		this.simln = new SimpleJdbcInsert(jtem.getDataSource());
		this.simln.withTableName("qna_board");
		this.simln.usingGeneratedKeyColumns("qId");// <<PK>>
	}

	@Override
	public boolean insertNewQna(String postTitle, int postId, String postContents) {
		int r = this.jtem.update(SQL_QNA_INSERT, new Object[] { postTitle, postId, postContents },
				new int[] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR });
		return r == 1;
	}

	@Override
	public int insertNewQnaReturnKey(QnaBoardVO qb) {
		// psc-KeyHolder 방식
		System.out.println(">> dao: keyholder...");
		KeyHolder kh = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(SQL_QNA_INSERT, new String[] { "rId" });
				// new String[] {"id", "xyz"}) postTitle, postId, postContent, productScore,
				// productImage
				pstmt.setString(1, qb.getPostTitle());
				pstmt.setInt(2, qb.getPostId());// <<FK>>
				pstmt.setString(3, qb.getPostContents());
				return pstmt;
			}
		};
		jtem.update(psc, kh);
		Number pk = kh.getKey();
		return pk.intValue(); // qna_board.id <<PK>>
	}

	@Override
	public QnaBoardVO selectOneQna(int qId) {
		try {
			return jtem.queryForObject(SQL_QNA_SELECT_ONE, BeanPropertyRowMapper.newInstance(QnaBoardVO.class), qId);
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
	public boolean updateQna(QnaBoardVO qb) {
		int r = jtem.update(SQL_QNA_UPDATE, qb.getPostTitle(), qb.getPostContents(), qb.getqId());
		return r == 1;
	}

	@Override
	public boolean increaseReadCount(int qId) {
		int r = this.jtem.update(SQL_QNA_INCREASE_READCOUNT, qId);
		return r == 1;
	}

	@Override
	public boolean deleteQna(int qId) {
		int r = this.jtem.update(SQL_QNA_DELETE, qId);
		return r == 1;
	}

	@Override
	public int checkAllQnaCount() {
		return jtem.queryForObject(SQL_QNA_CHECK_ALL_COUNT, Integer.class);
	}

/////////////////
	@Override
	public int checkAllQnaCount(String target, String keyword) {
		return jtem.queryForObject(SQL_QNA_CHECK_SEARCH_ALL, Integer.class, keyword, keyword, keyword);
	}

	@Override
	public int checkTitleQnaCount(String target, String keyword) {
		return jtem.queryForObject(SQL_QNA_CHECK_SEARCH_TITLE, Integer.class, keyword);
	}

	@Override
	public int checkContentsQnaCount(String target, String keyword) {
		return jtem.queryForObject(SQL_QNA_CHECK_SEARCH_CONTENT, Integer.class, keyword);
	}

	@Override
	public int checkMembersQnaCount(String target, String keyword) {
		return jtem.queryForObject(SQL_QNA_CHECK_SEARCH_MEMBERS, Integer.class, keyword);
	}

	@Override
	public List<QnaBoardRowVO> searchQnaForAll(String keyword, int offset, int limit) {

		return jtem.query(SQL_QNA_SEARCH_ALL, BeanPropertyRowMapper.newInstance(QnaBoardRowVO.class), keyword, keyword,
				keyword, offset, limit);
	}

	@Override
	public List<QnaBoardRowVO> searchQnaForTitle(String keyword, int offset, int limit) {
		return jtem.query(SQL_QNA_SEARCH_TITLE, BeanPropertyRowMapper.newInstance(QnaBoardRowVO.class), keyword, offset,
				limit);
	}

	@Override
	public List<QnaBoardRowVO> searchQnaForContent(String keyword, int offset, int limit) {
		return jtem.query(SQL_QNA_SEARCH_CONTENT, BeanPropertyRowMapper.newInstance(QnaBoardRowVO.class), keyword,
				offset, limit);
	}

	@Override
	public List<QnaBoardRowVO> searchQnaForPostMember(String keyword, int offset, int limit) {
		return jtem.query(SQL_QNA_SEARCH_MEMBERS, BeanPropertyRowMapper.newInstance(QnaBoardRowVO.class), keyword,
				offset, limit);
	}

	///////////////
	@Override
	public List<QnaBoardVO> selectAllQnas() {
		return jtem.query(SQL_QNA_SELECT_ALL, BeanPropertyRowMapper.newInstance(QnaBoardVO.class));
	}

	@Override
	public List<QnaBoardVO> selectAllQnas(boolean order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QnaBoardVO> selectAllQna(int offset, int limit) {
		return jtem.query(SQL_QNA_SELECT_ALL_PG, BeanPropertyRowMapper.newInstance(QnaBoardVO.class), offset, limit);
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

//	검색

	// 마이페이지 리뷰 추가
	@Override
	public List<QnaBoardVO> selectAllQnaForOneMember(int postId) {
		return jtem.query(SQL_QNA_SELECT_ALL_ONE_MEMBER, BeanPropertyRowMapper.newInstance(QnaBoardVO.class), postId);
	};

	public static final String SQL_QNA_SELECT_ALL_ONE_MEMBER = "SELECT * FROM qna_board where post_id = ? order by posted_day desc";

	@Override
	public List<QnaBoardVO> selectAllQnaForOneMember(int offset, int limit, int postId) {
		return jtem.query(SQL_QNA_SELECT_ALL_PG_ONE_MEMBER, BeanPropertyRowMapper.newInstance(QnaBoardVO.class), postId,
				offset, limit);
	}

	public static final String SQL_QNA_SELECT_ALL_PG_ONE_MEMBER = "SELECT * FROM qna_board where post_id = ? order by posted_day desc limit ?, ?";

	@Override
	public int checkAllArticleCount(int postId) {
		return jtem.queryForObject(SQL_QNACHECK_ALL_COUNT_ONE_MEMBER, Integer.class, postId);
		// return 0;
	}

	public static final String SQL_QNACHECK_ALL_COUNT_ONE_MEMBER = "SELECT count(q_id) from qna_board where post_id=?";

}
