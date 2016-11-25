<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>委托方名</th>
			<th>省市信息</th>
			<th>所属行业</th>
			<th>企业类型</th>
			<th>协议编号</th>
			<th>签约时间</th>
			<th>首单出口日期</th>
			<th>累计订单数</th>
			<th>累计订单金额</th>
			<th>首单外贸顾问</th>
			<th>当前客服</th>
			<th>最近出口日期</th>
		</tr>
	</thead>
	<c:choose>
		<c:when test="${empty exportlist}">
			<tbody>
				<tr>
					<td colspan="12">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${exportlist}" var="row">
					<tr>
						<td>
							<c:choose>
								<c:when test="${fn:length(row.companyName) >= 4}">
									<a seed=101004015 href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${row.companyId}')">${fn:substring(row.companyName,0,4)}…</a>
								</c:when>
								<c:otherwise>
									<a seed=101004015 href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${row.companyId}')">${row.companyName}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td style="text-align: left;">${row.areaInfo}</td>
						<td style="text-align: left;">${row.industryTypeName}</td>
						<td style="text-align: left;">${row.productSourceTypeName}</td>
						<td style="text-align: left;">${row.signCode}</td>
						<td style="text-align: left;">
							<fmt:formatDate value="${row.signTime}" pattern="yyyy-MM-dd" />
						</td>
						<td style="text-align: left;">
							<fmt:formatDate value="${row.firstExportDate}" pattern="yyyy-MM-dd" />
						</td>
						<td style="text-align: left;">${row.orderCount}</td>
						<td style="text-align: right;">
							<fmt:formatNumber value="${row.orderTotalMoney}" pattern="#,#00.00" />
						</td>
						<td style="text-align: left;">${row.firstOrderManagerUserName}</td>
						<td style="text-align: left;">${row.customerServiceName}</td>
						<td style="text-align: left;">
							<fmt:formatDate value="${row.lastExportDate}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
</table>