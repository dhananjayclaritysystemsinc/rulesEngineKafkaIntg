package org.edgexfoundry.engine.service;

import org.edgexfoundry.engine.repository.EPCISEventRepository;
import org.edgexfoundry.rule.domain.CommonEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EPCISEventServiceImpl implements EPCISEventService {

	@Autowired
	private EPCISEventRepository epcisEventRepository;
	
	@Override
	public CommonEventData saveEvent(CommonEventData commonEventData) {
		return epcisEventRepository.save(commonEventData);
	}

}
