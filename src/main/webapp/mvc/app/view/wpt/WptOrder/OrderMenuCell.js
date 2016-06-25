Ext.define('mvc.view.wpt.WptOrder.OrderMenuCell', {
			extend : 'Ext.form.field.Trigger',
			editable : false,
			trigger1Cls: Ext.baseCSSPrefix + 'form-clear-trigger',
		    trigger2Cls: Ext.baseCSSPrefix + 'form-search-trigger',
			
			beanType : 'sys',
			bean : null,
			grid : null,

			callback : null,
			onTrigger1Click : function(){
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

			
			//回调 -- Cell上的一离开，编辑控件已经没了，直接操作表格
			onTrigger : function(data, params) {
				var pkey = data.split(bean_split)[0];
				var name = data.split(bean_split)[1];
				this.setValue(name);
				this.callback(pkey);
			}
		});