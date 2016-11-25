<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>发生日期</th>
			<th>费用科目</th>
			<th>业务单据号</th>
			<th>订单号</th>
			<th>金额</th>
			<th>币种</th>
			<th>汇率</th>
			<th>收支类型</th>
			<th>金额(CNY)</th>
		</tr>
	</thead>
	
	<c:choose>
		<c:when test="${empty accountDetailList}">
			<tbody>
				<tr>
					<td colspan="9">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${accountDetailList}" var="row">
					<tr>
						<td style="text-align: left;width: 100px;"><fmt:formatDate value="${row.createdDate}"
								pattern="yyyy-MM-dd" /></td>
						<td style="text-align: left;width: 50px;">${row.capacity1Name}</td>
						<td style="text-align: left;width: 100px;">
						<a seed=122217007 onclick="documentDetail('${row.documentId}','${row.capacity1Name}')" href="javascript:void(0)">${row.documentCode}</a></td>
						<td style="text-align: left;width: 100px;">
						<a seed=122217008 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${row.orderId}')">${row.orderCode}</a></td>
						<td style="text-align: right;width: 100px;"><fmt:formatNumber value="${row.foreignAmount}" pattern="#,##0.00"/></td>
						<td style="text-align: left;width: 50px;">${row.currencyCode}</td>
						<td style="text-align: right;width: 50px;"><fmt:formatNumber value="${row.rate}" pattern="#,##0.00"/></td>
						<c:choose>
							    <c:when test="${row.raeType == 1}">
							    	<td style="text-align: left;width: 50px;width: 100px;">收入</td>
							     </c:when>
							     <c:when test="${row.raeType == 2}">
							    	<td style="text-align: left;color:red;width: 50px;">支出</td>
							     </c:when>
							     <c:when test="${row.raeType == 3}">
							    	<td style="text-align: left;color:red;width: 50px;">冻结</td>
							     </c:when>
							     <c:otherwise>
							         <td style="text-align: left;width: 50px;">取消冻结</td>
							     </c:otherwise>
		 				</c:choose>
						<td style="text-align: right;width: 100px;"><fmt:formatNumber value="${row.amount}" pattern="#,##0.00"/></td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
</table>
<script type="text/javascript">
	function documentDetail(applicationId,capacity1Name) {
		var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
		if(capacity1Name=='提佣金'){
			addTab('业务单据详情',contextPath + '/commission-withdraw-applications/' + applicationId);
		}else{
			addTab('业务单据详情',contextPath + '/commission-settlement-applications/' + applicationId+ '/detail');
		}
		//location.href = "${contextPath}/commission-withdraw-applications/"+applicationId;
	}
  </script>