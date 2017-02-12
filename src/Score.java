/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (p0)
// FILE:             (Score.java)
//
// Authors: (Nafis Faisal Khan Arrafi)
// Author1: (name1,email1,netID1,lecture number1)
// Author2: (name2,email2,netID2,lecture number2)
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: Identify persons by name, relationship to you, and email. 
// Describe in detail the the ideas and help they provided. 
// 
// Online sources: avoid web searches to solve your problems, but if you do 
// search, be sure to include Web URLs and description of 
// of any information you find. 
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * (responsible for storing a score)
 *
 * <p>Bugs: (s)
 *
 * @author (Nafis Faisal Khan)
 */
public class Score {
	
	//stores  name
	private String name;
	
	//store pointsEarned
	private double pointsEarned;
	
	//stores possible points
	private double possiblePoints;
	
	public Score(String name, double pointsEarned, double possiblePoints){
		
		//checks the criteria before throwing an IllegalArgumentException 
		if(name == null || pointsEarned < 0 || possiblePoints < 0 || pointsEarned > possiblePoints){
			
			throw new IllegalArgumentException();
		}
		
		this.name = name;
		this.pointsEarned = pointsEarned;
		this.possiblePoints = possiblePoints;
		
		
	}
	
	/**
	 * (returns the name)
	 *
	 * PRECONDITIONS: (name may not be null)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @param () ()
	 * @param () ()
	 * @return (String name)
	 */
	public String getName(){
		
		return name;
	}
	
	/**
	 * (returns the pointsEarned)
	 *
	 * PRECONDITIONS: (pointsEarned may not be)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @param () ()
	 * @param () ()
	 * @return (double pointsEarned)
	 */
	public double getPoints(){
		
		return pointsEarned;
	}
	
	/**
	 * (returns the the max possible points)
	 *
	 * PRECONDITIONS: (possiblePoints may not be null)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @param () ()
	 * @param () ()
	 * @return (double possiblePoints)
	 */
	public double getMaxPossible(){
		
		return possiblePoints;
	}
	
	/**
	 * (returns the first letter of the name which represents the catergory)
	 *
	 * PRECONDITIONS: (name may not be null)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @param () ()
	 * @param () ()
	 * @return (String)
	 */
	public String getCategory(){
		
		return name.substring(0,1);
		
	}
	
	/**
	 * (returns the the percentage of pointsEarned)
	 *
	 * PRECONDITIONS: (pointsEarned and possiblePoints may not be null)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @param () ()
	 * @param () ()
	 * @return (double percent)
	 */
	public double getPercent(){
		
		//percent stores the percentage of pointsEarned 
		double percent = (pointsEarned * 100 / possiblePoints) ;
		
		return percent;
	} 
	
}
