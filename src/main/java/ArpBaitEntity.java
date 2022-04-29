import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhang_fan
 * @since 2022/4/28 上午 10:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArpBaitEntity {
    private Integer id;

    private String ip;

    private String mac;
}
