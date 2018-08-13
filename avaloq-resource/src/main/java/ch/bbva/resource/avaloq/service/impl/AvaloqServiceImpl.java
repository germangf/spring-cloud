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
package ch.ggf.resource.avaloq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ggf.common.domain.BP;
import ch.ggf.common.domain.Container;
import ch.ggf.resource.avaloq.dao.MISDao;
import ch.ggf.resource.avaloq.service.AvaloqService;

/**
 * Service implementation that allows return Avaloq database information.
 *
 * @author garciag
 *
 */
@Service
public class AvaloqServiceImpl implements AvaloqService {

  @Autowired
  private MISDao avaloqDao;

  @Override
  public BP getBP(String bpNr) {
    return avaloqDao.getBP(bpNr);
  }

  @Override
  public Container getContainer(String containerNr) {
    return avaloqDao.getContainer(containerNr);
  }

}
