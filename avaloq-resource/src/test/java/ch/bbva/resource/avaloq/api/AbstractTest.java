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
package ch.ggf.resource.avaloq.api;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
public abstract class AbstractTest {

  @Autowired
  private WebApplicationContext wac;

  public WebApplicationContext getWac() {
    return wac;
  }

}
