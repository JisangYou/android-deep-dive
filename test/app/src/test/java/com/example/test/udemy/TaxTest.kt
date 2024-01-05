package com.example.test.udemy

import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class TaxTest {
    private lateinit var tax: Tax

    @Before
    fun setUp() {
        tax = Tax()
    }

    @After
    fun teardown() {

    }

    @Test
    fun calculateTax() {
        val netTax = tax.calculateTax(100.0, 0.1)
        assertThat(netTax).isEqualTo(10)
    }

    @Test
    fun calculateIncome() {
        val netIncome = tax.calculateIncome(200.0, 0.2)
        assertThat(netIncome).isEqualTo(160)
    }
}