Ext.define('mvc.tools.FieldSetCheck', {
			extend : 'Ext.form.FieldSet',
			alias : 'widget.fieldsetcheck',

			createCheckboxCmp: function() {
		        var me = this,
		            suffix = '-checkbox';
		
		        me.checkboxCmp = Ext.widget({
		            xtype: 'checkbox',
		            hideEmptyLabel: true,
		            name: me.checkboxName || me.id + suffix,
		            cls: me.baseCls + '-header' + suffix,
		            id: me.id + '-legendChk',
		            checked: false,
		            listeners: {
		                change: me.onCheckChange,
		                scope: me
		            }
		        });
		        return me.checkboxCmp;
		    }
		});