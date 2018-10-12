package io.rybalkinsd.kotlinbootcamp

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class DataAnalysisTest {

    @Test
    fun `check avg age`() {
        assertTrue(avgAge.isNotEmpty())
    }

    @Test
    fun `check grouped profiles`() {
        assertTrue(groupedProfiles.isNotEmpty())
    }

    @Test
    fun `check avg age2`() {
        val tmptest = listOf(
                RawProfile("""
            firstName=alice,
            age=22,
            source=facebook
            """.trimIndent()
                ),
                RawProfile("""
            firstName=dent,
            lastName=kent,
            age=40,
            source=facebook
            """.trimIndent()
                ),
                RawProfile("""
            firstName=bella,
            age=30,
            source=vk
            """.trimIndent()
                ),

                RawProfile("""
            lastName=carol,
            source=vk,
            age=50
            """.trimIndent()
                ),
                RawProfile("""
            firstName=bob,
            lastName=John,
            age=50,
            source=linkedin
            """.trimIndent()
                ),
                RawProfile("""
            lastName=kent,
            firstName=dent,
            age=10,
            source=linkedin
            """.trimIndent()
                ))
        val p = avg(ParseToSet(tmptest))
        assertEquals(40.0, p[DataSource.VK])
        assertEquals(30.0, p[DataSource.LINKEDIN])
        assertEquals(31.0, p[DataSource.FACEBOOK])
    }
}
