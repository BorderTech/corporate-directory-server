package com.github.bordertech.corpdir.sync.api.mapper;

import com.github.bordertech.corpdir.api.v1.model.Position;

/**
 * Position mapper
 * @author aswinkandula
 */
public class PositionMapper extends AbstractVersionMapper<Position> {

	@Override
	public void map(Position source, Position destination) {
		super.map(source, destination);
		destination.setTypeId(source.getTypeId());
		destination.setOuId(source.getOuId());
		
		source.getManageOuIds().stream()
			.filter((String fromOuId) -> (!destination.getManageOuIds().contains(fromOuId)))
			.forEach((String newOuId) -> {
				destination.getManageOuIds().add(newOuId);
			});
	}
	
}
