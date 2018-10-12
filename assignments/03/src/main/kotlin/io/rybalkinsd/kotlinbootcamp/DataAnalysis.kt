package io.rybalkinsd.kotlinbootcamp

/**
 * Here are Raw profiles to analyse
 */

val rawProfiles = listOf(
        RawProfile("""
            firstName=alice,
            age=16,
            source=facebook
            """.trimIndent()
        ),
        RawProfile("""
            firstName=dent,
            lastName=kent,
            age=88,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            firstName=alla,
            lastName=OloOlooLoasla,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bella,
            age=36,
            source=vk
            """.trimIndent()
        ),
        RawProfile("""
            firstName=angel,
            age=I will not tell you =),
            source=facebook
            """.trimIndent()
        ),

        RawProfile("""
            lastName=carol,
            source=vk,
            age=49
            """.trimIndent()
        ),
        RawProfile("""
            firstName=bob,
            lastName=John,
            age=47,
            source=linkedin
            """.trimIndent()
        ),
        RawProfile("""
            lastName=kent,
            firstName=dent,
            age=88,
            source=facebook
            """.trimIndent()
        )
)
class RawProfile(val rawData: String) {
    override fun toString() : String {
       return this.rawData
    }
}

enum class DataSource {
    FACEBOOK,
    VK,
    LINKEDIN
}

sealed class Profile(
    var id: Long,
    var firstName: String? = null,
    var lastName: String? = null,
    var age: Int? = null,
    var dataSource: DataSource
)

/**
 * Task #1
 * Declare classes for all data sources
 */
class FacebookProfile(id: Long) : Profile(dataSource = DataSource.FACEBOOK, id = id) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Profile -> firstName == other.firstName && lastName == other.lastName && age == other.age
            else -> false
        }
    }
}

class LinkedInProfile(id: Long) : Profile(dataSource = DataSource.FACEBOOK, id = id)

class VkProfile(id: Long) : Profile(dataSource = DataSource.FACEBOOK, id = id)

fun ParseToSet(e: List<RawProfile>) : Set<Profile> {
    var st = mutableSetOf<Profile>()
    var id = mutableListOf(0L, 0L, 0L)
    var firstName : String? = null
    var lastName : String? = null
    var age : Int? = null
    var dataSourcet : String = ""
    e.forEach {
        val str = it.toString().replace("\n", "").split(",")
        str.forEach {
            val lpart = it.substringBefore('=')
            val rpart = it.substringAfter('=')
            when (lpart) {
                "firstName" -> firstName = rpart
                "lastName" -> lastName = rpart
                "age" -> age = rpart.toIntOrNull()
                "source" -> dataSourcet = rpart
            }
            }
        when(dataSourcet) {
            "facebook" -> {
                st.add(FacebookProfile(id[0]).apply {
                    this.dataSource = DataSource.FACEBOOK
                    this.firstName = firstName
                    this.lastName = lastName
                    this.age = age
                })
                id[0]++
            }
            "linkedin" -> {
                st.add(LinkedInProfile(id[1]).apply {
                    this.dataSource = DataSource.LINKEDIN
                    this.firstName = firstName
                    this.lastName = lastName
                    this.age = age
                })
                id[1]++
            }
            else -> {
                st.add(VkProfile(id[2]).apply {
                    this.dataSource = DataSource.VK
                    this.firstName = firstName
                    this.lastName = lastName
                    this.age = age
                })
                id[2]++
            }
        }.also {
            firstName = null
            lastName = null
            age  = null
            dataSourcet = ""
        }
    }
    return st.toSet()
}

var ProfileSet: Set<Profile> = ParseToSet(rawProfiles)
/**
 * Task #2
 * Find the average age for each datasource (for profiles that has age)
 *
 * TODO
 */

fun avg(e: Set<Profile>) : Map<DataSource, Double> {
    var mp = mutableMapOf(Pair(DataSource.FACEBOOK, 0.0),
            Pair(DataSource.LINKEDIN, 0.0), Pair(DataSource.VK, 0.0))
    var lst = mutableListOf(0, 0, 0)
    e.forEach {
        when (it.dataSource) {
            DataSource.FACEBOOK -> {
                if (it.age != null) {
                    mp[DataSource.FACEBOOK] = it.age!!.toDouble() + mp[DataSource.FACEBOOK]!!
                    lst[0]++
                }
            }
            DataSource.LINKEDIN -> {
                if (it.age != null) {
                    mp[DataSource.LINKEDIN] = it.age!!.toDouble() + mp[DataSource.LINKEDIN]!!
                    lst[1]++
                }
            }
            DataSource.VK -> {
                if (it.age != null) {
                    mp[DataSource.VK] = it.age!!.toDouble() + mp[DataSource.VK]!!
                    lst[2]++
                }
            }
        }
    }
    mp[DataSource.FACEBOOK] = mp[DataSource.FACEBOOK]!! / lst[0].toDouble()
    mp[DataSource.LINKEDIN] = mp[DataSource.LINKEDIN]!! / lst[1].toDouble()
    mp[DataSource.VK] = mp[DataSource.VK]!! / lst[2].toDouble()
    return mp.toMap()
}

var avgAge = avg(ProfileSet)
/**
 * Group all user ids together with all profiles of this user.
 * We can assume users equality by : firstName & lastName & age
 *
 * TODO
 */

fun toGroup(e: Set<Profile>) : Map<Long, List<Profile>> {
    var mp = mutableMapOf<Long, List<Profile>>()
    var ind = 0L
    val tmp2 = e.toMutableList()
    while(tmp2.isNotEmpty()) {
        val tmp = tmp2[0]
        val ans = tmp2.filter{ it == tmp }
        ans.forEach {
            mp.put(ind, ans)
        }
        ans.forEach {
            tmp2.remove(it)
        }
        ind++
    }
    return mp.toMap()
}

val groupedProfiles: Map<Long, List<Profile>> = toGroup(ProfileSet)

