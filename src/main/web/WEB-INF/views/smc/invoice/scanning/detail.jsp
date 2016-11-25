<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>发票扫描详情</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript" src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=112431>
	<div class="body">
		<div class="page-body">
			<div class="page-title">发票扫描详情</div>
			<div style="text-align: center;">
				<h2>发票扫描详情</h2>
				<div class="search-group">
					<label style="width: 80px; text-align: right;">申请日期：</label>
					<label>
						<fmt:formatDate value="${scanningDetail.applicationDate}" pattern="yyyy-MM-dd" />
					</label>
				</div>
			</div>
			<div>
				<table class="table table-bordered table-hover" id="tab">
					<thead class="bordered-themeprimary">
						<tr>
							<th>订单编号</th>
							<th>委托客户</th>
							<th>开票人</th>
							<th>纳税识别证号</th>
							<th>商品数量</th>
							<th>合同金额</th>
							<th>来票张数</th>
						</tr>
					</thead>
					<c:set var="size" scope="session" value="${fn:length(scanningDetail.suppliers)}" />
					<c:choose>
						<c:when test="${size == 0}">
							<tbody>
								<tr>
									<td colspan="7" style="text-align: center;">采购合同未生成数据!</td>
								</tr>
							</tbody>
							<tfoot>
							</tfoot>
						</c:when>
						<c:otherwise>
							<tbody>
								<c:forEach items="${scanningDetail.suppliers}" var="item" varStatus="status">
									<c:set value="${totalContractAmount + item.contractAmount}" var="totalContractAmount" />
									<c:choose>
										<c:when test="${status.index == 0}">
											<tr>
												<td rowspan="${size}">
													<a seed=112431001 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${scanningDetail.orderId}')">${scanningDetail.orderCode}</a>
												</td>
												<td rowspan="${size}">
													<a seed=112431002 href="javascript:addTab('委托商','/Customer2nd/companyInfoByCompanyID.aspx?id=${scanningDetail.companyId}')">${scanningDetail.companyName}</a>
												</td>
												<td>
													<a seed=112431003 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${item.supplierId}')">${item.supplierName}</a>
												</td>
												<td>${item.taxpayerIdentityNumber}</td>
												<td>${item.declarequantity}</td>
												<td>
													<fmt:formatNumber value="${item.contractAmount}" pattern="#,##0.00" />
												</td>
												<td rowspan="${size}">${scanningDetail.regularInvoiceCount+scanningDetail.redInvoiceCount}</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td>
													<a seed=112431003 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${item.supplierId}')">${item.supplierName}</a>
												</td>
												<td>${item.taxpayerIdentityNumber}</td>
												<td>${item.declarequantity}</td>
												<td>
													<fmt:formatNumber value="${item.contractAmount}" pattern="#,##0.00" />
												</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td>合计</td>
									<td colspan="5" style="text-align: right; border-right: #ffffff 1px solid;">
										<fmt:formatNumber value="${totalContractAmount}" pattern="#,##0.00" />
									</td>
									<td></td>
								</tr>
							</tfoot>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<div>
				<label>备注:</label>
				<label>${scanningDetail.rejectTicketReason}</label>
			</div>
			<div style="text-align: right">
				<label>申请人:</label>
				<label>${scanningDetail.createdBy}</label>
				<label>
					<fmt:formatDate value="${scanningDetail.createdAt}" pattern="yyyy-MM-dd" />
				</label>
			</div>
			<div>
				<table class="table table-bordered table-hover" id="tab">
					<thead class="bordered-themeprimary">
						<tr>
							<th>发票扫描单号</th>
							<th>订单编号</th>
							<th>开票人</th>
							<th>来票张数</th>
							<th>发票含税总金额</th>
							<th>发票金额合计</th>
							<th>发票税额合计</th>
							<th>申请日期</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${empty scanningDetailRelations}">
								<tr>
									<td colspan="9" style="text-align: center;">无关联的申请单数据!</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${scanningDetailRelations}" var="itemModel">
									<c:forEach items="${itemModel.suppliers}" var="item" varStatus="relationStatus">
										<c:set var="relationSize" scope="session" value="${fn:length(itemModel.suppliers)}" />
										<c:choose>
											<c:when test="${relationStatus.index == 0}">
												<tr>
													<td rowspan="${relationSize}">
														<a seed=112431004 href="javascript:void(0)" onclick="invoiceOperation('发票扫描详情','/invoice-scanning-applications/','${itemModel.applicationId}/${itemModel.orderId}','/detail');">${itemModel.applicationCode}</a>
													</td>
													<td rowspan="${relationSize}">
														<a seed=112431001 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${itemModel.orderId}')">${itemModel.orderCode}</a>
													</td>
													<td>
														<a seed=112431003 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${item.supplierId}')">${item.supplierName}</a>
													</td>
													<td>${item.invoiceCount}</td>
													<td>${item.invoiceBefoetaxAmount}</td>
													<td>${item.invoiceTotalAmount}</td>
													<td>${item.invoiceTaxAmount}</td>
													<td rowspan="${relationSize}">
														<fmt:formatDate value="${itemModel.applicationDate}" pattern="yyyy-MM-dd" />
													</td>
													<td>${item.supplierStatus}</td>
												</tr>
											</c:when>
											<c:otherwise>
												<tr>
													<td>
														<a seed=112431003 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${item.supplierId}')">${item.supplierName}</a>
													</td>
													<td>${item.invoiceCount}</td>
													<td>${item.invoiceBefoetaxAmount}</td>
													<td>${item.invoiceTotalAmount}</td>
													<td>${item.invoiceTaxAmount}</td>
													<td>${item.supplierStatus}</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	contextPath = window.location.protocol + '//' + window.location.hostname
			+ ':' + window.location.port;

	function invoiceOperation(title, url, param, sign)
	{
		addTab (title, contextPath + url + param + sign, function()
		{

		});
	}
</script>