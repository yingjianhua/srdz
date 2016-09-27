Ext.define('mvc.view.wpt.WptHeadline.List',{
extend : 'Ext.grid.Panel',
oldId : 'btn_WptHeadline',
lock : true,
disableSelection : false,
loadMask : true,
multiSelect : true,
roles : '',
selModel : {selType : 'checkboxmodel'},
viewConfig : {enableTextSelection : true},
initComponent : function(){
var mainActs = [];		if (this.roles.indexOf('ins') != -1)
mainActs.push({
		text : '新增',
		iconCls : 'ins-icon',
		itemId : this.oldId+'ins',
		scope : this,
		handler : this.onIns
	});
		if (this.roles.indexOf('upd') != -1)
mainActs.push({
		text : '修改',
		iconCls : 'upd-icon',
		itemId : this.oldId+'upd',
		scope : this,
		handler : this.onUpd,
		disabled : this.lock
	});
		if (this.roles.indexOf('del') != -1)
mainActs.push({
		text : '删除',
		iconCls : 'del-icon',
		itemId : this.oldId+'del',
		scope : this,
		handler : this.onDel,
		disabled : this.lock
	});
		if (this.roles.indexOf('edit') != -1)
mainActs.push({
		text : '编辑',
		iconCls : 'edit-icon',
		itemId : this.oldId+'edit',
		scope : this,
		handler : this.onEdit,
		disabled : this.lock
	});
this.columns = [{text : '城市',width : 100,dataIndex : 'city.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptCity.List'}
	,{text : '区域',width : 100,dataIndex : 'cityline.pkey',sortable : true,renderer : mvc.Tools.beanRenderer()}
	,{text : '主题',width : 100,dataIndex : 'banquet.pkey',sortable : true,renderer : mvc.Tools.beanRendererHref(),md : 'wpt',mn : 'view.wpt.WptBanquet.List'}
	,{text : '标题',width : 100,dataIndex : 'title',sortable : true}
	,{text : '图片',width : 100,dataIndex : 'imgUrl',sortable : true,renderer:function(v) {return "<img src='../"+v+"'width='90px' height='70px'>"}}
	,{text : '时间',width : 100,dataIndex : 'date',sortable : true,renderer : Ext.util.Format.dateRenderer('Y-m-d')}
	,{text : '链接',width : 100,dataIndex : 'url',sortable : true}
	,{text : '置顶',width : 100,dataIndex : 'top',sortable : true,renderer : mvc.Tools.optRenderer('sys','Sys','OYn')}
	,{text : '排序',width : 100,dataIndex : 'sort',sortable : true}
	];
		if (mainActs.length > 0)
			this.tbar=mainActs;
		this.store=Ext.create('mvc.store.wpt.WptHeadline'); 
		this.store.remoteFilter = true;
		this.store.proxy.filterParam = 'filter';
		this.on({cellclick:mvc.Tools.onCellclick});
this.dockedItems=[{
		dock : 'top',
		xtype : 'toolbar',
		items : [{
				xtype : 'label',
				text : '城市：'
			},{
				xtype : 'beantrigger',
				name : 'city.pkey',
				bean : 'WptCity',
				beanType : 'wpt',
				emptyText : form_empty_text,
				listeners : {
					scope : this,
					change : function(field,newv,oldv){
						var cl = this.down('[name=cityline.pkey]');
						var store = cl.getStore()
						store.actWhere = 'city='+newv.split(bean_split)[0];
						store.getProxy().extraParams={"sarg1":store.actWhere};
						cl.setValue(null);
						cl.getStore().load();
				} }
			},'',{
				xtype : 'label',
				text : '区域：'
			},
				mvc.Tools.crtComboTrigger(false,'wpt/resource/cityLine','1=2',{
							name : 'cityline.pkey'
						})
			,'',{
				xtype : 'label',
				text : '标题：'
			},{
				xtype : 'textfield',
				name : 'title'
			},'',{
				xtype : 'label',
				text : '置顶：'
			},{
				xtype : 'combo',
				name : 'top',
				mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,
				typeAhead : true,
				editable : false,
				emptyText : form_empty_text,
				store : Ext.create('mvc.combo.sys.SysOYn')
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
		if (this.roles.indexOf('upd') != -1)
			this.down('#'+this.oldId+'upd').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('del') != -1)
			this.down('#'+this.oldId+'del').setDisabled(selected.length === 0);	
		if (this.roles.indexOf('edit') != -1)
			this.down('#'+this.oldId+'edit').setDisabled(selected.length === 0);	
}
},
onSaveRecord : function(form, data){
		this.store.insert(0,data);
		this.getView().select(0);
		Ext.example.msg(msg_title, msg_text);	
},
onIns : function(){
		var win = Ext.create('mvc.view.wpt.WptHeadline.Win',{
			title : this.title+'>新增'
		});
		win.on('create',this.onSaveRecord,this);
		win.show();		
},
onUpdateRecord : function(form, data){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		var bean = Ext.create('mvc.model.wpt.WptHeadline', data);
		Ext.apply(selection.data,bean.data);
		selection.commit();
		this.getView().select(selection);
		Ext.example.msg(msg_title, msg_text);			
},
onUpd : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		this.onUpdWin(selection);
},
onUpdRow : function(grid, rowIndex){
		var selection = this.getStore().getAt(rowIndex);
		this.getView().deselect(this.getView().getSelectionModel().getSelection());
		this.getView().select(selection);
		this.onUpdWin(selection);			
},
onUpdWin : function(selection){
		if (selection){
			var win = Ext.create('mvc.view.wpt.WptHeadline.Win',{
				title : this.title+'>修改',
				insFlag : false
			});
			win.on('create',this.onUpdateRecord,this);
			win.show();
			win.setActiveRecord(selection);
		}			
},
onDel : function(){
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
						arr.push(selection[i].get('pkey'));
						arrv.push(selection[i].get(BEAN_VERSION));
					}
					Ext.Ajax.request({
						url : base_path+'/wpt/resource/headline_del',
						params : {
							"bean.pkey" : selection[0].get("pkey"),
							"bean.rowVersion" : selection[0].get("rowVersion")
						},
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
onDelRow : function(grid, rowIndex){
		var me = this;
		var row = me.getStore().getAt(rowIndex);
		Ext.MessageBox.confirm(msg_confirm_title, msg_confirm_msg,
			function(btn) {
				if (btn != 'yes')
					return;
				Ext.Ajax.request({
					url : base_path+'/wpt/resource/headline_del',
					params : {
						"bean.pkey" : selection[0].get("pkey"),
						"bean.rowVersion" : selection[0].get("rowVersion")
					},
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
},
onEdit : function(){
		var selection = this.getView().getSelectionModel().getSelection()[0];
		if (selection){
			var win = Ext.create('mvc.view.wpt.WptHeadline.WinEdit',{
				title : this.title+'>编辑'
			});
			win.show();
			win.on('create', this.onUpdateRecord, this);
			win.setActiveRecord(selection);
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
		var win = Ext.create('mvc.view.wpt.WptHeadline.WinSearch',{
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