package com.devartlab.rubyjira.domain.entities.tasks

data class MytasksEntities(
    val indexEntities:List<IndexEntities>,
    val todayEntities:List<TodayEntities>,
    val upcomingEntities:List<UpcomingEntities>,
    val overdueEntities:List<OverdueEntities>,
    val noOverdueEntities:List<NoOverdueEntities>,
    val completedEntities: List<CompletedEntities>
)
data class CompletedEntities(
    val id: String,
    val avatar_url: String,
    val content: String,
    val created_at: String,
    val project_name: String
)
data class IndexEntities(
    val id: String,
    val avatar_url: String,
    val content:String,
    val created_at:String,
    val project_name:String
)
data class NoOverdueEntities(
    val id: String,
    val avatar_url: String,
    val content: String,
    val created_at: String,
    val project_name: String
)
data class OverdueEntities(
    val id: String,
    val avatar_url: String,
    val content:String,
    val created_at:String,
    val project_name:String
)
data class TodayEntities(
    val id: String,
    val avatar_url: String,
    val content:String,
    val created_at:String,
    val project_name:String
)
data class UpcomingEntities(
    val id: String,
    val avatar_url: String,
    val content:String,
    val created_at:String,
    val project_name:String
)