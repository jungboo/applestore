package com.javaweb.apple.model.vo;

import java.util.List;

public class SearchVO {
  int maxPg;
  int currentPg;
  List<ProductVO> pdList;
@Override
public String toString() {
   return "SearchVO [maxPg=" + maxPg + ", currentPg=" + currentPg + ", pdList=" + pdList + "]";
}
  
  public SearchVO() {
   // TODO Auto-generated constructor stub
}

public SearchVO(int maxPg, int currentPg, List<ProductVO> pdList) {
   super();
   this.maxPg = maxPg;
   this.currentPg = currentPg;
   this.pdList = pdList;
}

public int getMaxPg() {
   return maxPg;
}

public void setMaxPg(int maxPg) {
   this.maxPg = maxPg;
}

public int getCurrentPg() {
   return currentPg;
}

public void setCurrentPg(int currentPg) {
   this.currentPg = currentPg;
}

public List<ProductVO> getPdList() {
   return pdList;
}

public void setPdList(List<ProductVO> pdList) {
   this.pdList = pdList;
}

}