Ext.define('mvc.combo.wm.WmONewsType',{
extend : 'Ext.data.Store',
fields : ['value','text'],
data : [
        {value : 0,text : '普通图文'},
        {value : 1,text : '自定义链接'},
        {value : 2,text : '扩展链接'}
	]
});