package credit.application.system.entities

import credit.application.system.enummerations.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "credits")
data class Credit(
    @Column(nullable = false, unique = true)
    var creditCode: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val creditValue: BigDecimal,

    @Column(nullable = false)
    val dayFirstInstallment: LocalDate,

    @Column(nullable = false)
    val numberOfInstallments: Int,

    @Enumerated
    val status: Status = Status.IN_PROGRESS,

    @ManyToOne
    var customer: Customer? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)
