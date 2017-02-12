/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2016 
// PROJECT:          (p0)
// FILE:             (ScoreList.java)
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
 * (Implements ScoreListADT and is the list of references to all the scores )
 *
 * <p>Bugs: ()
 *
 * @author (Nafis Faisal Khan)
 */
import java.util.Arrays;

public class ScoreList implements ScoreListADT {
	
	//for storing the references to all the score objects	
	private Score scoreListArray[] ;
	
	//to keep track of the number of scores in the array
	private int numOfScores ;
	
	
	public ScoreList(){
		
		numOfScores = 0;
		scoreListArray = new Score[numOfScores];
		
	}
	
	/** 
	 * Returns the number of Scores in the list or zero
	 * @return the number of scores in this list
	 */
	@Override
	public int size() {
		
		return scoreListArray.length;
	}

	/** 
	 * Adds the score to the end of this list.
	 * 
	 * @param s a non-null Score to place as the last item in the list.
	 * @throws IllegalArgumentException
	 */
	@Override
	public void add(Score s) throws IllegalArgumentException {
		
		if(s == null){ 
			
			throw new IllegalArgumentException(); 
			
		}
		
		//calls expandArray if scoreListArry is full or has a length of 0
		if(numOfScores == scoreListArray.length){ 
			
			expandArray();
			
		}
		
		scoreListArray[numOfScores] = s;
		
		numOfScores++;
		
	}

	/**
	 * Removes and returns the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public Score remove(int i) throws IndexOutOfBoundsException {
		
		
		if(i < 0 || i > scoreListArray.length ){
			
			throw new IndexOutOfBoundsException();
			
		}
		
		//new score object that stores the reference to the score object that is to be removed
		Score returnScore = this.scoreListArray[i]; ;
		
		//to be used to determine the length of the tempScoreList which is 1 less than
		//scoreListArray's length
		int size = scoreListArray.length - 1;
		
		//temporary array
		Score [] tempScoreList = new Score[size];
		
		//if index i is 0 and scoreListArray has one score in it then 
		//creates a new scoreListArray with size zero
		if(i == 0 && scoreListArray.length == 1){
		
			scoreListArray = new Score[0];
		
		}

		//shifts all the elements to the left in the array from the index of the element which is to be removed
		for(int x = i ; x < size; x++ ){
			
			scoreListArray[x] = scoreListArray[x + 1]; 
			
		}
		
		//stores the shifted scoreListArray into tempScoreList
		for(int x = 0; x < size; x++){
			
			tempScoreList[x] = scoreListArray[x];
			
		}
		
		//assigns the temporary array to scoreListArray
		scoreListArray = tempScoreList;
		
		//decrements number of scores  
		this.numOfScores--;
		
		return returnScore;
	}

	/**
	 * Returns (without removing) the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public Score get(int i) throws IndexOutOfBoundsException {
		
		return scoreListArray[i];
	}
	
	/**
	 * (this method expands the array scoreListArray by 1)
	 *
	 * PRECONDITIONS: (numOfScores and scoreListArray may not be null)
	 * 
	 * POSTCONDITIONS: ()
	 *
	 * @param () ()
	 * @return (void)
	 */
	private void expandArray() {
		
		//new array which is 1 longer than the length of number of scores 
	    Score[] newScoreListArray = new Score[numOfScores + 1];
	    
	    //stores the elements in scoreListArray in newSCoreLIstArray 
	    for (int i = 0; i < numOfScores; i++) {
	    	
	        newScoreListArray[i] = scoreListArray[i];
	    }
	    
	    scoreListArray = newScoreListArray;
	
	}

}
