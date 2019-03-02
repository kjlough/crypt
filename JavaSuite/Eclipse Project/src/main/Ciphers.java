package main;
/*Contains methods that implement the Ciphers*/

public class Ciphers	
{	
	public static String Shift(String text, String key, int tag, boolean checked)		//*****SHIFT CIPHER CODE*****
	{
		String info = ": ";																//Variable declarations
		int[] numConvert = new int[text.length()];										
		numConvert = Methods.toNum(text);
		
		key = key.replaceAll("[^0-9]", "");												//Handles key inputs
		if(key.isEmpty())																
		{
			key = "26";
		}	
		int shift = Integer.parseInt(key);
		
		if(checked && tag == 0)															//Forcefully breaks encryption
		{
			int[] scores = new int[26];
			char[] letters = new char[numConvert.length];
			int[] temp = new int[numConvert.length];
			
			for(shift = 0; shift < 26; shift++)											//Provides possible key values
			{
				for(int i = 0; i < numConvert.length; i++)								//Performs cipher arithmetic
				{
					if(numConvert[i] < shift)
						temp[i] = (numConvert[i]+(26-shift)) % 26;
					else
						temp[i] = (numConvert[i]-shift) % 26;
				}
				
				letters = (Methods.toString(temp)).toCharArray();						//Scores the resulting strings from given keys
				scores[shift] = Methods.isEnglish(letters);
			}
			
			int min = scores[0];
			
			for(int i = 0; i < 26; i++)													//Determines which of the possible keys has the best score (most likely to be the true key)
			{
				info += i + "		" + scores[i] + "\n"; 
				if(scores[i] < min)
				{
					min = scores[i];
					shift = i;
				}
			}
		}
		
		if(0 <= shift && shift <= 25)													//If there is a valid key perform cipher arithmetic
		{		
			if(tag == 0)																//SHIFT DECRYPT
			{
				for(int i = 0; i < numConvert.length; i++)
				{
					if(numConvert[i] < shift)
						numConvert[i] = (numConvert[i]+(26-shift)) % 26;
					else
						numConvert[i] = (numConvert[i]-shift) % 26;
				}
			}
			
			if(tag == 1)																//SHIFT ENCRYPT
			{
				for(int i = 0; i < numConvert.length; i++)
				{
					numConvert[i] = (numConvert[i]+shift) % 26;
				}
			}
		}
		
		else 																			//Print if the given key was invalid 
		{
			text = "X:Invalid Key, Please enter a X between 0 and 25" + info;
			return text;
		}
		
		text = Methods.toString(numConvert);
		text = shift + ":" + text + info;
		
		return text;																	//Returns the correct key and relevant text
	}    

	public static String Affine(String text, String key, int tag, boolean checked)		//*****AFFINE CIPHER CODE*****
	{
		String info = ": ";																//Variable declarations
		int x, y;																		
		int[] numConvert = new int[text.length()];
		numConvert = Methods.toNum(text);
		String[] xy = key.split(",");
		
		for(int i = 0; i < xy.length; i++)												//Extracts individual key values x & y
			xy[i] = xy[i].replaceAll("[^0-9]", "");
		
		if(!xy[0].isEmpty() && !xy[1].isEmpty())										//Converts key from char to int
		{
			x = Integer.parseInt(xy[0]);
			y = Integer.parseInt(xy[1]);
		}
		
		else																			//Invalid key input case
		{
			x = 2;
			y = 26;
		}
		
		if(checked && tag == 0)															//Forcefully breaks encryption
		{
			int k = 0, tempX;
			int[] scores = new int[312], xVals = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25}, temp = new int[numConvert.length];
			char[] letters = new char[numConvert.length];
			
			for(int tempY = 0; tempY < 26; tempY++)										//Provides possible y values
			{
				for(int j = 0; j < 12; j++)												//Provides index in xVals[] containing possible x values
				{
					tempX = xVals[j];
					
					for(int i = 0; i < numConvert.length; i++)							//Iterates through the input text
					{
						int modInv = 0;
						for(int s = 0; s < 25; s++)										//Calculates modular inverse for a given x value
						{
							if((tempX * s) % 26 == 1)
							{
								modInv = s;
								break;
							}
						}
						
						for(int s = 0; s < numConvert.length; s++)						//Performs cipher arithmetic
						{
							if(numConvert[s] < tempY)
								temp[s] = (modInv * (numConvert[s] + (26 - tempY))) % 26;
							else
								temp[s] = (modInv * (numConvert[s] - tempY)) % 26;
						}
					}
					
					letters = (Methods.toString(temp)).toCharArray();					//Converts current char array to String
					scores[k] = Methods.isEnglish(letters);								//Assigns current key a score based on the decrypted string it produces from the given ciphertext 
					k++;
				}
			}
			
			int min = scores[0];
			
			for(int i = 0; i < 312; i++)												//Determines which of the possible keys has the best score (most likely to be the true key)
			{
				info += "(" + xVals[i % 12] + ", " + (int)((i+1)/12) + ")" + "		" + scores[i] + "\n";
				if(scores[i] < min)
				{
					min = scores[i];
					y = (int)((i+1)/12);												//Returns the y value
					x = xVals[i % 12];													//Returns the x value
				}
			}
		}
		
		if(0 <= y && y <= 25 && Methods.GCD(x, 26) == 1)								//If there is a valid key perform cipher arithmetic
		{		
			if(tag == 0)																//AFFINE DECRYPT
			{
				int m = 0;
				for(int i = 0; i < 25; i++)
				{
					if((x * i) % 26 == 1)
					{
						m = i;
						break;
					}
				}
				
				for(int i = 0; i < numConvert.length; i++)
				{
					if(numConvert[i] < y)
						numConvert[i] = (m* (numConvert[i]+(26-y))) % 26;
					else
						numConvert[i] = (m * (numConvert[i] - y)) % 26;
				}
			}
			
			if(tag == 1)																//AFFINE ENCRYPT
			{
				for(int i = 0; i < numConvert.length; i++)
				{
					numConvert[i] = (x * numConvert[i] + y) % 26;
				}
			}
		}
		
		else 																			//Print if the given key was invalid
		{
			text = "X:Y:Invalid Key, Please enter an X s.t gcd(x, 26) = 1 and a Y between 0 and 25" + info;
			return text;
		}
		
		text = Methods.toString(numConvert);
		text = x + ":" + y +":" + text + info;
		
		return text;																	//Returns the correct key and relevant text
	}
	
	public static String Vigenere(String text, String key, int tag, boolean checked)	//*****VIGENERE CIPHER CODE*****
	{
		String info = ": ";																//Variable declarations
		int[] numConvert = new int[text.length()];
		numConvert = Methods.toNum(text);
		
		key = key.toUpperCase().replaceAll("[^A-Z]", "");								//Processes input key 
		if(key.length() == 0)															//Invalid key input case
		{
			text = "[A-Z|a-zShift]:Invalid Key, Please enter a string of letters" + info;
			return text;
		}
		int[] keyConvert = new int[key.length()];										
		keyConvert = Methods.toNum(key);
		int n = keyConvert.length;
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/*if(checked && tag == 0)														//Forcefully breaks encryption
		{
			for(int i = 1; i <= text.length(); i++) {
				int[] subStrings = new int[text.length()/i];
				for(int k = 0; k < j; j++) {
					for(int j = 0; j*i < text.length(); j++) {
						
					
					}
				}
			}
		}*/
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(tag == 0)																	//VIGENERE DECRYPT
		{
			for(int i = 0; i < numConvert.length; i++)
			{
				if(numConvert[i] < keyConvert[i % n])
					numConvert[i] = (numConvert[i]+(26-keyConvert[i % n])) % 26;
				else
					numConvert[i] = (numConvert[i]-keyConvert[i % n]) % 26;
			}
				
		}
			
		if(tag == 1)																	//VIGENERE ENCRYPT
		{
			for(int i = 0; i < numConvert.length; i++)
			{
				numConvert[i] = (numConvert[i] + keyConvert[i % n]) % 26;
			}
		}
	
		text = Methods.toString(numConvert);
		text = key + ":" + text + info;
		
		return text;
	}
	
	public static String Playfair(String text, String key, int tag, boolean checked)	//*****PLAYFAIR CIPHER CODE*****
	{
		String info = ": ";																//Variable declarations
		int[] numConvert = new int[text.length()];
		numConvert = Methods.toNum(text);
		
		key = key.toUpperCase().replaceAll("[^A-Z]", "");								//Processes input key 
		if(key.length() == 0)															//Invalid key input case
		{
			text = "[A-Z|a-z]:Invalid Key, Please enter a string of letters" + info;
			return text;
		}
		int[] keyConvert = new int[key.length()];										
		keyConvert = Methods.toNum(key);
		
		boolean equiv = false;
		int[] n = new int[26];															//Builds key in usable form
		for(int i = 0; i < keyConvert.length; i++)
			 n[i] = keyConvert[i];
		for(int k = keyConvert.length; k < 26-keyConvert.length; k++)
			for(int i = 0; i < 26; i++)
			{
				for(int j = 0; j < k+1; j++)
				{
					 if(i == n[j])
						 equiv = true;
				}
				if(!equiv)
					n[k] = i;
			}
		
		for(int i = 0; i < 26; i++)
			System.out.print(n[i]+", ");
			
		/*if(checked && tag == 0)														//Forcefully breaks encryption
		{
			
		}*/
		
		if(tag == 0)																	//PLAYFAIR DECRYPT
		{
			
		}
			
		if(tag == 1)																	//PLAYFAIR ENCRYPT
		{
			
		}
	
		text = Methods.toString(numConvert);
		text = key + ":" + text + info;
		
		return text;
	}
	
	public static String Hill(String text, String key, int tag, boolean checked)		//*****HILL CIPHER CODE*****
	{
		String info = ": ";																//Variable declarations
		int[] numConvert = new int[text.length()];
		numConvert = Methods.toNum(text);
		
		key = key.toUpperCase().replaceAll("[^A-Z]", "");								//Processes input key 
		if(key.length() == 0)															//Invalid key input case
		{
			text = "[A-Z|a-z]:Invalid Key, Please enter a string of letters" + info;
			return text;
		}
		int[] keyConvert = new int[key.length()];										
		keyConvert = Methods.toNum(key);
		int n = keyConvert.length;
		
		/*if(checked && tag == 0)														//Forcefully breaks encryption
		{
			1) analyze coincidences and generate a list of probable key lengths
			    i) generate and score possible keys of the given lengths
			   ii) order massage into columns, one for each letter of the possible key, and perform frequency analysis on them individually  
			   
			2) info += (keyString|[A-Z]*)	(resulting plaintext)
		}*/
		
		if(tag == 0)																	//VIGENERE DECRYPT
		{
			for(int i = 0; i < numConvert.length; i++)
			{
				if(numConvert[i] < keyConvert[i % n])
					numConvert[i] = (numConvert[i]+(26-keyConvert[i % n])) % 26;
				else
					numConvert[i] = (numConvert[i]-keyConvert[i % n]) % 26;
			}
				
		}
			
		if(tag == 1)																	//VIGENERE ENCRYPT
		{
			for(int i = 0; i < numConvert.length; i++)
			{
				numConvert[i] = (numConvert[i] + keyConvert[i % n]) % 26;
			}
		}
	
		text = Methods.toString(numConvert);
		text = key + ":" + text + info;
		
		return text;
	}
} 