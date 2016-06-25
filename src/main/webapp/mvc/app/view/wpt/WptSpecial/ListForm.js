Ext.define('mvc.view.wpt.WptSpecial.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '餐厅',width : 100,dataIndex : 'bean.restaurant',sortable : true,renderer : mvc.Tools.beanRenderer(),
			editor : {xtype : 'beantriggercell',
				onTrigger2Click : function() {
			    	var win = Ext.create("mvc.view.wpt.WptRestaurant.Trigger");
					win.on('trigger', this.onTrigger, this);
					win.show();
					var store = win.down('grid').getStore();
					var array = [];
					array.push({
		            	id:'flds',
		                property: 'param',
		                value: '1=1'
		            });
		            var field = this.up("window").form.down("[name=bean.city]");
		            console.log(field)
		            console.log(1)
		            if(field.getValue()){
		            	array.push({
		            		id:'diy',
			                property: 'diy',
			                value: "city="+field.getValue().split(bean_split)[0]
		            	})
		            }
					store.filter(array);
				},
				bean : 'WptRestaurant',beanType : 'wpt'
				,beanName : 'bean.restaurant'
				,grid : this
				,emptyText : form_empty_text}
		}
	,{text : '排序',width : 100,dataIndex : 'bean.sort',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	];
		this.store=Ext.create('mvc.store.wpt.WptSpecialLine');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
	var win = Ext.create("mvc.tools.BeanSelectorMulti",{
		title : "选择器-餐厅",
		oBeanType : "wpt",
		oBean : "WptRestaurant",
		tBeanType : "wpt",
		tBean : "WptSpecialLine",
		grid : this,
		mapping : [{
			orig : "bean.pkey"+bean_split+"bean.name",
			targ : "bean.restaurant"
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