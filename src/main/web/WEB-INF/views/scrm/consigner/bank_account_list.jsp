<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>虚拟子账户管理</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body pageId=112113>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				虚拟子账户管理	
					<a href="javascript:addbankaccount()" 
						class="btn btn-creat" seed=112113011><i class="fa fa-plus"></i>新增</a>
			</div>
			<form id="formLessSearch">
				<div class="search-area">
					<div class="search-group">
						<label>银行</label> <select seed=112113001
							class="form-control" name="bankName"
							type="text" maxlength="30">
							<option value="" >请选择</option>
							</select>
					</div>
					<div class="search-group">
						<label>委托客户</label> <input seed=112113002
							 name="consignerName"
							type="text" maxlength="30" list='consigner1' >
							<datalist id='consigner1'>
								
							</datalist>
							
					</div>
					
					<div class="search-group">
						<label>户名/账号：</label> 
						<input type='text' seed=112113003 name='accountName' list='ACCOUNT'  maxlength="30">
						<datalist id='ACCOUNT'>
								
							</datalist>
					</div>
					
				</div>
				<div class="search-group">
						<button class="btn btn-default btn-search" type="button" seed=112113004
							onclick="refreshDatas()">查询</button>
						<a class="more-criteria" href="javascript:moreConditions()" seed=112113005>更多条件∨</a>
					</div>
			</form>
			<form id="formMoreSearch">
				<div class="search-area">
					<div class="search-group">
						<label>银行</label> <select
							class="form-control" name="bankName" seed=112113001
							type="text" maxlength="30">
							<option value="" >请选择</option>
							</select>
					</div>
					<div class="search-group">
						<label>委托客户</label> <input seed=112113002
							 name="consignerName"
							type="text" maxlength="30" list='consigner1' >
							<datalist id='consigner1'>
								
							</datalist>
							
					</div>
					
					<div class="search-group">
						<label>户名/账号：</label> 
						<input type='text' seed=112113003 name='accountName' list='ACCOUNT' class="form-control" maxlength="30">
						<datalist id='ACCOUNT'>
								
							</datalist>
					</div>
					<div class="search-group">
						<label>业务主体：</label> 
						<select seed=112113006 class="form-control" name='businessMainBody'>
							<option selected value='0'>请选择</option>
							
						</select>
					</div>
					<div class="search-group">
						<label>当前客服：</label> 
						<select seed=112113007 class="form-control" name='customer'>
							<option selected value="">请选择</option>
							
						</select>
					</div>
					<div class="search-group">
						<label>申请人：</label> 
						<select seed=112113008 class="form-control" name='createdByName'>
							<option selected value="">请选择</option>
							
						</select>
					</div>
					<div class="search-group">
						<label>申请日期:</label>
						<div class="input-group">
							<input seed=112113009  type="text" style="width: 100px;"
								id="applyDate" name="shenQingDate"  />
						</div>
						
					</div>
						<div class="search-group">
							<button seed=112113004 class="btn btn-default btn-search" type="button"
								onclick="refreshDatas()">查询</button>
							<a id="linkLess" class="close-criteria"
								href="javascript:lessConditions()" seed=112113010>收起条件∧</a>
						</div>
					</div></form>
			<div id="table"></div>
		</div>
	</div>
</body>

<script type="text/javascript">
	
	$("[name='businessMainBody']").append("${businessBody}");
	$("[name='createdByName']").append("${createdBy}");
	$("[name='bankName']").append("${bank}");
	$("[name='customer']").append("${customer}")
	$("#consigner1").append("${consigner}");
	$("#ACCOUNT").append("${accountName}");
	var date = new Date();
	var applyDate = dateFormat(date.getFullYear(), date.getMonth() + 1, date
			.getDay());
	moment.locale('zh-cn');
	$(function() {
		setDate();
	});

	function setDate() {
		var isSelDefault = true;
		
		$("#applyDate").daterangepicker({
			singleDatePicker : true,
			showDropdowns : true,
			timePicker : false,
			startDate : date,
			endDate : date,
			format : 'YYYY-MM-DD',
			locale : {
				applyLabel : '选择',
				cancelLabel : '清除'
			}
		}, function(bDate, eDate) {
			isSelDefault = false;
			$("#applyDate").val(bDate.format('YYYY-MM-DD'));
		}).on("cancel.daterangepicker", function(ev) {
			$("#applyDate").val("");
			$("#applyDate").blur();

		}).on("apply.daterangepicker", function(ev) {
			if (isSelDefault) {
				$("#applyDate").val(applyDate);
				isSelDefault = true;
			}
			$("#applyDate").blur();

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
		tmp="";
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
		tmp="More";
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
		var url = "${contextPath}/bank-account?format=table&" + searchParam;
		$('#table').loadWithMask(url, null, function(html) {
			var table = $('#table');
			table.find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				refreshDatas(true);
			});
		});
	}

	function addbankaccount() {
		var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
		addTab('新增虚拟子账户',contextPath + '/bank-account/new', function(){refreshDatas()});
		//location.href = "${contextPath}/commission-withdraw-applications/new";
	}

	function searchByStatus(status) {
		currentPage = 1;
		var url = "${contextPath}/commission-withdraw-applications?format=table&status="
				+ status + "&byStatus=true" + "&pageNumber="+currentPage;
		$('#table').loadWithMask(url, null, function(html) {
			var table = $('#table');
			table.find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				searchByStatus(status);
			});
		});
	}
</script>