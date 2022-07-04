package com.danc.petting.domain.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danc.petting.domain.models.Height
import com.danc.petting.domain.models.Image
import com.danc.petting.domain.models.Weight

@Entity(tableName = "petsanddogs")
class LocalPetsItem(
    @PrimaryKey
    @ColumnInfo(name = "petsDogs")
    val id: Int?,
    @ColumnInfo(name = "bredFor")
    val bred_for: String?,
    @ColumnInfo(name = "breedGroup")
    val breed_group: String?,
    @ColumnInfo(name = "lifeSpan")
    val life_span: String?,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "origin")
    val origin: String?,
    @ColumnInfo(name = "referrenceImage")
    val reference_image_id: String?,
    @ColumnInfo(name = "temperament")
    val temperament: String?,
    val imperial: String?,
    val metric: String?,
    val imperialHeight: String?,
    val metricHeight: String?
)
