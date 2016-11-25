<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>订单编号</th>
			<th>发票张数</th>
			<th>发票含税总金额</th>
			<th>发票金额合计</th>
			<th>发票税额合计</th>
			<th>转交日期</th>
			<th>状态</th>
			<th>客服</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty managementList}">
				<tr>
					<td colspan="9" style="text-align: center;">暂无数据</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${managementList}" var="item">
					<tr>
						<td>
							<a seed=112433009 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${item.orderId}')">${item.orderCode}</a>
						</td>
						<td>${item.invoiceCount}</td>
						<td style="text-align: right;"><fmt:formatNumber value="${item.orderInvoiceTotalAmount}" pattern="#,##0.00" /></td>
						<td style="text-align: right;"><fmt:formatNumber value="${item.orderInvoiceBeforeTaxAmount}" pattern="#,##0.00" /></td>
						<td style="text-align: right;"><fmt:formatNumber value="${item.orderInvoiceTaxAmount}" pattern="#,##0.00" /></td>
						<td>
							<fmt:formatDate value="${item.orderTransferDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<c:choose>
								<c:when test="${item.status == 1}">
									收齐
								</c:when>
								<c:when test="${item.status == 2}">
									红冲
								</c:when>
							</c:choose>
						</td>
						<td>${item.customerServiceName}</td>
						<td>
							<a seed=112433010 onclick="managementDetail('${item.orderId}')" href="javascript:void(0)">详情</a>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
</table>
<script type="text/javascript">
	function managementDetail(orderId) {
		var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
		addTab('发票详情',contextPath + '/invoice-management-applications/' + orderId + '/detail');
	}
</script>