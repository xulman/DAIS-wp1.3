package de.mpicbg.ulman.nodeNetImgSink;

import org.knime.core.node.NodeView;

/**
 * <code>NodeView</code> for the "Image Receiver" Node.
 * It as an adopted code from the MyExampleNode found in the KNIME SDK.
 *
 * @author Vladimir Ulman, MPI-CBG.de
 */
public class WriterNodeView extends NodeView<WriterNodeModel> {

    /**
     * Creates a new view.
     * 
     * @param nodeModel The model (class: {@link WriterNodeModel})
     */
    protected WriterNodeView(final WriterNodeModel nodeModel) {
        super(nodeModel);

        // TODO instantiate the components of the view here.

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void modelChanged() {

        // TODO retrieve the new model from your nodemodel and 
        // update the view.
        WriterNodeModel nodeModel =
            (WriterNodeModel)getNodeModel();
        assert nodeModel != null;
        
        // be aware of a possibly not executed nodeModel! The data you retrieve
        // from your nodemodel could be null, emtpy, or invalid in any kind.
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onClose() {
    
        // TODO things to do when closing the view
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onOpen() {

        // TODO things to do when opening the view
    }

}

