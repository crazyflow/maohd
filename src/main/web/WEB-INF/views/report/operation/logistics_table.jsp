<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>订单号</th>
			<th>业务日期</th>
			<th>物流方式</th>
			<th>业务操作</th>
			<th>费用类型</th>
			<th>物流供应商</th>
			<th>成本价</th>
			<th>客户报价</th>
			<th>利润</th>
		</tr>
	</thead>
	
		<c:choose>
		<c:when test="${empty logisticsList}">
			<tbody>
				<tr>
					<td colspan="9">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${logisticsList}" var="row">
					<tr>
						<td style="text-align: left;width: 130px;">
							<a seed=101208012 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${row.orderId}')">
								${row.orderCode}
							</a>
						</td>
						<td style="text-align: left;width: 100px;"><fmt:formatDate value="${row.orderDate}" pattern="yyyy-MM-dd"/></td>
						<td style="text-align: left;width: 100px;">${row.transportMode}</td>
		 				<td style="text-align: left;width: 160px;">${row.transportChildMode}</td>
		 				<td style="text-align: left;width: 100px;">${row.logisticsCostTypeName}</td>
		 				<c:choose>
						    <c:when test="${fn:length(row.logisticsBusName) > 8}">
						    	<td style="text-align: left;width: 160px;" title="${row.logisticsBusName}">
						    		<a seed=101208013 href="javascript:addTab('物流供应商详情','/basicManage/logistBus/LogBusail.aspx?cid=${row.logisticsBusId}')">
						    			${fn:substring(row.logisticsBusName,0,8)}…
						    		</a>
						    	</td>
						     </c:when>
						     <c:otherwise>
						         <td style="text-align: left;width: 160px;" title="${row.logisticsBusName}">
						         	<a seed=101208013 href="javascript:addTab('物流供应商详情','/basicManage/logistBus/LogBusail.aspx?cid=${row.logisticsBusId}')">
						         		${row.logisticsBusName}
						         	</a>
						         </td>
						     </c:otherwise>
		 				</c:choose>
		 				<td style="text-align: right;width: 160px;">${row.costPriceByCurrency}</td>
						<td style="text-align: right;width: 160px;">${row.customerPriceByCurrency}</td>
						<td style="text-align: right;width: 160px;">${row.profit}</td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
	

</table>