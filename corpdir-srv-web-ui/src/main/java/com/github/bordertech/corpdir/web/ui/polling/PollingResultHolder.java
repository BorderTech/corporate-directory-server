package com.github.bordertech.corpdir.web.ui.polling;

/**
 * Polling result holder to use in the Future.
 * <p>
 * The result can be an exception or the result.
 * </p>
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class PollingResultHolder {

	private Object result;

	/**
	 * @return the polling result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result the polling result
	 */
	public void setResult(final Object result) {
		this.result = result;
	}

}
