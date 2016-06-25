Ext.define('mvc.view.wx.WxAccount.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
searchField : null,
initComponent : function(){
this.columns = [{text : '名称',width : 100,dataIndex : 'bean.accountName',sortable : true}
	,{text : 'TOKEN',width : 100,dataIndex : 'bean.accountToken',sortable : true}
	,{text : '微信号',width : 100,dataIndex : 'bean.accountNumber',sortable : true}
	,{text : '原始ID',width : 100,dataIndex : 'bean.accountId',sortable : true}
	,{text : '公众号类型',width : 100,dataIndex : 'bean.accountType',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OAccountType')}
	,{text : '开放平台',width : 100,dataIndex : 'bean.openPlat',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '代理服务号',width : 100,dataIndex : 'bean.agentAccount',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '电子邮箱',width : 100,dataIndex : 'bean.accountEmail',sortable : true}
	,{text : '描述',width : 100,dataIndex : 'bean.accountDesc',sortable : true}
	,{text : 'APPID',width : 100,dataIndex : 'bean.accountAppid',sortable : true}
	,{text : 'APPSECRET',width : 100,dataIndex : 'bean.accountAppsecret',sortable : true}
	,{text : '负责人',width : 75,dataIndex : 'bean.userSys',sortable : true,renderer : mvc.Tools.beanRenderer()}
	];
		this.store=Ext.create('mvc.store.wx.WxAccount');this.dockedItems = [{
		dock : 'top',
		xtype : 'toolbar',
		items : ["搜索：",{
				xtype : 'combo',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				width : 100,
				value : 'accountName',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'accountName',text : '名称'}
								]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'accountName',
				store : this.store
			},'->',{
				xtype : 'button',
				text : '确定',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onTriggerList
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
		this.callParent(arguments);
		this.searchField = this.down('irilleSearchfield');
},
listeners : {
	itemdblclick : function(){
			this.onTriggerList();	
}
},
onTriggerList : function(){
			var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.accountName'), null);
		}
}
});