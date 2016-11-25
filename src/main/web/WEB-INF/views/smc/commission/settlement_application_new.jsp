<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>新增佣金结算申请单</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
<script type="text/javascript"
	src="<c:url value='/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=122219>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				新增佣金结算申请单 <a href="javascript:showOrderDialog()" seed=122219006 class="btn btn-creat">选择订单</a>
			</div>
			<form id="newSettlementForm" onsubmit="formchongfu()" action="${contextPath}/commission-settlement-applications" method="post">
				<div style="text-align: center;">
					<h2>佣金结算申请单</h2>
					<div class="search-group">
						<label style="width: 80px; text-align: right;">申请日期：</label>
						<input  type="hidden" name="status" />
						<input seed=122219001 class="form-control text-box single-line" id="applicationDate" name="applicationDate" style="width: 150px;" />
					</div>
				</div>
				
				<div id="channelInfo">
					<table class="table table-bordered table-hover" style="font-size: 13px;">
						<tbody>
							<tr>
								<td>业务主体</td>
								<td colspan="3">
									<c:forEach items="${businessMainBodys}" var="item2">
										<c:choose>
											<c:when test="${channel.businessMainBody == item2.id}">
												${item2.value}
											</c:when>
										</c:choose>
									</c:forEach>
									<input type="hidden" name="businessMainBody" value=${channel.businessMainBody } />
								</td>
							</tr>
							<tr>
								<td style="width: 25%">
									渠道商名称
									<input type="hidden" id="channelId" name="channelId" value="${channel.channelId}" />
									<input type="hidden" name="channelName" value="${channel.channelName}" />
									<input type="hidden" name="totalCostAmount" value=0 />
									<input type="hidden" name="totalIncomeAmount" value=0 />
									<input type="hidden" name="totalCommissionAmount" value=0 />
								</td>
								<td style="width: 25%">
									<a seed=122219002 href="javascript:addTab('渠道商详情','/Customer2nd/companyInfoByCompanyID.aspx?id=${channel.channelId}')">${channel.channelName}</a>
								</td>
								<td style="width: 25%">合作模式</td>
								<td style="width: 25%">
									<c:choose>
										<c:when test="${channel.cooperationMode == 1}">
											渠道拍档
										</c:when>
										<c:when test="${channel.cooperationMode == 2}">
											渠道代理
										</c:when>
									</c:choose>
									<input type="hidden" name="cooperationMode" value=${channel.cooperationMode } />
								</td>
							</tr>
							<tr>
								<td style="width: 25%">备注</td>
								<td style="width: 75%" colspan="3">
									<input seed=122219003 class="form-control text-box single-line" style="width: 80%" name="remark" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
					<table class="table table-bordered table-hover" id="tab">
						<thead class="bordered-themeprimary">
							<tr>
								<th>订单号</th>
								<th>委托客户</th>
								<th>金融产品</th>
								<th>成本</th>
								<th>收入</th>
								<th>佣金</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="commissionSettlementInfo">
						</tbody>
						<tfoot>
							<tr>
								<th colspan="7">
									<span style="font-weight: bold;">合计:</span><span style="float: right; font-weight: bold;" id="totalCommision"></span>
								</th>
							</tr>
						</tfoot>
					</table>
				</div>
			</form>
			<div style="text-align: center;">
				<a href="javascript:createSettlement(1)" seed=122219004 class="btn btn-default">保存草稿</a> <a href="javascript:createSettlement(2)" seed=122219005 class="btn btn-primary btn-font5">提交申请</a>
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
		setDate (applicationDate);
	});

	function setDate(applyDate)
	{
		$ ("#applicationDate").daterangepicker ({
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
		}, function(bDate, eDate)
		{

		}).on ("cancel.daterangepicker", function(ev)
		{
			$ ("#applicationDate").val ("");
		});
	}
	
	// 订单选择dialog
	var $orderDialog;
	function showOrderDialog()
	{
		if (!$orderDialog)
		{
			$orderDialog = $ ("<div style='overflow: hidden;' id='orderDialog' />");
			$ ("body").append ($orderDialog);
			var url = "${contextPath}/orders/commission-settlement";
			$orderDialog.loadWithMask (url, null, function()
			{
				$ ('#orderModal').modal ({
					keyboard : false
				});
				//$ (".modal-backdrop").css ("opacity", 0);
				$ ('#orderModal').on ('show.bs.modal', function()
				{
					// 打开modal初始化订单列表
					// 初始化订单dialog的日期控件
					var date = new Date ();
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
					var beginDate = year + "-" + month + "-01";
					var endDate = year + "-" + month + "-" + day;
					$ ("#updateTime").daterangepicker ({
						locale : {
							applyLabel : '选择',
							cancelLabel : '清除'
						},
						startDate : beginDate,
						endDate : endDate,
						showDropdowns : true,
						format : 'YYYY-MM-DD'
					}, function(bDate, eDate)
					{
						$ ("#formOrder input[name=beginDate]").val (bDate.format ('YYYY-MM-DD'));
						$ ("#formOrder input[name=endDate]").val (bDate.format ('YYYY-MM-DD'));
					}).on ("cancel.daterangepicker", function(ev)
					{
						$ ("#formOrder input[name=beginDate]").val("");
						$ ("#formOrder input[name=endDate]").val("");
						$ ("#updateTime").val ("");
					}).on ("apply.daterangepicker",function(ev)
							{
						$ ("#formOrder input[name=beginDate]").val ($ ("#updateTime").val ().substring (0, 10) + "-01");
						$ ("#formOrder input[name=endDate]").val ($ ("#updateTime").val ().substring ($ ("#updateTime").val ().length - 10));
					});
					// 查询该渠道商的订单数据
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
		var searchParam = $formOrder.serialize () + "&channelId="
				+ $ ("#channelId").val () + "&exceptOrder=" + selectedOrders ();
		var url = "${contextPath}/orders/commission-settlement?format=table&pageNumber="
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

	// 对话框订单选择
	function selectOrder()
	{
		var orders = "?orders=";
		// 获取所有选择的订单号
		$.each ($ ("#orderTable").find ("table tbody td input:checkbox"),
				function(index, item)
				{
					if ($ (item).prop ("checked"))
					{
						orders += $ (item).next ().val () + ",";
					}
				});
		if ("?orders=" == orders)
		{
			$ ("body").showErrorMsg ("请选择订单");
			return;
		}
		// 添加已经选择的code
		orders = orders + selectedOrders ();
		var url = "${contextPath}/orders/fiancials" + orders
				+ "&cooperationMode="
				+ $ ("input[name=cooperationMode]").val ();
		$ ("#commissionSettlementInfo").loadWithMask (url, null, function()
		{
			// 获取选择订单后总收入，总佣金和总成本
			mergeCell (tab, 1, -1, [ 0, 1, 6 ]);
			getAmount ();
			$ ("#totalCommision").text (totalcommission + "CNY");
			$ ('#orderModal').modal ("hide");
		});
	}

	// 已经选择的订单
	function selectedOrders()
	{
		var str = "";
		var $trs = $ ("#commissionSettlementInfo").children ();
		$.each ($trs, function(index, item)
		{
			if (undefined != $ (item).children ().eq (0).attr ("rowspan"))
			{
				str += $ (item).children ().eq (0).text () + ",";
			}
		});
		return str;
	};

	var totalCost = 0;
	var totalIncome = 0;
	var totalcommission = 0;
	// 获取总成本佣金及收入
	function getAmount()
	{
		totalCost = 0;
		totalIncome = 0;
		totalcommission = 0;
		var $trs = $ ("#commissionSettlementInfo").children ();
		$.each ($trs, function(index, item)
		{
			if (undefined != $ (item).children ().eq (0).attr ("rowspan"))
			{
				if (parseFloat ($ (item).children ().eq (3).text ()))
				{
					totalCost += parseFloat ($ (item).children ().eq (3)
							.text ());
				}
				if (parseFloat ($ (item).children ().eq (4).text ()))
				{
					totalIncome += parseFloat ($ (item).children ().eq (4)
							.text ());
				}
				if (parseFloat ($ (item).children ().eq (5).text ()))
				{
					totalcommission += parseFloat ($ (item).children ().eq (5)
							.text ());
				}
			}
			else
			{
				if (parseFloat ($ (item).children ().eq (1).text ()))
				{
					totalCost += parseFloat ($ (item).children ().eq (1)
							.text ());
				}
				if (parseFloat ($ (item).children ().eq (2).text ()))
				{
					totalIncome += parseFloat ($ (item).children ().eq (2)
							.text ());
				}
				if (parseFloat ($ (item).children ().eq (3).text ()))
				{
					totalcommission += parseFloat ($ (item).children ().eq (3)
							.text ());
				}
			}
		});
		// 通过toFixed修正parseFloat误差
		totalCost = totalCost.toFixed (2);
		totalIncome = totalIncome.toFixed (2);
		totalcommission = totalcommission.toFixed (2);
	}
	function formchongfu(){
		$(".btn-default").attr("disabled","disabled");
		$(".btn-primary").attr("disabled","disabled");
	}
	// 保存信息
	function createSettlement(status)
	{
		
		if (status == 2)
		{
			if ("" == selectedOrders ())
			{
				$ ("body").showErrorMsg ("请关联订单");
				return;
			}
		}
		$ ("input[name=status]").val (status);
		$ ("input[name=totalCostAmount]").val (totalCost);
		$ ("input[name=totalIncomeAmount]").val (totalIncome);
		$ ("input[name=totalCommissionAmount]").val (totalcommission);
		$ ("#newSettlementForm").submit ();
	}

	// 删除结算信息
	function removeOrder(obj)
	{
		// 获取行合并的参数，几行合并,获取该删除按钮所在的序号
		var $tr = $ (obj).parent ().parent ();
		var rowspan = parseInt ($tr.children ().eq (0).attr ("rowspan"));
		var index = parseInt ($tr.index ());
		if (rowspan == undefined)
		{
			$tr.remove ();
			return;
		}
		for (var i = index + rowspan; i > index; i--)
		{
			$tr.parent ().children ().eq (i - 1).remove ();
		}
		// 重新计算合计金额
		getAmount ();
		$ ("#totalCommision").text (totalcommission + "CNY");

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
				$ ("#orderTable :checkbox").prop ("checked", false);
			}
			else
			{
				$ ("#orderTable :checkbox").prop ("checked", true);
			}
		});
		// 每一个单选框的事件
		$tbody.find ("td input:checkbox").on (
				"click",
				function()
				{
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

	//tbl:table对应的dom元素，
	//beginRow:从第几行开始合并（从0开始），
	//endRow:合并到哪一行，负数表示从底下数几行不合并
	//colIdxes:合并的列下标的数组，如[0,1]表示合并前两列，[0]表示只合并第一列
	function mergeCell(tbl, beginRow, endRow, colIdxes)
	{
		var colIdx = colIdxes[0];
		var newColIdxes = colIdxes.concat ();
		newColIdxes.splice (0, 1)
		var delRows = new Array ();
		var rs = tbl.rows;
		if (rs.length < 3)
		{
			return;
		}
		//endRow为0的时候合并到最后一行，小于0时表示最后有-endRow行不合并
		if (endRow === 0)
		{
			endRow = rs.length - 1;
		}
		else if (endRow < 0)
		{
			endRow = rs.length - 1 + endRow;
		}
		var rowSpan = 1; //要设置的rowSpan的值
		var rowIdx = beginRow; //要设置rowSpan的cell行下标
		var cellValue; //存储单元格里面的内容
		for (var i = beginRow; i <= endRow + 1; i++)
		{
			if (i === endRow + 1)
			{//过了最后一行的时候合并前面的单元格
				if (newColIdxes.length > 0)
				{
					mergeCell (tbl, rowIdx, endRow, newColIdxes);
				}
				rs[rowIdx].cells[colIdx].rowSpan = rowSpan;
			}
			else
			{
				var cell = rs[i].cells[colIdx];
				if (i === beginRow)
				{//第一行的时候初始化各个参数
					cellValue = cell.innerHTML;
					rowSpan = 1;
					rowIdx = i;
				}
				else if (cellValue != cell.innerHTML)
				{//数据改变合并前面的单元格
					cellValue = cell.innerHTML;
					if (newColIdxes.length > 0)
					{
						mergeCell (tbl, rowIdx, i - 1, newColIdxes);
					}
					rs[rowIdx].cells[colIdx].rowSpan = rowSpan;
					rowSpan = 1;
					rowIdx = i;
				}
				else if (cellValue === cell.innerHTML)
				{//数据和前面的数据重复的时候删除单元格
					rowSpan++;
					delRows.push (i);
				}
			}
		}
		for (var j = 0; j < delRows.length; j++)
		{
			rs[delRows[j]].deleteCell (colIdx);
		}
	}
</script>