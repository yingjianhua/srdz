Ext.define('mvc.view.wx.WxMassMessage.WinPreview',{
extend : 'Ext.window.Window',
width : 400,
layout : 'fit',
form : null,
resizable : true,
modal : true,
iconCls : 'app-icon',
insFlag : true,
initComponent : function(){
		this.items =Ext.create('Ext.form.Panel',{
			layout : 'form',
			border : false,
			frame : false,
			bodyPadding : '5 5 5 5',
			url : base_path+'/wx_WxMassMessage_send',
			fieldDefaults : {
				labelWidth : 100,
				labelStyle : 'font-weight : bold'
			},
			items : [{
				layout : {
					type : 'vbox',
					align : 'stretch'
				},
				border : false,
				items : {xtype : 'beantrigger',name : 'wxUser', fieldLabel : '关注用户',bean : 'WxUser',beanType : 'wx'}
			}]
		});
		this.buttonAlign = 'right',
this.buttons =[{
		text : '关闭',
		scope : this,
		iconCls : 'win-close-icon',
		handler : this.onClose
	},{
		text : '确定',
		scope : this,
		iconCls : 'win-save-icon',
		handler : this.onSave
	}];
		this.callParent(arguments);
		this.addEvents('preview');
		this.form = this.items.items[0];
},
setActiveRecord : function(record){
		this.form.activeRecord = record;
		if (record || this.form.activeRecord) {
			this.form.getForm().loadRecord(record);
		} else {
			this.form.getForm().reset();
		}
},
onClose : function(){
		this.close();
},
onSave : function(){
	var record = this.form.down('[name=wxUser]').getValue();
	if(record) {
		this.fireEvent("preview",record.split(bean_split)[0],this);
	}
}
});