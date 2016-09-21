Ext.define('mvc.store.wpt.WptSpecialLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptSpecialLine',
model : 'mvc.model.wpt.WptSpecialLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/specialLine_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});