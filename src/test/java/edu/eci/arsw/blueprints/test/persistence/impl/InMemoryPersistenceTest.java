/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
    }
        
        
        @Test
        public void foundOutTheCorrectBlueprintGivenTheAuthorAndBlueprint() {
            BlueprintsPersistence mbp = new InMemoryBlueprintPersistence();
            Point[] p = {new Point(1,2)};
            Blueprint jose = new Blueprint("Josecito", "plano1", p);
            try {
            	
				mbp.saveBlueprint(jose);
			} catch (BlueprintPersistenceException e) {
				// TODO Auto-generated catch block
				fail("Impossible to save the Blueprint");
			}
            Blueprint bp = null;
            try {
				bp = mbp.getBlueprint("Josecito", "plano1");
			} catch (BlueprintNotFoundException e) {
				// TODO Auto-generated catch block
				fail("The Author or Blueprint does not exist");
			}
            assertEquals(jose, bp); 
        }
        
        
        @Test
        public void shouldFindOutAllTheBlueprintsOfJose() {
        	BlueprintsPersistence mbp = new InMemoryBlueprintPersistence();
        	
        	Point[] p = {new Point(1,2)};
        	Blueprint jose = new Blueprint("Josecito", "plano1", p);
        	Blueprint jose2 = new Blueprint("Josecito", "plano2", p);
        	Blueprint jose3 = new Blueprint("Josecito", "plano3", p);
        	Blueprint s = new Blueprint("Saenz", "plano4", p);
        	Set<Blueprint> correct = new HashSet<Blueprint>();
        	correct.add(jose);
        	correct.add(jose2);
        	correct.add(jose3);
        	try {

        		mbp.saveBlueprint(jose);
        		mbp.saveBlueprint(jose2);
        		mbp.saveBlueprint(jose3);
        		mbp.saveBlueprint(s);
        	} catch (BlueprintPersistenceException e) {
        		// TODO Auto-generated catch block
        		fail("Impossible to save the Blueprint");
        	}
        	Set<Blueprint> bp = null;
        	try {
        		bp = mbp.getBlueprintsByAuthor("Josecito");
        	} catch (BlueprintNotFoundException e) {
        		// TODO Auto-generated catch block
        		fail("The Author does not exist");
        	}
        	assertEquals(correct, bp); 
        }
        
        
        @Test
        public void ShouldThrowBlueprintNotFoundExceptionBecauseTheAuthorDoesNotExist() {
        	BlueprintsPersistence mbp = new InMemoryBlueprintPersistence();
        	
        	Point[] p = {new Point(1,2)};
        	Blueprint jose = new Blueprint("Josecito", "plano1", p);
        	try {
        		mbp.saveBlueprint(jose);
        	} catch (BlueprintPersistenceException e) {
        		// TODO Auto-generated catch block
        		fail("Impossible to save the Blueprint");
        	}
        	try {
        		mbp.getBlueprintsByAuthor("Jose");
        	} catch (BlueprintNotFoundException e) {
        		// This test should fail, because the author we're looking for does not exist
        		System.out.println("Exception thrown");
        	}
        }
        
        @Test
        public void ShouldThrowBlueprintNotFoundExceptionBecauseTheBlueprintDoesNotExist() {
        	BlueprintsPersistence mbp = new InMemoryBlueprintPersistence();
        	
        	Point[] p = {new Point(1,2)};
        	Blueprint jose = new Blueprint("Josecito", "plano1", p);
        	try {
        		mbp.saveBlueprint(jose);
        	} catch (BlueprintPersistenceException e) {
        		// TODO Auto-generated catch block
        		fail("Impossible to save the Blueprint");
        	}
        	try {
        		mbp.getBlueprint("Josecito", "plano");
        	} catch (BlueprintNotFoundException e) {
        		// This test should fail, because the blueprint we're looking for does not exist
        		System.out.println("Exception thrown");
        	}
        }
        
        
        @Test
        public void ShouldThrowBlueprintNotFoundExceptionBecauseTheTupleDoesNotExist() {
        	BlueprintsPersistence mbp = new InMemoryBlueprintPersistence();
        	
        	Point[] p = {new Point(1,2)};
        	Blueprint jose = new Blueprint("Josecito", "plano1", p);
        	try {
        		mbp.saveBlueprint(jose);
        	} catch (BlueprintPersistenceException e) {
        		// TODO Auto-generated catch block
        		fail("Impossible to save the Blueprint");
        	}
        	try {
        		mbp.getBlueprint("alfredo", "alfredo_plano");
        	} catch (BlueprintNotFoundException e) {
        		// This test should fail, because the tuple we're looking for does not exist
        		System.out.println("Exception thrown");
        	}
        }
        
        
        
        
        @Test
        public void ShouldShowAllTheBlueprintsStored() {
        	BlueprintsPersistence mbp = new InMemoryBlueprintPersistence();
        	Point[] p = {new Point(0,0)};
        	Blueprint jose = new Blueprint("Saenz", "plano1", p);
        	Blueprint jose2 = new Blueprint("Josecito", "plano2", p);
        	Blueprint jose3 = new Blueprint("Josecito", "plano3", p);
        	Set<Blueprint> correct = new HashSet<Blueprint>();
        	correct.add(jose);
        	correct.add(jose2);
        	correct.add(jose3);
        	try {
        		mbp.saveBlueprint(jose);
        		mbp.saveBlueprint(jose2);
        		mbp.saveBlueprint(jose3);
        	} catch (BlueprintPersistenceException e) {
        		// TODO Auto-generated catch block
        		fail("Impossible to save the Blueprint");
        	}
      
        	assertEquals(correct, mbp.getAllBlueprints());
        
        }
    
}
