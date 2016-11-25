<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>发票详情</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
<style>
label {
	font-weight: bolder;
}

th, td {
	word-break: keep-all !important;
}

.page-body table td {
	text-align: left;
}
</style>
</head>
<body pageId=112434>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				发票详情
			</div>
			<div>
				<input type="hidden" id="invoiceCnt" value="${summary.invoiceCount}" />
				<input type="hidden" id="invoicePastCnt" value="${summary.invoiceComparePastCount}" />
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>订单编号：<a seed=112434004 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${summary.orderId}')">${summary.orderCode}</a></td>
							<td>委托客户：<a seed=112434007 title="${summary.companyName}" href="javascript:addTab('委托方详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${summary.companyId}')">${fn:length(summary.companyName) > 15 ? fn:replace(summary.companyName, fn:substring(summary.companyName, 15, fn:length(summary.companyName)) , '...') : summary.companyName}</a></td>
							<td>发票数量/比对通过：<span id="twoInvoiceCnt">${summary.invoiceCount}/${summary.invoiceComparePastCount}</span></td>
							<td>金额合计：<fmt:formatNumber value="${summary.orderInvoiceBeforeTaxAmount}" pattern="#,##0.00" /></td>
							<td>税额合计：<fmt:formatNumber value="${summary.orderInvoiceTaxAmount}" pattern="#,##0.00" /></td>
							<td>发票状态：<span id="invoiceStatus">${summary.status == 1 ? "收齐" : "红冲"}</span></td>
						</tr>
					</tbody>
				</table>
			</div>
			<form id="invoiceSearch" style="float: right">
				<input type="hidden" name="orderId" value="${summary.orderId}" />
				<div class="search-group">
					<label>发票号码：</label> 
					<input seed=112434001 class="form-control" name="invoiceNumber" type="text" maxlength="8" style="width: 100px">
				</div>
				<div class="search-group">
					<label>开票人：</label>
					<select seed=1124340012 name="supplierId" class="form-control">
						<option value="" selected="selected">全部</option>
						<c:forEach items="${supplierList}" var="supplier">
							<option value="${supplier.userId}">${supplier.userName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="search-group">
					<button seed=112434003 class="btn btn-default btn-search" type="button" onclick="getInvoiceDatas()">查询</button>
				</div>
			</form>
			<div id="invoiceTable"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	moment.locale ('zh-cn');
	$ (function()
	{
		getInvoiceDatas ();
	});
	
	// 获取发票list数据
	function getInvoiceDatas(orderId)
	{
		var searchParam = $ ("#invoiceSearch").serialize ();
		var url = "${contextPath}/invoice-management-applications/invoice-list";
		$ ('#invoiceTable').loadWithMask (url, searchParam,function(){
			
		});
	}
</script>
</html>