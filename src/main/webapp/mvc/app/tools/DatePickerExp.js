Ext.define('mvc.tools.DatePickerExp', {
			extend : 'Ext.picker.Date',
			alias : 'widget.datepickerexp',
			todayText : '确定',
			timefield : null,
			timefield2 : null,
			focusField : null,
			initComponent : function() {
				// keep time part for value
				var value = this.value || new Date();
				this.callParent();
				this.value = value;
			},
			onRender : function(container, position) {
				var me = this;
				this.callParent(arguments);
				if (!this.timefield) {
					this.timefield = Ext.create('Ext.form.field.Date', {
								width : 205,
								labelWidth : 60,
								format : me.format,
								name : 'startDate' ,
								fieldLabel : '开始时间',
								hideTrigger : true
							});
					this.timefield2 = Ext.create('Ext.form.field.Date', {
								width : 205,
								labelWidth : 60,
								format : me.format,
								name : 'endDate' ,
								fieldLabel : '结束时间',
								style : {
									marginTop : '10px'
								},
								hideTrigger : true
							});
					this.focusField = this.timefield;
					me.timefield.on({
						focus: {fn:function() {me.focusField = me.timefield}}
					});
					me.timefield2.on({
						focus: {fn:function() {me.focusField = me.timefield2}}
					});
					//this.timefield.ownerCt = this;
					//this.timefield.ownerCt = this;
					//this.timefield.on('change', this.timeChange, this);
					//this.timefield2.on('change', this.timeChange, this);
					var table = Ext.get(Ext.DomQuery.selectNode('table',
							this.el.dom));
					var tfEl = Ext.core.DomHelper.insertAfter(table, {
								tag : 'div',
								style : 'border:0px;',
								children : [{
											tag : 'div',
											cls : 'x-datepicker-footer ux-timefield'
										}]
							}, true);
					this.timefield.render(this.el.child('div div.ux-timefield'));
					this.timefield2.render(this.el.child('div div.ux-timefield'));
					
					var p = this.getEl().parent('div.x-layer');
					if (p) {
						p.setStyle("height", p.getHeight() + 31);
					}
				}
			},
			/* TODO 时间值与输入框绑定, 考虑: 创建this.timeValue 将日期和时间分开保存. */
			// overwrite
			setValue : function(value) {
				this.value = value;
				return this.update(this.value);
			},
			// overwrite
			getValue : function() {
				return this.value;
			},
			// overwrite : fill time before setValue
			handleDateClick : function(e, t) {
				var me = this, handler = me.handler;
				e.stopEvent();
				if (!me.disabled && t.dateValue
						&& !Ext.fly(t.parentNode).hasCls(me.disabledCellCls)) {
					me.doCancelFocus = me.focusOnSelect === false;
					me.setValue(new Date(t.dateValue)); // overwrite:
					
					me.focusField.setValue(Ext.Date.format(me.getValue(),me.format));
					// before
					
					// setValue
					delete me.doCancelFocus;
					//me.fireEvent('select', me, me.value);
					if (handler) {
						handler.call(me.scope || me, me, me.value);
					}
					//me.onSelect();
				}
			},
			// overwrite : fill time before setValue
			selectToday : function() {
				var me = this, btn = me.todayBtn, handler = me.handler;
				if (btn && !btn.disabled) {
					// me.setValue(Ext.Date.clearTime(new Date())); //src
					me.setValue(new Date());// overwrite: fill time before
											// setValue
					me.fireEvent('select', me, me.value);
					if (handler) {
						handler.call(me.scope || me, me, me.value);
					}
					//me.onSelect();
				}
				return me;
			}
		});