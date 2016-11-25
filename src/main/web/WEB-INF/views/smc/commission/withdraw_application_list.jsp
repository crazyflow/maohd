<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>佣金提取申请管理</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=122222>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        佣金提取申请管理
        <div class="page-state" id="statusInfo"></div>
        <a seed=122222015 href="javascript:addCommissionWithdraw()" class="btn btn-creat">新增佣金提取申请</a>
      </div>
      <form id="formLessSearch">
        <div class="search-area">
          <div class="search-group">
            <label>提取申请单号：</label>
            <input seed=122222001 class="form-control" name="applicationCode" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>渠道商：</label>
            <input seed=122222002 class="form-control" name="channelName" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>状态：</label>
            <select seed=122222003 name="status" class="form-control">
              <option value=0 selected="selected">所有</option>
              <option value=2>待审核</option>
              <option value=3>审核通过</option>
              <option value=4>待修改</option>
              <option value=1>草稿</option>
            </select>
          </div>
        </div>
        <div class="search-group">
          <button class="btn btn-default btn-search" type="button" seed=122222004 onclick="refreshDatas()">查询</button>
          <a class="more-criteria" seed=122222005 href="javascript:moreConditions()">更多条件∨</a>
        </div>
      </form>
      <form id="formMoreSearch">
        <div class="row search-area">
          <div class="search-group">
            <label>提取申请单号：</label>
            <input seed=122222001 class="form-control" name="applicationCode" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>渠道商：</label>
            <input seed=122222002 class="form-control" name="channelName" type="text" maxlength="30">
          </div>
          <div class="search-group">
            <label>状态：</label>
            <select seed=122222003 name="status" class="form-control">
              <option value=0 selected="selected">所有</option>
              <option value=2>待审核</option>
              <option value=3>审核通过</option>
              <option value=4>待修改</option>
              <option value=1>草稿</option>
            </select>
          </div>
          <div class="search-group">
            <label>申请时间：</label>
            <input seed=122222006 readonly="readonly" type="text" class="active" id="txt" value="" name="BeginDate_EndDate">
            <input type="hidden" id="hidBegin" name="beginDate" value="">
            <input type="hidden" id="hidEnd" name="endDate" value="">
          </div>
          <div class="search-group">
            <label>币种：</label>
            <input seed=122222007 class="form-control" name="currencyCode" type="text" maxlength="30" >
          </div>
          <div class="search-group">
            <label>申请人：</label>
            <input seed=122222008 class="form-control" name="createdByName" type="text" maxlength="30">
          </div>
        </div>
        <div class="search-group">
          <button seed=122222004 class="btn btn-default btn-search" type="button" onclick="refreshDatas()">查询</button>
          <a id="linkLess" seed=122222009 class="close-criteria" href="javascript:lessConditions()">收起条件∧</a>
        </div>
      </form>
      <div id="table"></div>
    </div>
  </div>
</body>
<script type="text/javascript">
	var date = new Date();
	var startDate = dateFormat(date.getFullYear(), date.getMonth(), date
			.getDay());
	var endDate = dateFormat(date.getFullYear(), date.getMonth() + 1, date
			.getDay());
	moment.locale('zh-cn');
	$(function() {
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
			$("#hidBegin").val(bDate.format('YYYY-MM-DD'));
			$("#hidEnd").val(eDate.format('YYYY-MM-DD'));
		}).on("cancel.daterangepicker", function(ev) {
			$("#txt").val("");
			$("#hidBegin").val("");
			$("#hidEnd").val("");
			$("#txt").blur();
		}).on("apply.daterangepicker", function(ev) {
			if (isSelDefault) {
				$("#hidBegin").val(startDate);
				$("#hidEnd").val(endDate);
				isSelDefault = true;
			}
			$("#txt").blur();
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
		var url = "${contextPath}/commission-withdraw-applications?format=table&"
				+ searchParam;
		$('#table').loadWithMask(url, null, function(html) {
			addStatusInfo();
			var table = $('#table');
			table.find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				refreshDatas(true);
			});
		});
	}
	
	function addStatusInfo(){		
		var html = "";
		if(s1 > 0){
			html += "<a style='color: black; cursor: pointer' onclick='searchByStatus(4)' href='javascript:void(0)'>待我修改 (<span style='color: red'>"+s1+"</span>)</a>";
		}else{
			html += "<a style='color: black; cursor: pointer'>待我修改 (<span style='color: gray'>0</span>)</a>";
		}
		if(s0 > 0){
			html += "<a style='color: black; cursor: pointer' onclick='searchByStatus(2)' href='javascript:void(0)'>待我审核(<span style='color: red'>"+s0+"</span>)</a>";
		}else{
			html += "<a style='color: black; cursor: pointer'>待我审核 (<span style='color: gray'>0</span>)</a>";
		}
		$("#statusInfo").html(html);
	}

	function addCommissionWithdraw() {
		var contextPath = window.location.protocol + '//'
				+ window.location.hostname + ':' + window.location.port;
		addTab('佣金提取新增', contextPath + '/commission-withdraw-applications/new', function(){refreshDatas();} );
		//location.href = "${contextPath}/commission-withdraw-applications/new";
	}

	function searchByStatus(status) {
		currentPage = 1;
		var url = "${contextPath}/commission-withdraw-applications?format=table&status="
				+ status + "&byStatus=true" + "&pageNumber=" + currentPage;
		$('#table').loadWithMask(url, null, function(html) {
			var table = $('#table');
			table.find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				searchByStatus(status);
			});
		});
	}
</script>