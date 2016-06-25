(function() {
	Ext.ns('mvc');
	mvc.Tools = {};
	var mvcTools = mvc.Tools;
	Ext.apply(mvcTools, {
		opt: function(v, type, bean, fld) {
			//V值未定义并且V非===0，强制同类型比较
            if (!v && (v===0)==false) {
                return "";
            }
            var rtn = '错误的值';
            var optStore = Ext.create('mvc.combo.'+type+'.'+bean+fld);
            optStore.each(function(record){
            	if (v == record.get('value')){
            		rtn = record.get('text');
            		return;
            	}
            });
            return rtn;
        },
        onCollapseall : function(grid){
			view = grid.view;
			var i;
			//Ext.suspendLayouts();
			for(i=0;i<view.getNodes().length;i++){
				var rowNode = view.getNode(i); 
				row = Ext.fly(rowNode, '_rowExpander'); 
	            nextBd = row.down('.x-grid-rowbody-tr', true);
	            isCollapsed = row.hasCls('x-grid-row-collapsed');
	            if(isCollapsed) continue;
		        row['addCls']('x-grid-row-collapsed');
		        Ext.fly(nextBd)['addCls']('x-grid-row-body-hidden');
		        grid.getView().getPlugin('rowexpander');
		        grid.getPlugin('rowexpanderplugin').recordsExpanded[row.getAttribute('data-recordid')] = isCollapsed;
		        view.refreshSize();
			}
			//Ext.resumeLayouts(true);
		},
		onCollapseBySelection : function(grid,selections) {
			Ext.each(selections,function(selection){
				var pkey = selection.get('bean.pkey');
				var element = grid.getView().getEl(); 
				var tr = element.query('tr[data-recordid="'+pkey+'"]');                                      
				row = Ext.fly(tr[0], '_rowExpander'); 
			    nextBd = row.down('.x-grid-rowbody-tr', true);
			    isCollapsed = row.hasCls('x-grid-row-collapsed');
			    if(isCollapsed) return;
			    row['addCls']('x-grid-row-collapsed');
			    Ext.fly(nextBd)['addCls']('x-grid-row-body-hidden');
			    grid.getView().getPlugin('rowexpander');
			    grid.getPlugin('rowexpanderplugin').recordsExpanded[row.getAttribute('data-recordid')] = isCollapsed;
			})
		},
        red: function(v, num) {
			if (v && v<num)
                return '<span style="color:red;">'+v+'</span>';
            return v;
        },
        optRenderer : function(type, bean, fld) {
            return function(v) {
                return mvcTools.opt(v, type, bean, fld);
            };
        },
        numberRenderer : function(digits) {
            return function(v) {
                return mvc.Tools.formatNum(v, digits);
            };
        },
        numberRedRenderer : function() {
            return function(v) {
            	if (v && v<0)
                	return '<span style="color:red;">'+mvc.Tools.formatNum(v)+'</span>';
                else
                	return mvc.Tools.formatNum(v);
            };
        },
        redRenderer : function(num) {
            return function(v) {
	            return mvcTools.red(v, num);
            };
        },
        beanRenderer : function() {
            return function(v) {
                if (!v && v!==0)
	                return "";
	            return v.split(bean_split)[1]=="null" ? "" : v.split(bean_split)[1];
            };
        },
        beanRendererHref : function() {
        	return function(v) {
        		if(!v && v!==0)
        			return "";
        		var value = v.split(bean_split)[1]=="null" ? "" : v.split(bean_split)[1];
        		return "<div onmouseover='lastChild.color=\"#00f\";lastChild.style=\"text-decoration:underline;cursor:pointer\"' onmouseout='lastChild.color=\"#000\";lastChild.style=\"\"' ><font>"+value+"</font></div>";
        	};
        },
        longPkeyrendererHref : function() {
        	return function(v) {
        		
        	};
        },
        beanRendererMulti : function() {
            return function(v) {
                if (!v && v!==0)
	                return "";
	            var arg1 = v.split(',');
	            var diy = "";
	            for (var i=0; i<arg1.length; i++){
	            	diy += "," + arg1.split(bean_split)[1];
	            }
	            return diy.substring(1,diy.length-1);
            };
        },
         leftbeanRenderer : function() {
            return function(v) {
                if (!v && v!==0)
	                return "";
	            return '<div align="left">'+v.split(bean_split)[1]+'</div>';
            };
        },
        monthRenderer : function() {
        	return function(v) { 
        		if (!v && v!==0)
	                return "";
        		return v.substring(0,4) + '年' + v.substring(4) + '月';
    		}
        },
        
        seasonRenderer : function() {
        	return function(v) { 
        		if (!v && v!==0)
	                return "";
        		return v.replace('Q','年第') + '季度';
    		}
        },
        
        yearRenderer : function() {
        	return function(v) { 
        		if (!v && v!==0)
	                return "";
        		return v.replace('YY','年');
    		}
        },
        
        moneyRednderer : function() {
        	return function(v) { 
        		if (!v && v!==0)
	                return "";
        		return Ext.util.Format.number(v/10000, '0.00');
    		}
        },
        
        moneyRedndererRed : function() {
        	return function(v) { 
        		if (!v && v!==0)
	                return "";
        		return '<font color="red">' + Ext.util.Format.number(v/10000, '0.00')+'</font>';
    		}
        },
    
         numberRedndererRed : function() {
        	return function(v) { 
        		if (!v && v!==0)
	                return "";
        		return '<font color="red">' + v +'</font>';
    		}
        },
        
        numberRedndererBlue : function() {
        	return function(v) { 
        		if (!v && v!==0)
	                return "";
        		return '<font color="blue">' + v +'</font>';
    		}
        },
        
       numberRedndererGreater : function(numStr) {
        	return function(v,metaData,record) { 
        		if (!v && v!==0)
	                return "";
	            var num = Number(record.get(numStr));
				v = mvc.Tools.formatNum(v);
				if(v>num)
        			return '<font color="red">' + v +'</font>';
        		else
        			return v;
    		}
        },
        
         numberRedndererLess : function(numStr) {
        	return function(v,metaData,record) { 
        		if (!v && v!==0)
	                return "";
	            var num = Number(record.get(numStr));
				v = mvc.Tools.formatNum(v);
				if(v < num)
        			return '<font color="red">' + v +'</font>';
        		else
        			return v;
    		}
        },
        
        //百分比转换器-MODEL中使用
        pctConvert : function(pctValue) {
        	return function(val){
        		if (val || val===0){
        			//万元、亿元情况
        			if (pctValue.toString().indexOf('.') != -1){
        				return (val*pctValue).toFixed(2);
        			}
        			if (val.toString().indexOf('.') != -1){
        				var len = val.toString().split('.')[1].length;
        				len = len - pctValue.toString().length + 1;
        				if (len < 0)
        					len = 0;
        				return (val*pctValue).toFixed(len);
        			}
					return val * pctValue;
        		}
				return null;
			};
        },
        //列表读取参数表
        renderSysCtType:function(type){
        	var obj = Ext.state.Manager.get('appAll_sys_ctype')[type];
        	return function(value){
        		var v = '';
        		if(obj && value != null){
	        		v = obj[value];
        		}
        		return v
        	}
        },
        
        /*
         * 根据指标类型判断单位
         */
        renderSysIndType:function(v,m,r){
            var optStore = Ext.create('mvc.combo.sys.SysZblx').data;
            var value = '';
            for(var i = 0;i < optStore.length;i++){
            	var record = optStore.getAt(i);
            	if (v == record.get('value')){
            		value = record.get('text');
            		if(v == '00') {
            			value = '/';
            		}
            		else if(v == '01'){
            			value = '元';
            		} else if(v == '02') {
            			value = '万元';
            		} else if(v == '03') {
            			value = '亿元';
            		} else if(v == '09' || v == '10') {
            			value = "%";
            		}
            		break;
            	}
            }
            return value;
        },
        //居右显示，根据类型转换指标值cell:1:元、2:万元、3:亿元。
        //type:01:金额型、02:数量/笔数/户数/倍数、03:利率%、04:占比%
        formatVal:function(type,cell){
        	var formatNum = this.formatNum;
        	var moneyCell = this.moneyCell;
        	return function(v, m, r){
        		var indType = r.get(type);
        		var indCell = r.get(cell);
        		if(v){
	        		if(indType == '01'){
	        			v = formatNum(moneyCell(v,indCell));
	        		}else if(indType == '03' || indType == '04'){
	        			v = Number(v)*100;
	        		}
        		}
        		return '<div align="right">' + v + '</div>';
        	}
        },
        moneyCell : function(v,t){
        	if(t == 2){
    			v = (Number(v)/10000);
    		}else if(t == 3){
    			v = (Number(v)/100000000);
    		}
    		return v;
        },
        //金钱转换1:元、2:万元、3:亿元
        formatMoney:function(key){
        	var moneyCell = this.moneyCell;
        	return function(v,m,r){
        		var t = r.get(key);
        		return moneyCell(v,t)
        	}
        },
        
        //数字格式化
        formatNum : function(v,digits){
    		var vf = '';
        	if(v){
	    		//转为string
        		
        		if(!digits)
	    			v = String(Number(v).toFixed(2));
	    		else 
	    			v = String(Number(v).toFixed(digits));
	    		if(v.indexOf('.') >= 0){
	    			vf = '.' + v.split('.')[1];
	    			v = v.split('.')[0];
	    		}
			    var re = /(-?\d+)(\d{3})/;
			    while(re.test(v)){
			    	v = v.replace(re,"$1,$2");
			    }
        	}
        	return v == null?'':v + vf;
        },
        
        beanRendererMulti : function() {
            return function(v) {
                if (!v && v!=0)
	                return "";
                var vs = v.split(','), r = '';
                for (i = 0; i < vs.length; i++) {
                	r += vs[i].split(bean_split)[1];
                	if(i < vs.length - 1)
                		r += ',';
                }
	            return r != 'undefined' ? r : '';
            };
        },
        
        formFailure : function() {
            return function(form,action) {
                Ext.MessageBox.show({
						title : msg_title,
						msg : action.result.msg,
						buttons : Ext.MessageBox.OK,
						icon : Ext.MessageBox.ERROR
					});
            };
        },

        searchValues : function(toolbar) {
        	var array = [];
        	toolbar.items.each(function(item){
        		if (item.getXType()!='label' && item.getXType()!='tbspacer' && item.getXType()!='splitbutton'
        			&& item.getXType()!='button' && item.getXType()!='tbfill' && item.getXType()!='tbtext'){
        			if (item.getSubmitValue() || item.getSubmitValue()===0){
        				var value = {};
        				value['id']=item.getName();
        				value['property']=item.getName();
        				value['value']=item.getSubmitValue();
        				array.push(value);
        			}
        		}
            });
            return array;
        },
        
        advancedSearchValues : function(form) {
        	var array = [];
        	var arr = [];
        	form.items.each(function(item){
        		if (item.getXType()!='label') {
    				var value = {};
    				if (item.getName().indexOf('optcht')>=0){
    					var oc = item.getName().split('_');
    					value['property'] = oc[1];
    					value['value'] = item.getSubmitValue() + '##';
    					array.push(value);
    				} else {
    					for(var i=array.length-1; i>=0; i--){
    						if(array[i]['property'].indexOf(item.getName())==0){
    							array[i]['value'] += item.getSubmitValue()==null?"":item.getSubmitValue();
    							break;
    						}
    						if((array[i]['property']+"Ed").indexOf(item.getName())==0 && (item.getName()).indexOf(array[i]['property']+"Ed")==0 && array[i]['value'].indexOf('11##')==0){
    							array[i]['value'] += item.getSubmitValue();
    							break;
    						}
    					};
    				}
    			}
            });
        	for (var i=0; i<array.length; i++) {
        		var tmp = array[i]['value'].split('##');
        		var tmp2 = array[i]['value'].split('####');
        		if ((tmp[0].indexOf('11')==0 && !tmp2[1] == 0) || !tmp[1].length == 0)
        			arr.push(array[i]);
        		if (tmp[0].indexOf('9')==0 || tmp[0].indexOf('10')==0){
        			var obj = array[i];
        			obj['value'] = tmp[0] + '##null';
        			arr.push(array[i]);
        		}
        	}
            return arr;
        },
        
        columnValues : function(columns) {
        	var array = [];
        	for(var i=0; i<columns.length; i++){
        		if (columns[i].getXType() == 'actioncolumn')
        			continue;
        		//有数据或有一级表头情况
        		if (columns[i].dataIndex || columns[i].items.items.length > 0){
        			if (columns[i].hidden==false || columns[i].expandCol==true){
	        			var value = {};
	        			//有'%'时decode出错...
	        			var ct = columns[i].text;
	        			if (ct.indexOf('(') != -1){
	        				ct = ct.substring(0,ct.indexOf('('));
	        			}
	        			if (ct.indexOf('%') != -1){
	        				ct = ct.replace('%','');
	        			}
	        			value['value']=ct;
	        			//多列表头情况
	        			if (columns[i].items.items.length > 0)
	        				value['columns']=this.columnValues(columns[i].items.items);
	        			else
	        				value['property']=columns[i].dataIndex.replace('bean.','');
	        			array.push(value);
        			}
        		}
            }
            return array;
        },
        
        searchClear : function(toolbar) {
        	toolbar.items.each(function(item){
        		if (item.getXType()!='label' && item.getXType()!='tbspacer' && item.getXType()!='splitbutton'
        			&& item.getXType()!='button' && item.getXType() != 'tbfill' && item.getXType()!='tbtext'){
        			item.setValue('');
        		}
            });
        },
        
        /**
         * 只对远程查询的STORE有效，EXT默认的filter方法没有处理异常情况
         * 版本4.1
         * store LIST的数据仓库
         * array 过滤查询的参数数组
         */
        filterAndLoad : function(store, array){
    		var decoded = store.decodeFilters(array),
    			length = decoded.length,
    			i=0;
    		for (; i < length; i++) {
            	store.filters.replace(decoded[i]);
        	}
        	if (store.remoteFilter) {
            	delete store.totalCount;
	            if (store.buffered) {
	                store.pageMap.clear();
	                store.currentPage = 1;
	            } else {
	                store.currentPage = 1;
	            }
	            store.load({
				    callback: function(records, operation, success) {
				        if (!success){
				        	Ext.example.msg(msg_title,store.getProxy().getReader().jsonData.msg);
				        }
				    }
				});
	        }
    	},
        
    	//带了叉叉的下拉框
        crtComboForm : function(isnull, params) {
        	var array = {
        		xtype : 'combo',
        		mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,typeAhead : true,
				editable : false,
				emptyText : form_empty_text
        	};
        	if (!isnull){
        		array['afterLabelTextTpl'] = required;
        		array['allowBlank'] = false;
        	}
        	Ext.apply(array, params);
            return array;
        },
        
        crtComboBase : function(isnull, params) {
        	var array = {
        		xtype : 'combobox',
        		mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				forceSelection : true,typeAhead : true,
				editable : false,
				emptyText : form_empty_text
        	};
        	if (!isnull){
        		array['afterLabelTextTpl'] = required;
        		array['allowBlank'] = false;
        	}
        	Ext.apply(array, params);
            return array;
        },
        
        crtComboCust : function(isnull, params) {
        
        	
        	var array = {
        		xtype : 'combocust',
        		mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				typeAhead : true,
				editable : false,
				emptyText : form_empty_text
        	};
        	if (!isnull){
        		array['afterLabelTextTpl'] = required;
        		array['allowBlank'] = false;
        	}
        	Ext.apply(array, params);
            return array;
        },
        
        
        crtComboCustCell : function(isnull, params) {
        	var array = {
        		xtype : 'combocustcell',
        		mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				typeAhead : true,
				editable : false,
				emptyText : form_empty_text
        	};
        	if (!isnull){
        		array['afterLabelTextTpl'] = required;
        		array['allowBlank'] = false;
        	}
        	Ext.apply(array, params);
            return array;
        },
        
        /**
         * 返回自定义下拉框组件对象
         */
        crtComboSelf : function(isnull, act, where, params) {
        	var array = {
        		xtype : 'combotrigger',
        		mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				typeAhead : true,
				editable : false,
				store : Ext.create('mvc.store.ComboSelf',{
					actUrl : act,
					actWhere : where
				}),
				emptyText : form_empty_text
        	};
        	if (!isnull){
        		array['afterLabelTextTpl'] = required;
        		array['allowBlank'] = false;
        	}
        	Ext.apply(array, params);
            return array;
        },
        
        /**
         * 返回外键下拉框组件对象
         */
        crtComboTrigger : function(isnull, act, where, params) {
        	var array = {
        		xtype : 'combotrigger',
        		mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				typeAhead : true,
				editable : false,
				store : Ext.create('mvc.store.ComboTrigger',{
					actUrl : act,
					actWhere : where
				}),
				emptyText : form_empty_text
        	};
        	if (!isnull){
        		array['afterLabelTextTpl'] = required;
        		array['allowBlank'] = false;
        	}
        	Ext.apply(array, params);
            return array;
        },
        
        crtComboTriggerCell : function(isnull, act, where, params) {
        	var array = {
        		xtype : 'combotriggercell',
        		mode : 'local',
				valueField : 'value',
				triggerAction : 'all',
				typeAhead : true,
				editable : false,
				store : Ext.create('mvc.store.ComboTrigger',{
					actUrl : act,
					actWhere : where
				}),
				emptyText : form_empty_text
        	};
        	if (!isnull){
        		array['afterLabelTextTpl'] = required;
        		array['allowBlank'] = false;
        	}
        	Ext.apply(array, params);
            return array;
        },
        
        /**
         * 返回复合查询的报表的store
         */
        crtReportStore : function(param) {
        	Ext.define('Report', {
        		extend : 'Ext.data.Model',
        		fields : [
        		    {name : 's0'},{name : 's1'},{name : 's2'},{name : 's3'},{name : 's4'},
        		    {name : 's5'},{name : 's6'},{name : 's7'},{name : 's8'},{name : 's9'},
        		    {name : 's10'},{name : 's11'},{name : 's12'},{name : 's13'},{name : 's14'},
        		    {name : 's15'},{name : 's16'},{name : 's17'},{name : 's18'},{name : 's19'}
        		]
        	});
    		return Ext.create('Ext.data.Store', {
    			model : 'Report',
    			remoteSort : false,
    			autoLoad : false,
    			proxy : {
    				type : 'ajax',
    				url :base_path+ '/st_' + param + '_list',
    				reader : {
    					type : 'json',
    					root : 'items',
    					totalProperty : 'total'
    				},
    				simpleSortMode : true
    			}
			});
        },
        
        /**
         * 返回复合查询的统计的store
         */
        crtStatisticsStore : function(param) {
        	Ext.define('Statistics', {
        		extend : 'Ext.data.Model',
        		fields : [
        		    {name : 's0', type : 'string'},{name : 'f1', type : 'float'},{name : 'f2', type : 'float'},{name : 'f3', type : 'float'},{name : 'f4', type : 'float'},
        		    {name : 'f5', type : 'float'},{name : 'f6', type : 'float'},{name : 'f7', type : 'float'},{name : 'f8'},{name : 'f9', type : 'float'},
        		    {name : 'f10', type : 'float'},{name : 'f11', type : 'float'},{name : 'f12', type : 'float'},{name : 'f13', type : 'float'},{name : 'f14', type : 'float'},
        		    {name : 'f15', type : 'float'},{name : 'f16', type : 'float'},{name : 'f17', type : 'float'},{name : 'f18', type : 'float'},{name : 'f19', type : 'float'}
        		]
        	});
    		return Ext.create('Ext.data.Store', {
    			model : 'Statistics',
    			remoteSort : false,
    			autoLoad : false,
    			proxy : {
    				type : 'ajax',
    				url :base_path+ '/st_' + param + '_list',
    				reader : {
    					type : 'json',
    					root : 'items',
    					totalProperty : 'total'
    				},
    				simpleSortMode : true
    			}
			});
        },
        
        /**
         * 返回年份下拉框的store
         */
        crtYearCombo : function() {
        	var today = new Date();
        	var sdata = [];
    		for (i = 2008; i <= today.getFullYear(); i++)
    			sdata.push({value : i.toString(), text : i + '年'});
    		var store = Ext.create('Ext.data.Store', {
				fields : ['value', 'text'],
				data : sdata
			})
    		return store;
        },
        
        /**
         * 返回当前月份
         */
        getCurrentMonth : function() {
        	var today = new Date();
        	var mon = today.getMonth() + 1;
    		if (mon < 10)
    			return '0' + mon.toString();
    		else
    			return mon.toString();
    	},
    	
    	/**
         * 返回当前季度
         */
        getCurrentSeason : function() {
        	var today = new Date();
    		if (today.getMonth() < 3)
    			return '1';
    		else if (today.getMonth() > 2 && today.getMonth() < 6)
    			return '2';
    		else if (today.getMonth() > 5 && today.getMonth() < 9)
    			return '3';
    		else
    			return '4';
    	},
    	
    	/**
         * 返回当前年份
         */
    	getCurrentYear : function() {
    		return (new Date()).getFullYear().toString()
    	},
    	
    	/**
         * 返回当前年份前N个月的年份
         */
    	getFromYear : function(months) {
    		var today = new Date();
    		var mon = months % 12;
        	var years = (months - mon)/12
        	if (mon < today.getMonth() + 1)
        		return (today.getFullYear() - years).toString();
        	else
        		return (today.getFullYear() - years - 1).toString();
    	},
    	
    	/**
         * 返回当前年份前N个月的月份
         */
    	getFromSeason : function(months) {
        	var today = new Date();
        	var mon = months % 12;
        	mon = today.getMonth() + 1 - mon;
        	if (mon < 1)
        		mon = 12 + mon;
        	if (mon < 4)
    			return '1';
    		else if (mon > 3 && today.getMonth() < 7)
    			return '2';
    		else if (mon > 6 && today.getMonth() < 10)
    			return '3';
    		else
    			return '4';
    	},
    	
    	/**
         * 返回当前年份前N个月的月份
         */
    	getFromMonth : function(months) {
        	var today = new Date();
        	var mon = months % 12;
        	mon = today.getMonth() + 1 - mon;
        	if (mon < 1)
        		mon = 12 + mon;
    		if (mon < 10)
    			return '0' + mon.toString();
    		else
    			return mon.toString();
    	},
    	
        formValues : function(form) {
            var values  = {},
	            fields  = form.getFields().items,
	            f,
	            fLen    = fields.length,
	            isArray = Ext.isArray,
	            field, data, val, bucket, name;
	
	        for (f = 0; f < fLen; f++) {
	            field = fields[f];
	            if (typeof field.bean != "undefined")
	            	data = field.getBeanData();
	            else
                	data = field['getSubmitData'](false);
                if (Ext.isObject(data)) {
                    for (name in data) {
                        if (data.hasOwnProperty(name)) {
                            val = data[name];
                            if (values.hasOwnProperty(name)) {
                                bucket = values[name];
                                if (!isArray(bucket)) {
                                    bucket = values[name] = [bucket];
                                }
                                if (isArray(val)) {
                                    values[name] = values[name] = bucket.concat(val);
                                } else {
                                    bucket.push(val);
                                }
                            } else {
                                values[name] = val;
                            }
                        }
                    }
                }
	        }
	        return values;
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
		        		values[line_pr+'['+i+'].'+fields[f].mapping] = value;
        			}
	        	}
	        	i++;
        	});
        	Ext.apply(values, params);
        	return values;
        },
        
        sumStoreFld : function(store, fldname) {
        	var value,sum=0;
        	store.each(function(record) {
        		value = record.get(fldname);
        		if (value)
        			sum += value;
        	});
        	return sum;
        },
        
       filterItemRenderer: function(v, metaData, record, rowIndex, colIndex, store) {
	       if(!v && (v!=1)){ // 选择逻辑 --- 该值==1选中，否则不处理
	             filterItem_grid.getSelectionModel().selectRow(rowIndex,false);
	       }
	       return v;
		},
		
		/**
		 * 返回任务类型的文本
		 * className ：mvc.combo.su.SuRwlx
		 * newValue  ：对应的key值
		 */
		suwkType:function(className,newValue){
			var surwlxData = Ext.create(className).data;
			for(var i = 0;i <surwlxData.length;i++ ){
				var value = surwlxData.get(i).get('value');
				var text = surwlxData.get(i).get('text');
				if(value == newValue){
					return text;
				}
			}
			
		},
		
		/**
         * 诠释图表格的变更记录的取值方法
         * models	变更记录
         * fields	模型的字段集合
         * pre		传参对应的后台集合变量
         */
        syncValues : function(models, fields, pre) {
        	var fLen = fields.length,
        		i = 0,
        		value,record,
        		values = {};
        	for(m=0; m<models.length; m++) {
        		record = models[m]
        		for (f = 0; f < fLen; f++) {
        			value = record.get(fields[f].name);
        			if (value || value===0){
	        			if (typeof fields[f].outkey != 'undefined' || 
	        					typeof fields[f].optCust != 'undefined')
        					value = value.split(bean_split)[0];
        				else if (typeof fields[f].isPct != 'undefined')
        					value = value / fields[f].isPct;
		        		values[pre+'['+i+'].'+fields[f].mapping] = value;
        			}
	        	}
	        	i++;
        	}
        	return values;
        },
        //显示选中行的货物库存
        doLoadStock : function(grid) {
		var selection = grid.getView().getSelectionModel().getSelection()[0];
		if(!selection) {
			grid.up('window').down('#cqty').setText("");
			return;
		}
		var goods = selection.get('bean.goods').split(bean_split)[0];
		var warehouse = grid.up('window').form.down('[name=bean.warehouse]')
				.getValue();
		if (!goods || !warehouse) {
			grid.up('window').down('#cqty').setText("");
			return;
		}
		var urlGoods = base_path + '/gs_GsStock_loadQty?bean.goods=' + goods
				+ '&bean.warehouse=' + warehouse;
		Ext.Ajax.request({
					url : urlGoods,
					method : 'GET',
					success : function(response) {
						rtn = Ext.JSON.decode(response.responseText, true);
						if(rtn.success) {
							//Ext.example.msg("qty",rtn.qty+"");
							grid.up('window').down('#cqty').setText(rtn.qty);	
						} else {
							grid.up('window').down('#cqty').setText("库存查询出错");
						}
					},
					failure : function(response) {
						Ext.example.msg(msg_title, msg_ajax);
						grid.up('window').down('#cqty').setText("库存查询出错");
					}
				});
		},
        //货物 - 列表中加入关联字段
        doGoodsLine : function(columns, index) {
        	columns.splice(index,0,{text : '货物名称', width : 120, dataIndex : 'link.goodsName', sortable : true},
            		{text : '货物规格', width : 200, dataIndex : 'link.goodsSpec', sortable : true});
        	return columns;
        },
        //货物-列表编辑完成后自动读取关联数据
        onLoadInfo : function(newv, row, grid, loadStock){
    		if (row == null)
    			row = grid.getView().getSelectionModel().getSelection()[0];
    		if (!newv){
    			row.set('link.goodsName',null);
    			row.set('link.goodsSpec',null);
    			row.set('bean.uom',null);
    			return;
    		}
    		if (newv.indexOf(bean_split) != -1) //两种情况：手动输入代码 或 选择器带回
    			newv = newv.split(bean_split)[1]
    		var urlGoods = base_path+ '/loadInfo?sarg1=' + newv;
    		Ext.Ajax.request({
    			//async : false, //加上同步限制后，单元格之间切换会中断
    			url : urlGoods,
    			method : 'GET',
    			success : function(response) {
    				rtn = Ext.JSON.decode(response.responseText, true);
    				row.set('bean.goods',rtn.goods);
    				row.set('link.goodsName',rtn.goodsName);
    				row.set('link.goodsSpec',rtn.goodsSpec);
    				row.set('bean.uom',rtn.uom);
    				if (loadStock)
    					mvc.Tools.doLoadStock(grid);
    			},
    			failure : function(response) {
    				Ext.example.msg(msg_title, msg_ajax);
    			}
    		});
    	},
    	//货物-FORM编辑完后，自动带回货物的名称与规格
        onLoadGoodsForm : function(goodsCode,form,fldcode){
    		if (!goodsCode)
    			return null;
    		if (goodsCode.indexOf(bean_split) != -1) //两种情况：手动输入代码 或 选择器带回
    			goodsCode = goodsCode.split(bean_split)[1]
    		var urlGoods = base_path+ '/loadInfo?sarg1=' + goodsCode;
    		var rtn = null;
    		Ext.Ajax.request({
    			async : true,
    			url : urlGoods,
    			method : 'GET',
    			success : function(response) {
	    			rtn = Ext.JSON.decode(response.responseText, true);
    				if (!rtn) {
							form.down('[name=bean.'+fldcode+']')
									.setValue(null);
							form.down('[name=link.'+fldcode+'Name]')
							.setValue(null);
							form.down('[name=link.'+fldcode+'Spec]')
									.setValue(null);
						} else {
							form.down('[name=bean.'+fldcode+']')
							.setValue(rtn.goods);
							form.down('[name=link.'+fldcode+'Name]')
									.setValue(rtn.goodsName);
							form.down('[name=link.'+fldcode+'Spec]')
									.setValue(rtn.goodsSpec);
						}    				
    			},
    			failure : function(response) {
    				Ext.example.msg(msg_title, msg_ajax);
    			}
    		});
    	},
    	addCountAmtListener : function(grid) {//货物明细选择数量和单价后，以及移除后自动统计金额，
    		grid.on('edit', function(editor, e) {
				if (e.field == 'bean.goods'){
					if (grid.oldGoods != e.value){ //值变更后触发--根据货物读取INFO
						grid.onLoadInfoPrice(e.value, e.record);
					}
				}else if (e.field == 'bean.qty' || e.field == 'bean.price'){ //计算金额
					var qty = e.record.get('bean.qty');
					var price = e.record.get('bean.price');
					if (qty && price)
						e.record.set('bean.amt', (qty * price).toFixed(2));
					else
						e.record.set('bean.amt', null);
					//设置主表中的总金额字段
					this.up('window').down('form').down('[name=bean.amt]').setValue(mvc.Tools.sumStoreFld(grid.store, 'bean.amt'));
				}
    		});
			grid.getStore().on('remove', function(store,record){
				grid.up('window').down('form').down('[name=bean.amt]').setValue(mvc.Tools.sumStoreFld(store, 'bean.amt'));
			});
    	},
    	//给搜索栏中的每个field绑定回车监听事件，执行grid中的搜索方法
    	onENTER2SearchBar : function(searchBar, grid) {
        	searchBar.items.each(function(item){
        		if (item.getXType()!='label' && item.getXType()!='tbspacer' && item.getXType()!='splitbutton'
        			&& item.getXType()!='button' && item.getXType()!='tbfill' && item.getXType()!='tbtext'){
        			item.on("specialkey",function(field, e){
        				if(e.getKey()==e.ENTER) {
        					grid.onSearch();
        				}
        			},this);
        		}
            });
    	},
        //财报名称转换
        finRptNameConvert : function(value){
			if(value.indexOf("Q1")>=0){
				return value.substring(0,4)+"年第一季度季报";
			}else if(value.indexOf("Q2")>=0){
				return value.substring(0,4)+"年第二季度季报";
			}else if(value.indexOf("Q3")>=0){
				return value.substring(0,4)+"年第三季度季报";
			}else if(value.indexOf("Q4")>=0){
				return value.substring(0,4)+"年第四季度季报";
			}else if(value.indexOf("Y1")>=0){
				return value.substring(0,4)+"年上半年年报";
			}else if(value.indexOf("Y2")>=0){
				return value.substring(0,4)+"年下半年年报";
			}else if(value.substring(4,6).indexOf("01")>=0){
				return value.substring(0,4)+"年1月份月报";
			}else if(value.substring(4,6).indexOf("02")>=0){
				return value.substring(0,4)+"年2月份月报";
			}else if(value.substring(4,6).indexOf("03")>=0){
				return value.substring(0,4)+"年3月份月报";
			}else if(value.substring(4,6).indexOf("04")>=0){
				return value.substring(0,4)+"年4月份月报";
			}else if(value.substring(4,6).indexOf("05")>=0){
				return value.substring(0,4)+"年5月份月报";
			}else if(value.substring(4,6).indexOf("06")>=0){
				return value.substring(0,4)+"年6月份月报";
			}else if(value.substring(4,6).indexOf("07")>=0){
				return value.substring(0,4)+"年7月份月报";
			}else if(value.substring(4,6).indexOf("08")>=0){
				return value.substring(0,4)+"年8月份月报";
			}else if(value.substring(4,6).indexOf("09")>=0){
				return value.substring(0,4)+"年9月份月报";
			}else if(value.substring(4,6).indexOf("10")>=0){
				return value.substring(0,4)+"年10月份月报";
			}else if(value.substring(4,6).indexOf("11")>=0){
				return value.substring(0,4)+"年11月份月报";
			}else if(value.substring(4,6).indexOf("12")>=0){
				return value.substring(0,4)+"年12月份月报";
			}else if(value.indexOf("YY")>0){
				return value.substring(0,4)+"年审计前年报";
			}else {
				return value.substring(0,4)+"年审计后年报";
			}
		},
		finRptName : function() {
            return this.finRptNameConvert;
        },
        onCellclick : function(component,td,cellIndex,model,tr,rowIndex,e,eOpts) {
			var column = component.getHeaderAtIndex(cellIndex);
			if(!column.md)
				return ;
			if(!e.ctrlKey)
				return;
			if(!model.get(""+column.dataIndex))
				return;
			mvc.Tools.onCreateTab(column.md,column.mn,"pkey",model.get(""+column.dataIndex).split(bean_split)[0]);
		},
        onCreateTab : function (type,url,condition,value) {
			var types = '';
			var errmsg;
			var record;
			Ext.Ajax.request({
				async : false,
				url : base_path+'/sys_SysMenu_loadJump?type='+type+'&jumpUrl='+url,
				success : function (response, options) {
					var rtn = Ext.decode(response.responseText, true);
					if (rtn.success){
						types = rtn.types;
						record = rtn.menu;
					}else{
						errmsg =  rtn.msg;
					}
				}
			});
			if (errmsg){
				Ext.example.msg(msg_title,errmsg);
				return ;
			}
			var strs = types.split(',');
			Ext.Array.each(strs,function(str){
				if(str != type){
					Ext.getCmp("menu_"+str).hide();
				}
			});
			Ext.getCmp("menu_"+type).onLoadFirst();
			Ext.getCmp("menu_"+type).show();
			
			var mainTab = Ext.getCmp('mainTab');
			var tab = mainTab.getComponent(record.id);
			if (!tab) {
				var p = {
					title : record.text,
					id : record.id,
					iconCls : 'tab-user-icon',
					roles : record.roles,
					menuType : record.beanType, //用于TAB点击时，自动跳到对应的菜单模块下
					closable : true,
					autoDestroy:true
				};
				var url = "mvc." + record.url;
				tab = mainTab.add(Ext.create(url, p));
			}
			if(tab.store)
				tab.store.filter([{'id':'filter', 'property':condition,'value':value}]);
			else
				tab.mdMainTable.store.filter([{'id':'filter', 'property':condition,'value':value}]);
			mainTab.setActiveTab(tab);
		}
	});
}());