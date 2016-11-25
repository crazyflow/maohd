<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>发票比对详情</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript" src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=112432>
	<div class="body">
		<div class="page-title">发票比对详情</div>
		<div class="page-body">
			<div>
				<form id="checkSelectForm">
					<div class="search-area">
						<div class="search-group">
							<label>发票号码</label>
							<input seed=112432001 class="form-control" name="invoiceNumber" />
						</div>
						<div class="search-group">
							<label>开票人</label>
							<select seed=112432002 name="invoiceSellerId">
								<option value="" selected>全部</option>
								<c:forEach items="${suppliers}" var="item">
									<option value="${item.supplierId}">${item.supplierName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="search-group">
						<button seed=112432003 class="btn btn-default btn-search" type="button" onclick="getInvoiceCheckDetail()">查询</button>
					</div>
				</form>
			</div>
			<div>
				<table class="table table-bordered table-hover">
					<tbody>
						<tr>
							<td>
								订单编号: <a href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${scanningCheckDetail.orderId}')">${scanningCheckDetail.orderCode}</a>
								<input type="hidden" id="orderId" value="${scanningCheckDetail.orderId}" />
							</td>
							<td>
								委托客户: <a href="javascript:addTab('委托方详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${scanningCheckDetail.companyId}')">${scanningCheckDetail.companyName}</a>
							</td>
							<td>发票数量/认证通过:${scanningCheckDetail.totalApplicationCount}/${scanningCheckDetail.totalInvoiceCount}</td>
							<td>
								金额合计:
								<fmt:formatNumber value="${scanningCheckDetail.totalInvoiceAmount}" pattern="#,##0.00" />
							</td>
							<td>
								税额合计:
								<fmt:formatNumber value="${scanningCheckDetail.totalInvoiceTaxAmount}" pattern="#,##0.00" />
							</td>
							<td>
								发票状态:
								<c:choose>
									<c:when test="${scanningCheckDetail.isPurchaseInvoiceFinished == 1}">
									收齐
								</c:when>
									<c:when test="${scanningCheckDetail.isPurchaseInvoiceFinished == 2}">
									红冲
								</c:when>
									<c:otherwise>
									未收齐
								</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="table table-bordered table-hover">
					<thead class="bordered-themeprimary">
						<tr>
							<th>序号</th>
							<th>发票代码</th>
							<th>发票号码</th>
							<th>开票日期</th>
							<th>销方名称(开票人)</th>
							<th>销方税号</th>
							<th>金额合计</th>
							<th>税额合计</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody id="invoiceInfos">
					</tbody>
					<tfoot>
					</tfoot>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	$ (function()
	{
		getInvoiceCheckDetail ();
	});

	function getInvoiceCheckDetail()
	{
		var searchParam = $ ("#checkSelectForm").serialize () + "&orderId="
				+ $ ("#orderId").val ();
		var url = "${contextPath}/invoice-scanning-applications/invoice-comparation-detail?format=table";
		$ ('#invoiceInfos').loadWithMask (url, searchParam);
	}
</script>