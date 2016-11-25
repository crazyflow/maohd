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
						<td style="text-align: left;width: 100px;">${row.capacity1Name}</td>
						<td style="text-align: left;width: 150px;"><a onclick="withDrawDetail('${row.documentId}')"
									href="javascript:void(0)">${row.documentCode}</a></td>
						<td style="text-align: left;width: 150px;">${row.orderCode}</td>
						<td style="text-align: right;width: 150px;">${row.foreignAmount}</td>
						<td style="text-align: left;width: 100px;">${row.currencyCode}</td>
						<td style="text-align: right;width: 100px;">${row.rate}</td>
						<c:choose>
							    <c:when test="${row.raeType == 1}">
							    	<td style="text-align: left;width: 100px;">收入</td>
							     </c:when>
							     <c:when test="${row.raeType == 2}">
							    	<td style="color:red;text-align: left;width: 100px;">支出</td>
							     </c:when>
							     <c:when test="${row.raeType == 3}">
							    	<td style="color:red;text-align: left;width: 100px;">冻结</td>
							     </c:when>
							     <c:otherwise>
							         <td style="text-align: left;width: 100px;">取消冻结</td>
							     </c:otherwise>
		 				</c:choose>
						<td style="text-align: right;width: 150px;">${row.amount}</td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
</table>
<script type="text/javascript">
	function withDrawDetail(applicationId) {
		location.href = "${contextPath}/commission-withdraw-applications/"+applicationId;
	}