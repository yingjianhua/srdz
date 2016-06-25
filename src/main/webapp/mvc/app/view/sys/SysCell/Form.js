Ext.define('mvc.view.sys.SysCell.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysCell_',
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
({xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'numberfield',name : 'bean.year',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '年份',allowDecimals : false}
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysOrg','',{
					name : 'bean.org',
					fieldLabel : '机构'
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.dept',
		fieldLabel : '部门',
		bean : 'SysDept',
		beanType : 'sys',
		emptyText : form_empty_text
	},{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
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