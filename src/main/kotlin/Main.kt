import java.util.*
// This is a simply terminal inventory system built in Kotlin.
// Loads data from JSON and create a list of each item using Item Class.
// Main menu has three options:
//   1) View list, 2) Add Item, 3) Edit Item, 4) Help, x) Exit
// Option 1 will display a table of current items in the list.
// Option 2 will prompt for a name and amount.
//   Using the name and amount, use the Class to create an item object.
// Option 3 will prompt for the item name.
//   It will then ask the user how much they want to adjust the item's amount.
//   Positive numbers will increase, and negative numbers will decrease.
//   If the amount is 0 or less, the item is removed.
// Option 4 will display a help prompt.
// Option 'x' Exits the program.


// CONSTANTS
const val FORMAT_PADDING = "\n"             // Prints a blank line
const val NAME_PADDING = 20                 // For formatting the table cell length

// VARIABLES
var activeProgram = true                    // For determining when the user wants to quit the program
var currentItems = mutableListOf<Item>()    // Holds the list of Items

// CLASSES
class Item(name : String, initAmount : Int){
    // Declares the class variables
    var itemName = "default"
    var itemAmount = 0

    init {
        // Sets the initial values of the variables
        // Item names are to have the first letter capitalized
        itemName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        // Sets the initial amount of the item
        itemAmount = initAmount
    }

    fun changeAmount(amount : Int){
        // Method that will change the item amount.
        // This is done through addition, so a negative number is needed to decrement
        val newAmount = itemAmount + amount

        // Sets the new value to the object
        itemAmount = newAmount

        // Print the new amount
        println("Amount for $itemName changed to $itemAmount")
    }

    fun getAttr() {
        // Prints out the attributes of this object

        // For formatting, there needs to be spacing after the name.
        // Print the name with the padding
        print(itemName.padEnd(NAME_PADDING))

        // Print the item's current amount
        print("$itemAmount\n")
    }
}

fun showDict() {
    // This shows a table of the current inventory
    // First show the headers
    print(FORMAT_PADDING)
    print("ITEM NAME".padEnd(NAME_PADDING))
    print("AMOUNT\n")

    // Call the objects print method
    for (item in currentItems) {
        item.getAttr()
    }
}

fun addItem() {
    // Sets a variable so user can continue to add items
    var contAdd = true

    // While loop keeps prompting the user until they are done
    while (contAdd) {
        // Format the text spacing
        print(FORMAT_PADDING)

        // Prompt the user for the item name
        println("What item are you adding to the inventory?")
        print("Item Name: ")
        val itemName = readLine()!!.toString()

        // Prompt the user for how many of the item they want to declare
        println("How many of this item do you have?")
        print("Item Amount: ")
        val itemAmount = readLine()!!.toInt()

        // Create the Item object and add it to the list
        val newItem = Item(itemName, itemAmount)
        currentItems.add(newItem)

        // Ask the user if they want to add more items
        print("Add another Item (Y/N)? ")
        val selection = readLine()!!.toString()
        if (selection.lowercase() == "n") {
            contAdd = false
        }
    }
}

fun editItem() {
    // This function will prompt the user and allow them to change the item amount
    // Padding
    println(FORMAT_PADDING)

    // There is a warning that if the item is at 0 or less it will be removed
    println("Which item do you want to modify?")
    print("Item Name: ")
    val findItem = readLine()!!.toString()

    // Find the Item
    for (item in currentItems) {
        if (item.itemName == findItem) {
            // Display Padding
            print(FORMAT_PADDING)

            // Display how many of the item is currently in inventory
            print("CURRENT AMOUNT: ")
            print(item.itemAmount)

            // Prompt the user as to how much they want to increase or decrease the amount
            println("\nHow much do you want to change the amount by? Use negative numbers to decrease.")
            print("Amount to Change: ")
            val changeAmount = readLine()!!.toInt()

            // Apply the amount
            item.changeAmount(changeAmount)

        }
    }
}

fun showHelp() {
    // This function displays a help text
    println("This is a simple inventory tool built in Kotlin.")
    println("At the main menu you can type in either 1, 2, 3, or 4.")
    println("1) Displays the current inventory list.")
    println("2) You can add an item by typing in the item name and the amount in inventory.")
    println("3) You can edit an existing item by either adding or subtracting to it's amount.")
    println("4) This help screen.")
    println("To exit the program, just type 'x' at the main menu.")
}

fun main() {
    // Main function that controls the program
    // While loop controls when the program ends. User must type x at the menu.
    while(activeProgram) {
        // Start with the menu and prompt the user for their selection
        print(FORMAT_PADDING)
        println("Welcome. This is an item list program. Please select one of the following:")
        println("1) View List  2) Add Item  3) Edit Item  4) Help")
        print("Your Selection (x to Exit): ")

        // Based on their selection, match the selection to the following functions
        when (readLine()!!.lowercase()) {
            "1" -> showDict()               // Displays the current list if it exists
            "2" -> addItem()                // Allows the user to add Items
            "3" -> editItem()               // Allows the user to edit Items
            "4" -> showHelp()               // Displays a help text
            "x" -> activeProgram = false    // Exits the program
            else -> {
                // If the Selection doesn't match, this is printed
                println("Please type a number between 1 and 5")
            }
        }
    }
    // Before the program quits, it informs the user that the program is closing
    println("Closing Program")
}