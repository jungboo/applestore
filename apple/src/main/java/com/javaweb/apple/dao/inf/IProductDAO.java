package com.javaweb.apple.dao.inf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.ProductVO;

public interface IProductDAO {

//	상품 조회 + 페이지 네이션
	List<ProductVO> selectAllProducts(); //고객,어드민
	List<ProductVO> selectAllProductsByCategory(String category); // 고객
	List<ProductVO> selectAllProducts(int offset, int limit); // 고객,어드민
	int checkAllProductCount(); // 고객
	int checkMaxAdminPageNumber(); //어드민

// 	- 고객화면의 카테고리 페이지네이션(고객)
	List<ProductVO> selectAllProductsByCategoryWithPage(String category, int offset, int limit);
	int checkCategoryProductCount(String category);
	
	
	
//	- 상품 정보를 상세보기 할 수 있다.
	ProductVO selectOneProduct(int id); // 고객,어드민
	ProductVO selectOneProduct(String name); // 고객

//	- 상품 전체를 가격별로 조회 가능하다.
	List<ProductVO> selectAllProductsByPrice(int offset, int limit);
	

//	- 상품을 추가 할 수  있다.(어드민)
	boolean insertNewProduct(ProductVO pd);


//	- 상품 정보를 편집/갱신 할 수 있다.(어드민)
	boolean updateOneProduct(ProductVO pd);

// - 상품을 검색 할 수 있다
	List<ProductVO> searchProductByAll(String keyword, int offset, int limit);

	int checkAllProductCountByALL(String keyword);

// 상품명으로 검색 할 수 있다.
	List<ProductVO> searchProductByName(String keyword, int offset, int limit);

	int checkAllProductCountByName(String keyword);

// 상품 가격으로 검색 할 수 잇다.
	List<ProductVO> searchProductByPrice(String keyword, int offset, int limit);

	int checkAllProductCountByPrice(String keyword);

// 상품 카테고리로 검색 할 수 있다.
	List<ProductVO> searchProductByCategory(String keyword, int offset, int limit);

	int checkAllProductCountByCategory(String keyword);

//	- 상품 삭제 할 수 있다.(어드민)
	boolean deleteOneProduct(int id);
	int checksearchProductCount(String target);
	   List<ProductVO> searchProductsByTarget(int offset, int limit , String target);
}
