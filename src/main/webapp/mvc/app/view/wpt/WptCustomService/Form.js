Ext.define('mvc.view.wpt.WptCustomService.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt/resource/customService_',
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
({xtype : 'textfield',name : 'bean.name',id : 'name',afterLabelTextTpl : required,allowBlank : false,fieldLabel : '名称'}
,{xtype : 'imagefield',name : 'bean.icon',id : 'icon',afterLabelTextTpl : required,allowBlank : false, labelWidth : this.fieldDefaults.labelWidth,fieldLabel : '图标'}
	,{xtype : 'numberfield',name : 'bean.price',id : 'price',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '金额',decimalPrecision : 2}
	,{xtype : 'numberfield',name : 'bean.rowVersion',id : 'rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false}
	,{xtype : 'hiddenfield',name : 'bean.pkey',id : 'pkey'
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