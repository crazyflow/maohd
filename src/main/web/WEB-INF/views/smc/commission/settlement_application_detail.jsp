<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>佣金结算申请详情</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
<style>
th, td {
	word-break: keep-all !important;
}

.page-body table td {
	text-align: left;
}
</style>
</head>
<body pageId=122220>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				佣金结算详情
				<p class="page-state">${settlementApplication.applicationCode}</p>
				<c:choose>
					<c:when test="${settlementApplication.status == 1}">
						<p class="page-state">草稿</p>
					</c:when>
					<c:when test="${settlementApplication.status == 2}">
						<p class="page-state">待审核</p>
					</c:when>
					<c:when test="${settlementApplication.status == 3}">
						<p class="page-state">审核通过</p>
					</c:when>
					<c:otherwise>
						<p class="page-state">待修改</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<table class="table table-bordered">
					<tbody>
						<tr>
							<td>业务主体</td>
							<td colspan="3">
								<c:forEach items="${businessMainBodys}" var="item2">
									<c:choose>
										<c:when test="${settlementApplication.businessMainBody == item2.id}">
												${item2.value}
											</c:when>
									</c:choose>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td style="width: 25%">申请单号</td>
							<td style="width: 25%">${settlementApplication.applicationCode}
								<input type="hidden" id="applicationId" value="${settlementApplication.applicationId}" />
							</td>
							<td style="width: 25%">申请时间</td>
							<td style="width: 25%">
								<fmt:formatDate value="${settlementApplication.applicationDate}" pattern="yyyy-MM-dd" />
							</td>
						</tr>
						<tr>
							<td style="width: 25%">渠道商名称</td>
							<td style="width: 25%">
								<a seed=122220001 href="javascript:addTab('渠道商详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${settlementApplication.channelId}')">${settlementApplication.channelName}</a>
							</td>
							<td style="width: 25%">合作模式</td>
							<td style="width: 25%">
								<c:choose>
									<c:when test="${settlementApplication.cooperationMode == 1}">
										渠道拍档
									</c:when>
									<c:when test="${settlementApplication.cooperationMode == 2}">
										渠道代理
									</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td style="width: 25%">备注</td>
							<td style="width: 80%" colspan="3">${settlementApplication.remark}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="table table-bordered table-hover" id="tab">
					<thead class="bordered-themeprimary">
						<tr>
							<th>订单号</th>
							<th>委托客户</th>
							<th>金融产品</th>
							<th>成本</th>
							<th>收入</th>
							<th>佣金</th>
						</tr>
					</thead>
					<tbody id="settlementApplicationDetail">
						<c:forEach items="${settlementApplication.settlementDetails}" var="item" varStatus="status">
							<tr>
								<td style="text-align: left;">
									<a seed=122220002 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${item.orderId}')">${item.orderCode}</a>
								</td>
								<td style="text-align: left;">
									<a seed=122220003 href="javascript:addTab('委托方详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${item.entrustingId}')">${item.entrustingName}</a>
								</td>
								<td style="text-align: left;">
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
								</td>
								<c:choose>
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
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<th colspan="6">
								<span style="font-weight: bold;">合计:</span><span style="float: right; font-weight: bold;" id="totalCommisionAmount"></span>
							</th>
						</tr>
					</tfoot>
				</table>
			</div>
			<div class="text-right">
				<span>制单信息：</span> <span>${settlementApplication.createdByName}</span> <span><fmt:formatDate value="${settlementApplication.createdDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
			</div>
			<c:if test="${auditAuth}">
				<div style="text-align: center;">
					<button seed=122220004 class="btn btn-default " type="button" onclick="showAuditContent()">审核不通过</button>
					<button seed=122220005 class="btn btn-primary btn-font5" type="button" onclick="audit(true)">审核通过</button>
				</div>
			</c:if>
			<div class="margin-top-30 title">审核信息</div>
			<div>
				<div class="page-body">
					<div class="auditProcess">
						<ul>
							<c:forEach items="${trackLineEmployeeInfo}" var="row">
								<c:choose>
									<c:when test="${row.workState==200}">
										<li class="on">${row.name}</li>
									</c:when>
									<c:otherwise>
										<li>${row.name}</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div>
					<div class="auditinfo">
						<table class="table table-bordered">
							<thead class="bordered-themeprimary">
								<tr>
									<th>操作结果</th>
									<th>审批节点名称</th>
									<th>操作人</th>
									<th>审批意见</th>
									<th>操作时间</th>
									<th>处理时长</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${employeeTrackInfo}" var="item">
									<tr <c:if test="${item.operateSubject == '审核不通过'}">
										style="background-color:#FFFF00"
									</c:if>>
										<td>${item.operateSubject}</td>
										<td>${item.nodeName}</td>
										<td>${item.name}</td>
										<td>${item.operateContext}</td>
										<td>${item.operateTime}</td>
										<td>${item.costTimeFullInfo}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="auditModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 600px;">
			<div class="modal-content">
				<button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
				<div class="modal-header mhd-layer-header">
					<div class="DTTTN btn-group mhd-layer-header-btns"></div>
					<h4 class="modal-title mhd-layer-title">审核不通过原因</h4>
				</div>
				<div class="modal-body">
					<div class="page-body mhd-layer-body">
						<textarea seed=122220006 id="content" style="width: 100%; height: 150px;"></textarea>
					</div>
				</div>
				<div class="modal-footer mhd-layer-footer">
					<button seed=122220007 class="btn btn-primary btn-font4" type="button" onclick="audit(false)">确认</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	moment.locale ('zh-cn');
	$ (function()
	{
		mergeCell (tab, 1, -1, [ 0, 1 ]);
		$ ("#totalCommisionAmount").text (getAmount () + "CNY");
	});

	// 审核不同过意见
	function showAuditContent()
	{
		$ ("#auditModal").modal ("show");
	}

	function audit(flag)
	{
		if (!flag)
		{
			$ ("#auditModal").modal ("hide");
		}
		var url = "${contextPath}/commission-settlement-applications/"
				+ $ ("#applicationId").val () + "/audit?_method=put&flag="
				+ flag + "&content=" + $ ("#content").val ();
		$
				.ajax ({
					url : url,
					type : "post",
					dataType : "json",
					contentType : "application/json",
					success : function(result)
					{

						if (result.success)
						{
							window.location.href = "${contextPath}/submit/success";
						}
						else
						{
							window.location.href = "${contextPath}/submit/info?message="
									+ result.error;
						}
					}
				});
	}

	// 获取总成本佣金及收入
	function getAmount()
	{
		var totalcommission = 0;
		var $trs = $ ("#tab tbody").children ();
		$.each ($trs, function(index, item)
		{
			if (parseFloat ($ (item).find ("td:last").text ()))
			{
				totalcommission += parseFloat ($ (item).find ("td:last")
						.text ());
			}
		});
		return totalcommission.toFixed (2);
	}

	//tbl:table对应的dom元素，
	//beginRow:从第几行开始合并（从0开始），
	//endRow:合并到哪一行，负数表示从底下数几行不合并
	//colIdxes:合并的列下标的数组，如[0,1]表示合并前两列，[0]表示只合并第一列
	function mergeCell(tbl, beginRow, endRow, colIdxes)
	{
		var colIdx = colIdxes[0];
		var newColIdxes = colIdxes.concat ();
		newColIdxes.splice (0, 1)
		var delRows = new Array ();
		var rs = tbl.rows;
		if (rs.length < 3)
		{
			return;
		}
		//endRow为0的时候合并到最后一行，小于0时表示最后有-endRow行不合并
		if (endRow === 0)
		{
			endRow = rs.length - 1;
		}
		else if (endRow < 0)
		{
			endRow = rs.length - 1 + endRow;
		}
		var rowSpan = 1; //要设置的rowSpan的值
		var rowIdx = beginRow; //要设置rowSpan的cell行下标
		var cellValue; //存储单元格里面的内容
		for (var i = beginRow; i <= endRow + 1; i++)
		{
			if (i === endRow + 1)
			{//过了最后一行的时候合并前面的单元格
				if (newColIdxes.length > 0)
				{
					mergeCell (tbl, rowIdx, endRow, newColIdxes);
				}
				rs[rowIdx].cells[colIdx].rowSpan = rowSpan;
			}
			else
			{
				var cell = rs[i].cells[colIdx];
				if (i === beginRow)
				{//第一行的时候初始化各个参数
					cellValue = cell.innerHTML;
					rowSpan = 1;
					rowIdx = i;
				}
				else if (cellValue != cell.innerHTML)
				{//数据改变合并前面的单元格
					cellValue = cell.innerHTML;
					if (newColIdxes.length > 0)
					{
						mergeCell (tbl, rowIdx, i - 1, newColIdxes);
					}
					rs[rowIdx].cells[colIdx].rowSpan = rowSpan;
					rowSpan = 1;
					rowIdx = i;
				}
				else if (cellValue === cell.innerHTML)
				{//数据和前面的数据重复的时候删除单元格
					rowSpan++;
					delRows.push (i);
				}
			}
		}
		for (var j = 0; j < delRows.length; j++)
		{
			rs[delRows[j]].deleteCell (colIdx);
		}
	}
</script>