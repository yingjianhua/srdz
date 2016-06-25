Ext.define('mvc.view.sys.SysOrg.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysOrg_',
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
({xtype : 'textfield',name : 'bean.code',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '代码',readOnly : !this.insFlag}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '机构名称'}
	,{xtype : 'textfield',name : 'bean.shortName',fieldLabel : '机构简称'}
	,
		mvc.Tools.crtComboTrigger(true,'sys_SysOrg','',{
					name : 'bean.orgUp',
					fieldLabel : '上级机构',
					readOnly : !this.insFlag
				})
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysTemplat','type=1',{
					name : 'bean.templat',
					fieldLabel : '科目模板'
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.valuationMethods',
					fieldLabel : '存货计价方式',
					store : Ext.create('mvc.combo.sys.SysOValuationMethods'),
					value : 1
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.internationTrade',
					fieldLabel : '是否有国际贸易',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.currency',
					fieldLabel : '币种',
					store : Ext.create('mvc.combo.sys.SysOCurrency'),
					value : 1
				})
	,{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
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