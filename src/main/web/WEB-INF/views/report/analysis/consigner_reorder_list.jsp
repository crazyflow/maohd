<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>委托方返单率报表</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script src="${staticPath}/Contents/js/charts/morris/raphael-2.0.2.min.js"></script>
<script src="${staticPath}/Contents/js/charts/morris/morris.js"></script>
</head>
<body pageId=101002>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				委托方返单率报表
				<div id="divNotice" class="page-state">
					<p class="page-state" data-flow="SHDD" data-label="1"></p>
				</div>
				<div class="DTTT btn-group"></div>
			</div>
			<div class="row">
				<div class="col-lg-12 col-sm-12 col-xs-12">
					<form class="form-inline" class="form-horizontal" id="reorderForm" style="display: inline-block; width: 500px;">
						<div class="form-group">
							<div class="controls">
								日期范围:
								<div class="input-group">
									<span class="input-group-addon"> <i class="fa fa-calendar"></i>
									</span>
									<input seed=101002001 type="text" class="form-control" id="rangeDate" onkeypress="javascript:return false" />
									<input type="hidden" id="beginDate" name="beginDate" value="" />
									<input type="hidden" id="endDate" name="endDate" value="" />
								</div>
							</div>
						</div>
						<div class="form-group" style="margin-left: 40px;">
							<div class="radio">
								<label>
									统计区间
									<input name="form-field-radio" type="radio" class="colored-blue" checked="checked">
									<span class="text">月度</span>
								</label>
							</div>
						</div>
					</form>
					<button seed=101002002 class="btn btn-blue" onclick="refreshDatas()">查询</button>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-md-12 col-lg-12">
					<div class="widget">
						<div class="widget-body">
							<div id="reorderLineChart" class="chart chart-lg"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-md-2"></div>
				<div class="col-xs-12 col-md-8">
					<div id="activeTable">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>区间</th>
									<th>本期返单笔数</th>
									<th>本期返单客户数</th>
									<th>返单率(%)</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-xs-12 col-md-2"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$ (function()
	{
		moment.locale ('zh-cn');
		// 获取当前日期
		date = new Date ();
		var year = date.getFullYear ();
		var month = date.getMonth () + 1;
		if (month < 10)
		{
			month = "0" + (month)
		}
		var day = "-01";
		var beginDate = year + "-" + "01" + day;
		var endDate = year + "-" + month + day;
		$ ("#rangeDate").val (year + "-" + "01" + " - " + year + "-" + month);
		$ ("#beginDate").val (beginDate);
		$ ("#endDate").val (endDate);
		InitRangeDate (beginDate, endDate);

		/*Sets Themed Colors Based on Themes*/
		themeprimary = getThemeColorFromCss ('themeprimary');
		themesecondary = getThemeColorFromCss ('themesecondary');
		themethirdcolor = getThemeColorFromCss ('themethirdcolor');
		themefourthcolor = getThemeColorFromCss ('themefourthcolor');
		themefifthcolor = getThemeColorFromCss ('themefifthcolor');

		$tbody = $ ("#activeTable").find ("tbody");
		refreshDatas ();
	});

	//加载列表数据
	function refreshDatas()
	{
		var url = "${contextPath}/analysis/consigner-reorder?"
				+ $ ("#reorderForm").serialize ();
		$ ("#loading").show ();
		$.ajax ({
			url : url,
			type : "get",
			dataType : "json",
			contentType : "application/json",
			success : function(result)
			{
				var line_char_json = [];
				$tbody.empty ();
				$.each (result, function(index, item)
				{
					var dateByMonth = item.dateByMonth.substring (0, 7);
					var reorderNumber = item.reorderNumber;
					var reorderCustomer = item.reorderCustomer;
					$tbody.append ("<tr><td>"
							+ item.dateByMonth.substring (0, 7)
							+ "</td><td>"
							+ item.reorderNumber
							+ "</td><td>"
							+ item.reorderCustomer
							+ "</td><td>"
							+ parseFloat (reorderNumber / reorderCustomer)
									.toFixed (2) + "</td></tr>");
					line_char_json.push ({
						dateByMonth : dateByMonth,
						reorderNumber : reorderNumber,
						reorderCustomer : reorderCustomer
					});

				});
				$ ("#reorderLineChart").empty ();
				Morris.Line ({
					parseTime : false,
					element : 'reorderLineChart',
					data : line_char_json,
					xkey : 'dateByMonth',
					ykeys : [ 'reorderNumber', 'reorderCustomer' ],
					labels : [ '本期返单笔数', '本期返单客户数' ],
					lineColors : [ themeprimary, themethirdcolor ]
				});
				$ ("#loading").hide ();
			}
		});
	}

	function InitRangeDate(beginDate, endDate)
	{
		$ ("#rangeDate").daterangepicker ({
			locale : {
				applyLabel : '选择',
				cancelLabel : '清除'
			},
			startDate : beginDate,
			endDate : endDate,
			showDropdowns : true,
			format : 'YYYY-MM'
		}, function(bDate, eDate)
		{
		}).on ("cancel.daterangepicker", function(ev)
		{
			$ ("#beginDate").val ("");
			$ ("#endDate").val ("");
			$ ("#rangeDate").val ("");
		}).on ("apply.daterangepicker", function(ev, picker)
		{
			$ ("#beginDate").val (picker.startDate.format ('YYYY-MM-DD'));
			$ ("#endDate").val (picker.endDate.format ('YYYY-MM-DD'));
		});
	}
</script>
</html>