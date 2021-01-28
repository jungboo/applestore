package com.javaweb.apple.dao.impl;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaweb.apple.dao.inf.IMemberDAO;
import com.javaweb.apple.model.vo.MemberVO;

@Repository // 자동 빈저장
public class MemberMySqlDAOImpl implements IMemberDAO {

	@Autowired
	private JdbcTemplate jtem;
	private static String SQL_INSERT_MEMBER_CRYPO = "insert into members values(null,"
			+ " ?,?,hex(aes_encrypt(?,?)),?,?,?,?,?,?,now())";
	private static String SQL_MEMBER_DUPLICATE = "select count(id) from members where login =?";
	private static String SQL_SELECT_ONE_MEMBER_BYID = "select * from members where id = ?";
	private static String SQL_SELECT_ONE_MEMBER_BYLOGIN = "select * from members where login = ?";
	private static String SQL_UPDATE_ONE_MEMBER_BYID = "update members set name =? , password= hex(aes_encrypt(?,?)), birthday= ? ,email = ? , "
			+ "phone =? , zipcode= ? , address = ? , address_detail = ? where id =? ";
	private static String SQL_UPDATE_ONE_MEMBER_BYLOGIN = "update members set name =? , password= hex(aes_encrypt(?,?)), birthday= ? ,email = ? , "
			+ "phone =? , zipcode= ? , address = ? , address_detail=? where login =? ";
	private static String SQL_UPDATE_ONE_MEMBER_BYLOGIN_FOR_MYPAGE = "update members set name =? , birthday= ? ,email = ? , "
			+ "phone =? , zipcode= ? , address = ? , address_detail=? where login =? ";
	private static String SQL_SELECT_ALL_MEMBERS = "select * from members order by id asc";
	private static String SQL_DECRYPTPASSWORD_BYLOGIN = "select cast(aes_decrypt(unhex(password),?) as char(32) character set utf8) as password from members where login = ? ";
	private static String SQL_DELETE_ONE_MEMBER_BYID = "delete from members where id =?";
	private static String SQL_DELETE_ONE_MEMBER_BYlOGIN = "delete from members where login =?";
	private static String SQL_SELECT_ONE_MEMBER_BYNAMEANDPHONE = "select login from members where name = ? and phone = ?";
	private static String SQL_SELECT_ONE_MEMBER_BYLOGINANDEMAIL = "select password from members where login = ? and email = ?";
	private static String SQL_SELECT_ONE_MEMBER_BYLOGINANDEMAIL2 = "select * from members where login = ? and email = ?";
	private static String SQL_SELECT_MEMBERS_BYLOGIN = "select * from members where login like concat('%',?,'%') order by join_day desc limit ?,?";
	private static String SQL_SELECT_MEMBERS_BYEMAIL = "select * from members where email like concat('%',?,'%') order by join_day desc limit ?,?";
	private static String SQL_SELECT_MEMBERS_BYPHONE = "select * from members where phone like concat('%',?,'%') order by join_day desc limit ?,?";
	private static String SQL_SELECT_MEMBERS_BYJOINDAY = "select * from members where join_day like concat('%',?,'%') order by join_day desc limit ?,?";
	private static String SQL_SELECT_MEMBERS_BYNAME = "select * from members where name like concat('%',?,'%') order by join_day desc limit ?,?";
	private static String SQL_SELECT_MEMBERS_BYALL = "select * from members where name like concat('%',?,'%') or login like concat('%',?,'%') or "
			+ "email like concat('%',?,'%') or phone like concat('%',?,'%') order by join_day desc limit ?,?";
	// 이름 로그인 이메일 휴대폰 번호 생년월일로 일괄 조회
	
	// -------------------- admin sql 추가 --------------------
		private static String SQL_MEMBERS_CHEKC_SEARCH_LOGIN_PG = "select count(*) as searchCnt from members where login like concat('%',?,'%')";
		private static String SQL_MEMBERS_CHEKC_SEARCH_EMAIL_PG = "select count(*) as searchCnt from members where email like concat('%',?,'%')";
		private static String SQL_MEMBERS_CHEKC_SEARCH_PHONE_PG = "select count(*) as searchCnt from members where phone like concat('%',?,'%')";
//		private static String SQL_MEMBER_CHEKCS_SEARCH_JOINDAY_PG = "select count(*) as searchCnt from members where join_day like concat('%',?,'%')"; 
		private static String SQL_MEMBERS_CHEKC_SEARCH_NAME_PG = "select count(*) as searchCnt from members where name like concat('%',?,'%')"; 
		public static String SQL_MEMBERS_CHEKC_SEARCH_ALL_PG
		= "SELECT count(*) as searchCnt FROM members "
				+ "where name like concat('%',?,'%') or login like concat('%',?,'%') or "
				+ "email like concat('%',?,'%') or phone like concat('%',?,'%')";  

	@Override
	public boolean insertNewMemberWithCrypto(MemberVO mb) { // 로그인으로 암호화
		int r = this.jtem.update(SQL_INSERT_MEMBER_CRYPO, mb.getName(), mb.getLogin(), mb.getPassword(), mb.getLogin(),
				mb.getBirthday(), mb.getEmail(), mb.getPhone(), mb.getZipcode(), mb.getAddress(),
				mb.getAddressDetail());
		return r == 1 ? true : false;
	}

	@Override
	public boolean isDuplicate(String login) {
		int r = this.jtem.queryForObject(SQL_MEMBER_DUPLICATE, Integer.class, login); // 리턴 값이 1 이면 아이디 중복임!
		return r >= 1 ? true : false;
	}

	@Override // 로그인 프로세스 숫자 마다 리턴 값 정해 줘야해
	public int loginProcess(String login, String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO selectOneMember(int id) { // <<PK>>
		try {
			MemberVO mb = this.jtem.queryForObject(SQL_SELECT_ONE_MEMBER_BYID,
					BeanPropertyRowMapper.newInstance(MemberVO.class), id);
			return mb;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("dao 회원 조회 실패" + id);
			return null;
		}

	}

	@Override
	public MemberVO selectOneMember(String login) { // <<UQ>>
		try {
			MemberVO mb = this.jtem.queryForObject(SQL_SELECT_ONE_MEMBER_BYLOGIN,
					BeanPropertyRowMapper.newInstance(MemberVO.class), login);
			return mb;
		} catch (EmptyResultDataAccessException dae) {
			System.out.println("dao: 회원 조회 실패 empty login - " + login);
			return null;
		} catch (DataAccessException dae) {
			// DataAccessException spring dao에서 최상위 예외 객체
			System.out.println("dao: 회원 조회 실패 dae login - " + login);
			return null;
		}
	}

	public MemberVO selectOneMember(String login, String email) { // <<UQ>>
		try {
			MemberVO mb = this.jtem.queryForObject(SQL_SELECT_ONE_MEMBER_BYLOGINANDEMAIL2,
					BeanPropertyRowMapper.newInstance(MemberVO.class), login, email);
			return mb;
		} catch (EmptyResultDataAccessException dae) {
			System.out.println("dao: 회원 조회 실패 empty login - " + login);
			return null;
		} catch (DataAccessException dae) {
			// DataAccessException spring dao에서 최상위 예외 객체
			System.out.println("dao: 회원 조회 실패 dae login - " + login);
			return null;
		}
	}

	@Override
	public boolean updateOneMemberById(MemberVO mb) {
		try {
			int r = this.jtem.update(SQL_UPDATE_ONE_MEMBER_BYID, mb.getName(), mb.getPassword(), mb.getLogin(),
					mb.getBirthday(), mb.getEmail(), mb.getPhone(), mb.getZipcode(), mb.getAddress(),
					mb.getAddressDetail(), mb.getId());
//			"update members set name =? , password= hex(aes_encrypt(?,?)), birthday= ? ,email = ? , "
//			+ "phone =? , zipcode= ? , address = ? , address_detail=? where id =? ";
			return r == 1;
		} catch (DataAccessException e) {
			System.out.println("dao: 회원 갱신 실패 dae - " + mb.getName());
			return false;
		}

	}

	public boolean updateOneMemberByLogin(MemberVO mb) {
		try {
			int r = this.jtem.update(SQL_UPDATE_ONE_MEMBER_BYLOGIN, mb.getName(), mb.getPassword(), mb.getLogin(),
					mb.getBirthday(), mb.getEmail(), mb.getPhone(), mb.getZipcode(), mb.getAddress(),
					mb.getAddressDetail(), mb.getLogin());
//			"update members set name =? , password= hex(aes_encrypt(?,?)), birthday= ? ,email = ? , "
//			+ "phone =? , zipcode= ? , address = ? , address_detail=? where id =? ";
			return r == 1;
		} catch (DataAccessException e) {
			System.out.println("dao: 회원 갱신 실패 dae - " + mb.getName());
			return false;
		}

	}

	public boolean updateOneMemberByLogin2(String name, String birthday, String email, String phone, String zipcode,
			String address, String addressDetail, String login) {
		try {
			int r = this.jtem.update(SQL_UPDATE_ONE_MEMBER_BYLOGIN_FOR_MYPAGE, name, birthday, email, phone, zipcode,
					address, addressDetail, login);
//			"update members set name =? , password= hex(aes_encrypt(?,?)), birthday= ? ,email = ? , "
//			+ "phone =? , zipcode= ? , address = ? , address_detail=? where id =? ";
			System.out.println("dao: 회원 갱신 성공" + login);
			return r == 1;
		} catch (DataAccessException e) {
			System.out.println("dao: 회원 갱신 실패 dae - " + login);
			return false;
		}

	}

	@Override
	public List<MemberVO> selectAllMembers() {
		List<MemberVO> mbList = this.jtem.query(SQL_SELECT_ALL_MEMBERS,
				BeanPropertyRowMapper.newInstance(MemberVO.class));
		return mbList;
	}

	@Override
	public boolean deleteOneMember(int id) { // <PK>
		int r = this.jtem.update(SQL_DELETE_ONE_MEMBER_BYID, new Object[] { id });
		return r == 1;
	}

	@Override
	public boolean deleteOneMember(String login) { // <UQ>
		int r = this.jtem.update(SQL_DELETE_ONE_MEMBER_BYlOGIN, new Object[] { login });
		return r == 1;
	}

	@Override
	public String selectLoginByNameAndPhone(String name, String phone) {
		try {
			Map<String, Object> map = this.jtem.queryForMap(SQL_SELECT_ONE_MEMBER_BYNAMEANDPHONE,
					new Object[] { name, phone }, new int[] { Types.VARCHAR, Types.VARCHAR });
			String login = (String) map.get("login");
			return login;
		} catch (EmptyResultDataAccessException dae) {
			System.out.println("dao: 회원 조회 로그인 없음 ");
			return null;
		} catch (DataAccessException dae) {
			// DataAccessException spring dao에서 최상위 예외 객체
			System.out.println("dao: 회원 조회 로그인 없음 ");
			return null;
		}
	}

	@Override
	public String selectPasswordByLoginAndEmail(String login, String email) {
		try {
			Map<String, Object> map = this.jtem.queryForMap(SQL_SELECT_ONE_MEMBER_BYLOGINANDEMAIL,
					new Object[] { login, email }, new int[] { Types.VARCHAR, Types.VARCHAR });
			String password = (String) map.get("password");
			return password;
		} catch (EmptyResultDataAccessException dae) {
			System.out.println("dao: 회원 조회 로그인 없음 ");
			return null;
		} catch (DataAccessException dae) {
			// DataAccessException spring dao에서 최상위 예외 객체
			System.out.println("dao: 회원 조회 로그인 없음 ");
			return null;
		}
	}

	@Override
	public String decryptPassword(String login) {
		Map<String, Object> map = this.jtem.queryForMap(SQL_DECRYPTPASSWORD_BYLOGIN, new Object[] { login, login },
				new int[] { Types.VARCHAR, Types.VARCHAR });
		String password = (String) map.get("password");
		return password;
	}

	
	// -------------------- admin --------------------
	
	
		@Override
		public List<MemberVO> selectMembersByLogin(String login, int offset, int limit) {
			return this.jtem.query(SQL_SELECT_MEMBERS_BYLOGIN, new Object[] { login, offset, limit },
					BeanPropertyRowMapper.newInstance(MemberVO.class));

		}

		@Override
		public List<MemberVO> selectMembersByEmail(String email, int offset, int limit) {
			return this.jtem.query(SQL_SELECT_MEMBERS_BYEMAIL, new Object[] { email, offset, limit },
					BeanPropertyRowMapper.newInstance(MemberVO.class));
		}

		@Override
		public List<MemberVO> selectMembersByPhone(String phone, int offset, int limit) {
			return this.jtem.query(SQL_SELECT_MEMBERS_BYPHONE, new Object[] { phone, offset, limit },
					BeanPropertyRowMapper.newInstance(MemberVO.class));
		}

		@Override
		public List<MemberVO> selectMembersByJoinDay(String joinDay, int offset, int limit) {
			return null;//this.jtem.query(SQL_SELECT_MEMBERS_BYJOINDAY, new Object[] { joinDay, offset, limit },
					//BeanPropertyRowMapper.newInstance(MemberVO.class));
		}

		@Override
		public List<MemberVO> selectMembersByName(String name, int offset, int limit) {

			return this.jtem.query(SQL_SELECT_MEMBERS_BYNAME, new Object[] { name, offset, limit },
					BeanPropertyRowMapper.newInstance(MemberVO.class));
		}
		@Override
		public List<MemberVO> selectMembersByALL(String keyword, int offset,
				int limit) {
			return this.jtem.query(SQL_SELECT_MEMBERS_BYALL, new Object[] { keyword,keyword,keyword,keyword,offset, limit },
					BeanPropertyRowMapper.newInstance(MemberVO.class));

		}

		@Override
		public int checkAllMembersCount(String keyword) {
			return jtem.queryForObject(SQL_MEMBERS_CHEKC_SEARCH_ALL_PG,
					Integer.class, keyword, keyword, keyword, keyword);
		}

		@Override
		public int checkNameMembersCount(String keyword) {
			return jtem.queryForObject(SQL_MEMBERS_CHEKC_SEARCH_NAME_PG,
					Integer.class, keyword);
		}

		@Override
		public int checkLoginMembersCount(String keyword) {
			return jtem.queryForObject(SQL_MEMBERS_CHEKC_SEARCH_LOGIN_PG,
					Integer.class, keyword);
		}

		@Override
		public int checkEmailMembersCount(String keyword) {
			return jtem.queryForObject(SQL_MEMBERS_CHEKC_SEARCH_EMAIL_PG,
					Integer.class, keyword);
		}

		@Override
		public int checkPhoneMembersCount(String keyword) {
			return jtem.queryForObject(SQL_MEMBERS_CHEKC_SEARCH_PHONE_PG,
					Integer.class, keyword);
		}

		@Override
		public int checkJoinDayMembersCount(String keyword) {
			// TODO Auto-generated method stub
			return 0;
		}
}
