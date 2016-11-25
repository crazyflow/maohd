$ (function()
{
	
	// 用户操作事件绑定
	new Tracker ().dispatchEvent ("mousedown");
	// 页面记录
	new Page ();

})

function Page()
{
	var pageId = $ ("body").attr ("pageId");
	this.pageId = (pageId == undefined ? 0 : pageId);
	this.pageUrl = "/pageView/new";

	$.post (this.pageUrl, {
		pageId : this.pageId,
	});

	function refrash()
	{

	}

	function quit()
	{

	}
}

// 埋点，用户页面行为追踪tracker
function Tracker(_eventType)
{
	var event = this.eventType = _eventType;
	// 事件绑定
	this.dispatchEvent = function(event)
	{
		$ ("body")
				.on (
						event,
						function(e)
						{
							switch (event)
							{
							case "mousedown":
								// 获取第一个触发冒泡事件的seed属性
								var seed = bubbleEvent (e.target);
								if (seed != null)
								{
									var url = "/actionView/new";
									$.post (url, {
										htmlDomId : seed
									}, "json");
								}
								break;
							default:
								break;
							}
						});
	}

	// 递归查找所想触发的seed
	function bubbleEvent(obj)
	{
		// 查询不到seed属性
		if ($ (obj).is ($ ("body")))
		{
			return null;
		}
		var seed;
		// 判断参数是否为dom对象
		if ($ (obj))
		{
			seed = $ (obj).attr ("seed");
			if (seed)
			{
				return seed;
			}
			else
			{
				// 判断目标节点是否存在seed属性，如果不存在需要递归查询父节点是否存在seed属性的东西,根据事件冒泡属性，递归判断其父节点是否存在seed，若存在父节点则取消递归并打点上报
				return bubbleEvent ($ (obj).parent ().get (0));
			}
		}
		else
		{
			seed = obj;
			return seed;
		}
	}

	// 触发的元素类型--------------
	function tiggerOfEleType(obj)
	{
		// 节点名称
		var nodeName = e.target.nodeName;
		// input select li div
		if (nodeName == "INPUT")
		{
			// input类型分开处理
			if (e.target.type)
			{
				// reset - checkbox - text - image - submit - radio - file -
				// password - button
			}
		}
	}
};

function bro()
{
	var is360 = false;
	var isIE = false;
	var isFirefox = false;
	var isCrome = false;
	var broName = '';
	if (window.navigator.userAgent.indexOf ('MSIE') != -1
			&& window.navigator.appName.indexOf ("Microsoft") != -1)
	{
		isIE = true;
		broName = 'IE';

	}
	if (window.navigator.userAgent.indexOf ('Firefox') != -1)
	{
		isFirefox = true;
		broName = 'Firefox';
	}
	if (window.navigator.userAgent.indexOf ('Chrome') != -1)
	{
		if (window.navigator.presentation)
		{
			isCrome = true;
			broName = 'Chrome';
		}
		else
		{
			is360 = true;
			broName = '360';
		}
	}
	return broName;
}

function testTracker()
{
	var url = "/actionView/test";
	$.get (url, function(data)
	{
		var recode = "";
		$.each (data, function(index, item)
		{
			recode += (index + 1) + ".用户点击了" + item.html_dom_name + ",行为发生在页面"
					+ item.page_id + "\n";
		})
		
	}, "json");
}
