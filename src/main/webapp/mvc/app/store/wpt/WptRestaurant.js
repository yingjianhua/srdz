Ext.define('mvc.store.wpt.WptRestaurant',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurant',
model : 'mvc.model.wpt.WptRestaurant',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/restaurant_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});