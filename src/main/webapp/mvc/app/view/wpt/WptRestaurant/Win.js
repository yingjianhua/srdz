Ext.define('mvc.view.wpt.WptRestaurant.Win',{
extend : 'Ext.window.Window',
width : 860,
resizable : false,
modal : true,
iconCls : 'app-icon',
pkeyFlag : true,
insFlag : true,
layout: {
	type: "column"
},
initComponent : function(){
	this.items=[
	            Ext.create("mvc.view.wpt.WptRestaurant.Form", {insFlag:this.insFlag, width:540}),
	            Ext.create("mvc.view.wpt.WptRestaurant.ListForm", {height:481, columnWidth:0.6, scroll:"vertical"}),
	            Ext.create("mvc.view.wpt.WptRestaurant.ListFormWptBanquet", {height:481, columnWidth:0.4, scroll:"vertical"})
	            ]
	this.buttonAlign = 'right',
	this.buttons =[{
		text : '重置',
		iconCls : 'win-refresh-icon',
		scope : this,
		handler : this.onReset
	},{
		text : '关闭',
		iconCls : 'win-close-icon',
		scope : this,
		handler : this.onClose
	},{
		text : '保存',
		iconCls : 'win-save-icon',
		scope : this,
		handler : this.onSave
	}];
		this.callParent(arguments);
		this.addEvents('create');
		this.form = this.items.items[0];
		this.resLine = this.items.items[1];
		this.banquetLine = this.items.items[2];
},
setActiveRecord : function(record){
		this.form.activeRecord = record;
		if (record || this.form.activeRecord) {
			this.form.getForm().loadRecord(record);
			this.resLine.store.filter([{'id':'filter','property':'restaurant','value':record.get('bean.pkey')}]);
		} else {
			this.form.getForm().reset();
			this.resLine.store.removeAll();
		}
},
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.resLine.cellEditing.cancelEdit();
		this.close();
},
onSave : function(){
		var form = this.form.getForm();
		if (form.isValid()) {
			form.submit({
				url : this.form.url,
				submitEmptyText: false,
				type : 'ajax',
				params : mvc.Tools.storeValues(this.resLine.store,{insFlag : this.insFlag}),
				success : function(form, action) {
					this.fireEvent('create', this, action.result);
					this.onClose();
				},
				failure : mvc.Tools.formFailure(),
				waitTitle : wait_title,
				waitMsg : wait_msg,
				scope : this
			});
		}
}
});