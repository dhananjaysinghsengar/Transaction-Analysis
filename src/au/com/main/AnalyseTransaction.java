package au.com.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author dhananjay.sengar Implementation class responsible for analyzing
 *         transaction. for an account.
 */
public class AnalyseTransaction {

	// member variable
	private float relativeAccountBalance = 0;
	private int numberOfTransactions = 0;

	// Getters
	public float getRelativeAccountBalance() {
		return relativeAccountBalance;
	}

	public int getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public void transactionAnalysis(String accountId, Date startDate, Date endDate)
			throws FileNotFoundException, IOException {

		Properties configProperties = new Properties();
		configProperties.load(new FileInputStream("./src/au/com/resources/config.properties"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		try (Stream<String> lines = Files.lines(Paths.get(configProperties.getProperty("InputFilePath")))
				.filter(line -> line.contains(accountId))) {
			List<List<String>> values = lines.map(line -> Arrays.asList(line.split(","))).filter(line1 -> {
				try {
					return sdf.parse(line1.get(3)).after(startDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return false;
			}).collect(Collectors.toList());

			values.forEach(value -> {
				try {
					if (value.get(1).contains(accountId) && value.get(5).contains("PAYMENT")
							&& (sdf.parse(value.get(3)).after(startDate) && sdf.parse(value.get(3)).before(endDate))) {
						System.out.println(value);
						relativeAccountBalance -= Float.parseFloat(value.get(4));
						numberOfTransactions++;
						String TxId = value.get(0);

						// Iterate through each transaction to exclude reversal
						values.forEach(value1 -> {
							if (value1.get(5).contains("REVERSAL")) {
								if (value1.get(6).contains(TxId)) {
									System.out.println(value1);
									relativeAccountBalance += Float.parseFloat(value1.get(4));
									numberOfTransactions--;
								}
							}

						});

					} else if (value.get(2).contains(accountId) && value.get(5).contains("PAYMENT")
							&& (sdf.parse(value.get(3)).after(startDate) && sdf.parse(value.get(3)).before(endDate))) {
						System.out.println(value);
						relativeAccountBalance += Float.parseFloat(value.get(4));
						numberOfTransactions++;
						String TxId = value.get(0);

						// Iterate through each transaction to exclude reversal
						values.forEach(value1 -> {

							if (value1.get(5).contains("REVERSAL")) {
								if (value1.get(6).contains(TxId)) {
									System.out.println(value1);
									relativeAccountBalance -= Float.parseFloat(value1.get(4));
									numberOfTransactions--;
								}
							}

						});

					}

				} catch (NumberFormatException | ParseException e) {

					e.printStackTrace();
				}

			});

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
