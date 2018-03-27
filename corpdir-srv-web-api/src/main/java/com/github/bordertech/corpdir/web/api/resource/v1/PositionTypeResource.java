package com.github.bordertech.corpdir.web.api.resource.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.api.v1.model.PositionType;
import com.github.bordertech.corpdir.modify.api.v1.PositionTypeWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.PositionTypeReadOnlyService;
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
 * Position Type REST Resource.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Path(value = "v1/positiontypes")
public class PositionTypeResource implements PositionTypeReadOnlyService, PositionTypeWriteService {

	private final PositionTypeReadOnlyService readImpl;
	private final PositionTypeWriteService writeImpl;

	/**
	 * @param readImpl
	 * @param writeImpl
	 */
	@Inject
	public PositionTypeResource(final PositionTypeReadOnlyService readImpl, final PositionTypeWriteService writeImpl) {
		this.readImpl = readImpl;
		this.writeImpl = writeImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<PositionType>> search(@QueryParam("search") final String search) {
		return readImpl.search(search);
	}

	@GET
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<PositionType> retrieve(@PathParam("key") final String keyId) {
		return readImpl.retrieve(keyId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<PositionType> create(final PositionType type) {
		return writeImpl.create(type);
	}

	@PUT
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<PositionType> update(@PathParam("key") final String keyId, final PositionType type) {
		return writeImpl.update(keyId, type);
	}

	@DELETE
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public BasicResponse delete(@PathParam("key") final String keyId) {
		return writeImpl.delete(keyId);
	}

	@GET
	@Path("/{key}/positions")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<Position>> getPositions(@PathParam("key") final String keyId) {
		return readImpl.getPositions(keyId);
	}

}
