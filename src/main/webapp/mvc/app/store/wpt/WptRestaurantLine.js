Ext.define('mvc.store.wpt.WptRestaurantLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurantLine',
model : 'mvc.model.wpt.WptRestaurantLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurantLine_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});