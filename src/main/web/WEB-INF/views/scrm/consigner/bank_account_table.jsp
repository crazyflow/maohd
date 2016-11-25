<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>银行</th>
			<th>业务主体</th>
			<th>委托客户</th>
			<th>当前客服</th>
			<th>户名</th>
			<th>账号</th>
			<th>申请人</th>
			<th>申请日期</th>			
			<th>操作</th>
		</tr>
	</thead>
	
	<c:choose>
		<c:when test="${empty tablelist}">
			<tbody>
				<tr>
					<td colspan="9">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody >
			<c:forEach items="${tablelist}" var="row">
			
				
					<tr>
						<td style="text-align: center;">${row.bankName}</td>
						<td style="text-align: center;">${row.bodyName}</td>
						
						<c:choose>
							<c:when test="${fn:length(row.consignerName) >= 8}">
								<td style="text-align: center;" title="${row.consignerName}"><a seed=112113012 href="javascript:addTab('客户详情','/customer/companyInfo.aspx?id=${row.consignerId}')">${fn:substring(row.consignerName,0,8)}…</a></td>
							</c:when>
							<c:otherwise>
								<td style="text-align: center;" title="${row.consignerName}"><a seed=112113012 href="javascript:addTab('客户详情','/customer/companyInfo.aspx?id=${row.consignerId}')">${row.consignerName}</a></td>
							</c:otherwise>
						</c:choose>
						
							
						
						
						<td style="text-align: center;">${row.customer}</td>
						
						<td style="text-align: left;">${row.accountName}</td>
						<td style="text-align: center;">${row.accountNo}${row.subAccountNo}</td>
						<td style="text-align: center;">${row.createdByName}</td>
						<td style="text-align: center;"><fmt:formatDate value="${row.createdDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><a seed=112113013 onclick="viewbankaccount('${row.accountId}')">详情</a></td>
					</tr>
				
			
			</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
	
	
</table>
<script type="text/javascript">
function deletebank(accountId) {
	$.ajax({
		url : "${contextPath}/bank-account/"+accountId+"?_method=delete",
		type : "post",
		data : null,
		success : function(result) {
			
			if (result.success) {
				
				alert(result.message);
				refreshDatas();
				
			} else {
				$('.body').showErrorMsg(result.message);
			}
		}
	});
}
	
	
	
	function viewbankaccount(accountId) {
		
		
		var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
		addTab('查看虚拟子账户详情',contextPath + '/bank-account/'+accountId+'/view');
		//location.href = "${contextPath}/commission-withdraw-applications/new";
	}
</script>