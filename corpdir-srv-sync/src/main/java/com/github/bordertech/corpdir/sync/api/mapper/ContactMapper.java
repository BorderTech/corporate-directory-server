package com.github.bordertech.corpdir.sync.api.mapper;

import com.github.bordertech.corpdir.api.v1.model.Channel;
import com.github.bordertech.corpdir.api.v1.model.Contact;

/**
 * Contact mapper
 * @author aswinkandula
 */
public class ContactMapper extends AbstractVersionMapper<Contact> {

	@Override
	public void map(Contact from, Contact to) {
		super.map(from, to);
		to.setFirstName(from.getFirstName());
		to.setLastName(to.getLastName());

		from.getChannels().forEach((Channel fromC) -> {

			Channel toC = to.getChannels().stream()
				.filter((Channel channel) -> channel.getValue().equals(fromC.getValue()))
				.findAny()
				.orElse(null);
  
			if (toC == null) {
				toC = new Channel(null);
				toC.setType(fromC.getType());
				toC.setCustom(fromC.isCustom());
				toC.setValue(fromC.getValue());
				to.getChannels().add(toC);
			}
			
		});

		to.getPositionIds().clear();
		to.getPositionIds().addAll(from.getPositionIds());
		to.setCompanyTitle(from.getCompanyTitle());
		to.getAddress().setState(from.getAddress().getState());
		to.setLocationId(from.getLocationId());
	}
	
}
