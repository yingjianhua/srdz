Ext.define('mvc.store.wx.WxOpenPlat',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxOpenPlat',
model : 'mvc.model.wx.WxOpenPlat',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxOpenPlat_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});