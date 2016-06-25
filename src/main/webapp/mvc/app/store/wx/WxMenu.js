Ext.define('mvc.store.wx.WxMenu',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxMenu',
model : 'mvc.model.wx.WxMenu',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxMenu_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});