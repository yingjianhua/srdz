Ext.define('mvc.store.wpt.WptRestaurantCase',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurantCase',
model : 'mvc.model.wpt.WptRestaurantCase',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/restaurantCase_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});