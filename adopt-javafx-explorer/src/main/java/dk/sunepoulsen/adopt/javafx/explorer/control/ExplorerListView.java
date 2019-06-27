package dk.sunepoulsen.adopt.javafx.explorer.control;

import dk.sunepoulsen.adopt.javafx.explorer.api.ExplorerNode;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ExplorerListView<T extends ExplorerNode> extends ListView<T> {
    public ExplorerListView() {
        this( null );
    }

    public ExplorerListView( ObservableList<T> observableList ) {
        super( observableList );
        setCellFactory( listView -> new ExplorerListCell<>() );
    }
}
