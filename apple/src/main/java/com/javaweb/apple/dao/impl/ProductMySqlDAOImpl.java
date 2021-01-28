package com.javaweb.apple.dao.impl;

import java.sql.*;
import java.util.*;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.javaweb.apple.dao.inf.IProductDAO;
import com.javaweb.apple.model.vo.ProductVO;

@Repository
public class ProductMySqlDAOImpl implements IProductDAO {

	private class ProductRowMapper implements RowMapper<ProductVO> {
		// 콜백..
		@Override
		public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ProductVO(rs.getInt("id"), rs.getString("name"), rs.getInt("price"), rs.getString("category"),
					rs.getString("color"), rs.getString("spec"), rs.getString("image_path"),
					rs.getString("description"), rs.getTimestamp("reg_day"));
		}
	}

	@Autowired
	private JdbcTemplate jtem;

	private ProductRowMapper pdRowMapper; // 필드화 공유

	public ProductMySqlDAOImpl() {
		this.pdRowMapper = new ProductRowMapper();
	}

//	SQL 정의부
	private static final String SQL_INSERT_PRODUCT = "insert into products" + " values(null,?,?,?,?,?,?,?,now())";
	private static final String SQL_SELECT_ALL_PRODUCTS = "select * from products order by reg_day desc";
	private static final String SQL_SELECT_CATEGORY_PRODUCTS = "select * from products where category = ?";
	private static final String SQL_SELECT_ONE_PRODUCT = "select * from products where id = ?";
	private final static String SQL_SELECT_ONE_PRODUCT_NAME = "select * from products where name = ?";
	private final static String SQL_UPDATE_PRODUCT = "update products set name = ?, price = ?, category = ?,"
			+ " color = ?, spec = ?, image_path = ?, description = ?, reg_day = now() where id = ?";
	public static final String SQL_PRODUCT_SELECT_ALL_PG = "select * from products order by reg_day desc limit ?, ?";
	public static final String SQL_PRODUCT_CHECK_CATEOGORY_COUNT = "select count(id) from products where category = ?";
	public static final String SQL_PRODUCT_CHECK_ALL_COUNT = "select count(id) from products";
	public static final String SQL_SELECT_CATEGORY_PRODUCTS_PG = "select * from products where category = ? order by reg_day desc limit ?, ?";
	public static final String SQL_DELETE_ONE_PRODUCT_BYID = "delete from products where id=?";
	// 검색
	private final static String SQL_PRODUCT_SEARCH_ALL = "select * from products where name like concat('%',?,'%') or "
			+ " price like concat('%',?,'%') or category like concat('%',?,'%') order by reg_day desc limit ?, ?";
	private final static String SQL_PRODUCT_SEARCH_NAME = "select * from products where name like concat('%',?,'%') "
			+ "order by reg_day desc limit ?, ?";
	private final static String SQL_PRODUCT_SEARCH_PRICE = "select * from products where price like concat('%',?,'%') "
			+ "order by reg_day desc limit ?, ?";
	private final static String SQL_PRODUCT_SEARCH_CATEGORY = "select * from products where category like concat('%',?,'%') "
			+ "order by reg_day desc limit ?, ?";
	private final static String SQL_PRODUCT_CHECK_ALL_PG = "select count(*) as searchCnt from products where name like concat('%',?,'%') or"
			+ " price like concat('%',?,'%') or category like concat('%',?,'%')";
	private final static String SQL_PRODUCT_CHECK_NAME_PG = "select count(*) as searchCnt from products where name like concat('%',?,'%')";
	private final static String SQL_PRODUCT_CHECK_PRICE_PG = "select count(*) as searchCnt from products where price like concat('%',?,'%')";
	private final static String SQL_PRODUCT_CHECK_CATEGORY_PG = "select count(*) as searchCnt from products where category like concat('%',?,'%')";
	private static final String SQL_SEARCH_PRODUCTS_PG = "select * from products where (name like concat('%',?,'%') or spec like concat('%',?,'%') or category like concat('%',?,'%') or description like concat('%',?,'%')) order by name asc limit ?,?";
	private static final String SQL_SEARCH_COUNT_PRODUCTS = "select count(id) from products where (name like concat('%',?,'%') or spec like concat('%',?,'%') or category like concat('%',?,'%') or description like concat('%',?,'%'))";
	// ASC 낮은 가격
	// DESC 높은 가격

	// 상품추가
	@Override
	public boolean insertNewProduct(ProductVO pd) {
		int r = this.jtem.update(SQL_INSERT_PRODUCT, pd.getName(), pd.getPrice(), pd.getCategory(), pd.getColor(),
				pd.getSpec(), pd.getImage_path(), pd.getDescription());
		System.out.println("SQL:updateOneProduct");
		return r == 1;// ? true: false;
	}

	// 1개의 상품 조회(id)
	@Override
	public ProductVO selectOneProduct(int id) {
		System.out.println("SQL:selectOneProductById");
		try {
			ProductVO pd = jtem.queryForObject(SQL_SELECT_ONE_PRODUCT,
					BeanPropertyRowMapper.newInstance(ProductVO.class), id);
			return pd;
		} catch (Exception e) {
			System.out.println("selectOneProductById 조회 실패: " + id + "번");
			return null;
		}
	}

	// 1개의 상품 조회(name)
	@Override
	public ProductVO selectOneProduct(String name) {
		try {
			return jtem.queryForObject(SQL_SELECT_ONE_PRODUCT_NAME, new RowMapper<ProductVO>() {
				@Override
				public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new ProductVO(rs.getInt("id"), rs.getString("name"), rs.getInt("price"),
							rs.getString("category"), rs.getString("color"), rs.getString("spec"),
							rs.getString("image_path"), rs.getString("description"), rs.getTimestamp("reg_day"));
				}

			}, name);
		} catch (EmptyResultDataAccessException dae) {
			System.out.println("DAO: 상품 조회 실패! Empty name - " + name);
			return null;
		} catch (DataAccessException dae) {
			System.out.println("DAO: 상품 조회 실패! " + name);
			return null;
		}
	}

	// 전체 상품 조회
	@Override
	public List<ProductVO> selectAllProducts() {
		System.out.println("SQL:selectAllProducts");
		return jtem.query(SQL_SELECT_ALL_PRODUCTS, BeanPropertyRowMapper.newInstance(ProductVO.class));
	}

	// 카테고리별 전체상품 조회
	@Override
	public List<ProductVO> selectAllProductsByCategory(String category) {
		System.out.println("SQL:selectAllProductsByCategory");
		return jtem.query(SQL_SELECT_CATEGORY_PRODUCTS, BeanPropertyRowMapper.newInstance(ProductVO.class), category);
	}

	// 1개의 상품 수정
	@Override
	public boolean updateOneProduct(ProductVO pd) {
		System.out.println("SQL:updateOneProduct");
		try {
			int r = jtem.update(SQL_UPDATE_PRODUCT, pd.getName(), pd.getPrice(), pd.getCategory(), pd.getColor(),
					pd.getSpec(), pd.getImage_path(), pd.getDescription(), pd.getId());
			return r == 1;
		} catch (DataAccessException dae) {
			System.out.println("DAO: 상품 갱신 실패 - " + pd.toString());
			return false;
		}
	}

	// 1개의 상품 삭제
	@Override
	public boolean deleteOneProduct(int id) {
		int r = this.jtem.update(SQL_DELETE_ONE_PRODUCT_BYID, id);
		System.out.println(r);
		return r== 1;
	}

	// 전체상품 조회 / 페이지네이션
	@Override
	public List<ProductVO> selectAllProducts(int offset, int limit) {
		System.out.println("selectAllProducts");
		return jtem.query(SQL_PRODUCT_SELECT_ALL_PG, BeanPropertyRowMapper.newInstance(ProductVO.class), offset, limit);
	}

	// 카테고리별 상품 조회 / 페이지네이션
	public List<ProductVO> selectAllProductsByCategoryWithPage(String category, int offset, int limit) {
		System.out.println("SQL:selectAllProductsByCategoryWithPage");
		return jtem.query(SQL_SELECT_CATEGORY_PRODUCTS_PG, BeanPropertyRowMapper.newInstance(ProductVO.class), category,
				offset, limit);
	}

	// 상품 전체 갯수
	@Override
	public int checkAllProductCount() {
		System.out.println("SQL:checkAllProductCount");
		return jtem.queryForObject(SQL_PRODUCT_CHECK_ALL_COUNT, Integer.class);
	}

	// 카테고리별 전체 갯수
	@Override
	public int checkCategoryProductCount(String category) {
		System.out.println("SQL:checkCategoryProductCount");
		return jtem.queryForObject(SQL_PRODUCT_CHECK_CATEOGORY_COUNT, Integer.class, category);
	}

	// 가격별로 상품리스트 조회
	@Override
	public List<ProductVO> selectAllProductsByPrice(int offset, int limit) {
		return null;
	}

	@Override
	public int checkMaxAdminPageNumber() {
		return jtem.queryForObject(SQL_PRODUCT_CHECK_ALL_COUNT, Integer.class);
	}

	// 검색
	@Override
	public List<ProductVO> searchProductByAll(String keyword, int offset, int limit) {
		return jtem.query(SQL_PRODUCT_SEARCH_ALL, BeanPropertyRowMapper.newInstance(ProductVO.class), keyword, keyword,
				keyword, offset, limit);
	}

	@Override
	public int checkAllProductCountByALL(String keyword) {
		return jtem.queryForObject(SQL_PRODUCT_CHECK_ALL_PG, Integer.class, keyword, keyword, keyword);
	}

	@Override
	public List<ProductVO> searchProductByName(String keyword, int offset, int limit) {
		return jtem.query(SQL_PRODUCT_SEARCH_NAME, BeanPropertyRowMapper.newInstance(ProductVO.class), keyword, offset,
				limit);
	}

	@Override
	public int checkAllProductCountByName(String keyword) {
		return jtem.queryForObject(SQL_PRODUCT_CHECK_NAME_PG, Integer.class, keyword);
	}

	@Override
	public List<ProductVO> searchProductByPrice(String keyword, int offset, int limit) {
		return jtem.query(SQL_PRODUCT_SEARCH_PRICE, BeanPropertyRowMapper.newInstance(ProductVO.class), keyword, offset,
				limit);
	}

	@Override
	public int checkAllProductCountByPrice(String keyword) {
		return jtem.queryForObject(SQL_PRODUCT_CHECK_PRICE_PG, Integer.class, keyword);
	}

	@Override
	public List<ProductVO> searchProductByCategory(String keyword, int offset, int limit) {
		return jtem.query(SQL_PRODUCT_SEARCH_CATEGORY, BeanPropertyRowMapper.newInstance(ProductVO.class), keyword,
				offset, limit);
	}

	@Override
	public int checkAllProductCountByCategory(String keyword) {
		return jtem.queryForObject(SQL_PRODUCT_CHECK_CATEGORY_PG, Integer.class, keyword);
	}

	@Override
	   public int checksearchProductCount(String target) {
	      try {
	         int r = this.jtem.queryForObject(SQL_SEARCH_COUNT_PRODUCTS, new Object[] { target,target,target,target}, Integer.class);
	         return r;
	      } catch (EmptyResultDataAccessException dae) {
	         System.out.println("존재하지않은 키워드 입니다 실패!");
	         return 0;
	      } catch (DataAccessException dae) {
	         // DataAccessException spring dao에서 최상위 예외 객체
	         System.out.println("존재하지않은 키워드 입니다 실패!");
	         return 0;
	      }
	   }

	// 검색타켓과 페이지네이션
	   @Override
	   public List<ProductVO> searchProductsByTarget(int offset, int limit, String target) {
	      try {
	         List<ProductVO> pdList = this.jtem.query(SQL_SEARCH_PRODUCTS_PG, new Object[] { target,target,target,target,offset,limit},
	               BeanPropertyRowMapper.newInstance(ProductVO.class));
	         return pdList;
	      } catch (EmptyResultDataAccessException dae) {
	         System.out.println("존재하지않은 키워드 입니다 실패!");
	         return null;
	      } catch (DataAccessException dae) {
	         // DataAccessException spring dao에서 최상위 예외 객체
	         System.out.println("존재하지않은 키워드 입니다 실패!");
	         return null;
	      }
	      
	   }

}
