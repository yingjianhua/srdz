Ext.define('mvc.view.wax.WaxBnft.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wax_WaxBnft_',
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
	,{xtype : 'imagefield',name : 'bean.imgUrl',fieldLabel : '首图'}
	,{xtype : 'textfield',name : 'bean.des',fieldLabel : '介绍'}
	,{
		xtype : 'beantrigger',
		name : 'bean.bsn',
		fieldLabel : '商家',
		bean : 'WpBsn',
		beanType : 'wp',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'textfield',name : 'bean.gift',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '奖品'}
	,{xtype : 'textfield',name : 'bean.getDate',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '领取时间'}
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