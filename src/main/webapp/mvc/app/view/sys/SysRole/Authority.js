Ext.define('mvc.view.sys.SysRole.Authority', {
	extend : 'Ext.window.Window',
	width : 700,
	height : 500,
	layout : 'fit',
	title : '权限设置',
	resizable : true,
	modal : true,
	border : false,
	cls : 'x-checkgroup-boxLabel',
	initComponent : function() {
		this.items = [{
			xtype : 'tabpanel',
			items : [{
						xtype : Ext.create('mvc.view.sys.SysRole.AuthoritySet',{title:'系统管理', pkey:this.pkey, jyType:'sys'})
					},{
						xtype : Ext.create('mvc.view.sys.SysRole.AuthoritySet',{title:'财务管理', pkey:this.pkey, jyType:'gl'})
					},{
						xtype : Ext.create('mvc.view.sys.SysRole.AuthoritySet',{title:'库存管理', pkey:this.pkey, jyType:'gs'})
					},{
						xtype : Ext.create('mvc.view.sys.SysRole.AuthoritySet',{title:'货物管理', pkey:this.pkey, jyType:'goods'})
					},{
						xtype : Ext.create('mvc.view.sys.SysRole.AuthoritySet',{title:'目标管理', pkey:this.pkey, jyType:'tp'})
					},{
						xtype : Ext.create('mvc.view.sys.SysRole.AuthoritySet',{title:'销售管理', pkey:this.pkey, jyType:'sal'})
					},{
						xtype : Ext.create('mvc.view.sys.SysRole.AuthoritySet',{title:'调拨管理', pkey:this.pkey, jyType:'mv'})
					},{
						xtype : Ext.create('mvc.view.sys.SysRole.AuthoritySet',{title:'出纳管理', pkey:this.pkey, jyType:'rp'})
					}]
		}];
		this.buttonAlign = 'right';
		this.buttons = [{
			text : '关闭',
			scope : this,
			iconCls : 'win-close-icon',
			handler : this.onClose
		},{
			text : '保存',
			scope : this,
			iconCls : 'win-save-icon',
			handler : this.onSave
		}];
		this.callParent(arguments);
	},
	onClose : function(){
		this.close();
	},
	onSave : function(){
		var lines = '';
		this.items.each(function(tp){
			tp.items.each(function(comp){
				comp.items.each(function(fs){
					fs.items.each(function(cbg){
						cbg.items.each(function(cb){
							if(cb.getValue()==true)
								lines += cb.inputValue + ",";
						});
					});
				});
			});
		});
		Ext.Ajax.request({
			url : base_path+ '/sys_SysRole_updCtrl?pkey=' + this.pkey + '&lines=' + lines,
			method : 'GET',
			scope : this,
			success : function(response) {
				this.close();
				Ext.example.msg(msg_title, '权限修改成功');
			},
			failure : function(response) {
				Ext.example.msg(msg_title, msg_ajax);
			}
		});
	}
});