package com.devartlab.rubyjira.data.models

import com.devartlab.rubyjira.app.utilsView.MyDate
import com.devartlab.rubyjira.domain.entities.tasks.*
import com.google.gson.annotations.SerializedName

data class MyTasksResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: MyTaskData,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class NoOverdueItem(

	@field:SerializedName("due_date")
	val dueDate: String,

	@field:SerializedName("column")
	val column: Column,

	@field:SerializedName("index")
	val index: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("task_id")
	val taskId: Any,

	@field:SerializedName("priority")
	val priority: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("column_id")
	val columnId: Int,

	@field:SerializedName("completed_at")
	val completedAt: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("user")
	val user: User
){
	fun getDateFormatted(): String = if(createdAt == null) " " else MyDate.formatDate(createdAt)
}

data class Project(

	@field:SerializedName("end_date")
	val endDate: String,

	@field:SerializedName("tenant_id")
	val tenantId: Int,

	@field:SerializedName("color")
	val color: String,

	@field:SerializedName("visibility")
	val visibility: Int,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("deleted_at")
	val deletedAt: Any,

	@field:SerializedName("completed_at")
	val completedAt: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("start_date")
	val startDate: String
)

data class OverdueItem(

	@field:SerializedName("due_date")
	val dueDate: String,

	@field:SerializedName("column")
	val column: Column,

	@field:SerializedName("index")
	val index: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("task_id")
	val taskId: Any,

	@field:SerializedName("priority")
	val priority: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("column_id")
	val columnId: Int,

	@field:SerializedName("completed_at")
	val completedAt: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("user")
	val user: User
){
	fun getDateFormatted(): String = if(createdAt == null) " " else MyDate.formatDate(createdAt)
}

data class TodayItem(

	@field:SerializedName("due_date")
	val dueDate: String,

	@field:SerializedName("column")
	val column: Column,

	@field:SerializedName("index")
	val index: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("task_id")
	val taskId: Any,

	@field:SerializedName("priority")
	val priority: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("column_id")
	val columnId: Int,

	@field:SerializedName("completed_at")
	val completedAt: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("user")
	val user: User
){
	fun getDateFormatted(): String = if(createdAt == null) " " else MyDate.formatDate(createdAt)
}

data class Column(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("project_id")
	val projectId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("index")
	val index: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("project")
	val project: Project,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("uuid")
	val uuid: String
)

data class IndexItem(

	@field:SerializedName("due_date")
	val dueDate: String,

	@field:SerializedName("column")
	val column: Column,

	@field:SerializedName("index")
	val index: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("task_id")
	val taskId: Any,

	@field:SerializedName("priority")
	val priority: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("column_id")
	val columnId: Int,

	@field:SerializedName("completed_at")
	val completedAt: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("user")
	val user: User
){
	fun getDateFormatted(): String = if(createdAt == null) " " else MyDate.formatDate(createdAt)
}

data class CompletedItem(

	@field:SerializedName("due_date")
	val dueDate: String,

	@field:SerializedName("column")
	val column: Column,

	@field:SerializedName("index")
	val index: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("task_id")
	val taskId: Any,

	@field:SerializedName("priority")
	val priority: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("column_id")
	val columnId: Int,

	@field:SerializedName("completed_at")
	val completedAt: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("user")
	val user: User
){
	fun getDateFormatted(): String = if(createdAt == null) " " else MyDate.formatDate(createdAt)
}

data class UpcomingItem(

	@field:SerializedName("due_date")
	val dueDate: String,

	@field:SerializedName("column")
	val column: Column,

	@field:SerializedName("index")
	val index: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("task_id")
	val taskId: Any,

	@field:SerializedName("priority")
	val priority: Int,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("content")
	val content: String,

	@field:SerializedName("column_id")
	val columnId: Int,

	@field:SerializedName("completed_at")
	val completedAt: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("user")
	val user: User
){
	fun getDateFormatted(): String = if(createdAt == null) " " else MyDate.formatDate(createdAt)
}

data class User(

	@field:SerializedName("tenant_id")
	val tenantId: Int,

	@field:SerializedName("role")
	val role: Int,

	@field:SerializedName("tenant_owner")
	val tenantOwner: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Any,

	@field:SerializedName("media")
	val media: List<Any>,

	@field:SerializedName("uuid")
	val uuid: String,

	@field:SerializedName("deleted_at")
	val deletedAt: Any,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)

data class MyTaskData(

	@field:SerializedName("overdue")
	val overdue: List<OverdueItem>,

	@field:SerializedName("today")
	val today: List<TodayItem>,

	@field:SerializedName("index")
	val index: List<IndexItem>,

	@field:SerializedName("completed")
	val completed: List<CompletedItem>,

	@field:SerializedName("no_overdue")
	val noOverdue: List<NoOverdueItem>,

	@field:SerializedName("upcoming")
	val upcoming: List<UpcomingItem>
)
fun MyTaskData.toMyTaskDomain()=
	MytasksEntities(
        index.map { it.toIndexTaskDomain()},
		today.map { it.toTodayTaskDomain()},
		upcoming.map { it.toUpcomingTaskDomain()},
		overdue.map { it.toOverdueTaskDomain()},
		noOverdue.map { it.toNoOverdueTaskDomain()},
		completed.map { it.toCompletedTaskDomain()}
    )
fun IndexItem.toIndexTaskDomain() =
	IndexEntities(id,user.avatarUrl,content,getDateFormatted(),column.project.name)
fun TodayItem.toTodayTaskDomain() =
	TodayEntities(id,user.avatarUrl,content,getDateFormatted(),column.project.name)
fun UpcomingItem.toUpcomingTaskDomain() =
	UpcomingEntities(id,user.avatarUrl,content,getDateFormatted(),column.project.name)
fun OverdueItem.toOverdueTaskDomain() =
	OverdueEntities(id,user.avatarUrl,content,getDateFormatted(),column.project.name)
fun NoOverdueItem.toNoOverdueTaskDomain() =
	NoOverdueEntities(id,user.avatarUrl,content,getDateFormatted(),column.project.name)
fun CompletedItem.toCompletedTaskDomain() =
	CompletedEntities(id,user.avatarUrl,content,getDateFormatted(),column.project.name)
