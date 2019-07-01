import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.logging.Logger;

public class TelephoneOperator {

	Map<Integer, Float> countryCodePriceMapA;
	Map<Integer, Float> countryCodePriceMapB;

	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) {
		//enter phone number
		long phoneNumber = 4672777;
		//String array to store opr, countrycode and price
		String[] callValueA = new String[3];
		String[] callValueB = new String[3];
		TelephoneOperator o = new TelephoneOperator();
		o.populateValues();
		LOGGER.info(o.countryCodePriceMapA.toString());
		LOGGER.info(o.countryCodePriceMapB.toString());
		for (Entry<Integer, Float> entry : o.countryCodePriceMapA.entrySet()) {
			if (o.hasCountryCode(phoneNumber, entry.getKey())) {
				//store the value and break the loop
				callValueA = new String[] { "A", String.valueOf(entry.getKey()), String.valueOf(entry.getValue()) };
				break;
			}
		}
		for (Entry<Integer, Float> entry : o.countryCodePriceMapB.entrySet()) {
			if (o.hasCountryCode(phoneNumber, entry.getKey())) {
				//store the value and break the loop
				callValueB = new String[] { "B", String.valueOf(entry.getKey()), String.valueOf(entry.getValue()) };
				break;
			}
		}

		if (callValueA[1] == null && callValueB[1] == null) {
			System.out.println("Cannot make the call");
		} else if (callValueA[1] != null || Float.valueOf(callValueA[2]) > Float.valueOf(callValueB[2])) {
			o.printChanges(callValueA);
		} else {
			o.printChanges(callValueB);
		}
	}

	public void printChanges(String[] callValue) {
		System.out.println("Call operator user:" + callValue[0]);
		System.out.println("Country Code:" + callValue[1]);
		System.out.println("Charger per min:" + callValue[2]);
	}

	public void populateValues() {
		//populated reverse sorted key value
		countryCodePriceMapA = new TreeMap<Integer, Float>(Collections.reverseOrder());
		countryCodePriceMapA.put(1, 0.9f);
		countryCodePriceMapA.put(268, 5.1f);
		countryCodePriceMapA.put(46, 0.17f);
		countryCodePriceMapA.put(4620, 0.0f);
		countryCodePriceMapA.put(468, 0.15f);
		countryCodePriceMapA.put(4631, 0.15f);
		countryCodePriceMapA.put(4673, 0.9f);
		countryCodePriceMapA.put(46732, 1.1f);

		countryCodePriceMapB = new TreeMap<Integer, Float>(Collections.reverseOrder());
		countryCodePriceMapB.put(1, 0.92f);
		countryCodePriceMapB.put(44, 0.5f);
		countryCodePriceMapB.put(46, 0.2f);
		countryCodePriceMapB.put(467, 1.0f);
		countryCodePriceMapB.put(48, 1.2f);
	}

	public boolean hasCountryCode(long phonenumber, int countryCode) {
		while (phonenumber > 0) {
			if (phonenumber == countryCode) {
				LOGGER.info("<" + phonenumber + "><" + countryCode + ">");
				return true;
			}
			phonenumber = phonenumber / 10;
		}
		return false;
	}

}
