package ru.aston.util;

import ru.aston.CustomList;

import java.util.Comparator;

/**
 * Класс, предоставляющий метод быстрой сортировки текущего листа с типом данных, поддерживающих сортировку
 * в соответствии с Comparable.
 *
 * @author Серова Дарья
 * @version 1.0
 * @since 2023-08-15
 */

public class QuickSortUtil {

    /**
     * Метод быстрой сортировки текущего листа в соответствии с заданным порядком сортировки согласно Comparable.
     * Вызывает внутри себя приватный метод, делящий лист на два подлиста - элементы меньше опорного - слева,
     * элементы больше опорного - справа, с дальнейшей сортировкой.
     * Конструкция if() проверяет длину листа, если длина листа равна 0 или уже нечего делить.
     * Метод quickSort вызывается рекурсивно для сортировки левой и правой части после деления исходного листа.
     *
     * @param list  лист, который нужно отсортировать
     * @param start верхняя граница сортируемого листа
     * @param end   нижняя граница сортируемого листа
     * @param <T>   тип элементов содержащихся в листе
     */
    public static <T extends Comparable<? super T>> void quickSort(CustomList<T> list, int start, int end) {

        if (start >= end) {
            return;
        }

        int i = partition(list, start, end);
        quickSort(list, start, i - 1);
        quickSort(list, i + 1, end);
    }

    /**
     * Метод быстрой сортировки текущего листа в соответствии с пользовательским порядком сортировки Comparator.<p>
     * Вызывает внутри себя приватный метод, делящий лист на два подлиста - элементы меньше опорного - слева,
     * элементы больше опорного - справа, с дальнейшей сортировкой.
     * Конструкция if() проверяет длину листа, если длина листа равна 0 или уже нечего делить.
     * Метод quickSort вызывается рекурсивно для сортировки левой и правой части после деления исходного листа.
     *
     * @param list  лист, который нужно отсортировать
     * @param comp  правила сортировки заданные пользователем
     * @param start верхняя граница сортируемого листа
     * @param end   нижняя граница сортируемого листа
     * @param <T>   тип элементов содержащихся в листе
     */
    public static <T> void quickSort(CustomList<T> list, Comparator<? super T> comp, int start, int end) {

        if (start >= end) {
            return;
        }
        int i = partition(list, comp, start, end);
        quickSort(list, comp, start, i - 1);
        quickSort(list, comp, i + 1, end);
    }

    /**
     * Приватный метод деления текущего листа на два подлиста.<p>
     * После деления листа на два подлиста, возвращает индекс опорного элемента, относительно которого находятся меньшие
     * и большие элементы.<p>
     * В листе выбирается опорный элемент <Strong>pivot</Strong> (последний элемент в листе), лист разбивается на подлисты.
     * С помощью итерации, где i - индекс текущего элемента в листе, перебираются все элементы.<p>
     * Если элемент больше опорного, то поиск происходит дальше, если меньше, то найденный элемент записывается
     * в промежуточную переменную <Strong>temp</Strong> и меняется местами с элементом <Strong>swap</Strong>,
     * swap увеличивается на единицу.<p>
     * Переменная <Strong>swap</Strong> хранит индекс перемещаемого элемента. Подразумевается, что swap - это элемент,
     * который больше опорного и должен быть перемещен в правую часть исходного листа.<p>
     * Возможен случай, когда меньший элемент будет сам опорный элемент и в цикле он не будет найден. В таком случае
     * опорный элемент записывается в промежуточную переменную temp, элемент под индексом swap и опорный элемент
     * меняются местами.
     *
     * @param list  лист, который нужно отсортировать
     * @param low   нижняя граница сортируемого листа
     * @param right верхняя граница сортируемого листа
     * @param <T>   тип элементов содержащихся в листе.
     * @return swap индекс опорного элемента, делящего лист на два подлиста
     */
    private static <T extends Comparable<? super T>> int partition(CustomList<T> list, int low, int right) {

        T pivot = list.get(right);
        int swap = low;

        for (int i = low; i < right; i++) {
            if (list.get(i).compareTo(pivot) < 0) {
                T temp = list.get(i);
                list.set(i, list.get(swap));
                list.set(swap, temp);
                swap++;
            }
        }

        T temp = pivot;
        list.set(right, list.get(swap));
        list.set(swap, temp);

        return swap;
    }

    /**
     * Приватный метод деления текущего листа на два подлиста.<p>
     * После деления листа на два подлиста, возвращает индекс опорного элемента, относительно которого находятся меньшие
     * и большие элементы.<p>
     * В листе выбирается опорный элемент <Strong>pivot</Strong> (последний элемент в листе), лист разбивается на подлисты.
     * С помощью итерации, где i - индекс текущего элемента в листе, перебираются все элементы.<p>
     * В конструкции if() реализован поиск меньшего элемента относительно опорного согласно правилам сортировки
     * <Strong>comp</Strong>.
     * Если метод <Strong>compare</Strong> возвращает 1, элемент больше опорного, то поиск происходит дальше,
     * если возвращает -1, то элемент меньше опорного и найденный элемент записывается в промежуточную
     * переменную <Strong>temp</Strong> и меняется местами с элементом <Strong>swap</Strong>,
     * swap увеличивается на единицу.<p>
     * Переменная <Strong>swap</Strong> хранит индекс перемещаемого элемента. Подразумевается, что swap - это элемент,
     * который больше опорного и должен быть перемещен в правую часть исходного листа.<p>
     * Возможен случай, когда меньший элемент будет сам опорный элемент и в цикле он не будет найден. В таком случае
     * опорный элемент записывается в промежуточную переменную temp, элемент под индексом swap и опорный элемент
     * меняются местами.
     *
     * @param list  лист, который нужно отсортировать
     * @param comp  правила сортировки заданные пользователем
     * @param low   нижняя граница сортируемого листа
     * @param right верхняя граница сортируемого листа
     * @param <T>   тип элементов содержащихся в листе.
     * @return swap индекс опорного элемента, делящего лист на два подлиста
     */
    private static <T> int partition(CustomList<T> list, Comparator<? super T> comp, int low, int right) {

        T pivot = list.get(right);
        int swap = low;

        for (int i = low; i < right; i++) {
            if (comp.compare(list.get(i), pivot) < 0) {
                T temp = list.get(i);
                list.set(i, list.get(swap));
                list.set(swap, temp);
                swap++;
            }
        }

        T temp = pivot;
        list.set(right, list.get(swap));
        list.set(swap, temp);

        return swap;
    }

}