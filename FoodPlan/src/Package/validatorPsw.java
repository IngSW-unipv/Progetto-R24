package Package;

public class validatorPsw {
	
	boolean check(String input)
	{
		final int NUM_UPPER_LETTERS = 2;
		final int NUM_LOWER_LETTERS = 3;
		final int NUM_DIGITS = 1;
		int upperCount = 0;
		int lowerCount = 0;
		int digitCount = 0;
		
		int inputLen = input.length();
		
		//Character:
		for(int i = 0; i<inputLen; i++)
		{
			char cr = input.charAt(i);
			
			if(Character.isUpperCase(cr))
				upperCount++;
			else if(Character.isLowerCase(cr))
				lowerCount++;
			else if(Character.isDigit(cr))
				digitCount++;
		}
		
		if(upperCount >= NUM_UPPER_LETTERS &&
				lowerCount >= NUM_LOWER_LETTERS &&
				digitCount >= NUM_DIGITS)
		{
			System.out.println("Valid Password");
			return true;
		}
		else 
		{
			System.out.println("The Password did not have enough of the following");
			if(upperCount < NUM_UPPER_LETTERS)
				System.out.println("uppercase letters");
			else if(lowerCount < NUM_LOWER_LETTERS)
				System.out.println("lowercase letters");
			else if(digitCount < NUM_DIGITS)
				System.out.println("digits");
			
			return false;
		}
	}
}

