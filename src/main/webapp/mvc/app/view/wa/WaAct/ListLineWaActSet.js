Ext.define('mvc.view.wa.WaAct.ListLineWaActSet',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '奖项设置',width : 100,dataIndex : 'bean.actItem',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wa',mn : 'view.wa.WaActItem.List'}
	,{text : '奖品设置',width : 100,dataIndex : 'bean.actPrize',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wa',mn : 'view.wa.WaActPrize.List'}
	,{text : '奖品数量',width : 100,dataIndex : 'bean.actNum',sortable : true}
	];
		this.store=Ext.create('mvc.store.wa.WaActSet');
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