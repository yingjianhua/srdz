Ext.define('mvc.view.sys.SysEm.TriggerList',{
extend : 'mvc.tools.RowexpanderGrid',
disableSelection : false,
loadMask : true,
selModel : {selType : 'checkboxmodel'},
oneTdCount : 4,
searchField : null,
initComponent : function(){
this.columns = [{text : '工号',width : 100,dataIndex : 'bean.code',sortable : true}
	,{text : '登录账号',width : 100,dataIndex : 'user.loginName',sortable : true}
	,{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '昵称',width : 100,dataIndex : 'bean.nickname',sortable : true,expandCol : true,hidden : true}
	,{text : '职员状态',width : 80,dataIndex : 'bean.state',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OState')}
	,{text : '机构',width : 100,dataIndex : 'bean.org',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '部门',width : 100,dataIndex : 'bean.dept',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '个人证件类型',width : 100,dataIndex : 'one.peCardType',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OCardPersonType')}
	,{text : '个人证件号码',width : 150,dataIndex : 'one.peCardNumb',sortable : true}
	,{text : '个人常用手机',width : 100,dataIndex : 'one.peMobile',sortable : true}
	,{text : '个人邮箱',width : 100,dataIndex : 'one.peEmail',sortable : true,expandCol : true,hidden : true}
	,{text : '个人微信',width : 100,dataIndex : 'one.peWx',sortable : true,expandCol : true,hidden : true}
	,{text : '个人QQ',width : 100,dataIndex : 'one.peQq',sortable : true,expandCol : true,hidden : true}
	,{text : '个人性别',width : 100,dataIndex : 'one.peSex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex'),expandCol : true,hidden : true}
	,{text : '个人出生日期',width : 100,dataIndex : 'one.peBirthday',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d'),expandCol : true,hidden : true}
	,{text : '个人婚姻状况',width : 100,dataIndex : 'one.peMerry',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OMerry'),expandCol : true,hidden : true}
	,{text : '家庭电话',width : 100,dataIndex : 'one.hoTel',sortable : true,expandCol : true,hidden : true}
	,{text : '家庭地址',width : 100,dataIndex : 'one.hoAddr',sortable : true,expandCol : true,hidden : true}
	,{text : '家庭邮编',width : 100,dataIndex : 'one.hoZipCode',sortable : true,expandCol : true,hidden : true}
	,{text : '备注',width : 100,dataIndex : 'one.rem',sortable : true,expandCol : true,hidden : true}
	,{text : '更新员',width : 75,dataIndex : 'one.updatedBy',sortable : true,renderer : mvc.Tools.beanRenderer(),expandCol : true,hidden : true}
	,{text : '更新时间',width : 140,dataIndex : 'one.updatedDateTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol : true,hidden : true}
	,{text : '建档员',width : 75,dataIndex : 'one.createdBy',sortable : true,renderer : mvc.Tools.beanRenderer(),expandCol : true,hidden : true}
	,{text : '建档时间',width : 140,dataIndex : 'one.createdDateTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol : true,hidden : true}
	];
		this.store=Ext.create('mvc.store.sys.SysEm');this.dockedItems = [{
		dock : 'top',
		xtype : 'toolbar',
		items : ["搜索：",{
				xtype : 'combo',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				width : 100,
				value : 'code',
				store:	Ext.create('Ext.data.Store',{
							fields :  ['value', 'text'],
							data : [{value : 'code',text : '工号'}
								,{value : 'name',text : '名称'}
								]
						}),
				listeners : {
					scope : this,
					change : function(field,newv,oldv,opts) {	this.searchField.flds = newv; }
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
				handler : this.onTriggerList
			}]
	},{
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
		this.callParent(arguments);
		this.searchField = this.down('irilleSearchfield');
		this.getPlugin("rowexpanderplugin").expandOnDblClick=false;
},
listeners : {
	itemdblclick : function(){
			this.onTriggerList();	
}
},
onTriggerList : function(){
			var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			this.fireEvent('trigger', selection.get('bean.pkey') + bean_split + selection.get('bean.name'), null);
		}
}
});