<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div <c:if test="${redCount > 0}">
					<c:out value="style=cursor:pointer" />
					<c:out value="onclick=getDataByStatus(${redCount})" />
				</c:if>>红冲（<span <c:if test="${redCount > 0}"><c:out value="style=color:red;" />
				</c:if>>${redCount}</span>)</div>
</body>
</html>