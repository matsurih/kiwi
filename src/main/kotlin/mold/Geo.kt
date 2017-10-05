package mold

data class Place(
        val id: String,
        val url: String,
        val place_type: String,
        val name: String,
        val full_name: String,
        val country_code: String,
        val country: String,
        val bounding_box: BoundingBox
)

data class BoundingBox(
        val coordinates: List<List<List<Float>>>,
        val type: String
)

data class Coordinates(
        val coordinates: List<Float>,
        val type: String
)