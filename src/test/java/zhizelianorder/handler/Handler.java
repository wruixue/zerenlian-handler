package zhizelianorder.handler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

/**
 * Created by wangziqing on 16/9/29.
 */
public final class Handler implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
    }

    /**
     * 执行职责链
     *
     * @param handlerClasses 职责链Class
     * @param handlerContext 职责链上下文
     * @param 
     * @return
     */
    public static HandlerContext start(List<Class<? extends IHandler>> handlerClasses, HandlerContext handlerContext) {
        return HandlerBuild.builder(APPLICATION_CONTEXT,handlerClasses).start(handlerContext);
    }

    /**
     * 构建职责链
     *
     * @param handlerClass
     * @param
     * @return
     */
    public static HandlerBuild handler(Class<? extends IHandler> handlerClass) {
        return HandlerBuild.builder(APPLICATION_CONTEXT,handlerClass);
    }

    public static HandlerBuild handler(Class<? extends IHandler>... handlerClasses) {
        return HandlerBuild.builder(APPLICATION_CONTEXT,handlerClasses);
    }

    public static HandlerBuild handler(List<Class<? extends IHandler>> handlerClasses) {
        return HandlerBuild.builder(APPLICATION_CONTEXT,handlerClasses);
    }
    public static HandlerBuild nextHandler(Class<? extends IHandler> handlerClass) {
        return HandlerBuild.builder(APPLICATION_CONTEXT,handlerClass);
    }

    public static HandlerBuild nextHandler(Class<? extends IHandler>... handlerClasses) {
        return HandlerBuild.builder(APPLICATION_CONTEXT,handlerClasses);
    }

    public static HandlerBuild nextHandler(List<Class<? extends IHandler>> handlerClasses) {
        return HandlerBuild.builder(APPLICATION_CONTEXT,handlerClasses);
    }

}
