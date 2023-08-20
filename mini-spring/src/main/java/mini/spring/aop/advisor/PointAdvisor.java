package mini.spring.aop.advisor;

import mini.spring.aop.aspectj.PointCut;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public interface PointAdvisor extends Advisor {
    PointCut getPointCut();
}
