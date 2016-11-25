<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>渠道商</th>
			<th>自由佣金池(CNY)</th>
			<th>冻结佣金池(CNY)</th>
			<th>总佣金池(CNY)</th>
			<th>操作</th>
		</tr>
	</thead>
	
	<c:choose>
		<c:when test="${empty accountList}">
			<tbody>
				<tr>
					<td colspan="5">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${accountList}" var="row">
					<tr>
						<td style="text-align: left;"><a seed=122216005 href="javascript:addTab('渠道商详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${row.channelId}')">${row.channelName}</a></td>
						<td style="text-align: right;"><fmt:formatNumber value="${row.freeAmount}" pattern="#,##0.00"/></td>
						<td style="text-align: right;"><fmt:formatNumber value="${row.freezeAmount}" pattern="#,##0.00"/></td>
						<td style="text-align: right;"><fmt:formatNumber value="${row.totalAmount}" pattern="#,##0.00"/></td>
						<td style="text-align: left;"><a seed=122216006 onclick="accountDetail('${row.channelId}')" href="javascript:void(0)">详情</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
	
</table>
<script type="text/javascript">
	function accountDetail(channelId) {
		var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
		addTab('佣金台帐详情',contextPath + '/account-statements?channelId=' + channelId);
		//location.href = "${contextPath}/account-statements?channelId=" + channelId;
	}
</script>