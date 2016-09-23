Ext.define('mvc.view.wpt.WptOrder.ListLineWptOrderPayJournal',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns = [{text : '支付',width : 100,dataIndex : 'income',sortable : true, renderer:function(v){if(v)return"是";return"否";}}
		,{text : '商户订单号',width : 100,dataIndex : 'outTradeNo',sortable : true}
		,{text : '商户退款单号',width : 100,dataIndex : 'outRefundNo',sortable : true}
		,{text : '价格',width : 100,dataIndex : 'price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
		,{text : '创建时间',width : 100,dataIndex : 'createTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
		,{text : '支付成功时间',width : 100,dataIndex : 'payTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
		,{text : '退款成功时间',width : 100,dataIndex : 'refundTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
		,{text : '支付途径',width : 100,dataIndex : 'payChannel',sortable : true,renderer : mvc.Tools.optRenderer('wpt','Wpt','OPayJournal')}
		,{text : '收款人',width : 100,dataIndex : 'userId',sortable : true}
	];
		this.store=Ext.create('mvc.store.wpt.WptOrderPayJournal');
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