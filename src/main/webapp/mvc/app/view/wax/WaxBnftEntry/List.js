Ext.define('mvc.view.wax.WaxBnftEntry.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WaxBnftEntry',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];this.columns = [{text : '福利活动',width : 100,dataIndex : 'bean.bnft',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wa',mn : 'view.wax.WaxBnft.List'}
	,{text : '姓名',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '手机',width : 100,dataIndex : 'bean.mobile',sortable : true}
	,{text : '截图',width : 100,dataIndex : 'bean.imgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='90px' height='70px'>"}}
	,{text : '关注用户',width : 100,dataIndex : 'bean.wxUser',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wxex',mn : 'view.wx.WxUser.List'}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wax.WaxBnftEntry'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({celldblclick:function(cell, td, cellIndex, record) {
			if(cellIndex == 4) {
				var win = Ext.create("Ext.window.Window", {
					title : "预览",
					width : "100%",
					height : "100%",
					layout : "border",
					items : [{
						region : "center",
						html : "<img src='../"+record.get("bean.imgUrl")+"'>",
					}]
				})
				win.show();
			}
		}})
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '福利活动：'
			},{
				xtype : 'beantrigger',
				name : 'bnft',
				bean : 'WaxBnft',
				beanType : 'wax',
				emptyText : form_empty_text
			},'',{
				xtype : 'label',
				text : '姓名：'
			},{
				xtype : 'textfield',
				name : 'name'
			},'',{
				xtype : 'label',
				text : '手机：'
			},{
				xtype : 'textfield',
				name : 'mobile'
			},'',{
				xtype : 'label',
				text : '关注用户：'
			},{
				xtype : 'beantrigger',
				name : 'wxUser',
				bean : 'WxUser',
				beanType : 'wx',
				emptyText : form_empty_text
			},'',{
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			},{
				xtype : 'splitbutton',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				menu : [{text:'高级搜索',iconCls : 'win-ok-icon', scope : this,handler: this.onSearchAdv}]
			}]
	},{
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
		this.callParent(arguments);		mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'),this);},
listeners : {
	selectionchange : function(selModel, selected){
}
},
onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.wax.WaxBnftEntry.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
}
});