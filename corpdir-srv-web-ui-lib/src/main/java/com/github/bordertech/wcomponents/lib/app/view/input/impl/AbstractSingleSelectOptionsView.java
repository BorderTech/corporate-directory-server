package com.github.bordertech.wcomponents.lib.app.view.input.impl;

import com.github.bordertech.wcomponents.lib.app.view.event.base.SelectableBaseViewEvent;
import com.github.bordertech.wcomponents.lib.app.view.event.SelectableViewEvent;
import com.github.bordertech.wcomponents.lib.app.view.input.SingleSelectOptionsView;

/**
 *
 * @param <T> the options type
 * @author jonathan
 */
public abstract class AbstractSingleSelectOptionsView<T> extends AbstractInputOptionsView<T> implements SingleSelectOptionsView<T> {

	public AbstractSingleSelectOptionsView(final String viewId) {
		super(viewId);
	}

	@Override
	protected void doDispatchSelectEvent() {
		T bean = getSelectedOption();
		if (bean == null) {
			dispatchViewEvent(SelectableBaseViewEvent.UNSELECT);
		} else {
			dispatchViewEvent(SelectableBaseViewEvent.SELECT, bean);
		}
	}

	@Override
	public void clearSelectedOption() {
		getSelectInput().resetData();
	}

	@Override
	public T getSelectedOption() {
		return (T) getSelectInput().getSelected();
	}

	@Override
	public void setSelectedOption(final T option) {
		getSelectInput().setSelected(option);
	}

	@Override
	public Object getBeanValue() {
		Object beanValue = super.getBeanValue();
		if (isBoundByCode()) {
			return getOptionForCode(beanValue);
		}
		return beanValue;
	}

	@Override
	public void updateBeanValue() {
		T selected = getSelectedOption();
		if (isBoundByCode()) {
			String beanProperty = getCodeProperty();
			Object code = getCodeForOption(selected, beanProperty);
			doUpdateBeanValue(code);
		} else {
			doUpdateBeanValue(selected);
		}
	}

}
