Ext.define('mvc.view.wx.WxMassMessage.TemplatePicList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '标题',width : 100,dataIndex : 'bean.title',sortable : true}
	,{text : '作者',width : 100,dataIndex : 'bean.author',sortable : true}
	,{text : '封面',width : 100,dataIndex : 'bean.imageUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='90px' height='70px'>"}}
	,{text : '摘要',width : 100,dataIndex : 'bean.summary',sortable : true}
	,{text : '正文',width : 100,dataIndex : 'bean.content',sortable : true}
	,{text : '上级图文',width : 100,dataIndex : 'bean.picUp',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '链接地址',width : 100,dataIndex : 'bean.url',sortable : true}
	,{text : '原文链接',width : 100,dataIndex : 'bean.relUrl',sortable : true}
	,{text : '排序号',width : 100,dataIndex : 'bean.sort',sortable : true}
	,{text : '公众帐号',width : 100,dataIndex : 'bean.account',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '创建时间',width : 140,dataIndex : 'bean.createdTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '更新时间',width : 140,dataIndex : 'bean.updatedTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
	this.store=Ext.create('mvc.store.wm.WmNews');
	this.dockedItems = [{
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
				text : '新增图文',
				scope : this,
				iconCls : 'add-icon',
				handler : this.onIns
			},{
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
		this.addEvents("onIns");
		this.callParent(arguments);
		this.searchField = this.down('irilleSearchfield');
},
listeners : {
	itemdblclick : function(){
			this.onTriggerList();	
}
},
onIns : function() {
	this.fireEvent('onIns');
},
onTriggerList : function(){
		var selections = this.getView().getSelectionModel().getSelection();
		if(selections) {
			var selection = selections[0];	
			this.fireEvent('trigger', selection.get('bean.pkey'), null);
		}
}
});