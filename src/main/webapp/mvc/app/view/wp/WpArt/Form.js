Ext.define('mvc.view.wp.WpArt.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wp_WpArt_',
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
		name : 'bean.artShow',
		fieldLabel : '图文秀',
		bean : 'WpArtShow',
		beanType : 'wp',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'textfield',name : 'bean.title',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '标题'}
	,{xtype : 'imagefield',name : 'bean.imgUrl',afterLabelTextTpl : required,allowBlank : false, labelWidth : this.fieldDefaults.labelWidth,fieldLabel : '图片'}
	,{xtype : 'textfield',name : 'bean.url',fieldLabel : '链接'}
	,{xtype : 'textfield',name : 'bean.description',fieldLabel : '描述'}
	,{xtype : 'datefield',name : 'bean.date',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '时间',format : 'Y-m-d'}
	,
		mvc.Tools.crtComboTrigger(false,'wp_WpArtTheme','',{
					name : 'bean.theme',
					fieldLabel : '主题'
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.bsn',
		fieldLabel : '商家',
		bean : 'WpBsn',
		beanType : 'wp',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
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