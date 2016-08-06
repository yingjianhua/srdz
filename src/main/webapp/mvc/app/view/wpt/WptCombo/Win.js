Ext.define('mvc.view.wpt.WptCombo.Win',{
extend : 'Ext.window.Window',
width : 1140,
resizable : false,
modal : true,
iconCls : 'app-icon',
pkeyFlag : true,
insFlag : true,
layout: 'column',
 	initComponent : function(){
	this.items =[
			Ext.create('mvc.view.wpt.WptCombo.Form', {insFlag : this.insFlag, width: 540, height: 355}),
			Ext.create('mvc.view.wpt.WptCombo.ListForm', {title:"套餐菜品", height: 355, columnWidth:0.5, scroll:"vertical"}),
			Ext.create('mvc.view.wpt.WptCombo.ListFormWptRestaurantMenu', {title:"餐厅菜品", columnWidth:0.5, scroll:"vertical"})
	];
	this.buttonAlign = 'right',
	this.buttons = [];
	if(this.insFlag) {
		this.buttons.push({
			text : '新增',
			iconCls : 'add-icon',
			scope : this,
			handler : this.onIns
		});
	}
	this.buttons.push({
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
	});
		this.callParent(arguments);
		this.addEvents('create');
		this.form = this.items.items[0];
		this.lineTable = this.items.items[1];
		console.log(this.form)
		console.log(this.lineTable)
},
setActiveRecord : function(record){
		this.form.activeRecord = record;
		if (record || this.form.activeRecord) {
			this.form.getForm().loadRecord(record);
			this.lineTable.store.filter([{'id':'filter','property':'combo','value':record.get('bean.pkey')}]);
		} else {
			this.form.getForm().reset();
			this.lineTable.store.removeAll();
		}
},
onIns : function() {
	var form = this.form.getForm();
	if (form.isValid()) {
		form.submit({
			url : this.form.url,
			submitEmptyText: false,
			type : 'ajax',
			params : mvc.Tools.storeValues(this.lineTable.store,{insFlag : this.insFlag}),
			success : function(form, action) {
				this.fireEvent('create', this, action.result);
				this.lineTable.getStore().removeAll();
			},
			failure : mvc.Tools.formFailure(),
			waitTitle : wait_title,
			waitMsg : wait_msg,
			scope : this
		});
	}
},
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.lineTable.cellEditing.cancelEdit();
		this.close();
},
onSave : function(){
		var form = this.form.getForm();
		if (form.isValid()) {
			form.submit({
				url : this.form.url,
				submitEmptyText: false,
				type : 'ajax',
				params : mvc.Tools.storeValues(this.lineTable.store,{insFlag : this.insFlag}),
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