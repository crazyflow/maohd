<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>物流账单</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body pageId=101208>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        物流账单
        <a href="javascript:exportExcel()" seed=101208011 class="btn btn-creat">导出Excel</a>
      </div>
      <form id="formLessSearch">
        <div class="search-area">
          <div class="search-group">
            <label>订单号：</label>
            <input seed=101208001 class="form-control" name="orderCode" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>业务日期：</label>
            <input seed=101208002 readonly="readonly" type="text" class="active" id="txt" value="" name="BeginDate_EndDate">
            <input type="hidden" id="hidBegin" name="beginDate" value="">
            <input type="hidden" id="hidEnd" name="endDate" value="">
          </div>
          <div class="search-group">
            <label>物流方式：</label>
            <select seed=101208003 name="transportModeId" class="form-control">
              <option value=-1 selected="selected">全部</option>
              <option value=0>空运</option>
              <option value=1>海运</option>
              <option value=2>快递</option>
              <option value=3>中港快运</option>
            </select>
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" seed=101208004 type="button" onclick="refreshDatas()">查询</button>
          <a class="more-criteria" seed=101208005 href="javascript:moreConditions()">更多条件∨</a>
        </div>
      </form>
      <form id="formMoreSearch">
        <div class="search-area">
          <div class="search-group">
            <label>订单号：</label>
            <input seed=101208001 class="form-control" name="orderCode" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>业务日期：</label>
            <input seed=101208002 readonly="readonly" type="text" class="active" id="txttmp" value="" name="BeginDate_EndDate">
            <input type="hidden" id="hidBegintmp" name="beginDate" value="">
            <input type="hidden" id="hidEndtmp" name="endDate" value="">
          </div>
          <div class="search-group">
            <label>物流方式：</label>
            <select seed=101208003 id="transportModeId" name="transportModeId" onchange="choose()" class="form-control">
              <option value=-1 selected="selected">全部</option>
              <option value=0>空运</option>
              <option value=1>海运</option>
              <option value=2>快递</option>
              <option value=3>中港陆运</option>
            </select>
          </div>
          <div class="search-group">
            <label>业务操所：</label>
            <select seed=101208006 id="transportChildModeId" name="transportChildModeId" class="form-control">
              <option value=0>全部</option>
              <option value=1>订舱(空运)</option>
              <option value=2>拖车+订舱(空运)</option>
              <option value=3>拖车(海运)</option>
              <option value=4>订舱(海运)</option>
              <option value=5>拖车+订舱(海运)</option>
            </select>
          </div>
          <div class="search-group">
            <label>费用类型：</label>
            <select seed=101208007 name="logisticsCostTypeNameId" class="form-control">
              <option value=0>全部</option>
              <option value=2804>报关费</option>
              <option value=2805>拖车费</option>
              <option value=2813>海运费</option>
              <option value=2814>空运费</option>
              <option value=2815>快递费</option>
              <option value=2816>港车费</option>
              <option value=99999>包干费</option>
            </select>
          </div>
          <div class="search-group">
            <label>物流供应商：</label>
            <input seed=101208008 class="form-control" name="logisticsBusName" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>委托客户：</label>
            <input seed=101208009 class="form-control" name="companyName" type="text" maxlength="30">
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" seed=101208004 type="button" onclick="refreshDatas()">查询</button>
          <a id="linkLess" class="close-criteria" seed=101208010 href="javascript:lessConditions()">收起条件∧</a>
        </div>
      </form>
      <div id="table"></div>
    </div>
  </div>
</body>
<script type="text/javascript">
	function choose() {
		$("#transportChildModeId option").remove();
		var transportModeId = $("#transportModeId").val();
		if (transportModeId == 0) {
			$("#transportChildModeId").append("<option value=0>全部</option>");
			$("#transportChildModeId").append("<option value=1>订舱</option>");
			$("#transportChildModeId").append("<option value=2>拖车+订舱</option>");
		} else if (transportModeId == 1) {
			$("#transportChildModeId").append("<option value=0>全部</option>");
			$("#transportChildModeId").append("<option value=3>拖车</option>");
			$("#transportChildModeId").append("<option value=4>订舱</option>");
			$("#transportChildModeId").append("<option value=5>拖车+订舱</option>");
		} else if (transportModeId == 2) {
			$("#transportChildModeId").append("<option value=0>全部</option>");
		}else if (transportModeId == 3) {
			$("#transportChildModeId").append("<option value=0>中港陆运</option>");
		}else {
			$("#transportChildModeId").append("<option value=0>全部</option>");
			$("#transportChildModeId")
					.append("<option value=1>订舱(空运)</option>");
			$("#transportChildModeId").append(
					"<option value=2>拖车+订舱(空运)</option>");
			$("#transportChildModeId")
					.append("<option value=3>拖车(海运)</option>");
			$("#transportChildModeId")
					.append("<option value=4>订舱(海运)</option>");
			$("#transportChildModeId").append(
					"<option value=5>拖车+订舱(海运)</option>");
		}
	}
	var tmp = "";
	var date = new Date();
	var nowDate = dateFormat(date.getFullYear(), date.getMonth() + 1, date
			.getDay());
	var startDate = nowDate;
	var endDate = nowDate;
	moment.locale('zh-cn');
	$(function() {
		drpInit(startDate, endDate);
	});
	function drpInit(beginDate, endDate) {
		var isSelDefault = true;
		$("#txt" + tmp).daterangepicker({
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
			$("#hidBegin" + tmp).val(bDate.format('YYYY-MM-DD 00:00:00'));
			$("#hidEnd" + tmp).val(eDate.format('YYYY-MM-DD 23:59:59'));
		}).on("cancel.daterangepicker", function(ev) {
			$("#txt" + tmp).val("");
			$("#hidBegin" + tmp).val("");
			$("#hidEnd" + tmp).val("");
			$("#txt" + tmp).blur();
		}).on("apply.daterangepicker", function(ev) {
			if (isSelDefault) {
				$("#hidBegin" + tmp).val(nowDate);
				$("#hidEnd" + tmp).val(nowDate);
				isSelDefault = true;
			}
			$("#txt" + tmp).blur();
		});
	}

	$(function() {
		refreshDatas();
		lessConditions();
		$('#formLessSearch, #formMoreSearch').keydown(function(e) {
			var code = e.keyCode | e.charCode;
			if (code == 13) {
				refreshDatas();
			}
		});
	});

	var activeFormId = "formLessSearch";//当前有效的form表单id
	//较少的条件
	function lessConditions() {
		tmp = "";
		if (activeFormId != 'formLessSearch') {
            $("#formMoreSearch").animate({ height: "50px" }, function () {
                $(this).css("height", "0");
                $("#formLessSearch").show();
            });
        }
		activeFormId = "formLessSearch";
		passValue($("#formMoreSearch"), $("#formLessSearch"));
	}

	//较多的条件
	function moreConditions() {
		tmp = "tmp";
		drpInit(startDate, endDate);
		var formMoreSearchH = $("#formMoreSearch .search-area").height();
        $("#formLessSearch").hide();
        $("#formMoreSearch").show().css("height", "50px").animate({ height: formMoreSearchH });
		activeFormId = "formMoreSearch";
		passValue($("#formLessSearch"), $("#formMoreSearch"));
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
		var url = "${contextPath}/operation/logistics?format=table&"
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
		location.href = "${contextPath}/operation/logistics/excel?" + param;
	}
</script>