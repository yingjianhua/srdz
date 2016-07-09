Ext.define('mvc.view.wpt.WptComboBanner.Win',{
extend : 'Ext.window.Window',
width : 230,
layout : 'fit',
form : null,
resizable : false,
modal : true,
iconCls : 'app-icon',
insFlag : true,
initComponent : function(){
	this.items =[{
		anchor : '100%',
		plain : true,
		xtype : Ext.create('mvc.view.wpt.WptComboBanner.Form',{	insFlag : this.insFlag})
	},{
		xtype : Ext.create('mvc.view.wpt.WptComboBanner.ListForm',{height : 500,border : false })
	}];
		this.buttonAlign = 'right',
this.buttons =[{
		text : '重置',
		scope : this,
		iconCls : 'win-refresh-icon',
		handler : this.onReset
	},{
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
		this.addEvents('create');
		this.form = this.items.items[0];
		this.lineTable = this.items.items[1];
},
setActiveRecord : function(record){
		this.form.activeRecord = record;
		if (record || this.form.activeRecord) {
			this.form.getForm().loadRecord(record);
			this.lineTable.store.filter([{'id':'filter','property':'combo','value':record.get('bean.pkey')}]);
			this.lineTable.store.sort("bean.sort", "ASC");
		} else {
			this.form.getForm().reset();
		}
},
onReset : function(){
		this.setActiveRecord(this.form.activeRecord);
},
onClose : function(){
		this.close();
},
onSave : function(){
		var form = this.form.getForm();
		if (form.isValid()) {
			form.submit({
				url : base_path+'/wpt_WptComboBanner_insOrUpd',
				submitEmptyText: false,
				type : 'ajax',
				params : this.storeValues(this.lineTable.store,{insFlag : this.insFlag}),
				success : function(form, action) {
					this.fireEvent('create', this, action.result);
					this.close();
				},
				failure : mvc.Tools.formFailure(),
				waitTitle : wait_title,
				waitMsg : wait_msg,
				scope : this
			});
		}
},
storeValues : function(store, params) {
	var fields = store.model.getFields(),
		fLen    = fields.length,
		i = 0,
		value,
		values = {};
	store.each(function(record) {
		for (f = 0; f < fLen; f++) {
			if (fields[f].name.indexOf('link.') != -1) //过滤关联带出的字段
				continue;
			value = record.get(fields[f].name);
			if (value || value===0){
    			if (typeof fields[f].outkey != 'undefined' || 
    					typeof fields[f].optCust != 'undefined')
					value = value.split(bean_split)[0];
				else if (typeof fields[f].isPct != 'undefined')
					value = value / fields[f].isPct;
        		values['bannerList'+'['+i+'].'+fields[f].mapping] = value;
			}
    	}
    	i++;
	});
	Ext.apply(values, params);
	return values;
}
});