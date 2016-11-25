<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>编辑虚拟子账户</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				编辑虚拟子账户
				
			</div>
			<div class="modal-body">
				
				<form id="newWithDrawForm" action="${contextPath}/bank-account?_method=put"
					method="post">
					
					<c:forEach items="${consigner}" var="row">
					<input type="hidden" name='accountId' value="${accountId}">
					<table class="table table-bordered">
						<tr>
							<th>委托客户*</th>
							<td style="text-align: left">
								<select style="width:30%" id="consigner" name="consignerId" data-required="" data-number0="">
									<option value="" selected hidden>请选择已签约的客户</option>
								</select>&nbsp;<a onclick='khxq()'>查看该客户详情</a>
								<input type='hidden' name='consignerName'>
							</td>
							
						</tr>
						<tr>
							<th style="line-height: 30px">业务主体*</th>
							<td style="text-align: left">
								<select style="width:30%" name='businessMainBody' data-required="" data-number0="">
									<option value="" selected hidden>请选择</option>
									<option value=1>江苏跨境电子商务服务有限公司</option>
									<option value=2>南京贸互通电子商务有限公司</option>
								</select>
								
							</td>
							
						</tr>
						
						<tr>
							<th style="line-height: 30px">银行*</th>
							<td style="text-align: left">
								<select style="width:30%" name='bankId' data-required="" data-number0="">
									<option value="" selected hidden>请选择</option>
									<option value=1>江苏银行</option>
									<option value=2>南京银行</option>
								</select>
								<input type='hidden' name='bankName'>
							</td>
							
						</tr>
						<script>
						$("[name='bankId']").children("option").eq(0).text('${row.bankName}');
						$("[name='bankId']").children("option").eq(0).val('${row.bankId}');
						var businessMainBody = '${row.businessMainBody}';
						if(businessMainBody==1){
							$("[name='businessMainBody']").children("option").eq(0).text('江苏跨境电子商务服务有限公司');
							$("[name='businessMainBody']").children("option").eq(0).val(1);
						}
						if(businessMainBody==2){
							$("[name='businessMainBody']").children("option").eq(0).text('南京贸互通电子商务有限公司');
							$("[name='businessMainBody']").children("option").eq(0).val(2);
						}
						$("[name='consignerId']").children("option").eq(0).text('${row.consignerName}');
						$("[name='consignerId']").children("option").eq(0).val('${row.consignerId}');
						</script>
						<tr>
							<th style="line-height: 30px">备注</th>
							<td style="text-align: left"><input type="text"
								 class="form-control text-box single-line"
								style="width: 30%"  name='remark' value='${row.remark}'
								 /></td>
							
						</tr>
					</table>
					<div style="float:right"> 制单信息:&nbsp;${row.updatedByName} <span><fmt:formatDate value="${row.updatedDate}" pattern="yyyy-MM-dd hh:mm:ss"/></span></div>
					<br/>
					<div class="btn-submit">
						
						<button type="submit" class="btn btn-primary btn-font4"
							id="submitApply">提交申请</button>
						<input id='status' name='status' type='hidden'>
					</div>
					</c:forEach>
				</form>
			</div>

			
		</div>
	</div>
</body>

<script type="text/javascript">
	var consignerName = $("#consigner").find("option:selected").text();
	$("[name='consignerName']").val(consignerName);
	$("#consigner").change(function(){
		var consignerName = $("#consigner").find("option:selected").text();
		$("[name='consignerName']").val(consignerName);
	})
	var bankName = $("[name='bankId']").find("option:selected").text();
	$("[name='bankName']").val(bankName);
	$("[name='bankId']").change(function(){
		var bankName = $("[name='bankId']").find("option:selected").text();
		$("[name='bankName']").val(bankName);
	})
	function khxq(){
		var value = document.getElementById("consigner").value;
		if(value == "请选择已签约的客户"){
			alert("请先选择客户");
		}else{
			addTab('客户详情','/customer/companyInfo.aspx?id='+value+'')
		}

	}
	
	$("#consigner").append("${consignerId}");
	$("#newWithDrawForm").initValidate();
	var date = new Date();
	var applyDate = dateFormat(date.getFullYear(),date.getMonth()+1,date.getDay());
	moment.locale('zh-cn');
	$(function() {
		setDate(applyDate);
	});

	function setDate(applyDate) {
		var isSelDefault = true;
		$("#applyDate").val(applyDate);
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

	
	
	
	

	var currentPage;//当前页
	var searchParam;//查询表单数据
	
	function refreshDatas(isCurrentPage) {
		var searchParam = $("#settlementForm").serialize();
		var cId = $('#channelId').val();
		if (!isCurrentPage) {
			currentPage = 1;
		}
		searchParam += "&pageNumber=" + currentPage +"&channelId="+cId+"&applicationId="+settlementId+"&ids="+ids;
		var url = "${contextPath}/commission-settlement-applications?format=dialog&"
				+ searchParam;
		$('#settlementTable').loadWithMask(url, null, function(html) {
			$('#settlementTable').find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				refreshDatas(true);
			});
		});
	}

	
	
	var businessMainBody = 1;
	function chooseBody(){
		businessMainBody = $("#businessMainBody").val();
	}
	function saveSettlement() {
		if (newArr.length > 0) {
			if (businessMainBody == 1) {
				$('#ywzt').text("江苏跨境电子商务服务有限公司");
				$('#selectType').val(1);
			} else {
				$('#ywzt').text("南京贸互通电子商务有限公司");
				$('#selectType').val(2);
			}
			$('#qds').text(channelName);
			$('#channelName').val(channelName);
			$('#channelId').val(channelId);
			
			loadData();
			$("#myModal").modal("hide");
		} else {
			$('.body').showErrorMsg("请至少选择一条结算单！");
			return;
		}

	}

	function loadData() {
		var arr = new Array();
		ids = "";
		arr = $.unique(arr.concat(newArr));
		if (arr.length == 0) {
			$('#ywzt').text("");
			$('#selectType').val(0);
			$('#qds').text("");
			$('#channelName').val("");
			$('#channelId').val("");
		}
		var html = "";
		for (var i = arr.length - 1; i >= 0; i--) {
			var a = arr[i].split("#");
			ids += "'"+a[0]+"'"+ ",";
			html += "<tr><td>"
					+ a[1]
					+ "<input type='hidden' name='withDrawdetails["+i+"].settlementApplicationCode' value='"+a[1]+"'/>"
					+ "<input type='hidden' name='withDrawdetails["+i+"].settlementApplicationId' value='"+a[0]+"'/>"
					+ "<input type='hidden' class='currencyCode' name='withDrawdetails["+i+"].currencyCode' value='CNY' /> <input type='hidden' class='currencyIdc' name='currencyId' value='151' /></td><td>"
					+ a[2]
					+ "<input type='hidden' name='withDrawdetails["+i+"].commisionAmount' value='"+a[2]+"'/>"
					+ "</td><td>"
					+ a[3]
					+ "<input type='hidden' name='withDrawdetails["+i+"].withdrawnAmount' value='"+a[3]+"'/>"
					+ "</td><td><input type='text' class='form-control text-box single-line' amount='amount' name='withDrawdetails["+i+"].amount' value='0' data-required='' data-number0=''></td>"
					+ "<td><input type='text' class='form-control text-box single-line' readonly='readonly' foreignAmount='foreignAmount' name='withDrawdetails["+i+"].foreignAmount' value='0'></td>"
					+ "<td><a onclick=\"new function(){newArr.splice($.inArray('"
					+ arr[i] + "',newArr),1);loadData();}\">移除</a></td>" + "</tr>";
		}
		html += "<tr><th style='text-align: center;'>合计</th>"
				+ "<th colspan='3' style='text-align: right;'><span id='totalAmountTxt' style='font: 18px bolder'></span></th>"
				+ "<th style='text-align: right;'><span id='totalForeignAmountTxt' style='font: 18px bolder'></span></th>"
				+ "<th></th></tr>";
		$("#settlementBody").empty();
		$("#settlementBody").append(html);
	}

	$('body').on("blur",'input[amount=amount]',setAmount);
	
	function setAmount(){
		var total = 0;
		var i = 0;
		$('input[amount=amount]').each(
		function() {
			var amount = $(this).val();
			amount = parseFloat(amount == null
					|| $.trim(amount).length == 0 ? 0
					: amount);
			if(amount > $(this).parents("tr").find("td:eq(1) input:eq(0)").val()-$(this).parents("tr").find("td:eq(2) input:eq(0)").val()){
				$('.body').showErrorMsg("输入金额大于可提取余额，请重新输入！");
				$(this).val(0);
				return false;
			}else{
				$(this).val(amount.toFixed(2));
			}
			setForeignAmount(this, amount);
			total = total + amount;
		});
		$('#totalAmountTxt').html(MFormat(total, 2) + " CNY");
		$('#totalAmount').val(total.toFixed(2));
	}

	function setForeignAmount(obj, amount) {
		var rate = $('#sellingRate').val();
		$(obj).parents("tr").find("td:eq(4) input:eq(0)").val(rate==1?0.00:MFormat(parseFloat(amount) / rate,2));
		totalForeignAmount = 0;
		$('input[foreignAmount=foreignAmount]').each(
				function() {
					var foreignAmount = $(this).val();
					foreignAmount = parseFloat(foreignAmount == null
							|| $.trim(foreignAmount).length == 0 ? 0
							: foreignAmount);
					totalForeignAmount = totalForeignAmount + foreignAmount;
				})
		$('#totalForeignAmountTxt').html(
				MFormat(totalForeignAmount, 2) + " USD");
		$('#totalForeignAmount').val(totalForeignAmount.toFixed(2));
	}

	function MFormat(v, n) {
		n = n > 0 && n <= 5 ? n : 2;
		v = parseFloat((v + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";//更改这里n数也可确定要保留的小数位  
		var l = v.split(".")[0].split("").reverse(), r = v.split(".")[1];
		t = "";
		for (i = 0; i < l.length; i++) {
			t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
		}
		return t.split("").reverse().join("") + "." + r.substring(0, 2);
	}

	
	

	$('#submitApply').click(function(e) {
		
		if(!$("#newWithDrawForm").validateAll()){
			return false;
		}
		$('#status').val('2');
	});
	
</script>