Ext.define('mvc.tools.ComboxTrigger', {
			extend : 'Ext.form.field.ComboBox',
			alias : 'widget.combotrigger',
			//源代码选择事件查询比较时，有BUG，默认为undefine与空值比较，所以STORE还会自动加载一次
			lastQuery : '', 
			trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
			trigger2Cls: Ext.baseCSSPrefix + 'form-arrow-trigger',
			initComponent : function() {
				var me = this;
				this.callParent(arguments);
//				this.store.on("load",function() {
//					me.select(this.getAt(0))
//				});
			},
			setValue : function(value) {
				if (typeof value != "undefined" && value) {
					/**
					 * 如是OBJECT，是STORE的RECORD对象，选择更改时触发
					 * 1##本科，是FORM初始化数据时触发
					 */
					if (typeof value == "object"){
						this.callParent(arguments);
					}else{
						if (this.multiSelect){
							var args2;
							var one = [], two = [];
							var args1 = value.split(",");
							for (var i=0; i<args1.length; i++){
								args2 = args1[i].split(bean_split);
								two.push(args2[0]+"");
							}
							one.push(two);
							this.callParent(one);
						}else{
							var args = value.split(bean_split);
							this.callParent([args[0]]);
						}
					}
				} else{
					this.callParent();
				}
				return this;
			},
			
			onTrigger1Click : function() {
				this.setValue(null);
				this.focus();
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
		    }

		});