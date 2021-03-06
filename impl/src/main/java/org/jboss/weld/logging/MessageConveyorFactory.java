/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat, Inc., and individual contributors
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
package org.jboss.weld.logging;

import java.util.Locale;

import ch.qos.cal10n.IMessageConveyor;

public abstract class MessageConveyorFactory
{
   
   private static MessageConveyorFactory INSTANCE;
   
   private static MessageConveyorFactory load()
   {
      return new WeldMessageConveyerFactory();
   }
   
   public static MessageConveyorFactory messageConveyerFactory()
   {
      if (INSTANCE == null)
      {
         INSTANCE = load();
      }
      return INSTANCE;
   }
   
   public static void cleanup()
   {
      INSTANCE = null;
   }
   
   public static IMessageConveyor defaultMessageConveyer(String subsystem)
   {
      return messageConveyerFactory().getDefaultMessageConveyer(subsystem);
   }

   /**
    * Get the message conveyer for the default locale.
    * 
    * By default, Locale.getDefault() will be used as the locale, but a custom
    * implementation of MessageConveyerFactory may choose to use an alternative
    * locale.
    * 
    */
   public abstract IMessageConveyor getDefaultMessageConveyer(String subsystem);

   /**
    * Get the message conveyer for the given locale.
    * 
    */
   public abstract IMessageConveyor getMessageConveyer(Locale locale, String subsystem);

}