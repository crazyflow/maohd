<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>序号</th>
			<th>发票代码</th>
			<th>发票号码</th>
			<th>开票日期</th>
			<th>销方名称（开票人）</th>
			<th>销方税号</th>
			<th>金额合计</th>
			<th>税额合计</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty invoiceList}">
				<tr>
					<td colspan="10" style="text-align: center;">暂无数据</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:set value="0" var="sumInvoiceAmount" />
				<c:set value="0" var="sumInvoiceTaxAmount" />
				<c:forEach items="${invoiceList}" var="item" varStatus="status">
					<tr>
						<td style="text-align: center;">${status.index + 1}</td>
						<td style="text-align: center;">${item.invoiceCode}</td>
						<td style="text-align: center;">${item.invoiceNumber}</td>
						<td style="text-align: center;">
							<fmt:formatDate value="${item.invoiceDate}" pattern="yyyy-MM-dd" />
						</td>
						<td style="text-align: left;" title="${item.invoiceSellerName}">${fn:length(item.invoiceSellerName) > 15 ? fn:replace(item.invoiceSellerName, fn:substring(item.invoiceSellerName, 15, fn:length(item.invoiceSellerName)) , '...') : item.invoiceSellerName}</td>
						<td style="text-align: center;">${item.invoiceSellerTaxNumber}</td>
						<td style="text-align: right;"><fmt:formatNumber value="${item.invoiceBefortaxAmount}" pattern="#,##0.00" /></td>
						<td style="text-align: right;"><fmt:formatNumber value="${item.invoiceTaxAmount}" pattern="#,##0.00" /></td>
						<td style="text-align: center;">
							<c:choose>
								<c:when test="${item.invoiceStatus == 4}">
									比对通过
								</c:when>
								<c:when test="${item.invoiceStatus == 6}">
									红冲
								</c:when>
							</c:choose>
						</td>
						<td>
							<a seed=112434005 href="javascript:showInvoiceDialog('${item.invoiceId}')">详情</a>
							<c:choose>
								<c:when test="${item.invoiceStatus == 4}">
									<a seed=112434006 onclick="signRed('${item.invoiceId}','${item.orderId}')" href="javascript:void(0)">红冲</a>
								</c:when>
							</c:choose>
						</td>
					</tr>
						<c:set value="${sumInvoiceAmount + item.invoiceBefortaxAmount}" var="sumInvoiceAmount" />
						<c:set value="${sumInvoiceTaxAmount + item.invoiceTaxAmount}" var="sumInvoiceTaxAmount" />
				</c:forEach>
				<tr>
					<td style="text-align: center;">合计</td>
					<td colspan="6" style="text-align: right;"><label><fmt:formatNumber value="${sumInvoiceAmount}" pattern="#,##0.00" /></label></td>
					<td style="text-align: right; border-right-style: none"><label><fmt:formatNumber value="${sumInvoiceTaxAmount}" pattern="#,##0.00" /></label></td>
					<td colspan="2" style="border-left-style: none"></td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
<script type="text/javascript">
	var $invoiceDialog;
	function showInvoiceDialog(invoiceId)
	{
		if (!$invoiceDialog)
		{
			$invoiceDialog = $ ("<div style='overflow: hidden;' id='invoiceDialog' />");
			$ ("body").append ($invoiceDialog);
			var url = "${contextPath}/invoice-management-applications/invoice-detail?invoiceId=" + invoiceId;
			$invoiceDialog.loadWithMask (url, null, function()
			{
				$ ('#invoiceModal').modal ({
					keyboard : false
				});
				//$ (".modal-backdrop").css ("opacity", 0);
				$ ('#invoiceModal').on ('show.bs.modal', function()
				{
					// 打开modal初始化渠道商列表
					getInvoiceDatas ();
				});
				$ ('#invoiceModal').on ('hide.bs.modal', function()
				{

				});
				$ ('#invoiceModal').modal ("show");
			});
		}
		$ ("#invoiceModal").modal ("show");
	}
	
	//设置红冲
	function signRed(invoiceId, orderId){
		$.get('${contextPath}/invoice-management-applications/red?invoiceId=' + invoiceId + '&orderId=' + orderId, null, function(data){
			var code = data['code'];
			var msg = data['message'];
			if(code == "0"){
				$('body').showSuccessMsg(msg);
				getInvoiceDatas();
				var invoiceCnt = $('#invoiceCnt').val();
				var invoicePastCnt = $('#invoicePastCnt').val();
				var newInvoicePastCnt = invoicePastCnt - 1;
				$('#twoInvoiceCnt').html(invoiceCnt + "/" + newInvoicePastCnt);
				$('#invoiceStatus').html('红冲');
			}else{
				$('body').showErrorMsg(msg);
			}
		});
	}
</script>