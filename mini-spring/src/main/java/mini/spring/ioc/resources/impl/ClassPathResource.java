package mini.spring.ioc.resources.impl;

import mini.spring.ioc.resources.Resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class ClassPathResource implements Resources {

    private String path;

    public ClassPathResource() {
    }

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
        if (null == is) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return is;
    }

}
