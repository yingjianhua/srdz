Ext.define('mvc.combo.wx.WxOQRCodeType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [{value : 1,text : '临时二维码'}
	,{value : 2,text : '永久二维码'}
	]
});