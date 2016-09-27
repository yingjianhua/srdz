Ext.define('mvc.view.wpt.WptRestaurant.ListLineWptRestaurantCase',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
	this.columns = [{text : '餐厅',width : 100,dataIndex : 'restaurant.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptRestaurant.List'}
	,{text : '图片',width : 100,dataIndex : 'imgUrl',sortable : true, renderer : function(v) { return "<img src='../" + v + "' height='70px'>";}}
	,{text : '描述',width : 100,dataIndex : 'des',sortable : true}
	];
		this.store=Ext.create('mvc.store.wpt.WptRestaurantCase');
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