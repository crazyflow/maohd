<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>客服统计报表</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        客服统计报表
        <a class="btn btn-creat" href="javascript:exportExcel()">导出Excel</a>
      </div>
      <form id="formLessSearch">
        <div class="search-area">
          <div class="search-group">
            <label>客服人员：</label>
            <select id="customerService" class="form-control" name="customerServiceId">
              <option value="">全部</option>
            </select>
          </div>
          <div class="search-group">
            <label>委托客户：</label>
            <input class="form-control" name="companyName" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>订单号：</label>
            <input class="form-control" name="orderCode" type="text" maxlength="30">
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" type="button" onclick="refreshDatas()">查询</button>
          <a class="more-criteria" href="javascript:moreConditions()">更多条件∨</a>
        </div>
      </form>
      <form id="formMoreSearch">
        <div class="search-area">
          <div class="search-group">
            <label>客服人员：</label>
            <select id="customerServiceMore" class="form-control" name="customerServiceId">
              <option value="">全部</option>
            </select>
          </div>
          <div class="search-group">
            <label>委托客户：</label>
            <input class="form-control" name="companyName" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>订单号：</label>
            <input class="form-control" name="orderCode" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>收汇日期：</label>
            <input readonly="readonly" type="text" id="txt" value="" name="BeginDate_EndDate" class="active">
            <input type="hidden" id="hidBegin" name="beginDate" value="">
            <input type="hidden" id="hidEnd" name="endDate" value="">
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" type="button" onclick="refreshDatas()">查询</button>
          <a id="linkLess" class="close-criteria" href="javascript:lessConditions()">收起条件∧</a>
        </div>
      </form>
      <div id="table" style="overflow-x: auto;"></div>
    </div>
  </div>
</body>
<script type="text/javascript">
	var date = new Date();
	var nowDate = dateFormat(date.getFullYear(), date.getMonth() + 1, date
			.getDay());
	moment.locale('zh-cn');
	$(function() {
		var startDate = nowDate;
		var endDate = nowDate;
		drpInit(startDate, endDate);
	});
	function drpInit(beginDate, endDate) {
		var isSelDefault = true;
		$("#txt").daterangepicker({
			locale : {
				applyLabel : '选择',
				cancelLabel : '清除'
			},
			startDate : beginDate,
			endDate : endDate,
			showDropdowns : true,
			format : 'YYYY-MM-DD'
		}, function(bDate, eDate) {
			isSelDefault = false;
			$("#hidBegin").val(bDate.format('YYYY-MM-DD 00:00:00'));
			$("#hidEnd").val(eDate.format('YYYY-MM-DD 23:59:59'));
		}).on("cancel.daterangepicker", function(ev) {
			$("#txt").val("");
			$("#hidBegin").val("");
			$("#hidEnd").val("");
			$("#txt").blur();
		}).on("apply.daterangepicker", function(ev) {
			if (isSelDefault) {
				$("#hidBegin").val(nowDate);
				$("#hidEnd").val(nowDate);
				isSelDefault = true;
			}
			$("#txt").blur();
		});
	}

	$(function() {
		refreshDatas();
		lessConditions();
		getCustomerService();
		$('#formLessSearch, #formMoreSearch').keydown(function(e) {
			var code = e.keyCode | e.charCode;
			if (code == 13) {
				refreshDatas();
			}
		});
	});

	function getCustomerService() {
		//$("#customerService"+tmp+" option").remove();
		$("#customerService" + tmp).append("${customerServices}");//加载客服下拉框
	}

	var activeFormId = "formLessSearch";//当前有效的form表单id
	var tmp = "";
	//较少的条件
	function lessConditions() {
		tmp = "";
		if (activeFormId != 'formLessSearch') {
			$("#formMoreSearch").animate({
				height : "50px"
			}, function() {
				$(this).css("height", "0");
				$("#formLessSearch").show();
			});
		}
		activeFormId = "formLessSearch";
		passValue($("#formMoreSearch"), $("#formLessSearch"));
	}

	//较多的条件
	function moreConditions() {
		tmp = "More";
		var formMoreSearchH = $("#formMoreSearch .search-area").height();
		$("#formLessSearch").hide();
		$("#formMoreSearch").show().css("height", "50px").animate({
			height : formMoreSearchH
		});
		activeFormId = "formMoreSearch";
		passValue($("#formLessSearch"), $("#formMoreSearch"));
		getCustomerService();
	}

	//表单之间传值
	function passValue(formSource, formTarget) {
		formSource.find("[name]").each(
				function() {
					formTarget.find("[name='" + $(this).attr("name") + "']")
							.val($(this).val());
				});
	}

	var currentPage;//当前页
	var searchParam;//查询表单数据
	//加载列表数据
	function refreshDatas(isCurrentPage) {

		searchParam = $("#" + activeFormId).serialize();
		if (!isCurrentPage) {
			currentPage = 1;
		}
		searchParam += "&pageNumber=" + currentPage;
		var url = "${contextPath}/operation/customer-service?format=table&"
				+ searchParam;
		$('#table').loadWithMask(url, null, function(html) {
			var table = $('#table');
			table.find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				refreshDatas(true)
			});
		});
	}

	function exportExcel() {
		var param = $("#" + activeFormId).serialize();
		location.href = "${contextPath}/operation/customer-service/excel?"
				+ param;
	}
</script>