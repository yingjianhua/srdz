Ext.define('mvc.view.wpt.WptTop.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '城市',width : 100,dataIndex : 'bean.city',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '区域',width : 100,dataIndex : 'bean.cityline',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '主题',width : 100,dataIndex : 'bean.banquet',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '标题',width : 100,dataIndex : 'bean.title',sortable : true}
	,{text : '图片',width : 100,dataIndex : 'bean.imgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"' height='70px'>"}}
	,{text : '时间',width : 100,dataIndex : 'bean.date',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d')}
	,{text : '链接',width : 100,dataIndex : 'bean.url',sortable : true}
	,{text : '置顶',width : 100,dataIndex : 'bean.top',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '排序',width : 100,dataIndex : 'bean.sort',sortable : true}
	];
		this.store=Ext.create('mvc.store.wpt.WptTop');this.dockedItems = [{
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