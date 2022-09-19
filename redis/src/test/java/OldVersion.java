import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class OldVersion {

/*    @Test
    public void codeBlock() {
        String text2 = """
            {
              "name": "小黑说Java",
              "age": 18,
              "address": "北京市西城区"
            }
            """;
    }*/

    /*@Test
    public void nullPointer() {
        UserInfo userInfo = new UserInfo();
        int strLength = userInfo.getNickname().length();
        System.out.println(strLength);
    }*/

    @Test
    public void test() throws ParseException {
        String start = "2022-06-27";
        String startStr = start + " 00:00:00";
        System.out.println(startStr);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(startStr);
        Long timestamp=date.getTime();
        System.out.println(timestamp);
    }

    @Test
    public void test2() {
        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(483);
        //Arrays.toString(list.toArray(Object[]::new));
        System.out.println(Arrays.toString(list.toArray(Object[]::new)).replace("[","").replace("]",""));
    }

    @Test
    public void StringToList() {
        String string = "[]";
        string = string.replace("[","").replace("]","");
        System.out.println(string);
        String str[] = string.split(",");
        if (str.length > 0 && !Objects.equals(str[0], "")) {
            int array[] = new int[str.length];
            for(int i=0;i<str.length;i++){
                array[i]=Integer.parseInt(str[i]);
            }
            List<Integer> integers = Arrays.asList(ArrayUtils.toObject(array));
            System.out.println(integers);
        } else {
            System.out.println(new ArrayList<Integer>());
        }
    }
}
