Ext.define('mvc.store.wpt.WptRestaurantMenu',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurantMenu',
model : 'mvc.model.wpt.WptRestaurantMenu',
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