<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>开票人报表</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        开票人报表
        <a href="javascript:exportExcel()" class="btn btn-creat">导出Excel</a>
      </div>
      <form id="formLessSearch">
        <div class="search-area">
          <div class="search-group">
            <label>开票人：</label>
            <input class="form-control" name="name" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>状态：</label>
            <select name="orderStatus" class="form-control">
              <option value=0>全部</option>
              <option value=20>审核中</option>
              <option value=30>审核通过</option>
              <option value=10>审核不通过</option>
              <option value=40>已取消</option>
            </select>
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" type="button" onclick="refreshDatas()">查询</button>
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
		var url = "${contextPath}/risk-control/drawer?format=table&"
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
		location.href = "${contextPath}/risk-control/drawer/excel?" + param;
	}
</script>