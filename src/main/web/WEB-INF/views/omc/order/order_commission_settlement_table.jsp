<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>
				<input seed=122219012 type="checkbox" />
				全选
			</th>
			<th>订单号</th>
			<th>订单完成日期</th>
			<th>委托客户</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty OrderInfos}">
				<tr>
					<td colspan="7">没有查到匹配的订单!</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${OrderInfos}" var="item">
					<tr>
						<td>
							<input seed=122219013 type="checkbox" />
							<input type="hidden" id="orderCode" value="${item.orderCode}" />
						</td>
						<td style="text-align: left;">
							<a seed=122219014 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${item.orderId}')">${item.orderCode}</a>
						</td>
						<td style="text-align: left;">
							<fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd" />
						</td>
						<td>${item.companyName}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
</table>
