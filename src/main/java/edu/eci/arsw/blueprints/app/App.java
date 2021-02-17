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
			bs.addNewBlueprint(new Blueprint("Saenz", "plano1", new Point[] {new Point(1,1), new Point(5,5), new Point(2,3), new Point(2,3)}) );
			//bs.getBlueprintsByAuthor("Saenz");
			//System.out.println(bs.getBlueprintsByAuthor("Saenz"));
			//System.out.println(bs.getAllBlueprints());
			
			//Esta es la prueba del filtro, en este caso se usó el filtro muestreo.
			//Asegurese que el parametro de la etiqueta Qualifier sea "Muestreo" para que se inyecte el filtro de muestreo
			Blueprint bp = bs.getBlueprint("Saenz", "plano1");
			System.out.println(bp.getPoints().get(0).getX() == 1);
			System.out.println(bp.getPoints().get(0).getY() == 1);
			System.out.println(bp.getPoints().get(1).getX()== 2);
			System.out.println(bp.getPoints().get(1).getY()== 3);
			System.out.println(bp.getPoints().size() == 2);
			//Funciona, pues eliminó el segundo punto ya que lo hace de manera intercalada
			
			//A continuación se hará la prueba de filtro por redundancia, asegurese de cambiar el parámetro de la etiqueta Qualifer 
			// Por "Redundancia" para aplicar el filtro de redundancia
			/***
			bs.addNewBlueprint(new Blueprint("Jose", "plano1", new Point[] {new Point(1,1), new Point(1,1), new Point(2,3), new Point(2,3)}) );
			Blueprint bup = bs.getBlueprint("Saenz", "plano1");
			//Compara que el tamaño de la lista de puntos sea de dos, para verificar que si se haya filtrado la lista original
			System.out.println(bup.getPoints().size() == 2);
			//Compara punto por punto, con el valor que debería dar en realidad para comprobar que el filtrado se hizo bien.
			System.out.println(bup.getPoints().get(0).getX() == 1);
			System.out.println(bup.getPoints().get(0).getY() == 1);
			
			System.out.println(bup.getPoints().get(1).getX() == 2);
			System.out.println(bup.getPoints().get(1).getY() == 3);
			***/
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
