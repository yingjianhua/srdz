Ext.define('mvc.view.wx.WxMassMessage.WinMassMessage',{
extend : 'Ext.window.Window',
height : 150,
width : 285,
title : "群发消息",
layout : 'fit',
iconCls : 'app-icon',
tempPkey : null,
tempClass : null,
initComponent : function() {
		this.items = {
			anchor : '100%',
			plain : true,
			xtype : "form",
			bodyPadding : '10 5 10 10',
			items : [{
				xtype: 'radiogroup',
		        fieldLabel: '全部用户',
		        columns: 2,
		        vertical: true,
		        items: [
		            { boxLabel: '是', name: 'isToAll', inputValue: '1', checked: true},
		            { boxLabel: '否', name: 'isToAll', inputValue: '2'},
		        ],
				listeners : {
					scope : this,
					change : function(field, newv, oldv) {
						this.form.down("combobox").setDisabled(newv["isToAll"] == 1);
					}
				}
			},
			    mvc.Tools.crtComboTrigger(false, 'wx_WxUserGroup', '' ,{
					name : 'wxGroupPkey',
					fieldLabel : '用户分组',
					disabled : true
			    })
			]
		};
		this.buttonAlign = 'right',
		this.buttons =[{
			text : '确定',
			scope : this,
			iconCls : 'win-ok-icon',
			handler : this.onSave
		}];
		this.callParent(arguments);
		this.addEvents('create');
		this.form = this.items.items[0];
},
onSave : function(){
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, "您确定要群发消息吗？", function(btn) {
			if (btn != 'yes') return;
			if (me.form.isValid()) {
				me.form.submit({
					url : base_path+'/wx_WxMassMessage_mass',
					submitEmptyText: false,
					type : 'ajax',
					params : {
						tempPkey : me.tempPkey,
						tempClass : me.tempClass
					},
					success : function(form, action) {
						me.close();
					},
					failure : mvc.Tools.formFailure(),
					waitTitle : wait_title,
					waitMsg : wait_msg,
					scope : this
				});
			}
		});
}
});