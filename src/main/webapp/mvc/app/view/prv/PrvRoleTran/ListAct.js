Ext.define('mvc.view.prv.PrvRoleTran.ListAct',{
	extend : 'Ext.panel.Panel',
	loadMask : true,
	mainPkey : null, //主表的主键值，取LIST的默认条件
	layout : 'border',
	initComponent : function(){
		//===============右侧面板内容
		this.items = [{
			xtype : 'panel',
			region : 'north',
			items : [{
				xtype : 'toolbar',
				items : ['->',{
						text : '保存',
						iconCls : 'win-save-icon',
						scope : this,
						handler : this.onSave
					},{
						text : '重置',
						iconCls : 'win-refresh-icon',
						scope : this,
						handler : this.onReset
					}]
			},{
				xtype : 'panel',
				layout: {type: 'hbox'},
				overflowY : 'scroll',
				items : [{
					xtype : 'button',text : '资源控制组',flex : 1.7
				},{
					xtype : 'button',text : '权限类型',flex : 1.7
				},{
					xtype : 'button',text : '天数',flex : 1.8 //多的一点为调整滚动条多出的部分
				}]
			}]
		},{
		//===============右侧面板-中间菜单项N
			region : 'center',
			xtype : 'panel',
			action : 'menuPanel',
			loadMask : true,
			overflowY : 'scroll'
		}]
		this.callParent(arguments);
	},
	
	//模块类型组件变更事件
	refreshMenu : function(){
		if (!this.mainPkey)
			return;
		var newpanel = this.getMenuPanel();
		if (newpanel){
			var spanel = this.down('panel[action=menuPanel]');
			spanel.destroy();
			this.add(newpanel).show();
			this.doLayout();
		}
	},
	
	resetMainPkey : function(mainPkey){
		this.mainPkey = mainPkey;
		this.refreshMenu();
	},
	
	getMenuPanel : function(type){
		var me = this;
		var cmpmenus = [];
		var newpanel;
		var myMask = new Ext.LoadMask(me, {msg:"Please wait..."});  
   		myMask.show(); 
		Ext.Ajax.request({
			
			async : false,
			url : base_path+ '/prv_PrvRoleTran_loadByRole?bean.role=' + me.mainPkey,
			method : 'GET',
			success : function(response) {
				var rtn = Ext.JSON.decode(response.responseText, true);
				for (i = 0; i < rtn.total; i++) {
					cmpmenus.push({
						xtype : Ext.create('mvc.view.prv.PrvRoleTran.ListActSub',{infos : rtn.items[i]})
					});
				}
				newpanel = Ext.create('Ext.panel.Panel',{
					region : 'center',
					xtype : 'panel',
					action : 'menuPanel',
					loadMask : true,
					overflowY : 'scroll',
					items : cmpmenus
				});
			},
			failure : function(response) {
				Ext.example.msg(msg_title, msg_ajax);
			}
		});
		myMask.hide();
		return newpanel;
	},
	
	onSave : function(){
		var lines = {};
		var i = 0;
		this.down('panel[action=menuPanel]').items.each(function(mp){
			mp.items.each(function(cp){
				if (cp.action == 'pkey'){
					cp.items.each(function(pkey){
						lines[line_pr+'['+i+'].pkey']=pkey.getValue();
					}) 
				}else if (cp.action == 'type'){
					cp.items.each(function(type){
						lines[line_pr+'['+i+'].type']=type.getValue();
					}) 
				}else if (cp.action == 'day'){
					cp.items.each(function(day){
						lines[line_pr+'['+i+'].day']=day.getValue();
					}) 
				}
			});
			i++;
		});
		var combo = this.down('combobox[name=type]');
		Ext.Ajax.request({
			url : base_path+ '/prv_PrvRoleTran_updCtrl',
			method : 'GET',
			params : lines,//{lines : lines, type : combo.getValue()},
			scope : this,
			success : function (response, options) {
				var result = Ext.decode(response.responseText);
				if (result.success){
					Ext.example.msg(msg_title, '权限修改成功');
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
	},
	
	onReset : function(){
		this.refreshMenu();
	}
});