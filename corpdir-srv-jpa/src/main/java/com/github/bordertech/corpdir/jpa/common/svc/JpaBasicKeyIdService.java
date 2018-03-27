package com.github.bordertech.corpdir.jpa.common.svc;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.service.BasicKeyIdService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistKeyIdObject;
import com.github.bordertech.corpdir.jpa.modify.common.svc.JpaBasicKeyIdWriteService;
import com.github.bordertech.corpdir.jpa.readonly.common.svc.JpaBasicKeyIdReadOnlyService;
import javax.inject.Singleton;

/**
 * Keyed Entity JPA service implementation.
 *
 * @param <A> the API object type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @since 1.0.0
 * @deprecated split in {@link JpaBasicKeyIdReadOnlyService} and {@link JpaBasicKeyIdWriteService}
 */
@Deprecated
@Singleton
public abstract class JpaBasicKeyIdService<A extends ApiKeyIdObject, P extends PersistKeyIdObject> extends JpaBasicIdService<A, P> implements BasicKeyIdService<A> {

}
