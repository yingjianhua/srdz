Ext.define('mvc.tools.BeanTriggerCell', {
			extend : 'Ext.form.field.Trigger',
			alias : 'widget.beantriggercell',

			editable : false,
			trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
		    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
			
			
			beanType : 'sys',
			bean : null,
			beanName : null,
			beanValue : null,
			beanText : null,
			beanParams : null,
			grid : null,

			onTrigger1Click : function(){
				this.beanParams = null;
		        this.setValue();
		    },
			
			onTrigger2Click : function() {
				var win = Ext.create("mvc.view." + this.beanType + "."
						+ this.bean + ".Trigger");
				win.on('trigger', this.onTrigger, this);
				win.show();
				var store = win.down('grid').getStore();
				var array = [];
				array.push({
	            	id:'flds',
	                property: 'param',
	                value: '1=1'
	            });
	             if (this.grid.diySql){
	            	array.push({
		            	id:'diy',
		                property: 'diy',
		                value: this.grid.diySql
		            });
	            } else if(this.diySql){
	            	array.push({
	            		id:'diy',
		                property: 'diy',
		                value: this.diySql
	            	})
	            }
				store.filter(array);
			},

			initComponent : function() {
				this.callParent();
			},
			
			//回调 -- Cell上的一离开，编辑控件已经没了，直接操作表格
			onTrigger : function(data, params) {
				if (params)
					this.beanParams = params;
				this.setValue(data);
				var record = this.grid.getView().getSelectionModel().getSelection()[0];
				record.set(this.beanName,this.getValue());
			},

			setValue : function(value) {
				if (typeof value != "undefined" && value) {
					var args = value.split(bean_split);
					this.setBeanValue(args[0]);
					this.setBeanText(args[1]);
					this.callParent([args[1]]);
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
				return this.callParent();
			}
			
		});