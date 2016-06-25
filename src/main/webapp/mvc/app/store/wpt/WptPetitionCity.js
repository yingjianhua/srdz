Ext.define('mvc.store.wpt.WptPetitionCity',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptPetitionCity',
model : 'mvc.model.wpt.WptPetitionCity',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt_WptPetitionCity_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});