package credit.application.system.dtos

import credit.application.system.entities.Address
import credit.application.system.entities.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDTO(
    @field:NotEmpty val firstName: String,
    @field:NotEmpty val lastName: String,
    @field:NotEmpty
    @field:CPF
    val cpf: String,
    @field:NotNull
    @field:Positive
    val income: BigDecimal,
    @field:NotEmpty
    @field:Email
    val email: String,
    @field:NotEmpty val password: String,
    @field:NotEmpty val zipCode: String,
    @field:NotEmpty val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        email = this.email,
        income = this.income,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )

}
