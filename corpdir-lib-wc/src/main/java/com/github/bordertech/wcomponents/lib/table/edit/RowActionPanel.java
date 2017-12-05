package com.github.bordertech.wcomponents.lib.table.edit;

import com.github.bordertech.wcomponents.Action;
import com.github.bordertech.wcomponents.ActionEvent;
import com.github.bordertech.wcomponents.AjaxTarget;
import com.github.bordertech.wcomponents.Request;
import com.github.bordertech.wcomponents.WAjaxControl;
import com.github.bordertech.wcomponents.WButton;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WConfirmationButton;
import com.github.bordertech.wcomponents.WDiv;
import com.github.bordertech.wcomponents.WTable;
import com.github.bordertech.wcomponents.WebUtilities;
import com.github.bordertech.wcomponents.lib.icons.IconConstants;
import com.github.bordertech.wcomponents.util.TableUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Table with row edit actions.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class RowActionPanel<T> extends WDiv implements RowActionable {

	private static final String EDIT_ROWS_ATTR_KEY = "wc-edit-rows";

	private final WButton editButton = new WButton("Edit") {
		@Override
		public boolean isVisible() {
			Object key = TableUtil.getCurrentRowKey();
			return isAllowEdit() && getRowModeKey(key) == RowMode.READ;
		}
	};
	private final WButton cancelButton = new WButton("Cancel") {
		@Override
		public boolean isVisible() {
			Object key = TableUtil.getCurrentRowKey();
			return getRowModeKey(key) == RowMode.EDIT;
		}
	};
	private final WConfirmationButton deleteButton = new WConfirmationButton("Delete") {
		@Override
		public boolean isVisible() {
			Object key = TableUtil.getCurrentRowKey();
			return isAllowEdit() && getRowModeKey(key) != RowMode.EDIT;
		}
	};

	private final WAjaxControl editAjax = new WAjaxControl(editButton) {
		@Override
		public boolean isVisible() {
			return editButton.isVisible();
		}
	};

	private final WAjaxControl cancelAjax = new WAjaxControl(cancelButton) {
		@Override
		public boolean isVisible() {
			return cancelButton.isVisible();
		}
	};

	public RowActionPanel() {

		// Buttons Panel
		WDiv buttonPanel = new WDiv();
		add(buttonPanel);

		// Edit button
		editButton.setImageUrl(IconConstants.EDIT_IMAGE);
		editButton.setRenderAsLink(true);
		editButton.setToolTip("Edit");
		editButton.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				handleEditButtonAction();
			}
		});

		// Cancel Button
		cancelButton.setImageUrl(IconConstants.CANCEL_IMAGE);
		cancelButton.setRenderAsLink(true);
		cancelButton.setToolTip("Cancel");
		cancelButton.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				handleCancelButtonAction();
			}
		});

		// Delete Button
		deleteButton.setMessage("Do you want to delete row?");
		deleteButton.setImageUrl(IconConstants.REMOVE_IMAGE);
		deleteButton.setRenderAsLink(true);
		deleteButton.setToolTip("Delete");
		deleteButton.setAction(new Action() {
			@Override
			public void execute(final ActionEvent event) {
				handleDeleteButtonAction();
			}
		});

		buttonPanel.add(editButton);
		buttonPanel.add(cancelButton);
		buttonPanel.add(deleteButton);

		// Ajax Controls
		buttonPanel.add(editAjax);
		buttonPanel.add(cancelAjax);
	}

	@Override
	public RowMode getRowMode(final Object rowKey) {
		if (!isAllowEdit()) {
			return RowMode.READ;
		}
		return getRowModeKey(rowKey);
	}

	@Override
	protected void preparePaintComponent(final Request request) {
		super.preparePaintComponent(request);
		if (!isInitialised()) {
			setupColumnAjaxTargets();
			setInitialised(true);
		}
	}

	/**
	 * Setup the row action AJAX targets (ie each column)
	 */
	protected void setupColumnAjaxTargets() {
		WTable table = getTable();
		editAjax.addTargets(getColumnAjaxTargets());
		cancelAjax.addTargets(getColumnAjaxTargets());
		deleteButton.setAjaxTarget(table);
	}

	/**
	 * @param rowKey row key to include in edits.
	 */
	@Override
	public void addRowModeKey(final Object rowKey, final RowMode mode) {
		if (mode == null) {
			throw new IllegalArgumentException("Row mode cannot be null.");
		}
		WTable table = getTable();
		HashMap<Object, RowMode> editRows = (HashMap<Object, RowMode>) table.getAttribute(EDIT_ROWS_ATTR_KEY);
		if (editRows == null) {
			editRows = new HashMap<>();
			table.setAttribute(EDIT_ROWS_ATTR_KEY, editRows);
		}
		editRows.put(rowKey, mode);
	}

	/**
	 * @param rowKey the row key to remove from the edits.
	 */
	@Override
	public void removeRowModeKey(final Object rowKey) {
		WTable table = getTable();
		HashMap<Object, RowMode> editRows = (HashMap<Object, RowMode>) table.getAttribute(EDIT_ROWS_ATTR_KEY);
		if (editRows != null) {
			editRows.remove(rowKey);
		}
	}

	/**
	 * @param key the row key to test
	 * @return true if row key is being edited
	 */
	protected RowMode getRowModeKey(final Object key) {
		WTable table = getTable();
		Map<Object, RowMode> editRows = (Map<Object, RowMode>) table.getAttribute(EDIT_ROWS_ATTR_KEY);
		if (editRows == null) {
			return RowMode.READ;
		}
		RowMode mode = editRows.get(key);
		return mode == null ? RowMode.READ : mode;
	}

	protected void handleEditButtonAction() {
		Object key = TableUtil.getCurrentRowKey();
		addRowModeKey(key, RowMode.EDIT);
	}

	protected void handleDeleteButtonAction() {
		WTable table = getTable();
		Object key = TableUtil.getCurrentRowKey();
		removeRowModeKey(key);
		T bean = (T) getBean();
		List<T> beans = (List<T>) table.getBean();
		beans.remove(bean);
		table.handleDataChanged();
	}

	protected void handleCancelButtonAction() {
		Object key = TableUtil.getCurrentRowKey();
		removeRowModeKey(key);
		for (AjaxTarget target : getColumnAjaxTargets()) {
			target.reset();
		}
	}

	protected List<AjaxTarget> getColumnAjaxTargets() {
		List<AjaxTarget> targets = new ArrayList<>();
		WTable table = getTable();
		for (int i = 0; i < table.getColumnCount(); i++) {
			WComponent renderer = table.getColumn(i).getRenderer();
			if (renderer instanceof AjaxTarget) {
				targets.add((AjaxTarget) renderer);
			}
		}
		return targets;
	}

	protected WTable getTable() {
		return WebUtilities.getAncestorOfClass(WTable.class, this);
	}

	protected boolean isAllowEdit() {
		return getTable().isEditable();
	}

}
