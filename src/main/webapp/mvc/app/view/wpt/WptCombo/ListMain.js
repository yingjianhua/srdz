Ext.define('mvc.view.wpt.WptCombo.ListMain',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '餐厅',width : 100,dataIndex : 'restaurant.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptRestaurant.List'}
	,{text : '名称',width : 100,dataIndex : 'name',sortable : true}
	,{text : '图片',width : 100,dataIndex : 'imgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='90px' height='70px'>"}}
	,{text : '描述',width : 100,dataIndex : 'des',sortable : true}
	,{text : '价格',width : 100,dataIndex : 'price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : '最少人数',width : 100,dataIndex : 'numberMin',sortable : true}
	,{text : '最大人数',width : 100,dataIndex : 'numberMax',sortable : true}
	,{text : '使用日期',width : 100,dataIndex : 'serviceDate',sortable : true}
	,{text : '使用时段',width : 100,dataIndex : 'serviceTime',sortable : true}
	,{text : '排序',width : 100,dataIndex : 'sort',sortable : true}
	,{text : '启用',width : 75,dataIndex : 'enabled',sortable : true,renderer : function(v){return v?"是":"否";}}
	];
		this.store=Ext.create('mvc.store.wpt.WptCombo');
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
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wpt.WptCombo', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
},
onMenuRecord : function(form, data){
	var selection = this.getView().getSelectionModel().getSelection()[0];
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
			var win = Ext.create('mvc.view.wpt.WptCombo.Win',{
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
					url : '/wpt/resource/combo_del?',
					params : {
						"bean.pkey" : selection[0].get('pkey'),
						"bean.rowVersion" : selection[0].get("rowVersion")
					},
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