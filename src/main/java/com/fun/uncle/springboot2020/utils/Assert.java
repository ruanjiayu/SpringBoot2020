package com.fun.uncle.springboot2020.utils;

import com.fun.uncle.springboot2020.config.exception.BizErrorException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @Description: 断言异常类
 * @Author: Summer
 * @DateTime: 2020/8/1 5:23 下午
 * @Version: 0.0.1-SNAPSHOT
 */
public class Assert {


    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BizErrorException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new BizErrorException(message);
        }
    }


    public static void isNull(Object object) {
        isNull(object, "[Assertion failed] - the object argument must be null");
    }


    public static void notNull(Object object, String message) {
        if (object == null) {
            if (message == null) {
                message = "数据对象为空或不存在";
            }
            throw new BizErrorException(message);
        }
    }



    public static void notNull(Object object) {
        notNull(object, "数据对象为空或不存在");
    }

    public static void isMoney(Long object) {
        notNull(object, "数据对象为空或不存在");
        if (object <= 0) {
            throw new BizErrorException("金额不能为空");
        }
    }

    public static void hasLength(String text, String message) {
        if (!StringUtils.hasLength(text)) {
            if (message == null) {
                message = "数据对象为空或不存在object:" + text;
            } else {
                message = message + ",object:" + text;
            }
            throw new BizErrorException(message);
        }
    }

    /**
     * Assert that the given String is not empty; that is, it must not be {@code null} and not the empty String.
     *
     * <pre class="code">
     * Assert.hasLength(name);
     * </pre>
     *
     * @param text the String to check
     * @throws BizErrorException if the text is empty
     * @see StringUtils#hasLength
     */
    public static void hasLength(String text) {
        hasLength(text, "数据长度为空");
    }

    /**
     * Assert that the given String has valid text content; that is, it must not be {@code null} and must contain at least one non-whitespace character.
     *
     * <pre class="code">
     * Assert.hasText(name, "'name' must not be empty");
     * </pre>
     *
     * @param text the String to check
     * @throws BizErrorException if the text does not contain valid text content
     * @see StringUtils#hasText
     */
    public static void hasText(String text, String errorCodeMsg) {
        if (!StringUtils.hasText(text)) {
            throw new BizErrorException(errorCodeMsg);
        }
    }


    /**
     * Assert that the given String has valid text content; that is, it must not be {@code null} and must contain at least one non-whitespace character.
     *
     * <pre class="code">
     * Assert.hasText(name, "'name' must not be empty");
     * </pre>
     *
     * @param text the String to check
     * @throws BizErrorException if the text does not contain valid text content
     * @see StringUtils#hasText
     */
    public static void hasText(String text) {
        hasText(text, "hasText is false");
    }

    /**
     * Assert that the given text does not contain the given substring.
     *
     * <pre class="code">
     * Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");
     * </pre>
     *
     * @param textToSearch the text to search
     * @param substring    the substring to find within the text
     * @param message      the exception message to use if the assertion fails
     * @throws BizErrorException if the text contains the substring
     */
    public static void doesNotContain(String textToSearch, String substring, String message) {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
                && textToSearch.contains(substring)) {
            throw new BizErrorException(message);
        }
    }

    /**
     * Assert that the given text does not contain the given substring.
     *
     * <pre class="code">
     * Assert.doesNotContain(name, "rod");
     * </pre>
     *
     * @param textToSearch the text to search
     * @param substring    the substring to find within the text
     * @throws BizErrorException if the text contains the substring
     */
    public static void doesNotContain(String textToSearch, String substring) {
        doesNotContain(textToSearch, substring,
                "[Assertion failed] - this String argument must not contain the substring [" + substring
                        + "]");
    }

    /**
     * Assert that an array has elements; that is, it must not be {@code null} and must have at least one element.
     *
     * <pre class="code">
     * Assert.notEmpty(array, "The array must have elements");
     * </pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws BizErrorException if the object array is {@code null} or has no elements
     */
    public static void notEmpty(Object[] array, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new BizErrorException(message);
        }
    }

    /**
     * Assert that an array has elements; that is, it must not be {@code null} and must have at least one element.
     *
     * <pre class="code">
     * Assert.notEmpty(array);
     * </pre>
     *
     * @param array the array to check
     * @throws BizErrorException if the object array is {@code null} or has no elements
     */
    public static void notEmpty(Object[] array) {
        notEmpty(array, "数据不能为空");
    }

    /**
     * Assert that an array has no null elements. Note: Does not complain if the array is empty!
     *
     * <pre class="code">
     * Assert.noNullElements(array, "The array must have non-null elements");
     * </pre>
     *
     * @param array   the array to check
     * @param message the exception message to use if the assertion fails
     * @throws BizErrorException if the object array contains a {@code null} element
     */
    public static void noNullElements(Object[] array, String message) {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new BizErrorException(message);
                }
            }
        }
    }

    /**
     * Assert that an array has no null elements. Note: Does not complain if the array is empty!
     *
     * <pre class="code">
     * Assert.noNullElements(array);
     * </pre>
     *
     * @param array the array to check
     * @throws BizErrorException if the object array contains a {@code null} element
     */
    public static void noNullElements(Object[] array) {
        noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new BizErrorException(message);
        }
    }

    /**
     * Assert that a collection has elements; that is, it must not be {@code null} and must have at least one element.
     *
     * <pre class="code">
     * Assert.notEmpty(collection, "Collection must have elements");
     * </pre>
     *
     * @param collection the collection to check
     * @param message    the exception message to use if the assertion fails
     * @throws BizErrorException if the collection is {@code null} or has no elements
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BizErrorException(message);
        }
    }


    /**
     * Assert that a collection has elements; that is, it must not be {@code null} and must have at least one element.
     *
     * <pre class="code">
     * Assert.notEmpty(collection, "Collection must have elements");
     * </pre>
     *
     * @param collection the collection to check
     * @throws BizErrorException if the collection is {@code null} or has no elements
     */
    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection,
                "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    /**
     * Assert that a Map has entries; that is, it must not be {@code null} and must have at least one entry.
     *
     * <pre class="code">
     * Assert.notEmpty(map, "Map must have entries");
     * </pre>
     *
     * @param map     the map to check
     * @param message the exception message to use if the assertion fails
     * @throws BizErrorException if the map is {@code null} or has no entries
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new BizErrorException(message);
        }
    }



    /**
     * Assert that a Map has entries; that is, it must not be {@code null} and must have at least one entry.
     *
     * <pre class="code">
     * Assert.notEmpty(map);
     * </pre>
     *
     * @param map the map to check
     * @throws BizErrorException if the map is {@code null} or has no entries
     */
    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map,
                "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     *
     * <pre class="code">
     * Assert.instanceOf(Foo.class, foo);
     * </pre>
     *
     * @param clazz the required class
     * @param obj   the object to check
     * @throws BizErrorException if the object is not an instance of clazz
     * @see Class#isInstance
     */
    public static void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    /**
     * Assert that the provided object is an instance of the provided class.
     *
     * <pre class="code">
     * Assert.instanceOf(Foo.class, foo);
     * </pre>
     *
     * @param type    the type to check against
     * @param obj     the object to check
     * @param message a message which will be prepended to the message produced by the function itself, and which may be used to provide context. It should normally end in ":" or "." so that the generated message looks OK when appended to it.
     * @throws BizErrorException if the object is not an instance of clazz
     * @see Class#isInstance
     */
    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new BizErrorException(
                    (StringUtils.hasLength(message) ? message + " " : "") + "Object of class ["
                            + (obj != null ? obj.getClass().getName() : "null")
                            + "] must be an instance of " + type);
        }
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     *
     * <pre class="code">
     * Assert.isAssignable(Number.class, myClass);
     * </pre>
     *
     * @param superType the super type to check
     * @param subType   the sub type to check
     * @throws BizErrorException if the classes are not assignable
     */
    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    /**
     * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
     *
     * <pre class="code">
     * Assert.isAssignable(Number.class, myClass);
     * </pre>
     *
     * @param superType the super type to check against
     * @param subType   the sub type to check
     * @param message   a message which will be prepended to the message produced by the function itself, and which may be used to provide context. It should normally end in ":" or "." so that the generated message looks OK when appended to it.
     * @throws BizErrorException if the classes are not assignable
     */
    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new BizErrorException((StringUtils.hasLength(message) ? message + " " : "")
                    + subType + " is not assignable to " + superType);
        }
    }





}

