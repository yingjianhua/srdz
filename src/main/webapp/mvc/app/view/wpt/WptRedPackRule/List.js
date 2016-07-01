Ext.define('mvc.view.wpt.WptRedPackRule.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WptRedPackRule',
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
	                {text : '商户名称',width : 100,dataIndex : 'bean.sendName',sortable : true},
	                {text : '红包祝福语',width : 100,dataIndex : 'bean.wishing',sortable : true},
	                {text : '活动名称',width : 100,dataIndex : 'bean.actName',sortable : true}
	                ];
	this.store=Ext.create('mvc.store.wpt.WptRedPackRule'); 
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
	this.onSetWin(selection);
},
onSetWin : function(selection) {
	var win = Ext.create('mvc.view.wpt.WptRedPackRule.Win', {
		title : this.title + '>设置',
	});
	win.show();
	win.setActiveRecord(selection);
	win.on('create', function(form, data) {
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wpt.WptRedPackRule', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
	}, this);
},
onSearch : function(){
	this.store.clearFilter();
}
});