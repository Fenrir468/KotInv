import java.util.*

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
// Option 5 Exits the program

const val GREETING = "Welcome. This is an item list program. Please select one of the following"
const val OPTIONS = "1) View List  2) Add Item  3) Edit Item  4) Help  5) Exit"

var activeProgram = true
var currentItems = mutableListOf<Item>()

class Item(name : String, initAmount : Int){
    var itemName = "default"
    var itemAmount = 0

    init {
        itemName = name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        itemAmount = initAmount
    }

    fun changeAmount(amount : Int){
        var newAmount = itemAmount + amount
        itemAmount = newAmount
    }

    fun getAttr() {
        println("$itemName     $itemAmount")
    }
}

fun showDict() {
    for (item in currentItems) {
        item.getAttr()
    }
}

fun addItem() {
    println("What item are you adding to the inventory?")
    print("Item Name: ")
    var itemName = readLine()!!.toString()
    println("How many of this item do you have?")
    print("Item Amount: ")
    var itemAmount = readLine()!!.toInt()
    var newItem = Item(itemName, itemAmount)
    currentItems.add(newItem)
}

fun editItem() {
    // This function will prompt the user and allow them to change the item amount
    // There is a warning that if the item is at 0 or less it will be removed
    println("Note: An item with 0 or less in inventory will automatically be removed from the list")
    println("Which item do you want to modify?")
    print("Item Name: ")
    var findItem = readLine()!!.toString()

    // Find the Item
    for (item in currentItems) {
        if (item.itemName == findItem) {
            // Display how many of the item is currently in inventory
            println("Current Amount in Inventory: ")
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
        println(GREETING)
        println(OPTIONS)
        print("Your Selection: ")
        var selection = readLine()!!.toInt()

        // Based on their selection, perform the following functions
        when (selection) {
            1 -> showDict()     // Displays the current list if it exists
            2 -> addItem()      // Allows the user to add Items
            3 -> editItem()     // Allows the user to edit Items
            4 -> println("4")   // Displays a help text
            5 -> activeProgram = false  // Exits the program
            else -> {
                // If the Selection doesn't match, this is printed
                println("Please type a number between 1 and 5")
            }
        }
    }
    println("Closing Program")
}