<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>index</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.button {
	width: 250px;
	height: 40px;
	margin: 5px;
}
</style>
<script type="text/javascript"
	src="${contextPath}/static/js/lib/jquery.js' />"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/lib/plugins/jquery.json.js' />"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=4>
	<h1>需要认证登陆的界面<h1>
	<br />
	<h3>获取认证的信息></h3>
	<shiro:principal
		type="com.cbecs.framework.security.shiro.authc.UserPrincipal"
		property="username" />
	<br />
	<shiro:principal
		type="com.cbecs.framework.security.shiro.authc.UserPrincipal"
		property="orgName" />
	<br />
	<h2>测试</h2>
	<input type="button" value="返回index" class="button" onclick="exchange1()" />

	<input type="button" value="这个请求需要认证" class="button" onclick="requrestAuth()" />
	<input type="button" value="这个请求不需要认证" class="button" onclick="requrestAuth2()" />
</body>
<script type="text/javascript">
function exchange1(){
	window.location.href = "http://local.maohd.com:8080/framework/index"
	//$.get("http://local.maohd.com:8080/framework/pageView/index2");
}

function requrestAuth(){
	$.get("http://local.maohd.com:8080/framework/acc/test",function(data){
		if("auth_success" == data){
			alert(data);
		}else{
			window.location.href = "http://local.maohd.com:8080/framework/login"
		}
	});
}

function requrestAuth2(){
	$.get("http://local.maohd.com:8080/framework/noacc/test",function(data){
		alert(data);
	});
}
</script>
</html>