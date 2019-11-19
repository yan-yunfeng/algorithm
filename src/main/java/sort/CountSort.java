package sort;

/**
 * 计数排序
 * 计数排序不基于元素比较，而是利用数组下标来确定元素的正确位置
 * 通常适用于知道元素的取值（最好是整数）范围且范围不是很大的情况的快速排序，性能甚至快过O（nlogn）的排序速度
 * 假设n为待排序数组长度，m为值得范围区间，那么该算法得时间复杂度为O(n+m)，不考虑返回值空间复杂度为O(m)
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/18 11:13
 */
public class CountSort {

    public static int[] sort(int[] array) {
        //计算数组的最大值和最小值，算出数组的取值范围
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
            if (min > array[i]) {
                min = array[i];
            }
        }
        int range = max - min;

        //统计原始数组中各值出现的次数
        int[] countArray = new int[range + 1];
        for (int value : array) {
            countArray[value - min]++;
        }
        //将统计的次数转换为排序的下标结束位
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        //倒序遍历原始数组，按照次数统计的下标结束位进行排序,这样能保证该计数排序为稳定的排序
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[countArray[array[i] - min] - 1] = array[i];
            countArray[array[i] - min]--;
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        int[] array = new int[]{345,314,576,78,56,356,345,992,267};
        for (int i :sort(array)){
            System.out.println(i);
        }
    }

}
