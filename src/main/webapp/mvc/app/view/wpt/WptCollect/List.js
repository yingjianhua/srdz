Ext.define('mvc.view.wpt.WptCollect.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WptCollect',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];this.columns = [{text : '关注用户',width : 100,dataIndex : 'wxuser',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wxex',mn : 'view.wx.WxUser.List'}
	,{text : '头条',width : 100,dataIndex : 'headline.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptTop.List'}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wpt.WptCollect'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '头条：'
			},{
				xtype : 'beantrigger',
				name : 'headline',
				bean : 'WptTop',
				beanType : 'wpt',
				emptyText : form_empty_text
			},'',{
				xtype : 'label',
				text : '关注用户：'
			},{
				xtype : 'beantrigger',
				name : 'wxuser',
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
		var win = Ext.create('mvc.view.wpt.WptCollect.WinSearch',{
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