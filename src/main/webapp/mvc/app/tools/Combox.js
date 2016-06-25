//替换系统的COMBO
Ext.define('mvc.tools.Combox', {
	extend : 'Ext.form.field.ComboBox',
	alias: [ 'widget.combo'],
	trigger1Cls : Ext.baseCSSPrefix + 'form-clear-trigger',
	trigger2Cls : Ext.baseCSSPrefix + 'form-arrow-trigger',

	onTrigger1Click : function() {
		this.setValue(null);
	},

});