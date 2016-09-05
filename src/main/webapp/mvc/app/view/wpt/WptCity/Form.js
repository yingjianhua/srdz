Ext.define('mvc.view.wpt.WptCity.Form',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wpt/resource/city_',
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
({xtype : 'textfield',name : 'bean.name', id : "name",afterLabelTextTpl : required,allowBlank : false,fieldLabel : '城市'}
	,{xtype : 'numberfield',name : 'bean.rowVersion',id:"rowVersion",value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : false,allowDecimals : false}
	,{
		xtype : 'hiddenfield',
		name : 'bean.pkey',
		id : "pkey"
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