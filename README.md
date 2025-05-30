# Brainwave_Matrix_Intern
# Fully Functional ATM Interface in Java

## Overview
This project is a ATM interface implemented in Java. It allows users to perform common banking operations such as cash withdrawal, deposits, balance checks, fund transfers, and bill payments through a simple text-driven menu in the command line. The application includes PIN authentication, input validation, and basic error handling to simulate real-world ATM functionality.

## Features
- Secure user authentication with PIN verification  
- Account balance inquiry  
- Cash withdrawal and deposit options  
- Fund transfer between accounts  
- Bill payment facility  
- Clear, menu-driven terminal interface  
- Input validation and error handling  
- Transaction confirmation prompts
- New account creation

## Technologies  
- Java SE 8+  
- Standard Java libraries (no external dependencies)  

## Getting Started

### Prerequisites  
- JDK 8 or higher installed  
- Terminal or command prompt access
  

## Technologies
- Java SE 8 or higher  
- No external dependencies (uses standard Java libraries)  

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or above  
- Terminal or command prompt access

### Running the Application

1. Clone the repository:  
   ```bash
   git clone https://github.com/yourusername/atm-console.git
   
Navigate to the project directory:
BASH
cd atm-console

Compile the Java source files:
BASH
javac -d target/classes src/main/java/com/bank/atm/model/Account.java \
                       src/main/java/com/bank/atm/repo/Bank.java \
                       src/main/java/com/bank/atm/ui/ATM.java \
                       src/main/java/com/bank/atm/App.java
                       
Run the application:
BASH
java -cp target/classes com.bank.atm.App

Usage
-On startup, enter your account number and PIN to log in.
-Use the numeric menu to select transactions (withdraw, deposit, transfer, bill pay, balance inquiry).
-Follow on-screen prompts to complete operations.
-Choose the exit option to quit the application.


Contribution
Contributions are welcome! Feel free to fork the repository, create feature branches, and submit pull requests or open issues.
##Contact:
For any questions or support, please reach out at [your-gurijalaharikrishna90@gmail.com]
