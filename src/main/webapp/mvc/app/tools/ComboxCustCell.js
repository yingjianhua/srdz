Ext.define('mvc.tools.ComboxCustCell', {
			extend : 'Ext.form.field.ComboBox',
			alias : 'widget.combocustcell',
			//源代码选择事件查询比较时，有BUG，默认为undefine与空值比较，所以STORE还会自动加载一次
			lastQuery : '', 
			custName : '',
			custValue : '',
			trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
			trigger2Cls: Ext.baseCSSPrefix + 'form-arrow-trigger',

			setValue : function(value) {
				if (typeof value != "undefined" && value) {
					/**
					 * 如是OBJECT，是STORE的RECORD对象，选择更改时触发
					 * 1##本科，是FORM初始化数据时触发
					 */
					if (typeof value == "object"){
						var record = arguments[0][0].data;
						this.custValue = record['value'];
						this.custName = record['text'];
						this.callParent(arguments);
					}else{
						var args = value.split(bean_split);
						this.custValue = args[0];
						this.custName = args[1];
						this.callParent([args[0]]);
					}
				} else{
					this.custValue = '';
					this.custName = '';
					this.callParent();
				}
				return this;
			},
			
			getValue : function(){
				if (this.custValue != '')
					return this.custValue+bean_split+this.custName;
				return this.callParent();
			},
			
			onTrigger1Click : function() {
				this.setValue(null);
			},
			
			onTrigger2Click: function() {
		        var me = this;
		        if (!me.readOnly && !me.disabled) {
		            if (me.isExpanded) {
		                me.collapse();
		            } else {
		                me.onFocus({});
		                if (me.triggerAction === 'all') {
		                    me.doQuery(me.allQuery, true);
		                } else {
		                    me.doQuery(me.getRawValue(), false, true);
		                }
		            }
		            me.inputEl.focus();
		        }
		    },
		    
		    //4.2需要重写
		    getSubmitValue: function() {
//		        var value = this.getValue();
//		        if (Ext.isEmpty(value)) {
//		            value = '';
//		        }
//		        return value;
		    	return this.getValue();
		    }
		});