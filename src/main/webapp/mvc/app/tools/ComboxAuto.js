Ext.define('mvc.tools.ComboxAuto', {
	extend : 'Ext.form.field.ComboBox',
	alias : 'widget.comboauto',
	emptyText : form_empty_text,
	minChars : 0,
	diySql : null,
	trigger1Cls : Ext.baseCSSPrefix + 'form-clear-trigger',
	trigger2Cls : Ext.baseCSSPrefix + 'form-search-trigger',
	triggerAction : 'last',
	urlExt : null,
	hasBlur : true,
	reload: false,
	tplCn : '<tpl for="."><div class="x-boundlist-item"><div>{code}  {name}</div></div></tpl>',
	tplGoods : '<tpl for="."><div class="x-boundlist-item"><div>{code}  {name}</div><div>{spec}</div></div></tpl>',
	
	onTrigger1Click : function() {
		this.setValue(null);
		this.focus();
	},
	initComponent : function() {
		var me = this;
		var name = me.name;
		var fields = me.fields;
		if (this.listConfig){
			if (this.urlExt=='gs.GsGoods')
				this.listConfig['tpl']=this.tplGoods;
			else
				this.listConfig['tpl']=this.tplCn;
		}else{
			if (this.urlExt=='gs.GsGoods')
				this.listConfig = {tpl : this.tplGoods};
			else
				this.listConfig = {tpl : this.tplCn};
		}
		if (!me.autoFocus && me.hasBlur) {
			me.listeners = {
				blur : function(v) {//这句话有被调用到吗？
					if (!v.value) 
						v.setRawValue(null);
				}
			};
		}
		var store = Ext.create('Ext.data.Store', {
			fields : fields,
			pageSize : 20,
			listeners : {
				beforeload : function(store, operation) {
					var array = [];
					var param ={};
					param[me.displayField]=me.rawValue;
					array.push(param);
					if (me.diySql) {
						array.push({
							DIY : me.diySql
						});
					}
					store.getProxy().extraParams.params = Ext.encode(array);
				}
			},
			proxy : {
				type : 'ajax',
				url : me.url,
				reader : {
					type : 'json',
					root : 'items'
				}
			}
		});
		var displayField = me.textField;
		if (!displayField) {
			displayField = me.name;
		}
		var queryParam = me.param;
		if (!queryParam) {
			queryParam = displayField;
		}
		var afterLabelTextTpl = required;
		if (me.allowBlank) {
			afterLabelTextTpl = null;
		}
		Ext.apply(me, {
			displayField : displayField,
			queryParam : queryParam,
			store : store,
			afterLabelTextTpl : afterLabelTextTpl
		});
		this.callParent(arguments);
		this.on({
			expand : function(field, e) {
				if(me.reload) {
					me.reload = false;
					me.store.load({
						params : {
							code : me.rawValue,
							DIY : me.diysql
						}
					});
				}
			}
		})
	},

	// 加载外键选择对话框
	onTrigger2Click : function() {
		var win = Ext.create("mvc.view." + this.urlExt + ".Trigger");
		win.on('trigger', this.onTrigger, this);
		win.show();
		var store = win.down('grid').getStore();
		var array = [];
		array.push({
			id : 'flds',
			property : 'param',
			value : '1=1'
		});
		if (this.diySql) {
			array.push({
				id : 'diy',
				property : 'diy',
				value : this.diySql
			});
		}
		store.filter(array);
	},

	// params为预留参数
	onTrigger : function(data, params) {
		this.store.removeAll();
		this.reload = true,
		this.setValue(data);
		var me = this;
		var d = new Ext.util.DelayedTask(function(){  
			me.focus();
        });  
        d.delay(100);
	},


	setValue : function(value, doSelect) {
		if (typeof value != "undefined" && value && typeof value != "object"
				&& value.indexOf(bean_split) != -1) {
			var dataTs = value.split(bean_split);
			var rd = null;
			this.store.each(function(record) {
				if (record.get('pkey') == dataTs[0]) {
					rd = record;
				}
			});
			if (rd == null) {
				var obj = {};
				obj[this.fields[0]] = dataTs[0];
				obj[this.fields[1]] = dataTs[1];
				this.store.insert(0, obj);
				rd = this.store.getAt(0);
			}
			this.setValue(rd);
		} else {
			if(typeof value=="string" && value !== '') {
				value = this.store.getAt(0);
			}
			this.callParent(arguments);
		}
		return this;
	}
});