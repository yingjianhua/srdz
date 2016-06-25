Ext.define('mvc.view.wa.WaAct.WinCheck',{
extend : 'Ext.window.Window',
width : 700,
resizable : false,
modal : true,
iconCls : 'app-icon',
pkeyFlag : true,
insFlag : true,
listUrl :null,
initComponent : function(){
		this.items =[{
		xtype : Ext.create(this.listUrl,{height : 350,border : false })
	}];
		/*this.buttonAlign = 'right',
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
	}];*/
		this.callParent(arguments);
		this.addEvents('create');
		this.lineTable = this.items.items[0];
},
setActiveRecord : function(record){
        this.lineTable.store.filter([{'id':'filter','property':'act','value':record[0].data['bean.pkey']}]);
},

onClose : function(){
		this.lineTable.cellEditing.cancelEdit();
		this.close();
},

});