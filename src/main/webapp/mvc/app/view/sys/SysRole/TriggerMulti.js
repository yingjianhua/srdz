Ext.define('mvc.view.sys.SysRole.TriggerMulti', {
	extend : 'Ext.window.Window',
	width : 600,
	height : 400,
	layout : 'fit',
	title : '选择器-表单',
	resizable : true,
	modal : true,
	border : false,
	initComponent : function() {
		var list = Ext.create('mvc.view.sys.SysRole.TriggerListMulti');
		list.on('trigger', this.onTrigger, this);
		this.items = {
			anchor : '100%',
			plain : true,
			xtype : list
		};
		this.callParent(arguments);
	},
	onTrigger : function(data, params) {
		this.fireEvent('trigger', data, params);
		this.close();
	}
});