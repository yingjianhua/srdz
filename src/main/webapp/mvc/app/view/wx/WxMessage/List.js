Ext.define('mvc.view.wx.WxMessage.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WxMessage',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('reply') != -1)
mainActs.push({
		text : '回复',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'reply',
		scope : this,
		handler : this.onReply,
		disabled : this.lock
	});
		if (this.roles.indexOf('check') != -1)
mainActs.push({
		text : '查看',
		iconCls : '请在Tb.accActOpt()中指定ICON',
		itemId : this.oldId+'check',
		scope : this,
		handler : this.onCheck,
		disabled : this.lock
	});
this.columns = [{text : '消息类型',width : 100,dataIndex : 'bean.wxmsgType',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OWxMsgType')}
	,{text : '消息方向',width : 100,dataIndex : 'bean.wxmsgDir',sortable : true,renderer : mvc.Tools.optRenderer('wx','Wx','OWxMsgDir')}
	,{text : '消息ID',width : 100,dataIndex : 'bean.msgId',sortable : true}
	,{text : '消息內容',width : 100,dataIndex : 'bean.content',sortable : true}
	,{text : '回复标志',width : 100,dataIndex : 'bean.isReply',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '收藏标志',width : 100,dataIndex : 'bean.isCollect',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '关注用户',width : 100,dataIndex : 'bean.wxUser',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wxex',mn : 'view.wx.WxUser.List'}
	,{text : '创建时间',width : 140,dataIndex : 'bean.createdTime',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wx.WxMessage'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '消息內容：'
			},{
				xtype : 'textfield',
				name : 'content'
			},'',{
				xtype : 'label',
				text : '收藏标志：'
			},{
				xtype : 'combo',
				name : 'isCollect',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				emptyText : form_empty_text,
				store : Ext.create('mvc.combo.sys.SysOYn')
			},'',{
				xtype : 'label',
				text : '关注用户：'
			},{
				xtype : 'beantrigger',
				name : 'wxUser',
				bean : 'WxUser',
				beanType : 'wx',
				emptyText : form_empty_text
			},'',{
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
		if (this.roles.indexOf('reply') != -1)
			this.down('#'+this.oldId+'reply').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('check') != -1)
			this.down('#'+this.oldId+'check').setDisabled(selected.length === 0);	
}
},
onReply : function(){
    var selection = this.getView().getSelectionModel().getSelection()[0];
    if (selection){
        var win = Ext.create('mvc.view.wx.WxMessage.Win',{
            title : this.title+'>回复',
            insFlag : true
        });
        onUpdateRecord = function(form, data) {
                var selection = this.getView().getSelectionModel().getSelection()[0];
                var bean = Ext.create('mvc.model.wx.WxMessage', data);
                Ext.apply(selection.data, bean.data);
                selection.commit();
                this.getView().select(selection);
                Ext.example.msg("","回复成功!");
            },
        win.on('create',onUpdateRecord,this);
        win.show();
        win.setActiveRecord(selection);
    }
},
onCheck : function(){
      var selection = this.getView().getSelectionModel().getSelection();
      if(selection) {
          var win = Ext.create('mvc.view.wx.WxMessage.WinCheck',{
              title : this.title+'>查看',
              pkey : selection[0].data["bean.pkey"],
              insFlag : false
          }); 
          win.show();
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
		var win = Ext.create('mvc.view.wx.WxMessage.WinSearch',{
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