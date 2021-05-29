package mx.com.mercadolibre.hansolo.domain.model

import java.math.BigDecimal
import java.sql.Timestamp
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "satellite")
data class Satellite (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(updatable = false, insertable = false, unique = true, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
        var code: UUID? = null,

        var type: String? = null,

        var subType: String? = null,

        var origin: String? = null,

        var amount: String? = null,

        var createdAt: Timestamp? = null,

        var updatedAt: Timestamp? = null,

        var transactionId: String? = null,

        @Column(name = "title_key_i18n")
        var titleKeyI18n: String? = null,

        @Column(name = "description_key_i18n")
        var descriptionKeyI18n: String? = null,

        var image: String? = null,

        var icon: String? = null,

        var cashbackIcon: String? = null,

        var name: String? = null,

        var textColor: String? = null,

        var backgroundColor: String? = null,

        var externalReferenceId: String? = null,

        var userId: String? = null,

        var accountId: String? = null,

        var status: String? = null,

        var countryCode: String? = null,

        var userName: String? = null,

        var action: String? = null,

        var actionDetail: String? = null,

        var movementImage: String? = null,

        var comision: BigDecimal? = null,

        var referenceLastDigits: String? = null,

        @Column(name = "other_part_of_p2p_id")
        var otherPartOfP2pId: String? = null,

        val isSender: Boolean? = null,

        val otp: String? = null,

        var integrationReference: String? = null,

        var phone: String? = null,

        var reason: String? = null,

        var detailDescription: String? = null,

        var bankAccount: String? = null,

        var modelId: String? = null,

        val franchise: String? = null,

        var operator : String? = null,

        var version: String? = null,

        var amountDouble: Double? = null

) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as Satellite

                if (id != other.id) return false
                if (code != other.code) return false
                if (type != other.type) return false
                if (subType != other.subType) return false
                if (origin != other.origin) return false
                if (amount != other.amount) return false
                if (transactionId != other.transactionId) return false
                if (titleKeyI18n != other.titleKeyI18n) return false
                if (descriptionKeyI18n != other.descriptionKeyI18n) return false
                if (image != other.image) return false
                if (icon != other.icon) return false
                if (cashbackIcon != other.cashbackIcon) return false
                if (name != other.name) return false
                if (textColor != other.textColor) return false
                if (backgroundColor != other.backgroundColor) return false
                if (externalReferenceId != other.externalReferenceId) return false
                if (userId != other.userId) return false
                if (accountId != other.accountId) return false
                if (status != other.status) return false
                if (countryCode != other.countryCode) return false
                if (userName != other.userName) return false
                if (action != other.action) return false
                if (actionDetail != other.actionDetail) return false
                if (movementImage != other.movementImage) return false
                if (comision != other.comision) return false
                if (referenceLastDigits != other.referenceLastDigits) return false
                if (otherPartOfP2pId != other.otherPartOfP2pId) return false
                if (isSender != other.isSender) return false
                if (otp != other.otp) return false
                if (integrationReference != other.integrationReference) return false
                if (phone != other.phone) return false
                if (reason != other.reason) return false
                if (bankAccount != other.bankAccount) return false
                if (modelId != other.modelId) return false
                if (franchise != other.franchise) return false
                if (operator != other.operator) return false
                if (amountDouble != other.amountDouble) return false
                return true
        }

}