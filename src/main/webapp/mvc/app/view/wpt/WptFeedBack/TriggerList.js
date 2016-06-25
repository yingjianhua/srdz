Ext.define('mvc.view.wpt.WptFeedBack.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '内容',width : 100,dataIndex : 'bean.content',sortable : true}
	,{text : '联系方式',width : 100,dataIndex : 'bean.contactWay',sortable : true}
	,{text : '联系方式类型',width : 100,dataIndex : 'bean.contactType',sortable : true,renderer : mvc.Tools.optRenderer('wpt','Wpt','OContactStatus')}
	,{text : '处理人',width : 75,dataIndex : 'bean.handleMan',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '处理时间',width : 140,dataIndex : 'bean.handleTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '已处理',width : 100,dataIndex : 'bean.isHandle',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	];
		this.store=Ext.create('mvc.store.wpt.WptFeedBack');this.dockedItems = [{
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
				value : 'contactWay',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'contactWay',text : '联系方式'}
								]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'contactWay',
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
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.contactWay'), null);
		}
}
});