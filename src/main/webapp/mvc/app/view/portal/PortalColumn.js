/**
 * @class mvc.view.portal.PortalColumn
 * @extends Ext.container.Container
 * A layout column class used internally be {@link mvc.view.portal.PortalPanel}.
 */
Ext.define('mvc.view.portal.PortalColumn', {
    extend: 'Ext.container.Container',
    alias: 'widget.portalcolumn',

    requires: [
        'Ext.layout.container.Anchor',
        'mvc.view.portal.Portlet'
    ],

    layout: 'anchor',
    defaultType: 'portlet',
    cls: 'x-portal-column'

    // This is a class so that it could be easily extended
    // if necessary to provide additional behavior.
});