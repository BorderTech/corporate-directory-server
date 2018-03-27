package com.github.bordertech.corpdir.web.api.resource.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.VersionCtrl;
import com.github.bordertech.corpdir.modify.api.v1.VersionCtrlWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.VersionCtrlReadOnlyService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Version Control REST Resource.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Path(value = "v1/version")
public class VersionCtrlResource implements VersionCtrlReadOnlyService, VersionCtrlWriteService {

	private final VersionCtrlReadOnlyService readImpl;
	private final VersionCtrlWriteService writeImpl;

	/**
	 * @param impl the service implementation
	 */
	@Inject
	public VersionCtrlResource(final VersionCtrlReadOnlyService readImpl, final VersionCtrlWriteService writeImpl) {
		this.readImpl = readImpl;
		this.writeImpl = writeImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<VersionCtrl>> search(@QueryParam("search") final String search) {
		return readImpl.search(search);
	}

	@GET
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<VersionCtrl> retrieve(@PathParam("key") final String keyId) {
		return readImpl.retrieve(keyId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<VersionCtrl> create(final VersionCtrl type) {
		return writeImpl.create(type);
	}

	@PUT
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<VersionCtrl> update(@PathParam("key") final String keyId, final VersionCtrl type) {
		return writeImpl.update(keyId, type);
	}

	@DELETE
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public BasicResponse delete(@PathParam("key") final String keyId) {
		return writeImpl.delete(keyId);
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<Long> createVersion(@QueryParam("description") final String description) {
		return writeImpl.createVersion(description);
	}

	@PUT
	@Path("/copy/{fromVers}/to/{toVers}")
	@Override
	public BasicResponse copyVersion(@PathParam("fromVers") final Long fromId, @PathParam("toVers") final Long toId, @QueryParam("system") final boolean copySystem, @QueryParam("custom") final boolean copyCustom) {
		return writeImpl.copyVersion(fromId, toId, copySystem, copyCustom);
	}

}
