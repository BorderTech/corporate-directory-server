package com.github.bordertech.corpdir.jpa.modify.common.svc;

import com.github.bordertech.corpdir.api.common.ApiKeyIdObject;
import com.github.bordertech.corpdir.api.service.modify.BasicKeyIdWriteService;
import com.github.bordertech.corpdir.jpa.common.feature.PersistKeyIdObject;
import javax.inject.Singleton;

/**
 * Keyed Entity JPA write (modifiable) service implementation.
 *
 * @param <A> the API object type
 * @param <P> the entity type
 * @author Jonathan Austin
 * @author Aswin Kandula
 * @since 1.0.0
 */
@Singleton
public abstract class JpaBasicKeyIdWriteService<A extends ApiKeyIdObject, P extends PersistKeyIdObject> extends JpaBasicIdWriteService<A, P> implements BasicKeyIdWriteService<A> {
    
}
