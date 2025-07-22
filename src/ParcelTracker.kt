// ==========================
// ParcelTracker.kt
// ==========================
import java.time.LocalDateTime

/**
 * Handles the management of parcels including adding, listing, updating,
 * viewing history, and deleting parcels.
 */
class ParcelTracker {
    private val parcels = mutableListOf<Parcel>() // In-memory list of all parcels

    /**
     * Adds a new parcel to the system.
     */
    fun addParcel(parcel: Parcel) {
        parcels.add(parcel)
        println("\u2705 Parcel added successfully.\n")
    }

    /**
     * Displays all parcels in a tabular format, including their latest status and location.
     */
    fun listParcels() {
        if (parcels.isEmpty()) {
            println("\uD83D\uDEAB No parcels to display.\n")
        } else {
            println("\n%-15s %-20s %-30s %-20s %-20s".format("Tracking #", "Recipient", "Address", "Status", "Location"))
            println("=".repeat(110))
            parcels.forEach {
                val lastUpdate = it.history.lastOrNull()
                println("%-15s %-20s %-30s %-20s %-20s".format(
                    it.trackingNumber,
                    it.recipientName,
                    it.address,
                    lastUpdate?.status ?: "N/A",
                    lastUpdate?.location ?: "N/A"
                ))
            }
            println()
        }
    }

    /**
     * Displays the complete tracking history of a parcel by tracking number.
     */
    fun viewHistory(trackingNumber: String) {
        val parcel = parcels.find { it.trackingNumber == trackingNumber }
        if (parcel != null) {
            println("\uD83D\uDD0D Parcel Found: ${parcel.trackingNumber}")
            println("Recipient: ${parcel.recipientName}")
            println("Address: ${parcel.address}")
            println("\uD83D\uDDFA\uFE0F Tracking History:")
            if (parcel.history.isEmpty()) {
                println("No updates yet.")
            } else {
                parcel.history.forEach {
                    println("• ${it.timestamp} | ${it.status} @ ${it.location}")
                }
            }
            println()
        } else {
            println("\u274C Parcel not found.\n")
        }
    }

    /**
     * Updates the status and location of a parcel by appending a new TrackingUpdate.
     */
    fun updateParcel(trackingNumber: String, location: String, status: String) {
        val parcel = parcels.find { it.trackingNumber == trackingNumber }
        if (parcel != null) {
            val update = TrackingUpdate(LocalDateTime.now(), location, status)
            parcel.history.add(update)
            println("\u2705 Hub update added to parcel.\n")
        } else {
            println("\u274C Parcel not found.\n")
        }
    }

    /**
     * Deletes a parcel from the list after confirming user input.
     */
    fun deleteParcel(trackingNumber: String) {
        val parcel = parcels.find { it.trackingNumber == trackingNumber }
        if (parcel != null) {
            print("Are you sure you want to delete parcel '$trackingNumber'? (yes/no): ")
            val confirm = readLine()?.trim()?.lowercase()
            if (confirm == "yes") {
                parcels.remove(parcel)
                println("\u2705 Parcel deleted successfully.\n")
            } else {
                println("\u274C Deletion cancelled.\n")
            }
        } else {
            println("\u274C Parcel not found.\n")
        }
    }
}