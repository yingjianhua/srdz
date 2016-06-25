Ext.define('mvc.store.wx.WxPreviewUsr',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxPreviewUsr',
model : 'mvc.model.wx.WxPreviewUsr',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxPreviewUsr_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});