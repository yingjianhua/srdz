Ext.define('mvc.store.Image', {
	extend : 'Ext.data.Store',
	requires : 'mvc.model.Image',
	model : 'mvc.model.Image',
	remoteSort : false,
	autoLoad : true,
	proxy: {
        type: 'ajax',
        url: base_path+'/sys_SysMenu_loadModImg',
        reader : {
			type : 'json',
			root : 'items'
		}
    }
})
        