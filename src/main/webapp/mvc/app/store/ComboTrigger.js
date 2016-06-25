Ext.define('mvc.store.ComboTrigger', {
	extend : 'Ext.data.Store',
	fields : ['value', 'text'],
	autoLoad : true,
	actUrl : 'sys_SysCtype', //请求路径
	actWhere : '',
	proxy : {
		type : 'ajax',
		url : base_path+'/sys_SysCtype_getComboTrigger',
		reader : {
			type : 'json',
			root : 'items'
		}
	},
	constructor: function(config) {
		this.actUrl = config['actUrl'];
		this.actWhere = config['actWhere'];
		this.proxy.url = base_path + '/'+this.actUrl+'_getComboTrigger';
		if (this.actWhere != '')
			this.proxy.extraParams={"sarg1":this.actWhere};
		else
			this.proxy.extraParams=null;
		this.callParent(arguments);	
	}
})