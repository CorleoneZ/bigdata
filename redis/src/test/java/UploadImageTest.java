import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class UploadImageTest {

    @Test
    public void address() {
        String address = getDownLoadUrl("cd1b9c0497b83f61/WorkflowUserInfo-L8B3Q17P47-workflow:cachedUserIds:platId:L8B3Q17P47:updateTime:2022-06-20 14:59:24:elementId:12213.zip");
        System.out.println(address);
    }

    private String getDownLoadUrl(String fileName){
        return "http://oss-cn-hangzhou.aliyuncs.com".replace("http://", String.format("https://%s.", "wechat-pro")) + "/" + fileName;
    }

    public BufferedImage StringUrlToImg(String url) throws IOException {
        if (url.startsWith("http://") || url.startsWith("https://")) {
            return getBufferedImageDestUrl(url);
        }
        File f = new File(url);
        return ImageIO.read(f);
    }

    public BufferedImage getBufferedImageDestUrl(String destUrl) {
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

    @Test
    public void saveAndUpdateUserInfo() throws Exception {
        String headImage = "https://thirdwx.qlogo.cn/mmopen/vi_32/3Rayc4WLH8RTuAHEzOiaLQ3VouSr5ibz3ZzeMibyVwOKAxXAyIKfMCuI01SkI6bLJ6gO6XVEuCXgic4p3p8hoSHD8w/132";
        //String headImage = "http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLChJ53A7dn7wFOIVNabD28vakB3jTBaIaTjyUslliaPsezgIm8HCOHl1Wia0v886S5cDCcY55UDvbtg/132";
        //String headImage = "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKKUmQ9Vu1Jmf7ny1qcxRFsiaUzOg355fwohNMpDbktMzCgcVdsOQHhP6Niby5IWj7iaicqebxg0NM75w/132";
        String nickname = "truster";
        try {
            // save image into qiniu
            System.out.println("save user image into qiniu headimagurl: " + headImage + " nickname: " + nickname);
            BufferedImage imagebuf = StringUrlToImg(headImage);
            String uploadUrl = uploadImageToQiniu(imagebuf, "jpg");
            System.out.println("upload poster to qiniu result : " + uploadUrl);

        } catch (Exception e) {
            System.out.println("maybe somewhere null : " + e);
        }
    }

    public String uploadImageToQiniu(BufferedImage imageBuffer, String type) throws IOException {
        long startTime = System.currentTimeMillis();

        InputStream is = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(imageBuffer, type, os);
        System.out.println(os);
        is = new ByteArrayInputStream(os.toByteArray());
        System.out.println(is);

        System.out.println("finish to upload image to qiniu, time cost: " + (System.currentTimeMillis() - startTime));
        return null;
    }


    @Test
    public void judge() {
        String text = " [\n" +
                "        {\n" +
                "            \"id\": \"5854d994e26546248566dd423beca65f\",\n" +
                "            \"name\": \"万涛\",\n" +
                "            \"email\": \"tony.wan@31huiyi.com\",\n" +
                "            \"phone\": \"13916121385\",\n" +
                "            \"emailGroup\": [\n" +
                "                \"tony.wan@31huiyi.com\"\n" +
                "            ],\n" +
                "            \"phoneGroup\": [\n" +
                "                \"13916121385\"\n" +
                "            ],\n" +
                "            \"headImgUrl\": \"http://wx.qlogo.cn/mmhead/PsA4KTq7ic755lqkrv0dz3mGgw0QtHvgicKyVbzDIaowA/0\",\n" +
                "            \"gender\": 1,\n" +
                "            \"lifecycle\": 2,\n" +
                "            \"score\": 0,\n" +
                "            \"source\": 3,\n" +
                "            \"remarkCount\": 0,\n" +
                "            \"show\": 1,\n" +
                "            \"businessId\": 2,\n" +
                "            \"createTime\": 1655792803368,\n" +
                "            \"updateTime\": 1655794805150,\n" +
                "            \"tags\": [],\n" +
                "            \"belong\": 4891,\n" +
                "            \"belongObj\": {\n" +
                "                \"id\": 4891,\n" +
                "                \"head\": \"http://cdn.ifenghuotai.cn/20200072813/81130d50-d097-11ea-958b-91c6b1663f7f.jpg\",\n" +
                "                \"businessId\": null,\n" +
                "                \"firstName\": \"Intessa\",\n" +
                "                \"lastName\": \"\",\n" +
                "                \"email\": \"intessawan@parllay.cn\",\n" +
                "                \"cellphone\": \"18811558705\",\n" +
                "                \"passwordHash\": null,\n" +
                "                \"passwordSecret\": null,\n" +
                "                \"userType\": null,\n" +
                "                \"isVerified\": 1,\n" +
                "                \"channel\": \"parllay\",\n" +
                "                \"createdBy\": 4758,\n" +
                "                \"status\": 1,\n" +
                "                \"createdDate\": 1582612852000,\n" +
                "                \"lastUpdatedDate\": 1653448820000,\n" +
                "                \"loginLocation\": \"北京\",\n" +
                "                \"staffId\": 54,\n" +
                "                \"socialConnectionIds\": null,\n" +
                "                \"socialConnections\": null,\n" +
                "                \"permissionIds\": null,\n" +
                "                \"teamIds\": null,\n" +
                "                \"teams\": null,\n" +
                "                \"userId\": 0,\n" +
                "                \"token\": null,\n" +
                "                \"weworkName\": null,\n" +
                "                \"weworkAvatar\": null,\n" +
                "                \"weworkEmail\": null,\n" +
                "                \"weworkMobile\": null,\n" +
                "                \"groupIds\": null,\n" +
                "                \"groups\": null,\n" +
                "                \"bind\": null,\n" +
                "                \"admin\": false,\n" +
                "                \"signed\": false\n" +
                "            },\n" +
                "            \"historyIds\": [\n" +
                "                \"8b70fe27ca3e41e0b239e6aaca8b880e\"\n" +
                "            ],\n" +
                "            \"seqId\": 6944906711568237981,\n" +
                "            \"formId\": \"AbjIKn3r\",\n" +
                "            \"customFields\": {},\n" +
                "            \"wechat\": [\n" +
                "                {\n" +
                "                    \"platId\": \"1e377292035995ec\",\n" +
                "                    \"openId\": \"ovukQ5210Ue2g1bgLWiGojJWG3_U\",\n" +
                "                    \"headImgUrl\": \"\",\n" +
                "                    \"nickname\": \"\",\n" +
                "                    \"gender\": 0,\n" +
                "                    \"countryId\": 0,\n" +
                "                    \"provinceId\": 0,\n" +
                "                    \"cityId\": 0,\n" +
                "                    \"realtimeCountryId\": 0,\n" +
                "                    \"realtimeProvinceId\": 0,\n" +
                "                    \"realtimeCityId\": 0,\n" +
                "                    \"groupId\": 0,\n" +
                "                    \"wechatTags\": [],\n" +
                "                    \"languageId\": 50,\n" +
                "                    \"subscribe\": 1,\n" +
                "                    \"subscribeTime\": 1655794800000,\n" +
                "                    \"remark\": \"\",\n" +
                "                    \"lastInteractionTime\": 1655794800000,\n" +
                "                    \"subscribeScene\": 4,\n" +
                "                    \"qrScene\": 215,\n" +
                "                    \"qrSceneStr\": \"\",\n" +
                "                    \"unionId\": \"oapSe6vOtmpDGVUzVlFezJA2Jhxg\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"eweixin\": {\n" +
                "                \"corpId\": \"wx059471abeb063951\",\n" +
                "                \"unionId\": \"oapSe6vOtmpDGVUzVlFezJA2Jhxg\",\n" +
                "                \"userId\": \"wmQoprCQAAdN104zcjBZjkvpmTqVL1SA\",\n" +
                "                \"name\": \"万涛 31\",\n" +
                "                \"avatar\": \"http://wx.qlogo.cn/mmhead/PsA4KTq7ic755lqkrv0dz3mGgw0QtHvgicKyVbzDIaowA/0\",\n" +
                "                \"type\": 1,\n" +
                "                \"gender\": 1,\n" +
                "                \"addWay\": 1,\n" +
                "                \"csGroup\": [\n" +
                "                    \"54\"\n" +
                "                ],\n" +
                "                \"addFriendTime\": 1655792798000,\n" +
                "                \"friendStatus\": 0,\n" +
                "                \"csMap\": {\n" +
                "                    \"54\": {\n" +
                "                        \"address\": \"北京市朝阳区高碑店乡华腾世纪总部公园B座7层\",\n" +
                "                        \"gender\": \"WOMAN\",\n" +
                "                        \"isLeader\": [\n" +
                "                            0\n" +
                "                        ],\n" +
                "                        \"authStatus\": \"AUTHORIZED\",\n" +
                "                        \"mobile\": \"18811558705\",\n" +
                "                        \"telephone\": \"+86-10-85402700-8850\",\n" +
                "                        \"avatar\": \"https://wework.qpic.cn/wwhead/nMl9ssowtibVGyrmvBiaibzDhaJfBOXZ2TMmeFL6ichMG76iaSaDEyJEt5wc22Uy11b3qhuGJ9eHrKTY/0\",\n" +
                "                        \"departmentIds\": [\n" +
                "                            289\n" +
                "                        ],\n" +
                "                        \"mainDepartmentName\": \"Sales & BD Team\",\n" +
                "                        \"roleList\": [\n" +
                "                            {\n" +
                "                                \"name\": \"企微SCRM-企微专业版\",\n" +
                "                                \"id\": 5615,\n" +
                "                                \"type\": 3\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 2,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4523,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4407,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4415,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Marketing Power Team\",\n" +
                "                                \"id\": 4292,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Parllay\",\n" +
                "                                \"id\": 4353,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Marketing Teams\",\n" +
                "                                \"id\": 4263,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4325,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4346,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4282,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4318,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4279,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4238,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4265,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4297,\n" +
                "                                \"type\": 1\n" +
                "                            },\n" +
                "                            {\n" +
                "                                \"name\": \"Administrators\",\n" +
                "                                \"id\": 4266,\n" +
                "                                \"type\": 1\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"qrCode\": \"https://open.work.weixin.qq.com/wwopen/userQRCode?vcode=vc51e96677114ee54a\",\n" +
                "                        \"thumbAvatar\": \"https://wework.qpic.cn/wwhead/nMl9ssowtibVGyrmvBiaibzDhaJfBOXZ2TMmeFL6ichMG76iaSaDEyJEt5wc22Uy11b3qhuGJ9eHrKTY/100\",\n" +
                "                        \"name\": \"万文达-北京\",\n" +
                "                        \"alias\": \"Intessa Wan\",\n" +
                "                        \"id\": 54,\n" +
                "                        \"position\": \"BD副总监\",\n" +
                "                        \"email\": \"intessa.wan@i-click.com\",\n" +
                "                        \"departmentNames\": [\n" +
                "                            \"Sales & BD Team\"\n" +
                "                        ],\n" +
                "                        \"mainDepartmentId\": 289,\n" +
                "                        \"status\": \"ACTIVE\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            \"sendWecom\": true,\n" +
                "            \"sendSms\": false,\n" +
                "            \"sendEmail\": false\n" +
                "        }\n" +
                "    ]";
        JSONArray array = JSONArray.parseArray(text);
        boolean flag = arrayHasEntityCid(array,"8b70fe27ca3e41e0b239e6aaca8b880e");
        System.out.println(flag);
    }
    public boolean arrayHasEntityCid(JSONArray jsonArray, String key) {
        boolean flag = false;
        for (int i = 0;i < jsonArray.size();++i) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            try {
                if (obj.get("id").equals(key) || historyIds(obj,key)) {
                    flag = true;
                }
            } catch (Exception e) {
                e.toString();
            }
        }
        return flag;
    }
    public boolean historyIds(JSONObject object, String key) {
        boolean flag = false;
        try {
            JSONArray array = (JSONArray) object.get("historyIds");
            System.out.println(array);
            if (array.contains(key)) {
                flag = true;
            }
        } catch (Exception e) {
            e.toString();
        }
        System.out.println(flag);
        return flag;
    }

}
