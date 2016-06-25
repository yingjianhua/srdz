Ext.define('mvc.view.wpt.WptOrderService.ListForm',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
initComponent : function(){
	var me = this;
		var mainActs = [{
		text : '新增',
		iconCls : 'ins-icon',
		scope : this,
		handler : this.onIns
	},{
		text : '删除',
		iconCls : 'del-icon',
		scope : this,
		handler : this.onDel
	}];
		this.tbar = mainActs;		
		this.columns =[{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true,
			editor : Ext.create("mvc.view.wpt.WptOrder.OrderMenuCell",{
				bean : 'WptService',
				beanType : 'wpt',
				grid : this,
				emptyText : form_empty_text,
				callback : function(pkey) {
					Ext.Ajax.request({
						url : base_path+'/wpt_WptService_load?pkey='+pkey,
						success : function (response, options) {
							var data = Ext.decode(response.responseText);
							var select = me.getView().getSelectionModel().getSelection()[0];
							select.set("bean.price", data["price"]);
							select.set("bean.name", data["name"])
						}
					});
				}
			})
		},
	{text : '价格',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 2}}
	];
		this.store=Ext.create('mvc.store.wpt.WptOrderService');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.wpt.WptOrderService');
        this.store.insert(0, model);
        this.cellEditing.startEditByPosition({row: 0, column: 0});
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			this.getStore().remove(selection);
		}
}
});