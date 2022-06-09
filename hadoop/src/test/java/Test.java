import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> tmp = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            }
            if (nums1[i] > nums2[j]) {
                j++;
            }
            if (nums1[i] == nums2[j]) {
                tmp.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[tmp.size()];
        for (int k = 0; k < tmp.size(); k++) {
            result[k] = tmp.get(k);
        }
        return result;
    }

    public static void main(String[] args)
    {
        int[] a = {1, 2, 2, 1};
        int[] b = {1, 2};
        int[] c = intersect(a, b);
        System.out.println(Arrays.toString(c));
    }
}
