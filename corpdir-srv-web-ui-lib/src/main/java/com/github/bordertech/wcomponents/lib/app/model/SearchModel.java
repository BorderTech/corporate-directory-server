package com.github.bordertech.wcomponents.lib.app.model;

import com.github.bordertech.wcomponents.lib.mvc.Model;

/**
 *
 * @author jonathan
 * @param <S> the criteria type
 * @param <T> the response type
 */
public interface SearchModel<S, T> extends Model {

	T search(final S criteria);

}
