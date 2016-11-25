<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>委托客户</th>
			<th>自由资金池(CNY)</th>
			<th>限定资金池(CNY)</th>
			<th>定向资金池(CNY)</th>
			<th>冻结资金池(CNY)</th>
			<th>总资金池(CNY)</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
	</thead>
	
		<c:choose>
		<c:when test="${empty consignerCashPoolsList}">
			<tbody>
				<tr>
					<td colspan="8">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${consignerCashPoolsList}" var="row">
					<tr>
						<c:choose>
						    <c:when test="${fn:length(row.companyName) > 8}">
						    	<td style="text-align: left;" title="${row.companyName}">
							    	<a seed=112011005 onclick="companyDetail('${row.companyId}')"	href="javascript:void(0)">
							    		${fn:substring(row.companyName,0,8)}…
							    	</a>
						    	</td>
						     </c:when>
						     <c:otherwise>
						         <td style="text-align: left;" title="${row.companyName}"><a onclick="companyDetail('${row.companyId}')"	href="javascript:void(0)">${row.companyName}</a></td>
						     </c:otherwise>
		 				</c:choose>
		 				<td style="text-align: right;"><fmt:formatNumber value="${empty row.cashBalance?0:row.cashBalance}" pattern="#,##0.00"/></td>
						<td style="text-align: right;"><fmt:formatNumber value="${empty row.depositBalance?0:row.depositBalance}" pattern="#,##0.00"/></td>
						<td style="text-align: right;"><fmt:formatNumber value="${empty row.creditBalance?0:row.creditBalance}" pattern="#,##0.00"/></td>
						<td style="text-align: right;"><fmt:formatNumber value="${row.cashFreezeAmount + row.creditFreezeAmount + row.depositFreezeAmount}" pattern="#,##0.00"/></td>
						<td style="text-align: right;"><fmt:formatNumber value="${row.cashBalance + row.depositBalance + row.creditBalance + row.cashFreezeAmount + row.creditFreezeAmount + row.depositFreezeAmount}" pattern="#,##0.00"/></td>
						<td style="text-align: left;">可提取额度：<fmt:formatNumber value="${row.depositNeedPayAmount}" pattern="#,##0.00"/></td>
						<td><a seed=112011006 onclick="consignerAccountSettlements('${row.companyId}')" href="javascript:void(0)">详情</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
</table>
<script type="text/javascript">
	function companyDetail(companyId){
		alert(companyId+"wei zuo!");
	}
	
	function consignerAccountSettlements(companyId){
		location.href = "${contextPath}/channels/consigner-account-settlements/"+companyId;
	}
</script>