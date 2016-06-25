Ext.define('mvc.tools.BeanSelectorMulti', {
//打开一个窗口，多选返回，并将结果注入到grid中，
//使用时，需要覆盖 title，oBeanType, oBean, tBeanType，tBean, grid, mapping, diySql
	extend : 'Ext.window.Window',
	width : 700,
	height : 400,
	layout : 'fit',
	title : '选择器',
	resizable : true,
	modal : true,
	border : false,
	oBeanType : null,//原beanType
	oBean : null,//原bean
	tBeanType : null,//目标beanType
	tBean : null,//目标bean
	grid : null,
	mapping : null,//原数据和目标数据转换的映射关系
	diySql : null,
	initComponent : function(){
		var list = Ext.create("mvc.view." +this.oBeanType+ "." +this.oBean+ ".TriggerList", {
			diySql : this.diySql,
			mapping : this.mapping,
			onTriggerList : function(){
				var data = [];
				var selections = this.getView().getSelectionModel().getSelection();
				if(selections.length > 0) {
					Ext.each(selections, function(selection) {
						var model = {};
						Ext.each(this.mapping, function(map) {
							var val = map.orig;
							Ext.each(map.orig.split(bean_split), function(key) {
								val = val.replace(key, selection.get(key));
							})
							model[map.targ] = val;
						})
						data.push(model);
					}, this)
				}
				this.fireEvent('trigger', data, null);
			},
			listeners : {
				afterrender : function() {
					var array = [];
					array.push({
			        	id:'flds',
			            property: 'param',
			            value: '1=1'
			        });
			        if (this.diySql){
			        	array.push({
			            	id:'diy',
			                property: 'diy',
			                value: this.diySql
			            });
			        }
					this.getStore().filter(array)
				}
			}
		});
		list.on('trigger', this.onTrigger, this);
		this.items ={
		anchor : '100%',
			plain : true,
			xtype : list
		};
		this.callParent(arguments);
	},
	onTrigger : function(data, params){
		var models = [];
		Ext.each(data, function(model) {
			models.push(Ext.create("mvc.model." +this.tBeanType+ "." +this.tBean, model))
		}, this) 
		this.grid.getStore().insert(0, models);
		this.close();
	}
});