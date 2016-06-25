Ext.define('mvc.combo.js.JsOJsMenuType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 0,text : '音乐'}
	,{value : 1,text : '视频'}
	,{value : 2,text : '链接'}
	]
});