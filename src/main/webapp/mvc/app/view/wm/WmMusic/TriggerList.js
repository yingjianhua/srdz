Ext.define('mvc.view.wm.WmMusic.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '音乐标题',width : 100,dataIndex : 'bean.title',sortable : true}
	,{text : '音乐描述',width : 100,dataIndex : 'bean.description',sortable : true}
	,{text : '音乐',width : 100,dataIndex : 'bean.musicUrl',sortable : true, renderer : function(value) {return "<a href='" + base_path + value + "' target=_blank>点击播放</a>"}}
	,{text : '高质量音乐',width : 100,dataIndex : 'bean.hqmusicUrl',sortable : true, renderer : function(value) {return "<a href='" + base_path + value + "' target=_blank>点击播放</a>"}}
	,{text : '缩略图',width : 100,dataIndex : 'bean.thumbUrl',sortable : true, renderer: function(v) {return "<img src='../"+v+"' height='100px'>"}}
	,{text : '同步状态',width : 60,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OSyncStatus')}
	];
		this.store=Ext.create('mvc.store.wm.WmMusic');this.dockedItems = [{
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
							data : [{value : 'title',text : '音乐标题'}
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