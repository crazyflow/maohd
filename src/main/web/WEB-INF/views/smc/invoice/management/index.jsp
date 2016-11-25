<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>发票管理列表</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=112433>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				发票管理
				<div class="page-state" id="statusInfo"></div>
			</div>
			<form id="formLessSearch">
				<div class="search-area">
					<div class="search-group">
						<label>发票号码：</label> 
						<input seed=112433001 class="form-control" name="invoiceNumber" type="text" maxlength="30">
					</div>
					<div class="search-group">
						<label>销方名称：</label>
						<input seed=112433002 class="form-control" name="invoiceSellerName" type="text" maxlength="30">
					</div>
					<div class="search-group">
						<label>客服：</label>
						<select seed=112433003 name="customerService" class="form-control">
							<option value="" selected="selected">全部</option>
							<c:forEach items="${customerServiceList}" var="customer">
								<option value="${customer.userId}">${customer.userName}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-group">
					<button seed=112433004 class="btn btn-default btn-search" type="button" onclick="getInvoiceManagementDatas()">查询</button>
					<a seed=112433005 class="more-criteria" href="javascript:moreConditions()">更多条件∨</a>
				</div>
			</form>
			<form id="formMoreSearch">
				<div class="row search-area">
					<div class="search-group">
						<label>发票号码：</label>
						<input seed=112433001 class="form-control" name="invoiceNumber" type="text" maxlength="30">
					</div>
					<div class="search-group">
						<label>销方名称：</label>
						<input seed=112433002 class="form-control" name="invoiceSellerName" type="text" maxlength="30">
					</div>
					<div class="search-group">
						<label>客服：</label>
						<select seed=112433003 name="customerService" class="form-control">
							<option value="" selected="selected">全部</option>
							<c:forEach items="${customerServiceList}" var="customer">
								<option value="${customer.userId}">${customer.userName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="search-group">
						<label>订单编号：</label>
						<input seed=112433006 class="form-control" name="orderCode" type="text" maxlength="30">
					</div>
					<div class="search-group">
						<label>订单状态：</label>
						<select seed=112433007 name="status" id="staus" class="form-control">
							<option value="0" selected="selected">全部</option>
							<option value="1">收齐</option>
							<option value="2">红冲</option>
						</select>
					</div>
				</div>
				<div class="search-group">
					<button seed=112433004 class="btn btn-default btn-search" type="button" onclick="getInvoiceManagementDatas()">查询</button>
					<a seed=112433008 id="linkLess" class="close-criteria" href="javascript:lessConditions()">收起条件∧</a>
				</div>
			</form>
			<div id="ManagementApplicationTable"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	moment.locale ('zh-cn');
	$ (function()
	{
		getInvoiceManagementDatas ();
	});

	var activeFormId = "formLessSearch";//当前有效的form表单id
	var tmp = "";
	//较少的条件
	function lessConditions()
	{
		tmp = "";
		if (activeFormId != 'formLessSearch')
		{
			$ ("#formMoreSearch").animate ({
				height : "50px"
			}, function()
			{
				$ (this).css ("height", "0");
				$ ("#formLessSearch").show ();
			});
		}
		activeFormId = "formLessSearch";
		passValue ($ ("#formMoreSearch"), $ ("#formLessSearch"));
		getInvoiceManagementDatas ();
	}

	//较多的条件
	function moreConditions()
	{
		tmp = "More";
		var formMoreSearchH = $ ("#formMoreSearch .search-area").height ();
		$ ("#formLessSearch").hide ();
		$ ("#formMoreSearch").show ().css ("height", "50px").animate ({
			height : formMoreSearchH
		});
		activeFormId = "formMoreSearch";
		passValue ($ ("#formLessSearch"), $ ("#formMoreSearch"));
		getInvoiceManagementDatas ();
	}

	//表单之间传值
	function passValue(formSource, formTarget)
	{
		formSource.find ("[name]").each (
				function()
				{
					formTarget.find ("[name='" + $ (this).attr ("name") + "']")
							.val ($ (this).val ());
				});
	}

	// 获取发票扫描申请的list数据
	var currentPage;
	function getInvoiceManagementDatas(isCurrentPage)
	{
		if (!isCurrentPage)
		{
			currentPage = 1;
		}
		var searchParam = $ ("#" + activeFormId).serialize ();
		var url = "${contextPath}/invoice-management-applications?format=table&pageNumber=" + currentPage;
		$ ('#ManagementApplicationTable').loadWithMask (url, searchParam,
				function()
				{
					addStatusInfo();
					$ (this).find ('.data-page a').click (function()
					{
						currentPage = $ (this).attr ("data-page");
						getInvoiceManagementDatas (true);
					});
				});
	}
	
	//添加红冲状态数量
	function addStatusInfo(){
		$ ('#statusInfo').loadWithMask ("${contextPath}/invoice-management-applications/status", null,
				function(){
			
		});
	}
	
	//点击红冲状态
	function getDataByStatus(status){
		$('#staus').val('2');
		moreConditions();
		getInvoiceManagementDatas();
	}
</script>
</html>