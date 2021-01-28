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

import com.javaweb.apple.model.vo.QnAVO;
import com.javaweb.apple.service.inf.IQnASVC;

@Controller
public class QnAController {

	@Autowired
	IQnASVC qaSvc;

//- QnA를 수정 할 수 있다.
//	qa_edit_form.ap
	@RequestMapping(value = "qa_edit_form.ap", method = RequestMethod.GET)
	public String QnAEditForm(Model model, @RequestParam(value = "qaId", defaultValue = "0") int qaId) {
		if (qaId == 0) {
			// return "redirect:article_list.my?pg=1";
			return "redirect:qa_search_proc.ap";
		}
		QnAVO qa = qaSvc.selectOneQnA(qaId);
		if (qa != null) {
			model.addAttribute("qna", qa);
			model.addAttribute("msg", "게시글 편집 폼 준비 성공 " + qaId);
			return "QnA/qa_edit_form";
		} else {
			model.addAttribute("msg", "게시글 편집 폼 준비 실패: 게시글 못찾음.. " + qaId);
			return "redirect:qa_show.ap?qaId=" + qaId;
		}
	}

//	qa_update.ap
	@RequestMapping(value = "qa_update.ap", method = RequestMethod.POST)
	public String QnAUpdateProc(Model model, @ModelAttribute(value = "qna") QnAVO qa) {
		int b = qaSvc.updateQnA(qa);
		if (b == 1) {
			model.addAttribute("msg", "게시글 갱신 성공");
			return "redirect:qa_show.ap?qaId=" + qa.getqId();
		} else {
			System.out.println("갱신 실패");
			model.addAttribute("msg", "게시글 갱신 실패");
			return "QnA/qa_edit_form"; // fw
		}
	}

// QnA를 검색 할 수 있다.
//	qa_search_proc.ap
	@RequestMapping(value = "qa_search_proc.ap", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView QnASearchProc(@RequestParam(value = "keyword", defaultValue = "test") String k,
			@RequestParam(value = "target", required = false, defaultValue = "title") String target,
			@RequestParam(value = "pg", defaultValue = "1", required = false) int pg, HttpSession ses
	// pg
	// order
	) {
		System.out.println(">> keyword: " + k);
		List<QnAVO> qaList = qaSvc.searchQnA(k, target, pg);
		ModelAndView mav = new ModelAndView("QnA/qa_admin");
		if (qaList != null) {
			int qaSize = qaList.size();
			mav.addObject("msg", "게시글 검색 성공: " + qaSize + "개");
			mav.addObject("qaList", qaList);
			mav.addObject("qaSize", qaSize); // 0개 이상
			mav.addObject("pn", pg); // 요청으로 활성화된 페이지 번호
			Map<String, Integer> rMap = qaSvc.checkMaxPageNumber(k, target);
			mav.addObject("maxPg", rMap.get("maxPg")); // 최대 페이지 번호
			mav.addObject("qaMaxSize", rMap.get("totalQaCnt")); // 총 검색일치 레코드 개수
			mav.setViewName("QnA/search_qa_form");
		} else {
			System.out.println("QnA 조회 실패");
			mav.addObject("msg", "게시글 검색 실패"); // null
		}
		return mav;
	}

//- QnA를 상세보기 할 수 있다.
//	qa_show.ap
	@RequestMapping(value = "qa_show.ap", method = RequestMethod.GET)
	public String QnAShowProc(HttpSession ses, int qaId, Model model) {
		QnAVO qa = qaSvc.selectOneQnA(qaId);
		if (qa != null) {
			System.out.println("QnA 조회 성공" + qaId);
			model.addAttribute("msg", "게시글 상세조회 성공: " + qaId);
			model.addAttribute("qa", qa);
			return "QnA/qa_show";
		} else {
			System.out.println("QnA 조회 실패" + qaId);
			model.addAttribute("msg", "게시글 상세조회 실패: " + qaId);
			return "redirect:qa_search_proc.ap";
		}
	}

//- QnA에 답변 할 수 있다.
//	qa_answer_form.ap
	@RequestMapping(value = "qa_answer_form.ap", method = RequestMethod.GET)
	public String QnAAnswerForm(Model model, @RequestParam(value = "qaId", defaultValue = "0") int qaId) {
		if (qaId == 0) {
			System.out.println("qaId =" + qaId);
			// return "redirect:article_list.my?pg=1";
			return "redirect:qa_search_proc.ap";
		}
		QnAVO qa = qaSvc.selectOneQnA(qaId);
		if (qa != null) {
			model.addAttribute("qna", qa);
			model.addAttribute("msg", "게시글 답변 폼 준비 성공 " + qaId);
			return "QnA/qa_answer_form";
		} else {
			model.addAttribute("msg", "게시글 답변 폼 준비 실패: 게시글 못찾음.. " + qaId);
			return "redirect:qa_show.ap?qaId=" + qaId;
		}
	}

//	qa_answer_proc.ap
	@RequestMapping(value = "qa_answer_proc.ap", method = RequestMethod.POST)
	public String QnAAnswerProc(Model model, @ModelAttribute(value = "qna") QnAVO qa) {
		boolean b = qaSvc.answerQnA(qa);
		if (b) {
			model.addAttribute("msg", "답변 성공");
			return "redirect:qa_show.ap?qaId=" + qa.getqId();
		} else {
			System.out.println("답변 실패");
			model.addAttribute("msg", "답변 실패");
			return "QnA/qa_answer_form"; // fw
		}
	}

//- QnA를 삭제 할 수 있다.
//	qa_delete.ap
	@RequestMapping(value = "qa_delete.ap", method = RequestMethod.GET)
	public String QnADeleteProc(Model model, @ModelAttribute(value = "qaId") int qaId, HttpSession ses) {
		boolean b = qaSvc.deleteQnA(qaId);
		if (b) {
			model.addAttribute("msg", qaId + "번 게시글이 삭제 되었습니다.");
			return "redirect:qa_admin.ap";
		} else {
			System.out.println("삭제 실패");
			model.addAttribute("msg", qaId + "번 게시글 삭제 실패");
			return "QnA/qa_admin"; // fw
		}
	}
//	보류
}
