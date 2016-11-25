<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table class="table table-bordered table-hover">
  <thead class="bordered-themeprimary">
    <tr>
      <th>客服</th>
      <th>订单数</th>
      <th>订单金额(CNY)</th>
    </tr>
  </thead>
  <c:choose>
    <c:when test="${empty orderList}">
      <tbody>
        <tr>
          <td colspan="3">未查询到相关数据。</td>
        </tr>
      </tbody>
    </c:when>
    <c:otherwise>
      <tbody>
        <c:forEach items="${orderList}" var="row">
          <tr>
            <td style="text-align: left; width: 20%;">
              <a seed=101206009 onclick="orderReportDetail('${row.customerServiceId}')" href="javascript:void(0)">${row.customerServiceName}</a></td>
            <td style="text-align: right; width: 20%;">${row.orderCount}</td>
            <td style="text-align: right; width: 50%;">
              <fmt:formatNumber value="${row.orderAmount}" pattern="#,##0.00" />
            </td>
          </tr>
        </c:forEach>
      </tbody>
      <%@ include file="/WEB-INF/views/shared/paging.jsp"%>
    </c:otherwise>
  </c:choose>
</table>
<script type="text/javascript">
	function orderReportDetail(customerServiceId){
		var contextPath = window.location.protocol + '//' + window.location.hostname + ':' + window.location.port ;
		addTab('订单统计报表明细',contextPath + '/operation/order-details?customerServiceId=' + customerServiceId);
	}
</script>