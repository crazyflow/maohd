<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover" style="width: 1560px;">
	<thead class="bordered-themeprimary">
		<tr>
			<th>订单编号</th>
			<th>委托客户</th>
			<th>客服</th>
			<th>首单外贸顾问</th>
			<th>&nbsp;&nbsp;&nbsp;出口日期&nbsp;&nbsp;&nbsp;</th>
			<th>首单返单</th>
			<th>出口金额</th>
			<th>销售上报日期</th>
			<th>&nbsp;&nbsp;&nbsp;报关日期&nbsp;&nbsp;&nbsp;</th>
			<th>收汇金额</th>
			<th>退税垫付</th>
			<th>退税垫付金额</th>
			<th>贷款收齐</th>
			<th>未收齐金额</th>
			<th>退税垫付服务费</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${deptPerf}" var="item">
			<tr>
				<td style="text-align: left;width: 120px;">
					<a seed=101105005 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${item.id}')">${item.code}</a>
				</td>
				<td style="text-align: left;width: 180px;">
					<c:choose>
						<c:when test="${fn:length(item.companyName) > 8}">
							<a seed=101105006 href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${item.companyId}')">${fn:substring(item.companyName,0,8)}…</a>
						</c:when>
						<c:otherwise>
							<a seed=101105006 href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${item.companyId}')">${item.companyName}</a>
						</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: left;width: 60px;">${item.customerServiceName}</td>
				<td style="text-align: left;width: 100px;">${item.managerUser}</td>
				<td style="text-align: left;width: 120px;">
					<fmt:formatDate value="${item.exportDate}" pattern="yyyy-MM-dd" />
				</td>
				<td style="text-align: left;width: 80px;">
					<c:choose>
						<c:when test="${item.orderFlag == 1}">
							首单
						</c:when>
						<c:when test="${item.orderFlag == 2}">
							--
						</c:when>
						<c:otherwise>
							返单
						</c:otherwise>
					</c:choose>
				</td>
				<td style="text-align: right;width: 100px;">
					<c:choose>
						<c:when test="${item.totalForeignAmount != 0}">
							$<fmt:formatNumber value="${item.totalForeignAmount}" pattern="#,##0.00" />
						</c:when>
					</c:choose>
				</td>
				<td style="text-align: left;width: 120px;"><fmt:formatDate value="${item.planReceiptDate}" pattern="yyyy-MM-dd" /></td>
				<td style="text-align: left;width: 120px;">
					<fmt:formatDate value="${item.declarationDate}" pattern="yyyy-MM-dd" />
				</td>
				<td style="text-align: left;width: 100px;">
					<c:choose>
						<c:when test="${item.clearanceForeignAmount != 0}">
							$<fmt:formatNumber value="${item.clearanceForeignAmount}" pattern="#,##0.00" />
						</c:when>
					</c:choose>
				</td>
				<td style="text-align: left;width: 80px;">
					<c:choose>
						<c:when test="${item.isRefundService == 1}">
							√
						</c:when>
					</c:choose>
				</td>
				<td style="text-align: left;width: 100px;">
					<c:choose>
						<c:when test="${item.refundTexAmount != 0}">
							￥<fmt:formatNumber value="${item.refundTexAmount}" pattern="#,##0.00" />
						</c:when>
					</c:choose>
				</td>
				<td style="text-align: left;width: 80px;">
					<c:choose>
						<c:when test="${item.clearanceFinished == 1}">
							√
						</c:when>
					</c:choose>
				</td>
				<td style="text-align: right;width: 100px;">
					<c:choose>
						<c:when test="${item.clearanceFinished == 0}">
							<c:choose>
								<c:when test="${item.totalForeignAmount - item.clearanceForeignAmount > 0}">
									$<fmt:formatNumber value="${item.totalForeignAmount - item.clearanceForeignAmount}" pattern="#,##0.00" />
								</c:when>
							</c:choose>
						</c:when>
					</c:choose>
				</td>
				<td style="text-align: right;width: 120px;">
					<c:choose>
						<c:when test="${item.retTaxServiceAmount != 0}">
							￥<fmt:formatNumber value="${item.retTaxServiceAmount}" pattern="#,#00.00" />
						</c:when>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
	</tbody>
	<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
</table>
