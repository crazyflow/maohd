<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>新增佣金提取申请</title>

<style type="text/css">
	#beneficiaryId{
		margin:0;
		padding:0;
		position:absolute;
		left:5px;
	}
	#beneficiaryId li {
		margin-top:-1px;
		height:26px;
		line-height:26px;
		padding:0 10px;
		border:1px solid #ccc;
		background:#FFF;
		width:245px;
	}
	#beneficiaryId li:hover {
		background:#e0e0e0;
		cursor: pointer;
	}
</style> 
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=122223>
  <div class="body">
    <div class="page-body">
      <div class="page-title">
        新增佣金提取申请
        <a href="javascript:showDialog()"  seed=122223011 class="btn btn-creat">选择佣金结算申请</a>
      </div>
      <div class="modal-body">
        <h2 class="text-center">佣金提取申请单</h2>
        <form id="newWithDrawForm" onsubmit="formchongfu()" action="${contextPath}/commission-withdraw-applications" method="post">
          <input type="hidden" id="totalAmount" name="totalAmount" value="0" />
          <input type="hidden" id="totalForeignAmount" name="totalForeignAmount" value="0" />
          <input type="hidden" id="status" name="status" />
          <div class="row">
            <div class="creatTime">
              <label>申请日期:</label>
              <div class="input-group">
                <input seed=122223001 readonly="readonly" class="active" type="text" style="width: 100px;" id="applyDate" name="applicationDate" data-required="" />
              </div>
            </div>
          </div>
          <table class="table table-bordered">
            <tr>
              <th>业务主体</th>
              <td style="text-align: left">
                <span id="ywzt"></span>
                <input type="hidden" id="selectType" name="businessMainBody" />
              </td>
              <th>渠道商名称</th>
              <td colspan="3" style="text-align: left">
                <span id="qds"></span>
                <input type="hidden" id="channelName" name="channelName" value="" />
                <input type="hidden" id="channelId" name="channelId" value="" />
              </td>
            </tr>
            <tr>
              <th style="line-height: 30px">收款人*</th>
              <td style="text-align: left; position:relative;">
                <input seed=122223002 type="text" style="width: 245px;" id="beneficiaryName" autocomplete="off" name="beneficiaryName" data-required=""/>
                <ul id='beneficiaryId'></ul>                
              </td>
              <th style="line-height: 30px">收款银行*</th>
              <td style="text-align: left">
                <input seed=122223003 type="text" class="form-control" style="width: 245px" autocomplete="off" id="beneficiaryBank" name="beneficiaryBank" value="" data-required="" />
              </td>
            </tr>
            <tr>
              <th style="line-height: 30px">银行账号*</th>
              <td style="text-align: left">
                <input seed=122223004 type="text" class="form-control" style="width: 245px" autocomplete="off" id="beneficiaryAccountNo" name="beneficiaryAccountNo" value="" data-required="" data-number0="" />
              </td>
              <th style="line-height: 30px">是否提取为美元</th>
              <td style="text-align: left">
                <input seed=122223005 type="checkbox" onclick="getSellRate(this)" id="checkRate"/>
                <input type="hidden" class="currencyCode" name="currencyCode" value="CNY" />
                <input type="hidden" id="currencyId" name="currencyId" value="151" /><span>是</span>
              </td>
            </tr>
            <tr>
              <th style="line-height: 30px">先汇卖出汇率</th>
              <td style="text-align: left">
                <input type="text" id="sellingRate" class="form-control" style="width: 245px" name="sellingRate" value="1.0000" readonly="readonly" />
              </td>
              <th style="line-height: 30px">备注</th>
              <td style="text-align: left">
                <input seed=122223006 type="text" class="form-control" style="width: 245px" name="remark" value="" />
              </td>
            </tr>
          </table>
          <table class="table table-bordered table-hover">
            <thead class="bordered-themeprimary">
              <tr>
                <th>佣金结算申请单号</th>
                <th>结算佣金金额(人民币)</th>
                <th>已提取佣金金额(人民币)</th>
                <th>提取佣金金额(人民币)</th>
                <th>提取佣金金额(美元)</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody id="settlementBody"></tbody>
          </table>
          <div class="btn-submit">
            <button type="button" seed=122223009 class="btn btn-default btn-font4" id="saveDraft">保存草稿</button>
            <button type="submit" seed=122223010 class="btn btn-primary btn-font4" id="submitApply">提交申请</button>
          </div>
        </form>
      </div>
      <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width: 1000px;">
          <div class="modal-content">
            <button class="close" aria-hidden="true" type="button" data-dismiss="modal">×</button>
            <div class="modal-header mhd-layer-header">
              <div class="DTTTN btn-group mhd-layer-header-btns"></div>
              <h4 class="modal-title mhd-layer-title">选择佣金结算申请</h4>
            </div>
            <div class="modal-body">
              <div class="page-body mhd-layer-body">
                <form id="settlementForm">
                  <div class="search-area">
                    <div class="search-group">
                      <label>结算申请单号:</label>
                      <input seed=122223012 class="form-control" name="applicationCode" type="text" maxlength="30">
                    </div>
                    <div class="search-group">
                      <label>渠道商:</label>
                      <input seed=122223013 class="form-control" id="searchChannelName" name="channelName" type="text" maxlength="30">
                    </div>
                    <div class="search-group">
                      <label>订单号:</label>
                      <input seed=122223014 class="form-control" id="orderCodeW" name="orderCode" type="text" maxlength="30">
                    </div>
                    <div class="search-group">
                      <label>业务主体:</label>
                      <select seed=122223015 name="businessMainBody" id="businessMainBody" onclick="chooseBody()" class="form-control">
                        <option value="1" selected="selected">江苏跨境电子商务服务有限公司</option>
                        <option value="2">南京贸互通电子商务服务有限公司</option>
                        <option value="3">南京贸互达科技有限公司</option>
                      </select>
                    </div>
                    <div class="search-group">
                      <button seed=122223016 class="btn btn-default btn-search" type="button" onclick="refreshDatas()">查询</button>
                    </div>
                  </div>
                </form>
                <div id="settlementTable"></div>
              </div>
            </div>
            <div class="modal-footer mhd-layer-footer">
              <button class="btn btn-primary btn-font4" type="button" seed=122223021 onclick="saveSettlement()">确认选择</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	
	var date = new Date();
	var applyDate = dateFormat(date.getFullYear(), date.getMonth() + 1, date
			.getDay());
	moment.locale('zh-cn');
	$(function() {
		setDate(applyDate);
		$('#beneficiaryName').bind('input focus propertychange', function() { 
			$("#beneficiaryAccountNo").val("");
			$("#beneficiaryBank").val("");
			getHistoryAccounts($('#beneficiaryName').val());  
		});
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
	
	function getHistoryAccounts(name) {
		$.ajax({
				url : "${contextPath}/commission-withdraw-applications/history?beneficiaryName="+name+"&channelId="+$("#channelId").val(),
				type : "get",
				success : function(result) {
					if (result != null) {
						$('#beneficiaryId').html(result);
					} 
				}
		});
	}
	

	function setOtherValues(name,bankName,bankNo){
		$('#beneficiaryId').html("");
		$("#beneficiaryName").val(name);
		$("#beneficiaryName").blur();
		$("#beneficiaryAccountNo").val(bankNo);
		$("#beneficiaryAccountNo").blur();
		$("#beneficiaryBank").val(bankName);
		$("#beneficiaryBank").blur();
	}

	function getSellRate(obj) {
		if ($(obj).is(":checked")) {
			$
					.ajax({
						url : "${contextPath}/commission-withdraw-applications/currency-rate",
						type : "get",
						success : function(result) {
							if (result != null && result !="") {
								$('#sellingRate').val((result + 0.01).toFixed(4));
								$(".currencyCode").val("USD");
								$("#currencyId").val(318);
								$(".currencyIdc").val(318);
								setAmount();
							} else {
								$("#checkRate")[0].checked = false;
								$('.body').showErrorMsg('今日美元汇率不存在，请相关人员维护!');
							}
						}
					});
		} else {
			$(".currencyCode").val("CNY");
			$("#currencyId").val(151);
			$(".currencyIdc").val(151);
			$('#sellingRate').val("1.0000");
			setAmount();
		}
	}

	var ids = "";//已选择结算id
	var channelId = "";
	var settlementId = "";
	function showDialog() {
		settlementId = "";
		$("#orderCodeW").val("");
		refreshDatas();
		$("#myModal").modal("show");
	}

	var currentPage;//当前页
	var searchParam;//查询表单数据

	function refreshDatas(isCurrentPage) {
		var searchParam = $("#settlementForm").serialize();
		var cId = $('#channelId').val();
		if (!isCurrentPage) {
			currentPage = 1;
		}
		searchParam += "&pageNumber=" + currentPage + "&channelId=" + cId
				+ "&applicationId=" + settlementId + "&ids=" + ids;
		var url = "${contextPath}/commission-settlement-applications?format=dialog&"
				+ searchParam;
		$('#settlementTable').loadWithMask(url, null, function(html) {
			$('#settlementTable').find('.data-page a').click(function() {
				currentPage = $(this).attr("data-page");
				refreshDatas(true);
			});
		});
	}

	var newArr = new Array();
	//选择的订单个数
	var hits = 0;
	var channelName = "";

	function selectSingle(obj, type) {
		if ($(obj).is(":checked")) {
			newArr[newArr.length] = $(obj).attr("settlement");
			$('#businessMainBody').attr("disabled", "disabled");
			if (hits == 0) {
				$('#searchChannelName').val($(obj).attr("channelName")).attr(
						"readonly", "readonly");
				channelName = $(obj).attr("channelName");
				channelId = $(obj).attr("channelId");
				settlementId = $(obj).attr("applicationId");
				refreshDatas(false);
			}
			hits += 1;
		} else {
			newArr.splice($.inArray($(obj).attr("settlement"), newArr), 1);
			hits -= 1;
			if (hits == 0) {
				settlementId = "";
				$('#businessMainBody').removeAttr("disabled");
				$('#searchChannelName').val("").removeAttr("readonly");
				refreshDatas(false);
			}
		}
	}

	var businessMainBody = 1;
	function chooseBody() {
		businessMainBody = $("#businessMainBody").val();
	}
	function saveSettlement() {
		if (newArr.length > 0) {
			if (businessMainBody == 1) {
				$('#ywzt').text("江苏跨境电子商务服务有限公司");
				$('#selectType').val(1);
			} else if(businessMainBody == 2) {
				$('#ywzt').text("南京贸互通电子商务有限公司");
				$('#selectType').val(2);
			} else if(businessMainBody == 3) {
				$('#ywzt').text("南京贸互达科技有限公司");
				$('#selectType').val(3);
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
			$('#searchChannelName').val("");
			$('#businessMainBody').val(1);
			$('#businessMainBody').removeAttr("disabled");
		}
		var html = "";
		for (var i = arr.length - 1; i >= 0; i--) {
			var a = arr[i].split("#");
			ids += "'" + a[0] + "'" + ",";
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
					+ "</td><td><input seed=122223007  id='withDrawAmount' type='text' class='form-control' amount='amount' name='withDrawdetails["+i+"].amount' onkeyup='checkValue(this)' data-required data-maxvalidatenum='1'></td>"
					+ "<td><input type='text' class='form-control' readonly='readonly' foreignAmount='foreignAmount' name='withDrawdetails["+i+"].foreignAmount' value='0.00'></td>"
					+ "<td><a seed=122223008 onclick=\"new function(){newArr.splice($.inArray('"
					+ arr[i] + "',newArr),1);loadData();}\">移除</a></td>"
					+ "</tr>";
		}
		html += "<tr><th style='text-align: center;'>合计</th>"
				+ "<th colspan='3' style='text-align: right;'><span id='totalAmountTxt' style='font: 18px bolder'></span></th>"
				+ "<th style='text-align: right;'><span id='totalForeignAmountTxt' style='font: 18px bolder'></span></th>"
				+ "<th></th></tr>";
		$("#settlementBody").empty();
		$("#settlementBody").append(html);
	}
	
	$("#newWithDrawForm").initValidate();

	$('body').on({blur: setAmount}, 'input[amount=amount]');
	
	function checkValue(obj){
		$(obj).number2();
	}

	function setAmount() {
		var total = 0;
		var i = 0;
		$('input[amount=amount]').each(
				function() {
					var amount = $(this).val();
					amount = parseFloat(amount == null
							|| $.trim(amount).length == 0 ? 0 : amount);
					if (amount > $(this).parents("tr").find(
							"td:eq(1) input:eq(0)").val()
							- $(this).parents("tr")
									.find("td:eq(2) input:eq(0)").val()) {
						$('.body').showErrorMsg("输入金额大于可提取余额，请重新输入！");
						$(this).val(0);
						return false;
					} else {
						$(this).val(amount.toFixed(2));
					}
					setForeignAmount(this, amount);
					total = total + amount;
				});
		$('#totalAmountTxt').html(MFormat(total, 2) + " CNY");
		$('#totalAmount').val(total.toFixed(2));
		$(this).requiredValidate();
	}

	function setForeignAmount(obj, amount) {
		var rate = $('#sellingRate').val();
		$(obj).parents("tr").find("td:eq(4) input:eq(0)").val(
				rate == 1 ? MFormat(0, 2) : MFormat(parseFloat(amount) / rate, 2));
		totalForeignAmount = 0;
		$('input[foreignAmount=foreignAmount]').each(
				function() {
					var foreignAmount = $(this).val();
					foreignAmount = parseFloat(foreignAmount == null|| $.trim(foreignAmount).length == 0 ? 0: foreignAmount);
					totalForeignAmount = totalForeignAmount + foreignAmount;
				})
		$('#totalForeignAmountTxt').html(MFormat(totalForeignAmount, 2) + " USD");
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

	//保存草稿
	$('#saveDraft').click(function(e) {
		
		if (newArr.length == 0) {
			$('.body').showErrorMsg("请先选择结算申请单！");
			return false;
		}
		if (!$("#newWithDrawForm").validateAll()) {
			return false;
		}
		
		$('#status').val('1');
		$('#newWithDrawForm').submit();
	});

	$('#submitApply').click(function(e) {
		
		if (newArr.length == 0) {
			$('.body').showErrorMsg("请先选择结算申请单！");
			return false;
		}
		if (!$("#newWithDrawForm").validateAll()) {
			return false;
		}
		$('#status').val('2');
	});
	function formchongfu(){
		$(".btn-primary").attr("disabled","disabled");
	}
</script>