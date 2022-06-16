package com.danc.petting.domain.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danc.petting.domain.models.Height
import com.danc.petting.domain.models.Image
import com.danc.petting.domain.models.Weight

@Entity(tableName = "Pets and Dogs")
class LocalPetsItem(
    @PrimaryKey
    @ColumnInfo(name = "PetsDogs")
    val id: Int,

    val bred_for: String,
    val breed_group: String,
    val country_code: String,
    val description: String,
    val history: String,
    val life_span: String,
    val name: String,
    val origin: String,
    val reference_image_id: String,
    val temperament: String
)
