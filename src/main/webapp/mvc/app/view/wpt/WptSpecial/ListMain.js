Ext.define('mvc.view.wpt.WptSpecial.ListMain',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
		this.columns =[
		       {text : '标题',width : 100,dataIndex : 'bean.title',sortable : true},
               {text : '城市',width : 100,dataIndex : 'bean.city',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptCity.List'},
               {text : '忽略城市',width : 100,dataIndex : 'bean.ignoreCity',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')},
               {text : '顶图',width : 150,dataIndex : 'bean.topImgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='125px' height='70px'>"}},
               {text : '底图',width : 130,dataIndex : 'bean.baseImgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='105px' height='70px'>"}},
               {text : '介绍',width : 100,dataIndex : 'bean.intro',sortable : true},
               {text : '排序',width : 100,dataIndex : 'bean.sort',sortable : true}
        ];
		this.store=Ext.create('mvc.store.wpt.WptSpecial');
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
			items : [{xtype : Ext.create('mvc.tools.ComboxPaging',{myList : this})}]
		}];
		this.callParent(arguments);
},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wpt.WptSpecial', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
},
onUpdRow : function(grid, rowIndex){
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);
},
onUpdWin : function(selection){
		if (selection){
			var win = Ext.create('mvc.view.wpt.WptSpecial.Win',{
				title : this.title+'>修改',
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
		}
},
onDelRow : function(grid, rowIndex){
		var me = this;
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				var row = me.getStore().getAt(rowIndex);
				Ext.Ajax.request({
					url : '/wpt_WptSpecial_del?pkey='+row.get('bean.pkey')+'&rowVersion='+row.get(BEAN_VERSION),
					success : function (response, options) {
						var result = Ext.decode(response.responseText);
						if (result.success){
							me.getStore().removeAt(rowIndex);
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
});