<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table class="table table-bordered table-hover" style="width: 1150px;">
	<thead class="bordered-themeprimary">
		<tr>
			<th>委托方名称</th>
			<th>企业类型</th>
			<th>三证合一</th>
			<th>注册地址</th>
			<th>经营地址</th>
			<th>个人身份证</th>
			<th>个人征信报告</th>
		</tr>
	</thead>
	
		<c:choose>
		<c:when test="${empty consignerList}">
			<tbody>
				<tr>
					<td colspan="7">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${consignerList}" var="row">
					<tr>
						<c:choose>
						    <c:when test="${fn:length(row.name) > 8}">
						    	<td style="text-align: left; width: 200px;" title="${row.name}">
							    	<a seed=101310005 href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${row.id}')">
							    		${fn:substring(row.name,0,8)}…
							    	</a>
						    	</td>
						     </c:when>
						     <c:otherwise>
						         <td style="text-align: left; width: 200px;" title="${row.name}">
						         	<a seed=101310005 href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${row.id}')">
						         		${row.name}
						         	</a>
						         </td>
						     </c:otherwise>
		 				</c:choose>
		 				<td style="text-align: left; width: 150px;">${row.industrytype}</td>
						<td style="text-align: left; width: 100px;">${row.isCertificate}</td>
						<td style="text-align: left; width: 250px;">${row.registerAddress}</td>
						<td style="text-align: left; width: 250px;">${row.businessAddress}</td>
						<td style="text-align: right; width: 100px;">${row.idCardCount}</td>
						<td style="text-align: right; width: 100px;">${row.personalReporCount}</td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
	

</table>