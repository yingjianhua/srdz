Ext.define('mvc.view.wa.WaActVote.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActVote_',
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
	,{xtype : 'datefield',name : 'bean.entryStartTime',value : 'Env.getTranBeginTime()',fieldLabel : '报名开始时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'datefield',name : 'bean.entryEndTime',value : 'Env.getTranBeginTime()',fieldLabel : '报名结束时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'datefield',name : 'bean.actStartTime',value : 'Env.getTranBeginTime()',fieldLabel : '活动开始时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{xtype : 'datefield',name : 'bean.actEndTime',value : 'Env.getTranBeginTime()',fieldLabel : '活动结束时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
	,{
		xtype : 'beantrigger',
		name : 'bean.actTemplate',
		fieldLabel : '页面模板',
		bean : 'WaActTemplate',
		beanType : 'wa',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{
		xtype : 'beantrigger',
		name : 'bean.actConfig',
		fieldLabel : '参数设置',
		bean : 'WaActConfig',
		beanType : 'wa',
		emptyText : form_empty_text,
		afterLabelTextTpl : required,
		allowBlank : false
	},{
		xtype : 'beantrigger',
		name : 'bean.voteConfig',
		fieldLabel : '投票设置',
		bean : 'WaActVoteConfig',
		beanType : 'wa',
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