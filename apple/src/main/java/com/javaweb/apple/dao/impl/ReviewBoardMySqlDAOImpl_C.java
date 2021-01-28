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

import com.javaweb.apple.dao.inf.IReviewBoardDAO_C;
import com.javaweb.apple.model.vo.MemberVO;
import com.javaweb.apple.model.vo.ReviewBoardVO;
import com.javaweb.apple.model.vo.virtual.ReviewBoardRowVO;

@Repository
public class ReviewBoardMySqlDAOImpl_C implements IReviewBoardDAO_C {
	public static final String SQL_REVIEW_INSERT = "INSERT INTO review_board(post_title, post_id, post_contents , product_score, product_image) values (?,? ,?, ?, ?)";

	public static final String SQL_REVIEW_CHECK_ALL_COUNT = "SELECT count(r_id) FROM review_board";

	public static final String SQL_REVIEW_SELECT_ALL = "SELECT * FROM review_board order by posted_day desc";

	private static final String SQL_REVIEW_SELECT_ALL_PG = "SELECT * FROM review_board order by posted_day desc limit ?, ?";

	private static final String SQL_REVIEW_SELECT_ONE = "select * from review_board where r_id = ? order by posted_day desc";

	private static final String SQL_REVIEW_INCREASE_READCOUNT = "update review_board set read_count = read_count + 1 where r_id = ?";

	private static final String SQL_REVIEW_DELETE = "DELETE FROM review_board WHERE r_id = ?";

	private static final String SQL_REVIEW_UPDATE = "update review_board set post_title=?, post_contents=?, product_score= ?,"
			+ " updated_day=now() where r_id=?";
	// "product_image=?'

	// 검색
	private static final String SQL_REVIEW_SEARCH_TITLE = "select * from (select R.r_id, R.post_title, R.post_contents, (select M.login from members M where M.id = R.post_id) as mbid, R.posted_day, R.read_count,"
			+ " R.product_image, R.product_score, R.post_answer  from review_board R) S where S.post_title like concat('%',?,'%') order by posted_day desc limit ?, ?";

	private static final String SQL_REVIEW_CHECK_SEARCH_TITLE = "select count(*) from (select R.r_id, R.post_title, R.post_contents, (select M.login from members M where M.id = R.post_id) as mbid, R.posted_day, R.read_count,"
			+ " R.product_image, R.product_score, R.post_answer  from review_board R) S where S.post_title like concat('%',?,'%') order by posted_day desc";

	private static final String SQL_REVIEW_SEARCH_CONTENT = "select * from (select R.r_id, R.post_title, R.post_contents, (select M.login from members M where M.id = R.post_id) as mbid, R.posted_day, R.read_count,"
			+ " R.product_image, R.product_score, R.post_answer  from review_board R) S where S.post_contents like concat('%',?,'%') order by posted_day desc limit ?, ?";

	private static final String SQL_REVIEW_CHECK_SEARCH_CONTENT = "select count(*) from (select R.r_id, R.post_title, R.post_contents, (select M.login from members M where M.id = R.post_id) as mbid, R.posted_day, R.read_count,"
			+ " R.product_image, R.product_score, R.post_answer  from review_board R) S where S.post_contents like concat('%',?,'%') order by posted_day desc";

	private static final String SQL_REVIEW_SEARCH_ALL = "select * from (select R.r_id, R.post_title, R.post_contents, (select M.login from members M where M.id = R.post_id) as mbid, R.posted_day, R.read_count,"
			+ "R.product_image, R.product_score, R.post_answer  from review_board R) S where S.post_title like concat('%',?,'%') or S.post_contents"
			+ " like concat('%',?,'%') or S.mbid like concat('%',?,'%') order by posted_day desc limit ?, ?";

	public static final String SQL_REVIEW_CHECK_SEARCH_ALL = "select count(*) from (select R.r_id, R.post_title, R.post_contents, (select M.login from members M where M.id = R.post_id) as mbid, R.posted_day, R.read_count,"
			+ "R.product_image, R.product_score, R.post_answer  from review_board R) S where S.post_title like concat('%',?,'%') or S.post_contents"
			+ " like concat('%',?,'%') or S.mbid like concat('%',?,'%') order by posted_day desc";

	private static final String SQL_REVIEW_SEARCH_MEMBERS = "select * from (select R.r_id, R.post_title, (select M.login from members M where M.id = R.post_id) as mbid, R.posted_day, R.read_count, "
			+ "R.product_image, R.product_score, R.post_answer  from review_board R) S where S.mbid like concat('%',?,'%') order by posted_day desc limit ?, ?";

	private static final String SQL_REVIEW_CHECK_SEARCH_MEMBERS = "select count(*) from (select R.r_id, R.post_title, R.post_contents, (select M.login from members M where M.id = R.post_id) as mbid, R.posted_day, "
			+ "R.read_count, R.product_image, R.product_score, R.post_answer  from review_board R) S where S.mbid like concat('%',?,'%') order by posted_day desc";

	private JdbcTemplate jtem;
	private SimpleJdbcInsert simln;

	@Autowired
	public ReviewBoardMySqlDAOImpl_C(JdbcTemplate jtem) {
		this.jtem = jtem;
		this.simln = new SimpleJdbcInsert(jtem);
		this.simln = new SimpleJdbcInsert(jtem.getDataSource());
		this.simln.withTableName("review_board");
		this.simln.usingGeneratedKeyColumns("rId");// <<PK>>
	}

	@Override
	public boolean insertNewReview(ReviewBoardVO rb) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertNewReview(String postTitle, int postId, String postContent, float productScore,
			String productImage) {
		int r = this.jtem.update(SQL_REVIEW_INSERT,
				new Object[] { postTitle, postId, postContent, productScore, productImage },
				new int[] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.FLOAT, Types.VARCHAR });
		return r == 1;
	}

// 키홀더 리턴 작성ㄴ
	@Override
	public int insertNewReviewReturnKey(ReviewBoardVO rb) {
		// psc-KeyHolder 방식
		System.out.println(">> dao: keyholder...");
		KeyHolder kh = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(SQL_REVIEW_INSERT, new String[] { "rId" });
				// new String[] {"id", "xyz"}) postTitle, postId, postContent, productScore,
				// productImage
				pstmt.setString(1, rb.getPostTitle());
				pstmt.setInt(2, rb.getPostId());// <<FK>>
				pstmt.setString(3, rb.getPostContents());
				pstmt.setFloat(4, rb.getProductScore());
				pstmt.setString(5, rb.getProductImage());
				return pstmt;
			}
		};
		jtem.update(psc, kh);
		Number pk = kh.getKey();
		return pk.intValue(); // reveiw_board.id <<PK>>
	}

	@Override
	public ReviewBoardVO selectOneReview(int rId) {
		try {
			return jtem.queryForObject(SQL_REVIEW_SELECT_ONE, BeanPropertyRowMapper.newInstance(ReviewBoardVO.class),
					rId);
		} catch (EmptyResultDataAccessException e) {
			// 0개
			System.out.println("0개 게시글 조회");
			return null;
		} catch (DataAccessException e) {
			System.out.println("게시글 조회 일반 예외..");
			return null;
		}
	}

// 편집
	@Override
	public boolean updateReview(ReviewBoardVO rb) {
		int r = jtem.update(SQL_REVIEW_UPDATE, rb.getPostTitle(), rb.getPostContents(), rb.getProductScore(),
				rb.getrId());
		return r == 1;
	}

// 조회수
	@Override
	public boolean increaseReadCount(int rId) {
		int r = this.jtem.update(SQL_REVIEW_INCREASE_READCOUNT, rId);
		return r == 1;
	}

// 삭제
	@Override
	public boolean deleteReview(int rId) {
		int r = this.jtem.update(SQL_REVIEW_DELETE, rId);
		return r == 1;
	}

// 페이지네이션
	@Override
	public int checkAllReviewCount() {
		return jtem.queryForObject(SQL_REVIEW_CHECK_ALL_COUNT, Integer.class);
	}

	@Override
	public int checkAllReviewCount(String target, String keyword) {
		return jtem.queryForObject(SQL_REVIEW_CHECK_SEARCH_ALL, Integer.class, keyword, keyword, keyword);
	}

	@Override
	public int checkTitleReviewCount(String target, String keyword) {
		return jtem.queryForObject(SQL_REVIEW_CHECK_SEARCH_TITLE, Integer.class, keyword);
	}

	@Override
	public int checkContentsReviewCount(String target, String keyword) {
		return jtem.queryForObject(SQL_REVIEW_CHECK_SEARCH_CONTENT, Integer.class, keyword);
	}

	@Override
	public int checkMembersReviewCount(String target, String keyword) {
		return jtem.queryForObject(SQL_REVIEW_CHECK_SEARCH_MEMBERS, Integer.class, keyword);
	}

// 전체조회
	@Override
	public List<ReviewBoardVO> selectAllReviews() {
		return jtem.query(SQL_REVIEW_SELECT_ALL, BeanPropertyRowMapper.newInstance(ReviewBoardVO.class));
	}

	@Override
	public List<ReviewBoardVO> selectAllReview(int offset, int limit) {
		return jtem.query(SQL_REVIEW_SELECT_ALL_PG, BeanPropertyRowMapper.newInstance(ReviewBoardVO.class), offset,
				limit);
	}

// 검색
	@Override
	public List<ReviewBoardRowVO> searchReviewForAll(String keyword, int offset, int limit) {

		return jtem.query(SQL_REVIEW_SEARCH_ALL, BeanPropertyRowMapper.newInstance(ReviewBoardRowVO.class), keyword,
				keyword, keyword, offset, limit);
	}

	@Override
	public List<ReviewBoardRowVO> searchReviewForTitle(String keyword, int offset, int limit) {
		return jtem.query(SQL_REVIEW_SEARCH_TITLE, BeanPropertyRowMapper.newInstance(ReviewBoardRowVO.class), keyword,
				offset, limit);
	}

	@Override
	public List<ReviewBoardRowVO> searchReviewForContent(String keyword, int offset, int limit) {
		return jtem.query(SQL_REVIEW_SEARCH_CONTENT, BeanPropertyRowMapper.newInstance(ReviewBoardRowVO.class), keyword,
				offset, limit);
	}

///////////////////////
	@Override
	public List<ReviewBoardRowVO> searchReviewForPostMember(String keyword, int offset, int limit) {
		return jtem.query(SQL_REVIEW_SEARCH_MEMBERS, BeanPropertyRowMapper.newInstance(ReviewBoardRowVO.class), keyword,
				offset, limit);
	}

	// 마이페이지 리뷰 추가
	@Override
	public List<ReviewBoardVO> selectAllReviewsForOneMember(int postId) {
		return jtem.query(SQL_REVIEW_SELECT_ALL_ONE_MEMBER, BeanPropertyRowMapper.newInstance(ReviewBoardVO.class),
				postId);
	};

	public static final String SQL_REVIEW_SELECT_ALL_ONE_MEMBER = "SELECT * FROM review_board where post_id = ? order by posted_day desc";

	@Override
	public List<ReviewBoardVO> selectAllReviewsForOneMember(int offset, int limit, int postId) {
		return jtem.query(SQL_REVIEW_SELECT_ALL_PG_ONE_MEMBER, BeanPropertyRowMapper.newInstance(ReviewBoardVO.class),
				postId, offset, limit);
	}

	public static final String SQL_REVIEW_SELECT_ALL_PG_ONE_MEMBER = "SELECT * FROM review_board where post_id = ? order by posted_day desc limit ?, ?";

	@Override
	public int checkAllArticleCountOneMember(int postId) {
		return jtem.queryForObject(SQL_REVIEW_CHECK_ALL_COUNT_ONE_MEMBER, Integer.class, postId);
		// return 0;
	}

	public static final String SQL_REVIEW_CHECK_ALL_COUNT_ONE_MEMBER = "SELECT count(r_id) from review_board where post_id=?";
	//

}
