Ext.define('mvc.view.wpt.WptCombo.ListFormWptRestaurantMenu',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
initComponent : function(){
		this.columns =[{text : '菜品',width : 100,dataIndex : 'bean.menu',sortable : true,renderer : mvc.Tools.beanRenderer(),
			editor : {
				xtype : 'beantriggercell',
				bean : 'WptMenu',
				beanType : 'wpt',
				beanName : 'bean.menu',
				grid : this,
				emptyText : form_empty_text,
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
				listeners : {
					scope: this,
					change : function(field,newv,oldv){
						var me = this;
						Ext.Ajax.request({
							url : base_path+'/wpt_WptMenu_load?pkey='+newv.split(bean_split)[0],
							success : function (response, options) {
								var data = Ext.decode(response.responseText);
								me.getView().getSelectionModel().getSelection()[0].set("bean.price", data["price"])
							}
						})
					}
				}
			}
		}
	,{text : '价格',width : 100,dataIndex : 'bean.price',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right',editor : {xtype : 'numberfield',decimalPrecision : 2}}
	,{text : '排序',width : 100,dataIndex : 'bean.sort',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}}
	];
		this.store=Ext.create('mvc.store.wpt.WptComboLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
	var field = this.up("window").form.down("[name=bean.restaurant]");
	var win = Ext.create("mvc.tools.BeanSelectorMulti",{
		title : "选择器-菜品",
		oBeanType : "wpt",
		oBean : "WptMenu",
		tBeanType : "wpt",
		tBean : "WptComboLine",
		grid : this,
		diySql : "restaurant="+field.getValue().split(bean_split)[0],
		mapping : [{
			orig : "bean.pkey"+bean_split+"bean.name",
			targ : "bean.menu"
		},{
			orig : "bean.price",
			targ : "bean.price"
		}],
	});
	win.show();
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			this.getStore().remove(selection);
		}
}
});