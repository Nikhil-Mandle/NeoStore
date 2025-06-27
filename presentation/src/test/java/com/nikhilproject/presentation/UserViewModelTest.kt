package com.nikhilproject.presentation

import app.cash.turbine.test
import com.nikhilproject.domain.model.LogInRequest
import com.nikhilproject.domain.model.User
import com.nikhilproject.domain.usecase.ChangePasswordUseCase
import com.nikhilproject.domain.usecase.LoginUserUseCase
import com.nikhilproject.domain.usecase.RegisterUserUseCase
import com.nikhilproject.domain.usecase.UpdateProfileUseCase
import com.nikhilproject.presentation.viewmodel.UserViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {
    private lateinit var viewModel: UserViewModel

    private val loginUserUseCase: LoginUserUseCase = mockk()
    private val registerUserUseCase: RegisterUserUseCase = mockk()
    private val changePasswordUseCase: ChangePasswordUseCase = mockk()
    private val updateProfileUseCase: UpdateProfileUseCase = mockk()
    private val sharedPrefs: SharedPreferenceManager = mockk(relaxed = true)

    private val testDispatcher = UnconfinedTestDispatcher()

    // Initial setup before any test case runs, It ensures a fresh start for every test case.
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserViewModel(
            registerUserUseCase,
            loginUserUseCase,
            changePasswordUseCase,
            updateProfileUseCase,
            sharedPrefs
        )
    }

    //    clean up after each test runs, resetting any shared state or configuration
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    // ------------------ Login Success ------------------
    @Test
    fun `login success updates uiState to Success`() = runTest {
        val request = LogInRequest("nikhil1@test.com", "Nikhil1234")
        val user = sampleUser()

        coEvery { loginUserUseCase(request) } returns user

        viewModel.login(request)

        viewModel.uiState.test {
            val success = awaitItem()
            assertTrue("login success passed successfully.",  success is UiState.Success && success.data == user)

            cancelAndIgnoreRemainingEvents()
        }

        verify { sharedPrefs.addAccessToken(user.accessToken) }
        verify { sharedPrefs.saveUserEmail(user.email) }
    }

    // ------------------ Login Failure ------------------
    @Test
    fun `login failure updates uiState to Error`() = runTest {
        val request = LogInRequest("test@mail.com", "wrongpass")
        val errorMsg = "Invalid credentials"

        coEvery { loginUserUseCase(request) } throws Exception(errorMsg)

        viewModel.login(request)

        viewModel.uiState.test {
            val error = awaitItem()
            assertTrue("login failure passed successfully.", error is UiState.Error && error.message == errorMsg)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `login with blank username or password should not trigger login`() = runTest {
        val blankRequest = LogInRequest("", "")

        viewModel.login(blankRequest)

        viewModel.uiState.test {
            val state = awaitItem()
            assert(state is UiState.Error)
            println("Error state emitted for blank input: ${ (state as UiState.Error).message }")
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun sampleUser() = User(
        id = 1,
        roleId = 2,
        firstName = "TestUser",
        lastName = "Test",
        email = "test@gmail.com",
        username = "Test1234",
        profilePic = null,
        countryId = 1,
        gender = "Male",
        phoneNo = 1234567890,
        dob = "1990-01-01",
        isActive = true,
        created = "2023-01-01",
        modified = "2023-01-01",
        accessToken = "token_abc"
    )

}