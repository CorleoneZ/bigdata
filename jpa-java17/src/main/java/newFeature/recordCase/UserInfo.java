package newFeature.recordCase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

    private String openId;

    private String gender;

    private int groupId;

    private String group;

    private String nickname;

    private String headImageUrl;

    private String language;
}
