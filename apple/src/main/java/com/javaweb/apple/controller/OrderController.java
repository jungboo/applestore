package com.javaweb.apple.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.javaweb.apple.model.vo.MemberVO;
import com.javaweb.apple.model.vo.OrderVO;
import com.javaweb.apple.model.vo.ProductVO;
import com.javaweb.apple.service.inf.ICartSVC;
import com.javaweb.apple.service.inf.IMemberSVC;
import com.javaweb.apple.service.inf.IOrderSVC;
import com.javaweb.apple.service.inf.IProductSVC;
import com.javaweb.apple.service.inf.JavaMail;

@Controller
public class OrderController
{
   @Autowired
   IOrderSVC odSvc;
   
   @Autowired
   IMemberSVC mbSvc;
   
   @Autowired
   IProductSVC pdSvc;
   
   @Autowired
   ICartSVC ctSvc;
   
   @Autowired
   JavaMail mail;
   

//- 정보를 기입후 결제를 진행할 수 있다.
//   order_info_form.ap      post form dao
   @RequestMapping(value = "order_info_form.ap", method = RequestMethod.POST)
   public ModelAndView orderInfoForm(HttpSession ses, HttpServletRequest req)
   {
      ModelAndView mav = new ModelAndView();
      
      // 멤버 등록
//      int mbId = Integer.valueOf(req.getParameter("mbId"));
      int mbId = (int) ses.getAttribute("mbId");
      
      
      MemberVO mb = this.mbSvc.selectOneMember(mbId);
      Map<String, Object> mbMap = new HashMap<String, Object>();
      mbMap.put("id", mbId);
      mbMap.put("name", mb.getName());
      mbMap.put("email", mb.getEmail());
      mbMap.put("phone", mb.getPhone());
      mbMap.put("address1", mb.getAddress());
      mbMap.put("address2", mb.getAddressDetail());
      
      
      // 물품
      int amount = Integer.valueOf(req.getParameter("amount"));
      if (amount <= 0)
      {
         System.out.println("OrderController:: orderInfoForm: amount <= 0");
      }
      System.out.println("amount = " + amount);
      
      
      // 상품 
      String[] idTemp = req.getParameterValues("prIds");
      List<Integer> prIds = new ArrayList<Integer>();
      for (int i = 0; i < idTemp.length; i++)
      {
         prIds.add(Integer.valueOf(idTemp[i]));
      }
      List<ProductVO> prList = new ArrayList<ProductVO>();
      for (int i = 0; i < prIds.size(); i++)
      {
         prList.add(this.pdSvc.selectOneProduct(Integer.valueOf(prIds.get(i)) ));
      }
      if (prList.size() <= 0)
      {
         System.out.println("OrderController:: orderInfoForm: prList.size() <= 0");
      }
      
      
      // 수량
      String countTemp[] = req.getParameterValues("pCounts");
      List<Integer> counts = new ArrayList<Integer>(); 
      for (int i = 0; i < countTemp.length; i++)
      {
         counts.add(Integer.valueOf(countTemp[i]));
      }
      
      if(counts.size() <= 0)
      {
         System.out.println("OrderController:: orderInfoForm: counts.size()  <= 0");
      }
      
      
      int totalPrice = 0;
      for (int i = 0; i < prList.size(); i++)
      {
         int price = prList.get(i).getPrice();
         totalPrice += price * counts.get(i);
      }
      
      mav.addObject("mb", mbMap);
      mav.addObject("amount", amount);
      mav.addObject("totalPrice", totalPrice);   
      
      
      // 장바구니 정보를 다시 넘기기 위해 어쩔 수 없다... 다른 방법 찾기엔 늦었음
      List<Map<String, Object>> cart = new ArrayList<Map<String,Object>>();
      for (int i = 0; i < prIds.size(); i++)
      {
         Map<String, Object> temp = new HashMap<String, Object>();
         temp.put("prId", prIds.get(i));
         temp.put("count", counts.get(i));
         cart.add(temp);
      }
      mav.addObject("cart", cart);
      
      
      mav.setViewName("order/od_info_form");
      return mav;
   }

//   order_final_proc.ap (이메일)
   @RequestMapping(value = "order_final_proc.ap", method = RequestMethod.POST)
   public ModelAndView orderFinalProc(HttpSession ses,
                              HttpServletRequest req)
   {
      ModelAndView mav = new ModelAndView();

      int mbId = (int) ses.getAttribute("mbId");
//      int mbId = 1;
      
      // 상품 id와 수량 불러오기
      String[] t1 = req.getParameterValues("prIds");
      String[] t2 = req.getParameterValues("counts");
      String prIds = "";
      String counts = "";
      for (int i = 0; i < t1.length; i++)
      {
         prIds += "|" + t1[i];
         counts += "|" + t2[i];
      }
      
      // 총가격
      int totalPrice = Integer.valueOf(req.getParameter("totalPrice"));
      
      
      String creditCard = req.getParameter("credit_card1") + "-" + req.getParameter("credit_card2") + "-"  +
            req.getParameter("credit_card3") + "-" + req.getParameter("credit_card4");
      
      String address = req.getParameter("address1");
      String addressDetail = req.getParameter("address2");
      System.out.println("주소: " + address + addressDetail);
      
      
      boolean b = this.odSvc.addNewOrder(mbId, totalPrice, creditCard, address, addressDetail, prIds, counts);
      
      if (b)
      {
         System.out.println("결제에 성공하셨습니다!");
         this.ctSvc.resetCart(mbId);
//         mav.setViewName("order/od_final");
         
         MemberVO mb = this.mbSvc.selectOneMember(mbId);
         if (mb != null)
         {
            StringBuffer sb = new StringBuffer();
            String[] prIdsTemp = req.getParameterValues("prIds");
            String[] countTemp = req.getParameterValues("counts");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
            
            
            sb.append(mb.getName() + "님, " + sdf.format(new Date())+ " 결제 내용입니다.\n\n" );
            for (int i = 0; i < prIdsTemp.length; i++) {
               ProductVO pr = this.pdSvc.selectOneProduct(Integer.valueOf(prIdsTemp[i]));
               sb.append( pr.getName() + " --- " + countTemp[i] + "개\n");
            }
            sb.append("총결제금액: " + totalPrice); 
            
            
            boolean mailCheck = mail.gmailSend("결제에 성공하셨습니다!", sb.toString(), mb.getEmail()); // 이메일 보내기
            if (mailCheck) {
               System.out.println("이메일 전송 성공");
               mav.addObject("msg", "입력하신 이메일로 주문 목록 발송하였습니다..");
               mav.setViewName("order/od_final");
               return mav;
            } else {
               System.out.println("구글 보안 걸어놈 이메일 전송 실패");
               mav.addObject("msg", "이메일 발송 실패 , 고객센터 문의바람 ");
               mav.setViewName("order/od_final");
               return mav;
            }
         }
         
      }
      else
      {
         
      }
      
      return mav;
   }

//- 결제한 주문을 취소 할 수 있다.
//   order_cancle.ap?orderId=x get proc dao
   @RequestMapping(value = "order_cancle.ap", method = RequestMethod.GET)
   public String orderCancle(@RequestParam(value = "odId", required = true) int orderId)
   {
      boolean b = this.odSvc.cancleOrder(orderId);
      if (b == true)
      {
         return "redirect:order_list.ap";
      }
      else
      {
         System.out.println("OrderController:: orderCancle: 삭제 실패");
      }
      
      
      return "";
   }

//- 구매내역을 특정 단위기간으로 확인할 수 있다 // 1=하루, 2=삼일, 3=일주일, 4=한달, 5=3개월
//   order_list.ap           get proc dao
   @RequestMapping(value = "order_list.ap", method = RequestMethod.GET)
   public ModelAndView orderList(HttpSession ses)
   {
      ModelAndView mav = new ModelAndView();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
      
      int memberId = (int) ses.getAttribute("mbId");
//      int memberId = 1;
      List<OrderVO> odTemp = this.odSvc.searchOrderList(memberId);
      if (odTemp == null)
      {
         System.out.println("구매 목록 불러오기 실패");
         mav.setViewName("redirect:main.ap");
         return mav;
      }
      
      
      List<Map<String, Object>> odList = new ArrayList<Map<String,Object>>();
      for (int i = 0; i < odTemp.size(); i++)
      {      
         OrderVO od = odTemp.get(i);
         Map<String, Object> odMap = new HashMap<String, Object>();
         
         odMap.put("orderId", od.getOrderId());
         odMap.put("orderDate", sdf.format(od.getOrderDate()));
         odMap.put("totalPrice", od.getTotalPrice());
         String status = "";
         switch(od.getOrderState())
         {
         case 0: status = "미발송"; break;
         case 1: status = "배송중"; break;
         case 2: status = "배송완료"; break;
         default: status = "orderStatus error";
         }
         System.out.println("orderStatus: " + status);
         odMap.put("orderStatus", status);
         
         List<Map<String, Object>> prctList = new ArrayList<Map<String,Object>>();
         String[] prTemp = od.getPrIds().split("\\|");
         String[] ctTemp = od.getCounts().split("\\|");
         // [0] 값은 빈칸이니 1부터 시작
         for (int j = 1; j < prTemp.length; j++)
         {
            Map<String, Object> prCtMap = new HashMap<String, Object>();
            ProductVO pr = this.pdSvc.selectOneProduct(Integer.valueOf(prTemp[j]));
            System.out.println("pr: " + pr.toString());
            int count = Integer.valueOf(ctTemp[j]);
            prCtMap.put("prId", pr.getId());
            prCtMap.put("name", pr.getName());
            prCtMap.put("price", pr.getPrice());
            prCtMap.put("imagePath", pr.getImage_path());
            prCtMap.put("count", count);
            prctList.add(prCtMap);
         }
         
         odMap.put("prctList", prctList);
         
         odList.add(odMap);
      }
      
      if (odList.size() <= 0)
      {
         System.out.println("주문내역에 아무것도 없음");
         mav.setViewName("redirect:main.ap");
         return mav;
      }
      
      mav.addObject("odList", odList);
      mav.setViewName("order/od_list");
      
      return mav;
   }

//- 특정 날자의 구매내역의 상세 정보를 확인할 수 있다
//   order_show.ap?odId=x   get proc dao
   @RequestMapping(value = "order_show.ap", method = RequestMethod.GET)
   public String orderShow()
   {

   
      return "";
   }

}

























