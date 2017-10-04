package mold

data class StatusMold(
        val created_at: String,
        val id_str: String,
        val text: String,
        val truncated: Boolean,
        val entities: Entities,
        val source: String,
        val in_reply_to_status_id_str: String?,
        val in_reply_to_user_id_str: String?,
        val in_reply_to_screen_name: String?,
        val user: User,
        val geo: Geo?,
        val coordinates: List<Coordinate>?,
        val place: Place?,
        val contributors: List<Long>?
       // val retweeted_status: StatusMold
)

data class Entities(
        val hashTags: List<String>,
        val symbols: List<String>, //?
        val user_mentions: List<Mention>,
        val urls: List<Url>
)

data class Mention(
        val screen_name: String,
        val name: String,
        val id_str: String,
        val indices: List<Long>
)

data class Url(
        val url: String,
        val expanded_url: String,
        val display_url: String,
        val indices: List<Long>
)

data class Geo(
        val test: String?
)

data class Coordinate(
        val test: String?
)

data class Place(
        val test: String?
)

data class User(
        val id_str: String,
        val name: String,
        val screen_name: String,
        val location: String,
        val description: String,
        val url: String,
        val protected: Boolean,
        val followers_count: Long,
        val friends_count: Long,
        val listed_count: Long,
        val created_at: String,
        val favourites_count: Long,
        val utc_offset: Long,
        val time_zone: String,
        val geo_enabled: Boolean,
        val verified: Boolean,
        val statuses_count: Long,
        val lang: String,
        val contributors_enabled: Boolean,
        val is_translator: Boolean,
        val is_translation_enabled: Boolean,
        val profile_background_color: String,
        val profile_background_image_url: String,
        val profile_background_image_url_https: String,
        val profile_background_tile: Boolean,
        val profile_image_url: String,
        val profile_image_url_https: String,
        val profile_banner_url: String,
        val profile_link_color: String,
        val profile_sidebar_border_color: String,
        val profile_sidebar_fill_color: String,
        val profile_text_color: String,
        val profile_use_background_image: Boolean,
        val has_extended_profile: Boolean,
        val default_profile: Boolean,
        val default_profile_image: Boolean,
        val following: Boolean,
        val follow_request_sent: Boolean,
        val notifications: Boolean,
        val translator_type: String
)