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
            icon = R.drawable.microwave_100px
        ),
        Category(
            id = "2",
            name = "PC",
            description = "Trendy clothing and accessories.",
            icon = R.drawable.windows_client_100px
        ),
        Category(
            id = "3",
            name = "Clothes",
            description = "Everything for your home.",
            icon = R.drawable.clothes_100px
        ),
        Category(
            id = "4",
            name = "Clothes",
            description = "Everything for your home.",
            icon = R.drawable.clothes_100px
        ),
        Category(
            id = "5",
            name = "Clothes",
            description = "Everything for your home.",
            icon = R.drawable.clothes_100px
        )
    )

    private val _categoryFlow = MutableSharedFlow<List<Category>>(replay = 1)

    init{
        _categoryFlow.tryEmit(_categories.toList())
    }
    
    override fun getAllCategories(): List<Category> = _categories


}
