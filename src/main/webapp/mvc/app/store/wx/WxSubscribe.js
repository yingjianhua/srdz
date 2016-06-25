Ext.define('mvc.store.wx.WxSubscribe',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxSubscribe',
model : 'mvc.model.wx.WxSubscribe',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxSubscribe_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});