<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>新增发票扫描编辑</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript" src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=112430>
	<div class="body">
		<div class="page-body">
			<div class="page-title">发票扫描编辑</div>
			<div style="text-align: center;">
				<h2>发票扫描编辑</h2>
				<div class="search-group">
					<label style="width: 80px; text-align: right;">申请日期：</label>
					<label>
						<fmt:formatDate value="${scanningApplication.applicationDate}" pattern="yyyy-MM-dd" />
					</label>
					<input id="applicationId" type="hidden" value="${scanningApplication.applicationId}" />
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
							<th>合同金额</th>	
							<th>常规发票张数</th>
							<th>红票张数</th>
						</tr>
					</thead>
					<tbody id="orderContractForInvoice">
						<c:set var="size" scope="session" value="${fn:length(scanningApplication.suppliers)}" />
						<c:forEach items="${scanningApplication.suppliers}" var="item" varStatus="status">
							<c:set value="${totalContractAmount + item.contractAmount}" var="totalContractAmount" />
							<c:choose>
								<c:when test="${status.index == 0}">
									<c:choose>
										<c:when test="${scanningApplication.redInvoice == 0}">
											<tr>
												<td rowspan="${size}">
													<a seed=112430005 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${scanningApplication.orderId}')">${scanningApplication.orderCode}</a>
												</td>
												<td rowspan="${size}">
													<a seed=112430006 href="javascript:addTab('委托商','/Customer2nd/companyInfoByCompanyID.aspx?id=${scanningApplication.companyId}')">${scanningApplication.companyName}</a>
												</td>
												<td>
													<a seed=112430007 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${item.supplierId}')">${item.supplierName}</a>
												</td>
												<td>${item.taxpayerIdentityNumber}</td>
												<td>
													<fmt:formatNumber value="${item.contractAmount}" pattern="#,##0.00" />
												</td>
												<td rowspan="${size}">
													<input seed=112430002 class="form-control" name="regularInvoiceCount" value="${scanningApplication.regularInvoiceCount}" />
												</td>
												<td rowspan="${size}">
													<input type="hidden" name="redInvoiceCount" value="${scanningApplication.redInvoiceCount}" />
												</td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td rowspan="${size}">
													<a seed=112430005 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${scanningApplication.orderId}')">${scanningApplication.orderCode}</a>
												</td>
												<td rowspan="${size}">
													<a seed=112430006 href="javascript:addTab('委托商','/Customer2nd/companyInfoByCompanyID.aspx?id=${scanningApplication.companyId}')">${scanningApplication.companyName}</a>
												</td>
												<td>
													<a seed=112430007 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${item.supplierId}')">${item.supplierName}</a>
												</td>
												<td>${item.taxpayerIdentityNumber}</td>
												<td>
													<fmt:formatNumber value="${item.contractAmount}" pattern="#,##0.00" />
												</td>
												<td rowspan="${size}">
													<input seed=112430002 class="form-control" name="regularInvoiceCount" value="${scanningApplication.regularInvoiceCount}" />
												</td>
												<td rowspan="${size}">
													<input seed=112430003 class="form-control" name="redInvoiceCount" value="${scanningApplication.redInvoiceCount}" />
												</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<td>
										<a seed=112430007 href="javascript:addTab('开票人详情','/supply/detail.aspx?ID=${item.supplierId}')">${item.supplierName}</a>
									</td>
									<td>${item.taxpayerIdentityNumber}</td>
									<td>
										<fmt:formatNumber value="${item.contractAmount}" pattern="#,##0.00" />
									</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td>合计</td>
							<td style="text-align: right; border-right: #ffffff 1px solid" colspan="4">
								<fmt:formatNumber value="${totalContractAmount}" pattern="#,##0.00" />
							</td>
							<td colspan="2"></td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div style="text-align: center;">
				<a seed=112430004 href="javascript:editScanningApplication()" class="btn btn-primary btn-font5">提交申请</a>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	// 保存修改信息
	function editScanningApplication()
	{
		// 校验
		var $inputs = $ ("#orderContractForInvoice").find ("input");
		var size = $ ("#orderContractForInvoice").find ("input:hidden").size ();
		if (size == 1)
		{
			// 无红票情况
			var $input1 = $inputs.eq (0);
			if ($input1.val () == "")
			{
				$input1.addClass (".has-error");
				$input1.next ().remove ();
				$input1.parent ().append (
						"<small class='help-block'>该项必填</small>");
				return;
			}
			else
			{
				$input1.removeClass (".has-error");
				$input1.next ().remove ();
			}
		}
		else
		{
			var $input1 = $inputs.eq (0);
			var $input2 = $inputs.eq (1);
			if ($input1.val () == "" && $input2.val () == "")
			{
				$input1.addClass (".has-error");
				$input1.parent ().append (
						"<small class='help-block'>红票时，常规和红票必须填一个!</small>");
				return;
			}
			else
			{
				$input1.removeClass (".has-error");
				$input1.next ().remove ();
			}
		}
		var url = "${contextPath}/invoice-scanning-applications?_method=put";
		var json = {};
		json.applicationId = $ ("#applicationId").val ();
		json.regularInvoiceCount = parseInt ($ (
				"input[name=regularInvoiceCount]").val ());
		json.redInvoiceCount = parseInt ($ ("input[name=redInvoiceCount]")
				.val ());
		json.applicationStatus = 1;
		$.ajax ({
			url : url,
			type : "post",
			dataType : "json",
			data : JSON.stringify (json),
			contentType : "application/json",
			success : function(result)
			{
				if (result.success)
				{
					// 提示成功，关闭当前页
					removeCurrentTab ();
				}
				else
				{
					// 提示失败，刷新本业
					$ ("body").showErrorMsg (result.error);
				}
			}
		});
	}
</script>