Ext.define('mvc.store.wpt.WptRestaurantBsn',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurantBsn',
model : 'mvc.model.wpt.WptRestaurantBsn',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/restaurantBsn_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});