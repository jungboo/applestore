package com.javaweb.apple.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaweb.apple.dao.inf.IAdminLoginDAO;
import com.javaweb.apple.model.vo.AdminLoginVO;

@Repository // MVC 컨테이너 빈 자동 등록: 저장소 DAO의 역할을 맡은 컴포넌트 빈으로 등록
public class AdminLoginMySqlDAOimpl implements IAdminLoginDAO
{
	// Rowmapper 정의 (내자/내부클래스)
	// AdminLoginVO <==> admin_login TBL record
	private class AdminLoginRowMapper implements RowMapper<AdminLoginVO>
	{
		// 콜백
		@Override
		public AdminLoginVO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			return new AdminLoginVO(rs.getInt("id"), rs.getString("login"), rs.getString("pw"));
		}
	}

	private AdminLoginRowMapper alRowMapper;

	public AdminLoginMySqlDAOimpl()
	{
		this.alRowMapper = new AdminLoginRowMapper();
	}

	// SQL 정의부
	private static String SQL_INSERT_ADMIN_CRYPTO = "insert into admin_login values(null, ?, hex(aes_encrypt(?, ?)))";
	private static String SQL_ADMIN_DUPCHECK = "select count(id) from admin_login where login=?";
	private static String SQL_DECRYPT_PW = "select id, login, cast(aes_decrypt(unhex(pw),?)as char(32) character set utf8) as pw from admin_login where login=?";
	private static String SQL_SELECT_ONE_ADMIN_LOGIN = "select * from admin_login where login=?";

	// auto 자동 주입
	@Autowired
	private JdbcTemplate jtem;

	@Override
	public boolean insertNewAdminWithCrypto(AdminLoginVO al)
	{
		int r = this.jtem.update(SQL_INSERT_ADMIN_CRYPTO, al.getLogin(), al.getPw(), al.getLogin());
		return r == 1;
	}

	@Override
	public boolean isDuplicatedAdmin(String login)
	{
		int r = this.jtem.queryForObject(SQL_ADMIN_DUPCHECK, Integer.class, login); // 쿼리 결과 RS 테이블의 값을 정수 매핑
		return r >= 1;
	}

	@Override
	public AdminLoginVO selectOneAdmin(String login)
	{
		try
		{
			return this.jtem.queryForObject(SQL_SELECT_ONE_ADMIN_LOGIN, new RowMapper<AdminLoginVO>()
			{
				@Override
				public AdminLoginVO mapRow(ResultSet rs, int rowNum) throws SQLException
				{
					return new AdminLoginVO(rs.getInt("id"), rs.getString("login"), rs.getString("pw"));
				}
			}, login);
		} catch (EmptyResultDataAccessException dae)
		{
			System.out.println("dao: 관리자 조회 실패 empty login - " + login);
			return null;
		} catch (DataAccessException dae)
		{
			// DataAccessException spring dao에서 최상위 예외 객체
			System.out.println("dao: 관리자 조회 실패 dae login - " + login);
			return null;
		}
	}

	@Override
	public String decryptPassword(String login)
	{
		Map<String, Object> rMap = jtem.queryForMap(SQL_DECRYPT_PW, login, login);
		int id = (int) rMap.get("id");
		String pw = (String) rMap.get("pw"); // blob 에러 - cast char
		String loginDB = (String) rMap.get("login");
		return pw;
	}

}
