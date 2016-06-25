Ext.define('mvc.store.Menus', {
			extend : 'Ext.data.TreeStore',
			requires: 'mvc.model.Menu',
			autoLoad : false,
			autoSync : false,
			model : 'mvc.model.Menu',
			
			setRootNode: function(root, /* private */ preventLoad) {
		        var me = this,
		            model = me.model,
		            idProperty = model.prototype.idProperty
		
		        root = root || {};
		        if (!root.isModel) {
		            // create a default rootNode and create internal data struct.
		            Ext.applyIf(root, {
		                id: me.defaultRootId,
		                text: 'Root',
		                allowDrag: false
		            });
		            if (root[idProperty] === undefined) {
		                root[idProperty] = me.defaultRootId;
		            }
		            Ext.data.NodeInterface.decorate(model);
		            root = Ext.ModelManager.create(root, model);
		        } else if (root.isModel && !root.isNode) {
		            Ext.data.NodeInterface.decorate(model);
		        }
		
		
		        // Because we have decorated the model with new fields,
		        // we need to build new extactor functions on the reader.
		        me.getProxy().getReader().buildExtractors(true);
		
		        // When we add the root to the tree, it will automaticaly get the NodeInterface
		        me.tree.setRootNode(root);
		
		        //主要修改部分  Ext4.1
		        //if (preventLoad !== true && !root.isLoaded() && (me.autoLoad === true || root.isExpanded())) {
		        if (preventLoad !== true && !root.isLoaded() && (me.autoLoad === true)) {
		            me.load({
		                node: root
		            });
		        }
		
		        return root;
		    }
		})
