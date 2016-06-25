Ext.define('mvc.store.wx.WxMassMessage',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxMassMessage',
model : 'mvc.model.wx.WxMassMessage',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxMassMessage_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});