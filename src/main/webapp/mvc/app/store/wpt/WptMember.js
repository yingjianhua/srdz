Ext.define('mvc.store.wpt.WptMember',{
extend : 'Ext.data.Store',
requires : 'mvc.model.wpt.WptMember',
model : 'mvc.model.wpt.WptMember',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/wpt/resource/member_page',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});