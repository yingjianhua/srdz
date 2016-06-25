Ext.define('mvc.view.sys.SysUser.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysUser_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.loginName',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '登录账号'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.loginState',
					fieldLabel : '登录状态',
					store : Ext.create('mvc.combo.sys.SysOLoginState'),
					value : 0
				})
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysOrg','',{
					name : 'bean.org',
					fieldLabel : '机构',
					readOnly : !this.insFlag
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.dept',
		fieldLabel : '部门',
		bean : 'SysDept',
		beanType : 'sys',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false,
		readOnly : !this.insFlag
	},{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{xtype : 'textfield',name : 'mmNew',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '密码',hidden : !this.insFlag,disabled : !this.insFlag,disabledCls : ''}
	,{xtype : 'textfield',name : 'mmCheck',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '确认密码',hidden : !this.insFlag,disabled : !this.insFlag,disabledCls : ''}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
	});
	this.items = [{
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		border : false,
		items : formFlds
	}];
	this.callParent(arguments);
}
});