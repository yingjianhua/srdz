Ext.define('mvc.view.wa.WaActVote.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '报名开始时间',width : 140,dataIndex : 'bean.entryStartTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '报名结束时间',width : 140,dataIndex : 'bean.entryEndTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '活动开始时间',width : 140,dataIndex : 'bean.actStartTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '活动结束时间',width : 140,dataIndex : 'bean.actEndTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '页面模板',width : 100,dataIndex : 'bean.actTemplate',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '分享',width : 100,dataIndex : 'bean.jsMenushare',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '参数设置',width : 100,dataIndex : 'bean.actConfig',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '投票设置',width : 100,dataIndex : 'bean.voteConfig',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '活动状态',width : 60,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('wa','Wa','OActVoteStatus')}
	];
		this.store=Ext.create('mvc.store.wa.WaActVote');this.dockedItems = [{
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
				value : 'name',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'name',text : '名称'}
								]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'name',
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
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.name'), null);
		}
}
});