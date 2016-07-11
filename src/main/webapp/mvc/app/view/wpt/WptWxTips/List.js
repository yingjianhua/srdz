Ext.define('mvc.view.wpt.WptWxTips.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WptWxTips',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
	var mainActs = [];		
	mainActs.push({
			text : '新增',
			iconCls : 'ins-icon',
			itemId : this.oldId+'ins',
			scope : this,
			handler : this.onIns
		});
	mainActs.push({
			text : '删除',
			iconCls : 'del-icon',
			itemId : this.oldId+'del',
			scope : this,
			handler : this.onDel,
			disabled : this.lock
		});
	this.columns = [
			 {text : '昵称',width : 100,dataIndex : 'bean.nickname',sortable : true}
			,{text : '头像',width : 100,dataIndex : 'bean.imageUrl',sortable : true, renderer: function(v) {return "<img src='"+v+"' height='50px'/ >"}}
			,{text : '备注',width : 100,dataIndex : 'bean.rem',sortable : true}
			,{text : '关注状态',width : 60,dataIndex : 'bean.status',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OStatus')}
			];
			if (mainActs.length > 0)
				this.tbar=mainActs;
			this.store=Ext.create('mvc.store.wpt.WptWxTips'); 
			this.store.remoteFilter = true;
			this.store.proxy.filterParam = 'filter';
			this.on({cellclick:mvc.Tools.onCellclick});
	this.dockedItems=[{
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
},
listeners : {
	selectionchange : function(selModel, selected){
			this.down('#'+this.oldId+'del').setDisabled(selected.length === 0);	
}
},
onSaveRecord : function(form, data) {
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);	
},
onIns : function(){
	var me = this;
	function onTrigger(data, params) {
		Ext.Ajax.request({
			url : base_path+'/wpt_WptWxTips_ins?bean.pkey='+data.split(bean_split)[0],
			success : function (response, options) {
				var result = Ext.decode(response.responseText);
				if (result.success){
					console.log(result);
					me.store.insert(0,result);
					Ext.example.msg(msg_title, msg_text);
				}else{
					Ext.MessageBox.show({
						title : msg_title, 
						msg : result.msg,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
				}
			}
		});
	}
	var win = Ext.create("mvc.view.wx.WxUser.Trigger");
	win.on('trigger', onTrigger, this);
	win.show();
	var store = win.down('grid').getStore();
	store.load();
},
onDel : function(){
	console.log("onDel()")
		var selection = this.getView().getSelectionModel().getSelection();
		if (selection){
			var me = this;
			Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg, 
				function(btn) {
					if (btn != 'yes')
						return;
					var arr=new Array();
					var arrv = new Array();
					for(var i = 0; i < selection.length; i++){
						arr.push(selection[i].get('bean.pkey'));
						arrv.push(selection[i].get(BEAN_VERSION));
					}
					Ext.Ajax.request({
						url : base_path+'/wpt_WptWxTips_delMulti?pkeys='+arr.toString()+'&rowVersions='+arrv.toString(),
						success : function (response, options) {
							var result = Ext.decode(response.responseText);
							if (result.success){
								me.getStore().remove(selection);
								Ext.example.msg(msg_title, msg_del);
							}else{
								Ext.MessageBox.show({
									title : msg_title, 
									msg : result.msg,
									buttons : Ext.MessageBox.OK,
									icon : Ext.MessageBox.ERROR
								});
							}
						}
					});
				}
			);
		}
},
onSearchCancel : function(){
		this.getSelectionModel().deselectAll();
		this.store.clearFilter();
},
});