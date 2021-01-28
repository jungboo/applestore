package com.javaweb.apple.dao.inf;

import java.util.List;

import com.javaweb.apple.model.vo.CartVO;
import com.javaweb.apple.model.vo.OrderVO;
import com.javaweb.apple.model.vo.ProductVO;

public interface IOrderDAO {

//- 정보를 기입후 결제를 진행할 수 있다. 결제 정보는 Order과 OrderDetail에 저장
//	order_info_form.ap		post form dao
//	order_final_proc.ap		post proc dao
	boolean addNewOrder(int memberId, int totalPrice, String creditCard, String address, String addressDetail,
			String prIds, String counts);

//- 결제한 주문을 취소 할 수 있다.
//	order_cancle.ap?orderId=x get proc dao
	boolean cancleOrder(int orderId);

//- 구매내역을 특정 단위기간으로 확인할 수 있다 // 1=하루, 2=삼일, 3=일주일, 4=한달, 5=3개월
//	order_search.ap?unit=x 	get proc dao
	List<OrderVO> searchOrderList(int memberId);

	List<OrderVO> searchOrderList(int memberId, int unit);

// - 관리자에서 전체 결제 리스트를 볼 수 있다.	
	List<OrderVO> selectAllOrders();

	List<OrderVO> selectAllOrders(int offset, int limit);

	int checkAllOrderCount();

}
