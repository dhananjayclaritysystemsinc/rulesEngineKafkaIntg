/*******************************************************************************
 * Copyright 2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * @microservice: support-rulesengine
 * @author: Jim White, Dell
 * @version: 1.0.0
 *******************************************************************************/

package org.edgexfoundry.engine;

import org.edgexfoundry.controller.CmdClient;
import org.edgexfoundry.engine.service.EPCISEventService;
import org.edgexfoundry.rule.domain.CommonEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutor {

  @Autowired
  private CmdClient client;
  
  @Autowired
  private EPCISEventService epcisEventService;

  private static final org.edgexfoundry.support.logging.client.EdgeXLogger logger =
      org.edgexfoundry.support.logging.client.EdgeXLoggerFactory
          .getEdgeXLogger(CommandExecutor.class);

  @Async
  public void fireCommand( String recordId, String body) {
    logger.info(
        "Sending request for command:  " + recordId + " with body: " + body);
    try {
      // for now - all rule engine requests are puts
      forwardRequest(recordId, body);
    } catch (Exception exception) {
      logger.error("Problem sending command to the device service " + exception);
    }
  }
  
  @Async
  public CommonEventData storeEvent(CommonEventData commonEventData) {
    logger.info(
        "Sending request for command with body: " + commonEventData.getBizlocation());
    try {
      // for now - all rule engine requests are puts
      return epcisEventService.saveEvent(commonEventData);//forwardRequest(recordId, commonEventData);
    } catch (Exception exception) {
      logger.error("Problem sending command to the device service " + exception);
    }
	return commonEventData;
  }
  
	private CommonEventData forwardRequest(String recordId, CommonEventData commonEventData) {
		System.out.println("Comomn Event Data : " + commonEventData);
		return commonEventData;
	}

	private void forwardRequest(String recordId, String body) {
		// TODO Auto-generated method stub
		System.out.println("recordId : "+recordId + " body : "+body);

	}

@Async
  public void fireCommand(String deviceId, String commandId, String body) {
    logger.info(
        "Sending request to:  " + deviceId + "for command:  " + commandId + " with body: " + body);
    try {
      // for now - all rule engine requests are puts
      forwardRequest(deviceId, commandId, body, true);
    } catch (Exception exception) {
      logger.error("Problem sending command to the device service " + exception);
    }
  }

  private void forwardRequest(String id, String commandId, String body, boolean isPut) {
    if (client != null) {
      if (isPut)
        logger.debug("Resposne from command put is:  " + client.put(id, commandId, body));
      else
        logger.debug("Resposne from command get is:  " + client.get(id, commandId));
    } else {
      logger.error("Command Client not available - no command sent for: " + id + " to " + commandId
          + " containing: " + body);
    }
  }

}
