package com.github.bordertech.corpdir.web.api.resource.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.UnitType;
import com.github.bordertech.corpdir.modify.api.v1.UnitTypeWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.UnitTypeReadOnlyService;
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
 * Organisation Unit Type REST Resource.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Path(value = "v1/unittypes")
public class UnitTypeResource implements UnitTypeReadOnlyService, UnitTypeWriteService {

	private final UnitTypeReadOnlyService readImpl;
	private final UnitTypeWriteService writeImpl;

	/**
         * @param readImpl the read service implementation
	 * @param writeImpl the write service implementation
	 */
	@Inject
	public UnitTypeResource(final UnitTypeReadOnlyService readImpl, final UnitTypeWriteService writeImpl) {
		this.readImpl = readImpl;
		this.writeImpl = writeImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<UnitType>> search(@QueryParam("search") final String search) {
		return readImpl.search(search);
	}

	@GET
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<UnitType> retrieve(@PathParam("key") final String keyId) {
		return readImpl.retrieve(keyId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<UnitType> create(final UnitType type) {
		return writeImpl.create(type);
	}

	@PUT
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<UnitType> update(@PathParam("key") final String keyId, final UnitType type) {
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
	@Path("/{key}/units")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<OrgUnit>> getOrgUnits(@PathParam("key") final String keyId) {
		return readImpl.getOrgUnits(keyId);
	}

}
