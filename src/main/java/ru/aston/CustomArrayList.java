package ru.aston;

/**
 * {@code CustomArrayList} реализация интерфейса {@link CustomList}.
 * Реализует все его методы, а также управляет размером массива, используемого для хранения элементов
 * внутри CustomArrayList.
 *
 * <p>Каждый экземпляр {@code CustomArrayList} имеет емкость (<i>capacity</i>). По умолчанию размер емкости 10.
 * При добавлении большего количества элементов, чем емкость {@code CustomArrayList}, она увеличивается автоматически.
 * <p>Есть возможность при создании экземпляра {@code CustomArrayList} задать свой размер емкости.
 *
 * @param <E> тип элементов в листе.
 *
 @author Серова Дарья
 @see CustomList
 @version 1.0
 @since 2023-08-15
 */

public class CustomArrayList<E> implements CustomList<E> {

    /**
     * Емкость по умолчанию.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Внутренний массив для хранения элементов CustomArrayList.
     */
    private Object[] elementData;

    /**
     * Размер CustomArrayList (количество элементов, содержащихся в CustomArrayList).
     */
    private int size;

    /**
     * Создает пустой список с начальной емкостью 10.
     */
    public CustomArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Создает пустой список с заданной емкостью.
     * @param capacity начальная емкость листа.
     * @throws IllegalArgumentException если заданная емкость отрицательная.
     */
    public CustomArrayList(int capacity) {
        if (capacity >= 0) {
            this.elementData = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Capacity can't be less than 0!");
        }
    }

    /**
     * Добавляет передаваемый элемент в конец списка.
     * Если емкость списка не позволяет добавить элемент, автоматически емкость списка увеличивается.
     * @param element  элемент, который нужно добавить в список.
     * @return {@code true} - элемент успешно добавлен.
     */
    @Override
    public boolean add(E element) {

        if (size == elementData.length) {
            elementData = incrementCapacity();
        }
        elementData[size] = element;
        size++;

        return true;
    }

    /**
     * Вставляет передаваемый элемент в список в указанную позицию.
     * Сдвигает элемент, находящийся в данный момент в этой позиции (если таковой имеется), и все последующие элементы
     * вправо (добавляет единицу к их индексам).
     * @param index позиция для вставки.
     * @param element элемент, который должен быть вставлен.
     * @throws IndexOutOfBoundsException если позиция вне диапазона {@code (index < 0 || index > size)}.
     */
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elementData.length);
        }

        if (size == elementData.length) {
            Object[] newArray = incrementCapacity();
            System.arraycopy(newArray, index, newArray, index + 1, size - index);
            newArray[index] = element;
            size++;
        }

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    /**
     * Возвращает элемент в указанной позиции списка.
     * @param index позиция искомого элемента в списке.
     * @return элемент в указанной позиции.
     * @throws IndexOutOfBoundsException если позиция вне диапазона {@code (index >= size || index < 0)}.
     */
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be greater than the current array size = " + size
                    + "or less than 0!");
        }

        return (E) elementData[index];
    }

    /**
     * Заменяет элемент в указанной позиции в списке передаваемым элементом.
     * @param index позиция замещаемого элемента.
     * @param element элемент, который будет сохранен в казанную позицию.
     * @return элемент, ранее находившийся в указанном положении.
     * @throws IndexOutOfBoundsException если позиция вне диапазона {@code (index >= size || index < 0)}.
     */
    public E set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elementData.length);
        }
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * Удаляет элемент в списке из указанной позиции.
     * @param index позиция удаляемого элемента.
     * @return элемент, который был удален из списка.
     * @throws IndexOutOfBoundsException если позиция вне диапазона {@code (index >= size || index < 0)}.
     */
    @Override
    public E remove(int index) {
        E oldElement = (E) elementData[index];

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Element can't be found! "
                    + "Number of elements in array = " + size
                    + ". Total size of array = " + elementData.length);
        }

        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return oldElement;
    }

    /**
     * Возвращает количество элементов в списке.
     * @return возвращает количество элементов в списке.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Удаляет все элементы из этого списка.
     * Список будет пустым.
     */
    @Override
    public void clear() {
        int oldSize = size;
        if (oldSize > 0) {
            for (int i = 0; i < oldSize; i++) {
                elementData[i] = null;
                size--;
            }
        }
    }

    /**
     * Создает и возвращает список с емкостью на 50% + 1 больше от емкости начального списка.
     * Копирует в созданный список элементы начального списка сохраняя порядок элементов.
     * @return список, содержащий элементы начального списка.
     */
    private Object[] incrementCapacity() {

        int newCapacity = (elementData.length * 2) + 1;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);

        return newArray;
    }

}
