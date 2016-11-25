<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover" style="font-size: 12px; margin-top: 15px;">
	<thead class="bordered-themeprimary">
		<tr>
			<th>订单编号</th>
			<th>报关单号</th>
			<th>业务主体</th>
			<th>所属部门</th>
			<th>当前客服</th>
			<th>外贸顾问</th>
			<th nowrap="nowrap">首单/返单</th>
			<th>委托方</th>
			<th>省市信息</th>
			<th>&nbsp;下单日期&nbsp;</th>
			<th>下单方式</th>
			<th>&nbsp;出口日期&nbsp;</th>
			<th>&nbsp;申报日期&nbsp;</th>
			<th>&nbsp;离境日期&nbsp;</th>
			<th>报关方式</th>
			<th>指运港</th>
			<th>目的国</th>
			<th>物流方式</th>
			<th>保证金</th>
			<th>结算方式</th>
			<th>赊销服务</th>
			<th>报关币种</th>
			<th nowrap="nowrap">报关金额(外币)</th>
			<th>汇率</th>
			<th nowrap="nowrap">报关金额(RMB)</th>
			<th>订单状态</th>
			<th>是否删除</th>
			<th>价格条款</th>
			<th>退税服务类型</th>
			<th>退税服务费率</th>
			<th nowrap="nowrap">赊销金额(RMB)</th>
			<th>开票人</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${empty orderDetails}">
				<tbody>
					<tr>
						<td colspan="999" style="text-align: center;">未查询到相关数据。</td>
					</tr>
				</tbody>
			</c:when>
			<c:otherwise>
				<c:forEach items="${orderDetails}" var="item">
					<tr>
						<td>
							<a seed=101003005 href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${item.id}')">${item.code}</a>
						</td>
						<td>${item.declarationNumber}</td>
						<td>${item.ourCompanyName}</td>
						<td>${item.department}</td>
						<td>${item.customerServiceName}</td>
						<td>${item.managerUserName}</td>
						<td>
							<c:choose>
								<c:when test="${item.orderFlag == 1}">
									首单
								</c:when>
								<c:when test="${item.orderFlag == 0}">
									返单
								</c:when>
								<c:when test="${item.orderFlag == 2}">
									--
								</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${fn:length(item.companyName) >= 4}">
									<a seed=101003006 href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${item.companyId}')">${fn:substring(item.companyName,0,4)}…</a>
								</c:when>
								<c:otherwise>
									<a seed=101003006 href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${item.companyId}')">${item.companyName}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${item.areaInfo}</td>
						<td>
							<fmt:formatDate value="${item.orderDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<c:choose>
								<c:when test="${item.orderWay == 1}">
							自助下单
						</c:when>
								<c:when test="${item.orderWay == 2}">
							委托下单
						</c:when>
							</c:choose>
						</td>
						<td>
							<fmt:formatDate value="${item.exportDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<fmt:formatDate value="${item.declarationDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<fmt:formatDate value="${item.packingLeaveDate}" pattern="yyyy-MM-dd" />
						</td>
						<td>
							<c:choose>
								<c:when test="${item.declareType == 1}">
							贸互达报关
						</c:when>
								<c:when test="${item.declareType == 2}">
							自行报关
						</c:when>
							</c:choose>
						</td>
						<td>${item.destinationPort}</td>
						<td>${item.destinationCountryName}</td>
						<td>
							<c:choose>
								<c:when test="${item.logisticsService == 1}">
							委托物流
						</c:when>
								<c:when test="${item.logisticsService == 2}">
							自行安排
						</c:when>
								<c:when test="${item.logisticsService == 3}">
							待定
						</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${item.hasEnsureMoney == 1}">
							含
						</c:when>
								<c:when test="${item.hasEnsureMoney == 0}">
							不含
						</c:when>
							</c:choose>
						</td>
						<td>${item.paymentTerm}</td>
						<td>
							<c:choose>
								<c:when test="${item.isSellCreditService == 1}">
							是
						</c:when>
								<c:when test="${item.isSellCreditService == 0}">
							否
						</c:when>
							</c:choose>
						</td>
						<td>${item.currencyName}</td>
						<td style="text-align: right;">
							<fmt:formatNumber value="${item.totalForeignAmount}" pattern="#,#00.00" />
						</td>
						<td style="text-align: right;">${item.orderRate}</td>
						<td style="text-align: right;">
							<fmt:formatNumber value="${item.orderAmountRMB}" pattern="#,#00.00" />
						</td>
						<td>
							<c:choose>
								<c:when test="${item.status == 1}">
							草稿
						</c:when>
								<c:when test="${item.status == 10}">
							完善中
						</c:when>
								<c:when test="${item.status == 20}">
							审核中
						</c:when>
								<c:when test="${item.status == 30}">
							待确认
						</c:when>
								<c:when test="${item.status == 40}">
							待修改
						</c:when>
								<c:when test="${item.status == 50}">
							执行中
						</c:when>
								<c:when test="${item.status == 52}">
							已取消
						</c:when>
								<c:when test="${item.status == 90}">
							订单完成
						</c:when>
								<c:when test="${item.status == 92}">
							订单关闭
						</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${item.isDeleted == 1}">
							是
						</c:when>
								<c:when test="${item.isDeleted == 0}">
							否
						</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${item.tradeTerm == 1}">
							FOB
						</c:when>
								<c:when test="${item.tradeTerm == 2}">
							CIF
						</c:when>
								<c:when test="${item.tradeTerm == 3}">
							C&F
						</c:when>
								<c:when test="${item.tradeTerm == 4}">
							其他
						</c:when>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${item.isRefundService == 1}">
							垫付退税
						</c:when>
								<c:when test="${item.isRefundService == 0}">
							正常退税
						</c:when>
							</c:choose>
						</td>
						<td style="text-align: right;">${item.refundService}</td>
						<td>
							<c:choose>
								<c:when test="${item.sellCreditAmountRMB == 0}">
								</c:when>
								<c:otherwise>
									<fmt:formatNumber value="${item.sellCreditAmountRMB}" pattern="#,#00.00" />
								</c:otherwise>
							</c:choose>
						</td>
						<td>${item.drawers}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
</table>
