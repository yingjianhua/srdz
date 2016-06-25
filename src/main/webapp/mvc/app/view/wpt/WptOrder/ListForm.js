Ext.define('mvc.view.wpt.WptOrder.ListForm',{
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
		this.columns =[{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true ,
			editor : Ext.create('mvc.view.wpt.WptOrder.OrderMenuCell',{
				onTrigger2Click : function() {
			    	var win = Ext.create("mvc.view.wpt.WptMenu.Trigger");
					win.on('trigger', this.onTrigger, this);
					win.show();
					var store = win.down('grid').getStore();
					var array = [];
					array.push({
		            	id:'flds',
		                property: 'param',
		                value: '1=1'
		            });
		            var field = this.up("window").form.down("[name=bean.restaurant]");
		            if(field.getValue()){
		            	array.push({
		            		id:'diy',
			                property: 'diy',
			                value: "restaurant="+field.getValue().split(bean_split)[0]
		            	})
		            }
					store.filter(array);
				},
				callback : function(pkey) {
					Ext.Ajax.request({
						url : base_path+'/wpt_WptMenu_load?pkey='+pkey,
						success : function (response, options) {
							var data = Ext.decode(response.responseText);
							var select = me.getView().getSelectionModel().getSelection()[0];
							select.set("bean.name", data["name"]);
							select.set("bean.des", data["des"])
							select.set("bean.price", data["price"]);
							select.set("bean.number", 1);
						}
					});
				},
				beanName : 'bean.name',
				grid : this,
				emptyText : form_empty_text,
			})
		}
	,{text : '描述',width : 100,dataIndex : 'bean.des',sortable : true,editor : {}
		}
	,{text : '价格',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 2}
		}
	,{text : '数量',width : 100,dataIndex : 'bean.number',sortable : true,editor : {xtype : "numberfield"}}
	];
		this.store=Ext.create('mvc.store.wpt.WptOrderLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);
},
onIns : function(){
		var model = Ext.create('mvc.store.wpt.WptOrderLine');
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