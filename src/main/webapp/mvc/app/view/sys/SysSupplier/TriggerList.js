Ext.define('mvc.view.sys.SysSupplier.TriggerList',{
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
		this.columns = [{text : '代码', width : 100, dataIndex : 'bean.code', sortable : true},
			{text : '机构名称', width : 100, dataIndex : 'bean.name', sortable : true},
			{text : '机构简称', width : 100, dataIndex : 'bean.shortName', sortable : true},
			{text : '性质', width : 100, dataIndex : 'bean.comPersonFlag', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OComPersonFlag')},
			{text : '启用标志', width : 100, dataIndex : 'bean.enabled', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OEnabled')},
			{text : '管理机构', width : 100, dataIndex : 'bean.mngOrg', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '管理部门', width : 100, dataIndex : 'bean.mngDept', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '业务代表', width : 100, dataIndex : 'bean.businessMember', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '备注', width : 100, dataIndex : 'bean.rem', sortable : true}];
		this.store=Ext.create('mvc.store.sys.SysSupplier');
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
                value : 'code',
                store:	Ext.create('Ext.data.Store',{
					fields : ['value', 'text'],
					data : [
						{value : 'code', text : '代码'}
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
				flds : 'code',
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
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.code'), null);
		}
	}
});
