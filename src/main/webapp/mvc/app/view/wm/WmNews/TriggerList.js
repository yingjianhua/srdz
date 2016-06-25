Ext.define('mvc.view.wm.WmNews.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '标题',width : 100,dataIndex : 'bean.title',sortable : true}
	,{text : '封面',width : 100,dataIndex : 'bean.imageUrl',sortable : true, renderer: function(v) {return "<img src='../"+v+"' height='100px'>"}}
	,{text : '是否显示封面',width : 100,dataIndex : 'bean.showCoverPic',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '作者',width : 100,dataIndex : 'bean.author',sortable : true}
	,{text : '摘要',width : 100,dataIndex : 'bean.summary',sortable : true}
	,{text : '原文链接',width : 100,dataIndex : 'bean.relUrl',sortable : true}
	,{text : '图文类型',width : 100,dataIndex : 'bean.type',sortable : true,renderer : mvc.Tools.optRenderer('wm','Wm','ONewsType')}
	,{text : '扩展链接',width : 80,dataIndex : 'bean.exp',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '自定义链接',width : 100,dataIndex : 'bean.url',sortable : true}
	,{text : '同步状态',width : 60,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OSyncStatus')}
	,{text : '公众帐号',width : 100,dataIndex : 'bean.account',sortable : true,renderer : mvc.Tools.beanRenderer()}
	];
		this.store=Ext.create('mvc.store.wm.WmNews');this.dockedItems = [{
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
				value : 'title',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'title',text : '标题'}
								]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'title',
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
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.title'), null);
		}
}
});