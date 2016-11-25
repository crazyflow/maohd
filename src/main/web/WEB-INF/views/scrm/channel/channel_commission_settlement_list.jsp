<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="modal fade" id="channelModal" tabindex="-1" role="dialog" aria-labelledby="channelModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 80%">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="channelModalLabel">选择渠道商</h4>
				<!-- <div class="page-title">选择渠道商</div>  -->
			</div>
			<div class="modal-body">
				<form id="formChannel">
					<div class="search-area">
						<div class="search-group">
							<label>业务主体：</label>
							<select seed=122218014 style="width: 120px;" id="businessMainBody" name="businessMainBody">
							</select>
						</div>
						<div class="search-group">
							<label>渠道商：</label>
							<input seed=122218015 style="width: 120px;" class="form-control" name="channelName" />
						</div>
						<div class="search-group">
							<label>联系人：</label>
							<input seed=122218016 style="width: 120px;" class="form-control" name="contactName" />
						</div>
						<div class="search-group">
							<button class="btn btn-default btn-search" type="button" seed=122218017 onclick="getChannelDatas()">查询</button>
						</div>
					</div>
				</form>
				<div id="channelTable" style="margin: 15px;"></div>
			</div>
			<div class="modal-footer">
				<button type="button" seed=122218021 class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
<script>
	$ (function()
	{
		$ ("#formChannel #businessMainBody").append ("${businessMainBodys}");
		$ ("#formChannel #businessMainBody option:eq(0)").attr ("selected","selected");
	});
</script>