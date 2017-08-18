package com.github.bordertech.wcomponents.lib.model;

import java.io.Serializable;

/**
 *
 * @author jonathan
 */
public interface ServiceModel<S, T> extends Serializable {

	T service(final S criteria);

}
