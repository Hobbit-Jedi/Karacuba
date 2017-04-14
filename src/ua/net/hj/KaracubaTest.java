package ua.net.hj;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для тестирования Карацуба-чисел.
 * @author Hobbit Jedi
 */
public class KaracubaTest {
	public static HashMap<String, Integer> mSCounters = new HashMap<>();
	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		//testAdd();
		//testSubstract();
		//testMultiplicateKaracuba();
		//testMultiplicateShumers();
		contestKaracuba_vs_Shumers();
	}
	
	/**
	 * Выполнить тестирование операции сложения.
	 */
	public static void testAdd()
	{
		String[][] params;
		
		System.out.println("----------------------------");
		System.out.println("| Add testing");
		System.out.println("----------------------------");
		params = new String[][]{
									{"", "", "0"},
									{"12d3234", "345", "Error"},
									{"2", "3", "5"},
									{"9", "8", "17"},
									{"000", "000", "0"},
									{"100", "2", "102"},
									{"22", "1000", "1022"},
									{"49823261", "44269423", "94092684"},
									{"54761293", "65394884", "120156177"},
									{"9313685456934674", "7658898761837539", "16972584218772213"}
								};
		for (String[] param : params)
		{
			System.out.println("----------------------------");
			System.out.println("X = " + param[0]);
			System.out.println("Y = " + param[1]);
			try
			{
				NumberKaracuba X = new NumberKaracuba(new StringBuilder(param[0]));
				NumberKaracuba Y = new NumberKaracuba(new StringBuilder(param[1]));
				NumberKaracuba XY = X.add(Y);
				System.out.println("X+Y = " + XY);
				if (XY.toString().equals(param[2]))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
			}
			catch (Exception ex)
			{
				System.out.println("Выброшено исключение: " + ex.getMessage());
				if (param[2].equals("Error"))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
			}
		}
	}
	
	/**
	 * Выполнить тестирование операции вычитания.
	 */
	public static void testSubstract()
	{
		String[][] params;
		
		System.out.println("----------------------------");
		System.out.println("| Substract testing");
		System.out.println("----------------------------");
		params = new String[][]{
									{"", "", "0"},
									{"12d3234", "345", "Error"},
									{"2", "3", "Error"},
									{"3", "2", "1"},
									{"9", "8", "1"},
									{"000", "000", "0"},
									{"100", "2", "98"},
									{"22", "1000", "Error"},
									{"1000", "22", "978"},
									{"49823261", "44269423", "5553838"},
									{"54761293", "65394884", "Error"},
									{"65394884", "54761293", "10633591"},
									{"9313685456934674", "7658898761837539", "1654786695097135"}
								};
		for (String[] param : params)
		{
			System.out.println("----------------------------");
			System.out.println("X = " + param[0]);
			System.out.println("Y = " + param[1]);
			try
			{
				NumberKaracuba X = new NumberKaracuba(new StringBuilder(param[0]));
				NumberKaracuba Y = new NumberKaracuba(new StringBuilder(param[1]));
				NumberKaracuba XY = X.substract(Y);
				System.out.println("X-Y = " + XY);
				if (XY.toString().equals(param[2]))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
			}
			catch (Exception ex)
			{
				System.out.println("Выброшено исключение: " + ex.getMessage());
				if (param[2].equals("Error"))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
			}
		}
	}
	
	/**
	 * Выполнить тестирование операции умножения (методом Карацубы).
	 */
	public static void testMultiplicateKaracuba()
	{
		String[][] params;
		
		System.out.println("----------------------------------");
		System.out.println("| Multiplicate testing (Karacuba))");
		System.out.println("----------------------------------");
		params = new String[][]{
								{"", "", "0"},
								{"12d3234", "345", "Error"},
								{"2", "3", "6"},
								{"9", "8", "72"},
								{"000", "000", "0"},
								{"100", "2", "200"},
								{"12345", "2", "24690"},
								{"131", "70", "9170"},
								{"1234", "5678", "7006652"},
								{"49823261", "44269423", "2205647016448403", "8", "4", "7", "2"},
								{"54761293", "65394884", "3581108403425012", "5", "2"},
								{"9313685456934674", "7658898761837539", "71332574014261268360454523927286", "18", "1", "9", "3", "8", "7"},
								{"3957322621234423", "7748313756335578", "30662577304368647842211393201494", "14", "1", "9", "2", "8", "8"},
								{"34215432964249374812219364786397", "94541964835273822784327848699719", "3234794260129733170788831535430575611379062580407060392628922443", "45", "3", "9", "19", "8", "11"},
								{"71611955325935479159397713213124", "93567726499788166681348352945366", "6700567850052179472481148730882040129649508491917840721086183384", "40", "4", "36", "2", "10", "4"},
								{"8436939677358274975644341226884162349535836199962392872868456892", "3864264464372346883776335161325428226997417338413342945574123327", "32602566183268675582196165592691544162522778809155901895617284287276672563976841699892789718741377833554298336397153444191119684", "72", "4", "69", "3", "67", "1"},
								{"8711129198194917883527844183686122989894424943636426448417394566", "2924825637132661199799711722273977411715641477832758942277358764", "25478534007255378799894857247961445544397925869179138904636157575535921570058983065006369481295619500406669960288667484926076424", "64", "5", "60", "3", "58", "5"},
								{"21625695688898558125310188636840316594920403182768", "13306827740879180856696800391510469038934180115260", "287769407308846640970310151509826255482575362419155842891311909556878670000425352112987881085839680"},
								{"1685287499328328297814655639278583667919355849391453456921116729", "7114192848577754587969744626558571536728983167954552999895348492", "11989460275519080564894036768322865785999566885539505969749975204962718118914971586072960191064507745920086993438529097266122668", "105", "3", "72", "7", "12", "5"}
							};
		for (String[] param : params)
		{
			System.out.println("----------------------------");
			System.out.println("X = " + param[0]);
			System.out.println("Y = " + param[1]);
			try
			{
				NumberKaracuba X = new NumberKaracuba(new StringBuilder(param[0]));
				NumberKaracuba Y = new NumberKaracuba(new StringBuilder(param[1]));
				mSCounters.clear();
				NumberKaracuba XY = X.multiplicate(Y);
				System.out.println("X*Y = " + XY);
				if (XY.toString().equals(param[2]))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
			}
			catch (Exception ex)
			{
				System.out.println("Выброшено исключение: " + ex.getMessage());
				if (param[2].equals("Error"))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
			}
			System.out.print("{");
			boolean isFirstEntry = true;
			for (Map.Entry<String, Integer> entry: mSCounters.entrySet())
			{
				System.out.print((isFirstEntry?"":", ") + entry);
				isFirstEntry = false;
			}
			System.out.println("}");
			int i = 3;
			while (i+1 < param.length)
			{
				String sWanted = param[i];
				int sCounter;
				if (KaracubaTest.mSCounters.containsKey(sWanted))
				{
					sCounter = mSCounters.get(sWanted);
				}
				else
				{
					sCounter = 0;
				}
				System.out.println("S = " + sWanted + " встречается " + sCounter + " раз.");
				if (sCounter == Integer.valueOf(param[i+1]))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
				i += 2;
			}
		}
	}
	
	/**
	 * Выполнить тестирование операции умножения (методом Шумеров).
	 */
	public static void testMultiplicateShumers()
	{
		String[][] params;
		
		System.out.println("--------------------------------");
		System.out.println("| Multiplicate testing (Shumers)");
		System.out.println("--------------------------------");
		params = new String[][]{
								{"", "", "0"},
								{"12d3234", "345", "Error"},
								{"2", "3", "6"},
								{"9", "8", "72"},
								{"000", "000", "0"},
								{"100", "2", "200"},
								{"12345", "2", "24690"},
								{"131", "70", "9170"},
								{"1234", "5678", "7006652"},
								{"49823261", "44269423", "2205647016448403"},
								{"54761293", "65394884", "3581108403425012"},
								{"9313685456934674", "7658898761837539", "71332574014261268360454523927286"},
								{"3957322621234423", "7748313756335578", "30662577304368647842211393201494"},
								{"34215432964249374812219364786397", "94541964835273822784327848699719", "3234794260129733170788831535430575611379062580407060392628922443"},
								{"71611955325935479159397713213124", "93567726499788166681348352945366", "6700567850052179472481148730882040129649508491917840721086183384"},
								{"8436939677358274975644341226884162349535836199962392872868456892", "3864264464372346883776335161325428226997417338413342945574123327", "32602566183268675582196165592691544162522778809155901895617284287276672563976841699892789718741377833554298336397153444191119684"},
								{"8711129198194917883527844183686122989894424943636426448417394566", "2924825637132661199799711722273977411715641477832758942277358764", "25478534007255378799894857247961445544397925869179138904636157575535921570058983065006369481295619500406669960288667484926076424"},
								{"21625695688898558125310188636840316594920403182768", "13306827740879180856696800391510469038934180115260", "287769407308846640970310151509826255482575362419155842891311909556878670000425352112987881085839680"},
								{"1685287499328328297814655639278583667919355849391453456921116729", "7114192848577754587969744626558571536728983167954552999895348492", "11989460275519080564894036768322865785999566885539505969749975204962718118914971586072960191064507745920086993438529097266122668"}
							};
		for (String[] param : params)
		{
			System.out.println("----------------------------");
			System.out.println("X = " + param[0]);
			System.out.println("Y = " + param[1]);
			try
			{
				NumberKaracuba X = new NumberKaracuba(new StringBuilder(param[0]));
				NumberKaracuba Y = new NumberKaracuba(new StringBuilder(param[1]));
				NumberKaracuba XY = X.multiplicateShumer(Y);
				System.out.println("X*Y = " + XY);
				if (XY.toString().equals(param[2]))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
			}
			catch (Exception ex)
			{
				System.out.println("Выброшено исключение: " + ex.getMessage());
				if (param[2].equals("Error"))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("!!! FAIL !!!");
				}
			}
		}
	}
	
	/**
	 * Сравнить производительность умножения методом Шумеров и методом Карацубы.
	 */
	public static void contestKaracuba_vs_Shumers()
	{
		NumberKaracuba numX, numY, resultKaracuba, resultShumers;
		long timeStart, timeFinish, timeDeltaKaracuba, timeDeltaShumers;
		
		int numLen = 1000;
		
		System.out.println("-----------------------");
		System.out.println("| Karacuba vs. Shumers ");
		System.out.println("-----------------------");
		// Сгенерируем два охренительно больших числа для перемножения.
		StringBuilder numXBuilder = new StringBuilder(numLen);
		StringBuilder numYBuilder = new StringBuilder(numLen);
		for (int i = 1; i <= numLen; i++)
		{
			numXBuilder.append((byte)(11*Math.random()));
			numYBuilder.append((byte)(11*Math.random()));
		}
		System.out.println("X = " + numXBuilder);
		System.out.println("Y = " + numYBuilder);
		try
		{
			numX = new NumberKaracuba(numXBuilder);
			numY = new NumberKaracuba(numYBuilder);
			
			// Карацуба
			timeStart = System.nanoTime();
			resultKaracuba = numX.multiplicate(numY);
			timeFinish = System.nanoTime();
			timeDeltaKaracuba = timeFinish - timeStart;
			System.out.println("Karacuba X*Y = " + resultKaracuba);
			System.out.println("" + timeDeltaKaracuba + " наносекунд.");
			
			// Шумеры
			timeStart = System.nanoTime();
			resultShumers = numX.multiplicateShumer(numY);
			timeFinish = System.nanoTime();
			timeDeltaShumers = timeFinish - timeStart;
			System.out.println("Shumers X*Y = " + resultShumers);
			System.out.println("" + timeDeltaShumers + " наносекунд.");
			
			if (resultKaracuba.toString().equals(resultShumers.toString()))
			{
				System.out.println("OK");
			}
			else
			{
				System.out.println("!!! FAIL !!!");
			}
			System.out.println("Отношение Карацуба/Шумеры = " + (Double.valueOf(timeDeltaKaracuba)/Double.valueOf(timeDeltaShumers)) + " раз.");
			
		}
		catch (Exception e)
		{
			System.out.println("Вылетело исключение: " + e);
		}
		
	}
}
