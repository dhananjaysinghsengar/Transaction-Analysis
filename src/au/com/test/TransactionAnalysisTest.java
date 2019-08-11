package au.com.test;

import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Test;

import au.com.main.AnalyseTransaction;

public class TransactionAnalysisTest {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	@Test
	public void testIfGivenAccountTransferAmountIncludingReversal()
			throws ParseException, FileNotFoundException, IOException {
		AnalyseTransaction objAnalyseTransaction = new AnalyseTransaction();

		objAnalyseTransaction.transactionAnalysis("ACC334455", sdf.parse("20/10/2018 12:00:00"),
				sdf.parse("20/10/2018 19:00:00"));
		assertEquals(-25.0f, objAnalyseTransaction.getRelativeAccountBalance(), 0.0f);
		assertEquals(1, objAnalyseTransaction.getNumberOfTransactions());
	}

	@Test
	public void testIfGivenAccountReceivesAmountIncludingReversal()
			throws ParseException, FileNotFoundException, IOException {
		AnalyseTransaction objAnalyseTransaction = new AnalyseTransaction();

		objAnalyseTransaction.transactionAnalysis("ACC778890", sdf.parse("21/10/2018 09:00:00"),
				sdf.parse("21/10/2018 20:00:00"));
		assertEquals(7.25f, objAnalyseTransaction.getRelativeAccountBalance(), 0.0f);
		assertEquals(1, objAnalyseTransaction.getNumberOfTransactions());

	}

	@Test
	public void testIfGivenAccountReceivesAndTransferAmountIncludingReversal()
			throws ParseException, FileNotFoundException, IOException {
		AnalyseTransaction objAnalyseTransaction = new AnalyseTransaction();

		objAnalyseTransaction.transactionAnalysis("ACC665520", sdf.parse("09/08/2019 10:40:00"),
				sdf.parse("09/08/2019 17:50:00"));
		assertEquals(-80.50f, objAnalyseTransaction.getRelativeAccountBalance(), 0.0f);
		assertEquals(2, objAnalyseTransaction.getNumberOfTransactions());

	}

}
