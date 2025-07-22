// ==========================
// Main.kt
// ==========================
/**
 * Entry point for the console-based parcel tracking system.
 * Allows users to manage parcel records through a simple text-based menu.
 */
fun main() {
    val tracker = ParcelTracker()

    while (true) {
        println(
            """
            ================================
            📦 PARCEL TRACKING SYSTEM
            ================================
            1. Add Parcel
            2. List Parcels
            3. View Parcel History
            4. Update Parcel Hub/Status
            5. Delete Parcel
            6. Exit
            --------------------
            Choose an option:
        """.trimIndent()
        )

        when (readLine()?.trim()) {
            "1" -> {
                // Input parcel details
                print("Enter tracking number: ")
                val tn = readLine() ?: ""
                print("Enter recipient name: ")
                val rn = readLine() ?: ""
                print("Enter address: ")
                val addr = readLine() ?: ""

                val newParcel = Parcel(trackingNumber = tn, recipientName = rn, address = addr)
                tracker.addParcel(newParcel)
            }

            "2" -> tracker.listParcels() // List all parcels

            "3" -> {
                print("Enter tracking number to view history: ")
                val tn = readLine() ?: ""
                tracker.viewHistory(tn)
            }

            "4" -> {
                print("Enter tracking number to update: ")
                val tn = readLine() ?: ""
                print("Enter new hub location: ")
                val loc = readLine() ?: ""
                print("Enter new status (e.g., In Transit, Delivered): ")
                val status = readLine() ?: ""
                tracker.updateParcel(tn, loc, status)
            }

            "5" -> {
                print("Enter tracking number to delete: ")
                val tn = readLine() ?: ""
                tracker.deleteParcel(tn)
            }

            "6" -> {
                println("\uD83D\uDC4B Exiting system. Goodbye!")
                return
            }

            else -> println("❗ Invalid option. Try again.\n")
        }
    }
}
