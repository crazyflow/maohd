<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:choose>
	<c:when test="${empty invoiceCheckDetail}">
		<tr>
			<td colspan="9" style="text-align: center;">暂无数据</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${invoiceCheckDetail}" var="item" varStatus="status">
			<c:set value="${totalAmount + item.invoiceTotalAmount}" var="totalAmount" />
			<c:set value="${totalTaxAmount + item.invoiceTaxAmount}" var="totalTaxAmount" />
			<tr>
				<td style="text-align: center">${status.index+1}</td>
				<td style="text-align: center">${item.invoiceCode}</td>
				<td style="text-align: center">${item.invoiceNumber}</td>
				<td style="text-align: center">
					<fmt:formatDate value="${item.invoiceDate}" pattern="yyyy-MM-dd" />
				</td>
				<td style="text-align: center">${item.invoiceSellerName}</td>
				<td style="text-align: center">${item.invoiceSellerTaxNumber}</td>
				<td style="text-align: right">
					<fmt:formatNumber value="${item.invoiceTotalAmount}" pattern="#,##0.00" />
				</td>
				<td style="text-align: right">
					<fmt:formatNumber value="${item.invoiceTaxAmount}" pattern="#,##0.00" />
				</td>
				<td style="text-align: center">
					<c:choose>
						<c:when test="${item.invoiceStatus == 1}">
							验证通过
						</c:when>
						<c:when test="${item.invoiceStatus == 2}">
							验证未通过
						</c:when>
						<c:when test="${item.invoiceStatus == 3}">
							比对不通过
						</c:when>
						<c:when test="${item.invoiceStatus == 4}">
							比对通过
						</c:when>
						<c:when test="${item.invoiceStatus == 5}">
							扫描退票
						</c:when>
						<c:when test="${item.invoiceStatus == 6}">
							红冲
						</c:when>
					</c:choose>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td>合计</td>
			<td colspan="5"></td>
			<td style="text-align: right;">
				<fmt:formatNumber value="${totalAmount}" pattern="#,##0.00" />
			</td>
			<td style="text-align: right;">
				<fmt:formatNumber value="${totalTaxAmount}" pattern="#,##0.00" />
			</td>
			<td></td>
		</tr>
	</c:otherwise>
</c:choose>