package org.edgexfoundry.engine.repository;

import org.edgexfoundry.rule.domain.CommonEventData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EPCISEventRepository extends JpaRepository<CommonEventData, Integer>{

}
