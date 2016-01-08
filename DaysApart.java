public class DaysApart {
    
  public static int solution(int m1, int d1, int y1, int m2, int d2, int y2){
        int yearApart = Math.abs(y2-y1);
        return daysApart(m1,d1,y1, m2,d2,y2);
  }

  public static int daysApart(int m1, int d1, int y1, int m2, int d2, int y2){
	  	// if both inputs are valid 
		if (isValid(m1,d1,y1) && isValid(m2,d2,y2))
	  	{
			// PRE-defines 
			int previousYear = 0;
			int postYear = 0;
			int previousMonth = 0;
			int postMonth = 0;
			int previousDay = 0;
			int postDay = 0;
			int daysGap = 0;
			//Pre-calculations & set up
			int yearGap = Math.abs(y2-y1);
	  		if (isBefore(m1,d1,y1,m2,d2,y2))
	  		{
	  			previousYear = y1 ;
	  			postYear = y2;
	  			previousMonth = m1;
	  			postMonth = m2;
	  			previousDay = d1;
	  			postDay = d2;
	  		}
	  		
	  		else
	  		{
	  			previousYear = y2;
	  			postYear = y1;
	  			previousMonth = m2;
	  			postMonth = m1;
	  			previousDay = d2;
	  			postDay = d1;
	  			
	  		}
	  		
	  		
	  		/// Main calculation part 
	  		 			 
	  		// Same Year 
	  		if (yearGap == 0)
	  		{
	  			daysGap = Math.abs(daysToEndOfYear(m1,d1,y1)-daysToEndOfYear(m2,d2,y2));
	  			return daysGap;  // just return day differences
	  		}

	  		else
	  			for (int i=previousYear; i<=postYear; i++ )
	  			{
	  				if (i == previousYear)
	  					daysGap+= daysToEndOfYear(previousMonth, previousDay, previousYear);
	  				else if ( i == postYear )
	  					daysGap+= dayOfYear(postMonth, postDay,postYear);
	  				else
	  					daysGap+= daysInYear(i);
	  			}
	  			return daysGap;
	  		
	  		
	  		
	  	}
	  	else 
	  		return -1;
	  	
  }
  
  
  //daysOfYear  
  public static int dayOfYear(int m1,int d1, int y1){
		int numOfDays[] = {999,31,28,31,30,31,30,31,31,30,31,30,31};  
		int totalDays = 0;
		
		if(y1%4 == 0 && (y1%100 != 0 || y1%400 ==0))
		{ // this if condition is for leaf month , Feb 29 exists 
			numOfDays[2]=29;
			
			for (int i = 1; i<= m1 ; i++)
			{
				if (i == m1) // first day 
				{
					totalDays += d1; 
				}
				else{
					totalDays += numOfDays[i];
				}
				
			}
		}
		else // non - leaf month 
		{
			for (int i = 1; i<= m1 ; i++)
			{
				if (i == m1) // first day 
				{
					totalDays += d1; 
				}
				else{
					totalDays += numOfDays[i];
				}
				
			}
		}
 
		
		return totalDays;
  }
  
  public static boolean isValid(int m1, int d1, int y1){
	boolean _b_m = false , _b_d = false, _b_y = false;
	int numOfDays[] = {999,31,28,31,30,31,30,31,31,30,31,30,31};  
	
	if(y1%4 == 0 && (y1%100 != 0 || y1%400 ==0))
		numOfDays[2] = 29;
	
	if (1<=m1 && m1 <=12) {
		_b_m = true;
		
		if (1<=d1 && d1 <= numOfDays[m1])
			_b_d = true;
		
	}
	
	
	if (y1 != 0000 && y1 == (int)y1)
		_b_y = true;
	
	
	if ( _b_m == true && _b_d == true && _b_y == true)
		return true;
	else
		return false;
  }
  
  //daysToEndOfYear  
  public static int daysToEndOfYear(int m1, int d1, int y1){
		int numOfDays[] = {999,31,28,31,30,31,30,31,31,30,31,30,31};  
		int remainDays = 0;
		int totalDays=365;
		
		if(y1%4 == 0 && (y1%100 != 0 || y1%400 ==0))
		{ // this if condition is for leaf month , Feb 29 exists
			totalDays=366;
			numOfDays[2]=29;
			for (int i = 1; i<= m1 ; i++)
			{
				if (i == m1) // first day 
				{
					remainDays += d1; 
				}
				else{
					remainDays += numOfDays[i];
				}
				
			}
		}
		else // non - leaf month 
		{
			for (int i = 1; i<= m1 ; i++)
			{
				if (i == m1) // first day 
				{
					remainDays += d1; 
				}
				else{
					remainDays += numOfDays[i];
				}
				
			}
		}
		
		// from the Month input to 12, DEC  
		return totalDays-remainDays;
	}
  
  //daysInYear 
  public static int daysInYear(int y){
		int numOfDays = 365;
		if(y%4 == 0 && (y%100 != 0 || y%400 ==0))
			numOfDays = 366;
		
		return numOfDays;
  }
  
  //isBefore 
  public static boolean isBefore(int m1, int d1, int y1, int m2, int d2, int y2){
	  if (y2 - y1 > 0)
	  	return true;
	  
	  else if (y2 - y1 == 0)
	  {
		  if (m2 - m1 > 0)
			  return true;
		  
		  else if (m2 - m1 == 0)
		  {
			  if (d2 - d1 > 0)
				  return true;
		  }
	  }
      return false;
  }
  
  public static void main(String[] args){
      //System.out.println(daysApart(1,1,2014,1,1,2014));
      //System.out.println(daysApart(1,1,2014,1,2,2014));
      //System.out.println(daysApart(1,1,2013,1,1,2014));
      //System.out.println(daysApart(1,1,2012,1,1,2013));
  }

}
