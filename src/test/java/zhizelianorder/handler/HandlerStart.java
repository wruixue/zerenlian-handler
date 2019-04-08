package zhizelianorder.handler;

/***
 *
 * @author xiongchuang
 * @date 2017-12-14
 */
public class HandlerStart<T> {

    private HandlerBuild build;

    public HandlerStart(HandlerBuild build) {
        this.build = build;
    }

    public HandlerContext<T> start(HandlerContext<T> tHandlerContext){
        return build.start(tHandlerContext);
    }


}
