Ext.define('mvc.tools.BeanTriggerMulti', {
			extend : 'Ext.form.field.Trigger',
			alias : 'widget.beantriggermulti',

			editable : false,
			trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
		    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
			
			
			beanType : 'sys',
			bean : null,
			beanValue : null,
			beanText : null,
			beanParams : null,

			onTrigger1Click : function(){
				this.beanParams = null;
		        this.setValue();
		    },
			
			onTrigger2Click : function() {
				var win = Ext.create("mvc.view." + this.beanType + "."
						+ this.bean + ".TriggerMulti");
				win.on('trigger', this.onTrigger, this);
				win.show();
				var store = win.down('grid').getStore();
				store.filter([{
	            	id:'flds',
	                property: 'flds',
	                value: '1=1'
	            }]);
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
					var params = value.split(',');
					beanValue = '';
					beanText = '';
					for(i = 0; i < params.length; i++) {
						var args = params[i].split(bean_split);
							beanValue += args[0];
							beanText += args[1];
							if (i < params.length - 1) {
								beanValue += ',';
								beanText += ',';
							}
					}
					this.setBeanValue(beanValue);
					this.setBeanText(beanText);
					this.callParent([beanText]);
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
			
			getBeanText : function() {
				return this.beanText;
			},

			getBeanData : function() {
				var me = this,
		            data = null,
		            val;
		        if (!me.disabled && me.submitValue) {
		            val = me.getSubmitValue();
		            if (val !== null) {
		            	var bvs = me.beanValue.split(',');
		            	var bts = me.beanText.split(',');
		            	var result = '';
		            	for(i = 0; i < bvs.length; i++) {
		            		result += bvs[i] + bean_split + bts[i];
		            		if (i < bvs.length - 1)
		            			result += ',';
		            	}
		                data = {};
		                data[me.getName()] = result;
		            }
		        }
		        return data;
			},

			getSubmitValue : function() {
				return this.beanValue;
			}
			
		});