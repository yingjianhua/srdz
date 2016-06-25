Ext.define('mvc.view.sys.SysUser.WinEdit', {
	extend : 'Ext.window.Window',
	width : 600,
	height : 500,
	layout : 'border',
	resizable : true,
	maximizable : true,
	modal : true,
	iconCls : 'app-icon',
	record : null,
	oldId : 'SysUser_edit_',
	isEdit : true, //默认为编辑状态
	initComponent : function() {
		var mainPkey = this.record.get('bean.pkey');
		this.items = [{
			region : 'north',
			xtype : 'form',
			border : false,
			bodyPadding : '5 5 0 5',
			layout: {
			    type: 'table',
			    columns : 2,
			    itemCls : 'x-layout-table-items'
			},
			fieldDefaults : {
				labelWidth : 100,
				//width : 250,
				labelStyle : 'font-weight : bold',
				readOnly : true
			},
			items : [{
				xtype : 'textfield',
				value: this.record.get('bean.loginName'),
				fieldLabel : '登录账号'
			},{
				xtype : 'textfield',
				value: this.record.get('bean.name'),
				fieldLabel : '名称'
			}]
		},{
			region : 'center',
			xtype : 'tabpanel',
			border : false,
			items : [{
					xtype : Ext.create('mvc.view.sys.SysUser.ListEdit',{
					border : false,
					title : '角色',
					itemId : this.oldId+'1',
					mainPkey : mainPkey,
					iconCls : 'tab-user-icon',
					isEdit : this.isEdit
					})
				}]
		}];
		this.callParent(arguments);
	},
	loadData : function(){
		this.down('#'+this.oldId+'1').onLoadFirst();
	},
	close : function(){
		//关闭WIN窗口时需要手动调用单元格编辑取消事件]
		var tab = this.down('tabpanel').getActiveTab();
		if (tab.isEdit)
			this.down('tabpanel').getActiveTab().cellEditing.cancelEdit();
		this.callParent(arguments);
	}
});
