<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>发票扫描单号</th>
			<th>订单编号</th>
			<th>开票人</th>
			<th>来票张数</th>
			<th>申请日期</th>
			<th>申请状态</th>
			<th>比对状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty scanningList}">
				<tr>
					<td colspan="8" style="text-align: center;">暂无数据</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${scanningList}" var="item">
					<tr>
						<td>
							<a href="javascript:void(0)" seed=112428008 onclick="invoiceOperation('发票扫描详情','/invoice-scanning-applications/','${item.applicationId}/${item.orderId}','/detail');">${item.applicationCode}</a>
						</td>
						<td>
							<a seed=112428009 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${item.orderId}')">${item.orderCode}</a>
						</td>
						<td>
							<%-- <c:forEach items="${fn:split(item.suppliers, ',')}" var="item2" varStatus="index">
								<a seed=112428010 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${fn:split(item2, '|')[1]}')">${fn:split(item2, '|')[0]}</a>
								<br />
							</c:forEach> --%>
							<c:choose>
								<c:when test="${fn:length(item.suppliers) >= 20}">
									<span title="${item.suppliers}">${fn:substring(item.suppliers,0,20)}…</span>
								</c:when>
								<c:otherwise>
									<span title="${item.suppliers}">${item.suppliers}</span>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${item.regularInvoiceCount+item.redInvoiceCount}</td>
						<td>
							<fmt:formatDate value="${item.applicationDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<c:choose>
								<c:when test="${item.applicationStatus == 1}">
									扫描待收票
								</c:when>
								<c:when test="${item.applicationStatus == 2}">
									待扫描
								</c:when>
								<c:when test="${item.applicationStatus == 3}">
									扫描拒收票
								</c:when>
								<c:when test="${item.applicationStatus == 4}">
									扫描通过
								</c:when>
								<c:when test="${item.applicationStatus == 9}">
									扫描退票
								</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${item.orderInvoiceCheckStatus == 10}">
									比对待收票
								</c:when>
								<c:when test="${item.orderInvoiceCheckStatus == 20}">
									比对拒收票
								</c:when>
								<c:when test="${item.orderInvoiceCheckStatus == 30}">
									待比对
								</c:when>
								<c:when test="${item.orderInvoiceCheckStatus == 40}">
									比对通过
								</c:when>
								<c:when test="${item.orderInvoiceCheckStatus == 50}">
									比对不通过
								</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${item.applicationStatus == 1}">
									<a href="javascript:void(0)" seed=112428011 onclick="removeInvoiceApplication('${item.applicationId}')">删除</a>
									<a href="javascript:void(0)" seed=112428012 onclick="invoiceOperation('发票申请编辑','/invoice-scanning-applications/','${item.applicationId}','/edit');"> 编辑</a>
								</c:when>
								<c:when test="${item.applicationStatus == 3}">
									<a seed=112428013 href="javascript:void(0)" mark="${item.rejectTicketReason}" onclick="showScanningMark(this)">备注</a>
									<a seed=112428012 href="javascript:void(0)" onclick="invoiceOperation('发票申请编辑','/invoice-scanning-applications/','${item.applicationId}','/edit');"> 编辑</a>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${item.orderInvoiceCheckStatus == 50}">
									<a seed=112428014 href="javascript:void(0)" onclick="invoiceOperation('比对详情','/invoice-scanning-applications/','${item.orderId}','/check-detail');"> 比对详情</a>
								</c:when>
								<c:when test="${item.orderInvoiceCheckStatus == 20}">
									<a seed=112428013 href="javascript:void(0)" mark="${item.orderRefuseCollectInvoiceReason}" onclick="showComparationMark(this)">备注</a>
								</c:when>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
</table>
<div class="modal fade" id="scanningMarkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<button seed=112428017 class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
			<div class="modal-header mhd-layer-header">
				<div class="DTTTN btn-group mhd-layer-header-btns"></div>
				<h4 class="modal-title mhd-layer-title">扫描拒收票原因</h4>
			</div>
			<div class="modal-body">
				<div class="page-body mhd-layer-body">
					<textarea id="ScanningContent" style="width: 100%; height: 150px;" disabled></textarea>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal fade" id="comparationMarkModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<button seed=112428017 class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
			<div class="modal-header mhd-layer-header">
				<div class="DTTTN btn-group mhd-layer-header-btns"></div>
				<h4 class="modal-title mhd-layer-title">比对拒收票原因</h4>
			</div>
			<div class="modal-body">
				<div class="page-body mhd-layer-body">
					<textarea id="comparationContent" style="width: 100%; height: 150px;" disabled></textarea>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	// 备注
	function showScanningMark(obj)
	{
		$ ("#scanningMarkModal").modal ("show");
		$ ("#scanningMarkModal #ScanningContent").val ($ (obj).attr ("mark"));
	}

	function showComparationMark(obj)
	{
		$ ("#comparationMarkModal").modal ("show");
		$ ("#comparationMarkModal #comparationContent").val (
				$ (obj).attr ("mark"));
	}
	contextPath = window.location.protocol + '//' + window.location.hostname
			+ ':' + window.location.port;

	function invoiceOperation(title, url, param, sign)
	{
		addTab (title, contextPath + url + param + sign, function()
		{
			getInvoiceScanningDatas ();
		});
	}
	//删除该记录
	function removeInvoiceApplication(applicationId)
	{
		var url = "${contextPath}/invoice-scanning-applications/"
				+ applicationId + "?_method=delete";
		$.ajax ({
			url : url,
			type : "post",
			success : function(result)
			{
				alert (result.success);
				getInvoiceScanningDatas ();
			}
		});
	}
</script>