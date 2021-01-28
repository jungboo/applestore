package com.javaweb.apple.controller;

import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.apple.dao.inf.IProductDAO;
import com.javaweb.apple.model.vo.ProductVO;
import com.javaweb.apple.model.vo.SearchVO;
import com.javaweb.apple.service.inf.IProductSVC;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ProductController {

	@Autowired
	IProductSVC pdSvc;

	@Autowired
	IProductDAO pdDAO;



	// 상품 전체 불러오기 + 페이지네이션
	@RequestMapping(value = "product_all_list.ap", method = RequestMethod.GET)
	public ModelAndView productAllShow(
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {
		System.out.println("productAllShow controller 실행");
		System.out.println("productListProc 실행(전체상품 불러오기) + 페이지네이션");

		int maxPg = pdSvc.checkMaxPageNumber();
		if (pageNumber <= 0 || pageNumber > maxPg) {
			System.out.println("잘못된 페이지번호 요청: " + pageNumber);
			return new ModelAndView("redirect:product_all_list.ap?pg=1");
		}

		List<ProductVO> pdList = pdSvc.selectAllProducts(pageNumber);

		ModelAndView mav = new ModelAndView("product/pd_all_list");

		if (pdList != null) {
			int pdSize = pdList.size();
			System.out.println("전체상품 갯수: " + pdSize + "개");
			mav.addObject("pdList", pdList);
			mav.addObject("pdSize", pdSize);
			mav.addObject("pn", pageNumber);

			System.out.println("ctrl maxPg: " + maxPg);
			mav.addObject("maxPg", maxPg);
		} else {
			System.out.println("전체상품 조회 실패(없음)");
		}
		return mav;
	}

	// 상품 전체 불러오기(카테고리별로) + 카테고리별 갯수 페이지네이션
	@RequestMapping(value = "product_category_list.ap", method = RequestMethod.GET)
	public ModelAndView CategoryProductListAndPage(
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber, String category) {
		System.out.println("-------------------------------");
		System.out.println("CategoryProductListAndPage  실행");

		int maxPg = pdSvc.checkMaxCategoryPageNumber(category);
		if (pageNumber <= 0 || pageNumber > maxPg) {
			System.out.println("잘못된 페이지번호 요청: " + pageNumber);
			return new ModelAndView("redirect:product_category_list.ap?pg=1");
		}

		List<ProductVO> pdList = pdSvc.selectCategoryProductsWithPage(category, pageNumber);
		ModelAndView mav = new ModelAndView("product/pd_category_list");

		if (pdList != null) {
			int pdSize = pdList.size();
			mav.addObject("pdSize", pdSize);
			mav.addObject("pdList", pdList);

			mav.addObject("category", category);
			mav.addObject("pn", pageNumber);

			mav.addObject("maxPg", maxPg);

			System.out.println(category + "상품 카테고리 리스트 갯수 조회:" + pdSize + "개");
			System.out.println(category + "의 최대 페이지 수: " + maxPg);
			System.out.println(category + "의 페이지 수: " + pageNumber);
		} else {
			mav.setViewName("redirect:main.ap");
		}
		return mav;

	}

	// 상품 상세보기
	@RequestMapping(value = "product_show.ap", method = RequestMethod.GET)
	public ModelAndView productShowProc(HttpServletRequest req) {
		String pdId = req.getParameter("pdId");
		int id = Integer.parseInt(pdId);
		System.out.println("productShowProc실행(상세보기)");
		ProductVO pd = this.pdSvc.selectOneProduct(id);
		// <<PK>>

		ModelAndView mav = new ModelAndView();
		if (pd != null) {
			System.out.println("선택 하신 상품: " + pd.toString());
			mav.addObject("product", pd);
			mav.setViewName("product/pd_show");
		} else {
			mav.setViewName("redirect:pd_cateogry_list.ap");
		}

		return mav;
	}
	
	// 상품을 검색할수있다
	   // product_search.ap; post form
	   @RequestMapping(value = "product_search.ap", method = {RequestMethod.POST, RequestMethod.GET})
	   public ModelAndView productSearchProc(
			   @RequestParam(value = "keyword", defaultValue = "iPhone") String k,
			   @RequestParam(value = "target", defaultValue = "price") String target,
			   @RequestParam(value = "pg", defaultValue = "1", required = false) int pg){
		   System.out.println(">> keyword: " + k);
		   List<ProductVO> pdList = pdSvc.searchProduct(k, target, pg);
		   ModelAndView mav = new ModelAndView("product/ad_pd_search_form");
		   if( pdList != null ) {
			   int pdSize = pdList.size();
			   mav.addObject("msg", "상품 검색 성공!");
			   mav.addObject("pdList", pdList);
			   mav.addObject("pdSize", pdSize);
			   mav.addObject("pn", pg);
			   Map<String, Integer> rMap = pdSvc.checkMaxPageNumber(k, target);
			   mav.addObject("maxPg", rMap.get("maxPg"));
			   mav.addObject("pdMaxSize", rMap.get("totalPdCnt"));
			   mav.setViewName("product/ad_pd_search_result_form");
		   } else {
			   mav.addObject("msg", "상품 검색 실패");
		   }
		   return mav;
	   }
	   
	// 상품의 이름을 통해 중복 체크
		@RequestMapping(value = "product_dupcheck.ap", method = RequestMethod.GET)
		public String productDupCheck() {
			return null;
		}

		public ProductVO selectOneProduct(String name) {
			return null;
		}
		
		
		// 상품 메인페이지 검색
		   @RequestMapping(value = "product_search_main.ap", method = RequestMethod.GET)
	         @ResponseBody
	         public SearchVO productAllShow(@RequestParam(value = "pg", required = false, defaultValue = "1") int pg,
	               String search) {
	            System.out.println("productAllShow controller 실행");
	            System.out.println("productListProc 실행(전체상품 불러오기) + 페이지네이션");

	            int maxPg = pdSvc.checkMaxSearchPageNumber(search);
	            int totalCount = maxPg;
	            int countList = 4;

	            int totalPage = totalCount / countList;

	            if (totalCount > countList * totalPage) {

	                totalPage++;
	            }
	            if (pg <= 0 || pg > totalPage) {
	               System.out.println("잘못된 페이지번호 요청: " + pg);
	               pg = 1;

	            }

	            List<ProductVO> pdList = pdSvc.showSearchProducts(pg, search);

	            if (pdList == null) {
	               System.out.println("검색에 맞는것이 없음");
	               return null;
	            } else {
	               SearchVO svo = new SearchVO(maxPg, pg, pdList);

	               return svo;
	            }

	         }

}
