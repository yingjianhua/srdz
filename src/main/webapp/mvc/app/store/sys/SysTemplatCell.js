Ext.define('mvc.store.sys.SysTemplatCell',{
extend : 'Ext.data.Store',
requires : 'mvc.model.sys.SysTemplatCell',
model : 'mvc.model.sys.SysTemplatCell',
pageSize : 20,
remoteSort : false,
autoLoad : false,
proxy : {
	type : 'ajax',
	url : base_path+'/sys_SysTemplatCell_list',
	reader : {
		type : 'json',
		root : 'items',
		totalProperty : 'total'
	},
	simpleSortMode : true
}
});