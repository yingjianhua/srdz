Ext.define('mvc.store.wx.WxSetting',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxSetting',
model : 'mvc.model.wx.WxSetting',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxSetting_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});