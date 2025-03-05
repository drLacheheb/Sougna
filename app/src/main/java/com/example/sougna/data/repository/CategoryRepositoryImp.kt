package com.example.sougna.data.repository

import com.example.sougna.R
import com.example.sougna.data.model.Category
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject


class CategoryRepositoryImp @Inject constructor(): CategoryRepository {

    private val _categories  =mutableListOf(
        Category(
            id = "1",
            name = "Electronics",
            description = "Latest gadgets and devices.",
            icon = R.drawable.ic_electronics
        ),
        Category(
            id = "2",
            name = "Fashion",
            description = "Trendy clothing and accessories.",
            icon = R.drawable.ic_fashion
        ),
        Category(
            id = "3",
            name = "Home & Kitchen",
            description = "Everything for your home.",
            icon = R.drawable.ic_home_kitchen
        )
    )

    private val _categoryFlow = MutableSharedFlow<List<Category>>(replay = 1)

    init{
        _categoryFlow.tryEmit(_categories.toList())
    }
    
    override fun getAllCategories(): List<Category> = _categories


}
