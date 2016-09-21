Ext.define('mvc.view.wpt.WptRestaurant.ListMain',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[{text : '餐厅名称',width : 100,dataIndex : 'name',sortable : true}
	,{text : '电话',width : 100,dataIndex : 'mobile',sortable : true}
	,{text : '店长电话',width : 100,dataIndex : 'manager',sortable : true}
	,{text : '城市',width : 100,dataIndex : 'city.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptCity.List'}
	,{text : '区',width : 100,dataIndex : 'cityline.pkey',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '地址',width : 100,dataIndex : 'addr',sortable : true}
	,{text : '经度',width : 100,dataIndex : 'longitude',sortable : true}
	,{text : '纬度',width : 100,dataIndex : 'latitude',sortable : true}
	, {
			text : '图片',
			width : 100,
			dataIndex : 'imgUrl',
			sortable : true,
			renderer : function(v) {
				return "<img src='../" + v + "'width='90px' height='70px'>"
			}
		}
	,{text : '备注',width : 100,dataIndex : 'rem',sortable : true}
	,{text : '显示图片',width : 100,dataIndex : 'display',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '描述',width : 100,dataIndex : 'des',sortable : true}
	,{text : '开始营业时间',width : 100,dataIndex : 'startdate',sortable : true}
	,{text : '结束营业时间',width : 100,dataIndex : 'stopdate',sortable : true}
	,{text : '人均消费',width : 100,dataIndex : 'consumption',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'}
	,{text : 'wifi账号',width : 100,dataIndex : 'wifiaccount',sortable : true}
	,{text : 'wifi密码',width : 100,dataIndex : 'wifipassword',sortable : true}
	,{text : '启用',width : 75,dataIndex : 'enabled',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')}
	,{text : '模板',width : 100,dataIndex : 'template',sortable : true,renderer : mvc.Tools.beanRenderer()}
	];
		this.store=Ext.create('mvc.store.wpt.WptRestaurant');
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
onMenuRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
		},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wpt.WptRestaurant', data);
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
			var win = Ext.create('mvc.view.wpt.WptRestaurant.Win',{
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
					url : '/wpt_WptRestaurant_del?pkey='+row.get('pkey')+'&rowVersion='+row.get(BEAN_VERSION),
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