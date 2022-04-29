import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhang_fan
 * @since 2022/4/27 下午 04:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaitTemplateEntity {
    private Integer id;

    private String name;

    private String osType;

    private List<FileBaitEntity> fileBaitList = new ArrayList<>();

    private List<ArpBaitEntity> arpBaitList = new ArrayList<>();

    private List<ProcessBaitEntity> processBaitList = new ArrayList<>();
}
