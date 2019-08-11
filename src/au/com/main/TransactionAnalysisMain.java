package au.com.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author dhananjay.sengar Entry point for the application. user inputs
 *         implementation and final results on output console.
 */
public class TransactionAnalysisMain {

	static String accountId = null;
	static Date startDate = null;
	static Date endDate = null;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		AnalyseTransaction objAnalyseTransaction = new AnalyseTransaction();

		// Ask inputs from user
		Scanner input = new Scanner(System.in);

		System.out.print("accountId: ");
		while (!input.hasNext("[A][C][C][0-9]*")) {
			System.out.println("Please enter a valid account id starting with ACC");
			input.next();
		}
		accountId = input.nextLine();

		System.out.print("from: ");
		String from = input.nextLine();
		System.out.print("to: ");
		String to = input.nextLine();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			startDate = sdf.parse(from);
			endDate = sdf.parse(to);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			System.out.println("Invalid date provided, please enter the dates in dd/MM/yyyy HH:mm:ss format");
			input.next();
		}

		input.close();

		objAnalyseTransaction.transactionAnalysis(accountId, startDate, endDate);

		Locale locale = new Locale("EN", "AU");
		Currency currency = Currency.getInstance(locale);
		String symbol = currency.getSymbol();

		if (objAnalyseTransaction.getRelativeAccountBalance() > 0) {
			System.out.println("Relative balance for the period is: " + symbol
					+ objAnalyseTransaction.getRelativeAccountBalance());
			System.out
					.println("Number of transactions included is: " + objAnalyseTransaction.getNumberOfTransactions());
		} else if (objAnalyseTransaction.getRelativeAccountBalance() < 0) {
			System.out.println("Relative balance for the period is: -" + symbol
					+ String.valueOf(objAnalyseTransaction.getRelativeAccountBalance()).substring(1));
			System.out
					.println("Number of transactions included is: " + objAnalyseTransaction.getNumberOfTransactions());

		} else {
			System.out.println("Relative balance for the period is: " + symbol
					+ objAnalyseTransaction.getRelativeAccountBalance());
			System.out
					.println("Number of transactions included is: " + objAnalyseTransaction.getNumberOfTransactions());
		}

	}

}
