<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>login</title>
</head>
<body >
	<%-- <form action="${contextPath}/login' />" method="post">
		<input name="username" value="jiangpeng" />
		<input name="password" value="111111" />
		<input type="submit" value="submit" />
	</form> --%>
	${contextPath}
	${staticPath}
	<h1>登陆界面</h1>
	<a href="/maohd/report/drawer">开票人报表</a>
	<br/>
	<br/>
	<a href="/maohd/report/entrusting">委托方报表</a>
	<br/>
	<br/>
	<a href="/maohd/report/customer_service">客服统计报表</a>
	<br/>
	<br/>
	<a href="/maohd/report/order">订单统计报表</a>
	<br/>
	<br/>
	<a href="/maohd/report/logistics" >物流对账单报表 </a>
	<br/>
	<br/>
	<a href="/maohd/appraisal/dept-perf">业务部门考核报表</a>
	<br/>
	<br/>
	<a href="/maohd/analysis/consigner_active">委托方活跃度报表</a>
	<br/>
	<br/>
	<a href="/maohd/analysis/consigner_reorder">委托方返单率报表</a>
</body>
</html>