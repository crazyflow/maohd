<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<tfoot>
	<tr id=trPagerPager>
		<td colspan="1000" class="data-page">
			<div class="row data-page">
				<div class="col-xs-4 text-left" style="margin-top: 8px;">
					<span id="spPageInfoPager" class="pager-info">第${page.pageNumber}页,共${page.pageTotal}页,共${page.total}条数据</span>
				</div>
				<div class="col-xs-8">
					<div class="right">
						<ul class="pagination">
							<c:choose>
								<c:when test="${page.pageNumber>1}">
									<li class="pre"><a seed=132327001 href="javascript:void(0)" title="首页"
										data-page="1">首页</a></li>
									<li class="pre"><a seed=132327002 href="javascript:void(0)" title="上一页"
										data-page="${page.pageNumber-1}">上一页</a></li>
									<c:choose>
										<c:when test="${page.pageNumber>=3}">
											<li><a seed=132327003 href="javascript:void(0)"
												title="第${page.pageNumber-2}页"
												data-page="${page.pageNumber-2}">${page.pageNumber-2}</a></li>
											<li><a seed=132327003 href="javascript:void(0)"
												title="第${page.pageNumber-1}页"
												data-page="${page.pageNumber-1}">${page.pageNumber-1}</a></li>
										</c:when>
										<c:otherwise>
											<li><a seed=132327003 href="javascript:void(0)"
												title="第${page.pageNumber-1}页"
												data-page="${page.pageNumber-1}">${page.pageNumber-1}</a></li>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<li class="pre disabled"><span>首页</span></li>
									<li class="pre disabled"><span>上一页</span></li>
								</c:otherwise>
							</c:choose>
							<li class="active"><a seed=132327003 href="javascript:void(0)"
								title="第${page.pageNumber}页" data-page="${page.pageNumber}">${page.pageNumber}</a></li>
							<c:choose>
								<c:when test="${page.pageTotal>1}">
									<c:choose>
										<c:when test="${page.pageTotal-page.pageNumber>=2}">
											<li><a seed=132327003 href="javascript:void(0)"
												title="第${page.pageNumber+1}页"
												data-page="${page.pageNumber+1}">${page.pageNumber+1}</a></li>
											<li><a seed=132327003 href="javascript:void(0)"
												title="第${page.pageNumber+2}页"
												data-page="${page.pageNumber+2}">${page.pageNumber+2}</a></li>
											<li class="next"><a seed=132327004 title="下一页"
												data-page="${page.pageNumber+1}">下一页</a></li>
											<li class="next"><a seed=132327005 title="末页"
												data-page="${page.pageTotal}">末页</a></li>
										</c:when>
										<c:when test="${page.pageTotal-page.pageNumber>=1}">
											<li><a seed=132327003 href="javascript:void(0)"
												title="第${page.pageNumber+1}页"
												data-page="${page.pageNumber+1}">${page.pageNumber+1}</a></li>
											<li class="next"><a seed=132327004  title="下一页"
												data-page="${page.pageNumber+1}">下一页</a></li>
											<li class="next"><a seed=132327005 title="末页"
												data-page="${page.pageTotal}">末页</a></li>
										</c:when>
										<c:otherwise>
											<li class="next disabled"><span>下一页</span></li>
											<li class="next disabled"><span>末页</span></li>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<li class="next disabled"><span>下一页</span></li>
									<li class="next disabled"><span>末页</span></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
		</td>
	</tr>
</tfoot>

<script type="text/javascript">
	$(function() {
		$("#trPagerPager").find("a").click(function() {
			$("#spPageInfoPager").text("页面跳转中,请稍候...;");
		});
	});
</script>