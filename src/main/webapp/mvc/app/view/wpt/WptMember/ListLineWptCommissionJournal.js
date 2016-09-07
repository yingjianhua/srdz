Ext.define('mvc.view.wpt.WptMember.ListLineWptCommissionJournal',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [
		                {text : '订单编号',width : 100,dataIndex : 'orderid',sortable : true},
		            	{text : '订单状态',width : 60,dataIndex : 'status',sortable : true,renderer : mvc.Tools.optRenderer('wpt','Wpt','OStatus')},
		                {text : '订单金额',width : 100,dataIndex : 'price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'},
		                {text : '佣金',width : 100,dataIndex : 'commission',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'},
		                {text : '产生时间',width : 140,dataIndex : 'createTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
	];
		this.store=Ext.create('mvc.store.wpt.WptCommissionJournal');
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