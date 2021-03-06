Ext.define('mvc.view.wpt.WptQrcodeRule.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WptQrcodeRule',
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
	                {text : '单笔消费金额',width : 100,dataIndex : 'single',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'},
	                {text : '累计消费金额',width : 100,dataIndex : 'amount',sortable : true,renderer : mvc.Tools.numberRenderer(),align : 'right'},
	                {text : '有效天数',width : 100,dataIndex : 'validityPeriod',sortable : true},
	                {text : '提前更新天数',width : 100,dataIndex : 'aheadUpdate',sortable : true}
	                ];
	this.store=Ext.create('mvc.store.wpt.WptQrcodeRule'); 
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
	var win = Ext.create('mvc.view.wpt.WptQrcodeRule.Win', {
		title : this.title + '>设置',
	});
	win.show();
	win.setActiveRecord(selection);
	win.on('create', function(form, data) {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wpt.WptQrcodeRule', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
	}, this);
},
onSearch : function(){
	this.store.clearFilter();
}
});