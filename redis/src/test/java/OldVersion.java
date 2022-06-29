import org.junit.Test;

public class OldVersion {

    @Test
    public void codeBlock() {
        String text2 = """
            {
              "name": "小黑说Java",
              "age": 18,
              "address": "北京市西城区"
            }
            """;
    }

    @Test
    public void nullPointer() {
        UserInfo userInfo = new UserInfo();
        int strLength = userInfo.getNickname().length();
        System.out.println(strLength);
    }

}
