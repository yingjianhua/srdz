Ext.define('mvc.view.wpt.WptMember.ListMain',{
extend : 'Ext.grid.Panel',
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
this.columns =[
               {text : '昵称',width : 100,dataIndex : 'bean.nickname',sortable : true},
               {text : '性别',width : 100,dataIndex : 'bean.sex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex')},
               {text : '城市',width : 100,dataIndex : 'bean.city',sortable : true},
               {text : '省份',width : 100,dataIndex : 'bean.province',sortable : true},
               {text : '国家',width : 100,dataIndex : 'bean.country',sortable : true},
               {text : '头像',width : 100,dataIndex : 'bean.imageUrl',sortable : true, renderer: function(v) {return "<img src='"+v+"' height='50px'/ >"}},
               {text : '备注',width : 100,dataIndex : 'bean.rem',sortable : true},
               {text : '关注时间',width : 140,dataIndex : 'bean.subscribeTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
	];
		this.store=Ext.create('mvc.store.wx.WxUser');
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
		this.callParent(arguments);},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wx.WxUser', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
}
});