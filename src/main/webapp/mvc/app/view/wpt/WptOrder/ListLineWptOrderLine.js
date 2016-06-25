Ext.define('mvc.view.wpt.WptOrder.ListLineWptOrderLine',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '描述',width : 100,dataIndex : 'bean.des',sortable : true}
	,{text : '价格',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '数量',width : 100,dataIndex : 'bean.number',sortable : true}
	];
		this.store=Ext.create('mvc.store.wpt.WptOrderLine');
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