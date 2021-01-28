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

import com.javaweb.apple.model.vo.NoticeVO;
import com.javaweb.apple.service.inf.INoticeSVC;
import com.javaweb.apple.service.inf.INoticeSVC_C;

@Controller
public class NoticeController {
	@Autowired
	INoticeSVC_C ntSvc;
	
	@Autowired
	INoticeSVC adNtSvc;

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬, 태깅?) - 해시태그
//	article_list.my?pg=3 ; get proc dao o
//	article_list.my?pageNumber=3 x
//	article_list.my   => 기본 pg=1 동작..	
	@RequestMapping(value = "notice_list.ap", method = RequestMethod.GET)
	public ModelAndView NoticeListProc(
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {
		int maxPg = ntSvc.checkMaxPageNumber();
		if (pageNumber <= 0 || pageNumber > maxPg) {
			System.out.println(">> 잘못된 페이지번호 요청 pg :" + pageNumber);
			return new ModelAndView("redirect:notice_list.ap");
		}
		List<NoticeVO> totalNtList = ntSvc.selectAllNotices();
		List<NoticeVO> ntList = ntSvc.selectAllNotices(pageNumber);

		ModelAndView mav = new ModelAndView("board/notice/notice_list");
		if (ntList != null) {
			int ntSize = ntList.size(); // 1~5
			int totalNtSize = totalNtList.size();
//			mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + ntSize + "개");
			mav.addObject("ntList", ntList);
			mav.addObject("totalNtSize", totalNtSize);
			mav.addObject("ntSize", ntSize); // 0개 이상
			mav.addObject("pn", pageNumber); // 요청으로 활성화된 페이지 번호
			mav.addObject("maxPg", maxPg); // 최대 페이지 번호
		} else {
			mav.addObject("msg", "pg 게시글 리스트 조회 실패"); // null
			mav.setViewName("redirect:notice_list.ap"); // mypage..
		}
		return mav;
	}

//- 게시글을 상세보기 할 수  있다.
//	article_show.my?atId=x  ; get proc, dao
	@RequestMapping(value = "notice_show.ap", method = RequestMethod.GET)
	public String NoticeShowProc(HttpSession ses, int nId, Model model) {
		NoticeVO nt = this.ntSvc.selectOneNotice(nId);
		if (nt != null) {
//			model.addAttribute("msg", "게시글 상세조회 성공: " + nId);
			model.addAttribute("notice_board", nt); // vo
			return "board/notice/notice_show";
		} else {
			model.addAttribute("msg", "게시글 상세조회 실패: " + nId);
			return "redirect:notice_list.ap";
		}
	}

	//- 게시글 검색할 수 있다. 	
	
		@RequestMapping(value = "notice_search.ap", method = { RequestMethod.POST, RequestMethod.GET })
		public ModelAndView noticeSearchProc(
				@RequestParam(value = "keyword", defaultValue = "없음") String k, 
				@RequestParam(value = "target", required = false, defaultValue = "title") String target,
				@RequestParam(value = "pg", defaultValue = "1", required = false) int pg) {
			System.out.println(">> keyword: " + k);
			List<NoticeVO> ntList = ntSvc.searchNotice(k, target, pg);
			//
			ModelAndView mav = new ModelAndView("board/notice/notice_search");
			if( ntList != null ) {
				int ntSize = ntList.size();
//				mav.addObject("msg", "게시글 검색 성공: " + ntSize +"개");
				mav.addObject("ntList", ntList);
				mav.addObject("ntSize", ntSize); // 0개 이상
				// ${pn}, ${param.pg}
				mav.addObject("pn", pg); // 요청으로 활성화된 페이지 번호
				Map<String,Integer> nMap = ntSvc.checkMaxPageNumber(target,k);
				//System.out.println("ctrl: maxPg = " + maxPg );
				mav.addObject("maxPg", nMap.get("maxPg")); // 최대 페이지 번호
				mav.addObject("atMaxSize", nMap.get("totalAtCnt")); // 총 검색일치 레코드 개수
			} else {
				mav.addObject("msg", "게시글 검색 실패"); // null 
			}		
			return mav;
		}

// ---------------------------------------admin-------------------------------------	
	
//	- 공지사항을 작성 할 수 있다.
//	nt_new_form.ap
	@RequestMapping(value = "nt_new_form.ap", method = RequestMethod.GET)
	public String noticeForm() {
		return "notice/nt_new_form";
	}
//	nt_add_proc.ap 
	@RequestMapping(value = "nt_add_proc.ap", method = RequestMethod.POST)
	public ModelAndView NoticeAddProc(String postTitle, String postContents) {
		boolean b = adNtSvc.insertNewNotice(postTitle, postContents);
		ModelAndView mav = new ModelAndView();
		if( b ) {
			System.out.println("공지사항 등록 성공");
			mav.addObject("msg", "등록 성공");
			mav.setViewName("redirect:nt_list.ap");
		} else {
			System.out.println("공지사항 등록 실패");
			mav.addObject("msg", "등록 실패");
			mav.setViewName("notice/nt_admin");
		}
		return mav;
	}
//	- 공지사항 리스트를 조회 활 수 있다.
//	nt_list.ap
	@RequestMapping(value = "nt_list.ap", method = RequestMethod.GET)
	public ModelAndView noticeListProc() {
		List<NoticeVO> ntList = adNtSvc.selectAllNotices(); 
			// 게시글 생성날자의 역순으로 정렬된 기본 게시글 리스트가 조회
		
		ModelAndView mav = new ModelAndView("notice/nt_list");
		if( ntList != null ) {
			int ntSize = ntList.size();
			mav.addObject("msg", "전체 리스트 조회 성공: " + ntSize +"개");
			mav.addObject("ntList", ntList);
			mav.addObject("ntSize", ntSize); // 0개 이상
		} else {
			System.out.println("리스트 조회 실패");
			mav.addObject("msg", "전체 리스트 조회 실패"); // null 
			mav.setViewName("redirect:nt_admin.ap"); 
		}		
		return mav;
	}
	
//- 공지사항을 상세보기 할 수 있다.
//	nt_show.ap
	@RequestMapping(value = "nt_show.ap", 
			method = RequestMethod.GET)
	public String admibNoticeShowProc(HttpSession ses, int ntId, Model model) {
		NoticeVO nt = adNtSvc.selectOneNotice(ntId);
		if( nt != null ) {
			model.addAttribute("msg", "게시글 상세조회 성공: " + ntId);
			model.addAttribute("nt", nt);
			return "notice/nt_show";
		} else {
			model.addAttribute("msg", "게시글 상세조회 실패: " + ntId);
			return "redirect:nt_list.ap";
		}
	}
//- 공지사항을 검색 할 수 있다.
//	nt_search_proc.ap
	@RequestMapping(value = "nt_search_proc.ap",
			method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView noticeSearchProc(
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
		List<NoticeVO> ntList = adNtSvc.searchNotice(k, target, pg);
		ModelAndView mav = new ModelAndView("notice/nt_admin");
		if( ntList != null ) {
			int ntSize = ntList.size(); 
			mav.addObject("msg", "게시글 검색 성공: " + ntSize +"개");
			mav.addObject("ntList", ntList);
			mav.addObject("ntSize", ntSize); // 0개 이상
			mav.addObject("pn", pg); // 요청으로 활성화된 페이지 번호
			Map<String,Integer> rMap = adNtSvc.checkMaxPageNumber(k, target);
			mav.addObject("maxPg", rMap.get("maxPg")); // 최대 페이지 번호
			mav.addObject("ntMaxSize", rMap.get("totalNtCnt")); // 총 검색일치 레코드 개수
			mav.setViewName("notice/search_nt_form");
		} else {
			mav.addObject("msg", "게시글 검색 실패"); // null 
		}		 
		return mav;
	}
//- 공지사항을 수정 할 수 있다.
//	nt_edit_form.ap
	@RequestMapping(value = "nt_edit_form.ap", 
			method = RequestMethod.GET)
	public String noticeEditForm(Model model, 
			@RequestParam(value = "ntId", 
			defaultValue = "0") int ntId) {
		if( ntId == 0 ) {
			//return "redirect:article_list.my?pg=1";
			return "redirect:nt_search_proc.ap";
		}		
		NoticeVO nt = adNtSvc.selectOneNotice(ntId);
		if( nt != null ) {
			model.addAttribute("notice", nt);
			model.addAttribute("msg", "게시글 편집 폼 준비 성공 " + ntId);
			return "notice/nt_edit_form";
		} else {
			model.addAttribute("msg", "게시글 편집 폼 준비 실패: 게시글 못찾음.. " + ntId);
			return "redirect:nt_show.ap?ntId="+ntId;
		}
	}
//	nt_update.ap
	@RequestMapping(value = "nt_update.ap", 
			method = RequestMethod.POST)
	public String noticeUpdateProc(Model model, 
				@ModelAttribute(value = "notice") NoticeVO nt) {		
			int b = adNtSvc.updateNotice(nt);
			if( b == 1 ) {
				model.addAttribute("msg", "게시글 갱신 성공");
				return "redirect:nt_show.ap?ntId="+nt.getnId();
			} else {
				System.out.println("갱신 실패");
				model.addAttribute("msg", "게시글 갱신 실패");
				return "notice/nt_edit_form"; // fw
			}
	}	
//- 공지사항을 삭제 할 수 있다.
//	nt_delete.ap
	@RequestMapping(value = "nt_delete.ap", 
			method = RequestMethod.GET)
	public String noticeDeleteProc(Model model, 
			@ModelAttribute(value = "ntId") int ntId, HttpSession ses) {
		boolean b = adNtSvc.deleteNotice(ntId);
		if( b ) {
			model.addAttribute("msg", ntId + "번 게시글이 삭제 되었습니다.");
			return "redirect:nt_admin.ap";
		} else {
			System.out.println("삭제 실패");
			model.addAttribute("msg", ntId + "번 게시글 삭제 실패");
			return "notice/nt_admin"; // fw
		}
	}
	
	// 리스트에서 삭제
	// nt_delete2.ap
	@RequestMapping(value = "nt_delete2.ap", 
			method = RequestMethod.GET)
	public String noticeDeleteProc2(Model model, 
			@ModelAttribute(value = "ntId") int ntId, HttpSession ses) {
		boolean b = adNtSvc.deleteNotice(ntId);
		if( b ) {
			model.addAttribute("msg", ntId + "번 게시글이 삭제 되었습니다.");
			return "redirect:nt_list.ap";
		} else {
			System.out.println("삭제 실패");
			model.addAttribute("msg", ntId + "번 게시글 삭제 실패");
			return "notice/nt_admin"; // fw
		}
	}
	
}
