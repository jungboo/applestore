package com.javaweb.apple.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.apple.dao.impl.CartMySqlDAOImpl;
import com.javaweb.apple.dao.inf.ICartDAO;
import com.javaweb.apple.dao.inf.IProductDAO;
import com.javaweb.apple.model.vo.CartVO;
import com.javaweb.apple.model.vo.ProductVO;
import com.javaweb.apple.service.inf.ICartSVC;

@Service
public class CartSVCImpl implements ICartSVC
{
	@Autowired
	ICartDAO ctDao;
	
	@Autowired
	IProductDAO prDao;
	
	

	@Override
	public List<Map<String, Object>> showCartInfo(int memberId)
	{
		CartVO temp = this.ctDao.showCartInfo(memberId);
		
		if (temp == null ||temp.getPrIds() == null)
			return null;
		
		System.out.println("CartVO:: temp: " + temp);
		
		List<Map<String, Object>> ctList = new ArrayList<>();
		
		if (temp != null)
		{
			int amount = temp.getPrIds().split(CartMySqlDAOImpl.SEP).length - 1; // 제일 앞에도 | 가 붙어서 첫번째 배열은 그냥 빈공간임
			System.out.println("amount: " + amount);
			
			String[] prIds = temp.getPrIds().split(CartMySqlDAOImpl.SEP);
			String[] prNames = temp.getNames().split(CartMySqlDAOImpl.SEP);
			String[] prices = temp.getPrices().split(CartMySqlDAOImpl.SEP);
			String[] imagePaths = temp.getImagePaths().split(CartMySqlDAOImpl.SEP);
			String[] counts = temp.getCounts().split(CartMySqlDAOImpl.SEP);

			for (int i = 1; i < prIds.length; i++)
			{
				System.out.println(prIds[i]);
				System.out.println(prNames[i]);
				System.out.println(prices[i]);
				System.out.println(imagePaths[i]);
				System.out.println(counts[i]);
			}
			
			int totalPrice = 0;
			
			if (amount > 0)
			{
				// 제일 앞에도 | 가 붙어서 첫번째 배열은 그냥 빈공간임
				for (int i = 1; i < amount+1; i++)
				{
					Map<String, Object> ctMap = new HashMap<String, Object>();
					ctMap.put("prId", prIds[i]);
					ctMap.put("prName", prNames[i]);
					ctMap.put("price", prices[i]);
					ctMap.put("imagePath", imagePaths[i]);
					ctMap.put("count", counts[i]);
					totalPrice += Integer.valueOf(prices[i]) * Integer.valueOf(counts[i]);
					ctList.add(ctMap);
				}
			} 
			else
			{
				System.out.println("CartSVCImpl:: showCartInfo: amount <= 0");
			}
		} 
		else
		{
			System.out.println("CartSVCImpl:: showCartInfo: temp == null");
		}
		
		System.out.println(ctList);
		return ctList;
	}

	@Override
	public boolean addSingleProduct(int memberId, ProductVO pr, int count)
	{
		return this.ctDao.addSingleProduct(memberId, pr, count);
	}

	@Override
	public boolean addSingleProduct(int memberId, int prId, int count)
	{
		// 카트 db에 해당 mbId로 생성된게 없는지 확인
		boolean b = ctDao.checkCartExist(memberId);
		if (b == false)
		{
			ctDao.makeRowByMbId(memberId);
		}
		
		ProductVO pr = prDao.selectOneProduct(prId);
		
		if(pr == null)
		{
			System.out.println("CartSVCImpl:: addSingleProduct: pr 불러오기 실패");
			return false;
		}
		
		return this.ctDao.addSingleProduct(memberId, prId, pr.getName(), "" +pr.getPrice(), pr.getImage_path() , count);
	}

	@Override
	public boolean addMultiProduct(List<ProductVO> prs)
	{
		return this.ctDao.addMultiProduct(prs);
	}

	@Override
	public boolean fixProductCount(ProductVO pr)
	{
		return this.ctDao.fixProductCount(pr);
	}

//	@Override
//	public boolean deleteOneProduct(int memberId, int prId)
//	{
//		String selId = "" + prId;
//		Map<String, Object> cMap = this.ctDao.getCartColumns(memberId);
//		System.out.println("cMap: " + cMap);
//		System.out.println("cMap size: " + cMap.size());
//		System.out.println("memberId: "+memberId);
//		String dbIds = (String) cMap.get("pr_ids");
//		
//		boolean b = dbIds.indexOf(selId) != -1;
//		System.out.println("selId != -1: "+b);
//		if (b)
//		{ // 해당 상품id가 db에 존재함
//			boolean c = dbIds.indexOf(selId + CartMySqlDAOImpl.SEP) != -1;
//			// selId 저장된 자리가 첫번째인지 알아보는 것, 첫번째면 selId|가 존재할테니
//			for (int i = 0; i < cMap.size(); i++)
//			{
//				String colName = CartMySqlDAOImpl.columnNames[i];
//				String ori = (String) cMap.get(colName);
//				String del = c ? CartMySqlDAOImpl.SEP + ori : ori + CartMySqlDAOImpl.SEP;
//				String fix = ori.replace(ori, del);
//				System.out.println(i + "번 colName: " + colName);
//				System.out.println(i + "번 ori: " + ori);
//				System.out.println(i + "번 del: " + del);
//				System.out.println(i + "번 fix: " + fix);
//				cMap.put(colName, fix);
//			}
//		}
//		else
//		{ // 해당 상품id가 db에 없음
//			System.out.println("CartSVCImpl:: deleteOneProduct: 선택한 상품id가 db에 존재하지 않음!");
//		}
//		System.out.println("fix cMap: " + cMap);
//		
//		return this.ctDao.deleteOneProduct(memberId, cMap);
//	}
	
//	@Override
//	public boolean deleteOneProduct(int memberId, int prId)
//	{
//		String selId = "" + prId;
//		Map<String, Object> cMap = this.ctDao.getCartColumns(memberId);
//		System.out.println("cMap: " + cMap);
//		System.out.println("cMap size: " + cMap.size());
//		System.out.println("memberId: " + memberId);
//		String dbIds = (String) cMap.get("pr_ids");
//
//		// 선택한 pr이 몇번째인지 확인
//		int fIndex = dbIds.indexOf("prId");
//		String cut = dbIds.substring(0, fIndex);
//		String rem = cut.replaceAll("\\|", "");
//
//		int sepCount = cut.length() - rem.length();
//		int wPlace = sepCount + 1;
//
//		boolean b = dbIds.indexOf(selId) != -1;
//		System.out.println("selId != -1: " + b);
//		if (b)
//		{ // 해당 상품id가 db에 존재함
//			boolean c = dbIds.indexOf(selId + CartMySqlDAOImpl.SEP) != -1;
//			// selId 저장된 자리가 첫번째인지 알아보는 것, 첫번째면 selId|가 존재할테니
//			if (c)
//			{
//				for (int i = 0; i < cMap.size(); i++)
//				{
//					String colName = CartMySqlDAOImpl.columnNames[i];
//					String ori = (String) cMap.get(colName);
//					int temp = 0;
//					for (int j = 0; j < wPlace; j++)
//					{
//						temp = ori.indexOf("|", wPlace);
//						temp += 1;
//					}
//
//					String del = c ? CartMySqlDAOImpl.SEP + ori : ori + CartMySqlDAOImpl.SEP;
//					String fix = ori.replace(ori, del);
//					System.out.println(i + "번 colName: " + colName);
//					System.out.println(i + "번 ori: " + ori);
//					System.out.println(i + "번 del: " + del);
//					System.out.println(i + "번 fix: " + fix);
//					cMap.put(colName, fix);
//				}
//			} else // 첫번째자리임
//			{
//
//			}
//		} else
//		{ // 해당 상품id가 db에 없음
//			System.out.println("CartSVCImpl:: deleteOneProduct: 선택한 상품id가 db에 존재하지 않음!");
//		}
//		System.out.println("fix cMap: " + cMap);
//
//		return this.ctDao.deleteOneProduct(memberId, cMap);
//	}
	
	@Override
	public boolean deleteOneProduct(int memberId, int prId)
	{
		CartVO ct = this.ctDao.showCartInfo(memberId);
		
		
		List<String> prIds = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		List<String> prices = new ArrayList<String>();
		List<String> imagePaths = new ArrayList<String>();
		List<String> counts = new ArrayList<String>();

		prIds.addAll(Arrays.asList(ct.getPrIds().split("\\|")));
		names.addAll(Arrays.asList(ct.getNames().split("\\|")));
		prices.addAll(Arrays.asList(ct.getPrices().split("\\|")));
		imagePaths.addAll(Arrays.asList(ct.getImagePaths().split("\\|")));
		counts.addAll(Arrays.asList(ct.getCounts().split("\\|")));
		
		System.out.println("prIds = " + prIds.toString());
		System.out.println("prIds = " + names.toString());
		System.out.println("prIds = " + prices.toString());
		System.out.println("prIds = " + imagePaths.toString());
		System.out.println("prIds = " + counts.toString());
		int delIndex = prIds.indexOf(""+ prId);
		System.out.println("delIndex = " + delIndex);
		
		prIds.remove(delIndex);
		names.remove(delIndex);
		prices.remove(delIndex);
		imagePaths.remove(delIndex);
		counts.remove(delIndex);
		
		String fPrIds = "";
		String fNames = "";
		String fPrices = "";
		String fImagePaths = "";
		String fCounts = "";
		
		for (int i = 1; i < prIds.size(); i++)
		{
			fPrIds += "|" + prIds.get(i);
			fNames += "|" + names.get(i);
			fPrices += "|" + prices.get(i);
			fImagePaths += "|" + imagePaths.get(i);
			fCounts += "|" + counts.get(i);
		}
		
		System.out.println("fPrIds = " + fPrIds);
		System.out.println("fPrices = " + fPrices);
		
		return this.ctDao.deleteOneProduct(memberId, fPrIds, fNames, fPrices, fImagePaths, fCounts);
	}
	
	@Override
	public boolean deleteMultiProducts(int memberId, List<ProductVO> prs)
	{
		return this.ctDao.deleteMultiProducts(memberId, prs);
	}

	@Override
	public boolean deleteAllProducts()
	{
		return this.ctDao.deleteAllProducts();
	}

	@Override
	public boolean updateCart()
	{
		return this.ctDao.updateCart();
	}
	
	@Override
	public boolean resetCart(int mbId)
	{
		return this.ctDao.resetCart(mbId);
	}

	
}
