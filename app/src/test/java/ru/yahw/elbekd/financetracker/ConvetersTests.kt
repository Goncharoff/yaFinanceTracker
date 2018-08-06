package ru.yahw.elbekd.financetracker

import org.junit.Test
import org.assertj.core.api.Assertions.*
import ru.yahw.elbekd.financetracker.data.db.Convecters
import java.math.BigDecimal

class ConvetersTests{
    val convecters : Convecters = Convecters()
    @Test
    fun standartValuesTest(){
        assertThat(0L).isEqualTo(convecters.fromBigDecimal(BigDecimal.ZERO))
        assertThat(100L).isEqualTo(convecters.fromBigDecimal(BigDecimal.valueOf(100)))

        assertThat(BigDecimal.ZERO).isEqualTo(convecters.fromLongToBigDecimal(0))
        assertThat(BigDecimal(123)).isEqualTo(convecters.fromLongToBigDecimal(123))
    }
}