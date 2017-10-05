package mold

/**
 * completed!
 */
data class Tweet(
        val created_at: String,
        val id: Long,
        val id_str: String,
        val text: String,
        val source: String,
        val truncated: Boolean,
        val in_reply_to_status_id: Long?,
        val in_reply_to_status_id_str: String?,
        val in_reply_to_user_id: Long?,
        val in_reply_to_user_id_str: String?,
        val in_reply_to_screen_name: String?,
        val user: User,
        val coordinates: Coordinates?,
        val place: Place?,
        val quoted_status_id: Long?,
        val quoted_status_id_str: String?,
        val is_quote_status: Boolean,
        val quoted_status: Tweet?,
        val retweeted_status: Tweet,
        val quote_count: Int?,
        val reply_count: Int,
        val retweet_count: Int,
        val favorite_count: Int?,
        val entities: Entities,
        val extended_entities: List<Media>?,
        val favorited: Boolean?,
        val retweeted: Boolean,
        val possibly_sensitive: Boolean?,
        val filter_level: String,
        val lang: String?,
        val matching_rules: List<Rule>,
        val contributors: List<Long>?
)

data class Rule(
       val tag : String,
       val id: Long
)