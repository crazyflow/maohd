<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.left{
	text-align: left !important;
	line-height: 30px !important;
}

.padleft{
	padding-left: 20px;
}
</style>
<div class="modal fade" id="invoiceModal" tabindex="-1" role="dialog" aria-labelledby="invoiceModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="channelModalLabel">发票</h4>
			</div>
			<div class="modal-body">
				<table class="table table-bordered" style="width: 98%; margin: 20px 10px">
					<tbody>
						<tr>
							<td class="left"><label class="padleft">发票代码：</label>${invoice.invoiceCode}</td>
							<td class="left"><label class="padleft">发票号码：</label>${invoice.invoiceNumber}</td>
							<td class="left"><label class="padleft">开票日期：</label><fmt:formatDate value="${invoice.invoiceDate}" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<td class="left"><label class="padleft">销方名称：</label><span title="${invoice.invoiceSellerName}">${fn:length(invoice.invoiceSellerName) > 18 ? fn:replace(invoice.invoiceSellerName, fn:substring(invoice.invoiceSellerName, 18, fn:length(invoice.invoiceSellerName)) , '...') : invoice.invoiceSellerName}</span></td>
							<td class="left"><label class="padleft">销方税号：</label>${invoice.invoiceSellerTaxNumber}</td>
							<td class="left"><label class="padleft">销方地址：</label><span title="${invoice.invoiceSellerAddress}">${fn:length(invoice.invoiceSellerAddress) > 18 ? fn:replace(invoice.invoiceSellerAddress, fn:substring(invoice.invoiceSellerAddress, 18, fn:length(invoice.invoiceSellerAddress)) , '...') : invoice.invoiceSellerAddress}</span></td>
						</tr>
						<tr>
							<td class="left"><label class="padleft">购方名称：</label><span title="${invoice.invoiceBuyerName}">${fn:length(invoice.invoiceBuyerName) > 18 ? fn:replace(invoice.invoiceBuyerName, fn:substring(invoice.invoiceBuyerName, 18, fn:length(invoice.invoiceBuyerName)) , '...') : invoice.invoiceBuyerName}</span></td>
							<td class="left"><label class="padleft">购方税号：</label>${invoice.invoiceBuyerTaxNumber}</td>
							<td class="left"><label class="padleft">购方地址：</label><span title="${invoice.invoiceBuyerAddress}">${fn:length(invoice.invoiceBuyerAddress) > 18 ? fn:replace(invoice.invoiceBuyerAddress, fn:substring(invoice.invoiceBuyerAddress, 18, fn:length(invoice.invoiceBuyerAddress)) , '...') : invoice.invoiceBuyerAddress}</span></td>
						</tr>
					</tbody>
				</table>
				<table class="table table-bordered" style="width: 98%; margin: 10px 10px">
					<thead>
						<tr>
							<th>品名</th>
							<th>规格型号</th>
							<th>单位</th>
							<th>数量</th>
							<th>单价</th>
							<th>金额</th>
							<th>税率</th>
							<th>税额</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty invoiceProductList}">
								<tr>
									<td colspan="8" style="text-align: center;">暂无数据</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${invoiceProductList}" var="item">
									<tr>
										<td>${item.productDeclareName}</td>
										<td>${item.productSpecification}</td>
										<td>${item.productUnitName}</td>
										<td>${item.productQuanity}</td>
										<td style="text-align: right;"><fmt:formatNumber value="${item.productPrice}" pattern="#,##0.0000" /></td>
										<td style="text-align: right;"><fmt:formatNumber value="${item.productTotalAmount}" pattern="#,##0.00" /></td>
										<td>${item.productTaxRate}</td>
										<td style="text-align: right;"><fmt:formatNumber value="${item.productTaxAmount}" pattern="#,##0.00" /></td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<div style="padding-bottom: 40px">
					<div style="float: right; line-height: 30px; padding-right: 20px;"><label>税额合计：</label><fmt:formatNumber value="${invoice.invoiceTaxAmount}" pattern="#,##0.00" /></div>
					<div style="float: right; line-height: 30px; padding-right: 10px;"><label>未税金额合计：</label><fmt:formatNumber value="${invoice.invoiceBefortaxAmount}" pattern="#,##0.00" /></div>
					<div style="float: right; line-height: 30px; padding-right: 10px;"><label>价税合计：</label><fmt:formatNumber value="${invoice.invoiceTotalAmount}" pattern="#,##0.00" /></div>
				</div>
			</div>
		</div>
	</div>
</div>