Ext.define('mvc.store.wpt.WptRestaurantLine',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurantLine',
model : 'mvc.model.wpt.WptRestaurantLine',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/restaurantLine_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});