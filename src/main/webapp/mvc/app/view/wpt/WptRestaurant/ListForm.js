Ext.define('mvc.view.wpt.WptRestaurant.ListForm',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
initComponent : function(){
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
		this.columns =[{text : '宴会类型',width : 100,dataIndex : 'bean.banquet',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'WptBanquet',beanType : 'wpt',beanName : 'bean.banquet',grid : this,emptyText : form_empty_text}
		}
	];
		this.store=Ext.create('mvc.store.wpt.WptRestaurantLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var win = Ext.create("mvc.tools.BeanSelectorMulti",{
			title : "选择器-宴会类型",
			oBeanType : "wpt",
			oBean : "WptBanquet",
			tBeanType : "wpt",
			tBean : "WptRestaurantLine",
			grid : this,
			mapping : [{
				orig : "bean.pkey"+bean_split+"bean.name",
				targ : "bean.banquet"
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