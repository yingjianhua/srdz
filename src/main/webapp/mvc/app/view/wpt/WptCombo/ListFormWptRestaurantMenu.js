Ext.define('mvc.view.wpt.WptCombo.ListFormWptRestaurantMenu',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
viewConfig: {
    plugins: {
         ptype: 'gridviewdragdrop', 
         dragGroup: 'firstGridDDGroup', 
    },
	listeners: {
		drop: function(node, data, dropRec, dropPosition) { 
			var dropOn = dropRec ? ' ' + dropPosition + ' ' + dropRec.get('name') : ' on empty view';
			console.log(node)
			console.log(data)
			console.log(dropRec)
			console.log(dropPosition)
		} 
	}
},
listeners:{
	itemdblclick: function(grid, record, item, index, e, eOpts ){
		grid.up("window").comboLine.onIns(record);
	}
},
initComponent : function(){
	this.columns =[
           {text : '菜品',width : 100,dataIndex : 'bean.name',sortable : true},
           {text : '价格',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	];
		this.store=Ext.create('mvc.store.wpt.WptMenu');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'query';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
}
});