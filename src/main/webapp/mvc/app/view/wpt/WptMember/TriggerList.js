Ext.define('mvc.view.wpt.WptMember.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '关注状态',width : 60,dataIndex : 'status',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OStatus')}
	,{text : '昵称',width : 100,dataIndex : 'nickname',sortable : true}
	,{text : '性别',width : 100,dataIndex : 'sex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex')}
	,{text : '城市',width : 100,dataIndex : 'city',sortable : true}
	,{text : '头像',width : 100,dataIndex : 'imageUrl',sortable : true, renderer: function(v) {return "<img src='"+v+"' height='50px'/ >"}}
	,{text : '备注',width : 100,dataIndex : 'rem',sortable : true}
	,{text : '关注时间',width : 140,dataIndex : 'subscribeTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
		this.store=Ext.create('mvc.store.wpt.WptMember');this.dockedItems = [{
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
				value : 'nickname',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'nickname',text : '昵称'}
								,{value : 'city',text : '城市'}
								,{value : 'rem',text : '备注'}
								]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'nickname',
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
			this.fireEvent('trigger', selection.get('pkey') + bean_split + selection.get('nickname'), null);
		}
}
});