/**
 * ClassName: ArraySort
 * Author:   Mr lei
 * Date:     2018/3/16 20:41
 * Description: 数据结构中的8种排序算法
 * 1.简单插入排序
 * 2.简单选择排序
 * 3.冒泡排序
 * 4.快速排序
 * 5.希尔排序
 * 6.归并排序
 * 7.堆排序
 * 8.最小二叉树排序
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package arraysort;

public class ArraySort {
    /**
     * 简单插入排序算法:插入排序是基于比较的排序。所谓的基于比较，就是通过比较数组中的元素，看谁大谁小，根据结果来调整元素的位置。
     * 因此，对于这类排序，就有两种基本的操作：①比较操作； ②交换操作
     * 其中，对于交换操作，可以优化成移动操作，即不直接进行两个元素的交换，还是用一个枢轴元素(tmp)将当前元素先保存起来，然后执行移动操作，待确定了最终位置后，再将当前元素放入合适的位置。（下面的插入排序就用到了这个技巧）--因为，交换操作需要三次赋值，而移动操作只需要一次赋值！
     * 时间复杂的O(N^2),空间复杂度O(1)
     */
    public static int[] insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            int currentNum = nums[i];
            for (; j >= 0 && nums[j] > currentNum; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = currentNum;
        }
        return nums;
    }

    /**
     * 简单选择排序:首先找出数组中最小的数，与第一个数交换，然后在剩下的元素中找出最小的数，在与第二个数交换..........
     * 时间复杂度为：O(N^2)
     * 空间复杂度：O（1）
     */
    public static int[] chooseSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minNum = nums[i];//用来保存最小数
            int position = i;//用来保存最小数的下标
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < minNum) {
                    minNum = nums[j];
                    position = j;
                }
            }
            if (position != i) {
                nums[position] = nums[i];
                nums[i] = minNum;
            }
        }
        return nums;
    }

    /**
     * 冒泡排序：两两比较大小,这样能每次找出最大的数
     */
    public static int[] bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    /**
     * 快速排序:快排的策略是先找到一个基准数，一般选择数字的第一个数，然后分别从后往前与从前往后找到比它大的数放在它的右边，找出比它小的数放在它的左边
     * 时间复杂度为：O（nlgn），空间复杂度为：O（n）
     */
       public static int[] qkSort(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) qkSort(a, low, start - 1);//左边序列。第一个索引位置到关键值索引-1
        if (end < high) qkSort(a, end + 1, high);//右边序列。从关键值索引+1到最后一个
        return a;
    }

        int dummyNum = nums[low];
        int start = low;
        int end = hi;
        while (start < end) {
            //首先从后往前找出比基准数小的数，然后交换他们的位置
            while (start < end && nums[end] >= dummyNum) {
                end--;
            }
            nums[start] = nums[end];
            //从前半部分开始扫描
            while (start < end && nums[start] <= dummyNum) {
                start++;
            }
            nums[end] = nums[start];
        }
        if (start > low) qkSort(nums, low, start - 1);
        if (end < hi) qkSort(nums, end + 1, end);
        return nums;
    }

    /**
     * 希尔排序：现将待排序的数组元素分成多个子序列，使得每个子序列的元素个数相对较少，然后对各个子序列分别进行直接插入排序，待整个待排序列“基本有序”后，最后在对所有元素进行一次直接插入排序。
     * 时间复杂度：O（nlgn）
     * 空间复杂度：O（）
     */
    public static int[] shellSort(int[] nums){
        int increment=nums.length/2;
        while(increment>0){
            for(int j=increment;j<nums.length;j++){
                int temp=nums[j];
                int i=j-increment;
                while(i>=0&&temp<nums[i]){
                    nums[i+increment]=nums[i];
                    i=j-increment;
                }
                nums[i+increment]=temp;
            }
            increment=increment/2;
        }
        return nums;
    }

    /**
     * 归并排序：对于给定的一组记录，利用递归与分治技术将数据序列划分成为越来越小的半子表，在对半子表排序，最后再用递归方法将排好序的半子表合并成为越来越大的有序序列。
     * 时间复杂度：O(nlgn)
     * 空间复杂度：O(n)
     */
    public static void mergeNum(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++) {
            a[x + low] = temp[x];
        }
    }

    public static int[] sort(int[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            sort(a,low,mid);
            sort(a,mid+1,high);
            //左右归并
            mergeNum(a,low,mid,high);
        }
        return a;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        int[] nums = {-10, 100, 2, 1, 5, 8, 7, 9, 10, 0};
        System.out.println("插入排序测试");
        nums = insertSort(nums);
        for (int num : nums) {
            System.out.print(num + "-->");
        }
        //简单选择排序测试
        System.out.println("\n" + "简单选择排序");
        for (int num : chooseSort(nums)) {
            System.out.print(num + "-->");
        }
        //冒泡排序测试
        System.out.println("\n" + "冒泡排序测试");
        for (int num : bubbleSort(nums)) {
            System.out.print(num + "-->");
        }
        //快速排序测试
        System.out.println("\n" + "快速排序测试");
        for (int num : qkSort(nums, 0, nums.length - 1)) {
            System.out.print(num + "-->");
        }

        //shell排序测试
        System.out.println("\n" + "希尔排序测试");
        for (int num : shellSort(nums)) {
            System.out.print(num + "-->");
        }

        //归并排序测试
        System.out.println("\n" + "归并排序测试");
        for (int num : sort(nums,0,nums.length-1)) {
            System.out.print(num + "-->");
        }
    }
}
