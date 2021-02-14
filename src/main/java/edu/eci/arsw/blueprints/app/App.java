package edu.eci.arsw.blueprints.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.*;
import edu.eci.arsw.blueprints.services.*;
import edu.eci.arsw.blueprints.model.*;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;


public class App {
	public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        try {
			bs.addNewBlueprint(new Blueprint("Saenz", "plano1", new Point[] {new Point(1,1)}) );
			bs.getBlueprintsByAuthor("Saenz");
			//System.out.println(bs.getBlueprintsByAuthor("Saenz"));
			//System.out.println(bs.getAllBlueprints());
			//System.out.println(bs.getBlueprint("Saenz", "plano1"));
			
		} catch (BlueprintPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println(gc.check("la la la "));
        catch (BlueprintNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
