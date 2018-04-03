package com.github.bordertech.corpdir.web.ui.flux.dataapi;

import com.github.bordertech.corpdir.api.common.ApiTreeable;
import com.github.bordertech.corpdir.api.common.ApiVersionable;
import com.github.bordertech.corpdir.api.modify.service.BasicVersionTreeWriteService;
import com.github.bordertech.corpdir.api.readonly.service.BasicVersionTreeReadOnlyService;
import java.util.List;

/**
 * Corp CRUD Tree Version API with defined types.
 *
 * @author jonathan
 * @param <T> the Corp API Treeable &amp; Versionable Object
 * @param <R> the Corp backing Tree read-only Service
 * @param <W> the Corp backing Tree write Service
 */
public interface CorpCrudTreeVersionDataApi<T extends ApiTreeable & ApiVersionable, R extends BasicVersionTreeReadOnlyService<T>, W extends BasicVersionTreeWriteService<T>> extends CorpCrudTreeDataApi<T, R, W>, CorpCrudVersionDataApi<T, R, W> {

	boolean hasChildren(final Long versionId, final T item);

	List<T> getChildren(final Long versionId, final T item);

	List<T> getRootItems(final Long versionId);

	T addChild(final Long versionId, final T parent, final T child);

	T removeChild(final Long versionId, final T parent, final T child);
}
