import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhang_fan
 * @since 2022/4/29 下午 03:29
 */
class BaitTemplateServiceImplTest {

    @Test
    void test() {
        // 90ms 93ms 106ms 101ms 100ms
        BaitTemplateVo baitTemplateVo = getBaitTemplateVo();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            BaitTemplateEntity convert = convertToBaitTemplateEntity(baitTemplateVo);
        }
        System.out.println("原生get,set耗时: " + (System.currentTimeMillis() - start));
    }

    @Test
    void springBeanUtilsTest() {
        // spring beanUtils
        // 249ms 270ms 290ms 237ms
        BaitTemplateVo baitTemplateVo = getBaitTemplateVo();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            BaitTemplateEntity baitTemplateEntity = new BaitTemplateEntity();
            BeanUtils.copyProperties(baitTemplateVo, baitTemplateEntity);
        }
        System.out.println("spring beanUtils耗时: " + (System.currentTimeMillis() - start));
    }

    @Test
    void apacheBeanUtilsTest() throws InvocationTargetException, IllegalAccessException {
        /*
        apache beanUtils
        3405ms 3346ms
        日志很多
         */
        BaitTemplateVo baitTemplateVo = getBaitTemplateVo();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            BaitTemplateEntity baitTemplateEntity = new BaitTemplateEntity();
            org.apache.commons.beanutils.BeanUtils.copyProperties(baitTemplateEntity, baitTemplateVo);
        }
        System.out.println("apache beanUtils耗时: " + (System.currentTimeMillis() - start));
    }

    @Test
    void hutoolBeanUtilTest() {
        /*
        hutool beanUtil
        4566ms 4118ms 4489ms
         */
        BaitTemplateVo baitTemplateVo = getBaitTemplateVo();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            BeanUtil.copyProperties(baitTemplateVo, BaitTemplateEntity.class);
        }
        System.out.println("hutool beanUtil耗时: " + (System.currentTimeMillis() - start));
    }

    @Test
    void hutoolConvertTest() {
        /*
        hutool Convert
        4921ms 4314ms 4405ms
         */
        BaitTemplateVo baitTemplateVo = getBaitTemplateVo();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Convert.convert(BaitTemplateEntity.class, baitTemplateVo);
        }
        System.out.println("hutool Convert耗时: " + (System.currentTimeMillis() - start));
    }

    private BaitTemplateVo getBaitTemplateVo() {
        BaitTemplateVo baitTemplateVo = new BaitTemplateVo();
        baitTemplateVo.setName("123");
        baitTemplateVo.setOsType("windows");
        baitTemplateVo.setId(123456);
        baitTemplateVo.setArpBaitList(new ArrayList<ArpBaitVo>() {{
            ArpBaitVo arpBaitVo1 = new ArpBaitVo();
            arpBaitVo1.setId(123);
            arpBaitVo1.setIp("1.2.3.4");
            arpBaitVo1.setMac("dd-dd-dd-dd-dd-dd");
            add(arpBaitVo1);
            ArpBaitVo arpBaitVo2 = new ArpBaitVo();
            arpBaitVo2.setId(1234);
            arpBaitVo2.setIp("1.2.3.4");
            arpBaitVo2.setMac("dd-dd-dd-dd-dd-dd");
            add(arpBaitVo2);
        }});
        baitTemplateVo.setFileBaitList(new ArrayList<FileBaitVo>() {{
            FileBaitVo fileBaitVo1 = new FileBaitVo();
            fileBaitVo1.setId(123);
            fileBaitVo1.setPath("/test");
            add(fileBaitVo1);
            FileBaitVo fileBaitVo2 = new FileBaitVo();
            fileBaitVo2.setId(1234);
            fileBaitVo2.setPath("/test");
            add(fileBaitVo2);
        }});
        baitTemplateVo.setProcessBaitList(new ArrayList<ProcessBaitVo>() {{
            ProcessBaitVo processBaitVo1 = new ProcessBaitVo();
            processBaitVo1.setId(123);
            processBaitVo1.setCommand("df -h");
            add(processBaitVo1);
            ProcessBaitVo processBaitVo2 = new ProcessBaitVo();
            processBaitVo2.setId(123);
            processBaitVo2.setCommand("df -h");
            add(processBaitVo2);
        }});
        return baitTemplateVo;
    }


    public static BaitTemplateEntity convertToBaitTemplateEntity(BaitTemplateVo item) {
        if (item == null) {
            return null;
        }
        BaitTemplateEntity result = new BaitTemplateEntity();
        result.setId(item.getId());
        result.setName(item.getName());
        result.setOsType(item.getOsType());
        List<FileBaitVo> fileBaitList = item.getFileBaitList();
        if (fileBaitList == null) {
            result.setFileBaitList(null);
        } else {
            result.setFileBaitList(fileBaitList.stream().map(BaitTemplateServiceImplTest::convertToFileBaitEntity).collect(Collectors.toList()));
        }
        List<ArpBaitVo> arpBaitList = item.getArpBaitList();
        if (arpBaitList == null) {
            result.setArpBaitList(null);
        } else {
            result.setArpBaitList(arpBaitList.stream().map(BaitTemplateServiceImplTest::convertToArpBaitEntity).collect(Collectors.toList()));
        }
        List<ProcessBaitVo> processBaitList = item.getProcessBaitList();
        if (processBaitList == null) {
            result.setProcessBaitList(null);
        } else {
            result.setProcessBaitList(processBaitList.stream().map(BaitTemplateServiceImplTest::convertToProcessBaitEntity).collect(Collectors.toList()));
        }
        return result;
    }

    public static ProcessBaitEntity convertToProcessBaitEntity(ProcessBaitVo item) {
        if (item == null) {
            return null;
        }
        ProcessBaitEntity result = new ProcessBaitEntity();
        result.setId(item.getId());
        result.setCommand(item.getCommand());
        return result;
    }

    public static ArpBaitEntity convertToArpBaitEntity(ArpBaitVo item) {
        if (item == null) {
            return null;
        }
        ArpBaitEntity result = new ArpBaitEntity();
        result.setId(item.getId());
        result.setIp(item.getIp());
        result.setMac(item.getMac());
        return result;
    }

    public static FileBaitEntity convertToFileBaitEntity(FileBaitVo item) {
        if (item == null) {
            return null;
        }
        FileBaitEntity result = new FileBaitEntity();
        result.setId(item.getId());
        result.setPath(item.getPath());
        return result;
    }
}