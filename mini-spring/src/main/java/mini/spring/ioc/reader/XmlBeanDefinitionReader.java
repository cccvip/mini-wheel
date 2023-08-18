package mini.spring.ioc.reader;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import mini.spring.ioc.factory.bean.BeanDefinition;
import mini.spring.ioc.factory.bean.BeanReference;
import mini.spring.ioc.factory.bean.PropertyValue;
import mini.spring.ioc.factory.config.BeanDefinitionRegistry;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.resources.Resources;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    //XML tag定义,主要用途用于解析生成bean
    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    //新增两个属性解析
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }


    @Override
    public void loadBeanDefinitions(Resources resources) throws BeanException {
        try {
            InputStream inputStream = resources.getInputStream();
            doLoadBeanDefinition(inputStream);
        } catch (Exception e) {
            throw new BeanException("");
        }
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws BeanException {

        Document document = XmlUtil.readXML(inputStream);
        Element element = document.getDocumentElement();
        //解析根节点
        NodeList nodeList = element.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String nodeName = node.getNodeName();
            if (BEAN_ELEMENT.equals(nodeName)) {
                Element bean = (Element) nodeList.item(i);

                String id = bean.getAttribute(ID_ATTRIBUTE);
                String name = bean.getAttribute(NAME_ATTRIBUTE);
                String className = bean.getAttribute(CLASS_ATTRIBUTE);
                String initMethod = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
                String destroyMethod = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);
                Class<?> clazz;
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    throw new BeanException("Cannot find class [" + className + "]");
                }
                //id优先于name
                String beanName = StrUtil.isNotEmpty(id) ? id : name;
                if (StrUtil.isEmpty(beanName)) {
                    //如果id和name都为空，将类名的第一个字母转为小写后作为bean的名称
                    beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                }
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                beanDefinition.setInitMethod(initMethod);
                beanDefinition.setDestroyMethod(destroyMethod);

                for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                    Node node1 = bean.getChildNodes().item(j);
                    if (node1 instanceof Element) {
                        if (PROPERTY_ELEMENT.equals(node1.getNodeName())) {
                            //解析property标签
                            Element property = (Element) node1;
                            String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                            String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                            String refAttribute = property.getAttribute(REF_ATTRIBUTE);


                            if (StrUtil.isEmpty(nameAttribute)) {
                                throw new BeanException("The name attribute cannot be null or empty");
                            }

                            Object value = valueAttribute;
                            if (StrUtil.isNotEmpty(refAttribute)) {
                                value = new BeanReference(refAttribute);
                            }
                            PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                            beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                        }
                    }
                }

                //判断当前bean是否存在
                if (getBeanDefinitionRegistry().containsBeanDefinition(beanName)) {
                    //beanName不能重名
                    throw new BeanException("Duplicate beanName[" + beanName + "] is not allowed");
                }

                //注冊為Bean
                getBeanDefinitionRegistry().registerBeanDefinition(beanName, beanDefinition);
            }

        }
    }


}
