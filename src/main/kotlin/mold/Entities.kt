package mold

data class Entities(
        val hashTags: List<HashTag>,
        val media: List<Media>,
        val urls: List<Url>,
        val user_mentions: List<UserMention>,
        val symbols: List<Symbol>,
        val polls: List<Poll>
)

data class ExtendedEntities(
        val media: List<Media>
)

data class HashTag(
        val indices: List<Int>,
        val text: String
)

data class Media(
        val display_url: String,
        val expanded_url: String,
        val id: Long,
        val id_str: String,
        val indices: List<Int>,
        val media_url: String,
        val media_url_https: String,
        val sizes: Sizes,
        val source_status_id: Long,
        val source_status_id_str: String,
        val type: String,
        val url: String
)

data class Sizes(
        val thumb: Size?,
        val large: Size?,
        val medium: Size?,
        val small: Size?
)

data class Size(
        val w: Int,
        val h: Int,
        val resize: String
)

data class Url(
        val display_url: String,
        val expanded_url: String,
        val indices: List<Int>,
        val url: String
)

data class UserMention(
        val id: Long,
        val id_str: String,
        val indices: List<Int>,
        val name: String,
        val screen_name: String
)

data class Symbol(
        val indices: List<Int>,
        val text: String
)

data class Poll(
        val options: List<Option>,
        val end_datetime: String,
        val duration_minutes: String
)

data class Option(
        val position: Int,
        val text: String
)