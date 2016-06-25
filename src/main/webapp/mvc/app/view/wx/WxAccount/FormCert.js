Ext.define('mvc.view.wx.WxAccount.FormCert',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
url : base_path+'/wx_WxAccount_uploadCert',
fieldDefaults : {
	labelWidth : 150,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
	var formFlds = [];
	formFlds.push(
		{xtype : 'textfield',name : 'bean.accountName', fieldLabel : '名称', readOnly : true},
		{xtype : 'fileurlfield', name : "bean.mchPayCert", fieldLabel : '支付证书'}, 
 		{xtype : 'numberfield',name : 'bean.rowVersion',value : 0,afterLabelTextTpl : required,allowBlank : false,fieldLabel : '版本',hidden : true,allowDecimals : false},
		{xtype : 'hiddenfield',name : 'bean.pkey'}
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