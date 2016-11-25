<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>发生日期</th>
			<th>费用科目</th>
			<th>业务单据号</th>
			<th>订单号</th>
			<th>金额</th>
			<th>币种</th>
			<th>汇率</th>
			<th>收支类型</th>
			<th>金额(CNY)</th>
			<th>可用余额(CNY)</th>
		</tr>
	</thead>
	
	<c:choose>
		<c:when test="${empty consignerAccountSettlementList}">
			<tbody>
				<tr>
					<td colspan="10">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${consignerAccountSettlementList}" var="row">
					<tr>
						<td style="text-align: left;"><fmt:formatDate value="${row.createTime}"
								pattern="yyyy-MM-dd" /></td>
						<td style="text-align: left;">${row.exacctName}</td>
						<td style="text-align: left;"><a seed=112012011 onclick="alert('${row.billId}')"
									href="javascript:void(0)">${row.billNo}</a></td>
						<td style="text-align: left;"><a seed=112012012 onclick="alert('${row.orderId}')"
									href="javascript:void(0)">${row.orderCode}</a></td>
						<td style="text-align: right;"><fmt:formatNumber value="${row.foreignAmount}" pattern="#,##0.00"/></td>
						<td>${row.currencyName}</td>
						<td style="text-align: right;"><fmt:formatNumber value="${row.rate}" pattern="#,##0.00"/></td>
						<c:choose>
							    <c:when test="${row.amount > 0}">
							    	<td style="color:green;">${row.raeType}</td>
							    	<td style="color:green;"><fmt:formatNumber value="${row.amount}" pattern="#,##0.00"/></td>
							     </c:when>
							     <c:otherwise>
							     	<td style="color:red;">${row.raeType}</td>
							    	<td style="color:red;"><fmt:formatNumber value="${row.amount}" pattern="#,##0.00"/></td>
							     </c:otherwise>
		 				</c:choose>
						<td style="text-align: right;"><fmt:formatNumber value="${row.accountBalance}" pattern="#,##0.00"/></td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
</table>
<script type="text/javascript">
	function withDrawDetail(applicationId) {
		location.href = "${contextPath}/commission-withdraw-applications/"+applicationId;
	}