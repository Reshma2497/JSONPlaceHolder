package com.example.jsonplaceholder.ui.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jsonplaceholder.data.model.users.*
import com.example.jsonplaceholder.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UsersViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    @Before
    fun setUP() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }


    @Test
    fun testGetUsers_emptyResponse() = runTest {
        val users = UsersModel()

        // Return the mock ProductsModel object from the repository
        Mockito.`when`(repository.getUsers()).thenReturn(users)

        val viewModel = UsersViewModel(repository)
        viewModel.getUsers()

        // Call the function that uses the repository to get the products

        testDispatcher.scheduler.advanceUntilIdle()

        // Check that the products LiveData contains the expected result
        val usersView = viewModel.users.value
        assertEquals(0, usersView.size)
    }


@Test
fun `getUsers should post Success with valid response`() = runTest {
    // Arrange
    val users = listOf(
        UsersModelItemModel(
                id= 1,
                name= "Leanne Graham",
                username= "Bret",
                email= "Sincere@april.biz",
            address = AddressModel(
                street = "Kulas Light",
                suite ="Apt. 556",
                city= "Gwenborough",
                zipcode= "92998-3874",
            geo= GeoModel(
                lat= "-37.3159",
            lng= "81.1496")),
            phone ="1-770-736-8031 x56442",
            website= "hildegard.org",
                company=CompanyModel(
                    name= "Romaguera-Crona",
                catchPhrase= "Multi-layered client-server neural-net",
                bs="harness real-time e-markets"
            )),
        UsersModelItemModel(
            id= 2,
            name= "Ervin Howell",
            username= "Antonette",
            email= "Shanna@melissa.tv",
            address = AddressModel(
                street = "Victor Plains",
                suite ="Suite 879",
                city= "Wisokyburgh",
                zipcode= "90566-7771",
                geo= GeoModel(
                    lat= "-43.9509",
                    lng= "-34.4618")),
            phone ="010-692-6593 x09125",
            website= "anastasia.net",
            company=CompanyModel(
                name= "Deckow-Crist",
                catchPhrase= "Proactive didactic contingency",
                bs="synergize scalable supply-chains"
            )
        )
    )
    val users_sample = UsersModel()
    users_sample.addAll(users)

    Mockito.`when`(repository.getUsers()).thenReturn(users_sample)

    // Return the mock ProductsModel object from the repository
    val viewModel=UsersViewModel(repository)
    viewModel.getUsers()
    // Call the function that uses the repository to get the products

    testDispatcher.scheduler.advanceUntilIdle()


    // Check that the products LiveData contains the expected result
    val usersView = viewModel.users.value
    assertEquals(2, usersView.size)
    }
}

