Ext.define('mvc.tools.DateFieldExp', {
			extend : 'Ext.form.field.Date',
			alias : 'widget.datefieldexp',
			requires : ['mvc.tools.DatePickerExp'],
			altFormats : "m/d/Y|n/j/Y|n/j/y|m/j/y|n/d/y|m/j/Y|n/d/Y|m-d-y|m-d-Y|m/d|m-d|md|mdy|mdY|d|Y-m-d|n-j|n/j|Y-m-d 到 Y-m-d",
			format : "Y-m-d",
			time1 : null,
			time2 : null,
			submitValue : null,
			editable : false,
			initComponent : function() {
				this.format = this.format;
				this.callParent();
				this.time1 = this.getPicker().timefield;
				this.time2 = this.getPicker().timefield2;
			},
			// overwrite
			createPicker : function() {
				var me = this, format = Ext.String.format;
				return Ext.create('mvc.tools.DatePickerExp', {
							ownerCt : me.ownerCt,
							renderTo : document.body,
							floating : true,
							hidden : true,
							focusOnShow : true,
							minDate : me.minValue,
							maxDate : me.maxValue,
							disabledDatesRE : me.disabledDatesRE,
							disabledDatesText : me.disabledDatesText,
							disabledDays : me.disabledDays,
							disabledDaysText : me.disabledDaysText,
							format : me.format,
							showToday : me.showToday,
							startDay : me.startDay,
							minText : format(me.minText, me
											.formatDate(me.minValue)),
							maxText : format(me.maxText, me
											.formatDate(me.maxValue)),
							listeners : {
								scope : me,
								select : me.onSelect
							},
							keyNavConfig : {
								esc : function() {
									me.collapse();
								}
							}
						});
			},
			 onSelect: function(m, d) {
		        var me = this;
		        me.setValue(d);
		        me.fireEvent('select', me, d);
		        me.collapse();
		    },
		     getValue: function() {
		        var me = this,
		            val = me.rawToValue(me.processRawValue(me.getRawValue()));
		        var d1 = me.time1.getValue();
		        var d2 = me.time2.getValue();
				if(d1 instanceof Date && d2 instanceof Date) {
					return Ext.Date.format(d1, me.format)+" 到 "+Ext.Date.format(d2, me.format);
				}
				return "";
		    },
		    setValue: function(value) {
		        var me = this;
				if(value==null) {
					me.setRawValue("");
					me.submitValue = "";
				} else {
					var d1 = me.time1.getValue();
		       		var d2 = me.time2.getValue();
		       		me.setRawValue(me.getValue());
		       		me.submitValue = Ext.Date.format(d1, me.format)+"##"+Ext.Date.format(d2, me.format);
				}
		        return me.mixins.field.setValue.call(me, value);
		    },
		    getSubmitValue: function() {
				return this.submitValue;
		    }
		    
		});
