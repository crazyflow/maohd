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
							<label style="width: 120px; text-align: right;">订单编号</label>
							<input class="form-control" seed=112429006 id="orderCode" name="orderCode" />
						</div>
						<div class="search-group">
							<label style="width: 120px; text-align: right;">开票人</label>
							<input class="form-control" seed=112429007 id="suppliers" name="suppliers" />
						</div>
						<div class="search-group">
							<button seed=112429008 class="btn btn-default btn-search" type="button" onclick="getOrderDatas()">查询</button>
						</div>
					</div>
				</form>
				<div id="orderTable" style="margin: 15px;"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary" seed=112429009 onclick="OrdersConfirm()">确认</button>
			</div>
		</div>
	</div>
</div>