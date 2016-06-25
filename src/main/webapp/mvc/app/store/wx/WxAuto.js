Ext.define('mvc.store.wx.WxAuto',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxAuto',
model : 'mvc.model.wx.WxAuto',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxAuto_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});