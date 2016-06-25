Ext.define('mvc.tools.StoreSysCtype',{
	getStore: function(type){
		var type = Ext.state.Manager.get('appAll_sys_ctype')[type];
		var data;
		if(type){
			data = type['list'];
		}
		return Ext.create('Ext.data.Store',{
			fields : ['value', 'text'],
			data:data
		});
	}
});