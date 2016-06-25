Ext.define('mvc.view.sys.SysUser.WinPwd', {
	extend : 'Ext.window.Window',
	width : 400,
	layout : 'fit',
	form : null,
	resizable : true,
	modal : true,
	iconCls : 'app-icon',
	initComponent : function() {
		var formFlds = [];
		formFlds.push({
			xtype : 'hiddenfield',
			name : 'bean.pkey'
		},{
			xtype : 'textfield',
			name : 'bean.loginName',
			fieldLabel : '登录账号',
			afterLabelTextTpl : required,
			allowBlank : false,
			readOnly : true
		});
		formFlds.push({
	            xtype: 'textfield',
	            name: 'mmNew',
	            inputType: 'password',
	            afterLabelTextTpl : required,
	            fieldLabel: '密码',
	            allowBlank : false
	        },{
	            xtype: 'textfield',
	            name: 'mmCheck',
	            inputType: 'password',
	            afterLabelTextTpl : required,
	            fieldLabel: '密码确认',
	            allowBlank : false
	        });
		this.items = {
			anchor : '100%',
			plain : true,
			xtype : 'form',
			layout : 'form',
			border : false,
			frame : false,
			bodyPadding : '5 5 5 5',
			fieldDefaults : {
				labelWidth : 70,
				labelStyle : 'font-weight : bold'
			},
			items : [{
				layout : {
					type : 'vbox',
					align : 'stretch'
				},
				border : false,
				items : formFlds
			}]
		};
		this.buttonAlign = 'right';
		this.buttons = [{
			text : '关闭',
			scope : this,
			iconCls : 'win-close-icon',
			handler : this.onClose
		},{
			text : '保存',
			scope : this,
			iconCls : 'win-save-icon',
			handler : this.onSave
		}];
		this.callParent(arguments);
		this.form = this.items.items[0];
	},
	onClose : function(){
		this.close();
	},
	onSave : function(){
		var form = this.form.getForm();
		if (form.isValid()) {
			form.submit({
				url : base_path+ '/sys_SysUser_updPwd',
				type : 'ajax',
				success : function(form, action) {
					Ext.example.msg(msg_title, '密码修改成功');
					this.close();
				},
				failure : mvc.Tools.formFailure(),
				waitTitle : wait_title,
				waitMsg : wait_msg,
				scope : this
			});
		}
	},
	setActiveRecord : function(record) {
		this.form.activeRecord = record;
		this.form.getForm().loadRecord(record);
	}
});