<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>
				<input type="checkbox" />
			</th>
			<th>订单编号</th>
			<th>委托客户</th>
			<th>开票人</th>
			<th>报关金额</th>
			<th>报关币种</th>
			<th>发票金额</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty invoiceCollection}">
				<tr>
					<td colspan="7">没有查到匹配的订单!</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${invoiceCollection}" var="item">
					<tr>
						<td>
							<input type="checkbox" />
							<input type="hidden" id="orderId" value="${item.orderId}" />
							<input type="hidden" id="purchaseContract" value="${item.purchaseContract}" />
							<input type="hidden" id="delaration" value="${item.delaration}" />
						</td>
						<td style="text-align: center;">
							<a seed=112429002 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${item.orderId}')">${item.orderCode}</a>
						</td>
						<td style="text-align: left;"><a seed=112429003 href="javascript:addTab('委托方详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${item.companyId}')">${item.companyName}</a></td>
						<td style="text-align: left;">
							<c:forEach items="${fn:split(item.suppliers, ',')}" var="item2" varStatus="index">
								<a seed=112429010 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${fn:split(item2, '|')[1]}')">${fn:split(item2, '|')[0]}</a><br/>
							</c:forEach>
						</td>
						<td style="text-align: right;"><fmt:formatNumber value="${item.totalForeignAmount}" pattern="#,#0.00" /></td>
						<td style="text-align: center;">${item.currencyName}</td>
						<td style="text-align: right;"><fmt:formatNumber value="${item.invoiceAmount}" pattern="#,#0.00" /></td>
						
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
</table>
