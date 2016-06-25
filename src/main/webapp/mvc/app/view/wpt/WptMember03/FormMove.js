Ext.define('mvc.view.wx.WxUser.FormMove',{
extend : 'Ext.form.Panel',
requires : ['Ext.ux.DataTip'],
layout : 'form',
border : false,
frame : false,
insFlag : true,
bodyPadding : '5 5 5 5',
pkeys : "",
url : base_path+'/wx_WxUser_move',
fieldDefaults : {
	labelWidth : 100,
	labelStyle : 'font-weight : bold'
},
initComponent : function(){
			var formFlds = [];
			formFlds.push
(
		mvc.Tools.crtComboTrigger(false,'wx_WxUserGroup','',{
			name : 'bean.userGroup',
			fieldLabel : '用户分组'
		})
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