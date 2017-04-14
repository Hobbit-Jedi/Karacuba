package ua.net.hj;

/**
 * Представляет собой целое число, которое умеет умножаться
 * на другое число так, как учил Великий Карацуба.
 * @author Hobbit Jedi
 */
public class NumberKaracuba {
	private final StringBuilder mNumber; // Строковое представление хранимого в объекте числа.
	
	/**
	 * Сохранить большое число в объекте Карацубы.
	 * @param aNumber - Строковое представление сохраняемого в объекте числа.
	 * @throws java.lang.Exception - Если переданная строка не может быть преобразована в число,
	 *                               то вызывает исключение.
	 */
	public NumberKaracuba(StringBuilder aNumber) throws Exception
	{
		int aNumberLen = aNumber.length();
		mNumber = new StringBuilder(aNumberLen);
		boolean isValuable = false;
		for (int i = 0; i < aNumberLen; i++)
		{
			char currentChar = aNumber.charAt(i);
			if (Character.isDigit(currentChar))
			{
				if (isValuable | currentChar != '0')
				{
					isValuable = true;
					mNumber.append(currentChar);
				}
			}
			else
			{
				throw new Exception("Неудачная попытка преобразовать строку в число.");
			}
		}
		if (mNumber.length() == 0)
		{
			mNumber.append('0');
		}
	}
	
	/**
	 * Получить длину хранимого числа.
	 * @return - Длина хранимого числа (в десятичных разрядах).
	 */
	public int length()
	{
		return mNumber.length();
	}
	
	/**
	 * Получить строковое представление хранимого числа.
	 * @return - Строковое представление хранимого числа.
	 */
	@Override
	public String toString()
	{
		return mNumber.toString();
	}
	
	/**
	 * Прибавить к существующему числу другое Карацуба-число.
	 * @param other - Карацуба-число, которое нужно прибавить к текущему числу.
	 * @return - Новое Карацуба-число, представляющее собой результат сложения двух Карацуба-чисел.
	 * @throws java.lang.Exception - В случае ошибок выбрасывает исключение.
	 */
	public NumberKaracuba add(NumberKaracuba other) throws Exception
	{
		int lenX = this.length();
		int lenY = other.length();
		int maxLen = Math.max(lenX, lenY);
		StringBuilder resultBuilder = new StringBuilder(maxLen+1);
		byte carry = 0;
		for (int i = 0; i < maxLen; i++)
		{
			byte x = (i < lenX) ? (byte)(this.mNumber.charAt(lenX-1-i) - '0')  : 0;
			byte y = (i < lenY) ? (byte)(other.mNumber.charAt(lenY-1-i) - '0') : 0;
			byte add = (byte)(x + y + carry);
			resultBuilder.insert(0, (char)('0' + (byte)(add % 10)));
			carry = (byte)(add / 10);
		}
		if (carry != 0)
		{
			resultBuilder.insert(0, (char)('0' + carry));
		}
		try
		{
			return new NumberKaracuba(resultBuilder);
		}
		catch (Exception ex)
		{
			throw new Exception("Ошибка при сложении Карацуба-чисел: Результирующая строка получилась нечисловой.");
		}
	}
	
	/**
	 * Отнять от существующего числа другое Карацуба-число.
	 * @param other - Карацуба-число, которое нужно отнять от текущего числа.
	 * @return - Новое Карацуба-число, представляющее собой результат вычитания указанного числа от текущего.
	 * @throws java.lang.Exception - В случае ошибок выбрасывает исключение.
	 */
	public NumberKaracuba substract(NumberKaracuba other) throws Exception
	{
		int lenX = this.length();
		int lenY = other.length();
		if (lenX >= lenY)
		{
			StringBuilder resultBuilder = new StringBuilder(lenX);
			byte carry = 0;
			for (int i = 0; i < lenX; i++)
			{
				byte x = (byte)(this.mNumber.charAt(lenX-1-i) - '0');
				byte y = (i < lenY) ? (byte)(other.mNumber.charAt(lenY-1-i) - '0') : 0;
				byte sub = (byte)(x - y - carry);
				if (sub < 0)
				{
					sub += 10;
					carry = 1;
				}
				else
				{
					carry = 0;
				}
				resultBuilder.insert(0, (char)('0' + sub));
			}
			if (carry == 0)
			{
				try
				{
					return new NumberKaracuba(resultBuilder);
				}
				catch (Exception ex)
				{
					throw new Exception("Ошибка при вычитании Карацуба-чисел: Результирующая строка получилась нечисловой.");
				}
			}
			else
			{
				throw new Exception("Попытка вычесть из меньшего числа большее.");
			}
		}
		else
		{
			throw new Exception("Попытка вычесть из меньшего числа большее.");
		}
	}
	
	/**
	 * Умножить существующее число на другое Карацуба-число (методом Карацубы).
	 * @param other - Карацуба-число, на которое нужно умножить текущее число.
	 * @return - Новое Карацуба-число, представляющее собой результат умножения двух Карацуба-чисел.
	 * @throws java.lang.Exception - В случае ошибок выбрасывает исключение.
	 */
	public NumberKaracuba multiplicate(NumberKaracuba other) throws Exception
	{
		NumberKaracuba result;
		int lenX = this.length();
		int lenY = other.length();
		int maxLen = Math.max(lenX, lenY);
		if (maxLen == 1)
		{
			byte x = (byte)(this.mNumber.charAt(0) - '0');
			byte y = (byte)(other.mNumber.charAt(0) - '0');
			int factum = x * y;
			StringBuilder resultBuilder = new StringBuilder(2);
			resultBuilder.insert(0, (char)('0' + (byte)(factum % 10)));
			byte carry = (byte)(factum / 10);
			if (carry != 0)
			{
				resultBuilder.insert(0, (char)('0' + carry));
			}
			result = new NumberKaracuba(resultBuilder);
		}
		else
		{
			if ((maxLen & 1) == 1)
			{
				maxLen++;
			}
			int lenB = maxLen>>1;
			int thisLenA = Math.max(this.length() - lenB, 0);
			int otherLenA = Math.max(other.length() - lenB, 0);
			NumberKaracuba xA = new NumberKaracuba(new StringBuilder(this.mNumber.substring(0, thisLenA)));
			NumberKaracuba xB = new NumberKaracuba(new StringBuilder(this.mNumber.substring(thisLenA)));
			NumberKaracuba yA = new NumberKaracuba(new StringBuilder(other.mNumber.substring(0, otherLenA)));
			NumberKaracuba yB = new NumberKaracuba(new StringBuilder(other.mNumber.substring(otherLenA)));
			NumberKaracuba xAyA = xA.multiplicate(yA);
			NumberKaracuba xByB = xB.multiplicate(yB);
			NumberKaracuba xAxBAdd = xA.add(xB);
			NumberKaracuba yAyBAdd = yA.add(yB);
			NumberKaracuba xAxBAdd_yAyBAdd = xAxBAdd.multiplicate(yAyBAdd);
			NumberKaracuba S = xAxBAdd_yAyBAdd.substract(xAyA).substract(xByB);
			//(DEBUG: Счетчик для задания 2
			String numS = S.toString();
			int currentCouner;
			if (KaracubaTest.mSCounters.containsKey(numS))
			{
				currentCouner = KaracubaTest.mSCounters.get(numS);
			}
			else
			{
				currentCouner = 0;
			}
			KaracubaTest.mSCounters.put(numS, ++currentCouner);
			//)DEBUG
			
			StringBuilder highOrdersBuilder = new StringBuilder(xAyA.length() + maxLen);
			highOrdersBuilder.append(xAyA);
			for (int i = 1; i <= maxLen; i++)
			{
				highOrdersBuilder.append('0');
			}
			NumberKaracuba highOrders = new NumberKaracuba(highOrdersBuilder);
			
			StringBuilder mediumOrdersBuilder = new StringBuilder(S.length() + lenB);
			mediumOrdersBuilder.append(S);
			for (int i = 1; i <= lenB; i++)
			{
				mediumOrdersBuilder.append('0');
			}
			NumberKaracuba mediumOrders = new NumberKaracuba(mediumOrdersBuilder);
			
			result = highOrders.add(mediumOrders).add(xByB);
		}
		return result;
	}
	
	/**
	 * Умножить существующее число на другое Карацуба-число (методом Шумеров).
	 * @param other - Карацуба-число, на которое нужно умножить текущее число.
	 * @return - Новое Карацуба-число, представляющее собой результат умножения двух Карацуба-чисел.
	 * @throws java.lang.Exception - В случае ошибок выбрасывает исключение.
	 */
	public NumberKaracuba multiplicateShumer(NumberKaracuba other) throws Exception
	{
		NumberKaracuba result = new NumberKaracuba(new StringBuilder());
		int lenX = this.length();
		int lenY = other.length();
		for (int i = lenY-1; i>=0; i--)
		{
			byte y = (byte)(other.mNumber.charAt(i) - '0');
			if (y == 0) continue; // Все слагаемое будет нулевым - можно пропустить.
			StringBuilder currentBuilder = new StringBuilder(lenX + 1);
			byte carry = 0;
			for (int j = lenX-1; j >= 0; j--)
			{
				byte x = (byte)(this.mNumber.charAt(j) - '0');
				int factum = x * y + carry;
				currentBuilder.insert(0, (char)('0' + (byte)(factum % 10)));
				carry = (byte)(factum / 10);
			}
			if (carry != 0)
			{
				currentBuilder.insert(0, (char)('0' + carry));
			}
			for (int k = 0; k < lenY - 1 - i; k++)
			{
				currentBuilder.append('0');
			}
			NumberKaracuba currentKaracuba = new NumberKaracuba(currentBuilder);
			result = result.add(currentKaracuba);
		}
		return result;
	}
	
}
