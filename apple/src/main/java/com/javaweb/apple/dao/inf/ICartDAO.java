package com.javaweb.apple.dao.inf;

import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.CartVO;
import com.javaweb.apple.model.vo.MemberVO;
import com.javaweb.apple.model.vo.ProductVO;

public interface ICartDAO
{
	
//- 장바구니를 조회하고 결제 페이지로 이동할 수 있다 // 여기서 결제 버튼 클릭시 order_
//	cart_info_form.ap		post form
//	cart_buy_proc.ap		post proc dao
	CartVO showCartInfo(MemberVO member); // 아마 product sql을 참조하게될 것 같다
	CartVO showCartInfo(int memberId);
	
// - 장바구니에서 특정 상품의 상세보기 페이지로 이동할 수 있다. 
	boolean showProductDetail(ProductVO pr);
	boolean showProductDetail(int productId);
	
//- 상품 하나를 장바구니에 추가할 수 있다.	
//	cart_single_add.ap		post proc dao
	boolean addSingleProduct(int memberId, ProductVO pr, int count);
	boolean addSingleProduct(int memberId, int prId, String name, String price, String imagePath, int count); 
	
//- 상품 다수개를 장바구니에 추가할 수 있다
//	cart_multi_add.ap		post proc dao
	boolean addMultiProduct(List<ProductVO> prs); 
	
//- 장바구니에 상품의 갯수를 수정할 수 있다.
//	cart_count_fix.ap		post proc dao
	boolean fixProductCount(ProductVO pr);
	
//- 장바구니 상품 하나를 삭제할 수 있다.
//	cart_delete_one.ap?prId=x	get proc dao
	boolean deleteOneProduct(int memberId, String prIds, String names, String prices, String imagePaths, String counts);
	
//- 장바구니 상품 다수개를 삭제할 수 있다
//	cart_delete_multi.ap?prId0=x&prId1=y... get proc dao
	boolean deleteMultiProducts(int memberId, List<ProductVO> prs);

//- 장바구니의 모든 상품을 삭제할 수 있다.
//	cart_delete_all.ap		post proc dao
	boolean deleteAllProducts();
	
//- 장바구니를 갱신할 수 있다.
//	cart_update_proc.ap			post proc dao
	boolean updateCart();
	
	
	// member_id를 제외한 모든 컬럼을 소환
	public Map<String, Object> getCartColumns(int memberId);
	
	
	// 체크 카트
	public boolean checkCartExist(int mbId);
	
	
	public boolean makeRowByMbId(int mbId);
	
	
	// 리셋 카트
	public boolean resetCart(int mbId);
	
}
