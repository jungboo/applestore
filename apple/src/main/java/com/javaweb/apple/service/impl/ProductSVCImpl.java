package com.javaweb.apple.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.inf.IProductDAO;
import com.javaweb.apple.model.vo.ProductVO;
import com.javaweb.apple.service.inf.IProductSVC;

// 컨테이너 자동 빈등록: MVC service 처리
@Service
public class ProductSVCImpl implements IProductSVC {

	// 필드에 의존관계를 자동 주입
	@Autowired
	IProductDAO pdDao;

	// 상품추가(어드민)
	@Override
	public boolean insertNewProduct(ProductVO pd) {
		return this.pdDao.insertNewProduct(pd);
	}

	// 1개의 상품보기(고객, 어드민)
	@Override
	public ProductVO selectOneProduct(int id) {
		return pdDao.selectOneProduct(id);
	}

	// 1개의 상품보기(고객, 어드민)
	@Override
	public ProductVO selectOneProduct(String name) {
		return pdDao.selectOneProduct(name);

	}

	// 전체상품보기(고객)
	@Override
	public List<ProductVO> selectAllProducts() {
		return pdDao.selectAllProducts();
	}

	// 카테고리 상품 보기(고객)
	@Override
	public List<ProductVO> selectAllProductsByCategory(String category) {
		return pdDao.selectAllProductsByCategory(category);
	}

	// 상품 수정(어드민)
	@Override
	public boolean updateOneProduct(ProductVO pd) {
		return pdDao.updateOneProduct(pd);
	}

	// 상품 삭제(어드민)
	@Override
	public boolean deleteOneProduct(int id) {
		return pdDao.deleteOneProduct(id);
	}

	// 전체상품보기(페이지네이션,고객)
	@Override
	public List<ProductVO> selectAllProducts(int page) {
		int offset = (page - 1) * ALL_PAGE_SIZE;
		// 0, PAGE_SIZE, PAGE_SIZE*2, PAGE_SIZE*3,...
		int limit = ALL_PAGE_SIZE;
		List<ProductVO> pdListPg = pdDao.selectAllProducts(offset, limit);
		System.out.println("pdsvc, pg=" + page + " , pdList= " + pdListPg.size());
		return pdListPg;
	}

	// 전체 상품의 최대 페이지 수 계산(고객)
	@Override
	public int checkMaxPageNumber() {
		int totalPdCnt = pdDao.checkAllProductCount();
		int maxPg = totalPdCnt / ALL_PAGE_SIZE + (totalPdCnt % ALL_PAGE_SIZE == 0 ? 0 : 1);
		// 마지막 페이지에서는 1 ~ (PAGE_SIZE-1)개의 레코드가 존재하면 한페이지 봄.
		return maxPg;
	}

	// 카테고리 전체보기(페이지네이션,고객)
	@Override
	public List<ProductVO> selectCategoryProductsWithPage(String category, int page) {
		int offset = (page - 1) * CATEGORY_PAGE_SIZE;
		int limit = CATEGORY_PAGE_SIZE;
		System.out.println("selectCategoryProductsWithPage offset: " + offset);
		System.out.println("selectCategoryProductsWithPage limit: " + limit);
		List<ProductVO> pdListPg = pdDao.selectAllProductsByCategoryWithPage(category, offset, limit);
		return pdListPg;
	}

	// 카테고리 전체보기 최대 페이지 수 계산
	@Override
	public int checkMaxCategoryPageNumber(String category) {
		int totalpdCnt = pdDao.checkCategoryProductCount(category);
		int maxPg = totalpdCnt / CATEGORY_PAGE_SIZE + (totalpdCnt % totalpdCnt == 0 ? 1 : 2);
		System.out.println("maxpg: " + maxPg);
		System.out.println("totalpdcnt: " + totalpdCnt);
		return maxPg;
	}

	// 전체상품 조회(페이지네이션,어드민)
	@Override
	public List<ProductVO> selectAllAdminProducts(int page) {
		int offset = (page - 1) * PAGE_SIZE;
		int limit = PAGE_SIZE;
		List<ProductVO> pdListPg = pdDao.selectAllProducts(offset, limit);
		return pdListPg;
	}

	// 상품검색
	@Override
	public List<ProductVO> searchProduct(String keyword, String target, int pg) {
		List<ProductVO> pdList = null;
		int offset = (pg - 1) * SEARCH_PAGE_SIZE;
		switch (target) {
		case "all":
			pdList = pdDao.searchProductByAll(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "name":
			pdList = pdDao.searchProductByName(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "price":
			pdList = pdDao.searchProductByPrice(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		case "category":
			pdList = pdDao.searchProductByCategory(keyword, offset, SEARCH_PAGE_SIZE);
			break;
		}
		if (pdList != null) {
			System.out.println("검색결과 상품 수: " + pdList.size());
		} else {
			System.out.println("검색결과 null");
		}
		return pdList;
	}

	// 상품 검색
	@Override
	public Map<String, Integer> checkMaxPageNumber(String keyword, String target) {
		int totalPdCnt = 0;
		switch (target) {
		case "all":
			totalPdCnt = pdDao.checkAllProductCountByALL(keyword);
			break;
		case "name":
			totalPdCnt = pdDao.checkAllProductCountByName(keyword);
			break;
		case "price":
			totalPdCnt = pdDao.checkAllProductCountByPrice(keyword);
			break;
		case "category":
			totalPdCnt = pdDao.checkAllProductCountByCategory(keyword);
			break;
		}
		int maxPg = totalPdCnt / SEARCH_PAGE_SIZE + (totalPdCnt % SEARCH_PAGE_SIZE == 0 ? 0 : 1);
		System.out.println("totalPdCnt = " + totalPdCnt);
		Map<String, Integer> rMap = new HashMap<>();
		rMap.put("totalPdCnt", totalPdCnt); // 총 검색일치 레코드 개수
		rMap.put("maxPg", maxPg); // 최대 검색 페이지수
		return rMap;
	}
	
	// 관리자에서 최대 상품 페이지 수 계산
	@Override
	public int checkMaxAdminPageNumber() {
		int totalCnt = pdDao.checkAllProductCount();
		int maxPg = totalCnt / PAGE_SIZE + (totalCnt % PAGE_SIZE == 0 ? 0 : 1);
		return maxPg;
	}
	//메인 페이지 상품 검색
	@Override
	   public List<ProductVO> showSearchProducts(int pg ,String target) {
	      int offset = (pg - 1) * CATEGORY_PAGE_SIZE; // offset은 리미트의 0포함 배수로 나와야함
	      int limit = CATEGORY_PAGE_SIZE; // 1페이지당 4개출력
	       List<ProductVO> pdList =pdDao.searchProductsByTarget(offset, limit, target);
	      return pdList;
	   }

	   @Override
	   public int checkMaxSearchPageNumber(String target) {
	      int maxPage = pdDao.checksearchProductCount(target);
	      return maxPage;
	   }

}
