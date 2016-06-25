Ext.define('mvc.view.sys.SysDept.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysDept_',
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
({xtype : 'textfield',name : 'bean.code',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '代码',readOnly : !this.insFlag}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '部门名称'}
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysOrg','',{
					name : 'bean.org',
					fieldLabel : '所属机构',
					readOnly : !this.insFlag
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.manager',
		fieldLabel : '部门负责人',
		bean : 'SysUser',
		beanType : 'sys',
		emptyText : form_empty_text
	},{
		xtype : 'beantrigger',
		name : 'bean.deptUp',
		fieldLabel : '上级部门',
		bean : 'SysDept',
		beanType : 'sys',
		emptyText : form_empty_text,
		readOnly : !this.insFlag
	},{xtype : 'textfield',name : 'bean.rem',fieldLabel : '备注'}
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
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