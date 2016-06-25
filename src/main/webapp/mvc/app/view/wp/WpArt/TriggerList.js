Ext.define('mvc.view.wp.WpArt.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '图文秀',width : 100,dataIndex : 'bean.artShow',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '标题',width : 100,dataIndex : 'bean.title',sortable : true}
	,{text : '图片',width : 100,dataIndex : 'bean.imgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='90px' height='70px'>"}}
	,{text : '链接',width : 100,dataIndex : 'bean.url',sortable : true}
	,{text : '描述',width : 100,dataIndex : 'bean.description',sortable : true}
	,{text : '时间',width : 100,dataIndex : 'bean.date',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d')}
	,{text : '主题',width : 100,dataIndex : 'bean.theme',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '商家',width : 100,dataIndex : 'bean.bsn',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '创建人',width : 75,dataIndex : 'bean.createBy',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '创建时间',width : 140,dataIndex : 'bean.createTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '更新人',width : 75,dataIndex : 'bean.updateBy',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '更新时间',width : 140,dataIndex : 'bean.updateTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
		this.store=Ext.create('mvc.store.wp.WpArt');this.dockedItems = [{
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
								,{value : 'artShow',text : '图文秀'}
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