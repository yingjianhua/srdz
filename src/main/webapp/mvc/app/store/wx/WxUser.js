Ext.define('mvc.store.wx.WxUser',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxUser',
model : 'mvc.model.wx.WxUser',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxUser_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});