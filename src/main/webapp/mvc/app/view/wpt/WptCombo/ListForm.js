Ext.define('mvc.view.wpt.WptCombo.ListForm',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
viewConfig: {
    plugins: {
        ptype: 'gridviewdragdrop',
        dropGroup: 'firstGridDDGroup',
        enableDrag: false
    },
    listeners: {
    	beforedrop: function(node, data, overModel, dropPosition, dropHandlers) {
    		this.panel.onIns(data.records[0]);
    		dropHandlers.cancelDrop();
        }
    }
},
initComponent : function(){
		this.columns =[
			{text : '菜品',width : 100,dataIndex : 'menu.pkey',sortable : true,renderer : mvc.Tools.beanRenderer()},
			{text : '价格',width : 100,dataIndex : 'price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 2}},
			{text : '排序',width : 100,dataIndex : 'sort',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}},
			{text: '操作',width: 50,xtype: 'actioncolumn',  
	            items: [{  
	                icon: '../images/mvc/del.gif',  
	                tooltip: '删除',  
	                handler: function(grid, rowIndex, colIndex) {
	                	var store = grid.getStore();
	                	store.removeAt(rowIndex)
	                }
	            }]
			}
		];
		this.store=Ext.create('mvc.store.wpt.WptComboLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(origModel){
	console.log(this)
	var store = this.getStore();
	var origData = origModel.data;
	var targData = {
			"menu":{
				"pkey":origData["pkey"]+bean_split+origData["name"],
			},
			"price":origData["price"]
	};
	store.insert(0, Ext.create("mvc.model.wpt.WptComboLine", targData));
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			this.getStore().remove(selection);
		}
}
});