Ext.define('mvc.view.sys.SysPersonLink.List',{
	extend : 'Ext.grid.Panel',
	oldId : 'btn_SysPersonLink',
	lock : true,
	disableSelection : false,
	loadMask : true,
	multiSelect : true,
	roles : '',
	selModel : {selType : 'checkboxmodel'},
	initComponent : function(){
		
		var mainActs = [];
		this.columns = [ 
			{text : '名称', width : 100, dataIndex : 'bean.name', sortable : true},
			{text : '所属对象', width : 100, dataIndex : 'bean.tbObjInt', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '个人证件类型', width : 100, dataIndex : 'bean.peCardType', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OCardPersonType')},
			{text : '个人证件号码', width : 100, dataIndex : 'bean.peCardNumb', sortable : true},
			{text : '个人常用手机', width : 100, dataIndex : 'bean.peMobile', sortable : true},
			{text : '个人其它手机', width : 100, dataIndex : 'bean.peMobileOther', sortable : true},
			{text : '个人邮箱', width : 100, dataIndex : 'bean.peEmail', sortable : true},
			{text : '个人微信', width : 100, dataIndex : 'bean.peWx', sortable : true},
			{text : '个人QQ', width : 100, dataIndex : 'bean.peQq', sortable : true},
			{text : '个人微博', width : 100, dataIndex : 'bean.peWb', sortable : true},
			{text : '个人MSN', width : 100, dataIndex : 'bean.peMsn', sortable : true},
			{text : '个人性别', width : 100, dataIndex : 'bean.peSex', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OSex')},
			{text : '个人出生日期', width : 100, dataIndex : 'bean.peBirthday', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d')},
			{text : '个人婚姻状况', width : 100, dataIndex : 'bean.peMerry', sortable : true, renderer : mvc.Tools.optRenderer('sys','Sys','OMerry')},
			{text : '个人学历', width : 100, dataIndex : 'bean.peEdu', sortable : true},
			{text : '个人学位', width : 100, dataIndex : 'bean.peDegree', sortable : true},
			{text : '个人职称', width : 100, dataIndex : 'bean.pePositionalTitle', sortable : true},
			{text : '个人驾照类别', width : 100, dataIndex : 'bean.peDrivingLicense', sortable : true},
			{text : '个人党派', width : 100, dataIndex : 'bean.peParty', sortable : true},
			{text : '个人信仰', width : 100, dataIndex : 'bean.peBelief', sortable : true},
			{text : '公司公司名称', width : 100, dataIndex : 'bean.ofCompanyName', sortable : true},
			{text : '公司所在部门', width : 100, dataIndex : 'bean.ofDeptName', sortable : true},
			{text : '公司职务', width : 100, dataIndex : 'bean.ofPost', sortable : true},
			{text : '公司电话', width : 100, dataIndex : 'bean.ofTel', sortable : true},
			{text : '公司传真', width : 100, dataIndex : 'bean.ofFax', sortable : true},
			{text : '公司网址', width : 100, dataIndex : 'bean.ofWebsite', sortable : true},
			{text : '公司地址', width : 100, dataIndex : 'bean.ofAddr', sortable : true},
			{text : '公司邮编', width : 100, dataIndex : 'bean.ofZipCode', sortable : true},
			{text : '备注', width : 100, dataIndex : 'bean.rem', sortable : true},
			{text : '更新员', width : 100, dataIndex : 'bean.updatedBy', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '更新时间', width : 140, dataIndex : 'bean.updatedDateTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
			{text : '建档员', width : 100, dataIndex : 'bean.createdBy', sortable : true, renderer : mvc.Tools.beanRenderer()},
			{text : '建档时间', width : 140, dataIndex : 'bean.createdDateTime', sortable : true, renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
		];
		this.tbar=mainActs;
		this.store=Ext.create('mvc.store.sys.SysPersonLink');
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.dockedItems=[{
			dock : 'top', 
			xtype : 'toolbar',
			items : [{
				xtype : 'label',
				text : '名称：'
			},{
				xtype : 'textfield',
				name : 'name'
			},' ',{
				xtype : 'button',
				text : '撤销',
				scope : this,
				iconCls : 'win-close-icon',
				handler : this.onSearchCancel
			},{
				xtype : 'button',
				text : '搜索',
				scope : this,
				iconCls : 'win-ok-icon',
				handler : this.onSearch
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
		selectionchange : function(selModel, selected) {
			
		}
	},
	
	
	
	
	onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		mvc.Tools.searchClear(this.down('toolbar'));
		this.store.clearFilter();
	},
	onSearch : function(){
		this.getSelectionModel().deselectAll();
		var array = mvc.Tools.searchValues(this.down('toolbar'));
		if (array.length == 0){
			this.store.clearFilter();
			return;
		}
		this.store.clearFilter(true);
		this.store.filter(array);
	}
});
