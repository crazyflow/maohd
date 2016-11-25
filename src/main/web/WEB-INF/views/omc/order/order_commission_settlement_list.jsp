<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="modal fade" id="orderModal" tabindex="-1" role="dialog" aria-labelledby="orderModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="orderModalLabel">选择订单</h4>
			</div>
			<div class="modal-body">
				<form id="formOrder" style="margin: 10px;">
					<div class="form-inline">
						<div class="search-group">
							<label style="width: 120px; text-align: right;">订单完成日期：</label>
							<input seed=122219007 class="form-control" id="updateTime" onkeypress="javascript:return false" />
							<input type="hidden" name="beginDate" />
							<input type="hidden" name="endDate" />
						</div>
						<div class="search-group">
							<label style="width: 120px; text-align: right;">订单号：</label>
							<input seed=122219008 class="form-control" name="orderCode" />
						</div>
						<div class="search-group">
							<label style="width: 120px; text-align: right;">委托客户：</label>
							<input seed=122219009 class="form-control" name="companyName" />
						</div>
						<div class="search-group">
							<label style="width: 120px; text-align: right;">业务主体：</label>
							<select seed=122219010 id="businessMainBody" name="businessMainBody" >
							</select>
						</div>
						<div class="search-group">
							<button seed=122219011 class="btn btn-default btn-search" type="button" onclick="getOrderDatas()">查询</button>
						</div>
					</div>
				</form>
				<div id="orderTable" style="margin: 15px;"></div>
			</div>
			<div class="modal-footer">
				<button type="button" seed=122219016 class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" seed=122219017 class="btn btn-primary" onclick="selectOrder()">确认选择</button>
			</div>
		</div>
	</div>
</div>
<script>
	$ (function()
	{
		$ ("#formOrder #businessMainBody").append ("${businessMainBodys}");
		$ ("#formOrder #businessMainBody option:eq(0)").attr ("selected","selected");
	});
</script>