Ext.define('mvc.view.prv.PrvRole.List', {
	extend : 'Ext.panel.Panel',
	loadMask : true,
	action : 'mainPanel',
	roles : '',
	layout : 'border',
	initComponent : function() {
		this.items = [ Ext.create('mvc.view.prv.PrvRole.ListRole', {
			region : 'west',
			roles : this.roles,
			padding:'0 2 0 0',
			width : 200,
		}), Ext.create('mvc.view.prv.PrvRole.ListAct', {
			region : 'center',
			action : 'rightPanel',
			roles : this.roles
		})]
		this.callParent(arguments);
	},
	getStore : function() {
	},
});