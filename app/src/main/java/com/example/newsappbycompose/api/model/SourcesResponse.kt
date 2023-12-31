package com.example.newsappbycompose.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class SourcesResponse(

	@field:SerializedName("sources")
	val sources: List<SourceItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)
@Entity
data class SourceItem(

	@field:SerializedName("country")
	var country: String? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("description")
	var description: String? = null,

	@field:SerializedName("language")
	var language: String? = null,

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	var id: String,

	@field:SerializedName("category")
	var category: String? = null,

	@field:SerializedName("url")
	var url: String? = null
)
