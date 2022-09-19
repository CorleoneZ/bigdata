package newFeature;

import newFeature.recordCase.UserInfo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static newFeature.Fruit.APPLE;

/**
 * Java 17 目前已经进入Rampdown Phase One阶段，所有的功能特性都已经被冻结。这说明Java 17的新特性已经定了，不会再增加新的JEP(JDK增强建议)。
 * 之所以关心Java 17是因为和Java 8、Java 11一样它是下一个LTS版本。
 */
public class NewFeature {

    /**
     * 1.文本块
     * 双引号需要进行转义;
     * 为了字符串的可读性需要通过+号连接;
     * 如果需要将JSON复制到代码中需要做大量的格式调整;
     */
    public static void codeText() {
        String text1 = "{\n" +
                "  \"name\": \"小黑说Java\",\n" +
                "  \"age\": 18,\n" +
                "  \"address\": \"北京市西城区\"\n" +
                "}";
        System.out.println(text1);

        String text2 = """
            {
              "name": "小黑说Java",
              "age": 18,
              "address": "北京市西城区"
            }
            """;
        System.out.println(text2);
    }


    /**
     * 2.switch表达式
     * 去掉break， 使用 -> 返回case内容
     * -> 后还可以跟 {} 完成多重逻辑步骤
     * yield关键字
     */

    private static void switchFeature(Fruit fruit) {
        switch (fruit) {
            case APPLE, PEAR:
                System.out.println("普通水果");
                break;
            case MANGO, AVOCADO:
                System.out.println("进口水果");
                break;
            default:
                System.out.println("未知水果");
        }

        String fruitClass = switch (fruit) {
            case APPLE, PEAR -> "普通水果";
            case MANGO, AVOCADO -> "进口水果";
            default -> "未知水果";
        };
        System.out.println(fruitClass);

    }

    /*static String formatterPatternSwitch(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> o.toString();
        };
    }

    static void formatterPatternSwitch2(Object o) {
        switch (o) {
            case null      -> throw new NullPointerException();
            case String s  -> System.out.println("String: "+s);
            case Integer i -> System.out.println("Integer");
            default  -> System.out.println("default");
        }
    }*/


    static void testVar(int score){
        var str = "string";
        var i = 10;
        System.out.println(str);
        System.out.println(i);

        var res = switch (score) {
            case  90 -> "优秀";

            default -> {   //我们可以使用花括号来将整套逻辑括起来
                //TODO
                yield  "不及格";  //处理完成后需要返回最终结果，但是这样并不是使用return，而是yield关键字
            }
        };
        System.out.println(res);
    }

    /**
     * Helpful NullPointerExceptions
     * 行号 + 调用情况
     */
    public static void nullPointerExceptions() {
        UserInfo userInfo = new UserInfo();
        int strLength = userInfo.getNickname().length();
        System.out.println(strLength);
    }

    /**
     * httpClient
     */
    public static void HttpClient() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();   //直接创建一个新的HttpClient
        //现在我们只需要构造一个Http请求实体，就可以让客户端帮助我们发送出去了（实际上就跟浏览器访问类似）
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://www.baidu.com")).build();
        //现在我们就可以把请求发送出去了，注意send方法后面还需要一个响应体处理器（内置了很多）这里我们选择ofString直接吧响应实体转换为String字符串
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //来看看响应实体是什么吧
        System.out.println(response.body());
    }


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        //codeText();

        //switchFeature(APPLE);

        //testVar(90);

        //nullPointerExceptions();

        HttpClient();

    }

}

