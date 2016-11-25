<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>佣金提取申请详情</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=122224>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				佣金提取申请详情
				<p class="page-state">${withdraw.applicationCode}</p>
				<c:choose>
					<c:when test="${withdraw.status == 1}">
						<p class="page-state">草稿</p>
					</c:when>
					<c:when test="${withdraw.status == 2}">
						<p class="page-state">待审核</p>
					</c:when>
					<c:when test="${withdraw.status == 3}">
						<p class="page-state">审核通过</p>
					</c:when>
					<c:otherwise>
						<p class="page-state">待修改</p>
					</c:otherwise>
				</c:choose>
			</div>
			<table class="table table-bordered">
				<tbody>
					<tr>
						<th>申请单号</th>
						<td style="text-align: left">${withdraw.applicationCode}</td>
						<th>申请时间</th>
						<td style="text-align: left"><fmt:formatDate
								value="${withdraw.applicationDate}" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<th>业务主体</th>
						<c:choose>
							<c:when test="${withdraw.businessMainBody == 1}">
								<td style="text-align: left">江苏跨境电子商务服务有限公司</td>
							</c:when>
                            <c:when test="${withdraw.businessMainBody == 2}">
                              <td style="text-align: left">南京贸互通电子商务服务有限公司</td>
                            </c:when>
							<c:otherwise>
								<td style="text-align: left">南京贸互达科技有限公司</td>
							</c:otherwise>
						</c:choose>

						<th>渠道商名称</th>
						<td style="text-align: left">${withdraw.channelName}</td>
					</tr>
					<tr>
						<th style="line-height: 30px">收款人*</th>
						<td style="text-align: left">${withdraw.beneficiaryName}</td>
						<th style="line-height: 30px">收款银行*</th>
						<td style="text-align: left">${withdraw.beneficiaryBank}</td>
					</tr>
					<tr>
						<th style="line-height: 30px">银行账号*</th>
						<td style="text-align: left">${withdraw.beneficiaryAccountNo}</td>
						<th style="line-height: 30px">是否提取为美元</th>
						<c:choose>
							<c:when test="${withdraw.currencyCode=='USD'}">
								<td style="text-align: left">是</td>
							</c:when>
							<c:otherwise>
								<td style="text-align: left"></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th style="line-height: 30px">先汇卖出汇率</th>
						<c:choose>
							<c:when test="${withdraw.currencyCode=='USD'}">
								<td style="text-align: left">${withdraw.sellingRate}</td>
							</c:when>
							<c:otherwise>
								<td style="text-align: left"></td>
							</c:otherwise>
						</c:choose>
						<th style="line-height: 30px">备注</th>
						<td style="text-align: left">${withdraw.remark}</td>
					</tr>
				</tbody>
			</table>

			<table class="table table-bordered table-hover">
				<thead bordered-themeprimary>
					<tr>
						<th>佣金结算申请单号</th>
						<th>结算佣金金额(人民币)</th>
						<th>已提取佣金金额(人民币)</th>
						<th>本次提取佣金金额(人民币)</th>
						<th>提取佣金金额(美元)</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${withdraw.withDrawdetails}" var="row">
						<tr>
							<td>${row.settlementApplicationCode}</td>
							<td>${row.commisionAmount}</td>
							<td>${row.withdrawnAmount}</td>
							<td>${row.amount}</td>
							<td>${row.foreignAmount}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<th style="text-align: center;">合计</th>
						<th colspan="3" style="text-align: right;">${withdraw.totalAmount}
							CNY</th>
						<th style="text-align: right;">${withdraw.totalForeignAmount}
							USD</th>
					</tr>
				</tfoot>
			</table>
			<div class="text-right">
				<a style="color: blue; cursor: pointer" onclick="accountDetail('${withdraw.channelId}')" seed=122224001 href="javascript:void(0)">查看渠道商余额
				</a>
			</div>
			<div class="text-right" style="margin-top: 30px">
				<span>制单信息：</span> <span>${withdraw.createdByName}</span> <span><fmt:formatDate
						value="${withdraw.applicationDate}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
			</div>

			<c:if test="${audit==true}">
				<div class="btn-submit">
					<button type="button" seed=122224002 class="btn btn-default btn-font5"
						onclick="showDialog()">审核不通过</button>
					<button type="button" seed=122224003 class="btn btn-primary btn-font5" 
						onclick="audit(true)">审核通过</button>
				</div>
			</c:if>

			<div class="margin-top-30 title">审核信息</div>
			<div id="wftrace">
				<div class="page-body">
					<div class="auditProcess">
						<ul>
							<c:forEach items="${trackLineEmployeeInfo}" var="row">
								<c:choose>
									<c:when test="${row.workState==200}">
										<li class="on">${row.name}</li>
									</c:when>
									<c:otherwise>
										<li>${row.name}</li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</ul>
					</div>

					<div class="auditinfo">
						<table class="table table-bordered">
							<thead class="bordered-themeprimary">
								<tr>
									<th>操作结果</th>
									<th>审批节点名称</th>
									<th>操作人</th>
									<th>审批意见</th>
									<th>操作时间</th>
									<th>处理时长</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${employeeTrackInfos}" var="row">
									<c:choose>
										<c:when test="${empty row.operateContext}">
											<tr>
										</c:when>
										<c:otherwise>
											<tr style="background-color:#FFFF00">
										</c:otherwise>
									</c:choose>
									
										<td>${row.operateSubject}</td>
										<td>${row.nodeName}</td>
										<td>${row.name}</td>
										<td>${row.operateContext}</td>
										<td>${row.operateTime}</td>
										<td>${row.costTimeFullInfo}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 600px;">
				<div class="modal-content">
					<button class="close" aria-hidden="true" type="button"
						data-dismiss="modal">×</button>
					<div class="modal-header mhd-layer-header">
						<div class="DTTTN btn-group mhd-layer-header-btns"></div>
						<h4 class="modal-title mhd-layer-title">审核不通过原因</h4>
					</div>
					<div class="modal-body">
						<div class="page-body mhd-layer-body">
							<textarea seed=122224004 id="comment" style="width: 100%; height: 150px;"></textarea>
						</div>
					</div>
					<div class="modal-footer mhd-layer-footer">
						<button class="btn btn-primary btn-font4" seed=122224005 type="button"
							onclick="audit(false)">确认</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

function accountDetail(channelId) {
	var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
	addTab('佣金台帐详情',contextPath + '/account-statements?channelId=' + channelId);
	//location.href = "${contextPath}/account-statements?channelId=" + channelId;
}

var applicationId = '${withdraw.applicationId}';

function showDialog() {
	$("#myModal").modal("show");
}

function audit(auditValue){
	var comment = "";
	if(!auditValue){
		comment = $("#comment").val();
		if(comment==""){
			$('.body').showErrorMsg('请输入不通过原因!');
			return false;
		}
		$("#myModal").modal("hide");
	}
	$.ajax({
		url : "${contextPath}/commission-withdraw-applications/"+applicationId+"/audit?auditValue="+auditValue+"&comment="+comment,
		type : "post",
		success : function(result) {
			if (result=='') {
				window.location.href = "${contextPath}/submit/success";
				//location.href = "${contextPath}/commission-withdraw-applications"
			}else{
				window.location.href = "${contextPath}/submit/info?message=" + result;
			}
		}
	});
	
}
</script>