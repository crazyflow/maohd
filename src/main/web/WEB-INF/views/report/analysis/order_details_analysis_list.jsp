<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>订单明细分析报表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<style>
th, td {
	word-break: keep-all !important;
}

#orderDetails td {
	text-align: left;
}
</style>
</head>
<body pageId=101003>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				订单明细分析报表 <a href="javascript:exportExcel()" seed=101003019 class="btn btn-creat">导出Excel</a>
			</div>
			<form id="formLessSearch">
				<div class="search-area">
					<div class="search-group">
						<label>日期范围</label>
						<select seed=101003001 name="rangeType" style="width: 80px;">
							<option value=0 selected>不限</option>
							<option value=1>下单日期</option>
							<option value=2>出口日期</option>
							<option value=3>申报日期</option>
						</select>
						<input seed=101003002 type="text" name="rangeDate" onkeypress="javascript:return false" />
						<input type="hidden" name="beginDate" />
						<input type="hidden" name="endDate" />
					</div>
					<div class="search-group">
						<label>搜索条件</label>
						<input seed=101003003 type="text" name="searchVal" />
					</div>
				</div>
				<div class="search-group">
					<button class="btn btn-default btn-search" type="button" seed=101003004 onclick="refreshDatas()">查询</button>
					<a seed=101003007 class="more-criteria" href="javascript:moreConditions()">更多条件∨</a>
				</div>
				<input type="hidden" name="isSellCreditService" value=2 />
				<input type="hidden" name="logisticsService" value=0 />
				<input type="hidden" name="declareType" value=0 />
				<input type="hidden" name="orderFlag" value=2 />
				<input type="hidden" name="customerServiceId" value="" />
				<input type="hidden" name="managerUserId" value="" />
				<input type="hidden" name="orderWay" value=0 />
				<input type="hidden" name="department" value="" />
				<input type="hidden" name="status" value=0 />
				<input type="hidden" name="isDeleted" value=2 />
			</form>
			<form id="formMoreSearch">
				<div class="search-area">
					<div class="search-group">
						<label>日期范围</label>
						<select seed=101003001 name="rangeType" style="width: 80px;">
							<option value=0 selected>不限</option>
							<option value=1>下单日期</option>
							<option value=2>出口日期</option>
							<option value=3>申报日期</option>
						</select>
						<input seed=101003002 type="text" name="rangeDate" onkeypress="javascript:return false" />
						<input type="hidden" name="beginDate" />
						<input type="hidden" name="endDate" />
					</div>
					<div class="search-group">
						<label>搜索条件</label>
						<input seed=101003003 type="text" name="searchVal" />
					</div>
					<div class="search-group">
						<label>赊销易服务</label>
						<select seed=101003009 name="isSellCreditService" class="form-control">
							<option value=2 selected>不限</option>
							<option value=1>是</option>
							<option value=0>否</option>
						</select>
					</div>
					<div class="search-group">
						<label>物流方式</label>
						<select seed=101003010 name="logisticsService" class="form-control">
							<option value=0 selected>不限</option>
							<option value=1>委托物流</option>
							<option value=2>自行安排物流</option>
							<option value=3>待定</option>
						</select>
					</div>
					<div class="search-group">
						<label>报关方式</label>
						<select seed=101003011 name="declareType" class="form-control">
							<option value=0 selected>不限</option>
							<option value=1>贸互达报关</option>
							<option value=2>自行报关</option>
						</select>
					</div>
					<div class="search-group">
						<label>首单/返单</label>
						<select seed=101003012 name="orderFlag" class="form-control">
							<option value=2 selected>不限</option>
							<option value=1>首单</option>
							<option value=0>返单</option>
						</select>
					</div>
					<div class="search-group">
						<label>客服人员</label>
						<select seed=101003013 name="customerServiceId" id="customerServiceId" class="form-control">
							<option value="" selected="selected">不限</option>
						</select>
					</div>
					<div class="search-group">
						<label>外贸顾问</label>
						<select seed=101003014 name="managerUserId" id="managerUserId" class="form-control">
							<option value="" selected="selected">不限</option>
						</select>
					</div>
					<div class="search-group">
						<label>下单方式</label>
						<select seed=101003015 name="orderWay" class="form-control">
							<option value=0 selected>不限</option>
							<option value=1>自助下单</option>
							<option value=2>委托下单</option>
						</select>
					</div>
					<div class="search-group">
						<label>所属部门</label>
						<select seed=101003016 name="department" class="form-control">
							<option value="" selected>不限</option>
							<option value="渠道中心">渠道中心</option>
							<option value="直销中心">直销中心</option>
						</select>
					</div>
					<div class="search-group">
						<label>订单状态</label>
						<select seed=101003017 name="status" class="form-control">
							<option value=0 selected>不限</option>
							<option value=1>草稿</option>
							<option value=10>完善中</option>
							<option value=20>审核中</option>
							<option value=30>待确认</option>
							<option value=40>待修改</option>
							<option value=50>执行中</option>
							<option value=52>已取消</option>
							<option value=90>订单完成</option>
							<option value=92>订单关闭</option>
						</select>
					</div>
					<div class="search-group">
						<label>是否删除</label>
						<select seed=101003018 name="isDeleted" class="form-control">
							<option value=2 selected>不限</option>
							<option value=1>是</option>
							<option value=0>否</option>
						</select>
					</div>
				</div>
				<div class="search-group">
					<button class="btn btn-default btn-search" seed=101003004 type="button" onclick="refreshDatas()">查询</button>
					<a id="linkLess" class="close-criteria" seed=101003008 href="javascript:lessConditions()">收起条件∧</a>
				</div>
			</form>
			<div id="orderDetails" style="overflow-x: auto;"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	moment.locale ('zh-cn');
	$ (function()
	{
		// 初始化日期
		date = new Date ();
		var year = date.getFullYear ();
		var month = date.getMonth () + 1;
		if (month < 10)
		{
			month = "0" + (month)
		}
		var day = date.getDate ();
		var beginDate = year + "-" + month + '-01';
		var endDate = year + "-" + month + day;
		InitRangeDate (beginDate, endDate);
		// 获取客服数据,下拉框
		$ ("#customerServiceId").append ("${customerServices}");//加载客服下拉框
		$ ("#managerUserId").append ("${managerUsers}");//加载客服下拉框
		refreshDatas ();
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
		refreshDatas ();
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
		refreshDatas ();
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

	var currentPage;//当前页
	//加载列表数据
	function refreshDatas(isCurrentPage)
	{
		if (!isCurrentPage)
		{
			currentPage = 1;
		}
		var searchParam = "&pageNumber=" + currentPage + "&"
				+ $ ("#" + activeFormId).serialize ();
		var url = "${contextPath}/analysis/order-detail?format=table"
				+ searchParam;
		$ ('#orderDetails').loadWithMask (url, null, function()
		{
			$ (this).find ('.data-page a').click (function()
			{
				currentPage = $ (this).attr ("data-page");
				refreshDatas (true);
			});
		});
	}

	// 导出excel
	function exportExcel()
	{
		var searchParam = $ ("#" + activeFormId).serialize ();
		window.location.href = "${contextPath}/analysis/order-detail/excel?"
				+ searchParam;
	}

	function InitRangeDate(beginDate, endDate)
	{
		var isSelDefault = true;
		$ ("input[name=rangeDate]").daterangepicker ({
			locale : {
				applyLabel : '选择',
				cancelLabel : '清除'
			},
			startDate : beginDate,
			endDate : endDate,
			showDropdowns : true,
			format : 'YYYY-MM-DD'
		}, function(bDate, eDate)
		{
		}).on ("cancel.daterangepicker", function(ev)
		{
			$ ("input[name=beginDate]").val ("");
			$ ("input[name=endDate]").val ("");
			$ ("input[name=rangeDate]").val ("");
		}).on (
				"apply.daterangepicker",
				function(ev,picker)
				{
					$ ("input[name=beginDate]").val (picker.startDate.format('YYYY-MM-DD'));
					$ ("input[name=endDate]").val (picker.endDate.format('YYYY-MM-DD'));
				});
	}
</script>
</html>