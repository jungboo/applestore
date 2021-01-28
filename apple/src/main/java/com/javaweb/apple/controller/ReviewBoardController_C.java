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

import com.javaweb.apple.model.vo.ReviewBoardVO;
import com.javaweb.apple.model.vo.virtual.ReviewBoardRowVO;
import com.javaweb.apple.service.inf.IFileManageSVC;
import com.javaweb.apple.service.inf.IMemberSVC;
import com.javaweb.apple.service.inf.IReviewBoardSVC_C;

@Controller
public class ReviewBoardController_C {
	@Autowired
	IReviewBoardSVC_C rbSvc;

	@Autowired
	IFileManageSVC fileSvc;

	@Autowired
	IMemberSVC mbSvc;

	// - 게시글 리스트를 조회할 수 있다. (페이지네이션, 정렬, 태깅?) - 해시태그
//	article_list.my?pg=3 ; get proc dao o
//	article_list.my?pageNumber=3 x
//	article_list.my   => 기본 pg=1 동작..	
	@RequestMapping(value = "review_list.ap", method = RequestMethod.GET)
	public ModelAndView reviewListProc(
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {
		int maxPg = rbSvc.checkMaxPageNumber();
		if (pageNumber <= 0 || pageNumber > maxPg) {
			System.out.println(">> 잘못된 페이지번호 요청 pg :" + pageNumber);
			return new ModelAndView("redirect:review_list.ap");
		}
		List<ReviewBoardVO> totalRbList = rbSvc.selectAllReviews();
		List<ReviewBoardVO> rbList = rbSvc.selectAllReviews(pageNumber);

		ModelAndView mav = new ModelAndView("board/review/review_list");
		if (rbList != null) {
			int rbSize = rbList.size(); // 1~5
			int totalRbSize = totalRbList.size();
			List<String> mbNameList = new ArrayList<String>();
			for (ReviewBoardVO rb : rbList) {
				String mblogin = mbSvc.selectOneMember(rb.getPostId()).getLogin();
				mbNameList.add(mblogin);
			}
//			mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + rbSize + "개");
			mav.addObject("mbNameList", mbNameList);
			mav.addObject("rbList", rbList);
			mav.addObject("totalRbSize", totalRbSize);
			mav.addObject("rbSize", rbSize); // 0개 이상
			mav.addObject("pn", pageNumber); // 요청으로 활성화된 페이지 번호
			mav.addObject("maxPg", maxPg); // 최대 페이지 번호
		} else {
			mav.addObject("msg", "pg 게시글 리스트 조회 실패"); // null
			mav.setViewName("redirect:review_list.ap"); // mypage..
		}
		return mav;
	}

//- 회원이 신규 게시글을 등록 할 수 있다. (세션, 파일 업로드-사진/첨부)
//	article_new_form.my ; get
	@RequestMapping(value = "review_new_form.ap", method = RequestMethod.GET)
	public String articleNewForm() {
		return "board/review/review_new_form";
	}

//	review_add.my ; post proc dao
	@RequestMapping(value = "review_add.ap", method = RequestMethod.POST)
	public ModelAndView reviewAddProc(String postTitle, int postId, String postContents, float productScore,
			List<MultipartFile> upfiles, HttpSession ses) {
		System.out.println("ReviewBoardAddProc()");
		String realPath = ses.getServletContext().getRealPath(IFileManageSVC.DEF_UPLOAD_DEST + "/");
		System.out.println(">> realPath: " + realPath);
		HashMap<String, Object> rMap = fileSvc.writeMultipleUploadFilesWithInfo(upfiles, realPath, ses); // 다수 파일 업로드
																											// volume
																											// info
		String productImage = (String) rMap.get("multiFilePaths");
//		double totalMB = (Double) rMap.get("totalMB");
//		int totalFiles = (int) rMap.get("totalFiles");
		int rvRetKey = this.rbSvc.insertNewReviewReturnKey(
				new ReviewBoardVO(postTitle, postId, postContents, productScore, productImage));
		// <<PK>> return - keyholder 방식...
		ModelAndView mav = new ModelAndView();
		if (rvRetKey > 0) {
			System.out.println("게시글 등록 성공. id :" + rvRetKey);
			// mav.setViewName("redirect:article_list.my");
			mav.setViewName("redirect:review_show.ap?rId=" + rvRetKey);
			mav.addObject("msg", "게시글 등록 성공.");
		} else {
			System.out.println("게시글 등록 실패.");
			mav.addObject("msg", "게시글 등록 실패.");
			mav.setViewName("review/review_new_form");
		}
		return mav;
	}

//- 게시글을 상세보기 할 수  있다.
//	article_show.my?atId=x  ; get proc, dao
	@RequestMapping(value = "review_show.ap", method = RequestMethod.GET)
	public String reviewShowProc(HttpSession ses, int rId, Model model) {
		ReviewBoardVO rb = this.rbSvc.selectOneReview(rId);
		if (rb != null) {
			String mblogin = mbSvc.selectOneMember(rb.getPostId()).getLogin();
			model.addAttribute("mblogin", mblogin);
//			model.addAttribute("msg", "게시글 상세조회 성공: " + rId);
			model.addAttribute("review_board", rb); // vo
			// 멀티 파일 경로 시... 다수개 파일 경로를 배열, 리스트로 속성화...
			String rbFilePath = rb.getProductImage();
			int fpCount = -1; // 첨부파일경로 개수
			String[] fps = null;
			if (rbFilePath != null && !rbFilePath.isEmpty()) {
				if (rbFilePath.indexOf(IFileManageSVC.DEF_MUTLI_SEPARATOR) != -1) {
					fps = rbFilePath.split("\\|");
					fpCount = fps.length;
				} else {
					fpCount = 1;
					fps = new String[] { rbFilePath };
				}
				model.addAttribute("fps", fps);
			} else {
				// 첨부파일이 하나도 없는 정상 게시글 상세보기
				fpCount = 0;
			}
			model.addAttribute("fpCount", fpCount);
			return "board/review/review_show";
		} else {
			model.addAttribute("msg", "게시글 상세조회 실패: " + rId);
			return "redirect:review_list.ap";
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
	@RequestMapping(value = "review_delete.ap", method = RequestMethod.GET)
	public String reviewDeleteProc(int rId, Model model, HttpSession ses) {
		// 작성자가 맞는지?
		ReviewBoardVO rb = this.rbSvc.selectOneReview(rId);
		int writerId = rb.getPostId(); // <<FK>>
		int mbPKId = (Integer) ses.getAttribute("mbId");
		if (writerId != mbPKId) {
			System.out.println(writerId + "," + mbPKId);
			model.addAttribute("msg", "게시글 갱신 실패: 작성자가 아님!!! " + rb.getrId());
			return "redirect:review_show.ap?rId=" + rb.getrId();
		}
		boolean b = rbSvc.deleteReview(rb.getrId());
		model.addAttribute("b", b);
		if (b) { // OK
			model.addAttribute("msg", "게시글 삭제 성공");
			return "redirect:review_list.ap";
		} else {
			model.addAttribute("msg", "게시글 삭제 실패: DB ");
			return "redirect:review_show.ap?rId=" + rb.getrId();
		}
	}

//- 회원이 자신의 게시글 편집/갱신  할 수  있다. (권한/롤?)
	@RequestMapping(value = "review_edit_form.ap", method = RequestMethod.GET)
	public String reviewEditForm(HttpSession ses, Model model,
			@RequestParam(value = "rId", defaultValue = "0") int id) {
		if (id == 0) {
			return "redirect:review_list.ap";
		}
		ReviewBoardVO rb = rbSvc.selectOneReview(id);
		if (rb != null) {
			// 작성자가 맞는지?
			int writerId = rb.getPostId(); // <<FK>>
			int mbPKId = (Integer) ses.getAttribute("mbId");
			if (writerId == mbPKId) {
				model.addAttribute("reviewBoard", rb);
//				model.addAttribute("msg", "게시글 편집 폼 준비 성공 " + id);
				return "board/review/review_edit_form";
			} else {
				model.addAttribute("msg", "게시글 편집 폼 준비 실패: 작성자가 아님!!! " + id);
				return "redirect:review_show.ap?rId=" + id;
			}
		} else {
			model.addAttribute("msg", "게시글 편집 폼 준비 실패: 게시글 못찾음.. " + id);
			return "redirect:review_show.ap?rId=" + id;
		}
	}

//	article_update.my?atId=x ; post proc dao
	@RequestMapping(value = "review_update.ap", method = RequestMethod.POST)
	public String articleUpdateProc(Model model, HttpSession ses,
			@ModelAttribute(value = "reviewBoard") ReviewBoardVO rb // 'articleVO'
	) {

		// 작성자가 맞는지?
		int writerId = rb.getPostId(); // <<FK>>
		int mbPKId = (Integer) ses.getAttribute("mbId");
		if (writerId != mbPKId) {
			model.addAttribute("msg", "게시글 갱신 실패: 작성자가 아님!!! " + rb.getrId());
			return "redirect:review_show.ap?rId=" + rb.getrId();
		}
		//
		boolean b = rbSvc.updateReview(rb);
		model.addAttribute("b", b);
		if (b) { // OK
			model.addAttribute("msg", "게시글 편집 성공 ");
			return "redirect:review_show.ap?rId=" + rb.getrId();
		} else { // 3.. 4..
			model.addAttribute("msg", "게시글 갱신 실패: DB ");
			return "review/reivew_edit_form"; // fw
		}
	}

	/// 추가 마이페이지 리뷰
	@RequestMapping(value = "mypage_review.ap", method = RequestMethod.GET)
	public ModelAndView mypageReview(HttpSession ses,
			@RequestParam(value = "pg", required = false, defaultValue = "1") int pageNumber) {
		int postId = (Integer) ses.getAttribute("mbId");
		int maxPg = rbSvc.checkMaxPageNumber(postId);
		if (pageNumber <= 0 || pageNumber > maxPg) {
			System.out.println(">> 잘못된 페이지번호 요청 pg :" + pageNumber);
			return new ModelAndView("redirect:mypage_main.ap");
		}
		List<ReviewBoardVO> totalRbList = rbSvc.selectAllReviewsForOneMember(postId);
		List<ReviewBoardVO> rbList = rbSvc.selectAllReviewsForOneMember(pageNumber, postId);
		System.out.println(postId + "," + totalRbList + "," + rbList);
		ModelAndView mav = new ModelAndView("mypage/mp_mb_review");

		if (rbList != null) {
			int rbSize = rbList.size(); // 1~5
			int totalRbSize = totalRbList.size();
			List<String> mbNameList = new ArrayList<String>();
			for (ReviewBoardVO rb : rbList) {
				String mblogin = mbSvc.selectOneMember(rb.getPostId()).getLogin();
				mbNameList.add(mblogin);
			}
			mav.addObject("msg", "pg 게시글 리스트 조회 성공: " + rbSize + "개");
			mav.addObject("mbNameList", mbNameList);
			mav.addObject("rbList", rbList);
			mav.addObject("totalRbSize", totalRbSize);
			mav.addObject("rbSize", rbSize); // 0개 이상
			mav.addObject("pn", pageNumber); // 요청으로 활성화된 페이지 번호
			mav.addObject("maxPg", maxPg); // 최대 페이지 번호
		} else {
			mav.addObject("msg", "pg 게시글 리스트 조회 실패"); // null
			mav.setViewName("redirect:mypage_main.ap"); // mypage..
		}
		return mav;
	}

	// - 게시글 검색할 수 있다.

	@RequestMapping(value = "review_search.ap", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView reviewSearchProc(@RequestParam(value = "keyword", defaultValue = "없음") String k,
			@RequestParam(value = "target", required = false, defaultValue = "title") String target,
			@RequestParam(value = "pg", defaultValue = "1", required = false) int pg) {
		System.out.println(">> keyword: " + k);
		List<ReviewBoardRowVO> rbList = rbSvc.searchReview(k, target, pg);
		//
		ModelAndView mav = new ModelAndView("board/review/review_search");
		if (rbList != null) {
			int rbSize = rbList.size();
//			mav.addObject("msg", "게시글 검색 성공: " + rbSize +"개");
			mav.addObject("rbList", rbList);
			mav.addObject("rbSize", rbSize); // 0개 이상
			// ${pn}, ${param.pg}
			mav.addObject("pn", pg); // 요청으로 활성화된 페이지 번호
			Map<String, Integer> rMap = rbSvc.checkMaxPageNumber(target, k);
			// System.out.println("ctrl: maxPg = " + maxPg );
			mav.addObject("maxPg", rMap.get("maxPg")); // 최대 페이지 번호
			mav.addObject("atMaxSize", rMap.get("totalAtCnt")); // 총 검색일치 레코드 개수
		} else {
			mav.addObject("msg", "게시글 검색 실패"); // null
		}
		return mav;
	}

}
