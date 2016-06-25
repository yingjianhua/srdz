Ext.define('mvc.view.wa.WaActVoteEntry.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActVoteEntry_',
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
		fieldLabel : '活动',
		bean : 'WaActVote',
		beanType : 'wa',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{
		xtype : 'beantrigger',
		name : 'bean.wxUser',
		fieldLabel : '参赛者',
		bean : 'WxUser',
		beanType : 'wx',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{xtype : 'textfield',name : 'bean.namePerson',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '姓名'}
	,{xtype : 'textfield',name : 'bean.phonePerson',fieldLabel : '电话'}
	,{xtype : 'textfield',name : 'bean.des',fieldLabel : '描述'}
	,{xtype : 'numberfield',name : 'bean.voteCount',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '投票数',allowDecimals : false}
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