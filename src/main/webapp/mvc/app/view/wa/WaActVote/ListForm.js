Ext.define('mvc.view.wa.WaActVote.ListForm',{
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
		this.tbar = mainActs;		this.columns =[{text : '奖项',width : 100,dataIndex : 'bean.prizeitem',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'combotriggercell',mode : 'local',valueField : 'value',triggerAction : 'all',typeAhead : true,editable : false,store : Ext.create('mvc.store.ComboTrigger',{actUrl:'wa_WaActItem',actWhere:''}),emptyText : form_empty_text}
		}
	,{text : '奖品',width : 100,dataIndex : 'bean.prize',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'WaActPrize',beanType : 'wa',beanName : 'bean.prize',grid : this,emptyText : form_empty_text}
		}
	,{text : '数量',width : 100,dataIndex : 'bean.amount',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	,{text : '单位',width : 100,dataIndex : 'bean.unit',sortable : true,editor : {}
		}
	,{text : '排序',width : 100,dataIndex : 'bean.sort',sortable : true,editor : {xtype : 'numberfield',allowDecimals : false}
		}
	];
		this.store=Ext.create('mvc.store.wa.WaActVotePrize');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
		var model = Ext.create('mvc.store.wa.WaActVotePrize');
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