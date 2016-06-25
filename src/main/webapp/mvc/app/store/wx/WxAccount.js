Ext.define('mvc.store.wx.WxAccount',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxAccount',
model : 'mvc.model.wx.WxAccount',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxAccount_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});