Ext.define('mvc.tools.NumberPct', {
			extend : 'Ext.form.field.Number',
			alias : 'widget.pctfield',
			isCell : false, //默认为FORM表单情况
			pctValue : 100, //默认为百分比方式处理
			
			/**
			 * FORM表单提交参数获取是调用此方法 
			 */
			getSubmitData: function() {
		        var me = this,
		            data = null;
		        if (!me.disabled && me.submitValue && !me.isFileUpload()) {
		            data = {};
		            var v = me.getValue();
		            if (v || v===0){
		            	//万元、亿元情况
		            	if (this.pctValue.toString().indexOf('.') != -1){
	        				v = (v/this.pctValue).toFixed(2);
	        			}else{
		        			//百分比情况
			            	var len = this.pctValue.toString().length -1;
			            	if (v.toString().indexOf('.') != -1)
		        				len = len + v.toString().split('.')[1].length;
			            	v = (v/this.pctValue).toFixed(len);
	        			}
		            }
		            data[me.getName()] = '' + v;
		        }
		        return data;
		    },
		    
		    /**
		     * Cell编辑器使用时，由于离开该组件即撤销，而
		     * 百分比MODEL里已经设置convert值转换函数，
		     * 所以需要把用户输入的值/100来传给model。
		     * FORM表单忽略
		     */
		    getValue : function() {
				var val = this.callParent();
				if (this.isCell){
					if (val || val===0)
						return val / this.pctValue;
					return val;
				}
				return val;
			},
			
			getSubmitValue: function() {
		        var me = this,
		        v = me.callParent();
		        if (v || v===0){
	            	//万元、亿元情况
	            	if (this.pctValue.toString().indexOf('.') != -1){
        				v = (v/this.pctValue).toFixed(2);
        			}else{
	        			//百分比情况
		            	var len = this.pctValue.toString().length -1;
		            	if (v.toString().indexOf('.') != -1)
	        				len = len + v.toString().split('.')[1].length;
		            	v = (v/this.pctValue).toFixed(len);
        			}
	            }
		        return v;
		    }
		});