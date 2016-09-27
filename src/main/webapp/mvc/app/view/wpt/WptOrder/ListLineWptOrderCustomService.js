Ext.define('mvc.view.wpt.WptOrder.ListLineWptOrderCustomService',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '名称',width : 100,dataIndex : 'name',sortable : true}
		,{text : '价格',width : 100,dataIndex : 'price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	];
		this.store=Ext.create('mvc.store.wpt.WptOrderCustomService');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		xtype : 'pagingtoolbar',
		store : this.store,
		dock : 'bottom',
		displayInfo : true,
		displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
		emptyMsg : '没有数据',
		items : [{
				xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})
			}]
	}];
		this.callParent(arguments);}
});