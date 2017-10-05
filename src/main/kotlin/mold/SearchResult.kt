package mold

data class SearchResult(
       val statuses: List<Tweet>,
       val searchMetadata: SearchMetadata
)

data class SearchMetadata(
        val max_id: Long,
        val max_id_str: String,
        val since_id: Long,
        val since_id_str: String,
        val query: String,
        val count: Int,
        val completed_in: Float,
        val refresh_url: String,
        val next_results: String
)