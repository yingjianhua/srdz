Ext.define('mvc.view.sys.SysCustom.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysCustom_',
fieldDefaults : {
	labelWidth : 100,
	width : 275,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.code',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '代码'}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'textfield',name : 'bean.shortName',fieldLabel : '简称'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.comPersonFlag',
					fieldLabel : '性质',
					store : Ext.create('mvc.combo.sys.SysOComPersonFlag'),
					value : 1
				})
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysOrg','',{
					name : 'bean.mngOrg',
					fieldLabel : '管理机构'
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.mngDept',
		fieldLabel : '管理部门',
		bean : 'SysDept',
		beanType : 'sys',
		emptyText : form_empty_text
	},{
		xtype : 'beantrigger',
		name : 'bean.businessMember',
		fieldLabel : '业务代表',
		bean : 'SysUser',
		beanType : 'sys',
		emptyText : form_empty_text
	},{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{xtype : 'textfield',name : 'one.tel1',fieldLabel : '电话1'}
	,{xtype : 'textfield',name : 'one.tel2',fieldLabel : '电话2'}
	,{xtype : 'textfield',name : 'one.fax',fieldLabel : '传真'}
	,{xtype : 'textfield',name : 'one.website',fieldLabel : '网址'}
	,{xtype : 'textfield',name : 'one.addr',fieldLabel : '地址'}
	,{xtype : 'textfield',name : 'one.zipCode',fieldLabel : '邮编'}
	,{xtype : 'textfield',name : 'one.rem',fieldLabel : '备注'}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey'
	});
	this.items = [{
		layout : {
			type : 'table',
			columns : 2,
			itemCls : 'x-layout-table-items-form'
		},
		border : false,
		items : formFlds
	}];
	this.callParent(arguments);
}
});