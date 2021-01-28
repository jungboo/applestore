package com.javaweb.apple.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.inf.IOrderDAO;
import com.javaweb.apple.model.vo.OrderVO;
import com.javaweb.apple.service.inf.IOrderSVC;

@Service
public class OrderSVCImpl implements IOrderSVC {

	@Autowired
	IOrderDAO odDao;

	@Override
	public boolean addNewOrder(int memberId, int totalPrice, String creditCard, String address, String addressDetail,
			String prIds, String counts) {
		return this.odDao.addNewOrder(memberId, totalPrice, creditCard, address, addressDetail, prIds, counts);
	}

	@Override
	public boolean cancleOrder(int orderId) {
		return this.odDao.cancleOrder(orderId);
	}

	@Override
	public List<OrderVO> searchOrderList(int memberId) {
		return this.odDao.searchOrderList(memberId);
	}

	@Override
	public List<OrderVO> searchOrderList(int memberId, int unit) {
		return this.odDao.searchOrderList(memberId, unit);
	}

	@Override
	public List<OrderVO> selectAllOrders() {
		return this.odDao.selectAllOrders();
	}

	@Override
	public List<OrderVO> selectAllOrders(int page) {
		int offset = (page - 1) * PAGE_SIZE;
		int limit = PAGE_SIZE;
		List<OrderVO> odLisgPg = odDao.selectAllOrders(offset, limit);
		return odLisgPg;
	}

	@Override
	public int checkMaxPageNumber() {
		int totalOdCnt = odDao.checkAllOrderCount();
		int maxPg = totalOdCnt / PAGE_SIZE + (totalOdCnt % PAGE_SIZE == 0 ? 0 : 1);
		return maxPg;
	}

}
