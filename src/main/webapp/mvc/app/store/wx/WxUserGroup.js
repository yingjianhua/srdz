Ext.define('mvc.store.wx.WxUserGroup',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxUserGroup',
model : 'mvc.model.wx.WxUserGroup',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxUserGroup_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});