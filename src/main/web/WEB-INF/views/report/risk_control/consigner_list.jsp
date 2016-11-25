<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>委托方报表</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body pageId=101310>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        委托方报表
        <a href="javascript:exportExcel()" seed=101310004 class="btn btn-creat">导出Excel</a>
      </div>
      <form id="formLessSearch">
        <div class="search-area">
          <div class="search-group">
            <label>委托客户：</label>
            <input seed=101310001 class="form-control" name="name" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>签约状态：</label>
            <select seed=101310002 name="signFlag" class="form-control">
              <option value=-1>全部</option>
              <option value=0>未签约</option>
              <option value=1>已签约</option>
            </select>
          </div>
        </div>
        <div class="search-group">
          <button seed=101310003 class="btn btn-default btn-search" type="button" onclick="refreshDatas()">查询</button>
        </div>
      </form>
      <div id="table" style="overflow-x: auto;"></div>
    </div>
  </div>
</body>
<script type="text/javascript">
	$(function() {
		refreshDatas();
		$('#formLessSearch').keydown(function(e) {
			var code = e.keyCode | e.charCode;
			if (code == 13) {
				refreshDatas();
			}
		});
	});

	var currentPage;//当前页
	var searchParam;//查询表单数据
	//加载列表数据
	function refreshDatas(isCurrentPage) {

		searchParam = $("#formLessSearch").serialize();
		if (!isCurrentPage) {
			currentPage = 1;
		}
		searchParam += "&pageNumber=" + currentPage;
		var url = "${contextPath}/risk-control/consigner?format=table&"
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
		var param = $("#formLessSearch").serialize();
		location.href = "${contextPath}/risk-control/consigner/excel?" + param;
	}
</script>