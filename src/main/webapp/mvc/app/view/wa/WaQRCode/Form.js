Ext.define('mvc.view.wa.WaQRCode.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaQRCode_',
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
					fieldLabel : '二维码类型',
					store : Ext.create('mvc.combo.wx.WxOQRCodeType'),
					value : 1
				})
	,{xtype : 'textfield',name : 'bean.sceneKey',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '场景值'}
	,{xtype : 'numberfield',name : 'bean.validTerm',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '有效天数',decimalPrecision : 2}
	,{xtype : 'textfield',name : 'bean.summary',fieldLabel : '二维码详情'}
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