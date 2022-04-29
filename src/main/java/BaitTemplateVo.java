import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author zhang_fan
 * @since 2022/4/27 下午 02:39
 */
@Getter
@Setter
public class BaitTemplateVo {
    private Integer id;

    private String name;

    private String osType;

    private List<FileBaitVo> fileBaitList;

    private List<ArpBaitVo> arpBaitList;

    private List<ProcessBaitVo> processBaitList;
}
