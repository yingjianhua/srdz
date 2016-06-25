Ext.define('mvc.view.sys.SysObjectRel.TriggerList',{
	extend : 'Ext.grid.Panel',
	disableSelection : false,
	loadMask : true,
	selModel : {selType : 'checkboxmodel'},
	viewConfig : {
		trackOver : false,
		stripeRows : true
	},
	searchField : null,
	initComponent : function(){
		this.columns = [
			{text : '描述', width : 250, dataIndex : 'bean.description', sortable : true},
			{text : '机构', width : 100, dataIndex : 'bean.org', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '部门', width : 100, dataIndex : 'bean.dept', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '用户', width : 100, dataIndex : 'bean.em', sortable : true, renderer : mvc.Tools.beanRenderer()}
			];
		this.store=Ext.create('mvc.store.sys.SysObjectRel');
		this.dockedItems=[{
			dock : 'top',
			xtype : 'toolbar',
			items : ["搜索：",{
				xtype:          'combo',
                mode:           'local',
                valueField:     'value',
                triggerAction:  'all',
                forceSelection: true,
                typeAhead: true,
                editable:       false,
                width : 100,
                value : 'description',
                store:	Ext.create('Ext.data.Store',{
					fields : ['value', 'text'],
					data : [
						{value : 'description', text : '描述'}
					]
				}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {
						this.searchField.flds = newv;
					}
				}
			},'=',{
				width : 250,
				xtype : 'irilleSearchfield',
				flds : 'description',
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
		this.searchField = this.down('irilleSearchfield');
	},
	listeners : {
		itemdblclick : function() {
			this.onTrigger();
		}
	},
	onTrigger : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.description'), null);
		}
	}
});
