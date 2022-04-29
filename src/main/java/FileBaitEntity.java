import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang_fan
 * @since 2022/4/27 下午 05:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileBaitEntity {
    private Integer id;

    private String path;
}
