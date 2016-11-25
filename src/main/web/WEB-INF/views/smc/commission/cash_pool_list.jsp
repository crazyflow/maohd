<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>佣金台帐</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=122216>
  <div class="body">
    <div class="page-body">
      <div class="page-title">佣金台帐</div>
      <form id="formSearch">
        <div class="search-area">
          <div class="search-group">
            <label>渠道商：</label>
            <input seed=122216001 class="form-control" name="channelName" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>渠道专员：</label>
            <input seed=122216002 class="form-control" name="channelServiceName" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>业务主体：</label>
            <select seed=122216003 name="businessMainBody">
              <option value=1 selected="selected">江苏跨境电子商务服务有限公司</option>
              <option value=2>南京贸互通电子商务服务有限公司</option>
              <option value=3>南京贸互达科技有限公司</option>
            </select>
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" seed=122216004 type="button" onclick="refreshDatas()">查询</button>
        </div>
    </div>
    </form>
    <div id="table"></div>
  </div>
  </div>
</body>
<script type="text/javascript">
	$(function() {
		refreshDatas();
		$('#formSearch').keydown(function(e) {
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

		searchParam = $("#formSearch").serialize();
		if (!isCurrentPage) {
			currentPage = 1;
		}
		searchParam += "&pageNumber=" + currentPage;
		var url = "${contextPath}/cash-pools?format=table&" + searchParam;
		$('#table').loadWithMask(url, null, function(html) {
			var table = $('#table');
			table.find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				refreshDatas(true)
			});
		});
	}
</script>