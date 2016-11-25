<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:choose>
	<c:when test="${empty settlementApplication.settlementDetails}">
		<tr>
			<td colspan="5">选择的订单不存在佣金费用!</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${settlementApplication.settlementDetails}" var="item" varStatus="status">
			<tr>
				<td style="text-align: left;">
					<a href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${item.orderId}')">${item.orderCode}</a>
				</td>
				<td style="text-align: left;">
					<a href="javascript:addTab('委托方详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${item.entrustingId}')">${item.entrustingName}</a>
				</td>
				<td style="text-align: left;">
					<c:choose>
						<c:when test="${item.financialProduct == 0}">
						该订单不存在佣金费用!
					</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${item.financialProduct == 1}">
								退税垫付服务费
							</c:when>
								<c:when test="${item.financialProduct == 2}">
								赊销易服务费
							</c:when>
								<c:when test="${item.financialProduct == 3}">
								贸易代理服务费
							</c:when>
								<c:when test="${item.financialProduct == 4}">
								代理费
							</c:when>
							</c:choose>
							<input type="hidden" name="settlementDetails[${status.index}].orderId" value="${item.orderId}" />
							<input type="hidden" name="settlementDetails[${status.index}].orderCode" value="${item.orderCode}" />
							<input type="hidden" name="settlementDetails[${status.index}].entrustingId" value="${item.entrustingId}" />
							<input type="hidden" name="settlementDetails[${status.index}].entrustingName" value="${item.entrustingName}" />
							<input type="hidden" name="settlementDetails[${status.index}].financialProduct" value="${item.financialProduct}" />
							<input type="hidden" name="settlementDetails[${status.index}].costAmount" value="${item.costAmount}" />
							<input type="hidden" name="settlementDetails[${status.index}].incomeAmount" value="${item.incomeAmount}" />
							<input type="hidden" name="settlementDetails[${status.index}].commisionAmount" value="${item.commisionAmount}" />
						</c:otherwise>
					</c:choose>
				</td>
				<c:choose>
					<c:when test="${item.financialProduct == 0}">
						<td style="border-right: #ffffff 1px solid"></td>
						<td style="border-right: #ffffff 1px solid"></td>
						<td></td>
					</c:when>
					<c:when test="${item.financialProduct == 4}">
						<td style="border-right: #ffffff 1px solid"></td>
						<td style="border-right: #ffffff 1px solid"></td>
						<td style="text-align: right;">
							<c:choose>
								<c:when test="${settlementApplication.cooperationMode == 1}">
							--
						</c:when>
								<c:when test="${settlementApplication.cooperationMode == 2}">
									<fmt:formatNumber value="${item.commisionAmount}" pattern="0.00" />
								</c:when>
							</c:choose>
						</td>
					</c:when>
					<c:otherwise>
						<td style="text-align: right;">
							<fmt:formatNumber value="${item.costAmount}" pattern="0.00" />
						</td>
						<td style="text-align: right;">
							<fmt:formatNumber value="${item.incomeAmount}" pattern="0.00" />
						<td style="text-align: right;">
							<fmt:formatNumber value="${item.commisionAmount}" pattern="0.00" />
						</td>
					</c:otherwise>
				</c:choose>
				<td>
					<a href="javascript:void(0)" onclick="removeOrder(this)">移除</a>
				</td>
			</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>