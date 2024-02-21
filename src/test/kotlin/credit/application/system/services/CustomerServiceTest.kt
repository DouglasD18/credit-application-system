package credit.application.system.services

import credit.application.system.entities.Address
import credit.application.system.entities.Customer
import credit.application.system.exceptions.BusinessException
import credit.application.system.repositories.CustomerRepository
import credit.application.system.services.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    lateinit var customerRepository: CustomerRepository
    @InjectMockKs
    lateinit var customerService: CustomerService

    @Test
    fun `should create customer`(){
        //given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        //when
        val customer: Customer = customerService.save(fakeCustomer)

        //then
        Assertions.assertThat(customer).isNotNull
        Assertions.assertThat(customer).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)

        //when
        val customer: Customer = customerService.findById(fakeId)

        //then
        Assertions.assertThat(customer).isNotNull
        Assertions.assertThat(customer).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(customer).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should not find customer by invalid id and throw an BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()

        //when
        customerService.findById(fakeId)

        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    private fun buildCustomer(
        firstName: String = "Douglinhas",
        lastName: String = "Aguiar",
        cpf: String = "28475934625",
        email: String = "doga@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Rua Needless",
        income: BigDecimal = BigDecimal.valueOf(3500.0),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )

}