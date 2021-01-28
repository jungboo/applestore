package com.javaweb.apple.dao.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.apple.dao.inf.ICartDAO;
import com.javaweb.apple.model.vo.CartVO;
import com.javaweb.apple.model.vo.MemberVO;
import com.javaweb.apple.model.vo.ProductVO;

@Repository
public class CartMySqlDAOImpl implements ICartDAO
{

	@Autowired
	private JdbcTemplate jtem;

	private SimpleJdbcInsert simIn;
	

	@Autowired
	public CartMySqlDAOImpl(JdbcTemplate jtem)
	{
		this.jtem = jtem;
		this.simIn = new SimpleJdbcInsert(jtem.getDataSource());
		this.simIn.withTableName("cart");
		this.simIn.usingGeneratedKeyColumns("member_id");
	}
	
	public static final String SEP = "\\|";
	public static final String[] columnNames = {"pr_ids", "names", "prices", "image_paths", "counts"};
	
//	SQL start
	private static final String SQL_SELECT_CART_BY_ID = "select * from cart where member_id = ?;";
	private static final String SQL_ADD_SINGLE_PRODUCT = "update cart set pr_ids = concat(ifnull(pr_ids, ''), ?), "
			+ " names = concat(ifnull(names,''), ?), prices = concat(ifnull(prices,''), ?), "
			+ " image_paths = concat(ifnull(image_paths,''), ?)," 
			+ " counts = concat(ifnull(counts,''), ?)  where member_id = ?";
	private static final String SQL_UPDATE_DELETE = "update cart set pr_ids = ?, names = ?," + 
			" prices = ?, image_paths = ?, counts = ? where member_id = ?";	
	private static final String SQL_CHECK_CART_EXIST = "select count(member_id) from cart where member_id = ?;";
	private static final String SQL_MAKE_ROW = "insert into cart(member_id) values(?);";
	
	private static final String SQL_RESET_CART = 
			"update cart set pr_ids = null, names = null, prices = null, image_paths = null, "
			+ " counts = null where member_id = ?;";
	
	
	
	private static final String SQL_GET_COLUMNS = "select pr_ids, names, prices, image_paths, counts from cart "
			+ " where member_id = ?";

	
	
//	private static final String SQL_
//	SQL end

	@Override
	public CartVO showCartInfo(MemberVO member)
	{
		return null;
	}
	

	@Override
	public CartVO showCartInfo(int memberId)
	{

		try
		{
			return this.jtem.queryForObject(SQL_SELECT_CART_BY_ID, BeanPropertyRowMapper.newInstance(CartVO.class),
					memberId);
		} catch (Exception e)
		{
			System.out.println("CartMySqlDAOImpl:: showCartInfo 에러");
		}

		return null;
	}

	@Override
	public boolean showProductDetail(ProductVO pr)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean showProductDetail(int productId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addSingleProduct(int memberId, ProductVO pr, int count)
	{
		int r = 0;
		try 
		{
			r =this.jtem.update(SQL_ADD_SINGLE_PRODUCT, SEP + pr.getId(), SEP + pr.getName(), SEP + pr.getPrice(), SEP +
					pr.getImage_path(), SEP + count, memberId);
		}
		catch (Exception e) {
			System.out.println("CartMySqlDAOImpl:: addSingleProduct: 에러");
		}
		return r == 1;
		
	}

	@Override
	public boolean addSingleProduct(int memberId, int prId, String name, String price, String imagePath, int count)
	{
		int r = 0;
		try 
		{
			r =this.jtem.update(SQL_ADD_SINGLE_PRODUCT, "|" + prId, "|" + name, "|" + price,
					"|" + imagePath, "|" +count, memberId);
		}
		catch (Exception e) {
			System.out.println("CartMySqlDAOImpl:: addSingleProduct: 에러");
		}
		return r == 1;
	}

	@Override
	public boolean addMultiProduct(List<ProductVO> prs)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fixProductCount(ProductVO pr)
	{
		// TODO Auto-generated method stub
		return false;
	}	
	
	@Override
	public boolean deleteOneProduct(int memberId, String prIds, String names, String prices, String imagePaths,
			String counts)
	{
		return this.jtem.update(SQL_UPDATE_DELETE, prIds, names, prices, imagePaths, counts, memberId) == 1;
	}
	

	@Override
	public boolean deleteMultiProducts(int memberId, List<ProductVO> prs)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAllProducts()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCart()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Map<String, Object> getCartColumns(int memberId)
	{
		Map<String, Object> tMap = (Map<String, Object>) jtem.queryForMap(SQL_GET_COLUMNS, new Object[]{memberId});
		System.out.println(tMap);
		return tMap;
	}
	
	
	@Override
	public boolean checkCartExist(int mbId)
	{
		int r = this.jtem.queryForObject(SQL_CHECK_CART_EXIST, Integer.class,mbId);
		return r >= 1;
	}
	
	
	@Override
	public boolean makeRowByMbId(int mbId)
	{
		return this.jtem.update(SQL_MAKE_ROW, mbId) == 1;
	}
	
	@Override
	public boolean resetCart(int mbId)
	{
		return this.jtem.update(SQL_RESET_CART, mbId) == 1;
	}

}
