package org.edgexfoundry.engine.service;

import org.edgexfoundry.rule.domain.CommonEventData;

public interface EPCISEventService {

	CommonEventData saveEvent(CommonEventData commonEventData);

}
