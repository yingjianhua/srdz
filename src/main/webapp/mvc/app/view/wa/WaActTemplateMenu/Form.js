Ext.define('mvc.view.wa.WaActTemplateMenu.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActTemplateMenu_',
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
({
		xtype : 'beantrigger',
		name : 'bean.temp',
		fieldLabel : '页面模板',
		bean : 'WaActTemplate',
		beanType : 'wa',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'textfield',name : 'bean.name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
	,{xtype : 'imagefield',name : 'bean.currImgUrl',afterLabelTextTpl : required,allowBlank : false,labelWidth : this.fieldDefaults.labelWidth, widthLimit : 55,fieldLabel : '当前页图标'}
	,{xtype : 'imagefield',name : 'bean.imgUrl',afterLabelTextTpl : required,allowBlank : false,labelWidth : this.fieldDefaults.labelWidth, widthLimit : 55,fieldLabel : '非当前图标'}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.pageType',
					fieldLabel : '页面类型',
					store : Ext.create('mvc.combo.wa.WaOActPageType'),
					value : 1
				})
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