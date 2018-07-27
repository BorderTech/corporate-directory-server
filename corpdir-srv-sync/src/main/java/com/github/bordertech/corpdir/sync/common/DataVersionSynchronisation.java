package com.github.bordertech.corpdir.sync.common;

/**
 *
 * @author exiqaj
 */
public interface DataVersionSynchronisation<S, D, A> extends DataSynchronisation<S, D, A> {

	void syncBaseData(final Long version);

	void syncLinkedData(final Long version);

}
