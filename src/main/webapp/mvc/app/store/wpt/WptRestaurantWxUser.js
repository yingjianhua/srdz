Ext.define('mvc.store.wpt.WptRestaurantWxUser',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptRestaurantWxUser',
model : 'mvc.model.wpt.WptRestaurantWxUser',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptRestaurantWxUser_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});