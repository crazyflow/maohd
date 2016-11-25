<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>佣金台帐详情</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        佣金台帐详情
          <a href="javascript:exportExcel()" class="btn btn-creat">导出EXCEL</a>
      </div>
      <div class="form-title">
        ${cashPool.channelName}
        <span style="font-size: 13px; font-weight: normal;">合计(CNY)：${cashPool.totalAmount} 自由资金池(CNY)：${cashPool.freeAmount} </span>
      </div>
      <form id="formSearch">
        <div class="search-area">
          <div class="search-group">
            <label>发生日期：</label>
            <input readonly="readonly" type="text" id="txt" value="" name="BeginDate_EndDate" class="form-control">
            <input type="hidden" id="hidBegin" name="BeginDate" value="">
            <input type="hidden" id="hidEnd" name="EndDate" value="">
          </div>
          <div class="search-group">
            <label>收支类型：</label>
            <select name="raeType" class="form-control">
              <option value=0 selected="selected">全部</option>
              <option value=1>收入</option>
              <option value=2>支出</option>
              <option value=3>冻结</option>
              <option value=4>取消冻结</option>
            </select>
          </div>
          <div class="search-group">
            <label>费用科目：</label>
            <select name="capacity1Name" class="form-control">
              <option value="" selected="selected">全部</option>
              <option value="提佣金">提佣金</option>
              <option value="佣金">佣金</option>
            </select>
          </div>
          <div class="search-group">
            <label>订单号：</label>
            <input class="form-control" name="orderCode" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>业务单据号：</label>
            <input class="form-control" name="documentCode" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <button class="btn btn-default btn-search" type="button" onclick="refreshDatas()">查询</button>
          </div>
        </div>
      </form>
      <div id="table"></div>
    </div>
  </div>
</body>
<script type="text/javascript">
	var date = new Date();
	var nowDate = dateFormat(date.getFullYear(), date.getMonth() + 1, date
			.getDay());
	moment.locale('zh-cn');
	$(function() {
		drpInit(nowDate, nowDate);
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
			$("#hidBegin").val(bDate.format('YYYY-MM-DD'));
			$("#hidEnd").val(eDate.format('YYYY-MM-DD'));
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
		searchParam += "&channelId=${cashPool.channelId}&pageNumber="
				+ currentPage;
		var url = "${contextPath}/channels/commision-account-statements?format=table&"
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
		var param = $("#formSearch").serialize();
		param += "&channelId=${cashPool.channelId}&channelName=${cashPool.channelName}&totalAmount=${cashPool.totalAmount}&freeAmount=${cashPool.freeAmount}";
		location.href = "${contextPath}/channels/commision-account-statements/excel?"
				+ param;
	}
</script>