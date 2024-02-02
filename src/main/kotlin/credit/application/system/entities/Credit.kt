package credit.application.system.entities

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class Credit(
    val creditCode: UUID = UUID.randomUUID(),
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallments: Int,
    val status: Status = Status.IN_PROGRESS,
    val costumer: Costumer? = null,
    val id: Long? = null
)
