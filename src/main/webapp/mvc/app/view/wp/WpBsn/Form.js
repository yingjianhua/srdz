Ext.define('mvc.view.wp.WpBsn.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wp_WpBsn_',
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
	,
		mvc.Tools.crtComboTrigger(false,'wp_WpBsnCtg','',{
					name : 'bean.ctg',
					fieldLabel : '分类'
				})
	,
		mvc.Tools.crtComboTrigger(false,'wp_WpBsnArea','',{
					name : 'bean.area',
					fieldLabel : '区域'
				})
	,{xtype : 'numberfield',name : 'bean.latitude',value : 0,fieldLabel : '纬度',decimalPrecision : 6}
	,{xtype : 'numberfield',name : 'bean.longitude',value : 0,fieldLabel : '经度',decimalPrecision : 6}
	,{xtype : 'textfield',name : 'bean.addr',fieldLabel : '地址'}
	,{xtype : 'textfield',name : 'bean.des',fieldLabel : '描述'}
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