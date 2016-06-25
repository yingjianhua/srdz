Ext.define('mvc.view.Header', {
	extend : 'Ext.panel.Panel',
	layout : {
		type : 'border'
	},
	region : 'north',
	height : 60,
	border : false,
	initComponent : function() {
		this.getLoginMsg();
		this.items = [{
					region : 'west',
					width : 260,
					border : false,
					bodyCls : 'header',
					html : '<img style="margin: 7px 0 0 10px" src="'+base_path+'/images/ny_logo.png"/>'
				}, {
					xtype : 'panel',
					region : 'center',
					border : false,
					items : [{xtype : 'toolbar',
						id : 'headbar',
						region : 'north',
						cls : 'header',
						border : false,
						padding : '0 0 0 0',
						items : this.getNav()
						}]
				}, {
					xtype : 'panel',
					region : 'east',
					bodyCls : 'header',
					layout : 'anchor',
					border : false,
					width : 150,
					items : [{
						xtype : 'panel',
						border : false,
						bodyCls : 'headbar',
						margin : '5 0 0 10',
						items : [
								{
									xtype : 'label',
									cls : 'login-user',
									height : 15,
									text : ' 欢迎您，' + lg_user_name
								}]
					},{
						xtype : 'toolbar',
						id : 'headfunc',
						border : false,
						align : 'center',
						cls : 'headfunc',
						items : [
								{
									xtype : 'button',
//									text : '首页',
									scope : this,
									icon: '../images/sytb.png',
									padding : 5,
									handler : function() {
										var mainTab = Ext.getCmp('mainTab');
										var tab = mainTab
												.getComponent('HomePage');
										mainTab.setActiveTab(tab);
									}
								}, {
									xtype : 'button',
//									text : '修改密码',
									scope : this,
									icon: '../images/xgmm.png',
									padding : 5,
									handler : function() {
										var win = Ext.create(
												'mvc.view.sys.SysUser.WinPwdMy', {
													title : '修改密码'
												});
										win.show();
									}
								}, {
									xtype : 'button',
//									text : '注销',
									icon: '../images/tctb.png',
									padding : 5,
									scope : this,
									handler : function() {
										Ext.Ajax.request({
													url : base_path+'/sys_SysUser_loginout',
													success : function(response) {
														window.location = 'login.jsp';
													}
												});
									}
								}]
					}]
				}]
		this.callParent(arguments);
	},
	getLoginMsg : function() {
		Ext.Ajax.request({
				async : false,
				url : base_path+'/sys_SysUser_getLoginMsg',
				method : 'GET',
				success : function(response) {
					rtn = Ext.JSON.decode(response.responseText, true);
    				if (rtn.success){
    					lg_user_pkey = rtn.userPkey;
						lg_user_name = rtn.userName;
						lg_dept_pkey = rtn.deptPkey;
						lg_dept_name = rtn.deptName;
						lg_cell_pkey = rtn.cellPkey;
						lg_cell_name = rtn.cellName;
						lg_org_pkey = rtn.orgPkey;
						lg_org_name = rtn.orgName;
						sys_menus = rtn.menus.split(',');
						sys_menuOBJ = rtn.menusObj;
    				}else{
    					Ext.MessageBox.show({
								title : msg_title, 
								msg : rtn.msg,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.ERROR
							});
    				}
				}
			});
	},
	getNav : function() {
		var types = '';
		Ext.Ajax.request({
			async : false,
			url : base_path+'/sys_SysMenu_initAct',
			method : 'GET',
			success : function (response, options) {
				types = response.responseText;
			}
		});
		var strs = types.split(',');
		var nav = [];
		Ext.Array.each(sys_menus,function(arg){
			var enable = true;
			Ext.Array.each(strs,function(str){
				if(str == arg)
					enable = false;
			});
			if(!enable)
				nav.push({
					xtype : 'button',
					text:sys_menuOBJ[arg],
					icon:'../images/' + arg + '.png',
					id:'nav_'+arg,
					scale: 'large',
					margin: 0,
					iconAlign : 'top',
					disabled : enable,
					//border : false,
					fxcType : arg,
					navStrs : strs,
					handler : function(){
						Ext.getCmp("myWest").show();
						Ext.Array.each(this.navStrs,function(str){
							if(str != this.fxcType){
								Ext.getCmp("menu_"+str).hide();
							}
						});
						//var mainTab = Ext.getCmp("mainTab");
						//var tab = mainTab.getComponent("guide_"+this.fxcType);
						//Ext.example.msg("","guide_"+this.fxcType);
						//if(!tab) {
						//	tab = mainTab.add(Ext.create("mvc.guide.guide_"+this.fxcType));
						//}
						//mainTab.setActiveTab(tab);
						Ext.getCmp("menu_"+this.fxcType).onLoadFirst();
						Ext.getCmp("menu_"+this.fxcType).show();
					}
				});
		});
		return nav;
	}
});