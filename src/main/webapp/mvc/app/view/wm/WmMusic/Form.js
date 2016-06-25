Ext.define('mvc.view.wm.WmMusic.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wm_WmMusic_',
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
({xtype : 'textfield',name : 'bean.title',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '音乐标题'}
	,{xtype : 'textfield',name : 'bean.description',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '音乐描述'}
	,{xtype : 'fileurlfield',name : 'bean.musicUrl',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '音乐'}
	,{xtype : 'fileurlfield',name : 'bean.hqmusicUrl',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '高质量音乐'}
	,{xtype : 'imagefield',name : 'bean.thumbUrl',fieldLabel : '缩略图'}
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