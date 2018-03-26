package com.github.bordertech.corpdir.web.api.resource.v1;

import com.github.bordertech.corpdir.api.response.BasicResponse;
import com.github.bordertech.corpdir.api.response.DataResponse;
import com.github.bordertech.corpdir.api.v1.model.OrgUnit;
import com.github.bordertech.corpdir.api.v1.model.Position;
import com.github.bordertech.corpdir.modify.api.v1.OrgUnitWriteService;
import com.github.bordertech.corpdir.readonly.api.v1.OrgUnitReadOnlyService;
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
 * Organisation Unit REST Resource.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
@Path(value = "v1/orgunits")
public class OrgUnitResource implements OrgUnitReadOnlyService, OrgUnitWriteService {

	private final OrgUnitReadOnlyService readImpl;
	private final OrgUnitWriteService writeImpl;

	/**
	 * @param readImpl
	 * @param writeImpl
	 */
	@Inject
	public OrgUnitResource(final OrgUnitReadOnlyService readImpl, final OrgUnitWriteService writeImpl) {
		this.readImpl = readImpl;
		this.writeImpl = writeImpl;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<OrgUnit>> search(@QueryParam("search") final String search) {
		return readImpl.search(search);
	}

	@GET
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<OrgUnit> retrieve(@PathParam("key") final String keyId) {
		return readImpl.retrieve(keyId);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<OrgUnit> create(final OrgUnit orgUnit) {
		return writeImpl.create(orgUnit);
	}

	@PUT
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<OrgUnit> update(@PathParam("key") final String keyId, final OrgUnit orgUnit) {
		return writeImpl.update(keyId, orgUnit);
	}

	@DELETE
	@Path("/{key}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public BasicResponse delete(@PathParam("key") final String keyId) {
		return writeImpl.delete(keyId);
	}

	@GET
	@Path("/{key}/orgunits")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<OrgUnit>> getSubs(@PathParam("key") final String keyId) {
		return readImpl.getSubs(keyId);
	}

	@PUT
	@Path("/{key}/orgunits/{subKey}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<OrgUnit> addSub(@PathParam("key") final String keyId, @PathParam("subKey") final String subKeyId) {
		return writeImpl.addSub(keyId, subKeyId);
	}

	@DELETE
	@Path("/{key}/orgunits/{subKey}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<OrgUnit> removeSub(@PathParam("key") final String keyId, @PathParam("subKey") final String subKeyId) {
		return writeImpl.removeSub(keyId, subKeyId);
	}

	@GET
	@Path("/{key}/positions")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<List<Position>> getPositions(@PathParam("key") final String keyId) {
		return readImpl.getPositions(keyId);
	}

	@PUT
	@Path("/{key}/positions/{positionKey}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<OrgUnit> addPosition(@PathParam("key") final String keyId, @PathParam("positionKey") final String positionKeyId) {
		return writeImpl.addPosition(keyId, positionKeyId);
	}

	@DELETE
	@Path("/{key}/positions/{positionKey}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<OrgUnit> removePosition(@PathParam("key") final String keyId, @PathParam("positionKey") final String positionKeyId) {
		return writeImpl.removePosition(keyId, positionKeyId);
	}

	@GET
	@Path("/{key}/managers")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public DataResponse<Position> getManagerPosition(@PathParam("key") final String keyId) {
		return readImpl.getManagerPosition(keyId);
	}

	@Override
	public DataResponse<Position> getManagerPosition(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<List<OrgUnit>> getSubs(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<OrgUnit> addSub(Long versionId, String keyId, String subKeyId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<OrgUnit> removeSub(Long versionId, String keyId, String subKeyId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<List<OrgUnit>> search(Long versionId, String search) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<OrgUnit> retrieve(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<List<Position>> getPositions(Long versionId, String keyId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<OrgUnit> addPosition(Long versionId, String keyId, String positionKeyId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<OrgUnit> removePosition(Long versionId, String keyId, String positionKeyId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<List<OrgUnit>> getRootItems() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public DataResponse<List<OrgUnit>> getRootItems(Long versionId) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
