Ext.define('mvc.view.wa.WaActVoteConfig.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wa_WaActVoteConfig_',
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
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.entryAppr',
					fieldLabel : '报名审核',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 0
				})
	,{xtype : 'numberfield',name : 'bean.picLimit',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '报名图片限制',allowDecimals : false}
	,
		mvc.Tools.crtComboForm(false,{
					name : 'bean.showRanking',
					fieldLabel : '详情页显示排名',
					store : Ext.create('mvc.combo.sys.SysOYn'),
					value : 1
				})
	,{xtype : 'numberfield',name : 'bean.picWidth',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '图片宽度限制',allowDecimals : false}
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