/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
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

package org.jboss.webbeans.context;

import javax.context.ConversationScoped;

import org.jboss.webbeans.log.LogProvider;
import org.jboss.webbeans.log.Logging;

/**
 * The conversation context
 * 
 * @author Nicklas Karlsson
 */
public class ConversationContext extends AbstractThreadLocalMapContext
{
   private static LogProvider log = Logging.getLogProvider(ConversationContext.class);

   public static ConversationContext INSTANCE;
   
   public static ConversationContext create()
   {
      INSTANCE = new ConversationContext();
      return INSTANCE;
   }

   /**
    * Constructor
    */
   public ConversationContext()
   {
      super(ConversationScoped.class);
      log.trace("Created conversation context");
   }

   @Override
   public String toString()
   {
      String active = isActive() ? "Active " : "Inactive ";
      String beanStoreInfo = getBeanStore() == null ? "" : getBeanStore().toString();
      return active + "conversation context " + beanStoreInfo;
   }

}