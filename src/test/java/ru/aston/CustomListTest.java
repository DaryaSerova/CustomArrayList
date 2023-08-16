package ru.aston;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.aston.util.Fixture;
import ru.aston.util.TestObject;
import ru.aston.util.TestObjectComparator;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.aston.util.QuickSortUtil.quickSort;

public class CustomListTest {

    @Test
    public void shouldAddElementToList() {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        //then
        assertEquals(arr.size(), 3);
        assertEquals(arr.get(0), obj1);
        assertEquals(arr.get(1), obj2);
        assertEquals(arr.get(2), obj3);
    }

    @Test
    public void shouldAddElementToIndexList() {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        var obj4 =
                Fixture.generateTestObject(4L, LocalDateTime.now().plusHours(4), 1987,  "name4");

        arr.add(1, obj4);

        //then
        assertEquals(arr.size(), 4);
        assertEquals(arr.get(1), obj4);
    }

    @Test
    public void shouldAddElementToListWhenCapacitySameSize() {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>(3);
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        var obj4 =
                Fixture.generateTestObject(4L, LocalDateTime.now().plusHours(4), 1987, "name4");

        arr.add(1, obj4);

        //then
        assertEquals(arr.size(), 4);
        assertEquals(arr.get(1), obj4);
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenAddElementToWrongIndexList() throws Exception {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>(3);
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        var obj4 =
                Fixture.generateTestObject(4L, LocalDateTime.now().plusHours(4), 1987, "name4");

        //then
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            arr.add(4, obj4);
        }, "IndexOutOfBoundsException was expected");

        Assertions.assertEquals("Index cannot be greater than the current array size = " + 3
                + " or less than 0!", thrown.getMessage());
    }

    @Test
    public void shouldGetElementByIndex() {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        //then
        assertEquals(arr.size(), 3);
        assertEquals(arr.get(2), obj3);
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenGetElementToWrongIndexList() throws Exception {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        //then
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            arr.get(3);
        }, "IndexOutOfBoundsException was expected");

        Assertions.assertEquals("Index cannot be greater than the current array size = " + 3
                + "or less than 0!", thrown.getMessage());
    }

    @Test
    public void shouldSetElementByIndexToList() {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        var obj4 =
                Fixture.generateTestObject(4L, LocalDateTime.now().plusHours(4), 1987, "name4");

        arr.set(0, obj4);

        //then
        assertEquals(arr.size(), 3);
        assertEquals(arr.get(0), obj4);
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenSetElementToWrongIndexList() throws Exception {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);

        //then
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            arr.set(3, obj3);
        }, "IndexOutOfBoundsException was expected");

        Assertions.assertEquals("Index cannot be greater than the current array size = " + 2
                + " or less than 0!", thrown.getMessage());
    }

    @Test
    public void shouldRemoveElementByIndex() {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        arr.remove(1);

        //then
        assertEquals(arr.size(), 2);
        assertEquals(arr.get(1), obj3);
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenRemoveElementToWrongIndexList() throws Exception {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        //then
        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            arr.remove(3);
        }, "IndexOutOfBoundsException was expected");

        Assertions.assertEquals("Element can't be found! Number of elements in array = " + 3
                + ". Total size of array = " + 10, thrown.getMessage());
    }

    @Test
    public void shouldReturnSizeByList() {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        //then
        assertEquals(arr.size(), 3);

    }

    @Test
    public void shouldClearList() {

        //given
        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(1), 1990, "name1");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(2), 2010, "name2");

        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(3), 2022, "name3");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);

        //then
        assertEquals(arr.size(), 3);
        arr.clear();
        assertEquals(arr.size(), 0);

    }

    @Test
    public void shouldSortToList() {

        //given
        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(1), 2022, "name3");

        var obj4 =
                Fixture.generateTestObject(4L, LocalDateTime.now().plusHours(2), 1990, "name4");

        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(3), 2012, "name1");

        var obj5 =
                Fixture.generateTestObject(5L, LocalDateTime.now().plusHours(3), 2005, "name5");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(3), 1987, "name2");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj3);
        arr.add(obj4);
        arr.add(obj1);
        arr.add(obj5);
        arr.add(obj2);

        quickSort(arr, 0, arr.size() - 1);

        //then
        assertEquals(arr.size(), 5);
        assertEquals(arr.get(0), obj1);
        assertEquals(arr.get(1), obj2);
        assertEquals(arr.get(2), obj3);
        assertEquals(arr.get(3), obj4);
        assertEquals(arr.get(4), obj5);

    }

    @Test
    public void shouldSortToListWithComparator() {

        //given
        var obj3 =
                Fixture.generateTestObject(3L, LocalDateTime.now().plusHours(1), 2022, "name3");

        var obj4 =
                Fixture.generateTestObject(4L, LocalDateTime.now().plusHours(2), 1990, "name4");

        var obj1 =
                Fixture.generateTestObject(1L, LocalDateTime.now().plusHours(3), 2012, "name1");

        var obj5 =
                Fixture.generateTestObject(5L, LocalDateTime.now().plusHours(3), 2005, "name5");

        var obj2 =
                Fixture.generateTestObject(2L, LocalDateTime.now().plusHours(3), 1987, "name2");

        //when
        CustomList<TestObject> arr = new CustomArrayList<>();
        arr.add(obj3);
        arr.add(obj4);
        arr.add(obj1);
        arr.add(obj5);
        arr.add(obj2);

        TestObjectComparator testComp = new TestObjectComparator();

        quickSort(arr, testComp, 0, arr.size() - 1);

        //then
        assertEquals(arr.size(), 5);
        assertEquals(arr.get(0), obj2);
        assertEquals(arr.get(1), obj4);
        assertEquals(arr.get(2), obj5);
        assertEquals(arr.get(3), obj1);
        assertEquals(arr.get(4), obj3);

    }


}
