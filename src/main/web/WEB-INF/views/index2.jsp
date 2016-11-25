<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>index2</title>
<style type="text/css">
.button {
	width: 250px;
	height: 40px;
	margin: 5px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript"
	src="${contextPath}/static/js/lib/jquery.js' />"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/lib/plugins/jquery.json.js' />"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=2>

	<%-- <br/>
<shiro:principal type="com.cbecs.framework.security.shiro.authc.UserPrincipal" property="username" />
<br/>
<shiro:principal type="com.cbecs.framework.security.shiro.authc.UserPrincipal" property="orgName" /> --%>

	<div>
		<div class="header">index2.jsp</div>
		<div class="container">
			<ul>
				<li seed=1101000 style="display: inline-block; width: 300px;">
					<p>页面2菜单</p>
				</li>
			</ul>
			<input seed=1101001 type="button" class="button" value="确认" />
			<input seed=1101002 type="button" class="button" value="提交" />
			<input seed=1101003 type="button" class="button" value="取消" />
			<input seed=1101004 type="button" class="button" value="查询" />
			<br />
			<h2>测试</h2>
			<input type="button" value="页面跳转至1" class="button"
				onclick="exchange()" />
			<input type="button" value="跳转需要认证的界面" class="button"
				onclick="exchange2()" />
		</div>
		<div class="footer"></div>
	</div>


</body>
<script type="text/javascript">
	function exchange() {
		window.location.href = "http://local.maohd.com:8080/framework/index"
		//$.get("http://local.maohd.com:8080/framework/pageView/index2");
	}

	function exchange2() {
		window.location.href = "http://local.maohd.com:8080/framework/auth"
		//$.get("http://local.maohd.com:8080/framework/pageView/index2");
	}

	function exchange1() {
		window.location.href = "http://local.maohd.com:8080/framework/index"
		//$.get("http://local.maohd.com:8080/framework/pageView/index2");
	}
</script>
</html>