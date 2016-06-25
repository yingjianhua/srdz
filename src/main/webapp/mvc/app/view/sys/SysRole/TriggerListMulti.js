Ext.define('mvc.view.sys.SysRole.TriggerListMulti',{
	extend : 'Ext.grid.Panel',
	disableSelection : false,
	loadMask : true,
	selModel : {selType : 'checkboxmodel', mode: 'MULTI'},
	viewConfig : {
		trackOver : false,
		stripeRows : true
	},
	initComponent : function(){
		this.columns = [ 
			{text : '代码', width : 100, dataIndex : 'bean.code', sortable : true},
			{text : '名称', width : 100, dataIndex : 'bean.name', sortable : true},
			{text : '启用标志', width : 100, dataIndex : 'bean.enabled', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')},
			{text : '可视范围', width : 100, dataIndex : 'bean.tbObj', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '更新员', width : 100, dataIndex : 'bean.updatedBy', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '更新时间', width : 140, dataIndex : 'bean.updatedDateTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
		];
		this.store=Ext.create('mvc.store.sys.SysRole');
		this.dockedItems=[{ 
			dock : 'top',
			xtype : 'toolbar',
			items : [{
				width : 300,
				fieldLabel : '搜索',
				labelWidth : 50,
				xtype : 'searchfield',
				flds : 'pkey,roleName',
				store : this.store
			},'->',{
				xtype : 'button',
				text : '确定',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onTrigger
			}]
		},{
			xtype : 'pagingtoolbar',
			store : this.store,
			dock : 'bottom',
			displayInfo : true,
			displayMsg : '显示 {0} - {1} 条，共计 {2} 条',
			emptyMsg : '没有数据',
			items : [{xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})}]
		}];
		this.callParent(arguments);
	},
	listeners : {
		itemdblclick : function() {
			this.onTriggerOne();
		}
	},
	onTriggerOne : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.name'), null);
		}
	},
	onTrigger : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		var result = '';
		if (selection){
			for(i = 0; i < selection.length; i++) {
				result += selection[i].get('bean.pkey') + bean_split + selection[i].get('bean.name');
				if(i < selection.length -1)
					result += ',';
			}
			this.fireEvent('trigger', result, null);
		}
	}
});