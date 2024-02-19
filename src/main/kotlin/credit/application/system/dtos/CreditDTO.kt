package credit.application.system.dtos

import credit.application.system.entities.Credit
import credit.application.system.entities.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
    @field:NotNull val creditValue: BigDecimal,
    @field:Future val dayFirstInstallment: LocalDate,
    @field:Size(min = 1, max = 48) val numberOfInstallments: Int,
    @field:NotNull val customerId: Long
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )

}
