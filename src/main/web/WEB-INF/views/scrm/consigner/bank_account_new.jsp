<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>新增虚拟子账户</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body pageId=112114>
	<div class="body">
		<div class="page-body">
			<div class="page-title">
				新增虚拟子账户
				
			</div>
			<div class="modal-body">
				
				<form id="newWithDrawForm" action="${contextPath}/bank-account"
					method="post" onsubmit="formchongdu()">
					
					
					<table class="table table-bordered">
						<tr>
							<th style="line-height: 30px">银行*</th>
							<td style="text-align: left">
								<select seed=112114001 style="width:30%" name='bankId' data-required="" data-number0="">
									<option value="" selected>请选择</option>
									
								</select>
								<input type='hidden' name='bankName'>
							</td>
							
						</tr>
						<tr>
							<th style="line-height: 30px">业务主体*</th>
							<td style="text-align: left">
								<select seed=112114002 style="width:30%" name='businessMainBody' data-required="" data-number0="">
									<option value="" selected>请选择</option>
									
								</select>
								
							</td>
							
						</tr>
						<tr>
							<th style="line-height: 30px">主账号*</th>
							<td style="text-align: left">
								<select seed=112114003 style="width:30%" name="accountNo" data-required="" data-number0="">
									<option value="" selected>请选择</option>
									
								</select>
								
							</td>
							
						</tr>
						<tr>
							<th>委托客户*</th>
							<td style="text-align: left">
								
								<input seed=112114004 style="width:30%;border-radius:5px;height:30px;line-height:30px;padding-left:5px;vertical-align: middle;background: #fff;word-wrap:normal;border:1px solid #cccccc;" list="consigner2" name="consignerName"  data-required="" >
								<input type="hidden" name="consignerId"><a seed=112114008 onclick='khxq()'>查看该客户详情</a>
								<datalist id='consigner2' >
								
								
								</datalist>
							</td>
							
						</tr>
						<tr>
							<th style="line-height: 30px">户名*</th>
							<td style="text-align: left"><span id='txt'></span>&nbsp;<input type="text" seed=112114005
								 class="form-control text-box single-line" maxlength="30"
								style="width: 30%"  id="accountName" data-required="" 
								 /><input type="hidden" name="accountName" ></td>
							
						</tr>
						<tr>
							<th style="line-height: 30px">账号</th>
							<td style="text-align: left;color:gray" >提交申请后，账号由系统根据主账号自动生成</td>
							
						</tr>
						<tr>
							<th style="line-height: 30px">备注</th>
							<td style="text-align: left"><input type="text" seed=112114006
								 class="form-control text-box single-line"
								style="width: 30%"  name='remark' maxlength="100"
								 /></td>
							
						</tr>
					</table>
					<div style="float:right">制单信息:&nbsp; <span>${user.name }&nbsp;${time}</span></div>
					<br/>
					<div class="btn-submit">
						
						<button type="submit" seed=112114007 class="btn btn-primary btn-font4"
							id="submitApply">提交申请</button>
						<input id='status' name='status' type='hidden'>
					</div>
				</form>
			</div>

			
		</div>
	</div>
</body>

<script type="text/javascript">
	$("[name='consignerName']").change(function(){
		var consignerName = $("[name='consignerName']").val();
		var consignerId = $("#"+consignerName).val();
		var bankId = $("[name='bankId']").val();
		
		if(consignerId==undefined){
			
			$("[name='consignerName']").val("");
		}else{
			
			$.ajax({
				url:"${contextPath}/bank-account/getEnname/"+consignerId,
				type:'get',
				contentType:"json",
				success:function(data){
					var Enname= data.bankAccount.englishName;
					
					if(Enname!=""){
						$("#accountName").val(Enname);
					}else{
						$("#accountName").val("");
					}
					
				}
			})
			var bodyId=$("[name='businessMainBody']").val();
			var accountNo=$("[name='accountNo']").val();
			if($("[name='businessMainBody']").val!="" && $("[name='accountNo']").val()!=""){
				$.ajax({
					url:"${contextPath}/bank-account/getjianchen/"+bodyId+"/"+accountNo,
					type:'get',
					contentType:"json",
					success:function(data){
						
						$("#txt").text(data.bba.accountEnabbr)
						
					}
				})
			}
			$.ajax({
				url:"${contextPath}/bank-account/consignerexists/"+consignerId+"/"+bankId+"/"+accountNo,
				type:'get',
				contentType:"json",
				success:function(data){
					var num = data.list.length;
					var bankName=$("[name='bankName']").val();
					var zhuzhanghao = $("[name='accountNo']").val();
					var weituokehu = $("[name='consignerName']").val();
					if(num!=0){
						for(i=0;i<num;i++){
							alert("申请失败，"+weituokehu+"在"+bankName+"的主账号："+zhuzhanghao+"下已存在虚拟子账号:"+data.list[i].subAccountNo+"！");
							$("[name='consignerName']").val("");
						}
						
					}
				}
			})
		}
	})
	$.ajax({
		url:"${contextPath}/bank-account/getbank",
		type:'get',
		contentType:"json",
		success:function(data){
			var list = data.banklist;
			if(list.length==1){
				$("[name='bankId']").empty();
				for(var i = 0;i<list.length;i++){
					
					
					$("[name='bankId']").append("<option selected value="+list[i].bankId+">"+list[i].bankName+"</option>")
				}
				var bankName = $("[name='bankId']").find("option:selected").text();
				$("[name='bankName']").val(bankName);
			}else{
				for(var i = 0;i<list.length;i++){
					
					$("[name='bankId']").append("<option value="+list[i].bankId+">"+list[i].bankName+"</option>")
				}
				
			}
			
			if($("[name='bankId']").val()!=""){
				var bankId=$("[name='bankId']").val();
				
				$.ajax({
					url:"${contextPath}/bank-account/getbusinessbody/"+bankId,
					type:'get',
					contentType:"json",
					success:function(data){
						
						var list = data.list;
						if(list.length==1){
							$("[name='businessMainBody']").empty();
							for(var i = 0;i<list.length;i++){
								$("[name='businessMainBody']").append("<option selected value="+list[i].bodyId+">"+list[i].bodyName+"</option>")
							}
						}else{
							for(var i = 0;i<list.length;i++){
								$("[name='businessMainBody']").append("<option  value="+list[i].bodyId+">"+list[i].bodyName+"</option>")
							}
						}
						if($("[name='businessMainBody']").val()!="" ){
							var bankId=$("[name='bankId']").val();
							var bodyId=$("[name='businessMainBody']").val();
							
							
							$.ajax({
								url:"${contextPath}/bank-account/getaccountno/"+bankId+"/"+bodyId,
								type:'get',
								contentType:"json",
								success:function(data){
									var list = data.list;
									if(list.length==1){
										$("[name='accountNo']").empty()
										for(var i = 0;i<list.length;i++){
											$("[name='accountNo']").append("<option selected value="+list[i].accountNo+">"+list[i].accountNo+"</option>")
										}
									}else{
										$("[name='accountNo']").empty();
										$("[name='accountNo']").append("<option value=''>请选择</option>")
										for(var i = 0;i<list.length;i++){
											$("[name='accountNo']").append("<option value="+list[i].accountNo+">"+list[i].accountNo+"</option>")
										}
									}
									
									
								}
							})
							$.ajax({
								url:"${contextPath}/bank-account/getConsigner/"+bodyId,
								type:'get',
								contentType:"json",
								success:function(data){
									var list = data.list;
									for(var i = 0;i<list.length;i++){
										$("#consigner2").append("<option>"+list[i].consignerName+"</option>")
										$("#consigner2").append("<input value='"+list[i].consignerId+"' type='hidden' id='"+list[i].consignerName+"'>")
									}
								}
							})
							
						}
						
						
						
					}
				})
			}
			
		}
		
	})
	
	
	
	$("[name='bankId']").change(function(){
		var bankId=$("[name='bankId']").val();
		var bankName = $("[name='bankId']").find("option:selected").text();
		$("[name='bankName']").val(bankName);
		$.ajax({
			url:"${contextPath}/bank-account/getbusinessbody/"+bankId,
			type:'get',
			contentType:"json",
			success:function(data){
				
				var list = data.list;
				if(list.length==1){
					$("[name='businessMainBody']").empty();
					for(var i = 0;i<list.length;i++){
						$("[name='businessMainBody']").append("<option selected value="+list[i].bodyId+">"+list[i].bodyName+"</option>")
					}
					var bankId=$("[name='bankId']").val();
					var bodyId=$("[name='businessMainBody']").val();
					
					
					$.ajax({
						url:"${contextPath}/bank-account/getaccountno/"+bankId+"/"+bodyId,
						type:'get',
						contentType:"json",
						success:function(data){
							var list = data.list;
							if(list.length==1){
								$("[name='accountNo']").empty();
								for(var i = 0;i<list.length;i++){
									$("[name='accountNo']").append("<option selected value="+list[i].accountNo+">"+list[i].accountNo+"</option>")
								}
								
							}else{
								$("[name='accountNo']").empty();
								$("[name='accountNo']").append("<option value=''>请选择</option>")
								for(var i = 0;i<list.length;i++){
									$("[name='accountNo']").append("<option value="+list[i].accountNo+">"+list[i].accountNo+"</option>")
								}
							}
							
							
						}
					})
					
					$.ajax({
						url:"${contextPath}/bank-account/getConsigner/"+bodyId,
						type:'get',
						contentType:"json",
						success:function(data){
						var list = data.list;
						$("#consigner2").empty();
						$("[name='consignerName']").val("");
						for(var i = 0;i<list.length;i++){
							$("#consigner2").append("<option>"+list[i].consignerName+"</option>")
							$("#consigner2").append("<input value='"+list[i].consignerId+"' type='hidden' id='"+list[i].consignerName+"'>")
						}
						}
					})
				}else{
					$("[name='businessMainBody']").empty();
					$("[name='businessMainBody']").append("<option value=''>请选择</option>")
					for(var i = 0;i<list.length;i++){
						$("[name='businessMainBody']").append("<option value="+list[i].bodyId+">"+list[i].bodyName+"</option>")
					}
				}
			}
		})
	})
	
	$("[name='businessMainBody']").change(function(){
		var bankId=$("[name='bankId']").val();
		var bodyId=$("[name='businessMainBody']").val();
		
		
		$.ajax({
			url:"${contextPath}/bank-account/getaccountno/"+bankId+"/"+bodyId,
			type:'get',
			contentType:"json",
			success:function(data){
				var list = data.list;
				if(list.length==1){
					$("[name='accountNo']").empty();
					for(var i = 0;i<list.length;i++){
						$("[name='accountNo']").append("<option selected value="+list[i].accountNo+">"+list[i].accountNo+"</option>")
					}
				}else{
					$("[name='accountNo']").empty();
					$("[name='accountNo']").append("<option value=''>请选择</option>")
					for(var i = 0;i<list.length;i++){
						$("[name='accountNo']").append("<option value="+list[i].accountNo+">"+list[i].accountNo+"</option>")
					}
				}
				
				
			}
		})
		
		$.ajax({
			url:"${contextPath}/bank-account/getConsigner/"+bodyId,
			type:'get',
			contentType:"json",
			success:function(data){
				var list = data.list;
				$("#consigner2").empty();
				$("[name='consignerName']").val("");
				for(var i = 0;i<list.length;i++){
					
					$("#consigner2").append("<option>"+list[i].consignerName+"</option>")
					$("#consigner2").append("<input value='"+list[i].consignerId+"' type='hidden' id='"+list[i].consignerName+"'>")
				}
			}
		})
		
		
	})
	
	function khxq(){
			var consignerName = $("[name='consignerName']").val();
			var consignerId = $("#"+consignerName).val();
			
			if(consignerId==undefined){
				alert("请先选择客户");
			}else{
				addTab('客户详情','/customer/companyInfo.aspx?id='+consignerId+'')
			}

		}
	

	
	$("#newWithDrawForm").initValidate();
	


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

	
	$("[name='consignerName']").blur(function(){
		var bankId=$("[name='bankId']").val();
		var bodyId=$("[name='businessMainBody']").val();
		var accountNo=$("[name='accountNo']").val();
		
		$.ajax({
			url:"${contextPath}/bank-account/getjianchen/"+bodyId+"/"+accountNo,
			type:'get',
			contentType:"json",
			success:function(data){
				
				var accountname=$("#accountName").val();
				$("[name='accountName']").val(data.bba.accountEnabbr+accountname);
				
			}
		})
	})
	$("[name='accountNo']").change(function(){
		var bodyId=$("[name='businessMainBody']").val();
		var accountNo=$("[name='accountNo']").val();
		
			$.ajax({
				url:"${contextPath}/bank-account/getjianchen/"+bodyId+"/"+accountNo,
				type:'get',
				contentType:"json",
				success:function(data){
					
					$("#txt").text(data.bba.accountEnabbr)
					
				}
			})
			$.ajax({
			url:"${contextPath}/bank-account/getjianchen/"+bodyId+"/"+accountNo,
			type:'get',
			contentType:"json",
			success:function(data){
				
				var accountname=$("#accountName").val();
				$("[name='accountName']").val(data.bba.accountEnabbr+accountname);
				
			}
		})
		
	})
	$("#accountName").blur(function(){
		var bankId=$("[name='bankId']").val();
		var bodyId=$("[name='businessMainBody']").val();
		var accountNo=$("[name='accountNo']").val();
		
		$.ajax({
			url:"${contextPath}/bank-account/getjianchen/"+bodyId+"/"+accountNo,
			type:'get',
			contentType:"json",
			success:function(data){
				
				var accountname=$("#accountName").val();
				$("[name='accountName']").val(data.bba.accountEnabbr+accountname);
				var accountName = $("[name='accountName']").val().replace(/\s/g,"");
				$.ajax({
					url:"${contextPath}/bank-account/accountNameexist/"+accountName,
					type:'get',
					contentType:"json",
					
					success:function(data){
						var num = data.list
						var bankName=$("[name='bankName']").val();
						var zhuzhanghao = $("[name='accountNo']").val();
						var huming = $("[name='accountName']").val();
						if(num!=0){
							alert("申请失败，"+bankName+"的主账号："+zhuzhanghao+"下已存在户名："+huming);
							$("#accountName").val("");
						}
					}
				})
			}
		})
		
		
	})
	function formchongdu(){
		$('#submitApply').attr("disabled","disabled");
	}

	//保存草稿
	

	$('#submitApply').click(function(e) {
		
		
		var consignerName = $("[name='consignerName']").val();
		var consignerId = $("#"+consignerName).val();
		
		$("[name='consignerId']").val(consignerId);
		if(!$("#newWithDrawForm").validateAll()){
			return false;
		}
		
		$('#status').val('2');
	});
	
</script>