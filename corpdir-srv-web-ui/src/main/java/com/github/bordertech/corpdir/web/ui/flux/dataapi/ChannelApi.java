package com.github.bordertech.corpdir.web.ui.flux.dataapi;

import com.github.bordertech.corpdir.api.service.BasicIdService;
import com.github.bordertech.corpdir.api.v1.model.Channel;

/**
 * Channel has no backing service. The interface is only used for a new instance.
 *
 * @author jonathan
 */
public interface ChannelApi extends CorpCrudDataApi<Channel, BasicIdService<Channel>> {
}
