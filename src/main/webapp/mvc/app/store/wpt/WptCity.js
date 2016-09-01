Ext.define('mvc.store.wpt.WptCity',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCity',
model : 'mvc.model.wpt.WptCity',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/city_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});