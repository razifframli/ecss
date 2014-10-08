/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author WC
 */
public class Item {

		private String id;
		private String description;

		public Item(String id, String description)
		{
			this.id = id;
			this.description = description;
		}

		public String getId()
		{
			return id;
		}

		public String getDescription()
		{
			return description;
		}

		public String toString()
		{
			return description;
		}
    
}
