package com.javaweb.apple.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.javaweb.apple.dao.inf.IOrderDAO;
import com.javaweb.apple.model.vo.OrderVO;

@Repository
public class OrderMySqlDAOImpl implements IOrderDAO {
	@Autowired
	private JdbcTemplate jtem;

	private static final String SQL_ORDER_INSERT = "insert into apple_db.order(member_id, total_price, credit_card, address, address_detail, pr_ids, counts)"
			+ " values(?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_ORDER_SELECT = "select * from apple_db.order where member_id = ?  order by order_date desc";

	private static final String SQL_ORDER_DETAIL_SELECT = "select * from order_detail where order_id = ?";

	private static final String SQL_DELETE_ORDER = "delete from apple_db.order where order_id = ?;";

	private static final String SQL_DELETE_ORDER_DETAIL = "delete from order_detail where order_id = ?;";

	private static final String SQL_SELECT_ALL_ORDERS = "select * from apple_db.order order by order_date desc";

	public static final String SQL_ORDER_SELECT_ALL_PG = "select * from apple_db.order order by order_date desc limit ?, ?";

	public static final String SQL_ORDER_CHECK_ALL_COUNT = "select count(order_id) from apple_db.order";

	@Override
	public boolean addNewOrder(int memberId, int totalPrice, String creditCard, String address, String addressDetail,
			String prIds, String counts) {
		return this.jtem.update(SQL_ORDER_INSERT, memberId, totalPrice, creditCard, address, addressDetail, prIds,
				counts) == 1;
	}

	@Override
	public boolean cancleOrder(int orderId) {
		return this.jtem.update(SQL_DELETE_ORDER, orderId) == 1;
	}

	@Override
	public List<OrderVO> searchOrderList(int memberId) {
		return this.jtem.query(SQL_ORDER_SELECT, BeanPropertyRowMapper.newInstance(OrderVO.class), memberId);
	}

	@Override
	public List<OrderVO> searchOrderList(int memberId, int unit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderVO> selectAllOrders() {
		System.out.println("SQL:selectAllOrders");
		return jtem.query(SQL_SELECT_ALL_ORDERS, BeanPropertyRowMapper.newInstance(OrderVO.class));
	}

	@Override
	public List<OrderVO> selectAllOrders(int offset, int limit) {
		System.out.println("SQL:selectAllOrders(페이지네이션)");
		return jtem.query(SQL_ORDER_SELECT_ALL_PG, BeanPropertyRowMapper.newInstance(OrderVO.class), offset, limit);
	}

	@Override
	public int checkAllOrderCount() {
		System.out.println("checkAllOrderCount");
		return jtem.queryForObject(SQL_ORDER_CHECK_ALL_COUNT, Integer.class);
	}

}
