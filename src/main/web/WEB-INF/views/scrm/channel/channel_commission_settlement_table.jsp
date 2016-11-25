<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>渠道商</th>
			<th>主联系人</th>
			<th>合作模式</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty channels}">
				<tr>
					<td colspan="5">未查询到相关数据。</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${channels}" var="item">
					<tr>
						<td style="text-align: left;">
							<%-- <c:choose>
						<c:when test="${fn:length(item.channelName) >= 4}">
							<a href="javascript:addTab('渠道商详情','/Customer2nd/entrustCompany.aspx?channelCompanyID=${item.channelId}')">${fn:substring(item.channelName,0,4)}…</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:addTab('渠道商详情','/Customer2nd/entrustCompany.aspx?channelCompanyID=${item.channelId}')">${item.channelName}</a>
						</c:otherwise>
					</c:choose> --%>
							<a seed=122218018 href="javascript:addTab('渠道商详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${item.channelId}')">${item.channelName}</a>
						</td>
						<td style="text-align: left;">${item.contactName}</td>
						<td style="text-align: left;">
							<c:choose>
								<c:when test="${item.cooperationMode == 1}">
							渠道拍档
						</c:when>
								<c:when test="${item.cooperationMode == 2}">
							渠道代理
						</c:when>
							</c:choose>
						</td>
						<td>
							<a seed=122218019 href="javascript:void(0)" onclick="settlementOperation('佣金结算申请','/commission-settlement-applications/new?channelId=${item.channelId}&channelName=${item.channelName}&contactName=${item.contactName}&businessMainBody=${item.businessMainBody}&cooperationMode=${item.cooperationMode}')">选择</a>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
</table>
<script type="text/javascript">
	contextPath = window.location.protocol + '//' + window.location.hostname
			+ ':' + window.location.port;
	// 操作
	function settlementOperation(title, url)
	{
		$ ('#channelModal').modal ("hide");
		//关闭当前的弹出框
		addTab (title, contextPath + url, function()
		{
			getSettlementDatas ()
		});
	}
</script>