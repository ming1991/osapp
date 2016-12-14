package ming.webview;

/**
 * 类    名:  MyJsBinder
 * 创 建 者:  伍碧林
 * 创建时间:  2016/11/7 09:53
 * 描    述： 桥梁类
 * 描    述： 里面定义的方法是给js调用
 */
public interface IMyJsBinder {
    /*
    定义方法需要:
        1.方法的参数只能是String类型,或者没有参数
        2.api 17以以上,需要在定义的方法上面加上一个注解
     */

    /**
     * @param content
     * @des js向java本地代码问好
     * @called 在html中点击一个按钮的时候调用
     */
    public void sayHello2Java(String content);

    public void passCurImgUrl(String curImgUrl);

    public void passAllImgUrl(String allImgUrl);
}
