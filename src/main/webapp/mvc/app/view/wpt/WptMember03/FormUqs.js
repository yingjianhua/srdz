Ext.define('mvc.view.wx.WxUser.FormUqs',{
	extend : 'Ext.form.Panel',
	requires : ['Ext.ux.DataTip'],
	layout : 'form',
	border : false,
	frame : false,
	insFlag : true,
	bodyPadding : '5 5 5 5',
	pkeys : "",
	url : base_path+'/wx_WxUserQrcodeSet_set',
	fieldDefaults : {
		labelWidth : 100,
		labelStyle : 'font-weight : bold'
	},
	initComponent : function(){
		var formFlds = [];
		formFlds.push(
			{xtype : 'numberfield',name : 'bean.validityPeriod',fieldLabel : '有效期（天）',afterLabelTextTpl : required,allowBlank : false,allowDecimals : false}
		);
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