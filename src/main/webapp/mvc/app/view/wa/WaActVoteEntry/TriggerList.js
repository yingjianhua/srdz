Ext.define('mvc.view.wa.WaActVoteEntry.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '活动',width : 100,dataIndex : 'bean.vote',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '参赛者',width : 100,dataIndex : 'bean.wxUser',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '编号',width : 100,dataIndex : 'bean.number',sortable : true}
	,{text : '姓名',width : 100,dataIndex : 'bean.namePerson',sortable : true}
	,{text : '电话',width : 100,dataIndex : 'bean.phonePerson',sortable : true}
	,{text : '描述',width : 100,dataIndex : 'bean.des',sortable : true}
	,{text : '报名时间',width : 140,dataIndex : 'bean.entryTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '投票数',width : 100,dataIndex : 'bean.voteCount',sortable : true}
	,{text : '报名状态',width : 60,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('wa','Wa','OActEntryStatus')}
	];
		this.store=Ext.create('mvc.store.wa.WaActVoteEntry');this.dockedItems = [{
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
				value : 'wxUser',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'wxUser',text : '参赛者'}
								]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'wxUser',
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
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.wxUser'), null);
		}
}
});