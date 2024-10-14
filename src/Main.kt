fun main() {

    println("Welcome to your banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    var accountType = ""
    var userChoice = 0
    while (accountType == "") {
        println("Choose an option (1, 2 or 3)")
        userChoice = (1..5).random()
        println("The selected option is ${userChoice}.")
        when (userChoice) {
            1 -> accountType = "debit"
            2 -> accountType = "credit"
            3 -> accountType = "checking"
            else -> continue
        }
    }
    println("You have created a ${accountType} account.")

    var accountBalance = (0..1000).random()
    println("The current balance is ${accountBalance} dollars.")
    val money = (0..1000).random()
    println("The amount you transferred is ${money} dollars.")

    // Withdrawal operation
    fun withdraw(amount: Int): Int {
        accountBalance -= amount
        println("You successfully withdrew ${amount} dollars. The current balance is ${accountBalance} dollars.")
        return amount
    }

    // Debit account withdrawal operation
    fun debitWithdraw(amount: Int): Int {
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The current balance is ${accountBalance} dollars.")
            return 0
        } else {
            return withdraw(amount)
        }
    }

    // Deposit operation
    fun deposit(amount: Int): Int {
        accountBalance += amount
        println("You successfully deposited ${amount} dollars. The current balance is ${accountBalance} dollars.")
        return amount
    }

    // Credit account deposit operation
    fun creditDeposit(amount: Int): Int {
        if (accountBalance == 0) {
            println("This account is completely paid off! Do not deposit money!")
            return accountBalance
        } else if (accountBalance + amount > 0) {
            println("Deposit failed, you tried to pay off an amount greater than the credit balance. The current balance is ${accountBalance} dollars.")
            return 0
        } else if (amount == -accountBalance) {
            accountBalance = 0
            println("You have paid off this account!")
            return amount
        } else {
            return deposit(amount)
        }
    }

    // Transfer operation: withdraw or deposit based on the mode
    fun transfer(mode: String) {
        val transferAmount: Int
        when (mode) {
            "withdraw" -> {
                transferAmount = if (accountType == "debit") debitWithdraw(money) else withdraw(money)
                println("The amount you withdrew is ${transferAmount} dollars.")
            }
            "deposit" -> {
                transferAmount = if (accountType == "credit") creditDeposit(money) else deposit(money)
                println("The amount you deposited is ${transferAmount} dollars.")
            }
            else -> return
        }
    }

    // Loop to simulate banking operations
    var isSystemOpen = true
    var option: Int
    while (isSystemOpen) {
        println("What would you like to do?")
        println("1. Check bank account balance")
        println("2. Withdraw money")
        println("3. Deposit money")
        println("4. Close the system")
        println("Which option do you choose? (1, 2, 3 or 4)")

        option = (1..5).random()  // Random choice for simulation
        println("The selected option is ${option}.")

        when (option) {
            1 -> println("The current balance is ${accountBalance} dollars.")
            2 -> transfer("withdraw")
            3 -> transfer("deposit")
            4 -> {
                isSystemOpen = false
                println("The system is closed")
            }
            else -> continue
        }
    }
}
