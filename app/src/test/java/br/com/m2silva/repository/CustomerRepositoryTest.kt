package br.com.m2silva.repository

import br.com.m2silva.CustomerHelper
import br.com.m2silva.data.api.CustomerAPI
import br.com.m2silva.domain.repository.CustomerRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CustomerRepositoryTest {

    private lateinit var repository: CustomerRepository

    private val service: CustomerAPI = mockk(relaxed = true)

    @Before
    fun setUp() {
        repository = CustomerRepositoryImpl(service = service)
    }

    @Test
    fun `fetch customers when called and success should return result success with list customer`() =
        runTest {
            // GIVEN
            val responseAPI = Response.success(CustomerHelper.customerResponse)
            coEvery { service.customers() } returns responseAPI

            // WHEN
            val result = repository.fetchCustomers()

            // THEN
            val expected = Result.success(CustomerHelper.customerItems)
            Assert.assertEquals(expected, result)
        }
}