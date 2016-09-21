Ext.define('mvc.store.wpt.WptSpecial',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptSpecial',
model : 'mvc.model.wpt.WptSpecial',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/special_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});