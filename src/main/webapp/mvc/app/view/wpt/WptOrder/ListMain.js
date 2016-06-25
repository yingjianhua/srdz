Ext.define('mvc.view.wpt.WptOrder.ListMain',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '订单号',width : 100,dataIndex : 'bean.orderid',sortable : true}
	,{text : '定金订单号',width : 100,dataIndex : 'bean.depPayId',sortable : true}
	,{text : '退款单号',width : 100,dataIndex : 'bean.outRefundNo',sortable : true}
	,{text : '关注用户',width : 100,dataIndex : 'bean.wxuser',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wxex',mn : 'view.wx.WxUser.List'}
	,{text : '餐厅',width : 100,dataIndex : 'bean.restaurant',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptRestaurant.List'}
	,{text : '宴会类型',width : 100,dataIndex : 'bean.banquet',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptBanquet.List'}
	,{text : '用餐时间',width : 140,dataIndex : 'bean.time',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '宴会人数',width : 100,dataIndex : 'bean.number',sortable : true}
	,{text : '人均预算',width : 100,dataIndex : 'bean.consumption',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '城市',width : 100,dataIndex : 'bean.city',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptCity.List'}
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
		this.store=Ext.create('mvc.store.wpt.WptOrder');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
		this.dockedItems=[{
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
		this.callParent(arguments);},
onServiceRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
	},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wpt.WptOrder', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
},
onUpdRow : function(grid, rowIndex){
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);
},
onUpdWin : function(selection){
		if (selection){
			var isPt = selection.get("bean.isPt");
			var win = Ext.create('mvc.view.wpt.WptOrder.Win',{
				isPt : isPt==1,
				title : this.title+'>修改',
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
		}
},
onDelRow : function(grid, rowIndex){
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				var row = me.getStore().getAt(rowIndex);
				Ext.Ajax.request({
					url : '/wpt_WptOrder_del?pkey='+row.get('bean.pkey')+'&rowVersion='+row.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.getStore().removeAt(rowIndex);
							Ext.example.msg(msg_title, msg_del);
						}else{
							Ext.MessageBox.show({ 
								title : msg_title,
								msg : result.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
						}
					}
				});
			}
		);
}
});