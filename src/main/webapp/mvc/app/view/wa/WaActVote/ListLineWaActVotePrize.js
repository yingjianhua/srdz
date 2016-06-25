Ext.define('mvc.view.wa.WaActVote.ListLineWaActVotePrize',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '奖项',width : 100,dataIndex : 'bean.prizeitem',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wa',mn : 'view.wa.WaActItem.List'}
	,{text : '奖品',width : 100,dataIndex : 'bean.prize',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wa',mn : 'view.wa.WaActPrize.List'}
	,{text : '数量',width : 100,dataIndex : 'bean.amount',sortable : true}
	,{text : '单位',width : 100,dataIndex : 'bean.unit',sortable : true}
	,{text : '排序',width : 100,dataIndex : 'bean.sort',sortable : true}
	];
		this.store=Ext.create('mvc.store.wa.WaActVotePrize');
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