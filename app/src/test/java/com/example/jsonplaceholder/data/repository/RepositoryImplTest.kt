package com.example.jsonplaceholder.data.repository

import com.example.jsonplaceholder.data.model.albums.AlbumsModel
import com.example.jsonplaceholder.data.model.albums.AlbumsModelItemModel
import com.example.jsonplaceholder.data.model.comments.CommentsModel
import com.example.jsonplaceholder.data.model.photos.PhotosModel
import com.example.jsonplaceholder.data.model.posts.PostsModel
import com.example.jsonplaceholder.data.model.posts.PostsModelItemModel
import com.example.jsonplaceholder.data.model.todos.TodosModel
import com.example.jsonplaceholder.data.model.users.*
import com.example.jsonplaceholder.data.remote.ApiRequest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.Collections.addAll

class RepositoryImplTest{
    @Mock
    lateinit var api: ApiRequest


    @Before
    fun setUp()
    {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun repositoryTestGetUsers_EmptyList()= runTest {
        val users = UsersModel()
        //Mockito feature


        Mockito.`when`(api.getUsers()).thenReturn(users)
        val repositoryTest= RepositoryImpl(api)
        val result= repositoryTest.getUsers()
        assertEquals(0,result.size)
    }

    @Test
    fun repositoryTestGetPosts_EmptyList()= runTest {
        val posts = PostsModel()
        //Mockito feature
        Mockito.`when`(api.getPosts()).thenReturn(posts)
        val repositoryTest= RepositoryImpl(api)
        val result= repositoryTest.getPosts()
        assertEquals(0,result.size)
    }

    @Test
    fun repositoryTestGetAlbums_EmptyList()= runTest {
        val albums = AlbumsModel()
        //Mockito feature
        Mockito.`when`(api.getAlbums()).thenReturn(albums)

        val repositoryTest= RepositoryImpl(api)
        val result= repositoryTest.getAlbums()
        assertEquals(0,result.size)
    }

    @Test
    fun repositoryTestGetComments_EmptyList()= runTest {
        val comments = CommentsModel()
        //Mockito feature
        Mockito.`when`(api.getComments()).thenReturn(comments)

        val repositoryTest= RepositoryImpl(api)
        val result= repositoryTest.getComments()
        assertEquals(0,result.size)
    }

    @Test
    fun repositoryTestGetTodos_EmptyList()= runTest {
        val todos = TodosModel()
        //Mockito feature
        Mockito.`when`(api.getTodos()).thenReturn(todos)

        val repositoryTest= RepositoryImpl(api)
        val result= repositoryTest.getTodos()
        assertEquals(0,result.size)
    }

    @Test
    fun repositoryTestGetPhotos_EmptyList()= runTest {
        val photos = PhotosModel()
        //Mockito feature
        Mockito.`when`(api.getPhotos()).thenReturn(photos)

        val repositoryTest= RepositoryImpl(api)
        val result= repositoryTest.getPhotos()
        assertArrayEquals(arrayOf<UsersModelItemModel>(),result.toArray())
    }


    @Test
    fun testGetUsers_expectedUsersList()= runTest {
        val users_sample= UsersModel().apply {
            addAll(
                listOf(
                    UsersModelItemModel(
                        id = 1,
                        name = "Leanne Graham",
                        username = "Bret",
                        email = "Sincere@april.biz",
                        address = AddressModel(
                            street = "Kulas Light",
                            suite = "Apt. 556",
                            city = "Gwenborough",
                            zipcode = "92998-3874",
                            geo = GeoModel(
                                lat = "-37.3159",
                                lng = "81.1496"
                            )
                        ),
                        phone = "1-770-736-8031 x56442",
                        website = "hildegard.org",
                        company = CompanyModel(
                            name = "Romaguera-Crona",
                            catchPhrase = "Multi-layered client-server neural-net",
                            bs = "harness real-time e-markets"
                        )
                    ),
                    UsersModelItemModel(
                        id = 2,
                        name = "Ervin Howell",
                        username = "Antonette",
                        email = "Shanna@melissa.tv",
                        address = AddressModel(
                            street = "Victor Plains",
                            suite = "Suite 879",
                            city = "Wisokyburgh",
                            zipcode = "90566-7771",
                            geo = GeoModel(
                                lat = "-43.9509",
                                lng = "-34.4618"
                            )
                        ),
                        phone = "010-692-6593 x09125",
                        website = "anastasia.net",
                        company = CompanyModel(
                            name = "Deckow-Crist",
                            catchPhrase = "Proactive didactic contingency",
                            bs = "synergize scalable supply-chains"
                        )
                    )
                )
            )
        }
    
        Mockito.`when`(api.getUsers()).thenReturn(users_sample)

        val repositoryTest= RepositoryImpl(api)
        val result= repositoryTest.getUsers()
        assertEquals(2,result.size)
    }

    @Test
    fun testGetPosts_expectedPostsList()= runTest {
        val posts_sample= PostsModel().apply {
            addAll(
                listOf(
                    PostsModelItemModel(
                        "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto",
                        1,
                        "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                        1
                    ),
                    PostsModelItemModel(
                        "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla",
                        2,
                        "qui est esse",
                        1
                    ),
                    PostsModelItemModel(
                        "sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga",
                        3,
                        "ea molestias quasi exercitationem repellat qui ipsa sit aut",
                        1
                    ),
                )
            )
        }
        Mockito.`when`(api.getPosts()).thenReturn(posts_sample)

        val repositoryTest= RepositoryImpl(api)
        val result= repositoryTest.getPosts()
        assertEquals(3,result.size)
    }

//    @Test
//    fun testGetAlbums_expectedAlbumsList()= runTest {
//        val albums_sample= AlbumsModel().apply {
//            addAll(
//                listOf(
//                    AlbumsModelItemModel
//                id= 1,
//                name= "Leanne Graham",
//                username= "Bret",
//                email= "Sincere@april.biz",
//                address = AddressModel(
//                    street = "Kulas Light",
//                    suite ="Apt. 556",
//                    city= "Gwenborough",
//                    zipcode= "92998-3874",
//                    geo= GeoModel(
//                        lat= "-37.3159",
//                        lng= "81.1496")
//                ),
//                phone ="1-770-736-8031 x56442",
//                website= "hildegard.org",
//                company= CompanyModel(
//                    name= "Romaguera-Crona",
//                    catchPhrase= "Multi-layered client-server neural-net",
//                    bs="harness real-time e-markets"
//                )
//            ),
//            UsersModelItemModel(
//                id= 2,
//                name= "Ervin Howell",
//                username= "Antonette",
//                email= "Shanna@melissa.tv",
//                address = AddressModel(
//                    street = "Victor Plains",
//                    suite ="Suite 879",
//                    city= "Wisokyburgh",
//                    zipcode= "90566-7771",
//                    geo= GeoModel(
//                        lat= "-43.9509",
//                        lng= "-34.4618")
//                ),
//                phone ="010-692-6593 x09125",
//                website= "anastasia.net",
//                company= CompanyModel(
//                    name= "Deckow-Crist",
//                    catchPhrase= "Proactive didactic contingency",
//                    bs="synergize scalable supply-chains"
//                )
//            )
//
//        )
//        val users_sample = UsersModel()
//        users_sample.addAll(users)
//        Mockito.`when`(usersApi.getUsers()).thenReturn(users_sample)
//
//        val repositoryTest= RepositoryImpl(usersApi)
//        val result= repositoryTest.getUsers()
//        assertEquals(2,result.size)
//    }
//
//    @Test
//    fun testGetPhotos_expectedUsersList()= runTest {
//        val users = listOf(
//            UsersModelItemModel(
//                id= 1,
//                name= "Leanne Graham",
//                username= "Bret",
//                email= "Sincere@april.biz",
//                address = AddressModel(
//                    street = "Kulas Light",
//                    suite ="Apt. 556",
//                    city= "Gwenborough",
//                    zipcode= "92998-3874",
//                    geo= GeoModel(
//                        lat= "-37.3159",
//                        lng= "81.1496")
//                ),
//                phone ="1-770-736-8031 x56442",
//                website= "hildegard.org",
//                company= CompanyModel(
//                    name= "Romaguera-Crona",
//                    catchPhrase= "Multi-layered client-server neural-net",
//                    bs="harness real-time e-markets"
//                )
//            ),
//            UsersModelItemModel(
//                id= 2,
//                name= "Ervin Howell",
//                username= "Antonette",
//                email= "Shanna@melissa.tv",
//                address = AddressModel(
//                    street = "Victor Plains",
//                    suite ="Suite 879",
//                    city= "Wisokyburgh",
//                    zipcode= "90566-7771",
//                    geo= GeoModel(
//                        lat= "-43.9509",
//                        lng= "-34.4618")
//                ),
//                phone ="010-692-6593 x09125",
//                website= "anastasia.net",
//                company= CompanyModel(
//                    name= "Deckow-Crist",
//                    catchPhrase= "Proactive didactic contingency",
//                    bs="synergize scalable supply-chains"
//                )
//            )
//
//        )
//        val users_sample = UsersModel()
//        users_sample.addAll(users)
//        Mockito.`when`(usersApi.getUsers()).thenReturn(users_sample)
//
//        val repositoryTest= RepositoryImpl(usersApi)
//        val result= repositoryTest.getUsers()
//        assertEquals(2,result.size)
//    }
//
//    @Test
//    fun testGetComments_expectedUsersList()= runTest {
//        val users = listOf(
//            UsersModelItemModel(
//                id= 1,
//                name= "Leanne Graham",
//                username= "Bret",
//                email= "Sincere@april.biz",
//                address = AddressModel(
//                    street = "Kulas Light",
//                    suite ="Apt. 556",
//                    city= "Gwenborough",
//                    zipcode= "92998-3874",
//                    geo= GeoModel(
//                        lat= "-37.3159",
//                        lng= "81.1496")
//                ),
//                phone ="1-770-736-8031 x56442",
//                website= "hildegard.org",
//                company= CompanyModel(
//                    name= "Romaguera-Crona",
//                    catchPhrase= "Multi-layered client-server neural-net",
//                    bs="harness real-time e-markets"
//                )
//            ),
//            UsersModelItemModel(
//                id= 2,
//                name= "Ervin Howell",
//                username= "Antonette",
//                email= "Shanna@melissa.tv",
//                address = AddressModel(
//                    street = "Victor Plains",
//                    suite ="Suite 879",
//                    city= "Wisokyburgh",
//                    zipcode= "90566-7771",
//                    geo= GeoModel(
//                        lat= "-43.9509",
//                        lng= "-34.4618")
//                ),
//                phone ="010-692-6593 x09125",
//                website= "anastasia.net",
//                company= CompanyModel(
//                    name= "Deckow-Crist",
//                    catchPhrase= "Proactive didactic contingency",
//                    bs="synergize scalable supply-chains"
//                )
//            )
//
//        )
//        val users_sample = UsersModel()
//        users_sample.addAll(users)
//        Mockito.`when`(usersApi.getUsers()).thenReturn(users_sample)
//
//        val repositoryTest= RepositoryImpl(usersApi)
//        val result= repositoryTest.getUsers()
//        assertEquals(2,result.size)
//    }
//
//    @Test
//    fun testGetTodos_expectedUsersList()= runTest {
//        val users = listOf(
//            UsersModelItemModel(
//                id= 1,
//                name= "Leanne Graham",
//                username= "Bret",
//                email= "Sincere@april.biz",
//                address = AddressModel(
//                    street = "Kulas Light",
//                    suite ="Apt. 556",
//                    city= "Gwenborough",
//                    zipcode= "92998-3874",
//                    geo= GeoModel(
//                        lat= "-37.3159",
//                        lng= "81.1496")
//                ),
//                phone ="1-770-736-8031 x56442",
//                website= "hildegard.org",
//                company= CompanyModel(
//                    name= "Romaguera-Crona",
//                    catchPhrase= "Multi-layered client-server neural-net",
//                    bs="harness real-time e-markets"
//                )
//            ),
//            UsersModelItemModel(
//                id= 2,
//                name= "Ervin Howell",
//                username= "Antonette",
//                email= "Shanna@melissa.tv",
//                address = AddressModel(
//                    street = "Victor Plains",
//                    suite ="Suite 879",
//                    city= "Wisokyburgh",
//                    zipcode= "90566-7771",
//                    geo= GeoModel(
//                        lat= "-43.9509",
//                        lng= "-34.4618")
//                ),
//                phone ="010-692-6593 x09125",
//                website= "anastasia.net",
//                company= CompanyModel(
//                    name= "Deckow-Crist",
//                    catchPhrase= "Proactive didactic contingency",
//                    bs="synergize scalable supply-chains"
//                )
//            )
//
//        )
//        val users_sample = UsersModel()
//        users_sample.addAll(users)
//        Mockito.`when`(usersApi.getUsers()).thenReturn(users_sample)
//
//        val repositoryTest= RepositoryImpl(usersApi)
//        val result= repositoryTest.getUsers()
//        assertEquals(2,result.size)
//    }
//
//
}