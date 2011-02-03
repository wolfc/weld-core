package org.jboss.weld.tests.enterprise;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
@SessionScoped
public class AnotherBean implements Serializable
{
   private int c = 0;
   
   public int counter()
   {
      return c++;
   }

   @Override
   public String toString()
   {
      return "AnotherBean{" +
              "c=" + c +
              '}';
   }
}
