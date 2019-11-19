package sort;

import java.util.stream.Stream;

/**
 * 基数排序,基数排序时基于计数排序的
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/18 10:10
 */
public class RadixSort {

    private static final int SINGLE_WORD_VALUE_RANGE = 128;

    /**
     *
     * @param array 待排序的数组
     * @param maxLength 待排序的数组的最大的字符串长度
     * @return 排序后的数组
     */
    public static String[] sort(String[] array, int maxLength) {

        String[] cacheArray = new String[array.length];

        //从每个字符串的最后一个字符开始比较
        for (int i = maxLength - 1; i >= 0; i--) {

            //创建计数的数组
            int[] count = new int[SINGLE_WORD_VALUE_RANGE];

            //统计每个字符串对应的i位置的字符的数量
            for (String s : array) {
                int charValue = charValue(s, i);
                count[charValue]++;
            }

            //统计数组中所有值的和即为array的length，array中对应i的字符的排序范围即为所有前面元素之和的值开始，再往后排当前元素数量个位置
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }

            //倒序遍历原始数组，利用统计数组的对应的末位的index，进行排序
            //因为相同元素的排序位置是从末位递减的，所以倒序遍历，确保排序的稳定性
            for (int j = array.length - 1; j >= 0; j--) {
                int charValue = charValue(array[j], i);
                int index = count[charValue] - 1;
                cacheArray[index] = array[j];
                count[charValue]--;
            }
            //这里一定不能直接用array = cacheArray!!
            array = cacheArray.clone();
        }
        return array;
    }

    /**
     * 给定字符串，返回指定下标的位置的字符的char的数值，如果下标越界返回0
     *
     * @param str   给定的字符串
     * @param index 下标
     * @return char值
     */
    private static int charValue(String str, int index) {
        if (index >= str.length()) {
            return 0;
        }
        return str.charAt(index);
    }

    public static void main(String[] args) {
        String[] array = new String[]{"rtdf","234fc","nbbew","pkz","567gs","nmc","ghjrd","asdfs"};
        Stream.of(sort(array,5)).forEach(System.out::println);
    }

}
