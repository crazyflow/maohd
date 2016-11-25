<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>佣金结算申请列表</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
<style>
th, td {
	word-break: keep-all !important;
}

#commissionSettlementTable td {
	text-align: left;
}
</style>
</head>
<body pageId=122218>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				佣金结算申请列表
				<div class="page-state" id="statusInfo"></div>
				<a class="btn btn-creat" seed=122218010 href="javascript:showChannelDialog()">新增佣金结算申请</a>
			</div>
			<form id="formLessSearch">
				<div class="search-area">
					<div class="search-group">
						<label>结算申请单号：</label>
						<input seed=122218001 class="form-control" name="applicationCode" />
					</div>
					<div class="search-group">
						<label>渠道商：</label>
						<input seed=122218002 class="form-control" name="channelName" />
					</div>
					<div class="search-group">
						<label>状态：</label>
						<select seed=122218003 name="status">
							<option value=0>所有</option>
							<option value=1>草稿</option>
							<option value=2>待审核</option>
							<option value=3>审核通过</option>
							<option value=4>待修改</option>
						</select>
					</div>
				</div>
				<div class="search-group" >
					<button class="btn btn-default btn-search" seed=122218004 type="button" onclick="getSettlementDatas()">查询</button>
					<a id="moreConditions" class="more-criteria" seed=122218005 href="javascript:moreConditions()">更多条件∨</a>
				</div>
			</form>
			<form id="formMoreSearch">
				<div class="search-area">
					<div class="search-group">
						<label>结算申请单号：</label>
						<input seed=122218001 class="form-control" name="applicationCode" />
					</div>
					<div class="search-group">
						<label>渠道商：</label>
						<input seed=122218002 class="form-control" name="channelName" />
					</div>
					<div class="search-group">
						<label style="width: 80px; text-align: right;">状态：</label>
						<select seed=122218003 name="status">
							<option value=0>所有</option>
							<option value=1>草稿</option>
							<option value=2>待审核</option>
							<option value=3>审核通过</option>
							<option value=4>待修改</option>
						</select>
					</div>
					<div class="search-group">
						<label>订单号：</label>
						<input seed=122218006 class="form-control" name="orderCode" />
					</div>
					<div class="search-group">
						<label>申请时间：</label>
						<input seed=122218007 class="form-control" id="applicationDate" name="applicationDate" />
					</div>
					<div class="search-group">
						<label>申请人：</label>
						<input seed=122218008 class="form-control" name="proposerName" />
					</div>
				</div>
				<div class="search-group">
					<button class="btn btn-default btn-search" type="button" seed=122218004 onclick="getSettlementDatas()">查询</button>
					<a id="lessConditions" class="close-criteria" seed=122218009 href="javascript:lessConditions()">收起条件∧</a>
				</div>
			</form>
			<div id="commissionSettlementTable"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	moment.locale ('zh-cn');
	$ (function()
	{
		date = new Date ();
		var year = date.getFullYear ();
		var month = date.getMonth () + 1;
		if (month < 10)
		{
			month = "0" + (month)
		}
		var day = date.getDate ();
		if (day < 10)
		{
			day = "0" + (day)
		}
		var applicationDate = year + "-" + month + "-" + day
		setDate (applicationDate);
		getSettlementDatas ();
	});

	function setDate(applyDate)
	{
		$ ("#applicationDate").daterangepicker ({
			singleDatePicker : true,
			showDropdowns : true,
			timePicker : false,
			startDate : date,
			endDate : date,
			format : 'YYYY-MM-DD',
			locale : {
				applyLabel : '选择',
				cancelLabel : '清除'
			}
		}, function(bDate, eDate)
		{

		}).on ("cancel.daterangepicker", function(ev)
		{
			$ ("#applicationDate").val ("");
		});
	}

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
		getSettlementDatas ();
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
		getSettlementDatas ();
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

	var currentPage;
	//加载佣金结算列表数据
	function getSettlementDatas(isCurrentPage)
	{
		if (!isCurrentPage)
		{
			currentPage = 1;
		}
		var searchParam;
		if (toDealWith == 0)
		{
			searchParam = $ ("#" + activeFormId).serialize ();
		}
		else
		{
			searchParam = $ ("#" + activeFormId).serialize ()
					+ "&workflowStatus=" + toDealWith;
		}
		var url = "${contextPath}/commission-settlement-applications?format=table&pageNumber="
				+ currentPage;
		$ ('#commissionSettlementTable').loadWithMask (url, searchParam,
				function()
				{
					addStatusInfo();
					$ (this).find ('.data-page a').click (function()
					{
						currentPage = $ (this).attr ("data-page");

						getSettlementDatas (true);
					});
				});
	}

	// 是否点击待我审核和待我修改
	var toDealWith = 0;
	// 待我修改待我审核点击按钮
	function setWorkflowStatus(id)
	{
		toDealWith = id;
		getSettlementDatas ();
	}

	// 渠道商dialog
	var $channelDlg;
	function showChannelDialog()
	{
		if (!$channelDlg)
		{
			$channelDlg = $ ("<div style='overflow: hidden;' id='channelDlg' />");
			$ ("body").append ($channelDlg);
			var url = "${contextPath}/channels/commission-settlement";
			$channelDlg.loadWithMask (url, null, function()
			{
				$ ('#channelModal').modal ({
					keyboard : false
				});
				//$ (".modal-backdrop").css ("opacity", 0);
				$ ('#channelModal').on ('show.bs.modal', function()
				{
					// 打开modal初始化渠道商列表
					getChannelDatas ();
				});
				$ ('#channelModal').on ('hide.bs.modal', function()
				{
					// 关闭modal时
				});
				$ ('#channelModal').modal ("show");
			});
		}
		$ ("#channelModal").modal ("show");
	}

	function addStatusInfo(){		
		var html = "";
		if(s1 > 0){
			html += "<a style='color: black; cursor: pointer' onclick='setWorkflowStatus(1)' href='javascript:void(0)'>待我修改 (<span style='color: red'>"+s1+"</span>)</a>";
		}else{
			html += "<a style='color: black; cursor: pointer'>待我修改 (<span style='color: gray'>0</span>)</a>";
		}
		if(s0 > 0){
			html += "<a style='color: black; cursor: pointer' onclick='setWorkflowStatus(2)' href='javascript:void(0)'>待我审核(<span style='color: red'>"+s0+"</span>)</a>";
		}else{
			html += "<a style='color: black; cursor: pointer'>待我审核 (<span style='color: gray'>0</span>)</a>";
		}
		$("#statusInfo").html(html);
	}
	
	// 渠道商列表数据
	var channelCurrentPage;
	function getChannelDatas(isCurrentPage)
	{
		$formChannel = $ ("#formChannel");
		if (!isCurrentPage)
		{
			channelCurrentPage = 1;
		}
		var searchParam = $formChannel.serialize ();
		var url = "${contextPath}/channels/commission-settlement?format=table&pageNumber="
				+ channelCurrentPage;
		$ ("#channelTable").loadWithMask (url, searchParam, function()
		{
			$ (this).find ('.data-page a').click (function()
			{
				channelCurrentPage = $ (this).attr ("data-page");
				getChannelDatas (true);
			});
		});
	}
</script>