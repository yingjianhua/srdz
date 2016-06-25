Ext.define('mvc.view.South', {
	extend : 'Ext.Toolbar',
	margin : '5 0 0 0',
	initComponent : function() {
		this.region = "south";
		this.items = [ "当前用户：" + lg_user_name, '->', "技术支持" ];
		this.callParent(arguments);
	},
})