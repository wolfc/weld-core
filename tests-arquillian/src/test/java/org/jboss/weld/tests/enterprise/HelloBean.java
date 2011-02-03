/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.tests.enterprise;

import org.jboss.ejb3.annotation.CacheConfig;

import javax.annotation.Resource;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

@Stateful
@SessionScoped
@CacheConfig(idleTimeoutSeconds=1)
public class HelloBean implements IHelloBean
{
   @Inject
   private AnotherBean bean;
   
   @Resource(mappedName = "java:comp/BeanManager")
   private BeanManager beanManager;

   public String sayHello()
   {
      return "hello";
   }

   public String sayGoodbye()
   {
      return beanManager.getELResolver() != null ? "goodbye" : "error";
   }

   @PostActivate
   public void postActivate()
   {
      System.err.println("postActivate! " + bean);
      bean.counter();
      // counter == 2
   }

   @PrePassivate
   public void prePassivate()
   {
      bean.counter();
      System.err.println("prePassivate! " + bean);
   }
   
   @Remove
   public void remove()
   {
   }
}
