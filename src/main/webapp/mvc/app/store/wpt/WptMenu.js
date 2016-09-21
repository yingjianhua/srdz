Ext.define('mvc.store.wpt.WptMenu',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptMenu',
model : 'mvc.model.wpt.WptMenu',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/restaurantMenu_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});