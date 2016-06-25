Ext.define('mvc.store.ComboSelf', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	autoLoad : true,
	actUrl : '', //请求路径
	actWhere : '',
	proxy : {
		type : 'ajax',
		url : base_path+this.actUrl,
		reader : {
			type : 'json',
			root : 'items'
		}
	},
	constructor: function(config) {
		this.actUrl = config['actUrl'];
		this.actWhere = config['actWhere'];
		this.proxy.url = base_path + '/'+this.actUrl;
		if (this.actWhere != '')
			this.proxy.extraParams={"sarg1":this.actWhere};
		else
			this.proxy.extraParams=null;
		this.callParent(arguments);	
	}
})