Ext.define('mvc.view.wpt.WptCombo.List',{
extend : 'Ext.panel.Panel',
oldId : 'WptCombo_list_',
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
		if (this.roles.indexOf('del') != -1)
mainActs.push({
		text : '设置轮播图',
		iconCls : 'upd-icon',
		itemId : this.oldId+'bannerSet',
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
						text : '名称：'
					},{
						xtype : 'textfield',
						name : 'name'
					},'',{
						xtype : 'label',
						text : '餐厅：'
					},{
						xtype : 'beantrigger',
						name : 'restaurant',
						bean : 'WptRestaurant',
						beanType : 'wpt',
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
						title : '套餐信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [{xtype : 'textfield',name : 'bean.name',fieldLabel : '名称'}
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
				xtype : Ext.create('mvc.view.wpt.WptCombo.ListMain',{
							title : '套餐',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners : 							 {
								scope : this,
				                selectionchange: function(model, records) {
				                    if (records.length === 1){
				                    	var ENABLE = 1;
					                	var DISABLE = 0;
				                        this.mdMain.getForm().loadRecord(records[0]);
        								this.mdLineTable.store.filter([{'id':'filter', 'property':'combo','value':records[0].get('bean.pkey')}]);
        								this.mdBannerLineTable.store.filter([{'id':'filter', 'property':'combo','value':records[0].get('bean.pkey')}]);
        								if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(false);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(false);
										if (this.roles.indexOf('bannerSet') != -1)
											this.down('#'+this.oldId+'bannerSet').setDisabled(false);
										if (this.roles.indexOf('enableDisable') != -1) {
											var bEnableDisable = this.down('#'+this.oldId+'enableDisable');
											bEnableDisable.setDisabled(false);
											if(records[0].get('bean.enabled') == ENABLE) {
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
				                    	if (this.roles.indexOf('upd') != -1)
											this.down('#'+this.oldId+'upd').setDisabled(true);
										if (this.roles.indexOf('del') != -1)
											this.down('#'+this.oldId+'del').setDisabled(true);
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
				xtype : Ext.create('mvc.view.wpt.WptCombo.ListLineWptComboLine',{
							title : '套餐明细',
							itemId : this.oldId+'linetable',
							iconCls : 'tab-user-icon'
						})
			},{
				xtype : Ext.create('mvc.view.wpt.WptCombo.ListLineWptComboBanner',{
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
		var win = Ext.create('mvc.view.wpt.WptCombo.Win',{
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
						arr.push(selection[i].get('bean.pkey'));
						arrv.push(selection[i].get(BEAN_VERSION));
					}
					Ext.Ajax.request({
						url : base_path+'/wpt_WptCombo_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
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
onBannerSet : function() {
	var selection = this.mdMainTable.getView().getSelectionModel().getSelection()[0];
	this.onBannerSetWin(selection);
},
onBannerSetWin : function(selection) {
	if (selection) {
		var win = Ext.create(
				'mvc.view.wpt.WptComboBanner.Win', {
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
			url : base_path + '/wpt_WptCombo_enableDisable?pkey='+selection.get('bean.pkey')+'&rowVersions='+selection.get('bean.rowVersion'),
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
	var bean = Ext.create('mvc.model.wpt.WptCombo', data);
	Ext.apply(selection.data,bean.data);
	selection.commit();
	this.mdMainTable.getView().select(selection);
	Ext.example.msg(msg_title, msg_text);			
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
		var win = Ext.create('mvc.view.wpt.WptCombo.WinSearch',{
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
}
});