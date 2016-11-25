<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="//libs.baidu.com/fontawesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
<%-- <link href="${staticPath}/Contents/css/bootstrap.min.css" rel="stylesheet" /> --%>
<link href="//libs.baidu.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" />
<link href="${staticPath}/Contents/css/beyond.min.css" rel="stylesheet" />
<link href="${staticPath}/Contents/css/dataTables.bootstrap.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${staticPath}/Contents/css/tabs.css">
<link href="${staticPath}/Contents/css/style.css?v=201609271118" rel="stylesheet" />
<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
<script src="${staticPath}/Contents/js/datetime/moment-with-locales2.10.6.min.js"></script>
<script src="${staticPath}/Contents/js/datetime/daterangepicker.js"></script>
<script src="${staticPath}/Contents/js/bootstrap.min.js"></script>
<script src="${staticPath}/Contents/js/skins.min.js"></script>
<script src="${staticPath}/Contents/js/beyond.min.js"></script>
<script src="${staticPath}/Contents/js/toastr/toastr.js"></script>
<script type="text/javascript">
	document.domain = "maohd.com";
</script>
<div id="loading" tabindex="-1" class="modal fade bs-example-modal-lg modal-primary in" role="dialog" style="display: none; background-color: rgba(0, 0, 0, 0.2)">
  <div class="modal-dialog modal-lg" style="text-align: center; vertical-align: middle">
    <div style="margin-top: 100px;">
      <img src="${staticPath}/Contents/img/loading.gif" alt="">
      <br>
      <span style="color: white">数据加载中...</span>
    </div>
  </div>
</div>
<script type="text/javascript">
	//加载内容（带蒙版效果）
	$.fn.loadWithMask = function(url, data, callback) {
		$("#loading").show();
		return $(this).load(url, data, function(response, status, xhr) {
			if (callback)
				callback.call(this, response, status, xhr);
			$("#loading").hide();
		}).error(function() {
			$("#loading").hide();
		});
	}

	//初始化表单中所有需要校验的控件
	$.fn.initValidate = function() {
		this.find('[data-required]').required();//必填
		this.find('input[data-number]').number();//限制只能输入大于等于零的数字
		this.find('input[data-number0]').number0();//限制只能输入大于等于零的整数
		this.find('input[data-number2]').number2();//限制只能输入大于等于零保留2位小数的数字
		this.find('input[data-number4]').number4();//限制只能输入大于等于零保留4位小数的数字
		this.find('input[data-allNum]').allNum();//限制只能输入数字 
	}

	//必填校验
	$.fn.required = function() {
		this.sumMaxValidateNum();
		this.blur(function() {
			$(this).requiredValidate();
		});
	}

	$.fn.requiredValidate = function() {
		var success = true;
		this.each(function() {
			$(this).sumValidatedNum();
			if ($(this).attr("data-required") != undefined && $(this).val() == "") {
				var msg = $(this).attr("data-required-fail");
				if (!msg)
					msg = "该项必填";
				$(this).setControlStatus(false, msg);
				success = false;
			} else {
				$(this).setControlStatus(true);
			}
			
		});
		return success;
	}

	//合计控件本次已校验个数
	$.fn.sumValidatedNum = function() {
		if ($(this).attr("data-validatedNum")) {
			this.attr("data-validatedNum", parseInt($(this).attr("data-validatedNum")) + 1);
		} else {
			$(this).attr("data-validatedNum", 1);
		}
	}

	//合计每个控件需要通过校验个数
	$.fn.sumMaxValidateNum = function() {
		this.each(function() {
			if ($(this).attr("data-maxValidateNum")) {
				$(this).attr("data-maxValidateNum", parseInt($(this).attr("data-maxValidateNum")) + 1);
			} else {
				$(this).attr("data-maxValidateNum", 1);
			}
		});
	}

	$.fn.setControlStatus = function(success, failMsg) {
		if (!success) {
			if (this.attr("data-failMsg")) {
				this.attr("data-failMsg", this.attr("data-failMsg") + "<br/>"
						+ failMsg);
			} else {
				this.attr("data-failMsg", failMsg);
			}
		}

		if (this.attr("data-validatedNum") == this.attr("data-maxValidateNum")) {
			if (this.attr("data-failMsg")) {
				this.setError(this.attr("data-failMsg"));
			} else {
				this.setSuccess();
			}
			this.attr("data-validatedNum", "");
			this.attr("data-failMsg", "");
		}
	}

	//控件设置为通过验证样式
	$.fn.setSuccess = function() {
		this.each(function() {
			if ($(this).parent().find(".help-block").length > 0) {
				$(this).parent().find(".help-block").remove();
			}
			$(this).removeClass("has-error");
			$(this).addClass("has-success");
		});
	}

	//控件设置为未通过验证样式，并且在控件下方显示错误消息
	$.fn.setError = function(errorMsg) {
		if (this.parent().find(".help-block").length > 0) {
			this.parent().find(".help-block").remove();
		}
		this.removeClass("has-success");
		this.addClass("has-error");
		if (errorMsg)
			this.parent().append(
					'<small class="help-block">' + errorMsg + '</small>');
	}

	//数字校验
	function numberHandle(reg, fiexedNum) {
		return $(this).keyup(function(e) {
			var text = $.trim($(this).val().replace(/,|，/g, ""));
			if (text == "" || reg.test(text)) {
				$(this).attr('data-lastText', text);
				$(this).val(text);
			} else {
				$(this).val($(this).attr('data-lastText'));
			}
		}).blur(
				function() {
					var text = $.trim($(this).val().replace(/,|，/g, ""));
					var valNumber = parseFloat(text);
					if (isNaN(valNumber))
						this.value = "";
					else
						this.value = !(fiexedNum + 9999) ? valNumber
								: valNumber.toFixed(fiexedNum);

					$(this).attr('data-lastText', $(this).val());
				});
	}

	$.fn.allNum = function() {
		var reg = new RegExp(/^\-?(([1-9]\d*)|\d(\.\d*)?)?$/);
		return numberHandle.call(this, reg);
	}

	$.fn.number = function() {
		var reg = new RegExp(/^(([1-9]\d*)|\d)(\.\d*)?$/);
		return numberHandle.call(this, reg);
	}

	$.fn.number0 = function() {
		var reg = new RegExp(/^(([1-9]\d*)|\d)(\.\d*)?$/);
		return numberHandle.call(this, reg, 0);
	}

	$.fn.number2 = function() {
		var reg = new RegExp(/^(([1-9]\d*)|\d)(\.\d*)?$/);
		return numberHandle.call(this, reg, 2);
	}

	$.fn.number4 = function() {
		var reg = new RegExp(/^(([1-9]\d*)|\d)(\.\d*)?$/);
		return numberHandle.call(this, reg, 4);
	}

	//立即验证所有控件
	$.fn.validateAll = function() {
		var success = true;
		if (!this.find('[data-required]').requiredValidate())
			success = false;

		//其他需要提交的控件没有未通过标志的，打上通过验证标志
		this.find('[name]').not('.has-error').addClass("has-success");
		//定位到第一个未通过校验的控件
		this.find(".has-error:first").focus();
		return success;
	}

	$.fn.showErrorMsg = function(errorMsg) {
		window.top.Notify(errorMsg, 'top-right', '10000', 'danger', 'fa-bolt',
				true);
	}
	
	//显示成功的提示信息
	$.fn.showSuccessMsg = function (successMsg) {
	    window.top.Notify(successMsg, 'top-right', '10000', 'success', 'fa-check', true);
	}

	//
	function dateFormat(year, month, day) {
		if (month < 10) {
			month = "0" + (month)
		}
		var day = date.getDate();
		if (day < 10) {
			day = "0" + (day)
		}
		return year + "-" + month + "-" + day;
	}

	function addTab(tabName, tabUrl, onClose, tabId) {
		if (tabId == undefined) {
			tabId = "" + new Date().getTime();
		}
		window.top.addPanel(tabName, tabUrl, tabId, true, onClose);
	}

	function removeCurrentTab() {
		window.top.$('.tabs-selected').find('.tabs-close').click();
	}
	
	//截取字符串
	function cutStr(content, len) {
		if (content == null)
        {
            return "";
        }

        if (content.lenth > len)
        {
            return content.substr(0, len) + "...";
        }
        
        return content;
	}
</script>