<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
	th, td {
      	word-break: keep-all !important;
    }
</style>
<table class="table table-bordered table-hover" style="width: 2020px;">
	<thead class="bordered-themeprimary">
		<tr>
			<th>开票人名称</th>
			<th>对应委托人</th>
			<th>成立日期</th>
			<th>三证合一</th>
			<th>注册地址</th>
			<th>经营地址</th>
			<th>公司属性</th>
			<th>税票种类</th>
			<th>一般纳税人年限</th>
			<th>增值税票</th>
			<th>纳税人报表</th>
			<th>生产线照片</th>
			<th>公司门头</th>
			<th>出口商品</th>
			<th>已开票</th>
			<th>已开票金额</th>
		</tr>
	</thead>
	
		<c:choose>
		<c:when test="${empty drawerList}">
			<tbody>
				<tr>
					<td colspan="16">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${drawerList}" var="row">
					<tr>
						<c:choose>
						    <c:when test="${fn:length(row.name) > 8}">
						    	<td style="text-align: left;width: 160px;" title="${row.name}">
							    	<a href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${row.id}')">
							    		${fn:substring(row.name,0,8)}…
							    	</a>
						    	</td>
						     </c:when>
						     <c:otherwise>
						         <td style="text-align: left;width: 160px;" title="${row.name}">
							         <a href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${row.id}')">
							         	${row.name}
							         </a>
						         </td>
						     </c:otherwise>
		 				</c:choose>
		 				<c:choose>
						    <c:when test="${fn:length(row.companyName) > 8}">
						    	<td style="text-align: left;width: 160px;" title="${row.companyName}">
						    		<a href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${row.companyId}')">
						    			${fn:substring(row.companyName,0,8)}…
						    		</a>
					    		</td>
						     </c:when>
						     <c:otherwise>
						         <td style="text-align: left;width: 160px;" title="${row.companyName}">
						         	<a href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${row.companyId}')">
						         		${row.companyName}
						         	</a>
					         	</td>
						     </c:otherwise>
		 				</c:choose>
						<td style="text-align: left;width: 100px;"><fmt:formatDate value="${row.foundDate}" pattern="yyyy-MM-dd"/></td>
						<td style="text-align: left;width: 100px;">${row.isCertificate}</td>
						<td style="text-align: left;width: 200px;">${row.registerAddress}</td>
						<td style="text-align: left;width: 200px;">${row.businessAddress}</td>
						<td style="text-align: left;width: 100px;">${row.supplierEnterpriseType}</td>
						<td style="text-align: left;width: 100px;">${row.vATInvoiceUpper}</td>
						<td style="text-align: left;width: 120px;"><fmt:formatDate value="${row.grantedDate}" pattern="yyyy-MM-dd"/></td>
						<td style="text-align: right;width: 100px;">${row.ticketCount}</td>
						<td style="text-align: right;width: 100px;">${row.sheetCount}</td>
						<td style="text-align: right;width: 100px;">${row.pictureCount}</td>
						<td style="text-align: right;width: 100px;">${row.doorplateCount}</td>
						<td style="text-align: left;width: 200px;">${row.exportProducts}</td>
						<td style="text-align: left;width: 80px;">${row.isSupplier}</td>
						<td style="text-align: right;width: 100px;"><fmt:formatNumber value="${row.amount}" pattern="#,#00.00"/></td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
</table>