Ext.define('mvc.view.wpt.WptOrder.ListMain',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '订单号',width : 110,dataIndex : 'orderid',sortable : true}
	,{text : '订单状态',width : 60,dataIndex : 'status',sortable : true,renderer : mvc.Tools.optRenderer('wpt','Wpt','OStatus')}
	,{text : '关注用户',width : 100,dataIndex : 'member.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wxex',mn : 'view.wpt.WptMember.List'}
	,{text : '餐厅',width : 100,dataIndex : 'restaurant.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptRestaurant.List'}
	,{text : '套餐',width : 100,dataIndex : 'combo.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptCombo.List'}
	,{text : '用餐时间',width : 140,dataIndex : 'time',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	,{text : '备注',width : 100,dataIndex : 'rem',sortable : true}
	,{text : '城市',width : 100,dataIndex : 'city.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptCity.List'}
	,{text : '定金',width : 100,dataIndex : 'deposit',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '金额',width : 100,dataIndex : 'price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '联系人',width : 100,dataIndex : 'contactMan',sortable : true}
	,{text : '性别',width : 100,dataIndex : 'contactSex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex')}
	,{text : '联系方式类型',width : 100,dataIndex : 'contactType',sortable : true,renderer : mvc.Tools.optRenderer('wpt','Wpt','OContactStatus')}
	,{text : '联系方式',width : 100,dataIndex : 'contactWay',sortable : true}
	,{text : '核验码',width : 100,dataIndex : 'checkcode',sortable : true}
	,{text : '创建时间',width : 140,dataIndex : 'createTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
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
					url : '/wpt_WptOrder_del?pkey='+row.get('pkey')+'&rowVersion='+row.get(BEAN_VERSION),
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