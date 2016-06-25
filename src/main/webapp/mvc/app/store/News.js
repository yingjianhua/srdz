Ext.define('mvc.store.News', {
	extend : 'Ext.data.Store',
	requires : 'mvc.model.News',
	model : 'mvc.model.News',
	remoteSort : false,
	autoLoad : true,
	scope:this,
	proxy: {
        type: 'ajax',
        url: base_path+'/web_WebNews_loadIndex',
        extraParams : {sarg:'00'},
        reader : {
			type : 'json',
			root : 'items'
		}
    }
})
        