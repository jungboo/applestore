<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/_common.jsp" %>
<style>
.image_file{
	width: 100px;
	height: 100px;
	line-height: 100px;
	margin: 0 5px;
	display: inline-block;
	
}
.normal_file{

	margin: 0 5px;
	display: inline-block;
	
}
</style>
	<c:choose>
						<c:when test=
						"${ fn:endsWith(fp,'.png') 
							or fn:endsWith(fp,'.gif') 
							or fn:endsWith(fp,'.jpg')
							or fn:endsWith(fp,'.webp')
						  }">
							<div id="file_show_${vs.index}" class="image_file"> 
<%-- 								<b>${vs.index}번 이미지 경로: ${fp}"</b> <br> --%>
				<img src="${pageContext.request.contextPath}${fp}" 
								alt="오리지널 파일명은 ${fn:split(fp,'_')[1]} " width="100%", height="100%">								
							</div>
					 	</c:when>
					 	<c:when test=
						"${ not fn:endsWith(fp,'.png') 
							and not fn:endsWith(fp,'.gif') 
							and not fn:endsWith(fp,'.jpg')
							and not fn:endsWith(fp,'.webp')
						  }">
						  	<div id="file_show_${vs.index}" class="normal_file">
						  		<b>${vs.index}번 일반파일 경로: ${fn:split(fp,'_')[1]}"</b> <br>
		<a href="${pageContext.request.contextPath}${fp}">첨부파일 다운로드 LINK</a>
						  	</div> 
						</c:when> 
					 
	</c:choose>    