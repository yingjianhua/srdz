Ext.define('mvc.view.sys.SysCustom.Trigger', {
	extend : 'Ext.window.Window',
	width : 700,
	height : 400,
	layout : 'fit',
	title : '选择器-客户信息表',
	resizable : true,
	modal : true,
	border : false,
	initComponent : function() {
		var list = Ext.create('mvc.view.sys.SysCustom.TriggerList');
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
