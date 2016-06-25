Ext.define('mvc.view.wa.WaActVoteBanner.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActVoteBanner_',
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
	}   ,{xtype : 'imagefield',name : 'bean.picUrl',afterLabelTextTpl : required,allowBlank : false, labelWidth : this.fieldDefaults.labelWidth, widthLimit : 750,fieldLabel : '图片'}
	,{xtype : 'textfield',name : 'bean.url',fieldLabel : '链接'}
	,{xtype : 'textfield',name : 'bean.description',fieldLabel : '描述'}
	,{xtype : 'numberfield',name : 'bean.sort',value : 0,fieldLabel : '排序',allowDecimals : false}
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