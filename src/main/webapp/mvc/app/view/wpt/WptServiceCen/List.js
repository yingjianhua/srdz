Ext.define('mvc.view.wpt.WptServiceCen.List', {
	extend : 'Ext.grid.Panel',
	oldId : 'btn_WptServiceCen',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {
		selType : 'checkboxmodel'
	},
	viewConfig : {
		enableTextSelection : true
	},
	initComponent : function() {
		var mainActs = [];
		if (this.roles.indexOf('set') != -1)
			mainActs.push({
				text : '设置',
				iconCls : 'ins-icon',
				itemId : this.oldId + 'set',
				scope : this,
				handler : this.onSet
			});
		if (this.roles.indexOf('wxTip') != -1)
			mainActs.push({
					text : '微信提醒用户组',
					iconCls : 'upd-icon',
					itemId : this.oldId+'wxTip',
					scope : this,
					handler : this.onWxTip
				});
		this.columns = [ {
			text : '客服电话',
			width : 100,
			dataIndex : 'bean.mobile',
			sortable : true
		}, {
			text : '二维码',
			width : 90,
			dataIndex : 'bean.qrcode',
			sortable : true,
			renderer : function(v) {
				return "<img src='../" + v + "' height='70px'>"
			}
		},
		{
			text : '短信提醒',
			width : 100,
			dataIndex : 'bean.smsTips',
			sortable : true
		}
		];
		if (mainActs.length > 0)
			this.tbar = mainActs;
		this.store = Ext.create('mvc.store.wpt.WptServiceCen');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({
			cellclick : mvc.Tools.onCellclick
		});
		this.callParent(arguments);
		this.listeners = {
			afterrender : this.onSearch
		}
		mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'), this);
	},
	listeners : {},
	onRecord : function() {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
	},
	onWxTip : function() {
		var win = Ext.create("Ext.window.Window", {
			title: '微信提醒用户组',
		    height: 500,
		    width: 550,
		    layout: 'fit',
		    items: Ext.create("mvc.view.wpt.WptWxTips.List")
		}).show();
		win.down("grid").onSearchCancel();
	},
	onSearch : function() {
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		this.onSearchDo(array);
	},
	onSearchDo : function(array) {
		this.getSelectionModel().deselectAll();
		if (array.length == 0) {
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
	},
	onSet : function() {
		this.getView().getSelectionModel().selectAll();
		var selection = this.getView().getSelectionModel().getSelection()[0];
		console.log(selection)
		this.onSetWin(selection);
	},
	onSetWin : function(selection) {
		var win = Ext.create('mvc.view.wpt.WptServiceCen.Win', {
			title : this.title + '>设置',
		});
		win.show();
		win.setActiveRecord(selection);
		win.on('create', this.onSearch, this);
	}
});