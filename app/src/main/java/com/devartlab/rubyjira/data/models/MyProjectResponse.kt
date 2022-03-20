package com.devartlab.rubyjira.data.models

import com.devartlab.rubyjira.domain.entities.project.MyProjectEntities
import com.google.gson.annotations.SerializedName

data class MyProjectResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<ProjectItem>,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class ProjectItem(

	@field:SerializedName("color")
	val color: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("uuid")
	val uuid: String
)
fun List<ProjectItem>.toMyProjectDomain() = map {
	MyProjectEntities(
		it.name,
		it.uuid
	)
}