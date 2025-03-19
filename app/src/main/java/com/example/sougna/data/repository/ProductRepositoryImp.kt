package com.example.sougna.data.repository


import com.example.sougna.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

/**
 * Registry containing mock products for demonstration purposes.
 */
class ProductRepositoryImp @Inject constructor() : ProductRepository {

    private val _products = mutableListOf(
        Product(
            id = "1",
            name = "iPhone 15 Pro",
            description = "The latest flagship iPhone with advanced features.",
            price = 999.99,
            userId = "user1",
            rating = 3.5,
            categoryId = "7",
            thumbnailUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcdn.ipadizate.com%2F2023%2F09%2Fiphone-15-pro.png%3Fwidth%3D768%26format%3Dnowebp&f=1&nofb=1&ipt=be1c5a069ae71da81e508b5d546d0f388a2bd00a9f64079333fe6a15269adae3&ipo=images"
        ),
        Product(
            id = "2",
            name = "MacBook Pro 16-inch",
            description = "A powerful laptop for professionals, featuring the M2 Pro chip.",
            price = 2499.99,
            userId = "user2",
            rating = 2.5,
            categoryId = "7",
            thumbnailUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fimageio.forbes.com%2Fspecials-images%2Fimageserve%2F65496ba1379f809520ffebbf%2F0x0.jpg%3Fformat%3Djpg%26height%3D900%26width%3D1600%26fit%3Dbounds&f=1&nofb=1&ipt=91e6e46d55f089247a31a4843ff23f49de7e711bcc0bb40fa1649cf0b6cd2a66&ipo=images"
        ),
        Product(
            id = "9",
            name = "Smart Watch",
            description = "Track your fitness and stay connected with this modern smartwatch.",
            price = 299.99,
            userId = "user3",
            rating = 5.0,
            categoryId = "7",
            thumbnailUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi5.walmartimages.com%2Fasr%2Fe1ae90b2-98da-443b-888c-a71228c5234e.eb10d07052b374f38aa17166043f5a7a.jpeg&f=1&nofb=1&ipt=012b0d1d7906bfaabf6276727d36ca12be5198129845a3976e55a055ac404fa6&ipo=images"
        ),
        Product(
            id = "10",
            name = "Gaming Headset",
            description = "Immersive gaming headset with noise cancellation.",
            price = 149.99,
            userId = "user4",
            rating = 4.0,
            categoryId = "7",
            thumbnailUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi5.walmartimages.com%2Fasr%2F4a5972e6-11bc-4e3a-bd20-f6495b02a215.ca60fb0862062a0361b6db8276e779c4.jpeg&f=1&nofb=1&ipt=9d529350afc29dc89b338f33c8cc45cf6f02dc2b35f3fa96fcaeec91878d1984&ipo=images"
        ),
        Product(
            id = "11",
            name = "Wireless Mouse",
            description = "Ergonomic wireless mouse for productivity and gaming.",
            price = 79.99,
            userId = "user5",
            rating = 2.0,
            categoryId = "7",
            thumbnailUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.bhphotovideo.com%2Fimages%2Fimages2500x2500%2FLogitech_910_002651_Wireless_Mouse_M325_Mtlc_882511.jpg&f=1&nofb=1&ipt=70298bfa18589911da51eabeb73bdae91ac3dccf70ae27906cbc85a2469a14a3&ipo=images"
        ),

        // Fashion Category
        Product(
            id = "3",
            name = "Leather Jacket",
            description = "A stylish and durable leather jacket for all seasons.",
            price = 199.99,
            userId = "user6",
            categoryId = "2",
            rating = 2.5,
            thumbnailUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.myleather.co.uk%2Fwp-content%2Fuploads%2F2018%2F07%2F106-vintage-classic-diamond-brown-biker-leather-jacket.jpg&f=1&nofb=1&ipt=b3b96d2cb8d25a307bc46b74fdf9d3563367f04279219f74b401d926ffd5c14a&ipo=images"
        ),

        // Pets Category - Updated image
        Product(
            id = "4",
            name = "Premium Dog Food",
            description = "Premium dog food for a healthy and happy pet.",
            price = 49.99,
            rating = 2.0,
            userId = "user7",
            categoryId = "3",
            thumbnailUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi5.walmartimages.com%2Fasr%2Ffe92c921-bf4e-4694-8d5b-e6ae95facf0d.ed364d2d3fdd3d4e07f3a14546de983a.jpeg&f=1&nofb=1&ipt=9a7bd23c788d178fab64e55b2f0f1f6e5727f6d7a58ef416677e2de304ba9098&ipo=images"
        ),

        // Travel Category
        Product(
            id = "6",
            name = "Travel Backpack",
            description = "Durable and spacious backpack for all your travel needs.",
            price = 80.99,
            rating = 2.5,
            userId = "user8",
            categoryId = "5",
            thumbnailUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.storkz.com%2Fmedia%2Fcatalog%2Fproduct%2Fcache%2F1%2Fimage%2F1200x1200%2F9df78eab33525d08d6e5fb8d27136e95%2Fs%2F2%2Fs20910984.jpg&f=1&nofb=1&ipt=27422afbeef8132b17b3511a8fe19a09018d552fab9c5486991d04c30e597d45&ipo=images"
        )
    )

    private val _productFlow = MutableSharedFlow<List<Product>>(replay = 1)

    init{
        _productFlow.tryEmit(_products.toList())
    }

    override fun getAllProducts(): Flow<List<Product>> = _productFlow

    override suspend fun addProduct(product: Product) {
        _products.add(product)
        _productFlow.emit(_products.toList())
    }

}
