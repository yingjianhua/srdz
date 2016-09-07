Ext.define('mvc.view.wpt.WptMember.List',{
extend : 'Ext.panel.Panel',
oldId : 'WptUser_list_',
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
	this.items =[{
		region : 'north',
		xtype : 'panel',
		border : false,
		items : [{
				xtype : 'toolbar',
				itemId : this.oldId+'search',
				items : [
				         {xtype : 'label',text : '昵称：'},
				         {xtype : 'textfield',name : 'nickname'},
				         '',
				         {xtype : 'label',text : '城市：'},
				         {xtype : 'textfield',name : 'city'},
				         '',
				         {xtype : 'label',text : '备注：'},
				         {xtype : 'textfield',name : 'rem'},
				         '',
				         {xtype : 'label',text : '关注时间：'},
				         {xtype : 'datefield',name : 'subscribeTime',format : 'Y-m-d H:i:s'},
				         '',
				         {xtype : 'button',text : '撤销',scope : this,iconCls : 'win-close-icon',handler : this.onSearchCancel},
				         {xtype : 'splitbutton',text : '搜索',scope : this,iconCls : 'win-ok-icon',handler : this.onSearch,
				        	 menu : [{text:'高级搜索',iconCls : 'win-ok-icon', scope : this,handler: this.onSearchAdv}]
				         }
				         ]
			},{
				xtype : 'toolbar',
				itemId : this.oldId+'act',
				items : [{
					text : '成为会员',
					iconCls : 'upd-icon',
					itemId : this.oldId+'upd',
					scope : this,
					handler : this.onBeenMember,
				},{
					text : '为所有用户产生推广二维码',
					iconCls : 'upd-icon',
					itemId : this.oldId+'createAllQrcode',
					scope : this,
					handler : this.onCreateAllQrcode,
				}]
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
						title : '会员信息',
						collapsible : true,
						layout : {
							type : 'table',
							columns : 3
						},
						items : [
						         {xtype : 'textfield',name : 'nickname',fieldLabel : '昵称'},
						         {xtype : 'numberfield',name : 'historyCommission',fieldLabel : '历史佣金'},
						         {xtype : 'numberfield',name : 'cashableCommission',fieldLabel : '可提现佣金'}
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
				xtype : Ext.create('mvc.view.wpt.WptMember.ListMain',{
							title : '会员信息',
							itemId : this.oldId+'maintable',
							iconCls : 'tab-user-icon',
							roles : this.roles,
							listeners : 							 {
								scope : this,
				                selectionchange: function(model, records) {
				                    if (records.length === 1){
				                        this.mdMain.getForm().loadRecord(records[0]);
        								this.mdLineTable.store.filter([{'id':'filter', 'property':'wxuser','value':records[0].get('pkey')}]);
        								this.mdLineTable2.store.filter([{'id':'filter', 'property':'wxuser','value':records[0].get('pkey')}]);
				                    }else{
				                    	this.mdMain.getForm().reset();
				                    	this.mdLineTable.store.removeAll();
				                    	this.mdLineTable2.store.removeAll();
				                    }
				                }
			                }

						})
			},{
				xtype : Ext.create('mvc.view.wpt.WptMember.ListLineWptCommissionJournal',{
							title : '佣金流水',
							itemId : this.oldId+'linetable',
							iconCls : 'tab-user-icon'
						})
			},{
				xtype : Ext.create('mvc.view.wpt.WptMember.ListLineWptCashJournal',{
					title : '提现流水',
					itemId : this.oldId+'linetable2',
					iconCls : 'tab-user-icon'
				})
			}]
	}];
	this.callParent(arguments);
	this.mdSearch = this.down('#'+this.oldId+'search');
	this.mdMain = this.down('#'+this.oldId+'main');
	this.mdMainTable = this.down('#'+this.oldId+'maintable');
	this.mdLineTable = this.down('#'+this.oldId+'linetable');
	this.mdLineTable2 = this.down('#'+this.oldId+'linetable2');
	mvc.Tools.onENTER2SearchBar(this.mdSearch,this);
},
getStore : function(){
		return this.mdMainTable.store;
},
onCreateAllQrcode : function() {
	Ext.Ajax.request({
		url : base_path+'/wpt/resource/member_createAllQrcode'
	});
},
onBeenMember : function() {
	var selections = this.mdMainTable.getView().getSelectionModel().getSelection();
	if (selections){
		var selection = selections[0];
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, "确认吗?", 
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/wpt/resource/member_becomeMember?pkey='+selection.get('pkey')+'&rowVersions='+selection.get('rowVersion'),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							var selection = me.mdMainTable.getView().getSelectionModel().getSelection()[0];
							var bean = Ext.create('mvc.model.wpt.WptMember', result);
							Ext.apply(selection.data,result);
							selection.commit();
							this.getView().select(selection);
							Ext.example.msg(msg_title, msg_text);
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
onSaveRecord : function(form, data){
		this.mdMainTable.store.insert(0,data);
		this.mdMainTable.getView().select(0);
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
		var win = Ext.create('mvc.view.wpt.WptMember.WinSearch',{
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