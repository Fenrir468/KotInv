import java.util.*

// This is a simply terminal inventory system
// Load data from JSON and create a list of each item using Item Class
// Main menu has three options:
//   1) View list, 2) Add Item, 3) Edit Item, 4) Help, 5) Exit
// Option 1 will display a table of current items in the list
// Option 2 will prompt for a name and amount
//   Using the name and amount, use the Class to create an item object
// Option 3 will prompt for the item name
//   It will then ask the user how much they want to adjust the item's amount
//   Positive numbers will increase, and negative numbers will decrease
//   If the amount is 0 or less, the item is removed
// Option 4 will display a help prompt
// Option 'x' Exits the program


// CONSTANTS
const val ITEM_NAME_PROMPT = "Item Name: "
const val ITEM_AMOUNT_PROMPT = "Item Amount: "

// VARIABLES
var activeProgram = true
var currentItems = mutableListOf<Item>()

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
        var newAmount = itemAmount + amount
        // Sets the new value to the object
        itemAmount = newAmount
    }

    fun getAttr() {
        // Prints out the attributes of this object
        println("$itemName     $itemAmount")
    }
}

fun showDict() {
    for (item in currentItems) {
        item.getAttr()
    }
}

fun addItem() {
    // Sets a variable so user can continue to add items
    var contAdd = true

    // While loop keeps prompting the user until they are done
    while (contAdd) {
        // Prompt the user for the item name
        println("What item are you adding to the inventory?")
        print(ITEM_NAME_PROMPT)
        var itemName = readLine()!!.toString()

        // Prompt the user for how many of the item they want to declare
        println("How many of this item do you have?")
        print(ITEM_AMOUNT_PROMPT)
        var itemAmount = readLine()!!.toInt()

        // Create the Item object and add it to the list
        var newItem = Item(itemName, itemAmount)
        currentItems.add(newItem)

        // Ask the user if they want to add more items
        print("Add another Item (Y/N)? ")
        var selection = readLine()!!.toString()
        if (selection.lowercase() == "n") {
            contAdd = false
        }
    }

}

fun editItem() {
    // This function will prompt the user and allow them to change the item amount
    // There is a warning that if the item is at 0 or less it will be removed
    println("Note: An item with 0 or less in inventory will automatically be removed from the list")
    println("Which item do you want to modify?")
    print(ITEM_NAME_PROMPT)
    var findItem = readLine()!!.toString()

    // Find the Item
    for (item in currentItems) {
        if (item.itemName == findItem) {
            // Display how many of the item is currently in inventory
            print("Current Amount in Inventory: ")
            print(item.itemAmount)
            // Prompt the user as to how much they want to increase or decrease the amount
            println("How much do you want to change the amount by?")
            println("Use positive numbers to increase and negative numbers to decrease")
            print("Amount to Change: ")
            var changeAmount = readLine()!!.toInt()
            // Apply the amount
            item.changeAmount(changeAmount)
            // Do a check to see if the item needs to be removed
        }
    }
    // Display the current amount
}

fun main() {
    // Main function that controls the program
    // While loop controls when the program ends
    while(activeProgram) {
        // Start with the menu and prompt the user for their selection
        println("Welcome. This is an item list program. Please select one of the following")
        println("1) View List  2) Add Item  3) Edit Item  4) Help")
        print("Your Selection (x to Exit): ")
        var selection = readLine()!!.lowercase()

        // Based on their selection, perform the following functions
        when (selection) {
            "1" -> showDict()     // Displays the current list if it exists
            "2" -> addItem()      // Allows the user to add Items
            "3" -> editItem()     // Allows the user to edit Items
            "4" -> println("4")   // Displays a help text
            "x" -> activeProgram = false  // Exits the program
            else -> {
                // If the Selection doesn't match, this is printed
                println("Please type a number between 1 and 5")
            }
        }
    }
    println("Closing Program")
}