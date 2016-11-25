<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>佣金结算申请单号</th>
			<th>渠道商</th>
			<th>结算佣金（CNY）</th>
			<th>状态</th>
			<th>申请人</th>
			<th>申请时间</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty settlementApplications}">
				<tr>
					<td colspan="7" style="text-align: center;">暂无数据</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${settlementApplications}" var="item">
					<tr>
						<%-- <td applicationId="${item.applicationId}">${item.applicationCode}</td> --%>
						<td applicationId="${item.applicationId}">
							<a href="javascript:void(0)" seed=122218011 onclick="settlementOperation('佣金结算详情','/commission-settlement-applications/','${item.applicationId}','/detail');">${item.applicationCode}</a>
						</td>
						<td>
							<%-- <c:choose>
						<c:when test="${fn:length(item.channelName) >= 4}">
							<a href="javascript:addTab('渠道商详情','/Customer2nd/entrustCompany.aspx?channelCompanyID=${item.channelId}')">${fn:substring(item.channelName,0,4)}…</a>
						</c:when>
						<c:otherwise>
							
						</c:otherwise>
					</c:choose> --%>
							<a seed=122218012 href="javascript:addTab('渠道商详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${item.channelId}')">${item.channelName}</a>
						</td>
						<td class="text-right">${item.totalCommissionAmount}</td>
						<c:choose>
							<c:when test="${item.status == 1}">
								<td>草稿</td>
							</c:when>
							<c:when test="${item.status == 2}">
								<td>待审核</td>
							</c:when>
							<c:when test="${item.status == 3}">
								<td>审核通过</td>
							</c:when>
							<c:when test="${item.status == 4}">
								<td>待修改</td>
							</c:when>
						</c:choose>
						<td>${item.applicantName}</td>
						<td>
							<fmt:formatDate value="${item.applicationDate}" pattern="yyyy-MM-dd" />
						</td>
						<td class="text-left">
							<a seed=122218011 href="javascript:void(0)" onclick="settlementOperation('佣金结算详情','/commission-settlement-applications/','${item.applicationId}','/detail');">详情</a>
							<!-- 判断该workflow_id是否存在于我的工作流中，获取当前申请单的状态 -->
							<c:if test="${item.status == 1}">
								<a seed=122218023 href="javascript:void(0)" onclick="settlementOperation('佣金结算编辑','/commission-settlement-applications/','${item.applicationId}','/edit');"> 编辑</a>
								<a seed=122218024 href="javascript:void(0)" onclick="settlementDelete(this)">删除</a>
							</c:if>
							<c:if test="${fn:contains(workflowIds,item.workflowId)}">
								<c:if test="${item.status == 2}">
									<a seed=122218022 href="javascript:void(0)" onclick="settlementOperation('佣金结算审核','/commission-settlement-applications/','${item.applicationId}','/audit');">审核</a>
								</c:if>
							</c:if>
							<c:if test="${item.status == 3}">
							</c:if>
							<c:if test="${fn:contains(workflowIds,item.workflowId)}">
								<c:if test="${item.status == 4}">
									<a seed=122218023 href="javascript:void(0)" onclick="settlementOperation('佣金结算编辑','/commission-settlement-applications/','${item.applicationId}','/edit');"> 编辑</a>
								</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
</table>
<script type="text/javascript">
	var s1 = ${workflowSummary.reFix};
	var s0 = ${workflowSummary.reAudit};

	contextPath = window.location.protocol + '//' + window.location.hostname
			+ ':' + window.location.port;
	// 操作
	function settlementOperation(title, url, param, sign)
	{
		addTab (title, contextPath + url + param + sign, function()
		{
			getSettlementDatas ()
		});
	}

	// 删除该记录
	function settlementDelete(obj)
	{
		// 获取索要删除的结算单号 
		var applicationId = $ (obj).parent ().parent ().find ("td:first").attr (
				"applicationId");
		var url = "${contextPath}/commission-settlement-applications/"
				+ applicationId + "?_method=delete";
		$.ajax ({
			url : url,
			type : "post",
			success : function(result)
			{
				alert (result.success);
				getSettlementDatas ();
			}
		});
	}
</script>