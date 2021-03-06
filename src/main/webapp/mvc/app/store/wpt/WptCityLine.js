Ext.define('mvc.store.wpt.WptCityLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptCityLine',
model : 'mvc.model.wpt.WptCityLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/cityLine_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});