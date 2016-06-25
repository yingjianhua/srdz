Ext.define('mvc.view.sys.SysTemplat.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysTemplat_',
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
(
		mvc.Tools.crtComboForm(false,{
					name : 'bean.type',
					fieldLabel : '模板类型',
					store : Ext.create('mvc.combo.sys.SysOTemplateType'),
					value : 1,
					hidden : true
				})
	,{xtype : 'textfield',name : 'bean.code',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '代码'}
	,{xtype : 'numberfield',name : 'bean.year',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '启用年份',allowDecimals : false}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '模板名称'}
	,
		mvc.Tools.crtComboTrigger(true,'sys_SysCell','',{
					name : 'bean.mngCell',
					fieldLabel : '管理单元'
				})
	,{xtype : 'textfield',name : 'bean.rem',fieldLabel : '备注'}
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