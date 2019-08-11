# Getting Started

### References
Git repository url - https://github.com/dhananjaysinghsengar/Transaction-Analysis.git

### Project description
The goal of this implementation is to build a system that analyze financial transaction records.
The system will be initialized with an input file in CSV format containing a list of transaction records.
Once initialized it should be able to print the relative account balance (positive or negative) in a given time frame.
The relative account balance is the sum of funds that were transferred to / from an account in a given time frame, it does not account for funds that were in that account
prior to the time frame.
Another requirement is that, if a transaction has a reversing transaction, this transaction should be omitted from the calculation, even if the reversing transaction is
outside the given time frame.

# Project execution steps
### Project import
- If using IDE please import using git url https://github.com/dhananjaysinghsengar/Transaction-Analysis.git
- If you would like to download the project in your local desktop first then follow below commands - 
	- git clone https://github.com/dhananjaysinghsengar/Transaction-Analysis.git

### prerequisite
- JDK 8 or higher installed on the platform where application is required to be executed
- Please perform below actions before running the application
	- Create a directory in your local desktop with name "Date File"
	- copy input.csv from src/au/com/resource and paste it to "Date File"
	- Go to config.properties file the in project and update InputFilePath key with the location of absolute location of input.csv including the file name
		i.e. C:/Data File/input.csv


### Project execution
- From IDE -
		- Select the project, Go to Project -> Build Project
		- select the project and Right click, Run As -> Java Application
- From Command prompt 
		- Open Command prompt
		- Go to the location where You have saved the project and got to location where TransactionAnalysisMain.java is saved
		- run `javac TransactionAnalysisMain.java` to compile the project
		- run `java TransactionAnalysisMain` to run the program
		
- Use will be asked to provide below inputs
	- accountId: <Should start with ACC followed by number>
	- from: <Start date/time, should have a pattern dd/MM/yyyy HH:mm:ss>
	- to: <End date/time, should have a pattern dd/MM/yyyy HH:mm:ss>
Once execution is completed user will be prompted with two output - 
	- Relative balance for the period is: <Relative balance depending upon debit and credit transactions including reversal>
	- Number of transactions included is: <Number of transaction excluding the payment and their corresponding reversals>

