package com.fasilkom.tutorial2

import org.junit.Test
import org.junit.Before
import com.fasilkom.tutorial2.models.Biodata
import org.junit.Assert.*

class BiodataUnitTest {
    private lateinit var biodata: Biodata

    @Before
    fun setUp() {
        biodata = Biodata("Mock", "1234", "mock@mock.com")
    }

    @Test
    fun biodata_isInstantiated() {
        assertNotNull(biodata)
        assertEquals(biodata.name, "Mock")
        assertEquals(biodata.npm, "1234")
        assertEquals(biodata.email, "mock@mock.com")
    }
}