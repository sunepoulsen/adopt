package dk.sunepoulsen.adopt.javafx.explorer.control;

import dk.sunepoulsen.adopt.javafx.explorer.api.ExplorerNode;
import javafx.scene.control.ListCell;

public class ExplorerListCell<T extends ExplorerNode> extends ListCell<T> {
    public ExplorerListCell() {
    }

    @Override
    protected void updateItem( T item, boolean empty ) {
        super.updateItem( item, empty );

        if( empty || item == null ) {
            setText( null );
            setGraphic( null );
        }
        else {
            setText( item.displayText() );
        }
    }
}
