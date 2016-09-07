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
               {text : '昵称',width : 100,dataIndex : 'nickname',sortable : true},
               {text : '头像',width : 100,dataIndex : 'imageUrl',sortable : true, renderer: function(v) {return "<img src='"+v+"' height='50px'/ >"}},
               {text : '性别',width : 100,dataIndex : 'sex',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OSex')},
               {text : '是否代言人',width : 100,dataIndex : 'isMember',sortable : true,renderer : function(v) {return v?"是":"否"}},
               {text : '一级邀请人',width : 100,dataIndex : 'invited1.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptMember.List'},
               {text : '二级邀请人',width : 100,dataIndex : 'invited2.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptMember.List'},
               {text : '推广二维码',width : 100,dataIndex : 'qrcode',sortable : true, renderer: function(v) {return "<img src='.."+v+"' height='50px'/ >"}},
               {text : '二维码到期时间',width : 140,dataIndex : 'qrcodeExpireTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
               {text : '关注时间',width : 140,dataIndex : 'subscribeTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')},
               {text : '国家',width : 100,dataIndex : 'country',sortable : true},
               {text : '省份',width : 100,dataIndex : 'province',sortable : true},
               {text : '城市',width : 100,dataIndex : 'city',sortable : true},
               {text : '备注',width : 100,dataIndex : 'rem',sortable : true},
	];
		this.store=Ext.create('mvc.store.wpt.WptMember');
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
		var bean = Ext.create('mvc.model.wpt.WptMember', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getSelectionModel().deselectAll();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);
}
});