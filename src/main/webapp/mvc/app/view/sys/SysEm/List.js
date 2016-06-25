Ext.define('mvc.view.sys.SysEm.List',{
extend : 'mvc.tools.RowexpanderGrid',
oldId : 'btn_SysEm',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('ins') != -1)
mainActs.push({
		text : '新增',
		iconCls : 'ins-icon',
		itemId : this.oldId+'ins',
		scope : this,
		handler : this.onIns
	});
		if (this.roles.indexOf('upd') != -1)
mainActs.push({
		text : '修改',
		iconCls : 'upd-icon',
		itemId : this.oldId+'upd',
		scope : this,
		handler : this.onUpd,
		disabled : this.lock
	});
this.columns = [{text : '工号',width : 100,dataIndex : 'bean.code',sortable : true}
	,{text : '登录账号',width : 100,dataIndex : 'user.loginName',sortable : true}
	,{text : '名称',width : 100,dataIndex : 'bean.name',sortable : true}
	,{text : '昵称',width : 100,dataIndex : 'bean.nickname',sortable : true,expandCol : true,hidden : true}
	,{text : '职员状态',width : 80,dataIndex : 'bean.state',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OState')}
	,{text : '机构',width : 100,dataIndex : 'bean.org',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'sys',mn : 'view.sys.SysOrg.List'}
	,{text : '部门',width : 100,dataIndex : 'bean.dept',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'sys',mn : 'view.sys.SysDept.List'}
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
	,{text : '更新员',width : 75,dataIndex : 'one.updatedBy',sortable : true,renderer : mvc.Tools.beanRendererHref(),expandCol : true,hidden : true,md : 'sys',mn : 'view.sys.SysUser.List'}
	,{text : '更新时间',width : 140,dataIndex : 'one.updatedDateTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol : true,hidden : true}
	,{text : '建档员',width : 75,dataIndex : 'one.createdBy',sortable : true,renderer : mvc.Tools.beanRendererHref(),expandCol : true,hidden : true,md : 'sys',mn : 'view.sys.SysUser.List'}
	,{text : '建档时间',width : 140,dataIndex : 'one.createdDateTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s'),expandCol : true,hidden : true}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.sys.SysEm'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '工号：'
			},{
				xtype : 'textfield',
				name : 'code'
			},'',{
				xtype : 'label',
				text : '名称：'
			},{
				xtype : 'textfield',
				name : 'name'
			},'',{
				xtype : 'label',
				text : '机构：'
			},
				mvc.Tools.crtComboTrigger(true,'sys_SysOrg','',{
							name : 'org'
						})
			,'',{
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			},{
				xtype : 'splitbutton',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch,
				menu : [{text:'高级搜索',iconCls : 'win-ok-icon', scope : this,handler: this.onSearchAdv}]
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
		this.callParent(arguments);		mvc.Tools.onENTER2SearchBar(this.down('[dock=top]'),this);},
listeners : {
	selectionchange : function(selModel, selected){
		if (this.roles.indexOf('upd') != -1)
			this.down('#'+this.oldId+'upd').setDisabled(selected.length === 0);	
}
},
onSaveRecord : function(form, data){
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);	
},
onIns : function(){
		var win = Ext.create('mvc.view.sys.SysEm.Win',{
			title : this.title+'>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();		
},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.sys.SysEm', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);			
},
onUpd : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		this.onUpdWin(selection);
},
onUpdRow : function(grid, rowIndex){
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);			
},
onUpdWin : function(selection){
		if (selection){
			var win = Ext.create('mvc.view.sys.SysEm.Win',{
				title : this.title+'>修改',
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
		}			
},
onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
},
onSearch : function(){
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		this.onSearchDo(array);
},
onSearchAdv : function(){
		var win = Ext.create('mvc.view.sys.SysEm.WinSearch',{
			title : this.title+'>高级搜索',
			listCmp : this
		});
		win.show();
},
onSearchDo : function(array){
		this.getSelectionModel().deselectAll();
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
}
});