Ext.define('mvc.view.sys.SysCtype.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_SysCtype',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('edit') != -1)
mainActs.push({
		text : '编辑',
		iconCls : 'edit-icon',
		itemId : this.oldId+'edit',
		scope : this,
		handler : this.onEdit,
		disabled : this.lock
	});
this.columns = [{text : '编号',width : 100,dataIndex : 'bean.pkey',sortable : true}
	,{text : '名称',width : 100,dataIndex : 'bean.ctypeName',sortable : true}
	,{text : '描述',width : 100,dataIndex : 'bean.ctypeDes',sortable : true}
	,{text : '代码长度',width : 100,dataIndex : 'bean.ctypeLen',sortable : true}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.sys.SysCtype'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '名称：'
			},{
				xtype : 'textfield',
				name : 'ctypeName'
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
		if (this.roles.indexOf('edit') != -1)
			this.down('#'+this.oldId+'edit').setDisabled(selected.length === 0);	
}
},
onEdit : function(){
	var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			var win = Ext.create('mvc.view.sys.SysCtype.WinEdit',{
				title : this.title+'['+selection.get('bean.ctypeName')+']>编辑',
				record : selection
			});
			win.show();
			win.loadData();
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
		var win = Ext.create('mvc.view.sys.SysCtype.WinSearch',{
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