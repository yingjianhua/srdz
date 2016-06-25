Ext.define('mvc.view.wa.WaActVoteEntry.ListForm',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
initComponent : function(){
	var me = this;
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
		this.tbar = mainActs;		
		this.columns =[{
			text : '照片',width : 100,dataIndex : 'bean.photoUrl',sortable : true, 
			editor : Ext.create("mvc.view.wa.WaActVoteEntry.TriggerImgCell", {grid:me, beanName:"bean.photoUrl"}),
			renderer : function(v) {return "<img src='../"+v+"' height='80px'/>"}
		},{
			text : '排序',width : 100,dataIndex : 'bean.sort',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	];
		this.store=Ext.create('mvc.store.wa.WaActVotePhoto');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.wa.WaActVotePhoto');
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