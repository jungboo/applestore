package com.javaweb.apple.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.apple.model.vo.ReviewVO;
import com.javaweb.apple.service.inf.IReviewSVC;

@Controller
public class ReviewController {
	
	@Autowired
	IReviewSVC rvSvc;
	
//- review를 수정 할 수 있다.
//	rv_edit_form.ap
	@RequestMapping(value = "rv_edit_form.ap", 
			method = RequestMethod.GET)
	public String QnAEditForm(Model model, 
			@RequestParam(value = "rvId", 
			defaultValue = "0") int rvId) {
		if( rvId == 0 ) {
			//return "redirect:article_list.my?pg=1";
			return "redirect:rv_search_proc.ap";
		}		
		ReviewVO rv = rvSvc.selectOneReview(rvId);
		if( rv != null ) {
			model.addAttribute("review", rv);
			model.addAttribute("msg", "게시글 편집 폼 준비 성공 " + rvId);
			return "review/rv_edit_form";
		} else {
			model.addAttribute("msg", "게시글 편집 폼 준비 실패: 게시글 못찾음.. " + rvId);
			return "redirect:rv_show.ap?rvId="+rvId;
		}
	}
//	rv_update.ap
	@RequestMapping(value = "rv_update.ap", 
			method = RequestMethod.POST)
	public String QnAUpdateProc(Model model, 
				@ModelAttribute(value = "review") ReviewVO rv) {		
			int b = rvSvc.updateReview(rv);
			if( b == 1 ) {
				model.addAttribute("msg", "게시글 갱신 성공");
				return "redirect:rv_show.ap?rvId="+rv.getrId();
			} else {
				System.out.println("갱신 실패");
				model.addAttribute("msg", "게시글 갱신 실패");
				return "review/rv_edit_form"; // fw
			}
	}
	
// review를 검색 할 수 있다.
//	rv_search_proc.ap
	@RequestMapping(value = "rv_search_proc.ap",
			method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView rvSearchProc(
			@RequestParam(value = "keyword", 
			defaultValue = "test") String k, 
			@RequestParam(value = "target", required = false,
			defaultValue = "title") String target,
			@RequestParam(value = "pg", defaultValue = "1", 
			required = false) int pg, HttpSession ses
			// pg
			// order			
			) {
		System.out.println(">> keyword: " + k);
		List<ReviewVO> rvList = rvSvc.searchReview(k, target, pg);
		ModelAndView mav = new ModelAndView("review/rv_admin");
		if( rvList != null ) {
			int rvSize = rvList.size(); 
			mav.addObject("msg", "게시글 검색 성공: " + rvSize +"개");
			mav.addObject("rvList", rvList);
			mav.addObject("rvSize", rvSize); // 0개 이상
			mav.addObject("pn", pg); // 요청으로 활성화된 페이지 번호
			Map<String,Integer> rMap = rvSvc.checkMaxPageNumber(k, target);
			mav.addObject("maxPg", rMap.get("maxPg")); // 최대 페이지 번호
			mav.addObject("rvMaxSize", rMap.get("totalRvCnt")); // 총 검색일치 레코드 개수
			mav.setViewName("review/search_rv_form");
		} else {
			System.out.println("리뷰 조회 실패");
			mav.addObject("msg", "게시글 검색 실패"); // null 
		}		 
		return mav;
	}
//- review를 상세보기 할 수 있다.
//	rv_show.ap
	@RequestMapping(value = "rv_show.ap", 
			method = RequestMethod.GET)
	public String reviewShowProc(HttpSession ses, int rvId, Model model) {
		ReviewVO rv = rvSvc.selectOneReview(rvId);
		if( rv != null ) {
			System.out.println("리뷰 조회 성공" + rvId);
			model.addAttribute("msg", "게시글 상세조회 성공: " + rvId);
			model.addAttribute("rv", rv);
			return "review/rv_show";
		} else {
			System.out.println("리뷰 조회 실패" + rvId);
			model.addAttribute("msg", "게시글 상세조회 실패: " + rvId);
			return "redirect:rv_search_proc.ap";
		}
	}
	
//- Review에 답변 할 수 있다.
//	rv_answer_form.ap
	@RequestMapping(value = "rv_answer_form.ap", 
			method = RequestMethod.GET)
	public String reviewAnswerForm(Model model, 
			@RequestParam(value = "rvId", 
			defaultValue = "0") int rvId) {
		if( rvId == 0 ) {
			System.out.println("rvId =" + rvId);
			//return "redirect:article_list.my?pg=1";
			return "redirect:rv_search_proc.ap";
		}		
		ReviewVO rv = rvSvc.selectOneReview(rvId);
		if( rv != null ) {
			model.addAttribute("review", rv);
			model.addAttribute("msg", "게시글 답변 폼 준비 성공 " + rvId);
			return "review/rv_answer_form";
		} else {
			model.addAttribute("msg", "게시글 답변 폼 준비 실패: 게시글 못찾음.. " + rvId);
			return "redirect:rv_show.ap?rvId="+rvId;
		}
	}
//	rv_answer_proc.ap
	@RequestMapping(value = "rv_answer_proc.ap", 
			method = RequestMethod.POST)
	public String reviewAnswerProc(Model model, 
				@ModelAttribute(value = "review") ReviewVO rv) {		
			boolean b = rvSvc.answerReview(rv);
			if( b ) {
				model.addAttribute("msg", "답변 성공");
				return "redirect:rv_show.ap?rvId="+rv.getrId();
			} else {
				System.out.println("답변 실패");
				model.addAttribute("msg", "답변 실패");
				return "review/rv_answer_form"; // fw
			}
	}
//- review를 삭제 할 수 있다.
//	rv_delete.ap
	@RequestMapping(value = "rv_delete.ap", 
			method = RequestMethod.GET)
	public String reviewDeleteProc(Model model, 
			@ModelAttribute(value = "rvId") int rvId, HttpSession ses) {		
		boolean b = rvSvc.deleteReview(rvId);
		if( b ) {
			model.addAttribute("msg", rvId + "번 게시글이 삭제 되었습니다.");
			return "redirect:rv_admin.ap";
		} else {
			System.out.println("삭제 실패");
			model.addAttribute("msg", rvId + "번 게시글 삭제 실패");
			return "review/rv_admin"; // fw
		}
	}
//	보류
}
