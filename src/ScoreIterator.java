import java.util.NoSuchElementException;

public class ScoreIterator implements ScoreIteratorADT {
	
	private ScoreList list;
	
	private int currPos;
	
	private String category;
	
	public ScoreIterator(ScoreList list, String category ){
		
	this.list = list;
	
	this.category = category;
	
	this.currPos = 0; 
		
	}

	@Override
	public boolean hasNext() {
				
		for(int i = currPos; i < list.size(); i++){
			
			if(list.get(currPos).getCategory().equals(category)){
				
				return true;
				
			}else{ currPos++ ; }
			
		}
		
		return false;
	}

	@Override
	public Score next() {
		
		if(!hasNext()) throw new NoSuchElementException();
		
		Score score;
		
		while(currPos < list.size() ){
			
			if(list.get(currPos).getCategory().equals(category)){
				
				score = list.get(currPos++);
				
				return score;
			}
			
		}
		
		return null ;
	}



}
