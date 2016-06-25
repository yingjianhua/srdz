Ext.define('mvc.view.sys.SysTeller.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/sys_SysTeller_',
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
({xtype : 'textfield',name : 'bean.code',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '柜员号',readOnly : !this.insFlag}
	,{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '姓名'}
	,{xtype : 'textfield',name : 'bean.pwd',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '密码'}
	,
		mvc.Tools.crtComboTrigger(false,'sys_SysBranch','',{
					name : 'bean.branch',
					fieldLabel : '网点信息'
				})
	,{xtype : 'textfield',name : 'bean.tel',fieldLabel : '电话'}
	,{xtype : 'textfield',name : 'bean.mobile',fieldLabel : '手机号码'}
	,{xtype : 'textfield',name : 'bean.addr',fieldLabel : '地址'}
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