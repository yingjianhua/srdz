Ext.define('mvc.store.lg.LgLogin', {
	extend : 'Ext.data.Store',
	requires : 'mvc.model.lg.LgLogin',
	model : 'mvc.model.lg.LgLogin',
	pageSize : 20,
	remoteSort : false,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : base_path+'/lg_LgLogin_list',
		reader : {
			type : 'json',
			root : 'items',
			totalProperty : 'total'
		},
		simpleSortMode : true
	}
})
