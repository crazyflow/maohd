<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>虚拟子账户详情</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body pageId=112115>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				虚拟子账户详情
				
			</div>
			<div class="modal-body">
				
				<form id="newWithDrawForm" action="${contextPath}/bank-account"
					method="post">
					
					<c:forEach items="${consigner}" var="row">
					<table class="table table-bordered">
						<tr>
							<th style="line-height: 30px">银行</th>
							<td style="text-align: left">
								
								${row.bankName}
							</td>
							
						</tr>
						<tr>
							<th style="line-height: 30px">业务主体</th>
							<td style="text-align: left">
								${row.bodyName}
								
							</td>
							
						</tr>
						<tr>
							<th style="line-height: 30px">主账号</th>
							<td style="text-align: left">
								${row.accountNo}
								
							</td>
							
						</tr>
						<tr>
							<th>委托客户</th>
							<td style="text-align: left">
								<a seed=112115001 href="javascript:addTab('客户详情','/customer/companyInfo.aspx?id=${row.consignerId}')">${row.consignerName}</a>
							</td>
							
						</tr>
						<tr>
							<th style="line-height: 200px;width:20%">子账户信息</th>
							<td style="text-align: left;">
								<div style="width:70%;border-color:black;height:180px;background-color:#EAEAEA;border-radius:10px">
									
									<div style="height:10px;margin-left:5%;width:20%"></div>
									<span style="height:20px;margin-left:5%;width:20%">开户银行:</span><span style="margin-left:8%">${row.bankEnname }</span><br/><br/>
									<span style="height:20px;margin-left:5%;width:20%">开户银行地址:</span><span style="margin-left:3.5%">${row.enaddress }</span><br/><br/>
									<span style="height:20px;margin-left:5%;width:20%">SWIFT Code:</span><span style="margin-left:5%">${row.swiftCode}</span><br/><br/>
									<span style="height:20px;margin-left:5%;width:20%">户名:</span><span style="margin-left:12%">${row.accountName}</span><br/><br/>
									<span style="height:20px;margin-left:5%;width:20%">账号:</span><span style="margin-left:12%">${row.accountNo}${row.subAccountNo}</span>
								</div>
							</td>
							
						</tr>
						
						<tr>
							<th style="line-height: 30px">备注</th>
							<td style="text-align: left">${row.remark}</td>
							
						</tr>
					</table>
					
					<div style="float:right">制单信息:&nbsp; <span>${row.createdByName}&nbsp;<fmt:formatDate value="${row.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
					<br/>
					</c:forEach>
				</form>
			</div>

			
		</div>
	</div>
</body>

