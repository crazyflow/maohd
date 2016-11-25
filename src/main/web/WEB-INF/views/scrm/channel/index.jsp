<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>渠道中心</title>
<%@ include file="/WEB-INF/views/shared/script.jsp"%>
</head>
<body>
  <div class="main-container container-fluid">
    <div class="page-container">
      <div class="page-sidebar" id="sidebar">
        <div class="avatarmin" title="View your public profile">
          <img src="${staticPath}/Contents/img/ico-photo.png" />
        </div>
        <div class="sidebar-header-wrapper menu-text">
          <div class="dropdown profile-element">
            <c:if test="${not empty currentUser}">
              <div class="avatar" title="View your public profile">
                <img src="${staticPath}/Contents/img/ico-photo.png" />
              </div>
              <span class="clear">
                <span class="name">${currentUser.name} </span>
                <span class="position"> ${currentUser.position} </span>
              </span>
            </c:if>
          </div>
        </div>
        <ul class="nav sidebar-menu">
          <c:forEach items="${sysMenus}" var="m">
            <li class="">
              <a href="javascript:void(0);" class="menu-dropdown">
                <i class="${m.imageURL}"></i>
                <span class="menu-text">${m.menuName}</span>
                <i class="menu-expand"></i>
              </a>
              <ul class="submenu">
                <c:forEach items="${m.children}" var="c">
                  <c:if test="${fn:length(c.children)==0}">
                    <li class="menu-first">
                      <a href="javascript:void(0);" data-pagemenu="" onclick="addPanel('${c.menuName}','${c.linkURL}','${c.menuID}')">
                        <span class="menu-text"> ${c.menuName} </span>
                      </a>
                    </li>
                  </c:if>
                  <c:if test="${fn:length(c.children)!=0}">
                    <li class="menu-first">
                      <a href="javascript:void(0);" class="menu-dropdown">
                        <span class="menu-text"> ${c.menuName} </span>
                        <i class="menu-expand"></i>
                      </a>
                      <c:forEach items="${c.children}" var="s">
                        <ul class="submenu">
                          <li class="menu-second">
                            <a href="javascript:void(0)" data-pagemenu="" onclick="addPanel('${s.menuName}','${s.linkURL}','${c.menuID}')">
                              <span class="menu-text">${s.menuName}</span>
                            </a>
                          </li>
                        </ul>
                      </c:forEach>
                    </li>
                  </c:if>
                </c:forEach>
              </ul>
            </li>
          </c:forEach>
        </ul>
      </div>
      <div class="page-content">
        <div class="navbar">
          <div class="navbar-inner">
            <div class="navbar-container">
              <div class="navbar-logo">
                <div>
                  <img style="margin-left: -20px;" src="${staticPath}/theme/ico-cmlogo.png" alt="" />
                </div>
                <span style="background-color: white; width: 2px; height: 30px; display: inline-block; position: absolute; top: 15px; left: 200px;"></span>
                <span style="color: white; font-weight: bold; font-size: 16px; position: absolute; top: 10px; left: 210px;">${currentUser.department}</span>
                <c:choose>
                  <c:when test="${currentUser.signFlag == 1}">
                    <span style="color: white; font-size: 13px; position: absolute; top: 33px; left: 210px;">协议编号：${currentUser.signCode}</span>
                  </c:when>
                  <c:otherwise>
                    <span style="color: white; font-size: 13px; position: absolute; top: 33px; left: 210px;">未签约</span>
                  </c:otherwise>
                </c:choose>
              </div>
              </span>
              <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-menuArrow"></i> <i class="collapse-icon fa fa-bars"></i>
              </div>
              <div class="navbar-header pull-right">
                <div class="navbar-account">
                  <ul class="account-area">
                    <li>
                      <c:if test="${currentUser.roleType == 3}">
                        <a href="${channelUrl}/default.aspx">
                          <i class="navbar-ico4"></i> 切换委托方工作台
                        </a>
                      </c:if>
                      <a href="${channelUrl}/signOut.aspx">
                        <i class="navbar-ico4"></i> 退出
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div id="tt" class="easyui-tabs"></div>
      </div>
    </div>
    <div class="contextMenu" id="myMenu1">
      <ul>
        <li id="close">关闭</li>
        <li id="closeAll">全部关闭</li>
        <li id="refresh">刷新</li>
      </ul>
    </div>
    <div class="contextMenu" id="myMenuNoClose">
      <ul>
        <li id="closeAll">全部关闭</li>
        <li id="refresh">刷新</li>
      </ul>
    </div>
  </div>
  <script src="${staticPath}/Contents/js/easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
  <script src="${staticPath}/Contents/js/jquery.contextmenu.r2.js" type="text/javascript" charset="utf-8"></script>
  <script src="${staticPath}/Contents/js/beyond.min.js"></script>
  <script type="text/javascript">
			$(function() {
				//左侧菜单收缩 头像信息隐藏
				$("#sidebar-collapse").on("click", function() {
					$('.sidebar-header-wrapper').toggle();
					$('.avatarmin').toggle();
				})
				$("[data-pagemenu]").click(function() {
					$("[data-pagemenu]").each(function() {
						$(this).parent().removeClass("active");
					});
					$(this).parent().addClass("active");
				});
				addPanel('客户管理',	'${staticPath}/Customer2nd/customerList.aspx?channelCompanyID=','6AEFA88F-4854-4C19-9C9A-B1509A130CF0', false)
			});

			//添加tab标签
			function addPanel(tabName, tabUrl, tabId, closable, onClose) {
				if (!tabId)
					tabId = tabName;
				var frameId = "frm" + tabId;
				if (closable == undefined)
					closable = true;
				//判断tab 标签是否存在
				var isTab = $('#' + tabId).length > 0;

				if (tabUrl.indexOf("http")) {
					tabUrl = "${staticPath}" + tabUrl;
				}
				if (tabName == '佣金台账') {
					tabUrl = tabUrl + '?channelId=${currentUser.id}';
				}
				if (tabName == '客户管理') {
					tabUrl = tabUrl + '${currentUser.id}';
				}
				if (isTab) {
					$('#tt').tabs('select', tabName);
					$("#" + frameId).attr("src", tabUrl);
					$("#" + frameId)
							.before(
									'<div style="width:100%;height:100%;text-align:center;vertical-align:middle;margin-top:100px"><img src="${staticPath}/Contents/img/loading.gif" alt="" /><br/><span>页面刷新中,请稍候...</span></div>');
					$("#" + frameId).hide();
					$("#" + frameId).one("load", function() {
						$("#" + tabId).children(":first").remove();
						$("#" + frameId).show();
					});
				} else {
					$('#tt')
							.tabs(
									'add',
									{
										id : tabId,
										title : tabName,
										content : '<div style="width:100%;height:100%;text-align:center;vertical-align:middle;margin-top:100px"><img src="${staticPath}/Contents/img/loading.gif" alt="" /><br/><span>页面加载中,请稍候...</span></div><iframe src="'
												+ tabUrl
												+ '" id="'
												+ frameId
												+ '" frameborder="0" marginheight="0" marginwidth="0" style="width:100%;display:none;"></iframe>',
										closable : closable
									});

					//关闭事件
					if (onClose) {
						$('.tabs').find(".tabs-close:last").click(function() {
							onClose();
						});
					}

					var windowH = $(window).height() - 80;
					$("#" + frameId).height(windowH);
					$("#" + frameId).one("load", function() {
						$("#" + tabId).children(":first").remove();
						$("#" + frameId).show();
					});

					//设置tab页头的右键菜单
					var menuName = 'myMenu1';
					if (!closable)
						menuName = 'myMenuNoClose';
					$('.tabs').find("li:last").contextMenu(menuName, {
						bindings : {
							'closeAll' : function() {
								$(".tabs li").each(function(index, obj) {
									//获取所有可关闭的选项卡  
									var tab = $(".tabs-closable", this).text();
									$(".easyui-tabs").tabs('close', tab);
								});
							},
							'refresh' : function(t) {
								$(t).click();
								$("#" + frameId).attr("src", tabUrl);
							},
							'close' : function(t) {
								$(t).find('.tabs-close').click();
							}
						}
					});
				}
			}
		</script>
</body>
</html>
