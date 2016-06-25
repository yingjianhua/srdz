Ext.define('mvc.view.wa.WaActHomeTemplate.ListForm',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
initComponent : function(){
		var mainActs = [{
		text : '新增',
		iconCls : 'ins-icon',
		scope : this,
		handler : this.onIns
	},{
		text : '删除',
		iconCls : 'del-icon',
		scope : this,
		handler : this.onDel
	}];
		this.tbar = mainActs;		this.columns =[{text : '图片',width : 100,dataIndex : 'bean.picUrl',sortable : true,editor : {}
		}
	,{text : '链接',width : 100,dataIndex : 'bean.url',sortable : true,editor : {}
		}
	,{text : '描述',width : 100,dataIndex : 'bean.description',sortable : true,editor : {}
		}
	];
		this.store=Ext.create('mvc.store.wa.WaActHomeTemplateBarnner');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.wa.WaActHomeTemplateBarnner');
        this.store.insert(0, model);
        this.cellEditing.startEditByPosition({row: 0, column: 0});
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			this.getStore().remove(selection);
		}
}
});