package ru.yahw.elbekd.financetracker

import org.junit.Test
import org.assertj.core.api.Assertions.*
import ru.yahw.elbekd.financetracker.utils.makeNegative
import java.math.BigDecimal


class UtilsTest{
    @Test
    fun negativeCheck(){
        assertThat(BigDecimal.ZERO).isEqualTo(BigDecimal(0).makeNegative(true))
        assertThat(BigDecimal(-1)).isEqualTo(BigDecimal(-1).makeNegative(false))

    }
}