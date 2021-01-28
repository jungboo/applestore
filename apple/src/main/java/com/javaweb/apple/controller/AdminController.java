package com.javaweb.apple.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.apple.MyCode;
import com.javaweb.apple.model.vo.AdminLoginVO;
import com.javaweb.apple.model.vo.OrderVO;
import com.javaweb.apple.model.vo.ProductVO;
import com.javaweb.apple.model.vo.msg.ProductPCardVO;
import com.javaweb.apple.model.vo.stat.QnAStatVO;
import com.javaweb.apple.model.vo.stat.ReviewStatVO;
import com.javaweb.apple.service.inf.IAdminLoginSVC;
import com.javaweb.apple.service.inf.IOrderSVC;
import com.javaweb.apple.service.inf.IProductSVC;
import com.javaweb.apple.service.inf.IQnASVC;
import com.javaweb.apple.service.inf.IReviewSVC;

@Controller
public class AdminController {

	@Autowired
	IProductSVC pdSvc;

	@Autowired
	IQnASVC qaSvc;

	@Autowired
	IReviewSVC rvSvc;
	
	@Autowired
	IOrderSVC odSvc;

// 어드민 통계 폼준비 
	@RequestMapping(value = "admin_stat.ap", method = RequestMethod.GET)
	public String statMain() {
		return "admin/ad_stat";
	}

// 게시글별 댓글관련 도수 통계 - histogram
	@RequestMapping(value = "stat_rv_count.ap", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> reviewCountStatPerArticle() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		List<ReviewStatVO> rvStatList = rvSvc.reviewCountStatPerArticle();

		List<Integer> readCntData = new ArrayList<>(); // series 0 y
		List<Integer> rvIdData = new ArrayList<>(); // x category
		List<String> rvTitleData = new ArrayList<>(); // x category

		//
		for (ReviewStatVO rv : rvStatList) {
			readCntData.add(rv.getReadCount());
			rvIdData.add(rv.getRvId());
			rvTitleData.add(rv.getPostTitle());
		}

		jsonMap.put("readCntData", readCntData);
		jsonMap.put("rvIdData", rvIdData);
		jsonMap.put("rvTitleData", rvTitleData);
		jsonMap.put("msg", "통계!!!");
		jsonMap.put("title", "게시글 조회수 통계");

		return jsonMap;
	}

// 날자별 QnA게시글개수, 조회수 비율 통계 - pie
	@RequestMapping(value = "stat_qa_ratio.ap", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> QnAStatPerArticle(@RequestBody Map<String, Object> range // JSON
	) {
		String sd = (String) range.get("start_date");
		String ed = (String) range.get("end_date");
		//
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (sd == null || sd.isEmpty())
			sd = year + "-01-01"; // 정초
		if (ed == null || ed.isEmpty()) {
			long ts = new Date().getTime() + 86400000L; // 다음날 자정 미만..
			// ed = new SimpleDateFormat("yyyy-MM-dd").format(new Date(ts));
			Date tsDay = new Date(ts);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ed = sdf.format(tsDay);
		}
		System.out.println("sd: " + sd + " ~ ed: " + ed);
		//
		List<QnAStatVO> qaList = qaSvc.QnACountCompSumPerDayRatio(sd, ed);
		System.out.println(qaList.toString());
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		List<Map<String, Object>> ratioList = new ArrayList<Map<String, Object>>();
		for (QnAStatVO qa : qaList) {
			Map<String, Object> pieRatio = new HashMap<String, Object>();
			pieRatio.put("name", "날짜: " + qa.getStDay());
			pieRatio.put("y", qa.getQaCntRatio());
			ratioList.add(pieRatio);
		}

		jsonMap.put("pieName", "날자별 게시글 개수 비율 (%)");
		jsonMap.put("pieData", ratioList);
		jsonMap.put("oriData", qaList);
		return jsonMap;
	}

	// - 상품을 추가 할 수 있다.
//  add_product.ap
	@RequestMapping(value = "admin_add_product.ap", method = RequestMethod.GET)
	public String addProduct() {
		return "product/ad_pd_new_form";
	}

	// 신규 상품 추가 컨트롤러 post
	@RequestMapping(value = "admin_product_add.ap", method = RequestMethod.POST)
	public ModelAndView productAddProc(HttpServletRequest request) {
		HttpSession ses = request.getSession();
		System.out.println("productAddController 실행(신규상품추가하기)");
		// 요청파람, 세션 처리

		String name = request.getParameter("name"); // 상품이름
		int price = Integer.parseInt(request.getParameter("price"));// 상품가격
		String category = request.getParameter("category"); // 카테고리
		String color = request.getParameter("color"); // 상품컬러
		String spec = request.getParameter("spec"); // 스펙
		String image_path = request.getParameter("image_path"); // 이미지경로
		String description = request.getParameter("description"); // 상품설명

		ModelAndView mav = new ModelAndView();

		ProductVO pd = new ProductVO(name, price, category, color, spec, image_path, description);
		boolean b = this.pdSvc.insertNewProduct(pd);

		// view 패스 분기처리 (forward, redirect) => mav 리턴
		if (b) {
			if (b) {
				System.out.println("상품 추가 성공: " + pd.toString());
				mav.setViewName("redirect:manual.ap");
			} else
				System.out.println("상품 추가 실패: " + pd.toString());
			mav.setViewName("product/ad_pd_new_form"); // fw

		} else {
			System.out.println("상품 추가 실패: " + pd.toString());
			mav.setViewName("product/ad_pd_new_form"); // fw
		}

		return mav;
	}

	// - 상품을 조회 할 수 있다. + 페이지네이션
//  pd_list.ap
	@RequestMapping(value = "admin_product_list.ap", method = RequestMethod.GET)
	public ModelAndView AdminproductListPage(
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) { // 사용자에게 보여질 view 결정
		// String을 사용해도 됨.
		System.out.println("productListPage 실행(상품목록페이지 이동하기)");

		int maxPg = pdSvc.checkMaxPageNumber();
		if (pageNumber <= 0 || pageNumber > maxPg) {
			System.out.println("잘못된 페이지번호 요청: " + pageNumber);
			return new ModelAndView("redirect:admin_product_list.ap?pg=1");
		}

		List<ProductVO> pdList = pdSvc.selectAllProducts(pageNumber);
		ModelAndView mav = new ModelAndView("product/ad_pd_list");
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

	// 미리보기 카드
	@RequestMapping(value = "admin_prodcut_card@{name}.ap")
	@ResponseBody
	public ProductPCardVO adminProductProfileCard4Proc(@PathVariable("name") String name) {
		ProductVO pd = this.pdSvc.selectOneProduct(name); // UQ
		if (pd != null) {
			ProductPCardVO pcard = new ProductPCardVO(pd);
			return pcard;
		} else {
			return new ProductPCardVO();
		}
	}

	// - 상품을 상세보기할 수 있다.
	@RequestMapping(value = "admin_product_show.ap", method = RequestMethod.GET)
	public ModelAndView adminProductShowProc(HttpServletRequest req) {
		String pdId = req.getParameter("pdId");
		int id = Integer.parseInt(pdId);
		System.out.println("adminProductShowProc실행(상세보기)");
		ProductVO pd = this.pdSvc.selectOneProduct(id);
		// <<PK>>
		ModelAndView mav = new ModelAndView();
		if (pd != null) {
			System.out.println("선택 하신 상품: " + pd.toString());
			mav.addObject("pd", pd);
			mav.setViewName("product/ad_pd_show");
		} else {
			mav.setViewName("redirect:admin_product_list.ap?pg=1");
		}
		return mav;
	}

	// - 상품을 수정할 수 있다.
	@RequestMapping(value = "admin_product_edit.ap", method = RequestMethod.GET)
	public ModelAndView editProductForm(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("pdId"));

		ProductVO pd = pdSvc.selectOneProduct(id);

		ModelAndView mav = new ModelAndView();
		if (pd != null) {
			mav.setViewName("product/ad_pd_edit_form");
			mav.addObject("pd", pd);
		} else {
			mav.setViewName("redirect:admin_product_show.ap?pdId=" + id);
		}

		return mav;
	}

	// - 상품 수정 proc post
	@RequestMapping(value = "admin_product_edit.ap", method = RequestMethod.POST)
	public ModelAndView editProductProc(HttpServletRequest request) {

		int id = Integer.parseInt(request.getParameter("id"));
		// 원래.. 인증 세션 처리 권한 처리 등. 필요...
		String name = request.getParameter("name"); // *
		int price = Integer.parseInt(request.getParameter("price"));// *
		String category = request.getParameter("category");// *
		String color = request.getParameter("color");// *
		String spec = request.getParameter("spec");
		String image_path = request.getParameter("image_path");
		String description = request.getParameter("description");

		ProductVO pd = new ProductVO(id, name, price, category, color, spec, image_path, description, null);
		ModelAndView mav = new ModelAndView();
		boolean b = this.pdSvc.updateOneProduct(pd);
		if (b == true) {
			mav.setViewName("redirect:admin_product_show.ap?pdId=" + id);
		} else {
			mav.setViewName("redirect:admin_product_show.ap?pdId=" + id);
		}
		return mav;
	}

	// -상품하나를 삭제 할 수 있다.
	@RequestMapping(value = "admin_product_delete.ap", method = RequestMethod.GET)
	public String deleteOneProduct(Model model, @RequestParam(value = "pdId") int pdId, HttpSession ses) {
		boolean b = pdSvc.deleteOneProduct(pdId);
		if (b) {
			model.addAttribute("msg", pdId + "번 상품이 삭제되었습니다.");
			return "redirect:admin_product_list.ap?page=1";
		} else {
			model.addAttribute("msg", pdId + "번 상품이 삭제가 실패했습니다.");
			return "redirect:admin_product_show.ap?pdId=" + pdId;
		}

	}

	// - 상품을 검색 할 수 있다.
//     pd_search_form.ap
	@RequestMapping(value = "admin_product_search_form.ap", method = RequestMethod.GET)
	public String pdSearchForm() {
		return "product/ad_pd_search_form";
	}

	// - 관리자에서 주문 목록을 조회 할 수 있다.
	@RequestMapping(value = "admin_order_list.ap", method = RequestMethod.GET)
	public ModelAndView orderList(@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {

		System.out.println("orderList 실행(전체상품 불러오기) + 페이지네이션");

		int maxPg = pdSvc.checkMaxPageNumber();

		if (maxPg <= 0 || pageNumber > maxPg) {
			System.out.println("잘못된 페이지 요청: " + pageNumber);
			return new ModelAndView("redirect:admin_order_list.ap?pg=1");
		}

		List<OrderVO> odList = odSvc.selectAllOrders(pageNumber);
		ModelAndView mav = new ModelAndView("order/ad_od_list");

		if (odList != null) {
			int odSize = odList.size();
			System.out.println("전체주문: " + odSize + "개");
			mav.addObject("odSize", odSize);
			mav.addObject("odList", odList);
			mav.addObject("pn", pageNumber);
			mav.addObject("maxPg", maxPg);
		} else {
			System.out.println("전체주문목록 조회 실패(없음)");
			mav.setViewName("redirect:admin_stat.ap");
		}

		return mav;
	}

	// - 주문을 취소 할 수 있다.
//     cancle_form.ap
	@RequestMapping(value = "cancel_form.ap", method = RequestMethod.GET)
	public String cancelForm() {
		return "order/cancel_form";
	}

	// - 회원을 검색 할 수 있다.
//		mb_admin.ap
	@RequestMapping(value = "mb_admin.ap", method = RequestMethod.GET)
	public String mbAdmin() {
		return "member/mb_admin";
	}

	// - 공지사항을 검색 할 수 있다.
//		nt_admin.ap
	@RequestMapping(value = "nt_admin.ap", method = RequestMethod.GET)
	public String ntAdmin() {
		return "notice/nt_admin";
	}

	// - 리뷰게시물을 검색 할 수 있다.
//		rv_admin.ap
	@RequestMapping(value = "rv_admin.ap", method = RequestMethod.GET)
	public String rvAdmin() {
		return "review/rv_admin";
	}

	// - QnA게시물을 검색 할 수 있다.
//		qa_admin.ap
	@RequestMapping(value = "qa_admin.ap", method = RequestMethod.GET)
	public String qaAdmin() {
		return "QnA/qa_admin";
	}
}