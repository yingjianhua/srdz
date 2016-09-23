Ext.define('mvc.view.wpt.WptPetitionCity.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WptPetitionCity',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('doEnabled') != -1)
mainActs.push({
		text : '启用',
		iconCls : 'doEnabled-icon',
		itemId : this.oldId+'doEnabled',
		scope : this,
		handler : this.onDoEnabled,
		disabled : this.lock
	});
		if (this.roles.indexOf('unEnabled') != -1)
mainActs.push({
		text : '停用',
		iconCls : 'unEnabled-icon',
		itemId : this.oldId+'unEnabled',
		scope : this,
		handler : this.onUnEnabled,
		disabled : this.lock
	});
this.columns = [{text : '城市',width : 100,dataIndex : 'name',sortable : true}
	,{text : '数量',width : 100,dataIndex : 'count',sortable : true}
	,{text : '启用标志',width : 75,dataIndex : 'enabled',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wpt.WptPetitionCity'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '城市：'
			},{
				xtype : 'textfield',
				name : 'name'
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
		if (this.roles.indexOf('doEnabled') != -1)
			this.down('#'+this.oldId+'doEnabled').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('unEnabled') != -1)
			this.down('#'+this.oldId+'unEnabled').setDisabled(selected.length === 0);	
}
},
onDoEnabled : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection) {
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, '您确认要启用所选的记录吗?',
					function(btn) {
						if (btn != 'yes')
							return;
						var arr = new Array();
						var arrv = new Array();
						for (var i = 0; i < selection.length; i++) {
							arr.push(selection[i].get('pkey'));
							arrv.push(selection[i].get(BEAN_VERSION));
						}
						Ext.Ajax.request({
							url : base_path + '/wpt/resource/petitionCity_doEnabled',
							params: {
								"bean.pkey" : selection[0].get('pkey'),
								"bean.rowVersion" : selection[0].get(BEAN_VERSION)
							},
							success : function(response, options) {
								var result = Ext.decode(response.responseText);
								if (result.success) {
									me.getStore().reload();
									me.getView().deselect(me.getView().getSelectionModel().getSelection());
									Ext.example.msg(msg_title, msg_submit);
								} else {
									Ext.MessageBox.show({
										title : msg_title,
										msg : result.msg,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							}
						});
					});
		}
},
onUnEnabled : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection) {
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, '您确认要停用所选的记录吗?',
					function(btn) {
						if (btn != 'yes')
							return;
						var arr = new Array();
						var arrv = new Array();
						for (var i = 0; i < selection.length; i++) {
							arr.push(selection[i].get('pkey'));
							arrv.push(selection[i].get(BEAN_VERSION));
						}
						Ext.Ajax.request({
							url : base_path + '/wpt/resource/petitionCity_unEnabled',
							params: {
								"bean.pkey" : selection[0].get('pkey'),
								"bean.rowVersion" : selection[0].get(BEAN_VERSION)
							},
							success : function(response, options) {
								var result = Ext.decode(response.responseText);
								if (result.success) {
									me.getStore().reload();
									me.getView().deselect(me.getView().getSelectionModel().getSelection());
									Ext.example.msg(msg_title, msg_submit);
								} else {
									Ext.MessageBox.show({
										title : msg_title,
										msg : result.msg,
										buttons : Ext.MessageBox.OK,
										icon : Ext.MessageBox.ERROR
									});
								}
							}
						});
					});
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
		var win = Ext.create('mvc.view.wpt.WptPetitionCity.WinSearch',{
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