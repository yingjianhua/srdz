Ext.define('mvc.model.Menu', {
			extend : 'Ext.data.Model',
			fields : ['id', 'text', 'cls', 'url','beanType' ,'roles'],
			proxy : {
				type:'ajax',
				url :base_path+'/sys_SysMenu_loadMenu'
			}
		})
