<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>success</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body>
  <div class="modal fade bs-example-modal-lg modal-primary in" role="dialog" style="display: block; background-color: rgba(0, 0, 0, 0.2); text-align: center;">
    <div class="modal-dialog modal-lg" style="padding: 121px;">
      <div>
        <img src="${staticPath}/Contents/img/loading.gif" alt="">
        <br>
        <br>
        <div id="messageInfo" class="text-success">成功</div>
        <div>
          页面将在
          <span style="color: white" id="timeInfo">3</span>
          秒钟后跳转！
        </div>
      </div>
    </div>
  </div>
  <script type="text/javascript">
			var i = 2;
			$(function() {
				intervalid = setInterval(function() {
					if (i == 0) {
						openPage('${submission.tabName}',
								'${submission.tabUrl}', '${submission.tabId}');
						clearInterval(intervalid);
					}
					$("#timeInfo").html(i);
					i--;
				}, 1000);
			});
			function openPage(tabName, tabUrl, tabId) {
				var contextPath = window.location.protocol + '//'
						+ window.location.hostname + ':' + window.location.port;
				removeCurrentTab();
				addTab(tabName, contextPath + tabUrl, true, tabId);
			}
		</script>
</body>
</html>
