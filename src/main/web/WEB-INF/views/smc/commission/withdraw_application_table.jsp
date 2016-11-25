<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<table class="table table-bordered table-hover">
	<thead class="bordered-themeprimary">
		<tr>
			<th>佣金提取申请单号</th>
			<th>渠道商</th>
			<th>提取佣金(人民币)</th>
			<th>现汇卖出汇率</th>
			<th>提取佣金(美元)</th>
			<th>状态</th>
			<th>申请人</th>
			<th>申请时间</th>
			<th>操作</th>
		</tr>
	</thead>
	
	<c:choose>
		<c:when test="${empty withdrawList}">
			<tbody>
				<tr>
					<td colspan="9">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${withdrawList}" var="row">
					<tr>
						<td style="text-align: left;"><a seed=122222010 onclick="withDrawDetail('${row.applicationId}')"
									href="javascript:void(0)">${row.applicationCode}</a></td>
						<c:choose>
							<c:when test="${fn:length(row.channelName) > 8}">
								<td style="text-align: left;" title="${row.channelName}"><a seed=122222011 href="javascript:addTab('渠道商详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${row.channelId}')">${fn:substring(row.channelName,0,8)}…</a></td>
							</c:when>
							<c:otherwise>
								<td style="text-align: left;" title="${row.channelName}"><a seed=122222011 href="javascript:addTab('渠道商详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${row.channelId}')">${row.channelName}</a></td>
							</c:otherwise>
						</c:choose>
						<td style="text-align: right;"><fmt:formatNumber value="${row.totalAmount}" pattern="#,##0.00"/></td>
						<td style="text-align: right;"><fmt:formatNumber value="${row.sellingRate}" pattern="#,##0.00"/></td>
                        <td style="text-align: right;">
                          <c:choose>
                            <c:when test="${row.totalForeignAmount > 0}">
              						<fmt:formatNumber value="${row.totalForeignAmount}" pattern="#,##0.00"/>
                            </c:when>
                            <c:otherwise></c:otherwise>
                          </c:choose>
                        </td>
						<c:choose>
							<c:when test="${row.status == 1}">
								<td style="text-align: left;">草稿</td>
							</c:when>
							<c:when test="${row.status == 2}">
								<td style="text-align: left;">待审核</td>
							</c:when>
							<c:when test="${row.status == 3}">
								<td style="text-align: left;">审核通过</td>
							</c:when>
							<c:otherwise>
								<td style="text-align: left;">待修改</td>
							</c:otherwise>
						</c:choose>
						<td style="text-align: left;">${row.createdByName}</td>
						<td style="text-align: left;"><fmt:formatDate value="${row.applicationDate}"
								pattern="yyyy-MM-dd" /></td>
						<c:choose>
							<c:when test="${row.status == 1}">
								<td style="text-align: left;"><a seed=122222010 onclick="withDrawDetail('${row.applicationId}')"
									href="javascript:void(0)">详情</a> 
									<a onclick="editWithDraw('${row.applicationId}')" seed=122222012 href="javascript:void(0)">编辑</a> 
									<a onclick="deleteWithDraw('${row.applicationId}')" seed=122222013	href="javascript:void(0)">删除</a></td>
							</c:when>
							<c:when test="${row.status == 2}">
								<td style="text-align: left;"><a seed=122222010 onclick="withDrawDetail('${row.applicationId}')"
									href="javascript:void(0)">详情</a>
								<c:if test="${row.currentFlowStatus==2}">
										<a seed=122222014 onclick="withDrawDetail('${row.applicationId}')"	href="javascript:void(0)">审核</a>
								</c:if> 
								</td>
							</c:when>
							<c:when test="${row.status == 3}">
								<td style="text-align: left;"><a seed=122222010 onclick="withDrawDetail('${row.applicationId}')"
									href="javascript:void(0)">详情</a></td>
							</c:when>
							<c:otherwise>
								<td style="text-align: left;"><a seed=122222010 onclick="withDrawDetail('${row.applicationId}')"
									href="javascript:void(0)">详情</a>
									<c:if test="${row.currentFlowStatus==4}">
										<a seed=122222012 onclick="editWithDraw('${row.applicationId}')" href="javascript:void(0)">编辑</a> 
									</c:if> 
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
</table>
<script type="text/javascript">
    var s0 = ${statusInfo[0]};
    var s1 = ${statusInfo[1]};
    
	function withDrawDetail(applicationId) {
		var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
		addTab('佣金提取详情',contextPath + '/commission-withdraw-applications/' + applicationId, function(){refreshDatas();});
		//location.href = "${contextPath}/commission-withdraw-applications/"+applicationId;
	}

	function deleteWithDraw(applicationId) {
		$.ajax({
			url : "${contextPath}/commission-withdraw-applications/"+applicationId+"?_method=delete",
			type : "post",
			data : null,
			success : function(result) {
				if (result.success) {
					refreshDatas();
				} else {
					$('.body').showErrorMsg(result.message);
				}
			}
		});
	}

	function editWithDraw(applicationId) {
		var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
		addTab('佣金提取编辑',contextPath + '/commission-withdraw-applications/' + applicationId + '/edit',function(){refreshDatas();});
		//location.href = "${contextPath}/commission-withdraw-applications/"+applicationId+"/edit";
	}
</script>