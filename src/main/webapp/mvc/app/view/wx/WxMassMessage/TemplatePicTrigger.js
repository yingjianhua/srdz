Ext.define('mvc.view.wx.WxMassMessage.TemplatePicTrigger',{
extend : 'Ext.window.Window',
width : 700,
height : 400,
layout : 'fit',
title : '选择器-图文素材',
pre:null,
next:null,
resizable : true,
modal : true,
border : false,
url : base_path + '/wx_WxMassMessage_send',
initComponent : function(){
	var list = Ext.create('mvc.view.wx.WxMassMessage.TemplatePicList');
	list.on('onIns', this.onIns, this);
	list.on('trigger', this.onTrigger, this);
	this.items ={
		anchor : '100%',
		plain : true,
		xtype : list
	};
	this.addEvents('onSendCallback');
	this.callParent(arguments);
},
onTrigger : function(pkey, params){
	var me = this;
	if(!me.next) {
		me.next = Ext.create('mvc.view.wx.WxMassMessage.TemplatePreview',{
			pkey : pkey	
		});
		me.next.on('onReturn',this.onReturn,this);
		me.next.on('onUpd',this.onUpd,this);
		me.next.on('onSend',this.onSend,this);
	}
	me.next.onReload(pkey);
	me.next.show();
	this.hide();
},
onIns : function() {
	this.close();
	var tab = mvc.Tools.onCreateTab("wxtemp","view.wm.WmNews.List","pkey","0");
	tab.onIns();
},
onReturn : function() {
	this.next.hide();
	this.show();
},
onUpd : function(pkey) {
	this.next.close();
	this.close();
	var tab = mvc.Tools.onCreateTab("wxtemp","view.wm.WmNews.List","pkey",pkey);
	var model = Ext.create("mvc.model.wm.WmNews",{"bean.pkey":pkey});
	tab.onUpdWin(model);
},
onSuccess : function(result) {
	this.next.close();
	this.close();
	var model = Ext.create("mvc.model.wx.WxMassMessage",result);
	this.fireEvent("onSendCallback",model);
},
onSend : function(pkey){
	var me = this;
	Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg, 
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : me.url+"?pkey="+pkey,
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.onSuccess(result);
							Ext.example.msg(msg_title, "微信正在发送");
						}else{
							Ext.MessageBox.show({
								title : msg_title, 
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
	}
});