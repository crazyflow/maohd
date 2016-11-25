<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>委托方出口分析表</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body pageId=101004>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				委托方出口分析表 <a href="javascript:exportExcel()" seed=101004014 class="btn btn-creat">导出Excel</a>
			</div>
			<form id="formLessSearch">
				<div class="search-area">
					<div class="search-group">
						<label>日期范围</label>
						<select seed=101004001 name="rangeDateType" style="width: 100px;">
							<option value=0>不限</option>
							<option value=1>签约日期</option>
							<option value=2>首单出口日期</option>
						</select>
						<input seed=101004002 class="form-control" name="rangeDateVal" onkeypress="javascript:return false" />
						<input type="hidden" name="beginDate" value="">
						<input type="hidden" name="endDate" value="">
					</div>
					<div class="search-group">
						<label>委托客户：</label>
						<input seed=101004003 class="form-control" name="companyName" type="text">
						<input type="hidden" name="industryType" value=0 />
						<input type="hidden" name="productSourceType" value=0 />
						<input type="hidden" name="provinceId" value=0 />
						<input type="hidden" name="cityId" value=0 />
						<input type="hidden" name="orderCount" value="" />
						<input type="hidden" name="firstOrderManagerUser" value="" />
						<input type="hidden" name="customerService" value="" />
					</div>
				</div>
				<div class="search-group">
					<button class="btn btn-default btn-search" type="button" seed=101004004 onclick="refreshDatas()">查询</button>
					<a class="more-criteria" seed=101004005 href="javascript:moreConditions()">更多条件∨</a>
				</div>
			</form>
			<form id="formMoreSearch">
				<div class="search-area">
					<div class="search-group">
						<label>日期范围</label>
						<select seed=101004001 name="rangeDateType" style="width: 100px;">
							<option value=0>不限</option>
							<option value=1>签约日期</option>
							<option value=2>首单出口日期</option>
						</select>
						<input seed=101004002 class="form-control" name="rangeDateVal" onkeypress="javascript:return false">
						<input type="hidden" name="beginDate" value="">
						<input type="hidden" name="endDate" value="">
					</div>
					<div class="search-group">
						<label>委托客户：</label>
						<input seed=101004003 class="form-control" name="companyName" type="text">
					</div>
					<div class="search-group">
						<label>所属行业：</label>
						<select seed=101004006 id="industryType" class="form-control" name="industryType">
							<option value=0 selected>全部</option>
						</select>
					</div>
					<div class="search-group">
						<label>企业类型：</label>
						<select seed=101004007 id="productSourceType" class="form-control" name="productSourceType">
							<option value=0 selected>全部</option>
						</select>
					</div>
					<div class="search-group">
						<label>所属省市：</label>
						<select seed=101004008 id="provinceId" class="form-control" name="provinceId" style="width: 80px;">
							<option value=0 selected>全部</option>
						</select>
						<select seed=101004009 id="cityId" class="form-control" name="cityId" style="width: 80px;">
							<option value=0 selected>全部</option>
						</select>
					</div>
					<div class="search-group">
						<label>订单数：</label>
						<input seed=101004010 class="form-control" name="orderCount" onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" />
					</div>
					<div class="search-group">
						<label>首单顾问：</label>
						<select seed=101004011 id="firstOrderManagerUser" class="form-control" name="firstOrderManagerUser">
							<option value="" selected="selected">全部</option>
						</select>
					</div>
					<div class="search-group">
						<label>客服人员：</label>
						<select seed=101004012 id="customerService" class="form-control" name="customerService">
							<option value="" selected="selected">全部</option>
						</select>
					</div>
				</div>
				<div class="search-group">
					<button class="btn btn-default btn-search" type="button" seed=101004004 onclick="refreshDatas()">查询</button>
					<a id="linkLess" class="close-criteria" seed=101004013 href="javascript:lessConditions()">收起条件∧</a>
				</div>
			</form>
			<div id="table"></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var date = new Date ();
	var nowDate = dateFormat (date.getFullYear (), date.getMonth () + 1, date
			.getDay ());
	moment.locale ('zh-cn');
	$ (function()
	{
		var startDate = nowDate;
		var endDate = nowDate;
		drpInit (startDate, endDate);
	});

	function drpInit(beginDate, endDate)
	{
		$ ("input[name=rangeDateVal]").daterangepicker ({
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
			$ ("input[name=rangeDateVal]").val ("");
		}).on (
				"apply.daterangepicker",
				function(ev,picker)
				{
					$ ("input[name=beginDate]").val (picker.startDate.format('YYYY-MM-DD'));
					$ ("input[name=endDate]").val (picker.endDate.format('YYYY-MM-DD'));
				});
	}

	$ (function()
	{
		refreshDatas ();
		lessConditions ();

		$ ('#formLessSearch, #formMoreSearch').keydown (function(e)
		{
			var code = e.keyCode | e.charCode;
			if (code == 13)
			{
				refreshDatas ();
			}
		});
	});

	function getCustomerService()
	{
		$ ("#customerService").append ("${customerServices}");//加载客服下拉框
	}
	function getproductSourceType()
	{
		$ ("#productSourceType").append ("${productSourceType}");
	}
	function getIndustryType()
	{
		$ ("#industryType").append ("${industryType}")
	}
	function getProvinceName()
	{
		$ ("#provinceId").append ("${provinceName}")
	}
	function getfirstOrderManagerUser()
	{
		$ ("#firstOrderManagerUser").append ("${firstOrderManagerUser}")
	}
	function getCity()
	{
		$ ("[name='provinceId']").change (
				function()
				{
					var provinceid = $ ("#provinceId").val ();
					var url = "${contextPath}/analysis/getCity/" + provinceid;

					$.ajax ({
						url : url,
						type : 'get',
						contentType : "json",
						success : function(data)
						{
							var cityname = data.cityName;
							$ ("#cityId").empty ();
							for (i = 0; i < cityname.length; i++)
							{

								$ ("#cityId").append (
										"<option value='"+cityname[i].cityId+"'>"
												+ cityname[i].cityName
												+ "</option>")
							}
						}
					})

				})
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
		getCustomerService ();
		getproductSourceType ();
		getIndustryType ();
		getProvinceName ();
		getCity ();
		getfirstOrderManagerUser ();
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
	var searchParam;//查询表单数据
	//加载列表数据
	function refreshDatas(isCurrentPage)
	{
		if ($ ("#provinceId").val () == 0)
		{
			$ ("input[name = cityId]").val (0);
		}
		searchParam = $ ("#" + activeFormId).serialize ();
		// 判断订单数量
		if (!isCurrentPage)
		{
			currentPage = 1;
		}
		searchParam += "&pageNumber=" + currentPage;
		var url = "${contextPath}/analysis/consigner-export?format=table&"
				+ searchParam;
		$ ('#table').loadWithMask (url, null, function(html)
		{
			var table = $ ('#table');
			table.find ('.data-page a').click (function()
			{
				currentPage = $ (this).attr ("data-page");
				refreshDatas (true)
			});
		});
	}

	function exportExcel()
	{
		if ($ ("#provinceId").val () == 0)
		{
			$ ("input[name = cityId]").val (0);
		}
		var param = $ ("#" + activeFormId).serialize ();
		location.href = "${contextPath}/analysis/consigner-export/excel?"
				+ param;
	}
</script>