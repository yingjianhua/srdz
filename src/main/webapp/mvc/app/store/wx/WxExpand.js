Ext.define('mvc.store.wx.WxExpand',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wx.WxExpand',
model : 'mvc.model.wx.WxExpand',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wx_WxExpand_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});