Ext.define('mvc.view.sys.SysCustomOrg.ListEdit',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
initComponent : function(){
var mainActs =[{
		text : '新增',
		iconCls : 'ins-icon',
		scope : this,
		handler : this.onIns
	},{
		text : '删除',
		iconCls : 'del-icon',
		scope : this,
		handler : this.onDel
	},'->',{
		text : '保存',
		iconCls : 'win-save-icon',
		scope : this,
		handler : this.onSave
	},{
		text : '重置',
		iconCls : 'win-refresh-icon',
		scope : this,
		handler : this.onReset
	}];
			if (this.isEdit)
				this.tbar=mainActs;
this.columns = [{text : '机构',width : 150,dataIndex : 'bean.org',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'combotriggercell',mode : 'local',valueField : 'value',triggerAction : 'all',typeAhead : true,editable : false,store : Ext.create('mvc.store.ComboTrigger',{actUrl:'sys_SysOrg',actWhere:''}),emptyText : form_empty_text}
		}
	,{text : '部门',width : 150,dataIndex : 'bean.dept',sortable : true,renderer : mvc.Tools.beanRenderer(),editor : {xtype : 'beantriggercell',bean : 'SysDept',beanType : 'sys',beanName : 'bean.dept',grid : this,emptyText : form_empty_text}
		}
	];
		this.store=Ext.create('mvc.store.sys.SysCustomOrg');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems=[{
		dock : 'top',
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
		if (this.isEdit)
			this.plugins = [this.cellEditing];
		this.callParent(arguments);
},
onIns : function(){
		var model = Ext.create('mvc.store.sys.SysCustomOrg');
        this.store.insert(0, model);
        this.cellEditing.startEditByPosition({row: 0, column: 0});
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection)
			this.getStore().remove(selection);
},
onSave : function(){
		this.cellEditing.cancelEdit();
		var me = this,
			rtn,flds,
			syncValues = {};
		flds = this.store.model.getFields();
		rtn  = mvc.Tools.syncValues(this.store.getNewRecords(),flds,'insLines');
		Ext.apply(syncValues, rtn);
		rtn  = mvc.Tools.syncValues(this.store.getUpdatedRecords(),flds,'updLines');
		Ext.apply(syncValues, rtn);
		rtn  = mvc.Tools.syncValues(this.store.getRemovedRecords(),flds,'delLines');
		Ext.apply(syncValues, rtn);
		var change = false;
		for(var tmp in syncValues){
			change = true;
			break;
		}
		if (change){
			Ext.Ajax.request({
				url : base_path+'/sys_SysCustomOrg_sync?mainPkey='+me.mainPkey,
				params : syncValues,
				success : function (response, options) {
					var result = Ext.decode(response.responseText);
					if (result.success){
						me.onReset();
						Ext.example.msg(msg_title, msg_text);
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
},
onReset : function(){
		this.store.filter([{'id':'main','property':'custom','value':this.mainPkey}]);
},
onLoadFirst : function(){
		if (this.isLoadFirst)
			return;
		this.isLoadFirst = true;
		this.onReset();
}
});