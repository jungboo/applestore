package com.javaweb.apple.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.apple.model.vo.MemberVO;
import com.javaweb.apple.model.vo.ProductVO;
import com.javaweb.apple.service.inf.ICartSVC;
import com.javaweb.apple.service.inf.IMemberSVC;
import com.javaweb.apple.service.inf.IProductSVC;

@Controller
public class CartController
{
	@Autowired
	ICartSVC ctSvc;
	
	@Autowired
	IMemberSVC mbSvc;
	
	

//- 장바구니를 조회하고 결제 페이지로 이동할 수 있다 // 여기서 결제 버튼 클릭시 order_
//	cart_info_form.ap?mbId=x		get form
	@RequestMapping(value = "cart_info_form.ap", method = RequestMethod.GET)
	public ModelAndView cartInfoForm(HttpSession ses)
	{
		ModelAndView mav = new ModelAndView();
		if (ses.getAttribute("mbId") == null)
		{
			mav.addObject("msg", "로그인이 필요한 서비스입니다.");
			mav.setViewName("member/mb_signin");
			return mav;
		}
		int memberId = (int) ses.getAttribute("mbId");
//		int memberId = 1;

		if (this.ctSvc.showCartInfo(memberId) == null)
		{
			System.out.println("장바구니가 비었습니다.");
			mav.setViewName("redirect:main.ap");
			return mav;
		}
		List<Map<String, Object>> ctList = this.ctSvc.showCartInfo(memberId);
		
		
		mav.addObject("amount", ctList.size());
		mav.addObject("ctList", ctList);
		
		
		
		int totalPrice = 0;
		// 총 가격
		for (Map<String, Object> map : ctList)
		{
			totalPrice += Integer.valueOf((String) map.get("price")) * Integer.valueOf((String)map.get("count"));
		}
		mav.addObject("totalPrice", totalPrice);
		
		mav.setViewName("cart/ct_info_form");
//		else
//		{
//			mav.setViewName("main");
//		}
		return mav;
	}

//	cart_buy_proc.ap		post proc dao
	@RequestMapping(value = "cart_buy_proc.ap", method = RequestMethod.POST)
	public ModelAndView cartBuyProc(HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();
		
		
		
		mav.setViewName("order_info_form");
		return  mav;
	}

//- 장바구니에서 특정 상품의 상세보기 페이지로 이동할 수 있다.
//		
//- 상품 하나를 장바구니에 추가할 수 있다.	
//	cart_single_add.ap		get proc dao
	@RequestMapping(value = "cart_single_add.ap", method = RequestMethod.GET)
	public ModelAndView cartSingleAdd(HttpSession ses, HttpServletRequest req)
	{
		ModelAndView mav = new ModelAndView();
		int memberId = (int) ses.getAttribute("mbId");
//		int memberId = 1;
		
		int productId = Integer.valueOf(req.getParameter("prId"));
		int count = Integer.valueOf(req.getParameter("count"));
		
		boolean b = this.ctSvc.addSingleProduct(memberId, productId, count);	
		if(b)
		{
			System.out.println("CartController:: cartSingleAdd: 성공");
			mav.setViewName("redirect:main.ap");
		}
		else
		{
			System.out.println("CartController:: cartSingleAdd: 실패");
			mav.setViewName("redirect:main.ap");
		}
		return mav;
	}

//- 상품 다수개를 장바구니에 추가할 수 있다
//	cart_multi_add.ap		post proc dao
	@RequestMapping(value = "cart_multi_add.ap", method = RequestMethod.POST)
	public ModelAndView cartMultiAdd()
	{
		return null;
	}

//- 장바구니에 상품의 갯수를 수정할 수 있다.
//	cart_count_fix.ap		post proc dao
	@RequestMapping(value = "cart_count_fix.ap", method = RequestMethod.POST)
	public ModelAndView cartCountFix()
	{
		return null;
	}

//- 장바구니 상품 하나를 삭제할 수 있다.
//	cart_delete_one.ap?prId=x		get proc dao
	@RequestMapping(value = "cart_delete_one.ap", method = RequestMethod.GET)
	public ModelAndView cartDeleteOne(HttpSession ses,
									@RequestParam(value = "prId", required = true) String prId)
	{
		ModelAndView mav = new ModelAndView();
		int memberId =  (int) ses.getAttribute("mbId");
//		int memberId = 1;
		int productId = Integer.valueOf(prId);
		
		boolean b = this.ctSvc.deleteOneProduct(memberId, productId);
		if (b)
		{
			System.out.println("삭제 성공");
			mav.setViewName("redirect:cart_info_form.ap");
		}
		else
		{
			System.out.println("cartController:: cartDeleteOne: 삭제 실패!");
			mav.setViewName("cart_info_form.ap");
		}
		return mav;
	}

//- 장바구니 상품 다수개를 삭제할 수 있다
//	cart_delete_multi.ap 	 post proc dao
	@RequestMapping(value = "cart_delete_multi.ap", method = RequestMethod.POST)
	public ModelAndView cartDeleteMulti()
	{
		return null;
	}

//- 장바구니의 모든 상품을 삭제할 수 있다.
//	cart_delete_all.ap		post proc dao
	@RequestMapping(value = "cart_delete_all.ap", method = RequestMethod.POST)
	public ModelAndView cartDeleteAll()
	{
		return null;
	}

//- 장바구니를 갱신할 수 있다.
//	cart_update_proc.ap			post proc dao

}
