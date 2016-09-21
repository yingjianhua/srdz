Ext.define('mvc.view.wpt.WptRestaurant.List',{
extend : 'Ext.panel.Panel',
oldId : 'WptRestaurant_list_',
loadMask : true,
multiSelect : true,
roles : '',
layout : 'border',
lock : true,
mdSearch : null,
mdAct : null,
mdMain : null,
mdMainTable : null,
mdLineTable : null,
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('ins') != -1)
mainActs.push({
		text : '新增',
		iconCls : 'ins-icon',
		itemId : this.oldId+'ins',
		scope : this,
		handler : this.onIns
	});
		if (this.roles.indexOf('upd') != -1)
mainActs.push({
		text : '修改',
		iconCls : 'upd-icon',
		itemId : this.oldId+'upd',
		scope : this,
		handler : this.onUpd,
		disabled : this.lock
	});
		if (this.roles.indexOf('del') != -1)
mainActs.push({
		text : '删除',
		iconCls : 'del-icon',
		itemId : this.oldId+'del',
		scope : this,
		handler : this.onDel,
		disabled : this.lock
	});
			if (this.roles.indexOf('menuSet') != -1)
mainActs.push({
		text : '菜品设置',
		iconCls : 'edit-icon',
		itemId : this.oldId + 'menuSet',
		scope : this,
		handler : this.onMenuSet,
		disabled : this.lock
   });
		if (this.roles.indexOf('addHot') != -1)
mainActs.push({
		text : '添加热销',
		iconCls : 'edit-icon',
		itemId : this.oldId + 'addHot',
		scope : this,
		handler : this.onAddHot,
		disabled : this.lock
   });
		if (this.roles.indexOf('delHot') != -1)
mainActs.push({
		text : '删除热销',
		iconCls : 'edit-icon',
		itemId : this.oldId + 'delHot',
		scope : this,
		handler : this.onDelHot,
		disabled : this.lock
   });
		if (this.roles.indexOf('delHot') != -1)
mainActs.push({
		text : '同步到专题',
		iconCls : 'win-refresh-icon',
		itemId : this.oldId + 'addSpec',
		scope : this,
		handler : this.onAddSpec,
		disabled : this.lock
   });
		if (this.roles.indexOf('bannerSet') != -1)
mainActs.push({
		text : '轮播图设置',
		iconCls : 'edit-icon',
		itemId : this.oldId + 'bannerSet',
		scope : this,
		handler : this.onBannerSet,
		disabled : this.lock
   });
		if (this.roles.indexOf('enableDisable') != -1)
mainActs.push({
		text : '启用',
		iconCls : 'doEnabled-icon',
		itemId : this.oldId + 'enableDisable',
		scope : this,
		handler : this.onEnableDisable,
		disabled : this.lock
   });
		this.items =[{
		region : 'north',
		xtype : 'panel',
		border : false,
		items : [{
				xtype : 'toolbar',
				itemId : this.oldId+'search',
				items : [{
						xtype : 'label',
						text : '餐厅名称：'
					},{
						xtype : 'textfield',
						name : 'name'
					},'',{
						xtype : 'label',
						text : '城市：'
					},{
						xtype : 'beantrigger',
						name : 'city',
						bean : 'WptCity',
						beanType : 'wpt',
						emptyText : form_empty_text
					},'',{
						xtype : 'label',
						text : '区：'
					},
						mvc.Tools.crtComboTrigger(true,'wpt_WptCityLine','',{
									name : 'cityline'
								})
					,'',{
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
				xtype : 'toolbar',
				itemId : this.oldId+'act',
				items : mainActs
			},{
				xtype : 'form',
				itemId :  this.oldId+'main',
				bodyPadding : '5 5 0 5',
				fieldDefaults : {
					anchor : '100%',
					labelWidth : 100,
					width : 275,
					labelAlign : 'right',
					readOnly : true
				},
				items : [{
						xtype : 'fieldset',
						title : '餐厅信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [{xtype : 'textfield',name : 'name',fieldLabel : '餐厅名称'}
							,{
								xtype : 'beantrigger',
								name : 'city.pkey',
								fieldLabel : '城市',
								bean : 'WptCity',
								beanType : 'wpt',
								emptyText : form_empty_text
							},{xtype : 'textfield',name : 'mobile',fieldLabel : '电话'}
							,{xtype : 'textfield',name : 'manager',fieldLabel : '店长电话'}
							,{xtype : 'textfield',name : 'startdate',fieldLabel : '开始营业时间'}
							,{xtype : 'textfield',name : 'stopdate',fieldLabel : '结束营业时间'}
							]
					}]
			}]
	},{
		region : 'center',
		xtype : 'tabpanel',
		tabBar : {
			style : 'background:#fff'
		},
		items : [{
				xtype : Ext.create('mvc.view.wpt.WptRestaurant.ListMain',{
							title : '餐厅',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners : 							 {
								scope : this,
				                selectionchange: function(model, records) {
				                	var ENABLE = 1;
				                	var DISABLE = 0;
				                    if (records.length === 1){
				                        this.mdMain.getForm().loadRecord(records[0]);
        								this.mdLineTable.store.filter([{'id':'filter', 'property':'restaurant','value':records[0].get('pkey')}]);
        								this.mdMenuLineTable.store.filter([{'id':'filter', 'property':'restaurant','value':records[0].get('pkey')}]);
        								this.mdComboLineTable.store.filter([{'id':'filter', 'property':'restaurant','value':records[0].get('pkey')}]);
        								this.mdCaseLineTable.store.filter([{'id':'filter', 'property':'restaurant','value':records[0].get('pkey')}]);
        								this.mdBannerLineTable.store.filter([{'id':'filter', 'property':'restaurant','value':records[0].get('pkey')}]);
    									if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(false);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(false);
										if (this.roles.indexOf('menuSet') != -1)
											this.down('#'+this.oldId+'menuSet').setDisabled(false);
										if (this.roles.indexOf('addHot') != -1)
											this.down('#'+this.oldId+'addHot').setDisabled(false);
										if (this.roles.indexOf('delHot') != -1)
											this.down('#'+this.oldId+'delHot').setDisabled(false);
										if (this.roles.indexOf('addSpec') != -1)
											this.down('#'+this.oldId+'addSpec').setDisabled(false);
										if (this.roles.indexOf('bannerSet') != -1)
											this.down('#'+this.oldId+'bannerSet').setDisabled(false);
										if (this.roles.indexOf('enableDisable') != -1) {
											var bEnableDisable = this.down('#'+this.oldId+'enableDisable');
											bEnableDisable.setDisabled(false);
											if(records[0].get('enabled') == ENABLE) {
												bEnableDisable.setText("停用");
												bEnableDisable.setIconCls("unEnabled-icon");
											} else {
												bEnableDisable.setText("启用");
												bEnableDisable.setIconCls("doEnabled-icon");
											}
										}
				                    }else{
				                    	this.mdMain.getForm().reset();
				                    	this.mdLineTable.store.removeAll();
				                    	this.mdMenuLineTable.store.removeAll();
				                    	this.mdComboLineTable.store.removeAll();
				                    	this.mdCaseLineTable.store.removeAll();
				                    	this.mdBannerLineTable.store.removeAll();
				                    	if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(true);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(true);
										if (this.roles.indexOf('menuSet') != -1)
											this.down('#'+this.oldId+'menuSet').setDisabled(true);
										if (this.roles.indexOf('addHot') != -1)
											this.down('#'+this.oldId+'addHot').setDisabled(true);
										if (this.roles.indexOf('delHot') != -1)
											this.down('#'+this.oldId+'delHot').setDisabled(true);
										if (this.roles.indexOf('addSpec') != -1)
											this.down('#'+this.oldId+'addSpec').setDisabled(records.length === 0);
										if (this.roles.indexOf('bannerSet') != -1)
											this.down('#'+this.oldId+'bannerSet').setDisabled(true);
										if (this.roles.indexOf('enableDisable') != -1) {
											this.down('#'+this.oldId+'enableDisable').setDisabled(true);
										}
				                    }
				                }
			                }

						})
			},{
				xtype : Ext.create('mvc.view.wpt.WptRestaurant.ListLineWptRestaurantLine',{
							title : '宴会类型',
							itemId : this.oldId+'linetable',
							iconCls : 'tab-user-icon'
						})
			},{
				xtype : Ext.create('mvc.view.wpt.WptRestaurant.ListLineWptRestaurantMenu',{
					title : '菜品',
					itemId : this.oldId+'menulinetable',
					iconCls : 'tab-user-icon'
				})
			},{
				xtype : Ext.create('mvc.view.wpt.WptRestaurant.ListLineWptRestaurantCombo',{
					title : '套餐',
					itemId : this.oldId+'combolinetable',
					iconCls : 'tab-user-icon'
				})
			},{
				xtype : Ext.create('mvc.view.wpt.WptRestaurant.ListLineWptCase',{
					title : '案例',
					itemId : this.oldId+'caselinetable',
					iconCls : 'tab-user-icon'
				})
			},{
				xtype : Ext.create('mvc.view.wpt.WptRestaurant.ListLineWptRestaurantBanner',{
					title : '轮播图',
					itemId : this.oldId+'bannerlinetable',
					iconCls : 'tab-user-icon'
				})
			}]
	}];
		this.callParent(arguments);
		this.mdSearch = this.down('#'+this.oldId+'search');
		this.mdAct = this.down('#'+this.oldId+'act');
		this.mdMain = this.down('#'+this.oldId+'main');
		this.mdMainTable = this.down('#'+this.oldId+'maintable');
		this.mdLineTable = this.down('#'+this.oldId+'linetable');
		this.mdMenuLineTable = this.down('#'+this.oldId+'menulinetable');
		this.mdComboLineTable = this.down('#'+this.oldId+'combolinetable');
		this.mdCaseLineTable = this.down('#'+this.oldId+'caselinetable');
		this.mdBannerLineTable = this.down('#'+this.oldId+'bannerlinetable');
		mvc.Tools.onENTER2SearchBar(this.mdSearch,this);
		if (mainActs.length == 0)
			this.down('[region=north]').remove(this.mdAct);
},
getStore : function(){
		return this.mdMainTable.store;
},
onSaveRecord : function(form, data){
		this.mdMainTable.store.insert(0,data);
		this.mdMainTable.getView().select(0);
		Ext.example.msg(msg_title, msg_text);
},
onIns : function(){
		var win = Ext.create('mvc.view.wpt.WptRestaurant.Win',{
			title : this.title+'>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();
},
onUpd : function(){
		var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
		this.mdMainTable.onUpdWin(selection);
},
onDel : function(){
		var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
		if (selection){
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg, 
				function(btn) {
					if (btn != 'yes')
						return;
					var arr=new Array();
					var arrv = new Array();
					for(var i = 0; i < selection.length; i++){
						arr.push(selection[i].get('pkey'));
						arrv.push(selection[i].get(BEAN_VERSION));
					}
					Ext.Ajax.request({
						url : base_path+'/wpt_WptRestaurant_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								me.mdMainTable.getStore().remove(selection);
								Ext.example.msg(msg_title, msg_del);
							}else{
								Ext.MessageBox.show({
									title : msg_title, 
									msg : result.msg,
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.ERROR
								});
							}
						}
					});
				}
			);
		}
},
onMenuSet : function() {
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	this.onMenuSetWin(selection);
},
onMenuSetWin : function(selection) {
	if (selection) {
		var win = Ext.create(
				'mvc.view.wpt.WptMenu.Win', {
					title : this.title + '>菜品设置',
				});
		win.show();
		win.setActiveRecord(selection);
		win.on('create',this.mdMainTable.onMenuRecord,this.mdMainTable);
	}
},
onSearchCancel : function(){
		this.mdMainTable.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.mdSearch);
		this.mdMainTable.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.mdSearch);
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.wpt.WptRestaurant.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.mdMainTable.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.mdMainTable.store.clearFilter();
			return;
		}
		this.mdMainTable.store.clearFilter(true);
		this.mdMainTable.store.filter(array);
},
onAddHot : function(){
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
	if (selection) {
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, '您确认要添加到热销吗?',
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
						url : base_path + '/wpt_WptHot_addHot?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function(response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success) {
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
onDelHot : function(){
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
	if (selection) {
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, '您确认要从热销删除吗?',
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
						url : base_path + '/wpt_WptHot_delHot?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function(response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success) {
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
onAddSpec : function() {
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection();
	if (selection) {
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, '根据宴会类型名字同步到专题',
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
						url : base_path + '/wpt_WptRestaurant_addSpec?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function(response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success) {
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
onBannerSet : function() {
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	this.onBannerSetWin(selection);
},
onBannerSetWin : function(selection) {
	if (selection) {
		var win = Ext.create(
				'mvc.view.wpt.WptRestaurantBanner.Win', {
					title : this.title + '>轮播图设置',
				});
		win.show();
		win.setActiveRecord(selection);
		win.on('create',this.mdMainTable.onMenuRecord,this.mdMainTable);
	}
},
onEnableDisable:function() {
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	if (selection) {
		var me = this;
		Ext.Ajax.request({
			url : base_path + '/wpt_WptRestaurant_enableDisable?pkey='+selection.get('pkey')+'&rowVersions='+selection.get('rowVersion'),
			success : function(response, options) {
				var result = Ext.decode(response.responseText);
				if (result.success) {
					me.onUpdateRecord(null, result);
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
	}
},
onUpdateRecord : function(form, data){
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	var bean = Ext.create('mvc.model.wpt.WptRestaurant', data);
	Ext.apply(selection.data,bean.data);
	selection.commit();
	this.mdMainTable.getView().select(selection);
	Ext.example.msg(msg_title, msg_text);			
},
});