Ext.define('mvc.view.wx.WxUser.WinMove',{
extend : 'Ext.window.Window',
width : 400,
layout : 'fit',
form : null,
resizable : true,
modal : true,
pkeys : "",
rowVersions : "",
iconCls : 'app-icon',
insFlag : true,
initComponent : function(){
		this.items ={
	anchor : '100%',
	plain : true,
	xtype : Ext.create('mvc.view.wx.WxUser.FormMove')
};
		this.buttonAlign = 'right',
this.buttons =[{
		text : '重置',
		scope : this,
		iconCls : 'win-refresh-icon',
		handler : this.onReset
	},{
		text : '关闭',
		scope : this,
		iconCls : 'win-close-icon',
		handler : this.onClose
	},{
		text : '保存',
		scope : this,
		iconCls : 'win-save-icon',
		handler : this.onSave
	}];
		this.callParent(arguments);
		this.form = this.items.items[0];
},
setActiveRecord : function(record){
		this.form.activeRecord = record;
},
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.close();
},
onSave : function(){
		var me = this;
		var form = this.form.getForm();
		if (form.isValid()) {
			form.submit({
				url : this.form.url+'?pkeys='+me.pkeys+'&rowVersions='+me.rowVersions,
				submitEmptyText: false,
				type : 'ajax',
				success : function(form, action) {
					Ext.each(me.form.activeRecord,function(select) {
						select.data["bean.userGroup"]=action.result["bean.pkey"]+bean_split+action.result["bean.name"];
						select.commit();
					})
					this.close();
				},
				failure : mvc.Tools.formFailure(),
				waitTitle : wait_title,
				waitMsg : wait_msg,
				scope : this
			});
		}
}
});