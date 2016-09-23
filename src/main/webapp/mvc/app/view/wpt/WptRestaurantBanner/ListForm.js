Ext.define('mvc.view.wpt.WptRestaurantBanner.ListForm',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
cellEditing : Ext.create('Ext.grid.plugin.CellEditing', { clicksToEdit: 1 }),
mainPkey : null,
multiSelect: true, 
viewConfig: {
    plugins: {
    	 ptype: 'gridviewdragdrop',  
    	 dragText:"拖拽来完成排序"
    }
},
height: 400,
width: 800,
renderTo: Ext.getBody(),
initComponent : function(){
		var t = this;
		var mainActs = [{
		xtype : 'form',
		items : [{
		         xtype : "filefield",
		         name : 'file',
		         buttonText : "新增",
		         buttonConfig: {
		                iconCls: 'ins-icon',
		            },
		         buttonOnly : true,
		         listeners: {
		        	 scope : this,
		        	 change : function(field, newv, oldv, epts) {
		        		 var me = this.down('form');
		        		 me.submit({
		        			 url : base_path+"/sys_SysAccessory_uploadImage" + '?widthLimit=' + 0,
		        			 submitEmptyText: false,
		 					 type : 'ajax',
		 					 scope : this,
		 					params : {insFlag : this.insFlag},
		        			 success : function(form, action) {
		        				 var store = this.getStore();
		        				 var model = Ext.create("mvc.model.wpt.WptRestaurantBanner",{
		        					 "imgUrl" : action.result.url
		        				 })
		        				 store.insert(0, model);
		        			 }
		        		 })
		        	 }
		         }
		}]
	},{
		text : '删除',
		iconCls : 'del-icon',
		scope : this,
		handler : this.onDel
	}];
		this.tbar = mainActs;		
		this.columns =[
		{
	text : '图片',
	width : 200,
	dataIndex : 'imgUrl',
	sortable : true,
	renderer : function(v,meta,record) {
		return "<img src='../" + v + "'width='180px' height='140px'>"
	}
}
//		,{text : '排序',width : 100,dataIndex : 'sort',sortable : true,align : 'right', editor : {
//			xtype : "numberfield"
//		}}
	],
		this.store=Ext.create('mvc.store.wpt.WptRestaurantBanner');
		this.store.pageSize = 0;
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.plugins = [this.cellEditing];
		this.callParent(arguments);	
},
onIns : function(){
},
onDel : function(){
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			this.getStore().remove(selection);
		}
}
});