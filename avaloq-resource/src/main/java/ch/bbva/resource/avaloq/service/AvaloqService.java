/*
 * Copyright(c) 2013 BBVA (Suiza) SA All Rights Reserved.
 *
 * This software is the confidential and proprietary information of BBVA (Suiza) SA
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with BBVA (Suiza) SA
 *
 * Created on 01.11.2013
 *
 */
package ch.ggf.resource.avaloq.service;

import ch.ggf.common.domain.BP;
import ch.ggf.common.domain.Container;

/**
 * Service interface that allows return Avaloq database information.
 *
 * @author garciag
 *
 */
public interface AvaloqService {

  /**
   * Returns a BP data given its id.
   *
   * @param bpNr
   *          the BP number to be evaluated
   * @return a {@code BP} object
   *
   */
  BP getBP(String bpNr);

  Container getContainer(String containerNr);

}
