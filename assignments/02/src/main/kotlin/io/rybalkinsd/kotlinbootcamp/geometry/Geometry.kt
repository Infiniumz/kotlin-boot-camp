package io.rybalkinsd.kotlinbootcamp.geometry

import kotlin.math.*
/**
 * Entity that can physically intersect, like flame and player
 */
interface Collider {
    fun isColliding(other: Collider): Boolean
}

/**
 * 2D point with integer coordinates
 */
data class Point(var x: Int, var y: Int) : Collider {
    override fun isColliding(other: Collider): Boolean {
        if (other is Bar) {
            return other.isContains(this)
        }
        if (other is Point) {
            return equals(other)
        }
        return false
    }
}

/**
 * Bar is a rectangle, which borders are parallel to coordinate axis
 * Like selection bar in desktop, this bar is defined by two opposite corners
 * Bar is not oriented
 * (It does not matter, which opposite corners you choose to define bar)
 */
data class Bar(var firstCornerX: Int, var firstCornerY: Int, var secondCornerX: Int, var secondCornerY: Int) : Collider {
    var leftx: Int = min(firstCornerX, secondCornerX)
    var lefty: Int = min(firstCornerY, secondCornerY)
    var rightx: Int = max(firstCornerX, secondCornerX)
    var righty: Int = max(firstCornerY, secondCornerY)

    fun isContains(other: Point): Boolean = (other.x >= leftx) && (other.x <= rightx) &&
            (other.y >= lefty) && (other.y <= righty)

    override fun isColliding(other: Collider): Boolean {
        if (other is Point) {
            return isContains(other)
        }
        if (other is Bar) {
            return isContains(Point(leftx, lefty)) || isContains(Point(rightx, righty)) ||
                    isContains(Point(leftx, righty)) || isContains(Point(rightx, lefty)) || other.isColliding(this)
        }
        return false
    }

    override fun equals(other: Any?): Boolean {
        if (other is Bar) {
            return ((leftx == other.leftx) && (rightx == other.rightx) &&
                    (lefty == other.lefty) && (righty == other.righty))
        }
        return false
    }
}