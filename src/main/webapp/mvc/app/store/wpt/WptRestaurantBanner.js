Ext.define('mvc.store.wpt.WptRestaurantBanner',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurantBanner',
model : 'mvc.model.wpt.WptRestaurantBanner',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/restaurantBanner_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});