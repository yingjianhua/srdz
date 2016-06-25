Ext.define('mvc.view.wa.WaActVoteEntry.WinImg',{
extend : 'Ext.window.Window',
title : "图片选择器",
width : 300,
resizable : false,
modal : true,
iconCls : 'app-icon',
pkeyFlag : true,
insFlag : true,
initComponent : function(){
	this.items =[{
		xtype : "form",
		anchor : '100%',
		plain : true,
		bodyPadding : '5 5 5 5',
		layout : {
			type : 'vbox',
			align : 'stretch'
		},
		fieldDefaults : {
			labelWidth : 100,
			labelStyle : 'font-weight : bold'
		},
		items : [{
			xtype : 'imagefield',
			name : 'bean.photoUrl',
			labelWidth : 100,
			fieldLabel : '照片',
			widthLimit : 1080
		}]
		
	}];
		this.buttonAlign = 'right',
this.buttons =[{
		text : '关闭',
		iconCls : 'win-close-icon',
		scope : this,
		handler : this.onClose
	},{
		text : '确定',
		iconCls : 'win-save-icon',
		scope : this,
		handler : this.onTrigger
	}];
		this.callParent(arguments);
		this.addEvents('create');
		this.form = this.items.items[0];
		this.field = this.form.items.items[0];
},
setActiveRecord : function(record){
		this.form.activeRecord = record;
		console.log(record)
		if (record || this.form.activeRecord) {
			this.form.getForm().loadRecord(record);
		} else {
			this.form.getForm().reset();
		}
},
onClose : function(){
		this.close();
},
onTrigger : function(data, params){
	this.fireEvent('trigger', this.field.text.getValue(), params);
	this.close();
}
});