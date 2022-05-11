package br.com.m2silva.domain.usecase

import br.com.m2silva.CustomerHelper
import br.com.m2silva.network.interceptor.ConnectivityException
import br.com.m2silva.domain.repository.CustomerRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CustomerUseCaseTest {

    private lateinit var useCase: CustomerUseCase
    private val repository: CustomerRepository = mockk()

    @Before
    fun setUp() {
        useCase = CustomerUseCase(repository = repository)
    }

    @Test
    fun `when call usecase and success should return result success with customer list`() =
        runTest(UnconfinedTestDispatcher()) {
            // GIVEN
            val expected = Result.success(CustomerHelper.customerItems)
            coEvery { repository.fetchCustomers() } returns expected

            // WHEN
            val result = useCase.invoke()

            // THEN
            Assert.assertEquals(expected, result)
        }

    @Test
    fun `when call usecase and fail should return result failure with exception and message`() =
        runTest(UnconfinedTestDispatcher()) {
            // GIVEN
            val message = "No data"
            coEvery { repository.fetchCustomers() }.throws(ConnectivityException(message))

            // WHEN
            var result = Exception()
            try {
                useCase.invoke()
            } catch (e: Exception) {
                result = e
            }

            // THEN
            Assert.assertEquals(message, result.message)
        }
}