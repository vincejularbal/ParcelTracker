// ==========================
// Parcel.kt
// ==========================
import java.time.LocalDateTime

/**
 * Represents a tracking update that records the time, location, and status
 * of a parcel during its delivery journey.
 */
data class TrackingUpdate(
    val timestamp: LocalDateTime,
    val location: String,
    val status: String
)

/**
 * Represents a parcel with identifying details and a list of tracking updates.
 * @property trackingNumber Unique ID for the parcel.
 * @property recipientName Name of the parcel's recipient.
 * @property address Destination address of the parcel.
 * @property history Mutable list that stores the tracking history.
 */
data class Parcel(
    val trackingNumber: String,
    val recipientName: String,
    val address: String,
    val history: MutableList<TrackingUpdate> = mutableListOf()
)