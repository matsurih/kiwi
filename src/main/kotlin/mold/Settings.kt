package mold

data class Settings(
        val always_use_https: Boolean,
        val discoverable_by_email: Boolean,
        val geo_enabled: Boolean,
        val language: String,
        val protected: Boolean,
        val screen_name: String,
        val show_all_inline_media: Boolean,
        val sleep_time: SleepTime,
        val time_zone: TimeZone,
        val trend_location: Location,
        val use_cookie_personalization: Boolean,
        val allow_contributor_request: Boolean
)

data class SleepTime(
        val enabled: Boolean,
        val start_time: String?,
        val end_time: String?
)

data class TimeZone(
        val name: String,
        val tzinfo_name: String,
        val utc_offset: Int
)

data class Location(
        val country: String,
        val countryCode: String,
        val name: String,
        val parentid: Long,
        val placeType: PlaceType,
        val url: String,
        val woeid: Long
)

data class PlaceType(
        val code: Int,
        val name: String
)