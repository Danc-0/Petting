package com.danc.petting.domain.models

data class PetsItem(
    val weight: Weight,
    val height: Height,
    val bred_for: String,
    val breed_group: String,
    val country_code: String?,
    val description: String,
    val history: String,
    val id: Int,
    val image: Image,
    val life_span: String,
    val name: String,
    val origin: String,
    val reference_image_id: String,
    val temperament: String,
)