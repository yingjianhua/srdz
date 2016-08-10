Ext.define('mvc.view.wpt.WptRestaurant.ListFormWptBanquet',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
viewConfig: {
    plugins: {
         ptype: 'gridviewdragdrop', 
         dragGroup: 'firstGridDDGroup', 
    }
},
listeners:{
	itemdblclick: function(grid, record, item, index, e, eOpts ){
		grid.up("window").resLine.onIns(record);
	}
},
initComponent : function(){
		this.columns =[
		    {text : '宴会类型',width : 100,dataIndex : 'bean.name',sortable : true}
	];
		this.store=Ext.create('mvc.store.wpt.WptBanquet');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'query';
			var array = [{
				id:'flds',
				property: 'param',
				value: '1=1'
			}];
			this.getStore().filter(array);
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
}
});