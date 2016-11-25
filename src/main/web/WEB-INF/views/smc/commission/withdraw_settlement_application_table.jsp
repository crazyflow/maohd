<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th></th>
			<th>佣金结算申请单号</th>
			<th>渠道商</th>
			<th>结算佣金金额(CNY)</th>
			<th>已提取佣金金额(CNY)</th>
		</tr>
	</thead>
	
	<c:choose>
		<c:when test="${empty sttlementList}">
			<tbody>
				<tr>
					<td colspan="5">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${sttlementList}" var="row">
					<tr>
						<c:choose>
							<c:when test="${row.applicationId==page.applicationId}">
								<td><input seed=122223017 type="checkbox" checked="checked"
									settlement="${row.applicationId}#${row.applicationCode}#${row.totalCommissionAmount}#${row.withdrawnAmount}"
									onclick="selectSingle(this)" channelName="${row.channelName}"
									channelId="${row.channelId}"></td>
							</c:when>
							<c:otherwise>
								<td><input seed=122223017 type="checkbox"
									settlement="${row.applicationId}#${row.applicationCode}#${row.totalCommissionAmount}#${row.withdrawnAmount}"
									onclick="selectSingle(this)" channelName="${row.channelName}"
									channelId="${row.channelId}" applicationId="${row.applicationId}"></td>
							</c:otherwise>
						</c:choose>
						<td style="text-align: left;"><a onclick="documentDetail('${row.applicationId}')"
                  href="javascript:void(0)">${row.applicationCode}</a></td>
						<td style="text-align: left;"><a seed=122223019 href="javascript:addTab('渠道商详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${row.channelId}')">${row.channelName}</a></td>
						<td style="text-align: right;">${row.totalCommissionAmount}</td>
						<td style="text-align: right;">${row.withdrawnAmount}</td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
</table>
<script type="text/javascript">
  function documentDetail(applicationId) {
    var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
    addTab('业务单据详情',contextPath + '/commission-settlement-applications/' + applicationId+ '/detail');
    //location.href = "${contextPath}/commission-withdraw-applications/"+applicationId;
  }
  </script>