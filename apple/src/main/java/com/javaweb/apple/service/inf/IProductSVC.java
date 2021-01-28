package com.javaweb.apple.service.inf;

import java.util.List;
import java.util.Map;

import com.javaweb.apple.model.vo.ProductVO;

public interface IProductSVC {

//	- 전체 상품 조회  할 수  있다. (고객)
	List<ProductVO> selectAllProducts();

//	- 전체 상품을 카테고리별로 조회를 할 수 있다. (고객)
	List<ProductVO> selectAllProductsByCategory(String category);
	
//	- 상품 정보를 상세보기 할 수 있다.
	ProductVO selectOneProduct(int id); // <<PK>>
	
//	- 상품 정보를 이름으로 상세보기 할 수 있다. (고객)
	ProductVO selectOneProduct(String name); // <<UQ>>
	
	
//	- all 카테고리에서 보여지는 갯수 
	public static final int ALL_PAGE_SIZE = 16;

	List<ProductVO> selectAllProducts(int page);

	int checkMaxPageNumber(); // 최대 페이지 수

//	- 카테고리별 보여지는 갯수
	public static final int CATEGORY_PAGE_SIZE = 4;

	List<ProductVO> selectCategoryProductsWithPage(String category, int page);

	int checkMaxCategoryPageNumber(String category); // 최대 페이지 수


//	- 상품을 추가 할 수  있다.(어드민)
	boolean insertNewProduct(ProductVO pd);
	
//	- 전체 상품 조회  할 수  있다. (어드민)
	List<ProductVO> selectAllAdminProducts(int page);
	
//	- 상품 정보를 편집/갱신 할 수 있다.(어드민)
	boolean updateOneProduct(ProductVO pd);


// - 상품을 검색할 수 있다.(어드민)
	List<ProductVO> searchProduct(String keyword, String target, int pg);

	Map<String, Integer> checkMaxPageNumber(String keyword, String target);

	public static final int PAGE_SIZE = 5; // 페이지당 최대 수

	public static final int SEARCH_PAGE_SIZE = 4; // 검색결과 페이지당 나오는 수

	int checkMaxAdminPageNumber(); // 최대 페이지 수
	
//	- 상품 삭제 할 수 있다.(어드민)
	boolean deleteOneProduct(int id);
	
	// 메인 페이지 상품 검색
	// 검색 할 수 있다 .
    List<ProductVO> showSearchProducts(int page ,String target);
    int checkMaxSearchPageNumber(String target);
}
