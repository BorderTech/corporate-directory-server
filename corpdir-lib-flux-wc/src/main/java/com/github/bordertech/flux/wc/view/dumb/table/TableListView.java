package com.github.bordertech.flux.wc.view.dumb.table;

import com.github.bordertech.flux.wc.common.table.TextBeanColumn;
import com.github.bordertech.flux.wc.view.dumb.list.AbstractListView;
import com.github.bordertech.wcomponents.WTable;
import com.github.bordertech.wcomponents.lib.table.TableBeanModel;
import com.github.bordertech.wcomponents.lib.table.TableColumn;
import java.util.Arrays;
import java.util.List;

/**
 * Basic table view.
 *
 * @author Jonathan Austin
 * @param <T> the item type
 * @since 1.0.0
 */
public class TableListView<T> extends AbstractListView<T> {

	private final WTable table = new WTable();

	public TableListView(final String viewId) {
		this(viewId, new TextBeanColumn<T>());
	}

	public TableListView(final String viewId, final TableColumn<?, T>... columns) {
		this(viewId, Arrays.asList(columns));
	}

	public TableListView(final String viewId, final List<TableColumn<?, T>> columns) {
		super(viewId);
		table.setBeanProperty(".");
		getContent().add(table);
		TableBeanModel model = new TableBeanModel<>(columns);
		TableBeanModel.configTable(table);
		table.setTableModel(model);
	}

	public final WTable getTable() {
		return table;
	}

}
