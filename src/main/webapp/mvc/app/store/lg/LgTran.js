Ext.define('mvc.store.lg.LgTran', {
	extend : 'Ext.data.Store',
	requires : 'mvc.model.lg.LgTran',
	model : 'mvc.model.lg.LgTran',
	pageSize : 20,
	remoteSort : false,
	autoLoad : false,
	proxy : {
		type : 'ajax',
		url : base_path+'/lg_LgTran_list',
		reader : {
			type : 'json',
			root : 'items',
			totalProperty : 'total'
		},
		simpleSortMode : true
	}
})
