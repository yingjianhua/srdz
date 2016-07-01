Ext.define('mvc.view.wpt.WptDistributionRule.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WptDistributionRule',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
	if (this.roles.indexOf('set') != -1) {
		this.tbar = [{
			text : '设置',
			iconCls : 'edit-icon',
			itemId : this.oldId+'set',
			scope : this,
			handler : this.onSet,
		}];
	}
	this.columns = [
	                {text : '一级邀请人提成百分比',width : 200,dataIndex : 'bean.bonus1',sortable : true},
	                {text : '二级邀请人提成百分比',width : 200,dataIndex : 'bean.bonus2',sortable : true},
	                {text : '三级邀请人提成百分比',width : 200,dataIndex : 'bean.bonus3',sortable : true}
	                ];
	this.store=Ext.create('mvc.store.wpt.WptDistributionRule'); 
	this.store.remoteFilter = true;
	this.store.proxy.filterParam = 'filter';
	this.on({cellclick:mvc.Tools.onCellclick});
	this.listeners = {
			afterrender : this.onSearch
		}
	this.callParent(arguments);
},
onSet : function(){
	this.getView().getSelectionModel().selectAll();
	var selection = this.getView().getSelectionModel().getSelection()[0];
	console.log(selection)
	this.onSetWin(selection);
},
onSetWin : function(selection) {
	var win = Ext.create('mvc.view.wpt.WptDistributionRule.Win', {
		title : this.title + '>设置',
	});
	win.show();
	win.setActiveRecord(selection);
	win.on('create', function(form, data) {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wpt.WptDistributionRule', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
	}, this);
},
onSearch : function(){
		this.store.clearFilter();
}
});