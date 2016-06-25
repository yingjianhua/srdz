Ext.define('mvc.view.wa.WaActHomeTemplate.ListLineWaActHomeTemplateBarnner',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '图片',width : 100,dataIndex : 'bean.picUrl',sortable : true}
	,{text : '链接',width : 100,dataIndex : 'bean.url',sortable : true}
	,{text : '描述',width : 100,dataIndex : 'bean.description',sortable : true}
	];
		this.store=Ext.create('mvc.store.wa.WaActHomeTemplateBarnner');
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