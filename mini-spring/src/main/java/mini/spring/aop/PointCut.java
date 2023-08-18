/*
 * All Rights Reserved.
 *
 */
package mini.spring.aop;


/**
 * PointCut.
 *
 * @author Carl, 2023-08-18 14:43
 */
public interface PointCut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
