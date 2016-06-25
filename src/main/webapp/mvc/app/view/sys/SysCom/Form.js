Ext.define('mvc.view.sys.SysCom.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysCom_',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
plugins : {
	ptype : 'datatip'
},
initComponent : function(){
			if (this.insFlag)
				this.url = this.url + 'ins';
			else
				this.url = this.url + 'upd';
			var formFlds = [];
			formFlds.push
({xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '机构名称'}
	,{xtype : 'textfield',name : 'bean.shortName',fieldLabel : '机构简称'}
	,{xtype : 'textfield',name : 'bean.tel1',fieldLabel : '电话1'}
	,{xtype : 'textfield',name : 'bean.tel2',fieldLabel : '电话2'}
	,{xtype : 'textfield',name : 'bean.fax',fieldLabel : '传真'}
	,{xtype : 'textfield',name : 'bean.website',fieldLabel : '网址'}
	,{xtype : 'textfield',name : 'bean.addr',fieldLabel : '地址'}
	,{xtype : 'textfield',name : 'bean.zipCode',fieldLabel : '邮编'}
	,{xtype : 'textfield',name : 'bean.rem',fieldLabel : '备注'}
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysUser','',{
					name : 'bean.updatedBy',
					fieldLabel : '更新员'
				})
	,{xtype : 'datefield',name : 'bean.updatedDateTime',fieldLabel : '更新时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysUser','',{
					name : 'bean.createdBy',
					fieldLabel : '建档员'
				})
	,{xtype : 'datefield',name : 'bean.createdDateTime',fieldLabel : '建档时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
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