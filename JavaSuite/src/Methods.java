/*Contains auxilary methods*/

//import com.wolfram.jlink.*;
import java.lang.String;

public class Methods 
{	
	public static int GCD(int x, int y) 								//Euclydian algorithm
	{
		if(y == 0){
            return x;
        }
        return GCD(y, x % y);
    }
	
	public static int[] toNum(String text)								//Convert string to integer array 
	{
		text = text.toUpperCase().replaceAll("[^A-Z]", "");				//Removes whitespace and casts to uppercase
		
		char[] charConvert = text.toCharArray();
		int[] numConvert = new int[text.length()];
		
		for(int i = 0; i < text.length(); i++)							//Shifts standard ASCII values to the 0-25 range 
		{
			numConvert[i] = ((int) charConvert[i]) - 65;
		}
		
		return numConvert;
	}
	
	public static String toString(int[] numConvert)						//Convert integer array to string 
	{
		char[] charConvert = new char[numConvert.length];
		
		for(int i = 0; i < numConvert.length; i++)						//Shifts 0-25 range to the standard ASCII values and casts to char
		{
			charConvert[i] = (char) (numConvert[i] + 65);
		}
		
		String text = new String(charConvert);
		
		return text;
	}
	
	public static int freqAnalysis(char[] charConvert)					//Assigns a score to strings based on how similar they are to English
	{
		double score = 0;												//Variable declarations
		double[] count = new double[26];
		double[] frequency = {.08167, .01492, .02782, .04253, .12702, .02228, .02015, .06094, .06966, .00153, .00772, .04025, .02406, .06749, .07507, .01929, .00095, .05987, .06327, .09056, .02758, .00978, .02360, .00150, .01974, .00074};
		
		for(int i = 0; i < charConvert.length; i++)						//Counts number of times each letter occurs in the input text
		{
			count[(int)charConvert[i]-65] += 1; 
		}
		
		for(int i = 0; i < 26; i++)										//Calculates letter frequency
		{
			count[i] /= charConvert.length;
			score += Math.abs(count[i] - frequency[i]);
		}
		
		score *= charConvert.length;									//Assigns the input text a base score
		
		return (int)score;
	}
	
	public static int isEnglish(char[] charConvert)						//Assigns a score to strings based on how similar they are to English
	{
		double score = 0;												//Variable declarations
		double[] count = new double[26];
		double[] frequency = {.08167, .01492, .02782, .04253, .12702, .02228, .02015, .06094, .06966, .00153, .00772, .04025, .02406, .06749, .07507, .01929, .00095, .05987, .06327, .09056, .02758, .00978, .02360, .00150, .01974, .00074};
		
		for(int i = 0; i < charConvert.length; i++)						//Counts number of times each letter occurs in the input text
		{
			count[(int)charConvert[i]-65] += 1; 
		}
		
		for(int i = 0; i < 26; i++)										//Calculates letter frequency
		{
			count[i] /= charConvert.length;
			score += Math.abs(count[i] - frequency[i]);
		}
		
		score *= charConvert.length;									//Assigns the input text a base score
		
		if(charConvert.length < 200)									//If the message is short attempt further analysis
		{
			for(int i = 0; i < charConvert.length-1; i++)
			{
				if(charConvert[i] == 'A')
				{
					if(charConvert[i+1] == 'N')
					{
						score = score - 5;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'D')
							{
								score = score - 5;
								
							}
						}
					}
				}
	
				else if(charConvert[i] == 'B')
				{
					if(charConvert[i+1] == 'X') 
						score = score + 3;
				}
	
				else if(charConvert[i] == 'J')
				{
					if(charConvert[i+1] == 'C' || charConvert[i+1] == 'F' || charConvert[i+1] == 'G' || charConvert[i+1] == 'Q' || charConvert[i+1] == 'S' || charConvert[i+1] == 'V' || charConvert[i+1] == 'W' || charConvert[i+1] == 'X' || charConvert[i+1] == 'Z') 
						score = score + 5;
				}
				
				else if(charConvert[i] == 'Q')
				{
					if(charConvert[i+1] != 'U') 
						score = score + 50;
					else
						score = score - 5;
				}
				
				else if(charConvert[i] == 'V')
				{
					if(charConvert[i+1] == 'B' || charConvert[i+1] == 'F' || charConvert[i+1] == 'H' || charConvert[i+1] == 'J' || charConvert[i+1] == 'M' || charConvert[i+1] == 'P' || charConvert[i+1] == 'Q' || charConvert[i+1] == 'T' || charConvert[i+1] == 'W' || charConvert[i+1] == 'X') 
						score = score + 5;
				}
				
				else if(charConvert[i] == 'H')
				{
					if(charConvert[i+1] == 'A')
					{
						score = score - 1;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'S' || charConvert[i+2] == 'V')
							{
								score = score - 5;
								
							}
						}
					}
					if(charConvert[i+1] == 'X') 
						score = score + 3;
					if(charConvert[i+1] == 'E')
						score = score - 4;
				}
				
				else if(charConvert[i] == 'E')
				{
					if(charConvert[i+1] == 'N')
					{
						score = score - 4;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'T')
							{
								score = score - 5;
								
							}
						}
					}
					if(charConvert[i+1] == 'E' || charConvert[i+1] == 'R' || charConvert[i+1] == 'D')
						score = score - 4;
				}
				
				else if(charConvert[i] == 'I')
				{
					if(charConvert[i+1] == 'O')
					{
						score = score - 3;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'N')
							{
								score = score - 5;
								
							}
						}
					}
					if(charConvert[i+1] == 'N')
					{
						score = score - 4;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'G')
							{
								score = score - 10;
								
							}
						}
					}
					if(charConvert[i+1] == 'E')
						score = score - 3;
				}
				
				else if(charConvert[i] == 'F')
				{
					if(charConvert[i+1] == 'O')
					{
						score = score - 3;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'R')
							{
								score = score - 5;
								
							}
						}
					}
					if(charConvert[i+1] == 'Q' || charConvert[i+1] == 'X') 
						score = score + 3;
					if(charConvert[i+1] == 'F')
						score = score - 2;
				}
	
				else if(charConvert[i] == 'C')
				{
					if(charConvert[i+1] == 'E')
					{
						score = score - 3;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'I')
							{
								score = score - 5;
								
							}
						}
					}
					if(charConvert[i+1] == 'J' || charConvert[i+1] == 'V' || charConvert[i+1] == 'X') 
						score = score + 3;
				}
	
				else if(charConvert[i] == 'T')
				{
					if(charConvert[i+1] == 'H')
					{
						score = score - 8;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'E' || charConvert[i+2] == 'A')
							{
								score = score - 10;
								
							}
						}
					}
					if(charConvert[i+1] == 'I')
					{
						score = score - 3;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'O')
							{
								score = score - 5;
								if(i < charConvert.length-3)
								{
									if(charConvert[i+3] == 'N')
									{
										score = score - 10;
										
									}
								}
							}
						}
					}
					if(charConvert[i+1] == 'T')
						score = score - 3;
				}
				
				else if(charConvert[i] == 'Z')
				{
					if(charConvert[i+1] == 'J' || charConvert[i+1] == 'Q' || charConvert[i+1] == 'X') 
						score = score + 3;
				}
	
				else if(charConvert[i] == 'S')
				{
					if(charConvert[i+1] == 'X') 
						score = score + 3;
					if(charConvert[i+1] == 'S')
						score = score - 1;
				}
				
				else if(charConvert[i] == 'O')
				{
					if(charConvert[i+1] == 'N' || charConvert[i+1] == 'O')
						score = score - 3;
				}
	
				else if(charConvert[i] == 'M')
				{
					if(charConvert[i+1] == 'X') 
						score = score + 3;
					if(charConvert[i+1] == 'M')
						score = score - 1;
				}
	
				else if(charConvert[i] == 'G')
				{
					if(charConvert[i+1] == 'X' || charConvert[i+1] == 'Q') 
						score = score + 3;
				}
	
				else if(charConvert[i] == 'K')
				{
					if(charConvert[i+1] == 'X' || charConvert[i+1] == 'Q') 
						score = score + 3;
				}
				
				else if(charConvert[i] == 'P')
				{
					if(charConvert[i+1] == 'X' || charConvert[i+1] == 'Z') 
						score = score + 3;
				}
	
				else if(charConvert[i] == 'X')
				{
					if(charConvert[i+1] == 'J' || charConvert[i+1] == 'X') 
						score = score + 3;
				}
	
				else if(charConvert[i] == 'Y')
				{
					if(charConvert[i+1] == 'O')
					{
						score = score - 1;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'U')
							{
								score = score - 5;
								
							}
						}
					}
				}
				
				else if(charConvert[i] == 'N')
				{
					if(charConvert[i+1] == 'D')
					{
						score = score - 3;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'E')
							{
								score = score - 5;
								
							}
						}
					}
					if(charConvert[i+1] == 'C')
					{
						score = score - 1;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'E')
							{
								score = score - 5;
								
							}
						}
					}
					if(charConvert[i+1] == 'O')
					{
						score = score - 2;
						
						if(i < charConvert.length-2)
						{
							if(charConvert[i+2] == 'T')
							{
								score = score - 5;
								
							}
						}
					}
				}
	
				else if(charConvert[i] == 'D')
				{
					if(charConvert[i+1] == 'X') 
						score = score + 3;
				}
	
				else if(charConvert[i] == 'R')
				{
					if(charConvert[i+1] == 'E')
						score = score - 2;
				}
				
				else if(charConvert[i] == 'L')
				{
					if(charConvert[i+1] == 'L')
						score = score - 2;
				}
				
				else if(charConvert[i] == 'W')
				{
					if(charConvert[i+1] == 'X') 
						score = score + 3;
				}
			}
		}
		return (int)score;
	}
}