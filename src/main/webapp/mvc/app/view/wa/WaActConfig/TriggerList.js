Ext.define('mvc.view.wa.WaActConfig.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '活动周期',width : 100,dataIndex : 'bean.cycle',sortable : true}
	,{text : '单位',width : 100,dataIndex : 'bean.unit',sortable : true,renderer : mvc.Tools.optRenderer('wa','Wa','OActUnit')}
	,{text : '周期参与数',width : 100,dataIndex : 'bean.cycleLimit',sortable : true}
	,{text : '周期参与数提示语',width : 100,dataIndex : 'bean.cycleLimitWords',sortable : true}
	,{text : '同IP周期参与数',width : 100,dataIndex : 'bean.ipLimit',sortable : true}
	,{text : '同IP周期参与数提示语',width : 100,dataIndex : 'bean.ipLimitWords',sortable : true}
	,{text : '浏览量系数',width : 100,dataIndex : 'bean.viewRate',sortable : true}
	,{text : '报名数系数',width : 100,dataIndex : 'bean.entryRate',sortable : true}
	,{text : '参与数系数',width : 100,dataIndex : 'bean.actRate',sortable : true}
	,{text : '图片限制',width : 100,dataIndex : 'bean.imageShape',sortable : true,renderer : mvc.Tools.optRenderer('wa','Wa','OActImageShape')}
	,{text : '自定义菜单',width : 100,dataIndex : 'bean.customMenu',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '限制区域',width : 100,dataIndex : 'bean.resArea',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '限制区域异常提示',width : 100,dataIndex : 'bean.resAreaWords',sortable : true}
	,{text : '有效区域',width : 100,dataIndex : 'bean.area',sortable : true}
	,{text : '无效区域',width : 100,dataIndex : 'bean.invalidArea',sortable : true}
	];
		this.store=Ext.create('mvc.store.wa.WaActConfig');this.dockedItems = [{
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