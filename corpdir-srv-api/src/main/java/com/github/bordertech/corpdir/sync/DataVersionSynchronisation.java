package com.github.bordertech.corpdir.sync;

/**
 *
 * @author exiqaj
 */
interface DataVersionSynchronisation<S, D, A> extends DataSynchronisation<S, D, A> {

    void syncLinkedData();

    String getOrCreateNewVersion();

}
