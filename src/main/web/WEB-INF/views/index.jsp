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

.text {
	width: 400px;
	height: 40px;
	margin: 5px;
}

.checkbox {
	width: 40px;
	height: 20px;
}
</style>
<script type="text/javascript"
	src="${contextPath}/static/js/lib/jquery.js' />"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/lib/plugins/jquery.json.js' />"></script>
<script type="text/javascript"
	src="${contextPath}/static/js/logging/tracker.js' />"></script>
</head>
<body pageId=1>
	<div>
		<div class="header">
			<h1>测试界面 -- index</h1>
		</div>
		<div class="container">
			<ul>
				<li seed=1001000 style="display: inline-block; width: 300px;"
					onclick="change('#caidan1')">
					<p>菜单1</p>
				</li>
				<li seed=1002000 style="display: inline-block; width: 300px;"
					onclick="change('#caidan2')">
					<p>菜单2</p>
				</li>
			</ul>
			<div id="caidan1">
				<input seed=1001001 type="button" class="button" value="确认" />
				<input seed=1001002 type="button" class="button" value="提交" />
				<input seed=1001003 type="button" class="button" value="取消" />
				<input seed=1001004 type="button" class="button" value="查询" />
				<br />
				<input seed=1001005 type="text" class="text" value="11" />
				<input seed=1001006 type="text" class="text" value="11" />
				<img seed=1001007 /> <br> 多选框选择checkbox： <br>
				<input type="checkbox" name="nation" class="checkbox" seed=1001008 />
				美国
				<input type="checkbox" name="nation" class="checkbox" seed=1001009 />
				丹麦
				<input type="checkbox" name="nation" class="checkbox" seed=1001010 />
				荷兰
				<input type="checkbox" name="nation" class="checkbox" seed=1001011 />
				葡萄牙 <br />
				<input type="radio" name="sex" class="checkbox" value="男"
					seed=1001012>
				男
				<input type="radio" name="sex" class="checkbox" value="女"
					seed=1001013>
				女 <br />
				<select  seed=1001014 style="width: 200px; height: 40px;">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</div>
			<div id="caidan2" style="display: none;">
				<h2>菜单2的内容啊</h2>
				<input seed=1002001 type="button" class="button" value="确认" />
				<input seed=1002002 type="button" class="button" value="提交" />
				<input seed=1002003 type="button" class="button" value="取消" />
				<input seed=1002004 type="button" class="button" value="查询" />
			</div>

		</div>
		<div class="footer"></div>
	</div>
	<h2>测试</h2>
	<input type="button" value="获取行为轨迹数据" class="button"
		onclick="testTracker()" />
	<br />
	<shiro:authenticated>
		用户[<shiro:principal />]已身份验证通过
	</shiro:authenticated>
	<shiro:notAuthenticated>
		未身份验证（包括记住我）
	</shiro:notAuthenticated>
	</br>
	<input type="button" value="注销当前用户" class="button" onclick="logout()" />

	<br />
	<input type="button" value="进行登录" class="button" onclick="login()" />
	<br />
	<input type="button" value="页面跳转至2" class="button" onclick="exchange()" />
	<br />
	<input type="button" value="跳转需要认证的界面" class="button"
		onclick="exchange2()" />
</body>
<script type="text/javascript">
	function change(obj) {
		$("div[id^=caidan]").hide();
		$(obj).show();
	}

	function login() {
		window.location.href = "http://local.maohd.com:8080/framework/login"
	}

	function logout() {
		window.location.href = "http://local.maohd.com:8080/framework/logout";
	}

	function exchange() {
		window.location.href = "http://local.maohd.com:8080/framework/index2"
	}

	function exchange2() {
		window.location.href = "http://local.maohd.com:8080/framework/auth"
		//$.get("http://local.maohd.com:8080/framework/pageView/index2");
	}
</script>
</html>