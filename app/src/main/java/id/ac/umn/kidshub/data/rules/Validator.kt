package id.ac.umn.kidshub.data.rules

object Validator {
    fun validateFirstName(firstName: String): ValidationResult {
        return ValidationResult(
            (firstName.isNotEmpty() && firstName.length >= 3)
        )
    }

    fun validateLastName(lastName: String): ValidationResult {
        return ValidationResult(
            (lastName.isNotEmpty() && lastName.length >= 3)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (email.isNotEmpty() && email.contains("@"))
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (password.isNotEmpty() && password.length >= 6)
        )
    }
}

data class ValidationResult(
    val status :Boolean = false
)