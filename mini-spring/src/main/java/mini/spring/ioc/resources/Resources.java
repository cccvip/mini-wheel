package mini.spring.ioc.resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public interface Resources {
    InputStream getInputStream() throws IOException;
}
