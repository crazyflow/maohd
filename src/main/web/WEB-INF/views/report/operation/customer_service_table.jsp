<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>
	th, td {
      	word-break: keep-all !important;
    }
</style>
<table id="tab" class="table table-bordered table-hover" style="width: 1170px;">
	<thead id="test" class="bordered-themeprimary">
		<tr>
			<th>订单号</th>
			<th>委托方</th>
			<th>客服</th>
			<th>实付货款</th>
			<th>报关金额</th>
			<th>报关币种</th>
			<th>收汇日期</th>
			<th>收汇币种</th>
			<th>收汇金额(外币)</th>
			<th>汇率</th>
			<th>收汇金额(人民币)</th>
		</tr>
	</thead>
	
		<c:choose>
		<c:when test="${empty customerServiceList}">
			<tbody>
				<tr>
					<td colspan="11">未查询到相关数据。</td>
				</tr>
			</tbody>
		</c:when>
		<c:otherwise>
			<tbody>
				<c:forEach items="${customerServiceList}" var="row">
					<tr>
						<td style="text-align: left;width: 130px;">
							<a href="javascript:addTab('订单详情','/omc/order/orderDetail.aspx?id=${row.orderId}')">
								${row.orderCode}
							</a>
						</td>
		 				<c:choose>
						    <c:when test="${fn:length(row.companyName) > 8}">
						    	<td style="text-align: left;width: 160px;" title="${row.companyName}">
						    		<a href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${row.companyId}')">
						    			${fn:substring(row.companyName,0,8)}…
						    		</a>
					    		</td>
						     </c:when>
						     <c:otherwise>
						         <td style="text-align: left;width: 160px;" title="${row.companyName}">
						         	<a href="javascript:addTab('委托方详情','/customer/companyInfo.aspx?id=${row.companyId}')">
						         		${row.companyName}
						         	</a>
					         	 </td>
						     </c:otherwise>
		 				</c:choose>
		 				<td style="text-align: left;width: 100px;">${row.customerServiceName}</td>
		 				<td style="text-align: right;width: 100px;"><fmt:formatNumber value="${row.paidAmount}" pattern="#,##0.00" maxFractionDigits="2"/></td>
		 				<td style="text-align: right;width: 100px;"><fmt:formatNumber value="${row.declarationAmount}" pattern="#,##0.00"/></td>
		 				<td style="text-align: left;width: 80px;">${row.currencySymbol}</td>
						<td style="text-align: left;width: 100px;"><fmt:formatDate value="${row.gatherDate}" pattern="yyyy-MM-dd"/></td>
						<td style="text-align: left;width: 80px;">${row.gatherSymbol}</td>
						<td style="text-align: right;width: 120px;"><fmt:formatNumber value="${row.gatherAmountForeign}" pattern="#,##0.00"/></td>
						<td style="text-align: right;width: 80px;"><fmt:formatNumber value="${row.gatherRate}" pattern="#,##0.0000"/></td>
						<td style="text-align: right;width: 140px;"><fmt:formatNumber value="${row.gatherAmountRmb}" pattern="#,##0.00"/></td>
					</tr>
				</c:forEach>
			</tbody>
			<%@ include file="/WEB-INF/views/shared/paging.jsp"%>
		</c:otherwise>
	</c:choose>
	

</table>
<script type="text/javascript">
$(function() {
	mergeCell(tab,1,-1,[0,1,2,3,4,5]);
});
//tbl:table对应的dom元素，
//beginRow:从第几行开始合并（从0开始），
//endRow:合并到哪一行，负数表示从底下数几行不合并
//colIdxes:合并的列下标的数组，如[0,1]表示合并前两列，[0]表示只合并第一列
function mergeCell(tbl,beginRow,endRow,colIdxes){
	var colIdx = colIdxes[0];
	var newColIdxes = colIdxes.concat();
	newColIdxes.splice(0,1)
	var delRows = new Array();
	var rs = tbl.rows;
	if(rs.length<3){
		return;
	}
	//endRow为0的时候合并到最后一行，小于0时表示最后有-endRow行不合并
	if(endRow === 0){
		endRow = rs.length - 1;
	}else if(endRow < 0){
		endRow = rs.length - 1 + endRow;
	}
	var rowSpan = 1; //要设置的rowSpan的值
	var rowIdx = beginRow; //要设置rowSpan的cell行下标
	var cellValue; //存储单元格里面的内容
	for(var i=beginRow; i<= endRow + 1; i++){
		if(i === endRow + 1){//过了最后一行的时候合并前面的单元格
			if(newColIdxes.length > 0){
				mergeCell(tbl,rowIdx,endRow,newColIdxes);
			}
			rs[rowIdx].cells[colIdx].rowSpan = rowSpan;
		}else{
			var cell = rs[i].cells[colIdx];
			if(i === beginRow){//第一行的时候初始化各个参数
				cellValue = cell.innerHTML;
				rowSpan = 1;
				rowIdx = i;
			}else if(cellValue != cell.innerHTML){//数据改变合并前面的单元格
				cellValue = cell.innerHTML;
				if(newColIdxes.length > 0){
					mergeCell(tbl,rowIdx,i - 1,newColIdxes);
				}
				rs[rowIdx].cells[colIdx].rowSpan = rowSpan;
				rowSpan = 1;
				rowIdx = i;
			}else if(cellValue === cell.innerHTML){//数据和前面的数据重复的时候删除单元格
				rowSpan++;
				delRows.push(i);
			}
		}
	}
	for(var j=0;j<delRows.length; j++){
		rs[delRows[j]].deleteCell(colIdx);
	}
}
</script>