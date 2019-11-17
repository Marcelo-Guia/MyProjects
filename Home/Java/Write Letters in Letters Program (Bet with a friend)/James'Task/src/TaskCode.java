import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;

public class TaskCode {
	
	public static void main(String[] args) 
	{
		try
		{
			String UserInput = Input();
			String [][] FileContent = GetFileContent();
			char[] AlphabetArray = GetAlphabet();
			char[] CharArray = UserInput.toCharArray();
			WriteOutInput(CharArray, AlphabetArray, FileContent);
		}
		            
		catch (Exception EX) 
		{
			System.err.println("An error occurred.");
			EX.printStackTrace();
		}
	}

	public static String Input() 
	{
		Scanner Scanner = new Scanner(System.in);
		System.out.println("Write a sentence (Numbers must be written and no punctuation or special characters are allowed, just letters):");
		String Input = Scanner.nextLine().toLowerCase();
		Scanner.close();
		return Input;
	}
	
	public static char[] GetAlphabet()
	{
		char[] Alphabet = new char[27];
		int Iteration = 0;
		for (char Letters = 'a'; Letters <= 'z'; Letters++)
		{
			Alphabet[Iteration] = Letters;
			Iteration++;
		}
		return Alphabet;
	}
	
	public static String[][] GetFileContent() throws FileNotFoundException
	{
		String[][] FileContent = new String[27][5];
		File TaskFile = new File("src\\JamesTaskFile.txt");
		Scanner FileReader = new Scanner(TaskFile);
		for (int I1 = 0; I1 <= 26; I1++)
		{
			for (int I2 = 0; I2 <= 4; I2++)
			{
				FileContent[I1][I2] = FileReader.nextLine();
				if (I1 == 26)
				{
					FileContent[I1][I2] = "     ";
				}
			}
		}
		FileReader.close();
		return FileContent;
	}
	
	public static void WriteOutInput(char[] CharArray, char[] AlphabetArray, String[][] FileContent)
	{
		for (int I1 = 0; I1 <= 4; I1++)
		{
			int CharArrayPos = 0;
			for (int I2 = 0; I2 <= CharArray.length - 1; I2++)
			{
				for (int I3 = 0; I3 <= 26; I3++)
				{
					if (CharArray[CharArrayPos] == AlphabetArray[I3])
					{
						System.out.print(FileContent[I3][I1]);
					}
					else if (I3 == 26)
					{
						System.out.print(FileContent[I3][I1]);
					}
				}
				CharArrayPos++;
			}
			System.out.println();
		}
	}
}