package mold

/**
 * completed!
 */
data class User(
        val id_str: String,
        val name: String,
        val screen_name: String,
        val location: String?,
        val url: String?,
        val description: String?,
        val protected: Boolean,
        val verified: Boolean,
        val followers_count: Int,
        val friends_count: Int,
        val listed_count: Int,
        val favourites_count: Int,
        val statuses_count: Int,
        val created_at: String,
        val utc_offset: Int?,
        val time_zone: String?,
        val geo_enabled: Boolean,
        val lang: String,
        val contributors_enabled: Boolean,
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
        val is_translation_enabled: Boolean,
        val has_extended_profile: Boolean,
        val default_profile: Boolean,
        val default_profile_image: Boolean,
        val follow_request_sent: Boolean?,
        val translator_type: String

)