<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>新增发票扫描申请</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=112429>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				新增发票扫描申请 <a seed=112429001 href="javascript:showOrderDialog()" class="btn btn-creat">选择订单</a>
			</div>
			<div style="text-align: center;">
				<h2>发票扫描申请</h2>
				<div class="search-group">
					<label style="width: 80px; text-align: right;">申请日期：</label>
					<input class="form-control" id="applicationDate" style="width: 150px;" disabled />
				</div>
			</div>
			<div>
				<table class="table table-bordered table-hover" id="tab">
					<thead class="bordered-themeprimary">
						<tr>
							<th>订单编号</th>
							<th>委托客户</th>
							<th>开票人</th>
							<th>纳税识别证号</th>
							<th>合同金额</th>
							<th>常规发票张数</th>
							<th>红票张数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="orderContractForInvoice">
						<tr>
							<td colspan="9">请选择订单!</td>
						</tr>
					</tbody>
					<tfoot>
					</tfoot>
				</table>
			</div>
			<div style="text-align: center;">
				<a href="javascript:saveScanningApplication()" seed=112429005 class="btn btn-primary btn-font5">提交申请</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	moment.locale ('zh-cn');
	$ (function()
	{
		date = new Date ();
		var year = date.getFullYear ();
		var month = date.getMonth () + 1;
		if (month < 10)
		{
			month = "0" + (month)
		}
		var day = date.getDate ();
		if (day < 10)
		{
			day = "0" + (day)
		}
		var applicationDate = year + "-" + month + "-" + day
		$ ("#applicationDate").val (applicationDate);
	});

	// 订单选择dialog
	var $orderDialog;
	function showOrderDialog()
	{
		if (!$orderDialog)
		{
			$orderDialog = $ ("<div style='overflow: hidden;' id='orderDialog' />");
			$ ("body").append ($orderDialog);
			var url = "${contextPath}/orders/invoice-collection";
			$orderDialog.loadWithMask (url, null, function()
			{
				$ ('#orderModal').modal ({
					keyboard : false
				});
				$ ('#orderModal').on ('show.bs.modal', function()
				{
					// 打开modal初始化订单列表
					$ ("#orderModal #orderCode").val ("");
					$ ("#orderModal #supplier").val ("");
					getOrderDatas ();
				});
				$ ('#orderModal').on ('hide.bs.modal', function()
				{

				});
				$ ('#orderModal').modal ("show");
			});
		}
		$ ("#orderModal").modal ("show");
	}

	// 获取订单数据
	var orderCurrentPage;
	function getOrderDatas(isCurrentPage)
	{
		$formOrder = $ ("#formOrder");
		if (!isCurrentPage)
		{
			orderCurrentPage = 1;
		}
		var searchParam = $formOrder.serialize () + "&exceptOrder="
				+ selectedOrders ();
		var url = "${contextPath}/orders/invoice-collection?format=table&pageNumber="
				+ orderCurrentPage;
		$ ("#orderTable").loadWithMask (url, searchParam, function()
		{
			$ (this).find (".data-page a").click (function()
			{
				orderCurrentPage = $ (this).attr ("data-page");
				getOrderDatas (true);
			});
			initCheckBoxTable (this);
		});
	}

	//订单选择确认
	function OrdersConfirm()
	{
		var orders = "?orderIds=";
		// 获取所有选择的订单号
		$.each ($ ("#orderTable").find ("table tbody td input:checkbox"),
				function(index, item)
				{
					if ($ (item).prop ("checked"))
					{
						orders += $ (item).next ().val () + ",";
					}
				});
		if ("?orderIds=" == orders)
		{
			$ ("body").showErrorMsg ("请选择订单");
			return;
		}
		var url = "${contextPath}/orders/order-contract-invoice" + orders;
		$.ajax ({
			url : url,
			type : "get",
			success : function(result)
			{
				var $tbody = $ ("#orderContractForInvoice");
				// 若是第一行提示的则删除
				if ($tbody.find ("tr:first td:first").attr ("colspan") == 9)
				{
					$tbody.find ("tr:first").remove ();
				}
				$.each (result, function(index, item)
				{
					// 判断supplier
					var size = item.suppliers.length;
					var orderHtml = "";
					var contractAmount = 0;
					$.each (item.suppliers, function(index2, item2)
					{
						contractAmount += parseFloat(item2.contractAmount);
						if (index2 == 0)
						{
							// 无红冲票
							if (item.redInvoice == 0)
							{
								orderHtml += "<tr  ='"+item.redInvoice+"'><td style='text-align: left;' rowspan="+size+"><a data="+item.orderId+" seed=112429002 href=javascript:addTab('委托方详情','/omc/order/orderDetail.aspx?id="+item.orderId+"') >"+item.orderCode+"</a></td>"
									+"<td style='text-align: left;' rowspan="+size+"><a data="+item.companyId+" seed=112429003 href=javascript:addTab('委托方详情','/Customer2nd/companyInfoByCompanyID.aspx?id="+item.companyId+"') >"+item.companyName+"</a></td>"
									+"<td><a seed=112429010 href=javascript:addTab('开票人详情','/supply/detail.aspx?ID="+item2.supplierId+"') >"+item2.supplierName+"</a></td><td>"+item2.taxpayerIdentityNumber+"</td><td>"+MFormat(item2.contractAmount,2)+"</td>"
									+"<td style='text-align: center;' rowspan="+size+"><input class='form-control' style='width:120px;' name='regularInvoiceCount' /></td>"
									+"<td style='text-align: center;' rowspan="+size+"><input type='hidden' style='width:120px;' name='redInvoiceCount' value=0 /></td>"
									+"<td style='text-align: center;' rowspan="+size+"><a href='javascript:void(0)' onclick='removeOrder(this)' seed=112429004>移除</a></td>"
								+"</tr>";
							}
							else
							{
								// 有红票
								orderHtml += "<tr data='"+item.redInvoice+"'><td style='text-align: left;' rowspan="+size+"><a data="+item.orderId+" seed=112429002 href=javascript:addTab('委托方详情','/omc/order/orderDetail.aspx?id="+item.orderId+"') >"+item.orderCode+"</a></td>"
									+"<td style='text-align: left;' rowspan="+size+"><a data="+item.companyId+" seed=112429003 href=javascript:addTab('委托方详情','/Customer2nd/companyInfoByCompanyID.aspx?id="+item.companyId+"') >"+item.companyName+"</a></td>"
									+"<td><a seed=112429010 href=javascript:addTab('开票人详情','/supply/detail.aspx?ID="+item2.supplierId+"') >"+item2.supplierName+"</a></td><td>"+item2.taxpayerIdentityNumber+"</td><td>"+MFormat(item2.contractAmount,2)+"</td>"
									+"<td style='text-align: center;' rowspan="+size+"><input class='form-control' style='width:120px;' name='regularInvoiceCount' /></td>"
									+"<td style='text-align: center;' rowspan="+size+"><input class='form-control' style='width:120px;' name='redInvoiceCount' /></td>"
									+"<td style='text-align: center;' rowspan="+size+"><a href='javascript:void(0)' onclick='removeOrder(this)' seed=112429004>移除</a></td>"
								+"</tr>";
							}
						}
						else
						{
							orderHtml += "<tr><td><a seed=112429010 href=javascript:addTab('开票人详情','/supply/detail.aspx?ID="+item2.supplierId+"') >"+item2.supplierName+"</a></td><td>"
									+ item2.taxpayerIdentityNumber
									+ "</td><td>" + MFormat(item2.contractAmount,2)
									+ "</td></tr>";
						}
					});
					$ ("#orderContractForInvoice").append (orderHtml);
					// 添加合计
					$ ("#orderContractForInvoice").append (
							"<tr><td>合计</td><td style='text-align: right; border-right: #ffffff 1px solid' colspan='4'>" + MFormat(contractAmount,2)
									+ "</td><td colspan='3'></td></tr>");
					$ ('#orderModal').modal ("hide");
				});
			}
		})
	}

	// 已经选择的订单--排除合计的行
	function selectedOrders()
	{
		var str = "";
		var $trs = $ ("#orderContractForInvoice").children ();
		$.each ($trs, function(index, item)
		{
			/* if (undefined != $ (item).children ().eq (0).attr ("rowspan"))
			{
				str += $ (item).children ().eq (0).text () + ",";
			} */
			if ($ (item).find ("td:first a").text ())
			{
				str += $ (item).find ("td:first a").attr ("data") + ",";
			}
		});
		return str;
	};

	// 申请单保存
	function saveScanningApplication()
	{
		var $trs = $ ("#orderContractForInvoice").find ("tr");
		if ($trs.length == 1)
		{
			$ ("body").showErrorMsg ("请选择订单!");
			return;
		}
		var flag = true;
		// 校验
		$.each ($trs, function(index, item)
		{
			var $input = $ (item).find ("input").eq(0);
			if ($ (item).attr ("data") == 0)
			{//无红票
				if ($input.val () == "")
				{
					$input.addClass (".has-error");
					$input.next ().remove ();
					$input.parent ().append (
							"<small class='help-block'>该项必填</small>");
					flag = false;
				}
				else
				{
					$input.removeClass (".has-error");
					$input.next ().remove ();
				}
			}
			else if ($ (item).attr ("data") == 1)
			{//有红票
				var $input1 = $ (item).find ("input").eq (0);
				var $input2 = $ (item).find ("input").eq (1);
				if ($input1.val () == "" && $input2.val () == "")
				{
					$input1.addClass (".has-error");
					$input1.parent ().append (
							"<small class='help-block'>该项必填</small>");
					flag = false;
				}
				else
				{
					$input1.removeClass (".has-error");
					$input1.next ().remove ();
				}
			}
		})
		if(!flag){
			return;
		}
		var list = [];
		$.each ($trs, function(index, item)
		{
			if ($ (item).find ("td:first").attr ("rowspan") == undefined)
			{
				return true;
			}
			var json = {};
			json.orderId = $ (item).find ("td:first").find ("a").attr ("data");
			json.orderCode = $ (item).find ("td:first").find ("a").text ();
			json.companyId = $ (item).find ("td").eq(1).find("a").attr ("data");
			json.companyName = $ (item).find ("td").eq (1).text ();
			json.regularInvoiceCount = $ (item).find ("input").eq (0).val ();
			json.redInvoiceCount = $ (item).find ("input").eq (1).val ();
			json.applicationStatus = 1;
			json.scannedTicketCount = 0;
			json.refundTicketCount = 0;
			json.applicationDate = $ ("#applicationDate").val ();
			list.push (json);
		});

		var url = "${contextPath}/invoice-scanning-applications";
		$.ajax ({
			url : url,
			type : "post",
			dataType : "json",
			data : JSON.stringify (list),
			contentType : "application/json",
			success : function(result)
			{
				if (result.success)
				{
					// 成功，关闭页面
					removeCurrentTab();
				}
				else
				{
					alert ("失败");
				}
			}
		});

	}

	// 删除结算信息--删除时需要删除订单信息后的合计行
	function removeOrder(obj)
	{
		// 获取行合并的参数，几行合并,获取该删除按钮所在的序号
		var $tr = $ (obj).parent ().parent ();
		var rowspan = $tr.children ().eq (0).attr ("rowspan");
		var index = parseInt ($tr.index ());
		// 删除合计行
		$tr.next ().remove ();
		if (rowspan == undefined)
		{
			$tr.remove ();
			return;
		}
		for (var i = index + rowspan; i > index; i--)
		{
			$tr.parent ().children ().eq (i - 1).remove ();
		}

	}

	// 初始化表格checkbox选择事件
	function initCheckBoxTable(obj)
	{
		var $thead = $ (obj).find ("table thead");
		var $tbody = $ (obj).find ("table tbody");
		if ($thead == undefined && $tbody == undefined)
		{
			return;
		}
		// 多选框事件绑定
		$thead.find ("th:first input:checkbox").on ("click", function()
		{
			var flag = $ (this).prop ("checked");
			// 判断当前是否选中
			if (!flag)
			{
				$tbody.find (":checkbox").prop ("checked", false);
			}
			else
			{
				$tbody.find ("td input:checkbox").click ();
				var allFlag = true;
				$.each ($tbody.find (":checkbox"), function(item, index)
				{
					if (!$ (this).prop ("checked"))
					{
						allFlag = false;
						return false;
					}
				});
				if (!allFlag)
				{
					$ (this).prop ("checked", false);
				}
			}
		});
		// 每一个单选框的事件
		$tbody.find ("td input:checkbox").on (
				"click",
				function()
				{
					var perContract = true;
					var decleration = true;
					if ($ (this).next ().next ().val () == 0)
					{
						// 采购合同没有生成
						perContract = false;
						$ ("body").showErrorMsg (
								"订单" + $ (this).parent ().next ().text ()
										+ "没有生成采购合同");
					}
					if ($ (this).next ().next ().next ().val () == 0)
					{
						// 报关预录单
						decleration = false;
						$ ("body").showErrorMsg (
								"订单" + $ (this).parent ().next ().text ()
										+ "没有生成报关预录单");
					}
					if (!(perContract && decleration))
					{
						$ (this).prop ("checked", false);
						return;
					}
					// 默认为全选
					var flag = true;
					//变量所有checkbox，判断当前选择的状态
					$.each ($tbody.find ("input:checkbox"), function(index,
							item)
					{
						if (!$ (item).prop ("checked"))
						{
							flag = false;
							return false;
						}
					});
					if (flag)
					{
						$thead.find ("th:first input:checkbox").prop (
								"checked", true);
					}
					else
					{
						$thead.find ("th:first input:checkbox").prop (
								"checked", false);
					}
				});
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
</script>