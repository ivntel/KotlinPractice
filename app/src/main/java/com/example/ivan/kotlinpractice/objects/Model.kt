package com.example.ivan.kotlinpractice.objects

object Model {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo, val search: List<Search>)
    data class SearchInfo(val totalhits: Int)
    data class Search(val title: String)
}