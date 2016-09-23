Ext.define('mvc.view.wpt.WptCombo.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '餐厅',width : 100,dataIndex : 'restaurant',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '名称',width : 100,dataIndex : 'name',sortable : true}
	,{text : '图片',width : 100,dataIndex : 'imgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='90px' height='70px'>"}}
	,{text : '描述',width : 100,dataIndex : 'des',sortable : true}
	,{text : '价格',width : 100,dataIndex : 'price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '最少人数',width : 100,dataIndex : 'numberMin',sortable : true}
	,{text : '最大人数',width : 100,dataIndex : 'numberMax',sortable : true}
	,{text : '使用日期',width : 100,dataIndex : 'serviceDate',sortable : true}
	,{text : '使用时段',width : 100,dataIndex : 'serviceTime',sortable : true}
	];
		this.store=Ext.create('mvc.store.wpt.WptCombo');this.dockedItems = [{
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
			this.fireEvent('trigger', selection.get('pkey') + bean_split + selection.get('name'), null);
		}
}
});