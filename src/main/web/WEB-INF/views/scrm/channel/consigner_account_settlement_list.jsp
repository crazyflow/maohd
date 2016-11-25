<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>客户资金台账</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body pageId=112012>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        客户资金台账
        <a href="javascript:exportExcel()" seed=112012010 class="btn btn-creat">导出EXCEL</a>
      </div>
      <div class="form-title">
        ${consignerCashPool.companyName}
        <span style="font-size: 13px; font-weight: normal;">
          合计(CNY)：
          <fmt:formatNumber value="${consignerCashPool.cashBalance + consignerCashPool.depositBalance + consignerCashPool.creditBalance + consignerCashPool.cashFreezeAmount + consignerCashPool.creditFreezeAmount + consignerCashPool.depositFreezeAmount}" pattern="#,##0.00" />
        </span>
      </div>
      <form id="formSearch">
        <div class="search-area">
          <div class="search-group">
            <label>发生日期：</label>
            <input seed=112012001 readonly="readonly" type="text" class="form-control" id="txt" value="" name="BeginDate_EndDate">
            <input type="hidden" id="hidBegin" name="BeginDate" value="">
            <input type="hidden" id="hidEnd" name="EndDate" value="">
          </div>
          <div class="search-group">
            <label>收支类型：</label>
            <select seed=112012003 name="accountChangeType" class="form-control">
              <option value=0 selected="selected">全部</option>
              <option value=1600>收入</option>
              <option value=1601>支出</option>
              <option value=1602>冻结</option>
              <option value=1603>取消冻结</option>
            </select>
          </div>
          <div class="search-group">
            <label>业务单据号：</label>
            <input seed=112012007 class="form-control" name="billNo" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>订单号：</label>
            <input seed=112012008 class="form-control" name="orderCode" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>费用科目：</label>
            <select seed=112012004 name="capitalType2" class="form-control">
              <option value="" selected="selected">全部</option>
              <option value="提佣金">提佣金</option>
              <option value="佣金">佣金</option>
            </select>
          </div>
          <div class="search-group">
            <label>本币金额：</label>
            <input seed=112012005 class="form-control" name="minAmount" type="text">
            <span>~</span>
            <input seed=112012006 class="form-control" name="maxAmount" type="text">
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" seed=112012009 type="button" onclick="refreshDatas()">查询</button>
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
		searchParam += "&companyId=${consignerCashPool.companyId}&pageNumber="
				+ currentPage;
		var url = "${contextPath}/channels/consigner-account-settlements?format=table&"
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
		param += "&companyName=${consignerCashPool.companyName}&companyId=${consignerCashPool.companyId}";
		location.href = "${contextPath}/channels/consigner-account-settlements/excel?"
				+ param;
	}
</script>