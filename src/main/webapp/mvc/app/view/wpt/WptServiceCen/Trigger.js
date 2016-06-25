Ext.define('mvc.view.wpt.WptServiceCen.Trigger', {
	extend : 'Ext.window.Window',
	width : 700,
	height : 400,
	layout : 'fit',
	title : '选择器-客服中心',
	resizable : true,
	modal : true,
	border : false,
	initComponent : function() {
		var list = Ext.create('mvc.view.wpt.WptServiceCen.TriggerList');
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