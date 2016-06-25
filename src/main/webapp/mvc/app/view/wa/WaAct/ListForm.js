Ext.define('mvc.view.wa.WaAct.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '奖项设置',width : 100,dataIndex : 'bean.actItem',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'combotriggercell',mode : 'local',valueField : 'value',triggerAction : 'all',typeAhead : true,editable : false,store : Ext.create('mvc.store.ComboTrigger',{actUrl:'wa_WaActItem',actWhere:''}),emptyText : form_empty_text}
		}
	,{text : '奖品设置',width : 100,dataIndex : 'bean.actPrize',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'WaActPrize',beanType : 'wa',beanName : 'bean.actPrize',grid : this,emptyText : form_empty_text}
		}
	,{text : '奖品数量',width : 100,dataIndex : 'bean.actNum',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	];
		this.store=Ext.create('mvc.store.wa.WaActSet');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.wa.WaActSet');
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