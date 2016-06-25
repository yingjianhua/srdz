Ext.define('mvc.view.wax.WaxLady.ListMain',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '姓名',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '手机',width : 100,dataIndex : 'bean.mobile',sortable : true}
	,{text : '关注用户',width : 100,dataIndex : 'bean.wxUser',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wxex',mn : 'view.wx.WxUser.List'}
	];
		this.store=Ext.create('mvc.store.wax.WaxLady');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
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