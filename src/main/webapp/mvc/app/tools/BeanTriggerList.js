Ext.define('mvc.tools.BeanTriggerList', {
			extend : 'Ext.form.field.Trigger',
			alias : 'widget.beantriggerlist',

			editable : false,
			trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
		    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
			
			
			beanType : 'sys',
			bean : null,
			beanValue : null,
			beanText : null,
			beanParams : null,
			diySql : null,

			onTrigger1Click : function(){
				this.beanParams = null;
		        this.setValue();
		    },
			
			onTrigger2Click : function() {
				var win = Ext.create("mvc.view." + this.beanType + "."
						+ this.bean + ".Trigger");
				win.on('trigger', this.onTrigger, this);
				var store = win.down('tree-grid').getStore();
//				var array = [];
//				array.push({
//	            	id:'flds',
//	                property: 'param',
//	                value: '1=1'
//	            });
//	            if (this.diySql){
//	            	array.push({
//	            	id:'diy',
//	                property: 'diy',
//	                value: this.diySql
//	            });
//	            }
//				store.filter(array);
				store.getProxy().extraParams={"diySql":this.diySql};
//				if(this.diySql)
//					store.load({params:{diySql:this.diySql}});
				store.load();
				win.show();
			},

			initComponent : function() {
				this.callParent();
			},

			onTrigger : function(data, params) {
				if (params)
					this.beanParams = params;
				this.setValue(data);
			},

			setValue : function(value) {
				if (typeof value != "undefined" && value) {
					if (typeof value == "string"){
						var args = value.split(bean_split);
						this.setBeanValue(args[0]);
						this.setBeanText(args[1]);
						this.callParent([args[1]]);
					}else{
						/**
						 * 外键选择器，一般接受为字符串，如: 1##总机构；
						 * 但如股东类型-股东ID情史，即外键可能是A表或B表的情况；
						 * 那底层传值还是INT，但前台EXT则使用外键选择器，这种特殊情况需要在
						 * 对应的WIN中编码解决，因为在WIN加载RECORD时，这里return了，需要
						 * 直接大方法中对该选择器赋值
						 */
						return this;
					}
				} else{
					this.setBeanValue(null);
					this.setBeanText(null);
					this.callParent(arguments);
				}
				return this;
			},

			setBeanValue : function(value) {
				this.beanValue = value;
			},
			
			setBeanText : function(value) {
				this.beanText = value;
			},

			getBeanData : function() {
				var me = this,
		            data = null,
		            val;
		        if (!me.disabled && me.submitValue) {
		            val = me.getSubmitValue();
		            if (val !== null) {
		                data = {};
		                data[me.getName()] = this.beanValue+bean_split+this.beanText;
		            }
		        }
		        return data;
			},

			getSubmitValue : function() {
				return this.beanValue;
			},
			
			getValue : function() {
				if (this.beanValue)
					return this.beanValue+bean_split+this.beanText;
			}
			
		});