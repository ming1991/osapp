package net.oschina.app.base;

import android.support.annotation.NonNull;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import net.oschina.app.util.XmlUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.Call;

/**
 * 创建者:   ming001
 * 创建时间: 2016/11/2 23:39
 * 描述：    TODO
 */
public abstract class BaseProtocol<RESTYPE> {

    /**
     * 加载数据
     * reqType 请求方式,(初次,下拉,上拉)
     * @return
     */
    public void loadDataByGet(Callback callback, int reqType) {
        //在网络
        loadDataFromNetByGet(callback, reqType);
    }

    public void loadDataByPost(Callback callback, int reqType) {
        //在网络
        loadDataFromNetByPost(callback, reqType);
    }

    /**
     * 从网络获取数据
     *
     * @param callback
     * @param reqType
     * @throws IOException
     */
    private void loadDataFromNetByGet(final Callback callback, final int reqType) {  //接口回调
        //从网络加载数据
        String url = getUrl();
        //?pageIndex=0&catalog=1&pageSize=20
        OkHttpUtils
                .get()
                .url(url)
                .params(getParmasMap())  //请求参数
                .headers(getHeadersMap())  //请求头
                .build()
                .execute(new StringCallback() {  //异步
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 if (callback != null) {
                                     callback.onError(call, e, id, reqType);    //接口回调还回数据
                                 }
                             }

                             @Override
                             public void onResponse(String resXml, int id) {

                                 RESTYPE restype = parseXml(resXml);  //解析还回结果

                                 if (callback != null) {
                                     callback.onResponse(restype, id, reqType);
                                 }
                             }
                         }
                );
    }


    /**
     * 发起post请求
     *
     * @param callback
     */
    private void loadDataFromNetByPost(final Callback callback, final int reqType) {
        //从网络加载数据
        String url = getUrl();
        //?pageIndex=0&catalog=1&pageSize=20

       // File file = null;

        Map<String, File> fileMap = getFileMap();

        PostFormBuilder postFormBuilder = OkHttpUtils
                .post()
                .url(url)
                .params(getParmasMap())
                .headers(getHeadersMap());

        //遍历集合fileMap,动态添加图片

        if (fileMap != null) {//需要上传图片
            for (Map.Entry<String, File> info : fileMap.entrySet()) {
                String key = info.getKey();
                File value = info.getValue();

                //支持单个多个文件，addFile的第一个参数为文件的key，即类别表单中<input type="file" name="mFile"/>的name属性。
                postFormBuilder.addFile(key, value.getName(), value);
            }
        }

        postFormBuilder.build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 if (callback != null) {
                                     callback.onError(call, e, id, reqType);
                                 }
                             }

                             @Override
                             public void onResponse(String resXml, int id) {
                                 RESTYPE restype = parseXml(resXml);
                                 if (callback != null) {
                                     callback.onResponse(restype, id, reqType);
                                 }
                             }
                         }
                );
    }



    /**
     * 接口回调,还回网络全请求的数据
     * */
    public interface Callback<RESTYPE> {
        void onError(Call call, Exception e, int id, int reqType);

        void onResponse(RESTYPE restype, int id, int reqType);
    }

    /**
     * 基类完成统一的泛型解析
     *
     * @param resXml
     * @return
     */
    private RESTYPE parseXml(String resXml) {
        Type type = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return (RESTYPE) XmlUtils.toBean((Class) type, resXml.getBytes());
    }


    /**
     * 决定url
     *
     * @return
     */
    @NonNull
    public abstract String getUrl();


    /**
     * 传递对应的参数信息
     */
    protected Map<String, String> getParmasMap() {
        return null;
    }

    /**
     * 添加请求头
     *
     * @return
     */
    protected Map<String, String> getHeadersMap() {
        return null;
    }

    /**
     * 添加需要上传的图片
     *
     * @return
     */
    protected Map<String, File> getFileMap() {
        return null;
    }



}
