import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import net.coobird.thumbnailator.Thumbnails;

public class QiniuTest {

    @Test
    public void test() throws Exception {
        JSONObject design = JSONObject.parseObject("{\"bgheight\":711,\"headimg\":{\"width\":55.55555555555556,\"position\":\"301.1111026340061,66.66668362087674\"" +
                ",\"type\":\"circle\",\"height\":55.55555555555556},\"bgwidth\":400,\"bgimage\":\"http://cdn-ppe.ifenghuotai.cn/20210032614/4e8854f0-8dfa-11eb-9ff2-0b67a225ab4a.gif\"" +
                ",\"qrcode\":{\"width\":83.33333333333334,\"position\":\"172.22224765353732,583.3333608839247\",\"height\":83.33333333333334},\"nickname\":{\"size\":18.383002792802348" +
                ",\"position\":\"282.22224765353735,131.11111111111111\",\"rgb\":\"126, 211, 33\"}}");
        long start = System.currentTimeMillis();
        BufferedImage bufferedImage = processPoster("十二点前要睡觉", "http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLChJ53A7dn7wFOIVNabD28vakB3jTBaIaTjyUslliaPsezgIm8HCOHl1Wia0v886S5cDCcY55UDvbtg/132",
                "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQE38DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyWGdhbkJpdldkUjIxXzZyLU52Y3oAAgTGTtdfAwQAjScA", design);
        System.out.println("processPoster time cost" + (System.currentTimeMillis() - start));
        ImageIO.write(bufferedImage, "jpg", new File("/Users/truster/Desktop/somefiles/631/ss.jpg"));
    }

    public static BufferedImage processPoster(String nickname, String headImg, String qrcodeUrl, JSONObject design) throws Exception {
        long start = System.currentTimeMillis();

        String bgImage = "http://cdn-ppe.ifenghuotai.cn/20210032614/4e8854f0-8dfa-11eb-9ff2-0b67a225ab4a.gif";
        BufferedImage bufferedImage = getBufferedImage(bgImage);
        start = System.currentTimeMillis();
        if (StringUtils.isNotBlank(nickname) && design.containsKey("nickname") && !"false".equals(design.getString("nickname"))) {
            processText(bufferedImage, nickname, design.getJSONObject("nickname"));
        }

        if (StringUtils.isNotBlank(headImg) && design.containsKey("headimg") && !"false".equals(design.getString("headimg"))) {
            start = System.currentTimeMillis();
            BufferedImage headImageBuffer = getBufferedImage(headImg);
            start = System.currentTimeMillis();
            processImage(bufferedImage, headImageBuffer, design.getJSONObject("headimg"));
        }
        if (StringUtils.isNotBlank(qrcodeUrl) && design.containsKey("qrcode")) {
            start = System.currentTimeMillis();
            BufferedImage headImageBuffer = getBufferedImage(qrcodeUrl);
            start = System.currentTimeMillis();
            processImage(bufferedImage, headImageBuffer, design.getJSONObject("qrcode"));

        }
        return bufferedImage;
    }

    private static void processImage(BufferedImage bgImage, BufferedImage headImageBuffer, JSONObject design) throws IOException {
        String type = design.getOrDefault("type", "").toString();

        String[] position = design.getString("position").split(",");
        int width = design.getDouble("width").intValue();
        int height = design.getDouble("height").intValue();
        headImageBuffer = resizeImage(headImageBuffer, width, height, true);
        if ("circle".equals(type))
            headImageBuffer = transferImgForRoundImage(headImageBuffer);
        drawNewImage(bgImage, headImageBuffer, Double.valueOf(position[0]).intValue(), Double.valueOf(position[1]).intValue());
    }

    //图片转换BufferedImage对象
    public static BufferedImage getBufferedImage(String fileUrl)
            throws IOException {
        if (fileUrl.startsWith("http://") || fileUrl.startsWith("https://")) {
            return getBufferedImageDestUrl(fileUrl);
        }
        File f = new File(fileUrl);
        return ImageIO.read(f);
    }

    private static BufferedImage getBufferedImageDestUrl(String destUrl) {
        HttpURLConnection conn = null;
        BufferedImage image = null;
        try {
            URL url = new URL(destUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == 200) {
                image = ImageIO.read(conn.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert conn != null;
            conn.disconnect();
        }
        return image;
    }


    // 图片大小转换
    private static BufferedImage resizeImage(BufferedImage srcPath, int newWith, int newHeight, boolean forceSize) throws IOException {
        if (forceSize) {
            return Thumbnails.of(srcPath).forceSize(newWith, newHeight).asBufferedImage();
        } else {
            return Thumbnails.of(srcPath).width(newWith).height(newHeight).asBufferedImage();
        }
    }

    public static void processText(BufferedImage imageBuffer, String text, JSONObject design) throws IOException {
        String fontType = design.getOrDefault("type", "").toString();
        int fontSize = Double.valueOf(design.getOrDefault("size", 10).toString()).intValue();
        String[] rgb = design.getString("rgb").split(",");
        String[] position = design.getString("position").split(",");

        if (StringUtils.isBlank(fontType))
            fontType = "宋体";
        /*
        Font.PLAIN（普通）
        Font.BOLD（加粗）
        Font.ITALIC（斜体）
        Font.BOLD+ Font.ITALIC（粗斜体）
         */
        Font font = new Font(fontType, Font.PLAIN, fontSize);

        Graphics2D g = imageBuffer.createGraphics();
        g.setFont(font);
        if (rgb.length == 3)
            g.setColor(new Color(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()))); //文字
        else g.setColor(Color.WHITE);

//			// 透明度
//			float alpha = 0.9f;
//			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

        //对线段的锯齿状边缘处理
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawString(text, Double.valueOf(position[0]).intValue(), Double.valueOf(position[1]).intValue() + fontSize);
        g.dispose();

    }

    private static int getWatermarkLength(String text, Graphics g) {
        return g.getFontMetrics(g.getFont()).charsWidth(
                text.toCharArray(), 0, text.length());
    }


    public static BufferedImage transferImgForRoundImage(BufferedImage imageBuffer) {
        BufferedImage resultImg = new BufferedImage(imageBuffer.getWidth(), imageBuffer.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resultImg.createGraphics();
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, imageBuffer.getWidth(), imageBuffer.getHeight());
        // 使用 setRenderingHint 设置抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        resultImg = g.getDeviceConfiguration().createCompatibleImage(imageBuffer.getWidth(), imageBuffer.getHeight(),
                Transparency.TRANSLUCENT);
        //g.fill(new Rectangle(buffImg2.getWidth(), buffImg2.getHeight()));
        g = resultImg.createGraphics();
        // 使用 setRenderingHint 设置抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setClip(shape);
        g.drawImage(imageBuffer, 0, 0, null);
        g.dispose();

        return resultImg;
    }

    private static BufferedImage drawNewImage(BufferedImage img1, BufferedImage img2,
                                              int x, int y) throws IOException {
        Graphics2D img1Graphics = null;
        try {
            img1Graphics = img1.createGraphics();
            img1Graphics.drawImage(img2, x, y, img2.getWidth(), img2.getHeight(), null);
            img1Graphics.dispose();
        } finally {
            if (img1Graphics != null) {
                img1Graphics.dispose();
            }
        }
        return img1;
    }

    @Test
    public void test2() {
        String state = "justcode";
        if ("justcode".equals(state)) {
            System.out.println(state);
        } else {
            System.out.println("false");
        }
    }
}
