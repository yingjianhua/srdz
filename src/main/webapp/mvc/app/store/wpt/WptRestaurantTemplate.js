Ext.define('mvc.store.wpt.WptRestaurantTemplate',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurantTemplate',
model : 'mvc.model.wpt.WptRestaurantTemplate',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/restaurantTemplate_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});