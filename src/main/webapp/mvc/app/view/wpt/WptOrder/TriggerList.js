Ext.define('mvc.view.wpt.WptOrder.TriggerList',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '订单号',width : 100,dataIndex : 'bean.orderid',sortable : true}
	,{text : '定金订单号',width : 100,dataIndex : 'bean.depPayId',sortable : true}
	,{text : '退款单号',width : 100,dataIndex : 'bean.outRefundNo',sortable : true}
	,{text : '关注用户',width : 100,dataIndex : 'bean.wxuser',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '餐厅',width : 100,dataIndex : 'bean.restaurant',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '宴会类型',width : 100,dataIndex : 'bean.banquet',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '用餐时间',width : 140,dataIndex : 'bean.time',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '宴会人数',width : 100,dataIndex : 'bean.number',sortable : true}
	,{text : '人均预算',width : 100,dataIndex : 'bean.consumption',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '城市',width : 100,dataIndex : 'bean.city',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '创建时间',width : 140,dataIndex : 'bean.createTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '订单状态',width : 60,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('wpt','Wpt','OStatus')}
	,{text : '定金',width : 100,dataIndex : 'bean.deposit',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '余款',width : 100,dataIndex : 'bean.residue',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '联系人',width : 100,dataIndex : 'bean.contactMan',sortable : true}
	,{text : '性别',width : 100,dataIndex : 'bean.contactSex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex')}
	,{text : '联系方式',width : 100,dataIndex : 'bean.contactWay',sortable : true}
	,{text : '联系方式类型',width : 100,dataIndex : 'bean.contactType',sortable : true,renderer : mvc.Tools.optRenderer('wpt','Wpt','OContactStatus')}
	,{text : '备注',width : 100,dataIndex : 'bean.rem',sortable : true}
	,{text : '套餐名称',width : 100,dataIndex : 'bean.comboName',sortable : true}
	,{text : '金额',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '是否私人定制 ',width : 100,dataIndex : 'bean.isPt',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '核验码',width : 100,dataIndex : 'bean.checkcode',sortable : true}
	];
		this.store=Ext.create('mvc.store.wpt.WptOrder');this.dockedItems = [{
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
				value : 'orderid',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'orderid',text : '订单号'}
								]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'orderid',
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
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.orderid'), null);
		}
}
});