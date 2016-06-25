Ext.define('mvc.view.wa.WaActVotePrize.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActVotePrize_',
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
		name : 'bean.vote',
		fieldLabel : '投票活动',
		bean : 'WaActVote',
		beanType : 'wa',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},
		mvc.Tools.crtComboTrigger(false,'wa_WaActItem','',{
					name : 'bean.prizeitem',
					fieldLabel : '奖项'
				})
	,{
		xtype : 'beantrigger',
		name : 'bean.prize',
		fieldLabel : '奖品',
		bean : 'WaActPrize',
		beanType : 'wa',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'numberfield',name : 'bean.amount',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '数量',allowDecimals : false}
	,{xtype : 'textfield',name : 'bean.unit',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '单位'}
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