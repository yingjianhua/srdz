Ext.define('mvc.view.wa.WaActVoteRecord.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActVoteRecord_',
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
		mvc.Tools.crtComboTrigger(false,'wa_WaActVote','',{
					name : 'bean.act',
					fieldLabel : '活动'
				})
	,
		mvc.Tools.crtComboTrigger(false,'wa_WaActVoteEntry','',{
					name : 'bean.entryRecord',
					fieldLabel : '参赛者'
				})
	,
		mvc.Tools.crtComboTrigger(false,'wx_WxUser','',{
					name : 'bean.wxUser',
					fieldLabel : '投票者'
				})
	,{xtype : 'datefield',name : 'bean.voteTime',value : 'Env.getTranBeginTime()',fieldLabel : '投票时间',afterLabelTextTpl : required,allowBlank : false,format : 'Y-m-d H:i:s'}
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