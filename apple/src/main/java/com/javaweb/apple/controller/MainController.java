package com.javaweb.apple.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.apple.model.vo.ProductVO;
import com.javaweb.apple.service.inf.IProductSVC;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainController {

	@Autowired
	IProductSVC pdSvc;

//	HttpServletRequest request, 

	@RequestMapping(value = "default.ap", method = RequestMethod.GET)
	public ModelAndView defaultApple(HttpServletRequest request) {
		System.out.println("defaultApple controller 실행");
		System.out.println("productListProc 실행(전체상품 불러오기)");
		List<ProductVO> pdList = pdSvc.selectAllProducts();

		ModelAndView mav = new ModelAndView();
		if (pdList != null) {
			int pdSize = pdList.size();
			System.out.println("전체상품 갯수: " + pdSize + "개");
			mav.addObject("pdList", pdList);
			mav.addObject("pdSize", pdSize);
			mav.setViewName("main");
			return mav;
		} else {
			System.out.println("전체상품 조회 실패(없음)");
			mav.setViewName("redirect:main.ap");
		}
		return mav;
	}

	@RequestMapping(value = "main.ap", method = RequestMethod.GET)
	public ModelAndView mainApple(HttpServletRequest request) {
		System.out.println("mainApple controller 실행");
		System.out.println("productListProc 실행(전체상품 불러오기)");
		List<ProductVO> pdList = pdSvc.selectAllProducts();

		ModelAndView mav = new ModelAndView();
		if (pdList != null) {
			int pdSize = pdList.size();
			System.out.println("전체상품 갯수: " + pdSize + "개");
			mav.addObject("pdList", pdList);
			mav.addObject("pdSize", pdSize);
			mav.setViewName("main");
			return mav;
		} else {
			System.out.println("전체상품 조회 실패(없음)");
			mav.setViewName("redirect:main.ap");
		}
		return mav;
	}

	@RequestMapping(value = "mainpage.ap", method = RequestMethod.GET)
	public ModelAndView mainPageApple(HttpServletRequest request) {
		System.out.println("mainPageApple controller 실행");
		System.out.println("productListProc 실행(전체상품 불러오기)");
		List<ProductVO> pdList = pdSvc.selectAllProducts();

		ModelAndView mav = new ModelAndView();
		if (pdList != null) {
			int pdSize = pdList.size();
			System.out.println("전체상품 갯수: " + pdSize + "개");
			mav.addObject("pdList", pdList);
			mav.addObject("pdSize", pdSize);
			mav.setViewName("main");
			return mav;
		} else {
			System.out.println("전체상품 조회 실패(없음)");
			mav.setViewName("redirect:main.ap");
		}
		return mav;
	}

}
