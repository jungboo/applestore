package com.javaweb.apple.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.apple.model.vo.QnaBoardVO;
import com.javaweb.apple.model.vo.ReviewBoardVO;
import com.javaweb.apple.model.vo.virtual.QnaBoardRowVO;
import com.javaweb.apple.service.inf.IMemberSVC;
import com.javaweb.apple.service.inf.IQnaBoardSVC_C;

@Controller
public class QnaBoardController_C {
	@Autowired
	IQnaBoardSVC_C qbSvc;

	@Autowired
	IMemberSVC mbSvc;

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬, 태깅?) - 해시태그
//	article_list.my?pg=3 ; get proc dao o
//	article_list.my?pageNumber=3 x
//	article_list.my   => 기본 pg=1 동작..	
	@RequestMapping(value = "qna_list.ap", method = RequestMethod.GET)
	public ModelAndView qnaListProc(@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {
		int maxPg = qbSvc.checkMaxPageNumber();
		if (pageNumber <= 0 || pageNumber > maxPg) {
			System.out.println(">> 잘못된 페이지번호 요청 pg :" + pageNumber);
			return new ModelAndView("redirect:qna_list.ap");
		}
		List<QnaBoardVO> totalQbList = qbSvc.selectAllQnas();
		List<QnaBoardVO> qbList = qbSvc.selectAllQnas(pageNumber);

		ModelAndView mav = new ModelAndView("board/qna/qna_list");
		if (qbList != null) {
			int qbSize = qbList.size(); // 1~5
			int totalQbSize = totalQbList.size();
			List<String> mbNameList = new ArrayList<String>();
			for (QnaBoardVO qb : qbList) {
				String mblogin = mbSvc.selectOneMember(qb.getPostId()).getLogin();
				mbNameList.add(mblogin);
			}
//			mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + qbSize +"개");
			mav.addObject("mbNameList", mbNameList);
			mav.addObject("qbList", qbList);
			mav.addObject("totalQbSize", totalQbSize);
			mav.addObject("qbSize", qbSize); // 0개 이상
			mav.addObject("pn", pageNumber); // 요청으로 활성화된 페이지 번호
			mav.addObject("maxPg", maxPg); // 최대 페이지 번호
		} else {
			mav.addObject("msg", "pg 게시글 리스트 조회 실패"); // null
			mav.setViewName("redirect:qna_list.ap"); // mypage..
		}
		return mav;
	}

	// - 회원이 신규 게시글을 등록 할 수 있다.
//		article_new_form.my ; get
	@RequestMapping(value = "qna_new_form.ap", method = RequestMethod.GET)
	public String qnaNewForm() {
		return "board/qna/qna_new_form";
	}

//		review_add.my ; post proc dao
	@RequestMapping(value = "qna_add.ap", method = RequestMethod.POST)
	public ModelAndView qnaAddProc(String postTitle, int postId, String postContents, HttpSession ses) {
		System.out.println("QnaBoardAddProc()");
		int qbRetKey = this.qbSvc.insertNewQnaReturnKey(new QnaBoardVO(postTitle, postId, postContents));
		// <<PK>> return - keyholder 방식...
		ModelAndView mav = new ModelAndView();
		if (qbRetKey > 0) {
			System.out.println("게시글 등록 성공. id :" + qbRetKey);
			// mav.setViewName("redirect:article_list.my");
			mav.setViewName("redirect:qna_show.ap?qId=" + qbRetKey);
			mav.addObject("msg", "게시글 등록 성공.");
		} else {
			System.out.println("게시글 등록 실패.");
			mav.addObject("msg", "게시글 등록 실패.");
			mav.setViewName("qna/qna_new_form");
		}
		return mav;
	}

//- 게시글을 상세보기 할 수  있다.
//	article_show.my?atId=x  ; get proc, dao
	@RequestMapping(value = "qna_show.ap", method = RequestMethod.GET)
	public String qnaShowProc(HttpSession ses, int qId, Model model) {
		QnaBoardVO qb = this.qbSvc.selectOneQna(qId);
		if (qb != null) {
			String mblogin = mbSvc.selectOneMember(qb.getPostId()).getLogin();
			model.addAttribute("mblogin", mblogin);
//			model.addAttribute("msg", "게시글 상세조회 성공: " + qId);
			model.addAttribute("qna_board", qb); // vo
			return "board/qna/qna_show";
		} else {
			model.addAttribute("msg", "게시글 상세조회 실패: " + qId);
			return "redirect:qna_list.ap";
		}
	}
//	//article_show.my?atId=x  ; get proc, dao
//	@RequestMapping(value = "review_show.ap", method = RequestMethod.GET)
//	public String articleShowProc(HttpSession ses, 
//			int rvId, Model model) {
//		ReviewBoardVO at = this.rbSvc.selectOneReview(rvId);
//		if( at != null ) {
//			model.addAttribute("msg", "게시글 상세조회 성공: " + rvId);
//			model.addAttribute("reviw_board", at); //vo
//			return "board/review/review_show";
//		} else {
//			model.addAttribute("msg", "게시글 상세조회 실패: " + rvId);
//			return "redirect:review_list.ap";
//		}
//	}

//	회원이 자신의 게시글을 삭제 할 수 있다.
	@RequestMapping(value = "qna_delete.ap", method = RequestMethod.GET)
	public String qnaDeleteProc(int qId, Model model, HttpSession ses) {
		// 작성자가 맞는지?
		QnaBoardVO qb = this.qbSvc.selectOneQna(qId);
		int writerId = qb.getPostId(); // <<FK>>
		int mbPKId = (Integer) ses.getAttribute("mbId");
		if (writerId != mbPKId) {
			System.out.println(writerId + "," + mbPKId);
			model.addAttribute("msg", "게시글 갱신 실패: 작성자가 아님!!! " + qb.getqId());
			return "redirect:qna_show.ap?rId=" + qb.getqId();
		}
		boolean b = qbSvc.deleteQna(qb.getqId());
		model.addAttribute("b", b);
		if (b) { // OK
			model.addAttribute("msg", "게시글 삭제 성공");
			return "redirect:qna_list.ap";
		} else {
			model.addAttribute("msg", "게시글 삭제 실패: DB ");
			return "redirect:qna_show.ap?qId=" + qb.getqId();
		}
	}

//- 회원이 자신의 게시글 편집/갱신  할 수  있다. (권한/롤?)
	@RequestMapping(value = "qna_edit_form.ap", method = RequestMethod.GET)
	public String qnaEditForm(HttpSession ses, Model model, @RequestParam(value = "qId", defaultValue = "0") int id) {
		if (id == 0) {
			return "redirect:qna_list.ap";
		}
		QnaBoardVO qb = qbSvc.selectOneQna(id);
		if (qb != null) {
			// 작성자가 맞는지?
			int writerId = qb.getPostId(); // <<FK>>
			int mbPKId = (Integer) ses.getAttribute("mbId");
			if (writerId == mbPKId) {
				model.addAttribute("qnaBoard", qb);
//				model.addAttribute("msg", "게시글 편집 폼 준비 성공 " + id);
				return "board/qna/qna_edit_form";
			} else {
				model.addAttribute("msg", "게시글 편집 폼 준비 실패: 작성자가 아님!!! " + id);
				return "redirect:qna_show.ap?qId=" + id;
			}
		} else {
			model.addAttribute("msg", "게시글 편집 폼 준비 실패: 게시글 못찾음.. " + id);
			return "redirect:qna_show.ap?qId=" + id;
		}
	}

//	article_update.my?atId=x ; post proc dao
	@RequestMapping(value = "qna_update.ap", method = RequestMethod.POST)
	public String qnaUpdateProc(Model model, HttpSession ses, @ModelAttribute(value = "qnaBoard") QnaBoardVO qb) {

		// 작성자가 맞는지?
		int writerId = qb.getPostId(); // <<FK>>
		int mbPKId = (Integer) ses.getAttribute("mbId");
		if (writerId != mbPKId) {
			System.out.println("-----------------");
			model.addAttribute("msg", "게시글 갱신 실패: 작성자가 아님!!! " + qb.getqId());
			return "redirect:qna_show.ap?qId=" + qb.getqId();
		}
		//
		boolean b = qbSvc.updateQna(qb);
		model.addAttribute("b", b);
		if (b) { // OK
			System.out.println("----ssssssss");
			model.addAttribute("msg", "게시글 편집 성공 ");
			return "redirect:qna_show.ap?qId=" + qb.getqId();
		} else { // 3.. 4..
			System.out.println("----ddddddddddd");
			model.addAttribute("msg", "게시글 갱신 실패: DB ");
			return "qna/qna_edit_form"; // fw
		}
	}

	// 추가 마이페이지 qna
	@RequestMapping(value = "mypage_qna.ap", method = RequestMethod.GET)
	public ModelAndView mypageQna(HttpSession ses,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {
		int postId = (Integer) ses.getAttribute("mbId");
		int maxPg = qbSvc.checkMaxPageNumber(postId);
		if (pageNumber <= 0 || pageNumber > maxPg) {
			System.out.println(">> 잘못된 페이지번호 요청 pg :" + pageNumber);
			return new ModelAndView("redirect:mypage_main.ap");
		}

		List<QnaBoardVO> totalQbList = qbSvc.selectAllQnaForOneMember(postId);
		List<QnaBoardVO> qbList = qbSvc.selectAllQnaForOneMember(pageNumber, postId);

		ModelAndView mav = new ModelAndView("mypage/mp_mb_qna");
		if (qbList != null) {
			int qbSize = qbList.size(); // 1~5
			int totalQbSize = totalQbList.size();
			List<String> mbNameList = new ArrayList<String>();
			for (QnaBoardVO qb : qbList) {
				String mblogin = mbSvc.selectOneMember(qb.getPostId()).getLogin();
				mbNameList.add(mblogin);
			}
			System.out.println(postId + "," + totalQbSize + "," + qbSize);
//			mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + qbSize +"개");
			mav.addObject("mbNameList", mbNameList);
			mav.addObject("qbList", qbList);
			mav.addObject("totalQbSize", totalQbSize);
			mav.addObject("qbSize", qbSize); // 0개 이상
			mav.addObject("pn", pageNumber); // 요청으로 활성화된 페이지 번호
			mav.addObject("maxPg", maxPg); // 최대 페이지 번호
		} else {
			mav.addObject("msg", "pg 게시글 리스트 조회 실패"); // null
			mav.setViewName("redirect:mypage_main.ap"); // mypage..
		}
		return mav;
	}

	// - 게시글 검색할 수 있다.

	@RequestMapping(value = "qna_search.ap", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView qnaSearchProc(@RequestParam(value = "keyword", defaultValue = "없음") String k,
			@RequestParam(value = "target", required = false, defaultValue = "title") String target,
			@RequestParam(value = "pg", defaultValue = "1", required = false) int pg) {
		System.out.println(">> keyword: " + k);
		List<QnaBoardRowVO> qbList = qbSvc.searchQna(k, target, pg);
		//
		ModelAndView mav = new ModelAndView("board/qna/qna_search");
		if (qbList != null) {
			int qbSize = qbList.size();
//				mav.addObject("msg", "게시글 검색 성공: " + qbSize +"개");
			mav.addObject("qbList", qbList);
			mav.addObject("qbSize", qbSize); // 0개 이상
			// ${pn}, ${param.pg}
			mav.addObject("pn", pg); // 요청으로 활성화된 페이지 번호
			Map<String, Integer> qMap = qbSvc.checkMaxPageNumber(target, k);
			// System.out.println("ctrl: maxPg = " + maxPg );
			mav.addObject("maxPg", qMap.get("maxPg")); // 최대 페이지 번호
			mav.addObject("atMaxSize", qMap.get("totalAtCnt")); // 총 검색일치 레코드 개수
		} else {
			mav.addObject("msg", "게시글 검색 실패"); // null
		}
		return mav;
	}

}
