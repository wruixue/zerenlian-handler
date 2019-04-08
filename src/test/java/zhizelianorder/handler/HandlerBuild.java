package zhizelianorder.handler;

import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/***
 *
 * @author xiongchuang
 * @date 2017-12-14
 */
public class HandlerBuild {
    private List<Class<? extends IHandler>> handlerClasses=new ArrayList<>();
    private ApplicationContext applicationContext;

    public HandlerBuild(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static HandlerBuild builder(ApplicationContext applicationContext, Class<? extends IHandler> first){
        return new HandlerBuild(applicationContext).nextHandler(first);
    }

    public static HandlerBuild builder(ApplicationContext applicationContext, Class<? extends IHandler>... handlers){
        return new HandlerBuild(applicationContext).nextHandler(handlers);
    }

    public static HandlerBuild builder(ApplicationContext applicationContext, List<Class<? extends IHandler>> handlers){
        return new HandlerBuild(applicationContext).nextHandler(handlers);
    }


    public HandlerBuild nextHandler(Class<? extends IHandler> handlerClass){
        handlerClasses.add(handlerClass);
        return this;
    }
    public HandlerBuild nextHandler(Class<? extends IHandler>... handlerClass){
        handlerClasses.addAll(Arrays.asList(handlerClass));
        return this;
    }
    public HandlerBuild nextHandler(List<Class<? extends IHandler>> handlerClass){
        handlerClasses.addAll(handlerClass);
        return this;
    }

    /**
     * 一般在预定义group时使用
     * @return
     */
    public <T> HandlerStart<T> end(){
        return new HandlerStart(this);
    }

    public HandlerContext start(HandlerContext tHandlerContext){

        for (Class<? extends IHandler> cls : handlerClasses) {

            IHandler iHandler = null;

            Map<String, ? extends IHandler> beans = applicationContext.getBeansOfType(cls);
            for (Map.Entry<String,? extends IHandler> beanEntry : beans.entrySet()) {
                IHandler value = beanEntry.getValue();
                //这有可能获取到代理类 所以使用AopUtils获取真实类
                if(AopUtils.getTargetClass(value).equals(cls)){
                    iHandler= value;
                    break;
                }
            }


            if (null != iHandler) {
                tHandlerContext = iHandler.handle(tHandlerContext);
                if (null == tHandlerContext) {
                    break;
                }
            }
        }
        return tHandlerContext;
    }


}