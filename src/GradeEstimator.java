import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GradeEstimator {
	
	 private ScoreList list;
	 private final String[] GRADE_LETTER;
	 private final double[] GRADE_THRESHOLD;
	 private final String[] CATEGORY_KEY;
	 private final double[] CATEGORY_WEIGHT;
	 private static Scanner fileInput ;

	 public GradeEstimator(String[] grade_letter, double[] grade_threshold, String[] category_key,double[] category_weight, ScoreList list) {
	    
		this.GRADE_LETTER = grade_letter;
	    this.GRADE_THRESHOLD = grade_threshold;
	    this.CATEGORY_KEY = category_key;
	    this.list = list;
	    this.CATEGORY_WEIGHT = category_weight;
	  
	 }
	
	public static void main(String [] args){
		
		if(args.length != 1) {
			
			System.out.println(Config.USAGE_MESSAGE);
		}
		else{
			
			try{
				
				GradeEstimator gradeEstimator = createGradeEstimatorFromFile(args[0]);
				
				System.out.println(gradeEstimator.getEstimateReport());
				
			}catch (FileNotFoundException e) {
	            
				System.out.println("File Not Found");
	        
			} catch (GradeFileFormatException e) {
	            
	        	System.out.println("GradeFileFormatException");
	        
			} catch (NumberFormatException e){
	        			
				System.out.println("GradeFileFormatException");
	        	
	        }catch(ArrayIndexOutOfBoundsException e){
	        	
	        	System.out.println("GradeFileFormatException");
	        	
	        }
		}
	}
	
	public static GradeEstimator createGradeEstimatorFromFile( String gradeInfo ) 
		      throws FileNotFoundException, GradeFileFormatException {
		
		String grades = fileReader(gradeInfo);
		
		GradeEstimator gradeEstimator = makeGradeEstimator(grades);
		
		return gradeEstimator;
	}
	
	public String getEstimateReport(){
		
		String gradeReport = "";
		
		String tempReport = "";
		
		double  averageScore ;
		
		double  weightedScore ;
		
		double sum ;
		
		int count;
		
		String grade = "";
		
		double totalScore = 0;
		
		ScoreIterator  scoreIterator ;
		
		for(int i = 0 ; i < CATEGORY_KEY.length; i++){
			
			scoreIterator = new ScoreIterator(list,CATEGORY_KEY[i].substring(0,1));
			
			while(scoreIterator.hasNext()){
				
				Score score = scoreIterator.next();
				
				gradeReport += String.format("%s   %7.2f%s",score.getName(),score.getPercent(),"%\n"); 
				
				}
		}
		
		gradeReport += "Grade estimate is based on "+list.size()+" scores\n";
		
		
		for(int i = 0; i < CATEGORY_KEY.length; i++){
			
			scoreIterator = new ScoreIterator(list,CATEGORY_KEY[i].substring(0,1));
			
			averageScore = 0;
			
			weightedScore = 0;
			
			sum = 0;
			
			count = 0;
			
			while(scoreIterator.hasNext()){
				
				Score score = scoreIterator.next();
				
				sum += score.getPercent();
				
				count++;
				
			}
			
			averageScore = sum/count;
			
			weightedScore = (averageScore * CATEGORY_WEIGHT[i])/100;
			
			totalScore += weightedScore;
			
			tempReport += String.format("  %5.2f%s = %5.2f%s of %.0f%s for %s", weightedScore,"%",averageScore,"%",CATEGORY_WEIGHT[i],"%",CATEGORY_KEY[i]+"\n");
			
		}
		
		gradeReport += tempReport + "--------------------------------\n";
		
		for(int i = 0; i < GRADE_THRESHOLD.length;i++){
			
			if(totalScore > GRADE_THRESHOLD[i]){
				
				grade = GRADE_LETTER[i];
				
				break;
				
			}
				
		}
		
		gradeReport += String.format("  %5.2f%s %s \n%s %s",totalScore,"%","weighted percent","Letter Grade Estimate:",grade ); 
		
		return gradeReport;
		
	}
	
	private static String fileReader(String dir)throws FileNotFoundException {
		
		 	fileInput = new Scanner(new File(dir));
		 
	        String gradeInfo = "";
	        
	        while (fileInput.hasNextLine()){
	        	
	            gradeInfo += fileInput.nextLine() + "\n"; 
	            
	        }
	        
	        fileInput.close();
	        
	        return gradeInfo;
		
	}
	
	private static GradeEstimator makeGradeEstimator( String grades) throws GradeFileFormatException {
		
		fileInput = new Scanner(grades);
		
		int i = 0;
		
		String [] gradeInfo ;
		
		String [] gradeLetter ;
		
		double [] gradeThreshold;
		
		String [] temp ;
		
		String [] categoryKey;
		
		double [] categoryWeight;
		
		ScoreList scoreList = new ScoreList();
		
		Score score;
		
		while(fileInput.hasNextLine()){
			
			String x = fileInput.nextLine();
				
			if( !x.equals("") ) i++;
			
		}
		
		gradeInfo = new String[i];
		
		fileInput = new Scanner(grades);
		
		i = 0;
		
		while(fileInput.hasNextLine() && i <gradeInfo.length){
			
			String x = fileInput.nextLine();
			
			if(x.contains("#")){
				
				gradeInfo[i] = x.split("#")[0].trim();
			
			}else{
				
				gradeInfo[i] = x.trim();
			}
			
			if(x.equals("")){
				
				i--;
			}
			
			i++;
		}
		
		i = 0;
		
		while( i < gradeInfo.length -1){
			
			if(gradeInfo[i].equals("")){
				
				gradeInfo[i] = gradeInfo[i+1];
			
			}
				i++;
		}
		
		i = 0;
		
		gradeLetter = gradeInfo[0].split(" ");
		
		temp = gradeInfo[1].split(" ");
		
		gradeThreshold = new double [temp.length];
		
		while(i < temp.length){
			
			gradeThreshold[i] = Double.parseDouble(temp[i]);
			
			i++;
		}
		
		i = 0;
		
		categoryKey = gradeInfo[2].split(" ");
		
		temp = gradeInfo[3].split(" ");
		
		categoryWeight = new double [temp.length];
		
		while(i < temp.length){
			
			categoryWeight[i] = Double.parseDouble(temp[i]);
			
			i++;
		}
		
		i = 0;
		
		for(int j = 4; j < gradeInfo.length; j++){
			
			temp = gradeInfo[j].split(" ");
			
			score = new Score(temp[0]+"",Double.parseDouble(temp[1]), Double.parseDouble(temp[2]));
			
			scoreList.add(score);
			
		}
	
		  if (scoreList.size() == 0){
			  
			  throw new GradeFileFormatException();
		  }
	
		return new GradeEstimator(gradeLetter,gradeThreshold,categoryKey,categoryWeight, scoreList);
	}


}
