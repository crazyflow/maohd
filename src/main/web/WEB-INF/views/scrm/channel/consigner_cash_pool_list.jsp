<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>客户收支管理</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body pageId=112011>
  <div class="body">
    <div class="page-body">
      <div class="page-title">客户收支管理</div>
      <form id="formLessSearch">
        <div class="search-area">
          <div class="search-group">
            <label>公司名称：</label>
            <input seed=112011001 class="form-control" name="companyName" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>渠道商：</label>
            <select seed=112011002 name="consultantId" class="form-control">
              <option value="">全部</option>
              <option value="">apple</option>
              <option value="">xiaomi</option>
            </select>
          </div>
          <div class="search-group">
            <label>业务主体：</label>
            <select seed=112011003 name="companyType" class="form-control">
              <option value=1>江苏跨境电子商务服务有限公司</option>
              <option value=2>南京贸互通电子商务有限公司</option>
            </select>
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" seed=112011004 type="button" onclick="refreshDatas()">查询</button>
        </div>
      </form>
      <div id="table"></div>
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
		var url = "${contextPath}/channels/consigner-cash-pools?format=table&"
				+ searchParam;
		$('#table').loadWithMask(url, null, function(html) {
			var table = $('#table');
			table.find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				refreshDatas(true)
			});
		});
	}
</script>