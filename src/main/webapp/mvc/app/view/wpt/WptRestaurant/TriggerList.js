Ext.define('mvc.view.wpt.WptRestaurant.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '餐厅名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '电话',width : 100,dataIndex : 'bean.mobile',sortable : true}
	,{text : '店长电话',width : 100,dataIndex : 'bean.manager',sortable : true}
	,{text : '城市',width : 100,dataIndex : 'bean.city',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '区',width : 100,dataIndex : 'bean.cityline',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '地址',width : 100,dataIndex : 'bean.addr',sortable : true}
	,{text : '经度',width : 100,dataIndex : 'bean.longitude',sortable : true}
	,{text : '纬度',width : 100,dataIndex : 'bean.latitude',sortable : true}
	,{text : '图片',width : 100,dataIndex : 'bean.imgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='90px' height='70px'>"}}
	,{text : '备注',width : 100,dataIndex : 'bean.rem',sortable : true}
	,{text : '显示图片',width : 100,dataIndex : 'bean.display',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '描述',width : 100,dataIndex : 'bean.des',sortable : true}
	,{text : '开始营业时间',width : 100,dataIndex : 'bean.startdate',sortable : true}
	,{text : '结束营业时间',width : 100,dataIndex : 'bean.stopdate',sortable : true}
	,{text : '人均消费',width : 100,dataIndex : 'bean.consumption',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : 'wifi账号',width : 100,dataIndex : 'bean.wifiaccount',sortable : true}
	,{text : 'wifi密码',width : 100,dataIndex : 'bean.wifipassword',sortable : true}
	];
		this.store=Ext.create('mvc.store.wpt.WptRestaurant');this.dockedItems = [{
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
							data : [{value : 'name',text : '餐厅名称'}
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