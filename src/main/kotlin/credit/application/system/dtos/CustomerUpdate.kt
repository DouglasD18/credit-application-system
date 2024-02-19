package credit.application.system.dtos

import credit.application.system.entities.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import kotlin.math.min

data class CustomerUpdate(
    @field:NotEmpty val firstName: String,
    @field:NotEmpty val lastName: String,
    @field:NotNull
    @field:Positive
    val income: BigDecimal,
    @field:NotEmpty
    @field:Size(min = 8, max = 8)
    val zipCode: String,
    @field:NotEmpty val street: String
) {
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}
