<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>业务部门统计报表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<style>
th, td {
  word-break: keep-all !important;
}

#deptPerfTable td {
  text-align: left;
}
</style>
</head>
<body pageId=101105>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        业务部门考核报表
        <a href="javascript:exportExcel()" seed=101105004 class="btn btn-creat">导出Excel</a>
      </div>
      <form id="formLessSearch">
        <div class="search-area">
          <div class="search-group">
            <label>出口日期:</label>
            <input seed=101105001 type="text" id="exportDate" />
            <input type="hidden" id="beginExportDate" name="beginExportDate" />
            <input type="hidden" id="endExportDate" name="endExportDate" />
          </div>
          <div class="search-group">
            <label>报关日期:</label>
            <input seed=101105002 type="text" id="declarationDate" />
            <input type="hidden" id="beginDeclarationDate" name="beginDeclarationDate" />
            <input type="hidden" id="endDeclarationDate" name="endDeclarationDate" />
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" type="button" seed=101105003 onclick="refreshDatas()">查询</button>
        </div>
      </form>
      <div id="deptPerfTable" style="overflow-x: auto;"></div>
    </div>
  </div>
</body>
<script type="text/javascript">
	$(function() {
		moment.locale('zh-cn');
		// 获取当前日期
		var date = new Date();
		var year = date.getFullYear();
		var month = date.getMonth();
		if (month < 10) {
			month = "0" + (month + 1)
		}
		var day = date.getDate();
		if (day < 10) {
			day = "0" + day;
		}
		var beginDate = year + "-01" + "-01";
		var endDate = year + "-" + month + "-" + day;
		var beginDate = InitRangeDate(beginDate, endDate);

		refreshDatas();
	});

	function InitRangeDate(beginDate, endDate) {
		$("#exportDate").daterangepicker({
			locale : {
				applyLabel : '选择',
				cancelLabel : '清除'
			},
			startDate : beginDate,
			endDate : endDate,
			showDropdowns : true,
			format : 'YYYY-MM-DD'
		}, function(bDate, eDate) {
		}).on("cancel.daterangepicker", function(ev) {
			$("#beginExportDate").val("");
			$("#endExportDate").val("");
			$("#exportDate").val("");
		}).on(
				"apply.daterangepicker",
				function(ev) {
					$("#beginExportDate").val(
							$("#exportDate").val().substring(0, 10)+" 00:00:00");
					$("#endExportDate").val(
							$("#exportDate").val().substring(
									$("#exportDate").val().length - 10)+" 23:59:59");
				});

		$("#declarationDate").daterangepicker({
			locale : {
				applyLabel : '选择',
				cancelLabel : '清除'
			},
			startDate : beginDate,
			endDate : endDate,
			showDropdowns : true,
			format : 'YYYY-MM-DD'
		}, function(bDate, eDate) {
		}).on("cancel.daterangepicker", function(ev) {
			$("#beginDeclarationDate").val("");
			$("#endDeclarationDate").val("");
			$("#declarationDate").val("");
		}).on(
				"apply.daterangepicker",
				function(ev) {
					$("#beginDeclarationDate").val(
							$("#declarationDate").val().substring(0, 10)+" 00:00:00");
					$("#endDeclarationDate").val(
							$("#declarationDate").val().substring(
									$("#declarationDate").val().length - 10)+" 23:59:59");
				});
	}

	var currentPage;//当前页
	//加载列表数据
	function refreshDatas(isCurrentPage) {
		if (!isCurrentPage) {
			currentPage = 1;
		}
		var searchParam = "&pageNumber=" + currentPage + "&"
				+ $("#formLessSearch").serialize();
		var url = "${contextPath}/appraisal/dept-perf?format=table"
				+ searchParam;
		$('#deptPerfTable').loadWithMask(url, null, function() {
			$(this).find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				refreshDatas(true);
			});
		});
	}

	// 导出excel
	function exportExcel() {
		var searchParam = $("#formLessSearch").serialize();
		window.location.href = "${contextPath}/appraisal/dept-perf/excel?"
				+ searchParam;
	}
</script>
</html>