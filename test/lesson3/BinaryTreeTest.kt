package lesson3

import org.junit.jupiter.api.Tag
import kotlin.test.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BinaryTreeTest {
    private fun testAdd(create: () -> CheckableSortedSet<Int>) {
        val tree = create()
        tree.add(10)
        tree.add(5)
        tree.add(7)
        tree.add(10)
        assertEquals(3, tree.size)
        assertTrue(tree.contains(5))
        tree.add(3)
        tree.add(1)
        tree.add(3)
        tree.add(4)
        assertEquals(6, tree.size)
        assertFalse(tree.contains(8))
        tree.add(8)
        tree.add(15)
        tree.add(15)
        tree.add(20)
        assertEquals(9, tree.size)
        assertTrue(tree.contains(8))
        assertTrue(tree.checkInvariant())
        assertEquals(1, tree.first())
        assertEquals(20, tree.last())
    }

    @Test
    @Tag("Example")
    fun testAddKotlin() {
        testAdd { createKotlinTree() }
    }

    @Test
    @Tag("Example")
    fun testAddJava() {
        testAdd { createJavaTree() }
    }

    private fun <T : Comparable<T>> createJavaTree(): CheckableSortedSet<T> = BinaryTree()

    private fun <T : Comparable<T>> createKotlinTree(): CheckableSortedSet<T> = KtBinaryTree()

    private fun testRemove(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
            }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val toRemove = list[random.nextInt(list.size)]
            treeSet.remove(toRemove)
            binarySet.remove(toRemove)
            println("Removing $toRemove from $list")
            assertEquals<SortedSet<*>>(treeSet, binarySet, "After removal of $toRemove from $list")
            assertEquals(treeSet.size, binarySet.size)
            for (element in list) {
                val inn = element != toRemove
                assertEquals(inn, element in binarySet,
                        "$element should be ${if (inn) "in" else "not in"} tree")
            }
            assertTrue(binarySet.checkInvariant())
        }

        val tree1 = create()
        tree1.add(51)
        tree1.add(23)
        tree1.add(54)
        tree1.add(98)
        tree1.add(62)
        tree1.add(67)
        tree1.remove(51)
        assertEquals(5, tree1.size)
        assertFalse(tree1.contains(51))

        val tree2 = create()
        tree2.add(51)
        tree2.add(23)
        tree2.add(54)
        tree2.add(98)
        tree2.add(62)
        tree2.add(67)
        tree2.remove(98)
        assertEquals(5, tree2.size)
        assertFalse(tree2.contains(98))
    }

    @Test
    @Tag("Normal")
    fun testRemoveKotlin() {
        testRemove { createKotlinTree() }
    }

    @Test
    @Tag("Normal")
    fun testRemoveJava() {
        testRemove { createJavaTree() }
    }

    private fun testIterator(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
            }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val treeIt = treeSet.iterator()
            val binaryIt = binarySet.iterator()
            println("Traversing $list")
            while (treeIt.hasNext()) {
                assertEquals(treeIt.next(), binaryIt.next())
            }
        }

        val list = mutableListOf<Int>()
        list.add(43)
        list.add(54)
        list.add(23)
        list.add(90)
        list.add(73)
        list.add(56)
        val treeSet = TreeSet<Int>()
        val binarySet = create()
        for (elem in list) {
            treeSet += elem
            binarySet += elem
        }
        val treeIterator = treeSet.iterator()
        val binaryIterator = binarySet.iterator()
        while (treeIterator.hasNext()) {
            assertEquals(treeIterator.next(), binaryIterator.next())
        }

        val list1 = mutableListOf<Int>()
        list1.add(1)
        list1.add(1)
        list1.add(1)
        list1.add(1)
        list1.add(1)
        list1.add(1)
        val treeSet1 = TreeSet<Int>()
        val binarySet1 = create()
        for (elem in list) {
            treeSet1 += elem
            binarySet1 += elem
        }
        val treeIterator1 = treeSet1.iterator()
        val binaryIterator1 = binarySet1.iterator()
        while (treeIterator1.hasNext()) {
            assertEquals(treeIterator1.next(), binaryIterator1.next())
        }
    }

    @Test
    @Tag("Normal")
    fun testIteratorKotlin() {
        testIterator { createKotlinTree() }
    }

    @Test
    @Tag("Normal")
    fun testIteratorJava() {
        testIterator { createJavaTree() }
    }

    private fun testIteratorRemove(create: () -> CheckableSortedSet<Int>) {
        val random = Random()
        for (iteration in 1..100) {
            val list = mutableListOf<Int>()
            for (i in 1..20) {
                list.add(random.nextInt(100))
            }
            val treeSet = TreeSet<Int>()
            val binarySet = create()
            for (element in list) {
                treeSet += element
                binarySet += element
            }
            val toRemove = list[random.nextInt(list.size)]
            treeSet.remove(toRemove)
            println("Removing $toRemove from $list")
            val iterator = binarySet.iterator()
            while (iterator.hasNext()) {
                val element = iterator.next()
                print("$element ")
                if (element == toRemove) {
                    iterator.remove()
                }
            }
            println()
            assertEquals<SortedSet<*>>(treeSet, binarySet, "After removal of $toRemove from $list")
            assertEquals(treeSet.size, binarySet.size)
            for (element in list) {
                val inn = element != toRemove
                assertEquals(inn, element in binarySet,
                        "$element should be ${if (inn) "in" else "not in"} tree")
            }
            assertTrue(binarySet.checkInvariant())
        }
    }

    @Test
    @Tag("Hard")
    fun testIteratorRemoveKotlin() {
        testIteratorRemove { createKotlinTree() }
    }

    @Test
    @Tag("Hard")
    fun testIteratorRemoveJava() {
        testIteratorRemove { createJavaTree() }
    }
}
