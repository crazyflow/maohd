<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>发票采集管理</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
<style>
th, td {
	word-break: keep-all !important;
}
</style>
</head>
<body pageId=112428>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				发票采集管理
				<a class="btn btn-creat" seed=112428016 href="javascript:invoiceScanningOperation('发票扫描申请','/invoice-scanning-applications/add')">发票扫描申请</a>
			</div>
			<form id="formLessSearch">
				<div class="search-area">
					<div class="search-group">
						<label>发票扫描单号</label>
						<input seed=112428001 class="form-control" name="applicationCode" />
					</div>
					<div class="search-group">
						<label>订单编号</label>
						<input seed=112428002 class="form-control" name="orderCode" />
					</div>
					<div class="search-group">
						<label>扫描申请状态</label>
						<select seed=112428003 name="applicationStatus">
							<option value=0 selected>全部</option>
							<option value=1>扫描待收票</option>
							<option value=2>待扫描</option>
							<option value=3>扫描拒收票</option>
							<option value=4>扫描通过</option>
							<option value=9>扫描退票</option>
						</select>
					</div>
					<input type="hidden" name="orderInvoiceCheckStatus" value=0 />
				</div>
				<div class="search-group">
					<button class="btn btn-default btn-search" type="button" seed=112428004 onclick="getInvoiceScanningDatas()">查询</button>
					<a id="moreConditions" seed=112428005 class="more-criteria" href="javascript:moreConditions()">更多条件∨</a>
				</div>
			</form>
			<form id="formMoreSearch">
				<div class="search-area">
					<div class="search-group">
						<label>发票扫描单号</label>
						<input seed=112428001 class="form-control" name="applicationId" />
					</div>
					<div class="search-group">
						<label>订单编号</label>
						<input seed=112428002 class="form-control" name="orderCode" />
					</div>
					<div class="search-group">
						<label>扫描申请状态</label>
						<select seed=112428003 name="applicationStatus">
							<option value=0 selected>全部</option>
							<option value=1>扫描待收票</option>
							<option value=2>待扫描</option>
							<option value=3>扫描拒收票</option>
							<option value=4>扫描通过</option>
							<option value=9>扫描退票</option>
						</select>
					</div>
					<div class="search-group">
						<label>比对状态</label>
						<select seed=112428006 name="orderInvoiceCheckStatus">
							<option value=0 selected>全部</option>
							<option value=10>比对待收票</option>
							<option value=20>比对拒收票</option>
							<option value=30>待比对</option>
							<option value=40>比对通过</option>
							<option value=50>比对不通过</option>
						</select>
					</div>
				</div>
				<div class="search-group">
					<button seed=112428004 class="btn btn-default btn-search" type="button" onclick="getInvoiceScanningDatas()">查询</button>
					<a id="lessConditions" class="close-criteria" seed=112428007 href="javascript:lessConditions()">收起条件∧</a>
				</div>
			</form>
			<div id="ScanningApplicationTable"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	moment.locale ('zh-cn');
	$ (function()
	{
		getInvoiceScanningDatas ();
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
		getInvoiceScanningDatas ();
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
		getInvoiceScanningDatas ();
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
	function getInvoiceScanningDatas(isCurrentPage)
	{
		if (!isCurrentPage)
		{
			currentPage = 1;
		}
		var searchParam = $ ("#" + activeFormId).serialize ();
		var url = "${contextPath}/invoice-scanning-applications?format=table&pageNumber="
				+ currentPage;
		$ ('#ScanningApplicationTable').loadWithMask (url, searchParam,
				function()
				{
					$ (this).find ('.data-page a').click (function()
					{
						currentPage = $ (this).attr ("data-page");
						getInvoiceScanningDatas (true);
					});
				});
	}
	
	// 删除结算申请
	function removeInvoiceScanning(obj)
	{
		// 获取索要删除的结算单号 
		var applicationId = $ (obj).parent ().parent ().find ("td:first").attr (
				"applicationId");
		var url = "${contextPath}/commission-settlement-applications/"
				+ applicationId + "?_method=delete";
		$.ajax ({
			url : url,
			type : "post",
			success : function(result)
			{
				getInvoiceScanningDatas ();
			}
		});
	}
	
	// 打开tab页
	var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port;
	// 操作
	function invoiceScanningOperation(title, url)
	{
		addTab (title, contextPath + url, function()
		{
			getInvoiceScanningDatas ();
		});
	}	
</script>