Ext.define('mvc.view.portal.PilotageChart', {
	extend : 'Ext.DataView',
	alias : 'widget.pilotagechart',
	tpl  : new Ext.XTemplate(
			'<div id="phones" width="100%" heigth="100%">',
			'<ul>',
	      '<tpl for=".">',
	          '<li class="phone">',
	              '<img width="100" height="100" src="{url}" />',
	              '<br/>',
	              '<span>{name}</span>',
	              '<input type="hidden" value="{path}"/>',
	              '<input type="hidden" value="{type}"/>',
	          '</li>',
	      '</tpl>',
	     '</ul>',
	     '</div>'
  ),
  overClass   : 'phone-hover',
  itemSelector: 'li.phone',
  singleSelect: true,
  multiSelect : true,
  autoScroll  : true,
	initComponent : function() {
		this.listeners = {  
			scope: this,
			itemclick: {
				fn : this.onIconSelect,
			}
		}
		var store = new Ext.data.ArrayStore({
      proxy   : new Ext.data.MemoryProxy(),
      fields  : ['name','url','path','type'],
      data :[
   		      {name:"自定义菜单",url:"../images/menu.png",path:"view.wx.WxMenu.List",type:"wx"},
   		      {name:"发送消息",url:"../images/xiaoxi.png",path:"view.wx.WxMenu.List",type:""},
   		      {name:"图文素材",url:"../images/img.png",path:"view.wm.WmNews.List",type:"wm"},
   		      {name:"关注用户",url:"../images/user.png",path:"view.wx.WxUser.List",type:"wxex"},
   		      {name:"大转盘",url:"../images/user.png",path:"expand_wa_WaActivity_showWeb"},
   		      //{name:"大转盘",url:"../images/user.png",path:"wa_WaActivity_name"},
   		      {name:"刮刮乐",url:"../images/user.png",path:"wa_WaScratch_name"},
   		      {name:"群发消息",url:"../images/xiaoxi.png",path:"view.wx.WxMassMessage.List", type:"wxex"}
  		  ]
		});
		this.store = store;
		this.callParent();
	},
	onIconSelect: function(bean, record, item, index, e,eOpts) {
		if(record.get('type')!=null&&record.get('type')!=""){			
			mvc.Tools.onCreateTab(record.get('type'),record.get('path'),'','');
		}else{
			window.open(record.get('path'), record.get('name'));
		}
 }
})